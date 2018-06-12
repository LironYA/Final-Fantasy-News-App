package com.example.android.finalfantasynewsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {
    private static final String LOCATION_SEPARATOR = " of ";
    public NewsAdapter(@NonNull Context context, List<News> news) {
        super(context, 0, news);
    }
    //Return a list of the games title and the published date

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }
        News currentNews = getItem(position);
        TextView category = (TextView)listItemView.findViewById(R.id.category);
        category.setText(currentNews.getSectionName());
        TextView artical_title = (TextView) listItemView.findViewById(R.id.artical_title);
        artical_title.setText(currentNews.getWebTitle());
      //  Date date = new Date (currentNews.getWebPublicationDate());
        TextView publish_date = (TextView)listItemView.findViewById(R.id.publish_date);
      //  String formattedDate = formatDate(date);
        publish_date.setText(currentNews.getWebPublicationDate());
        return listItemView;
    }
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
}
