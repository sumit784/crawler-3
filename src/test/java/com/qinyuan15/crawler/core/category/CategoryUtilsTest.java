package com.qinyuan15.crawler.core.category;

import com.qinyuan15.crawler.dao.Category;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test CategoryUtils
 * Created by qinyuan on 15-3-13.
 */
public class CategoryUtilsTest {
    @Test
    public void testGetIds() throws Exception {
        List<Category> categories = mockCategories();
        List<Integer> ids = CategoryUtils.getIds(categories);
        assertThat(ids).containsExactly(1, 2, 3, 4, 5);
    }

    private List<Category> mockCategories() {
        List<Category> categories = new ArrayList<Category>();
        categories.add(mockCategory(1));
        categories.add(mockCategory(2));
        categories.add(mockCategory(3));
        categories.add(mockCategory(4));
        categories.add(mockCategory(5));
        return categories;
    }

    private Category mockCategory(int id) {
        Category category = new Category();
        category.setId(id);
        return category;
    }

    @Test
    public void testGetIdsString() throws Exception {
        List<Category> categories = mockCategories();
        String idsString = CategoryUtils.getIdsString(categories);
        assertThat(idsString).isEqualTo("1,2,3,4,5");
    }
}
