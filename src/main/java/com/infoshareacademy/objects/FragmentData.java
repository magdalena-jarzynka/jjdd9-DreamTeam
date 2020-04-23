package com.infoshareacademy.objects;

import org.jsoup.Jsoup;

public class FragmentData {
    String html;

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getFragment() {
        return Jsoup.parse(html).text();
    }
}
