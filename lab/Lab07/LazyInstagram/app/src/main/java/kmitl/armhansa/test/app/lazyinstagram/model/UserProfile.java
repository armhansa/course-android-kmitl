package kmitl.armhansa.test.app.lazyinstagram.model;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import kmitl.armhansa.test.app.lazyinstagram.MainActivity;
import kmitl.armhansa.test.app.lazyinstagram.R;
import kmitl.armhansa.test.app.lazyinstagram.adapter.PostAdapter;

public class UserProfile {

    private String user;
    private String urlProfile;
    private String bio;
    private int follower;
    private int following;
    private boolean isFollow;
    private int post;

    public UserProfile() {
    }

    private Post posts[];

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public Post[] getPosts() {
        return posts;
    }

    public void setPosts(Post[] posts) {
        this.posts = posts;
    }

    public void toImagePosts(MainActivity mainActivity) {
        PostAdapter postAdapter = new PostAdapter(mainActivity);
        postAdapter.setData(posts);
        RecyclerView recyclerView = mainActivity.findViewById(R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(mainActivity, 3));
        recyclerView.setAdapter(postAdapter);
    }
}
