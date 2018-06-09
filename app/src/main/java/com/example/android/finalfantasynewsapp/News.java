package com.example.android.finalfantasynewsapp;


public class News {

    private String mWebPublicationDate;

    private String mWebTitle;

    private String mWebUrl;

    public News (String webTitle, String webPublicationDate, String URL) {
        mWebTitle  = webTitle;
        mWebPublicationDate = webPublicationDate;
        mWebUrl = URL;
    }
    public String getWebTitle() { return mWebTitle; }

    public String getWebPublicationDate() {
        return mWebPublicationDate;
    }
    public String getURL () {
        return mWebUrl;
    }
}
