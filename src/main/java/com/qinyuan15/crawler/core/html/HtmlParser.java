package com.qinyuan15.crawler.core.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Parse HTML
 * Created by qinyuan on 14-12-24.
 */
public class HtmlParser {

    public final static String CLASS_ATTR_KEY = "class";
    private Document doc;

    public HtmlParser(String html) {
        doc = Jsoup.parse(html);
    }

    public String getTitle() {
        return getInnerHTML("title");
    }

    public Elements getElements(String tagName) {
        return doc.getElementsByTag(tagName);
    }

    public Elements getElements(String tagName, String className) {
        Elements elements = getElements(tagName);
        Elements filteredElements = new Elements();

        for (Element element : elements) {
            if (element.hasAttr(CLASS_ATTR_KEY) && element.attr(CLASS_ATTR_KEY).equals(className)) {
                filteredElements.add(element);
            }
        }

        return filteredElements;
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
