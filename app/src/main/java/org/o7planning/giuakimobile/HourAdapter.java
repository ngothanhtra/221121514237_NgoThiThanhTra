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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder> {

    private Context context;
    private List<Weather> weatherList;

    public HourAdapter(Context context, List<Weather> weatherList) {
        this.context = context;
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public HourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hour, parent, false);
        return new HourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HourViewHolder holder, int position) {
        Weather weather = weatherList.get(position);

        // Hiển thị giờ
        String dateTime = weather.getDt_txt();
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat("ha", Locale.getDefault()); // Ví dụ: "3AM"
            Date date = inputFormat.parse(dateTime);
            holder.hourTextView.setText(outputFormat.format(date).toUpperCase());
        } catch (ParseException e) {
            e.printStackTrace();
            holder.hourTextView.setText("N/A");
        }

        // Hiển thị nhiệt độ
        double temp = weather.getMain().getTemp() - 273.15; // Chuyển từ Kelvin sang Celsius
        holder.temperatureTextView.setText(String.format("%.1f", temp));

        // Hiển thị biểu tượng thời tiết
        if (weather.getWeather() != null && !weather.getWeather().isEmpty()) {
            String iconCode = weather.getWeather().get(0).getIcon();
            String iconUrl = "https://openweathermap.org/img/w/" + iconCode + ".png";
            Glide.with(context)
                    .load(iconUrl)
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.ic_menu_report_image)
                    .into(holder.weatherIcon);
        }
    }

    @Override
    public int getItemCount() {
        return weatherList != null ? weatherList.size() : 0;
    }

    static class HourViewHolder extends RecyclerView.ViewHolder {
        TextView hourTextView, temperatureTextView;
        ImageView weatherIcon;

        public HourViewHolder(@NonNull View itemView) {
            super(itemView);
            hourTextView = itemView.findViewById(R.id.hour_text_view);
            temperatureTextView = itemView.findViewById(R.id.temperature_text_view);
            weatherIcon = itemView.findViewById(R.id.weather_icon);
        }
    }
}