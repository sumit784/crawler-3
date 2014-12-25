package com.qinyuan15.crawler.core;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Created by qinyuan on 14-12-24.
 */
public class HtmlUnitExample {
    public static void main(String[] args) throws Exception{
        final WebClient webClient = new WebClient();
        final HtmlPage htmlPage = webClient.getPage("http://www.baidu.com");
        System.out.println(htmlPage.getTitleText());
        System.out.println(htmlPage.getBody().getTextContent());
        /*
        System.out.println(htmlPage.getTextContent());
        */
    }
}
