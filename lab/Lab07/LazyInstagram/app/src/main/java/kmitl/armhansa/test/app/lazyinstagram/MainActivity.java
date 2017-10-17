package kmitl.armhansa.test.app.lazyinstagram;

import android.content.DialogInterface;
import android.graphics.Color;
import android.preference.DialogPreference;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    private String username[] = {"cartoon", "nature"};
    private boolean isGrid;
    private ImageButton gridView, listView;
    private Button btnFollow;
    private InstagramApi instagramApi;

    private Spinner spinner;
    private TextView user, bio, post, following, follower;
    private ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isGrid = true;
        setView();
        getConnection();
        getUserProfile("android");



    }


    public void follow(View view) {

        userProfile.setFollow(!userProfile.isFollow());
        if(userProfile.isFollow()) {
            btnFollow.setText("Following");
            btnFollow.setBackgroundColor(Color.rgb(240, 240, 240));
            btnFollow.setTextSize(10);
            btnFollow.setTextColor(Color.BLUE);
        }
        else {
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Do you want to unfollow?");
            builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });
            builder.setNegativeButton("Unfollow", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    btnFollow.setBackgroundColor(Color.rgb(85, 204, 85));
                    btnFollow.setText("Follow");
                    btnFollow.setTextSize(13);
                    btnFollow.setTextColor(Color.WHITE);
                }
            });
            builder.show();

        }
    }

    public  void selectUser(View view) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Select Username");
        builder.setItems(username, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String selected = username[i];
                username[i] = userProfile.getUser();
                userProfile.setUser(selected);
                getUserProfile(userProfile.getUser());
            }
        });
        builder.create();

        builder.show();

    }

    public void switchView(View view) {
        isGrid = !isGrid;
        gridView.setEnabled(!isGrid);
        listView.setEnabled(isGrid);

        if(userProfile != null) getUserProfile(userProfile.getUser());
    }

    private void getConnection() {
        OkHttpClient client = new OkHttpClient
                .Builder()
                .build();

        Retrofit retrofit = new Retrofit
                .Builder()
                .client(client)
                .baseUrl(InstagramApi.BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        instagramApi =
                retrofit.create(InstagramApi.class);
    }

    private void getUserProfile(final String username) {

        Call<UserProfile> call = instagramApi.getProfile(username);
        call.enqueue(new Callback<UserProfile>() {


            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if(response.isSuccessful()) {
                    userProfile = response.body();
                    setIntoView();
                    toImagePosts(userProfile.getPosts());

                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }

        });
    }

    public void toImagePosts(Post posts[]) {
        PostAdapter postAdapter = new PostAdapter(MainActivity.this, isGrid);
        postAdapter.setData(posts);
        RecyclerView recyclerView = MainActivity.this.findViewById(R.id.list);
        if(isGrid) recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
        else recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(postAdapter);
    }

    private void setView() {
        user = findViewById(R.id.txtUsername);
        profileImage = findViewById(R.id.imgProfile);
        bio = findViewById(R.id.txtBio);
        post = findViewById(R.id.txtPost);
        follower = findViewById(R.id.txtFollower);
        following = findViewById(R.id.txtFollowing);
        btnFollow = findViewById(R.id.btnFollow);

        gridView = findViewById(R.id.toGrid);
        gridView.setEnabled(false);
        listView = findViewById(R.id.toList);
    }

    private void setIntoView() {
        user.setText("   "+userProfile.getUser());
        Glide.with(MainActivity.this)
                .load(userProfile.getUrlProfile()).into(profileImage);
        bio.setText("        "+userProfile.getBio());
        post.setText(String.valueOf(userProfile.getPost()));
        follower.setText(String.valueOf(userProfile.getFollower()));
        following.setText(String.valueOf(userProfile.getFollowing()));

        if(userProfile.isFollow()) {
            btnFollow.setText("Following");
            btnFollow.setBackgroundColor(Color.rgb(240, 240, 240));
            btnFollow.setTextSize(10);
            btnFollow.setTextColor(Color.BLUE);
        }
        else {

            btnFollow.setBackgroundColor(Color.rgb(85, 204, 85));
            btnFollow.setText("Follow");
            btnFollow.setTextSize(13);
            btnFollow.setTextColor(Color.WHITE);
        }

    }

}
