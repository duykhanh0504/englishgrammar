package com.Tornado.englishgrammar.fast_scroller;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.Tornado.englishgrammar.R;

public class FastScroller extends LinearLayout {

    private static final int STYLE_NONE = -1;
    private final RecyclerViewScrollListener scrollListener = new RecyclerViewScrollListener(this);
    private RecyclerView recyclerView;
    private ImageView handle;
    private int handleColor;
    private int scrollerOrientation;
    private int maxVisibility;
    private boolean manuallyChangingPosition;
    private ScrollerViewProvider viewProvider;

    public FastScroller(Context context) {
        this(context, null);
    }

    public FastScroller(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FastScroller(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setClipChildren(false);
        TypedArray style = context.obtainStyledAttributes(attrs, R.styleable.fastscroll__fastScroller, R.attr.fastscroll__style, 0);
        try {
            handleColor = style.getColor(R.styleable.fastscroll__fastScroller_fastscroll__handleColor, STYLE_NONE);
        } finally {
            style.recycle();
        }
        maxVisibility = getVisibility();
        setViewProvider(new DefaultScrollerViewProvider());
    }

    public void setViewProvider(ScrollerViewProvider viewProvider) {
        removeAllViews();
        this.viewProvider = viewProvider;
        viewProvider.setFastScroller(this);
        handle = viewProvider.provideHandleView(this);
        addView(handle);
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        recyclerView.addOnScrollListener(scrollListener);
        invalidateVisibility();
        recyclerView.setOnHierarchyChangeListener(new OnHierarchyChangeListener() {
            @Override
            public void onChildViewAdded(View parent, View child) {
                invalidateVisibility();
            }

            @Override
            public void onChildViewRemoved(View parent, View child) {
                invalidateVisibility();
            }
        });
    }

    @Override
    public void setOrientation(int orientation) {
        scrollerOrientation = orientation;
        super.setOrientation(orientation == HORIZONTAL ? VERTICAL : HORIZONTAL);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        initHandleMovement();
        applyStyling();
        if (!isInEditMode()) {
            scrollListener.updateHandlePosition(recyclerView);
        }
    }

    private void applyStyling() {
        if(handleColor!=STYLE_NONE) setBackgroundTint(handle, handleColor);
    }

    private void setBackgroundTint(ImageView view, int color) {
        final Drawable background = DrawableCompat.wrap(view.getBackground());
        if(background==null) return;
        DrawableCompat.setTint(background.mutate(), color);
        Utils.setBackground(view, background);
    }

    private void initHandleMovement() {
        handle.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                requestDisallowInterceptTouchEvent(true);
                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    if(event.getAction() == MotionEvent.ACTION_DOWN) viewProvider.onHandleGrabbed();
                    manuallyChangingPosition = true;
                    float relativePos = getRelativeTouchPosition(event);
                    setScrollerPosition(relativePos);
                    setRecyclerViewPosition(relativePos);
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    manuallyChangingPosition = false;
                    viewProvider.onHandleReleased();
                    return true;
                }
                return false;
            }
        });
    }

    private float getRelativeTouchPosition(MotionEvent event){
        if(isVertical()){
            float yInParent = event.getRawY() - Utils.getViewRawY(handle);
            return yInParent / (getHeight() - handle.getHeight());
        } else {
            float xInParent = event.getRawX() - Utils.getViewRawX(handle);
            return xInParent / (getWidth() - handle.getWidth());
        }
    }

    @Override
    public void setVisibility(int visibility) {
        maxVisibility = visibility;
        invalidateVisibility();
    }

    private void invalidateVisibility() {
        if(
                recyclerView.getAdapter()==null ||
                        recyclerView.getAdapter().getItemCount()==0 ||
                        recyclerView.getChildAt(0)==null ||
                        isRecyclerViewNotScrollable() ||
                        maxVisibility != View.VISIBLE
                ){
            super.setVisibility(INVISIBLE);
        } else {
            super.setVisibility(VISIBLE);
        }
    }

    private boolean isRecyclerViewNotScrollable() {
        if(isVertical()) {
            return recyclerView.getChildAt(0).getHeight() * recyclerView.getAdapter().getItemCount() <= recyclerView.getHeight();
        } else {
            return recyclerView.getChildAt(0).getWidth() * recyclerView.getAdapter().getItemCount() <= recyclerView.getWidth();
        }
    }

    private void setRecyclerViewPosition(float relativePos) {
        if (recyclerView == null) return;
        int itemCount = recyclerView.getAdapter().getItemCount();
        int targetPos = (int) Utils.getValueInRange(0, itemCount - 1, (int) (relativePos * (float) itemCount));
        recyclerView.scrollToPosition(targetPos);
    }

    void setScrollerPosition(float relativePos) {
        if(isVertical()) {
            handle.setY(Utils.getValueInRange(
                    0,
                    getHeight() - handle.getHeight(),
                    relativePos * (getHeight() - handle.getHeight()))
            );
        } else {

            handle.setX(Utils.getValueInRange(
                    0,
                    getWidth() - handle.getWidth(),
                    relativePos * (getWidth() - handle.getWidth()))
            );
        }
    }

    public boolean isVertical(){
        return scrollerOrientation == VERTICAL;
    }

    boolean shouldUpdateHandlePosition() {
        return handle!=null && !manuallyChangingPosition && recyclerView.getChildCount() > 0;
    }

    ScrollerViewProvider getViewProvider() {
        return viewProvider;
    }
}
