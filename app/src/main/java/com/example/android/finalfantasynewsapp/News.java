package com.example.android.finalfantasynewsapp;


public class News {

    private String mWebPublicationDate;

    private String mWebTitle;

    private String mWebUrl;

    private String mSectionName;

    public News (String sectionName, String webTitle, String webPublicationDate, String URL) {

        mWebTitle  = webTitle;
        mWebPublicationDate = webPublicationDate;
        mWebUrl = URL;
        mSectionName = sectionName;

    }
    public String getSectionName() { return mSectionName; }

    public String getWebTitle() { return mWebTitle; }

    public String getWebPublicationDate() {
        return mWebPublicationDate;
    }

    public String getURL () {
        return mWebUrl;
    }
}
