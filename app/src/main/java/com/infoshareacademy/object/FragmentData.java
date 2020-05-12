package com.infoshareacademy.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.jsoup.Jsoup;
@JsonIgnoreProperties(ignoreUnknown = true)

public class FragmentData {
    String html;

    public void setHtml(String html) {
        this.html = html;
    }

    public String getHtml() {
        return Jsoup.parse(html).text();
    }
}
