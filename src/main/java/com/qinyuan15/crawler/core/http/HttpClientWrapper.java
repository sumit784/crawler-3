package com.qinyuan15.crawler.core.http;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.qinyuan15.crawler.dao.Proxy;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Wrap HttpClient of Apache
 * Created by qinyuan on 14-12-24.
 */
public class HttpClientWrapper {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpClientWrapper.class);
    // default connection timeout is 10 seconds
    public final static int DEFAULT_TIMEOUT = 10000;
    public final static String DEFAULT_USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.111 Safari/537.36";

    private CloseableHttpClient client;
    private Proxy proxy;
    private int timeout = DEFAULT_TIMEOUT;

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    public HttpClientWrapper() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        client = httpClientBuilder.build();
    }

    public HttpResponse get(String url) throws IOException {
        if (!url.contains("://")) {
            url = "http://" + url;
        }
        HttpGet get = new HttpGet(url);

        // set config
        RequestConfig.Builder configBuilder = RequestConfig.custom().setConnectTimeout(this.timeout);
        if (proxy != null) {
            configBuilder.setProxy(new HttpHost(proxy.getHost(), proxy.getPort(), proxy.getType()));
        }
        get.setConfig(configBuilder.build());

        CloseableHttpResponse response = client.execute(get);
        LOGGER.info("connected to {} with proxy {}", url, proxy);

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
