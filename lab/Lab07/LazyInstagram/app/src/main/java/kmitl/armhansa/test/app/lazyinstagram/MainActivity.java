package kmitl.armhansa.test.app.lazyinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kmitl.armhansa.test.app.lazyinstagram.adapter.PostAdapter;
import kmitl.armhansa.test.app.lazyinstagram.api.InstagramApi;
import kmitl.armhansa.test.app.lazyinstagram.model.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String user[] = {"android", "cartoon", "nature"};
    private int pointer = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            private UserProfile userProfile;
            private TextView user;
            private ImageView profileImage;
            private TextView bio;
            private TextView post;
            private TextView following;
            private TextView follower;

            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if(response.isSuccessful()) {
                    userProfile = response.body();
                    setValue();
                    setIntoView();
                    userProfile.toImagePosts(MainActivity.this);

                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }

            private void setValue() {
                user = findViewById(R.id.txtUsername);
                profileImage = findViewById(R.id.imgProfile);
                bio = findViewById(R.id.txtBio);
                post = findViewById(R.id.txtPost);
                follower = findViewById(R.id.txtFollower);
                following = findViewById(R.id.txtFollowing);
            }

            private void setIntoView() {
                user.setText("   "+userProfile.getUser());
                Glide.with(MainActivity.this)
                        .load(userProfile.getUrlProfile()).into(profileImage);
                bio.setText("        "+userProfile.getBio());
                post.setText(String.valueOf(userProfile.getPost()));
                follower.setText(String.valueOf(userProfile.getFollower()));
                following.setText(String.valueOf(userProfile.getFollowing()));

            }
        });
    }

    public void tmpSwitchUser(View view) {
        this.pointer = (this.pointer+1)%3;

        getUserProfile(user[pointer]);
    }
}
