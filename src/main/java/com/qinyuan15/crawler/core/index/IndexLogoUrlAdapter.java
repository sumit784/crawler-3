package com.qinyuan15.crawler.core.index;

import com.qinyuan15.crawler.core.image.ImageDownloader;
import com.qinyuan15.crawler.core.image.PictureUrlConverter;
import com.qinyuan15.crawler.dao.IndexLogo;

import java.util.List;

/**
 * Adjust url of branch
 * Created by qinyuan on 15-2-25.
 */
public class IndexLogoUrlAdapter {
    private PictureUrlConverter pictureUrlConverter;

    public IndexLogoUrlAdapter(PictureUrlConverter pictureUrlConverter) {
        this.pictureUrlConverter = pictureUrlConverter;
    }

    public IndexLogoUrlAdapter(ImageDownloader imageDownloader, String localAddress) {
        this(new PictureUrlConverter(imageDownloader, localAddress));
    }

    public List<IndexLogo> adjust(List<IndexLogo> indexLogos) {
        for (IndexLogo indexLogo : indexLogos) {
            adjust(indexLogo);
        }
        return indexLogos;
    }

    public IndexLogo adjust(IndexLogo indexLogo) {
        indexLogo.setPath(this.pictureUrlConverter.pathToUrl(indexLogo.getPath()));
        return indexLogo;
    }
}
