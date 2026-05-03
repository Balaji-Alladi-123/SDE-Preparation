package Questions.SocialNetworkFaceBook.service;

import Questions.SocialNetworkFaceBook.interfaces.Post;
import Questions.SocialNetworkFaceBook.models.Notification;
import Questions.SocialNetworkFaceBook.models.User;

import java.util.*;

 class SocialNetworkingServiceDemo {
     private static final Scanner scanner = new Scanner(System.in);
     private static final FaceBookService sns = FaceBookService.getInstance();
     private static User loggedInUser;

    public static void run() throws Exception {
        while (true) {
            System.out.println("\n===== Social Networking Service Menu =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Send Friend Request");
            System.out.println("4. Accept Friend Request");
            System.out.println("5. Create Post");
            System.out.println("6. Like Post");
            System.out.println("7. Comment on Post");
            System.out.println("8. View Newsfeed");
            System.out.println("9. View Notifications");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1 -> registerUser();
                case 2 -> loginUser();
                case 3 -> sendFriendRequest();
                case 4 -> acceptFriendRequest();
                case 5 -> createPost();
                case 6 -> likePost();
                case 7 -> commentOnPost();
                case 8 -> viewNewsFeed();
                case 9 -> viewNotifications();
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void registerUser() throws Exception {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter bio: ");
        String bio = scanner.nextLine();
        User user = new User(name, email,password,bio);
        User registeredUser = sns.registerUser(user);
        System.out.println("Registered user: " + registeredUser.getName());
    }

    private static void loginUser() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        loggedInUser = sns.loginUser(email, password);
        if (loggedInUser != null) {
            System.out.println("Logged in as: " + loggedInUser.getName());
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void sendFriendRequest() {
        if (!isLoggedIn()) return;

        System.out.print("Enter friend's user ID: " );
        UUID friendId = UUID.fromString(scanner.nextLine());
        sns.sendFriendRequest(loggedInUser.getId(), friendId);
        System.out.println("Friend request sent.");
    }

    private static void acceptFriendRequest() throws Exception {
        if (!isLoggedIn()) return;

        System.out.print("Enter user ID of person who sent request: ");
        UUID requesterId =  UUID.fromString(scanner.nextLine());
        sns.acceptFriendRequest(loggedInUser.getId(), requesterId);
        System.out.println("Friend request accepted.");
    }

    private static void createPost() {
        if (!isLoggedIn()) return;

        System.out.print("Enter post content: ");
        String content = scanner.nextLine();
        System.out.print("Enter post type: ");
        String postOption = scanner.nextLine();
        Post post;
        if(Objects.equals(postOption, "Reel")) {
            post = sns.createPost(loggedInUser.getId(), content,true, false);
        }
        if(Objects.equals(postOption,"post")) {
            post = sns.createPost(loggedInUser.getId(), content,false, true);
        }
        else {
            post = null;
        }
        System.out.println("Post created with ID: " + post.getId());
    }

    private static void likePost() {
        if (!isLoggedIn()) return;

        System.out.print("Enter post ID to like: ");
        UUID postId =  UUID.fromString(scanner.nextLine());
        sns.likePost(loggedInUser.getId(), postId);
        System.out.println("Post liked.");
    }

    private static void commentOnPost() {
        if (!isLoggedIn()) return;

        System.out.print("Enter post ID to comment on: ");
        UUID postId =  UUID.fromString(scanner.nextLine());
        System.out.print("Enter comment: ");
        String comment = scanner.nextLine();
        sns.commentOnPost(loggedInUser.getId(), postId, comment);
        System.out.println("Comment added.");
    }

    private static void viewNewsFeed() {
        if (!isLoggedIn()) return;

        List<Post> newsfeed = sns.getNewsFeedPost(loggedInUser.getId());
        System.out.println("Newsfeed:");
        for (Post post : newsfeed) {
            System.out.println("Post ID: " + post.getId());
            System.out.println("Content: " + post.getContent());
            System.out.println("Likes: " + post.getLikeCount());
            System.out.println("Comments: " + post.getComments().size());
            System.out.println();
        }
    }

    private static void viewNotifications() {
        if (!isLoggedIn()) return;

        List<Notification> notifications = sns.getNotifications(loggedInUser.getId());
        System.out.println("Notifications:");
        for (Notification notification : notifications) {
            System.out.println("Type: " + notification.getType());
            System.out.println("Content: " + notification.getContent());
        }
    }

    private static boolean isLoggedIn() {
        if (loggedInUser == null) {
            System.out.println("Please login first.");
            return false;
        }
        return true;
    }

     public static void main(String[] args) throws Exception {
         SocialNetworkingServiceDemo socialNetworkingServiceDemo = new SocialNetworkingServiceDemo();
        run();
     }
}

