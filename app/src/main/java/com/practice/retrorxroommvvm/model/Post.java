package com.practice.retrorxroommvvm.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "post_table")
public class Post {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int serial;


    @SerializedName("userId")
    @NonNull
    private int userId;

    @SerializedName("id")
    @NonNull
    private int id;

    @SerializedName("title")
    @NonNull
    private String title;

    @SerializedName("body")
    @NonNull
    private String body;


    public Post(int serial, int userId, int id, String title, String body){
        this.serial=serial;
        this.userId=userId;
        this.id=id;
        this.title=title;
        this.body=body;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
