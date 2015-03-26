package com.qinyuan15.crawler.dao;

/**
 * Persist object of category and branch
 * Created by qinyuan on 15-3-25.
 */
public class CategoryBranch extends PersistObject implements Ranking {
    private Integer categoryId;
    private Integer branchId;
    private Integer ranking;

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    private boolean categoryNameInit = false;
    private String categoryName;

    public synchronized String getCategoryName() {
        if (!this.categoryNameInit) {
            Category category = new CategoryDao().getInstance(this.categoryId);
            this.categoryName = category == null ? null : category.getName();
            this.categoryNameInit = true;
        }
        return this.categoryName;
    }

    private boolean branchNameInit = false;
    private String branchName;

    public synchronized String getBranchName() {
        if (!this.branchNameInit) {
            Branch branch = new BranchDao().getInstance(this.branchId);
            this.branchName = branch == null ? null : branch.getName();
            this.branchNameInit = true;
        }
        return this.branchName;
    }
}
