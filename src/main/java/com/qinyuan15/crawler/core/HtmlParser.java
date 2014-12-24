package com.qinyuan15.crawler.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Parse HTML
 * Created by qinyuan on 14-12-24.
 */
public class HtmlParser {

    private Document doc;

    public HtmlParser(String html) {
        doc = Jsoup.parse(html);
    }

    public String getTitle() {
        return getInnerHTML("title");
    }

    public String getInnerHTML(String tagName) {
        Elements elements = doc.getElementsByTag(tagName);
        if (elements.size() > 0) {
            return elements.get(0).html();
        } else {
            return null;
        }
    }
}
