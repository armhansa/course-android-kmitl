package kmitl.armhansa.test.app.lazyinstagram.api;

import kmitl.armhansa.test.app.lazyinstagram.model.UserProfile;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InstagramApi {

    String BASE = "https://us-central1-retrofit-course.cloudfunctions.net";

    @GET("/getProfile")
    Call<UserProfile> getProfile(@Query("user") String username);

}
