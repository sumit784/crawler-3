package com.qinyuan15.crawler.core.image;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test PictureUrlConverter
 * Created by qinyuan on 15-3-1.
 */
public class PictureUrlConverterTest {
    private PictureUrlConverter pictureUrlConverter;

    @Before
    public void setUp() throws Exception {
        String localAddress = "127.0.0.1";
        ImageDownloader imageDownloader = mock(ImageDownloader.class);
        when(imageDownloader.getSaveDir()).thenReturn("/var/ftp");
        pictureUrlConverter = new PictureUrlConverter(imageDownloader, localAddress);
    }

    @Test
    public void testUrlToPath() throws Exception {
        String testUrl = "ftp://127.0.0.1/hello/World.png";
        assertThat(pictureUrlConverter.urlToPath(testUrl)).isEqualTo("/var/ftp/hello/World.png");

        testUrl = "http://127.0.0.1/hello/World.png";
        assertThat(pictureUrlConverter.urlToPath(testUrl)).isEqualTo("http://127.0.0.1/hello/World.png");

        pictureUrlConverter.setUrlPrefix("http://");
        assertThat(pictureUrlConverter.urlToPath(testUrl)).isEqualTo("/var/ftp/hello/World.png");
    }

    @Test
    public void testPathToUrl() throws Exception {
        String testPath = "/var/ftp/hello/world.png";
        assertThat(pictureUrlConverter.pathToUrl(testPath)).isEqualTo("ftp://127.0.0.1/hello/world.png");

        testPath = "http://hello/world.png";
        assertThat(pictureUrlConverter.pathToUrl(testPath)).isEqualTo("http://hello/world.png");
    }
}
