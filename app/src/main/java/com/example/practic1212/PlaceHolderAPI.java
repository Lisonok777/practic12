package com.example.practic1212;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
public interface PlaceHolderAPI {
    @GET("posts")
    Call<List<PlaceHolderPost>> getPosts();
    @POST("posts")
    Call<Void> postComment(@Body PlaceHolderPost comm);
    @PUT("posts/{id}")
    Call<PlaceHolderPost> updateComment(@Path("id") int postId, @Body PlaceHolderPost comm);
}