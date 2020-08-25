package com.practice.retrorxroommvvm.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.practice.retrorxroommvvm.model.Post;
import com.practice.retrorxroommvvm.retrofit.PostHandler;
import com.practice.retrorxroommvvm.retrofit.RetrofitLauncher;
import com.practice.retrorxroommvvm.room.PostDao;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {

    public static PostRepository postRepository;
    PostHandler postHandler;

    private PostRepository() {
        postHandler = RetrofitLauncher.getInstance().create(PostHandler.class);
    }

    public static PostRepository getInstance() {

        if (postRepository == null) {
            postRepository = new PostRepository();
        }
        return postRepository;
    }

    public Single<Boolean> insertPost(PostDao postDao, Post post) {

        Single<Boolean> addPost = Single.create(emitter -> {

            long l = postDao.insertPost(post);
            if (l != -1)
                emitter.onSuccess(true);
            else
                emitter.onSuccess(false);
        });

        return addPost.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public LiveData<ArrayList<Post>> getAllPosts() {

        MutableLiveData<ArrayList<Post>> posts = new MutableLiveData<>();

//        postHandler.getPosts().enqueue(new Callback<ArrayList<Post>>() {
//            @Override
//            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
//                posts.setValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
//                posts.setValue(null);
//            }
//        });

        postHandler.getPosts().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(post -> {
            posts.setValue(post);
        });


        return posts;
    }

    public Single<List<Post>> getAllLocalPosts(PostDao postDao) {

        Single<List<Post>> localPostsSingle = Single.create(emitter -> {

            List<Post> localPosts = postDao.getLocalPosts();
            emitter.onSuccess(localPosts);


        });

        return localPostsSingle.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

}
