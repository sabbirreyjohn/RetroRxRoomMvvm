package com.practice.retrorxroommvvm.view.activity;

import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.retrorxroommvvm.R;
import com.practice.retrorxroommvvm.model.Post;
import com.practice.retrorxroommvvm.view.adapter.PostAdapter;
import com.practice.retrorxroommvvm.viewmodel.PostViewModel;

import java.lang.reflect.Method;
import java.util.ArrayList;

import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    PostViewModel postViewModel;
    PostAdapter adapter;
    RecyclerView rcv;
    ArrayList<Post> posts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rcv = findViewById(R.id.rcvPosts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv.setLayoutManager(layoutManager);
        rcv.setItemAnimator(new DefaultItemAnimator());

        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        postViewModel.getPosts().observe(MainActivity.this, posts -> {

            adapter = new PostAdapter(MainActivity.this, posts);
            rcv.setAdapter(adapter);
            Toast.makeText(getApplicationContext(), String.valueOf(posts.get(4).getSerial()), Toast.LENGTH_LONG).show();
            adapter.selectedPost.observe(this, post -> {
                Disposable disposable = postViewModel.insertPost(post).subscribe(success -> {
                    if (success)
                        Toast.makeText(getApplicationContext(),
                                "Save operation " + success, Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(),
                                "Save operation " + success, Toast.LENGTH_LONG).show();

                });
            });

        });


    }

    public void loadLocal(View view) {

        postViewModel.getLocalPosts().subscribe(localPosts -> {

            Toast.makeText(getApplicationContext(), localPosts.get(0).getBody(), Toast.LENGTH_LONG).show();


        });

    }
}
