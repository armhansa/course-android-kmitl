package com.example.student.lasyinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.student.lasyinstagram.adapter.PostAdapter;
import com.example.student.lasyinstagram.api.LazyInstagramApi;
import com.example.student.lasyinstagram.api.UserProfile;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUserProfile("nature");

        PostAdapter postAdapter = new PostAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(postAdapter);


    }

    private void getUserProfile(final String userName) {
        OkHttpClient client = new OkHttpClient
                            .Builder()
                            .build();
        Retrofit retrofit = new Retrofit
                .Builder()
                .client(client)
                .baseUrl(LazyInstagramApi.BASE)
                .addConverterFactory(
                        GsonConverterFactory.create())
                .build();
        LazyInstagramApi lazyInstagramApi =
                retrofit.create(LazyInstagramApi.class);
        Call<UserProfile> call = lazyInstagramApi.getProfile(userName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if(response.isSuccessful()) {
                    UserProfile userProfile = response.body();
                    TextView userName = (TextView) findViewById(R.id.txtUsername);
                    userName.setText("@"+userProfile.getUser());
                    ImageView imageProfile = (ImageView) findViewById(R.id.imageView);
                    Glide.with(MainActivity.this)
                            .load(userProfile.getUrlProfile()).into(imageProfile);
                    TextView bio = (TextView) findViewById(R.id.txtBio);
                    bio.setText(userProfile.getBio());
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }
}
