package com.arimbimega.weatherinfo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arimbimega.weatherinfo.R;
import com.arimbimega.weatherinfo.model.Forecastday;
import com.arimbimega.weatherinfo.model.Hour;
import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HourlyForecastAdapter extends RecyclerView.Adapter<HourlyForecastAdapter.ListViewHolder> {

    ArrayList<Hour> mHourArray;

    public HourlyForecastAdapter (ArrayList<Hour> hourArrayList){
        mHourArray = hourArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public HourlyForecastAdapter.ListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int viewType) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_forecast_detail, viewGroup, false);
        HourlyForecastAdapter.ListViewHolder listViewHolder = new HourlyForecastAdapter.ListViewHolder(mView);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HourlyForecastAdapter.ListViewHolder holder, int position) {

        holder.tvTimeHourly.setText(mHourArray.get(position).getTime());
        holder.tvTempHourly.setText(mHourArray.get(position).getTemp_c());
        holder.tvConditionHourly.setText(mHourArray.get(position).getConditionHour().getText());
        Glide.with(holder.itemView.getContext())
                .load("http:" + mHourArray.get(position).getConditionHour().getIcon())
                .into(holder.imgHourly);

    }

    @Override
    public int getItemCount() {
        return mHourArray.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTimeHourly, tvTempHourly, tvConditionHourly;
        public ImageView imgHourly;

        public ListViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tvTimeHourly = itemView.findViewById(R.id.item_time_forecast_hourly_detail);
            tvTempHourly = itemView.findViewById(R.id.item_temp_forecast_hourly_detail);
            tvConditionHourly = itemView.findViewById(R.id.item_condition_forecast_hourly_detail);
            imgHourly = itemView.findViewById(R.id.img_forecast_hourly_detail);
        }
    }
}
