package com.qinyuan15.crawler.core.http;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.qinyuan15.crawler.core.html.ProxyPageParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of ProxyCrawler
 * Created by qinyuan on 14-12-29.
 */
public class ProxyCrawlerImpl implements ProxyCrawler {

    private ProxyPageParser pageParser;
    private String url;

    public void setPageParser(ProxyPageParser pageParser) {
        this.pageParser = pageParser;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public List<HttpProxy> getProxies() {
        final WebClient webClient = new WebClient();
        //HttpClientWrapper client = new HttpClientWrapper();
        List<HttpProxy> proxies =  new ArrayList<HttpProxy>();
        try {
            final HtmlPage htmlPage = webClient.getPage(url);
            System.out.println(htmlPage.getDocumentElement().getTextContent());
            //System.out.println(htmlPage.getTitleText());
            //System.out.println(htmlPage.getBody().getTextContent());
            /*
            HttpResponse response = client.get(url);
            System.out.println(response.getContent());
            */
        }catch (Exception e) {
            e.printStackTrace();
        }
        return proxies;
    }
}
