package com.qinyuan15.crawler.core.branch;

import com.google.common.collect.Lists;
import com.qinyuan15.crawler.dao.Branch;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test BranchGrouper
 * Created by qinyuan on 15-2-25.
 */
public class BranchGrouperTest {
    @Test
    public void testGroupByLetter() throws Exception {
        Branch branch1 = new Branch();
        branch1.setId(1);
        branch1.setFirstLetter("A");

        Branch branch2 = new Branch();
        branch2.setId(2);
        branch2.setFirstLetter("B");

        Branch branch3 = new Branch();
        branch3.setId(3);
        branch3.setFirstLetter("B");

        List<Branch> branches = Lists.newArrayList(branch1, branch2, branch3);

        BranchGrouper branchGrouper = new BranchGrouper();
        Map<String, List<Branch>> map = branchGrouper.groupByLetter(branches);
        assertThat(map).containsKeys("A", "B");
        assertThat(map.get("A")).hasSize(1);
        assertThat(map.get("B")).hasSize(2);
    }
}
