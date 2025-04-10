package org.o7planning.giuakimobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context context;
    private List<Item> newsList;

    public NewsAdapter(Context context, List<Item> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Item item = newsList.get(position);

        // Gán tiêu đề
        holder.titleTextView.setText(item.getTitle());

        // Gán thời gian (sử dụng release_date từ API)
        holder.timeTextView.setText(item.getRelease_date());

        // Gán mô tả (sử dụng overview từ API)
        holder.descriptionTextView.setText(item.getOverview());

        // Gán hình ảnh
        String posterUrl = "https://image.tmdb.org/t/p/w500" + item.getPoster_path();
        Glide.with(context)
                .load(posterUrl)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_report_image)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, timeTextView, descriptionTextView;
        ImageView imageView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.news_title);
            timeTextView = itemView.findViewById(R.id.news_time);
            descriptionTextView = itemView.findViewById(R.id.news_description);
            imageView = itemView.findViewById(R.id.news_image);
        }
    }
}