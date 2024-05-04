package com.example.practic1212;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
public class MainActivity extends AppCompatActivity {
    public final String URL_API = "https://jsonplaceholder.typicode.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button getButton = findViewById(R.id.button);
        Button postButton = findViewById(R.id.button2);
        Button putButton = findViewById(R.id.button3);
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFromApi();
            }
        });
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDataToApi();
            }
        });
        putButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putDataToApi();
            }
        });
    }
    private void getDataFromApi() {
        Retrofit retrofit = RetrofitFactory.getRetrofit(URL_API);
        PlaceHolderAPI placeholderAPI = retrofit.create(PlaceHolderAPI.class);
        Call<List<PlaceHolderPost>> call = placeholderAPI.getPosts();
        call.enqueue(new Callback<List<PlaceHolderPost>>() {
            @Override
            public void onResponse(Call<List<PlaceHolderPost>> call, Response<List<PlaceHolderPost>> response) {
                if (response.isSuccessful()) {
                    List<PlaceHolderPost> comm = response.body();
                    Log.d("Успешно", comm.get(0).getBody().toString());
                    TextView text = findViewById(R.id.text);
                    text.setText(comm.get(0).getBody().toString());
                } else {
                    Log.d("Fail", "Не удалось получить посты");
                }
            }
            @Override
            public void onFailure(Call<List<PlaceHolderPost>> call, Throwable t) {
                Log.e("Error", "Ошибка!!");
            }
        });
    }
    private void postDataToApi() {
        PlaceHolderPost newPost = new PlaceHolderPost();
        newPost.setTitle("Jack");
        newPost.setPostId(101);
        newPost.setId(501);
        newPost.setBody("Это наш пост");
        Retrofit retrofit = RetrofitFactory.getRetrofit(URL_API);
        PlaceHolderAPI placeholderAPI = retrofit.create(PlaceHolderAPI.class);
        Call<Void> createPostCall = placeholderAPI.postComment(newPost);
        createPostCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("Успешно", "Пост успешно запостился");
                    TextView text = findViewById(R.id.text);
                    text.setText(newPost.getBody());
                } else {
                    Log.d("Fail", "Пост не запостился");
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Error", "Ошибка!!");
            }
        });
    }
    private void putDataToApi() {
        PlaceHolderPost updatePost = new PlaceHolderPost();
        updatePost.setId(1);
        updatePost.setBody("Нами был обновлён пост");
        Retrofit retrofit = RetrofitFactory.getRetrofit(URL_API);
        PlaceHolderAPI placeholderAPI = retrofit.create(PlaceHolderAPI.class);
        Call<PlaceHolderPost> createPostCall = placeholderAPI.updateComment(1, updatePost);
        createPostCall.enqueue(new Callback<PlaceHolderPost>() {
            @Override
            public void onResponse(Call<PlaceHolderPost> call, Response<PlaceHolderPost> response) {
                if (response.isSuccessful()) {
                    Log.d("Успешно", "Наш пост успешно обновился!!!");
                    TextView text = findViewById(R.id.text);
                    text.setText(updatePost.getBody());
                } else {
                    Log.d("Fail", "Наш пост не смог обновиться((((");
                }
            }
            @Override
            public void onFailure(Call<PlaceHolderPost> call, Throwable t) {
                Log.e("Error", "Ошибка!!");
            }
        });
    }
}