package mtisfa.com.sfa.model;


import org.json.JSONObject;

import java.util.HashMap;

import mtisfa.com.sfa.model.Response.AssignmentListResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface API {
    public final String BASE_URL = "http://172.104.166.243";
    @GET("/api/method/login")
    Call<LoginDetail> login(@Query("usr") String usr, @Query("pwd") String pwd, @Query("device") String device);
    @GET("/api/resource/Assignment?fields=[\"status\",\"note\",\"date\",\"user\",\"customer\"] ")
    Call<AssignmentListResponse> getAssignment(@Query("filters") String filters);
}

