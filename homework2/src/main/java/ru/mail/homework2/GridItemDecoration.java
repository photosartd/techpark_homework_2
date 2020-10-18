package ru.mail.homework2;

import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*public class GridItemDecoration extends RecyclerView.ItemDecoration {
    //Spacing between grid
    private int mSizeGridSpacingPx;
    //how many cells in line
    private int mGridSize;
    private int orientation;
    private boolean mNeedLeftSpacing;
    public GridItemDecoration(int gridSpacingPx, int gridSize, int orientation) {
        this.mSizeGridSpacingPx = gridSpacingPx;
        this.mGridSize = gridSize;
        this.orientation = orientation;
        mNeedLeftSpacing = false;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        //width of one frame
        int frameWidth;
        frameWidth = (int)((parent.getWidth() - (float) mSizeGridSpacingPx * (mGridSize - 1))/mGridSize);
        Log.d("ParentWidth", "" + parent.getWidth());
        Log.d("FrameWidth", "" + frameWidth);
        int padding = parent.getWidth()/mGridSize - frameWidth;
        //getting current position of the item
        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewAdapterPosition();
        //if its top, then connect outRect to 0
        if (itemPosition < mGridSize) {
            outRect.top = 0;
        }
        else {
            outRect.top = mSizeGridSpacingPx;
        }
        //for left item
        if (itemPosition % mGridSize == 0) {
            outRect.left = 0;
            outRect.right = padding;
            //for not left element
            mNeedLeftSpacing = true;
        } else if ((itemPosition + 1) % mGridSize == 0) {
            mNeedLeftSpacing = false;
            outRect.right = 0;
            outRect.left = padding;
        } else if (mNeedLeftSpacing) {
            mNeedLeftSpacing = false;
            outRect.left = mSizeGridSpacingPx - padding;
            if ((itemPosition + 2) % mGridSize == 0) {
                outRect.right = mSizeGridSpacingPx - padding;
            } else {
                outRect.right = mSizeGridSpacingPx / 2;
            }
        } else if ((itemPosition + 2) % mGridSize == 0) {
            mNeedLeftSpacing = false;
            outRect.left = mSizeGridSpacingPx / 2;
            outRect.right = mSizeGridSpacingPx - padding;
        } else {
            mNeedLeftSpacing = false;
            outRect.left = mSizeGridSpacingPx / 2;
            outRect.right = mSizeGridSpacingPx / 2;
        }
        outRect.bottom = 0;
    }
}*/

public class GridItemDecoration extends RecyclerView.ItemDecoration {

    //Number of columns
    private int spanCount;
    //Spacing between
    private int spacing;
    //Do we include edge or not
    private boolean includeEdge;

    public GridItemDecoration(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = spacing;
            }
            outRect.bottom = spacing; // item bottom
        } else {
            outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing; // item top
            }
        }
    }
}
