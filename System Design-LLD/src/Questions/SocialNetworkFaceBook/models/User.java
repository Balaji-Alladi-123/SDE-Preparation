package Questions.SocialNetworkFaceBook.models;

import Questions.SocialNetworkFaceBook.enums.FriendRequestStatus;
import Questions.SocialNetworkFaceBook.interfaces.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class User {
    private final UUID id;
    private final String name;
    private final String email;
    private final String password;
    private final String bio;
    private final Set<User> friends;
    private final List<FriendRequest> friendRequests;
    private final Map<UUID, Post> postMap;
    private final List<Notification> notifications;

    public User(String name, String email, String password, String bio) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.friends = ConcurrentHashMap.newKeySet();
        this.friendRequests = Collections.synchronizedList(new ArrayList<>());
        this.postMap = new ConcurrentHashMap<>();
        this.notifications = Collections.synchronizedList(new ArrayList<>());
    }

    public void addFriend(User user) {
        friends.add(user);
    }

    public boolean isFriend(User user) {
        return friends.contains(user);
    }

    public void addPost(Post post) {
        postMap.put(post.getId(), post);
    }

    public PhotoPost getPost(UUID postId) {
        Post post = postMap.get(postId);
        if (post instanceof PhotoPost) {
            return (PhotoPost) post;
        }
        return null;
    }

    public ReelVideo getReel(UUID postId) {
        Post post = postMap.get(postId);
        if (post instanceof ReelVideo) {
            return (ReelVideo) post;
        }
        return null;
    }

    public List<PhotoPost> getListOfPhotoPosts() {
        return postMap.values().stream()
                .filter(post -> post instanceof PhotoPost)
                .map(post -> (PhotoPost) post)
                .toList();
    }

    public List<ReelVideo> getListOfReelVideos() {
        return postMap.values().stream()
                .filter(post -> post instanceof ReelVideo)
                .map(post -> (ReelVideo) post)
                .toList();
    }

    public FriendRequest getRequestFrom(User user) throws Exception {
        try {
            for (FriendRequest friendRequest : friendRequests) {
                if (friendRequest.getFrom().equals(user) && friendRequest.getStatus() == FriendRequestStatus.PENDING) {
                    return friendRequest;
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
    }

    public void receiveRequest(FriendRequest req) {
        friendRequests.add(req);
    }

    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBio() {
        return bio;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public List<FriendRequest> getFriendRequests() {
        return friendRequests;
    }

    public Map<UUID, Post> getPostMap() {
        return postMap;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public synchronized List<Post> getPosts() {
        return postMap.values().stream().toList();
    }
}
