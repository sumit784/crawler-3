package com.qinyuan15.crawler.core.branch;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test BranchGroup
 * Created by qinyuan on 15-2-25.
 */
public class BranchGroupTest {
    @Test
    public void testBuild() throws Exception {
        List<BranchGroup> branchGroups = new BranchGroupBuilder().build();
        assertThat(branchGroups).hasSize(27);
        for (BranchGroup branchGroup : branchGroups) {
            System.out.print(branchGroup.getLetter() + " ");
            System.out.println(branchGroup.getBranches().size());
        }
    }
}
