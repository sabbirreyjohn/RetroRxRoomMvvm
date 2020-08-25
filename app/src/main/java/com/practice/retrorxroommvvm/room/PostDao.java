package com.practice.retrorxroommvvm.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.practice.retrorxroommvvm.model.Post;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PostDao {

    @Insert
    public long insertPost(Post post);

    @Query("SELECT * from post_table")
    public List<Post> getLocalPosts();

}
