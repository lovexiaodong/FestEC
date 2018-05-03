package com.newpos.latte.ui.refresh;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public final class PagingBean {

    private int mPageIndex = 0;

    private int mPageTotal = 0;

    private int mPageSize = 0;

    private int mCurrentCount = 0;

    private int mDelayed = 0;

    public int getmPageIndex() {
        return mPageIndex;
    }

    public PagingBean setmPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
        return this;
    }

    public int getPageTotal() {
        return mPageTotal;
    }

    public PagingBean setPageTotal(int mPageTotal) {
        this.mPageTotal = mPageTotal;
        return this;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public PagingBean setPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
        return this;
    }

    public int getCurrentCount() {
        return mCurrentCount;
    }

    public PagingBean setCurrentCount(int mCurrentCount) {
        this.mCurrentCount = mCurrentCount;
        return this;
    }

    public int getDelayed() {
        return mDelayed;
    }

    public PagingBean setDelayed(int mDelayed) {
        this.mDelayed = mDelayed;
        return this;
    }

    public PagingBean addIndex(){
        mPageIndex++;
        return this;
    }
}
