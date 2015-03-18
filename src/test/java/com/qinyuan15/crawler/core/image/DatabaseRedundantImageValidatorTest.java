package com.qinyuan15.crawler.core.image;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

/**
 * Test DatabaseRedundantImageValidator
 * Created by qinyuan on 15-3-18.
 */
public class DatabaseRedundantImageValidatorTest {

    private DatabaseRedundantImageValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = new DatabaseRedundantImageValidator();
        validator.setColumns(Lists.newArrayList("Branch.logo"));
        validator.setThumbnailSuffixes(
                Lists.newArrayList("_thumbnail_small", "_thumbnail_middle"));
    }

    @Test
    public void testIsRedundant() throws Exception {
        String testPath = "/var/ftp/mall/branch/logo/SgktfeqFDrllgIdjnyrU_make.png";
        System.out.println(validator.isRedundant(testPath));

        testPath = "/var/ftp/mall/branch/logo/SgktfeqFDrllgIdjnyrU_make_thumbnail_middle.png";
        System.out.println(validator.isRedundant(testPath));

        testPath = "/var/ftp/mall/branch/logo/SgktfeqFDrllgIdjnyrU_make_thumbnail_middle_.png";
        System.out.println(validator.isRedundant(testPath));

        testPath = "/var/ftp/mall/branch/logo/SgktfeqFDrllgIdjnyrU_make_.png";
        System.out.println(validator.isRedundant(testPath));
    }
}
