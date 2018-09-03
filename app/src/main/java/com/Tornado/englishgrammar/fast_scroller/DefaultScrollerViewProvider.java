package com.Tornado.englishgrammar.fast_scroller;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.Tornado.englishgrammar.R;

class DefaultScrollerViewProvider extends ScrollerViewProvider {

    private ImageView handle;

    @Override
    public ImageView provideHandleView(ViewGroup container) {
        handle = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.fastscroll__default_handle, container, false);
        handle.setAlpha((float) 0.5);
        return handle;
    }

    @Override
    protected ViewBehavior provideHandleBehavior() {
        return new DefaultHandleBehavior(new VisibilityAnimationManager.Builder(handle).withPivotX(1f).withPivotY(1f).build());
    }


}
