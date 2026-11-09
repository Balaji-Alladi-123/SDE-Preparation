package Questions.DigitalWallet;

import Questions.DigitalWallet.Repository.TransactionRepository;
import Questions.DigitalWallet.Repository.UserRepository;
import Questions.DigitalWallet.Repository.WalletRepository;
import Questions.DigitalWallet.Repository.impl.TransactionRepoImpl;
import Questions.DigitalWallet.Repository.impl.UserRepoImpl;
import Questions.DigitalWallet.Repository.impl.WalletRepoImpl;
import Questions.DigitalWallet.controller.AdminController;
import Questions.DigitalWallet.controller.TransactionController;
import Questions.DigitalWallet.controller.WalletController;
import Questions.DigitalWallet.domain.Transaction;
import Questions.DigitalWallet.domain.TransactionStatus;
import Questions.DigitalWallet.domain.User;
import Questions.DigitalWallet.service.LockService;
import Questions.DigitalWallet.service.TransactionService;
import Questions.DigitalWallet.service.WalletService;
import Questions.DigitalWallet.service.gateway.MockPaymentGatewayProvider;
import Questions.DigitalWallet.service.gateway.PaymentGatewayRouter;
import Questions.DigitalWallet.service.notification.EmailNotificationChannel;
import Questions.DigitalWallet.service.notification.NotificationRouter;
import Questions.DigitalWallet.service.notification.SmsNotificationChannel;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
public class PaymentWalletDemo {
    public static void main(String[] args) {
            System.out.println("=== DIGITAL WALLET SYSTEM SIMULATION ===\n");

            // Initialize repositories
            UserRepository userRepository = new UserRepoImpl();
            WalletRepository walletRepository = new WalletRepoImpl();
            TransactionRepository transactionRepository = new TransactionRepoImpl();

            // Initialize infrastructure services
            LockService lockService = new LockService(); // TODO: Replace with Redis-based distributed lock

            PaymentGatewayRouter paymentGatewayRouter = new PaymentGatewayRouter();
            paymentGatewayRouter.register(new MockPaymentGatewayProvider()); // TODO: Register real PG provider(s)

            NotificationRouter notificationRouter = new NotificationRouter();
            notificationRouter.register("email", new EmailNotificationChannel()); // TODO: Replace with real email provider
            notificationRouter.register("sms", new SmsNotificationChannel());     // TODO: Replace with Twilio

            // Initialize domain services
            WalletService walletService = new WalletService(walletRepository);
            TransactionService transactionService = new TransactionService(
                    transactionRepository, walletRepository, lockService, paymentGatewayRouter, notificationRouter
            );

            // Initialize controllers
            WalletController walletController = new WalletController(walletService, transactionService);
            TransactionController transactionController = new TransactionController(transactionService);
            AdminController adminController = new AdminController(walletService);

            // Simulation flow
            System.out.println("1. Creating users...");
            User userA = new User(UUID.randomUUID().toString(), "alice", "alice@example.com", "Alice", System.currentTimeMillis());
            userRepository.save(userA);
            User userB = new User(UUID.randomUUID().toString(), "bob", "bob@example.com", "Bob", System.currentTimeMillis());
            userRepository.save(userB);
            System.out.println("Users created: " + userA + " | " + userB);

            System.out.println("\n2. Creating wallets...");
            var walletA = walletController.createWallet(userA.getId());
            var walletB = walletController.createWallet(userB.getId());
            System.out.println("Wallet A: " + walletA);
            System.out.println("Wallet B: " + walletB);

            System.out.println("\n3. Initiating deposit for Wallet A...");
            Map<String, String> paymentDetails = new HashMap<>();
            // TODO: populate card/UPI details as needed
            Transaction depTx = transactionController.initiateDeposit(walletA.getAccountNumber(), 50000, "CARD", "mock", paymentDetails);
            System.out.println("Deposit initiated: " + depTx);

            System.out.println("\n4. Simulating payment success callback...");
            transactionController.handlePaymentCallback(depTx.getProviderRef(), TransactionStatus.COMPLETED);
            System.out.println("Deposit completed. Balance A: " + walletController.getBalance(walletA.getAccountNumber()));

            System.out.println("\n5. Transfer from Wallet A to Wallet B...");
            Transaction transferTx = transactionController.transfer(walletA.getAccountNumber(), walletB.getAccountNumber(), 20000, "Pay Bob");
            System.out.println("Transfer completed: " + transferTx);
            System.out.println("Balance A: " + walletController.getBalance(walletA.getAccountNumber()));
            System.out.println("Balance B: " + walletController.getBalance(walletB.getAccountNumber()));

            System.out.println("\n6. Withdrawal request from Wallet B...");
            Transaction withdrawTx = transactionController.withdraw(walletB.getAccountNumber(), 10000, "Withdraw to bank");
            System.out.println("Withdrawal initiated: " + withdrawTx);

            System.out.println("\n7. Statement for Wallet A...");
            var statementA = walletController.getStatement(walletA.getAccountNumber(), null, null);
            System.out.println("Statement A: tx count = " + statementA.getTransactions().size() + ", balance = " + statementA.getCurrentBalanceMinor());

            System.out.println("\n8. Suspend Wallet B...");
            adminController.suspendWallet(walletB.getAccountNumber());
            System.out.println("Wallet B suspended. Trying another transfer (should fail)...");
            try {
                transactionController.transfer(walletA.getAccountNumber(), walletB.getAccountNumber(), 1000, "Test after suspend");
            } catch (Exception ex) {
                System.out.println("Expected failure: " + ex.getMessage());
            }

            System.out.println("\n=== SIMULATION COMPLETED ===");
        }
    }
