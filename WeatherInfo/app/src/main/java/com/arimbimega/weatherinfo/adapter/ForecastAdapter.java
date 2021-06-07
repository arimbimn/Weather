package com.arimbimega.weatherinfo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arimbimega.weatherinfo.R;
import com.arimbimega.weatherinfo.model.Forecast;
import com.arimbimega.weatherinfo.model.Forecastday;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ListViewHolder> {

    List<Forecastday> mforecastdays;

    //Onclick
    private OnItemClickCallback onItemClickCallback;

    //Onclick Method
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public ForecastAdapter(List<Forecastday> forecastdays){

        mforecastdays = forecastdays;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ForecastAdapter.ListViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup viewGroup, int viewType) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_forecast, viewGroup, false);
        ListViewHolder listViewHolder = new ListViewHolder(mView);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ForecastAdapter.ListViewHolder holder, int position) {

        holder.fcDay.setText("On Date " + mforecastdays.get(position).getDate());
        holder.avgtemp.setText("Temp " + Double.toString(mforecastdays.get(position).getDayForecast().getAvgtemp_c()));
        holder.fcCondition.setText(mforecastdays.get(position).getDayForecast().getConditionForecast().getText());

        Glide.with(holder.itemView.getContext())
                .load("http:" + mforecastdays.get(position).getDayForecast().getConditionForecast().getIcon())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.imgForecast);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemClickCallback.onItemClicked(mforecastdays.get(holder.getAdapterPosition()));

            }
        });

    }

    @Override
    public int getItemCount() {
        return mforecastdays.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgForecast;
        public TextView fcDay, fcCondition, avgtemp;

        public ListViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            imgForecast = itemView.findViewById(R.id.img_forecast);
            fcDay = itemView.findViewById(R.id.item_forecast_day);
            avgtemp = itemView.findViewById(R.id.item_avgtemp);
            fcCondition = itemView.findViewById(R.id.item_forecast_condition);

        }
    }

    //Onclick Interface
    public interface OnItemClickCallback {
        void onItemClicked(Forecastday data);
    }
}
