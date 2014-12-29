package com.qinyuan15.crawler.core.html;

import com.qinyuan15.crawler.core.http.HttpProxy;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Parse page of http://pachong.org
 * Created by qinyuan on 14-12-29.
 */
public class PachongPageParser extends AbstractProxyPageParser {

    @Override
    public List<HttpProxy> getProxies() {
        List<HttpProxy> proxies = new ArrayList<HttpProxy>();

        HtmlParser parser = new HtmlParser(this.html);
        Elements elements = parser.getElements("table", "tb");
        if (elements.size() == 0) {
            return proxies;
        }

        Element tableElement = elements.get(0) ;

        System.out.println(elements.get(0).html());
        return null;
    }

    @Override
    public List<String> getSubLinks() {
        return null;
    }
}
