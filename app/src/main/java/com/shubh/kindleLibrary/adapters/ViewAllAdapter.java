package com.shubh.kindleLibrary.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shubh.kindleLibrary.R;
import com.shubh.kindleLibrary.ViewAllActivity;
import com.shubh.kindleLibrary.models.ViewAllModel;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ExViewHolder> {

    ViewAllActivity context;
    private List<ViewAllModel> list;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onArrowClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {mListener=listener;}

    public static class ExViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView, ArrowImg;
        public TextView name,description, price, rating, stock;

        public ExViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.view_img);
            name = itemView.findViewById(R.id.view_name);
            description = itemView.findViewById(R.id.view_description);
            rating = itemView.findViewById(R.id.view_rating);
            price = itemView.findViewById(R.id.view_price);
            stock = itemView.findViewById(R.id.view_stock_db);
            ArrowImg = itemView.findViewById(R.id.arrow_img);

            itemView.setOnClickListener((v) -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });

            ArrowImg.setOnClickListener((v) -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onArrowClick(position);
                    }
                }
            });
        }
    }


    public ViewAllAdapter(ViewAllActivity context, List<ViewAllModel> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public ExViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_books, parent, false);
        ExViewHolder evh = new ExViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExViewHolder holder, int position) {
        ViewAllModel currentItem = list.get(position);

        Glide.with(context).load(currentItem.getImg_url()).into(holder.imageView);
        holder.name.setText(currentItem.getName());
        holder.description.setText(currentItem.getDescription());
        holder.rating.setText(currentItem.getRating());
        holder.price.setText(currentItem.getPrice()+"$");
        holder.stock.setText("Stock : "+currentItem.getAvailability());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}

