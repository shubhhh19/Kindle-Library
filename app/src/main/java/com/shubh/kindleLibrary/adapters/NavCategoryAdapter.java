package com.shubh.kindleLibrary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shubh.kindleLibrary.R;
import com.shubh.kindleLibrary.models.NavCategoryModel;

import java.util.List;

public class NavCategoryAdapter extends RecyclerView.Adapter<NavCategoryAdapter.ViewHolder> {

    Context context;
    List<NavCategoryModel> list;
    private onItemClickListener mlistener;

    public interface onItemClickListener {
        public void MyTTS(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        mlistener = listener;
    }




    public NavCategoryAdapter(Context context, List<NavCategoryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_cat_book,parent,false),mlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(list.get(position).getName());
        holder.description.setText(list.get(position).getDescription());
        holder.stock.setText(list.get(position).getStock());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView, recView;
        TextView name, description, stock;

        public ViewHolder(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cat_nav_img);
            name = itemView.findViewById(R.id.nav_cat_name);
            description = itemView.findViewById(R.id.nav_cat_description);
            stock = itemView.findViewById(R.id.nav_cat_stock);
            recView = itemView.findViewById(R.id.cat_nav_voice);

            recView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        listener.MyTTS(position);
                    }
                }
            });

        }
    }
}
