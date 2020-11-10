package com.example.tiisetsosemaushu.mensa;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>
{
    List<Comment> comments;




    public CommentAdapter(Context context, List<Comment> list)
    {
        comments = list;

    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView tvComment, tvName,tvCreated,tvMealType;

        public ViewHolder(View itemView) {
            super(itemView);

            tvComment = itemView.findViewById(R.id.tvComment);
            tvMealType = itemView.findViewById(R.id.tvMealType);
            tvName = itemView.findViewById(R.id.tvNameComment);


            tvCreated = itemView.findViewById(R.id.tvCreated);


        }
    }
    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list_view_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {

        holder.itemView.setTag(comments.get(position));

        holder.tvComment.setText(comments.get(position).getComment());
        holder.tvName.setText("By: "+comments.get(position).getName());
        holder.tvCreated.setText("On: "+comments.get(position).getCreated1());
        holder.tvMealType.setText(comments.get(position).getMealType());

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}
