package Questions.DigitalWallet.controller;

import Questions.DigitalWallet.domain.AccountStatement;
import Questions.DigitalWallet.domain.Wallet;
import Questions.DigitalWallet.service.TransactionService;
import Questions.DigitalWallet.service.WalletService;

public class WalletController {
    private WalletService walletService;
    private TransactionService transactionService;

    public WalletController(WalletService walletService, TransactionService transactionService) {
        this.walletService = walletService;
        this.transactionService = transactionService;
    }

    public Wallet createWallet(String userId) {
        return walletService.createWallet(userId);
    }

    public long getBalance(String accountNumber) {
        return walletService.getByAccountNumber(accountNumber).getBalanceMinor();
    }

    public AccountStatement getStatement(String accountNumber, Long startUtc, Long endUtc) {
        return transactionService.getStatement(accountNumber, startUtc, endUtc);
    }
}


