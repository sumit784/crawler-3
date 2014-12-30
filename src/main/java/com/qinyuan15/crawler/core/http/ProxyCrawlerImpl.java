package com.qinyuan15.crawler.core.http;

import com.qinyuan15.crawler.core.html.ProxyPageParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of ProxyCrawler
 * Created by qinyuan on 14-12-29.
 */
public class ProxyCrawlerImpl implements ProxyCrawler {

    private ProxyPageParser pageParser;
    private String host;
    private String path = "";

    public void setPageParser(ProxyPageParser pageParser) {
        this.pageParser = pageParser;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public List<HttpProxy> getProxies() {
        return getProxies(this.host + "/" + this.path);
    }

    private List<HttpProxy> getProxies(String rootUrl) {
        List<HttpProxy> proxies = new ArrayList<HttpProxy>();
        HttpClientWrapper client = new HttpClientWrapper();
        try {
            HttpResponse response = client.get(rootUrl);

            pageParser.setHTML(response.getContent());
            proxies.addAll(pageParser.getProxies());

            for (String subUrl : pageParser.getSubLinks()) {
                String fullSubUrl;
                if (subUrl.startsWith("http://") || subUrl.startsWith("wwww.")) {
                    fullSubUrl = subUrl;
                } else if (subUrl.startsWith("/")) {
                    fullSubUrl = this.host + subUrl;
                } else {
                    fullSubUrl = rootUrl.replace("[^/]+$", "") + subUrl;
                }
                proxies.addAll(getProxies(fullSubUrl));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proxies;
    }
}
