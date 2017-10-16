package kmitl.armhansa.test.app.lazyinstagram;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kmitl.armhansa.test.app.lazyinstagram.adapter.PostAdapter;
import kmitl.armhansa.test.app.lazyinstagram.api.InstagramApi;
import kmitl.armhansa.test.app.lazyinstagram.model.Post;
import kmitl.armhansa.test.app.lazyinstagram.model.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private UserProfile userProfile;
    private String user[] = {"android", "cartoon", "nature"};
    private int pointer = 0;
    private boolean isGrid;
    private ImageButton gridView, listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isGrid = true;
        getUserProfile(user[pointer]);

        gridView = findViewById(R.id.toGrid);
        gridView.setEnabled(false);
        listView = findViewById(R.id.toList);

    }

    public void selectUser(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragmentSwitchUser, new SwitchUserFragment())
                .commit();

    }

    public void switchUser(String username) {
        this.pointer = (this.pointer+1)%3;

        getUserProfile(user[pointer]);
    }

    public void switchView(View view) {
        isGrid = !isGrid;
        gridView.setEnabled(!isGrid);
        listView.setEnabled(isGrid);

        getUserProfile(user[pointer]);
    }



    private void getUserProfile(final String username) {
        OkHttpClient client = new OkHttpClient
                .Builder()
                .build();

        Retrofit retrofit = new Retrofit
                .Builder()
                .client(client)
                .baseUrl(InstagramApi.BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InstagramApi instagramApi =
                retrofit.create(InstagramApi.class);
        Call<UserProfile> call = instagramApi.getProfile(username);
        call.enqueue(new Callback<UserProfile>() {
            private TextView user;
            private ImageView profileImage;
            private TextView bio;
            private TextView post;
            private TextView following;
            private TextView follower;
            private Button isFollowing;

            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if(response.isSuccessful()) {
                    userProfile = response.body();
                    setValue();
                    setIntoView();
                    toImagePosts(userProfile.getPosts());


                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }

            public void toImagePosts(Post posts[]) {
                PostAdapter postAdapter = new PostAdapter(MainActivity.this, isGrid);
                postAdapter.setData(posts);
                RecyclerView recyclerView = MainActivity.this.findViewById(R.id.list);
                if(isGrid) recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
                else recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(postAdapter);
            }

            private void setValue() {
                user = findViewById(R.id.txtUsername);
                profileImage = findViewById(R.id.imgProfile);
                bio = findViewById(R.id.txtBio);
                post = findViewById(R.id.txtPost);
                follower = findViewById(R.id.txtFollower);
                following = findViewById(R.id.txtFollowing);
                isFollowing = findViewById(R.id.btnFollow);
            }

            private void setIntoView() {
                user.setText("   "+userProfile.getUser());
                Glide.with(MainActivity.this)
                        .load(userProfile.getUrlProfile()).into(profileImage);
                bio.setText("        "+userProfile.getBio());
                post.setText(String.valueOf(userProfile.getPost()));
                follower.setText(String.valueOf(userProfile.getFollower()));
                following.setText(String.valueOf(userProfile.getFollowing()));

                isFollowing.setEnabled(!userProfile.isFollow());
                if(userProfile.isFollow()) {
                    isFollowing.setText("Following");
                    isFollowing.setBackgroundColor(Color.rgb(240, 240, 240));
                    isFollowing.setTextSize(10);
                    isFollowing.setTextColor(Color.BLUE);
                }
                else {

                    isFollowing.setBackgroundColor(Color.rgb(85, 204, 85));
                    isFollowing.setText("Follow");
                    isFollowing.setTextSize(13);
                    isFollowing.setTextColor(Color.WHITE);
                }

            }
        });
    }

}
