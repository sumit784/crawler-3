package com.qinyuan15.crawler.core.http;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

/**
 * Wrap HttpClient of Apache
 * Created by qinyuan on 14-12-24.
 */
public class HttpClientWrapper {

    public final static String DEFAULT_PROXY_TYPE = "http";
    public final static int DEFAULT_PROXY_PORT = 80;

    private CloseableHttpClient client;
    private String proxyHost;
    private int proxyPort = DEFAULT_PROXY_PORT;
    private String proxyType = DEFAULT_PROXY_TYPE;

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public void setProxyType(String proxyType) {
        this.proxyType = proxyType;
    }

    public void setProxy(HttpProxy proxy) {
        if (proxy != null) {
            setProxyHost(proxy.getHost());
            setProxyPort(proxy.getPort());
        }
    }

    public HttpClientWrapper() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        client = httpClientBuilder.build();
    }

    private void setProxy(HttpGet get) {
        HttpHost proxy = new HttpHost(proxyHost, proxyPort, proxyType);
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
        get.setConfig(config);
    }

    public HttpResponse get(String url) throws IOException {
        if (!url.contains("://")) {
            url = "http://" + url;
        }
        HttpGet get = new HttpGet(url);
        if (proxyHost != null) {
            setProxy(get);
        }
        CloseableHttpResponse response = client.execute(get);
        String content = EntityUtils.toString(response.getEntity());
        int status = response.getStatusLine().getStatusCode();
        return new HttpResponse(content, status);
    }

    public String getContent(String url) throws IOException {
        return get(url).getContent();
    }

    public void download(String url, String fileName) throws IOException {
        download(url, new File(fileName));
    }

    public void download(String url, File file) throws IOException {
        String content = getContent(url);
        FileUtils.write(file, content);
    }
}
