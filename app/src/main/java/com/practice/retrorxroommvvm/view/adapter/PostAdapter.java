package com.practice.retrorxroommvvm.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.practice.retrorxroommvvm.R;
import com.practice.retrorxroommvvm.model.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.TheViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    List<Post> posts;
    public MutableLiveData<Post> selectedPost;

    public PostAdapter(Context context, List<Post> posts) {
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.posts = posts;
        selectedPost = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public TheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.row_text_layout, parent, false);
        TheViewHolder holder = new TheViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TheViewHolder holder, int position) {
        holder.textView.setText(posts.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class TheViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        MaterialButton materialButton;

        public TheViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvBody);
            materialButton = itemView.findViewById(R.id.mbSave);
            materialButton.setOnClickListener(v -> {
                selectedPost.setValue(posts.get(getAdapterPosition()));
            });
        }
    }
}
