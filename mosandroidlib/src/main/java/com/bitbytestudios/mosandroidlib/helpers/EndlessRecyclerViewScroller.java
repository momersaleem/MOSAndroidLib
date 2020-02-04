package com.bitbytestudios.mosandroidlib.helpers;//package com.sdsol.utilities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by hashmi on 11/02/2016.
 */
public abstract class EndlessRecyclerViewScroller extends RecyclerView.OnScrollListener {

    private int firstVisibleItem = 0, visibleItemCount = 0, totalItemCount = 0;
    private int mPreLast = 0;

    private LinearLayoutManager mLinearLayoutManager;

    public EndlessRecyclerViewScroller(LinearLayoutManager mLinearLayoutManager) {
        this.mLinearLayoutManager = mLinearLayoutManager;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        final int lastItem = firstVisibleItem + visibleItemCount;
        // do not make call back when items are less and not scrollable
        if (firstVisibleItem < 1) {
            return;
        }
        if (lastItem == totalItemCount) {
            if (mPreLast != lastItem) { //to avoid multiple calls for last item
                mPreLast = lastItem;
                onBottomReached();
            }
        }
    }

    public abstract void onBottomReached();
}
