package com.example.android.finalfantasynewsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {
    private static final String FF_NEWS = "https://content.guardianapis.com/search?section=games&from-date=2017-01-01&to-date=2018-01-01&order-by=newest&use-date=published&q=%22final%20fantasy%22&api-key=c018038d-3e83-48f8-9208-c09dd8c08614";
    private ProgressBar progressBar;
    private int LOADER_ID = 1;
    // TextView that is displayed when the list is empty
    private TextView emptyStateTextView;
    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView newsView = (ListView) findViewById(R.id.list);
        // If there's no data to display, show the empty_view TextBox
        emptyStateTextView = (TextView) findViewById(R.id.empty_view);
        newsView.setEmptyView(emptyStateTextView);
        //Progress Bar
        progressBar = (ProgressBar) findViewById(R.id.loading_spinner);
        //Check if there is internet connection
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();
            // Initialize the loader.
            loaderManager.initLoader(LOADER_ID, null, this);
            // Find a reference to the {@link ListView} in the layout
        } else {
            progressBar.setVisibility(View.GONE);
            emptyStateTextView.setText("No internet connection");
        }
        adapter = new NewsAdapter(MainActivity.this, new ArrayList<News>());
        newsView.setAdapter(adapter);
        newsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                News currentNews = adapter.getItem(position);
                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri newsUri = Uri.parse(currentNews.getURL());

                // Create a new intent
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(MainActivity.this, FF_NEWS);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        progressBar.setVisibility(View.GONE);
        emptyStateTextView.setText("No news found");
        adapter.clear();

        if(news != null && !news.isEmpty()) {
            adapter.addAll(news);
        }
    }
    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        adapter.clear();
    }
}
