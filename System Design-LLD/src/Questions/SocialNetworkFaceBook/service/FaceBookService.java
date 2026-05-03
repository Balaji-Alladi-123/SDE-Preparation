package Questions.SocialNetworkFaceBook.service;

import Questions.SocialNetworkFaceBook.enums.NotificationType;
import Questions.SocialNetworkFaceBook.interfaces.Post;
import Questions.SocialNetworkFaceBook.models.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class FaceBookService {
    private static FaceBookService instance;

    private final Map<UUID, User> userMap;
    private final Map<UUID, Post> postMap;
    private static final Logger logger = Logger.getLogger(FaceBookService.class.getName());


    FaceBookService() {
        userMap = new ConcurrentHashMap<>();
        postMap = new ConcurrentHashMap<>();
    }

    public static synchronized FaceBookService getInstance() {
        if (instance == null) {
            instance = new FaceBookService();
        }
        return instance;
    }

    public synchronized User registerUser(User user) throws Exception {
        try {
            if (!userMap.containsKey(user.getId())) {
                userMap.put(user.getId(), user);
                return user;
            } else {
                logger.info("User is already exists" + user.getName());
                return user;
            }
        } catch (UnknownError error) {
            throw new Exception(error.getMessage());
        }
    }

    public User loginUser(String email, String password) {
        for (User user : userMap.values()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void sendFriendRequest(UUID fromId, UUID toId) {
        User fromUser = userMap.get(fromId);
        User toUser = userMap.get(toId);
        if (fromUser == null || toUser == null) {
            throw new IllegalArgumentException("User not found");
        }
        if (fromUser.isFriend(toUser)) {
            return;
        }
        FriendRequest friendRequest = new FriendRequest(fromUser, toUser);
        toUser.receiveRequest(friendRequest);
        sendNotification(toUser, NotificationType.FRIEND_REQUEST, "Friend request from: " + fromUser.getName());
    }

    private void sendNotification(User receiver, NotificationType type, String notificationText) {
        Notification notification = new Notification(receiver, type, notificationText);
        receiver.addNotification(notification);
    }

    void acceptFriendRequest(UUID fromId, UUID toId) throws Exception {
        User fromUser = userMap.get(fromId);
        User toUser = userMap.get(toId);
        if (fromUser == null || toUser == null) {
            throw new IllegalArgumentException("User not found");
        }
        if (fromUser.isFriend(toUser)) {
            return;
        }
        FriendRequest friendRequest = toUser.getRequestFrom(fromUser);
        if (friendRequest == null) throw new IllegalArgumentException("No pending request");
        friendRequest.acceptRequest();
        toUser.addFriend(fromUser);
        fromUser.addFriend(toUser);
        sendNotification(fromUser, NotificationType.FRIEND_REQUEST_ACCEPTED, "You are now friends" + toUser.getName());
    }

    Post createPost(UUID userId, String content, boolean isReel, boolean isPost) {
        User user = userMap.get(userId);
        if (user == null) {
            throw new IllegalArgumentException("No User found");
        }
        Post post;
        if (isReel) {
            post = new ReelVideo(user, content);
        } else {
            post = new PhotoPost(user, content);
        }

        user.addPost(post);
        postMap.put(post.getId(), post);
        return post;
    }

    public void likePost(UUID userId, UUID postId) {
        User user = userMap.get(userId);
        Post post = postMap.get(postId);
        post.addLike(user);
        sendNotification(post.getAuthor(), NotificationType.LIKE, "Your post was liked by " + user.getName());
    }

    public void commentOnPost(UUID userId, UUID postId, String text) {
        User user = userMap.get(userId);
        Post post = postMap.get(postId);
        post.addComment(user, text);
        sendNotification(post.getAuthor(), NotificationType.COMMENT, "Your post received a comment from " + user.getName());
    }

    public List<Post> getNewsFeedPost(UUID userId) {
        User user = userMap.get(userId);
        if(user == null) throw new IllegalArgumentException("No User found");
      return   user.getFriends().stream()
                .flatMap(friend -> friend.getPosts().stream())
                .sorted(Comparator.comparing(Post::getTimestamp).reversed())
                .limit(20)
                .toList();
    }

    public List<Notification> getNotifications(UUID userId) {
        return userMap.get(userId).getNotifications();
    }
}
