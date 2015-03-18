package com.qinyuan15.crawler.core.image;

import com.qinyuan15.crawler.dao.HibernateUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to validate redundancy of images by database
 * Created by qinyuan on 15-3-18.
 */
public class DatabaseRedundantImageValidator implements RedundantImageValidator {
    private List<ImageColumn> columns;
    private List<String> thumbnailSuffixes;

    public void setColumns(List<String> tableColumns) {
        this.columns = new ArrayList<>();
        for (String tableColumn : tableColumns) {
            String[] tableColumnArray = tableColumn.split("\\.");
            if (tableColumnArray.length >= 2) {
                ImageColumn imageColumn = new ImageColumn(tableColumnArray[0], tableColumnArray[1]);
                this.columns.add(imageColumn);
            }
        }
    }

    public void setThumbnailSuffixes(List<String> thumbnailSuffixes) {
        this.thumbnailSuffixes = thumbnailSuffixes;
    }

    @Override
    public boolean isRedundant(String imagePath) {
        if (!StringUtils.hasText(imagePath)) {
            return false;
        }

        if (this.columns == null || this.columns.size() == 0) {
            return false;
        }

        if (this.thumbnailSuffixes != null) {
            for (String thumbnailSuffix : this.thumbnailSuffixes) {
                imagePath = imagePath.replace(thumbnailSuffix, "");
            }
        }

        for (ImageColumn column : this.columns) {
            long count = HibernateUtil.getCount(column.table,
                    column.column + "='" + StringEscapeUtils.escapeSql(imagePath) + "'");
            if (count > 0) {
                return false;
            }
        }
        return true;
    }

    private static class ImageColumn {
        String table;
        String column;

        ImageColumn(String table, String column) {
            this.table = table;
            this.column = column;
        }
    }
}
