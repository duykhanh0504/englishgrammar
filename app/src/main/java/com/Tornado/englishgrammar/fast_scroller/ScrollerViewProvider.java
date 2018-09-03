package com.Tornado.englishgrammar.fast_scroller;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

abstract class ScrollerViewProvider {

    private FastScroller scroller;
    private ViewBehavior handleBehavior;

    void setFastScroller(FastScroller scroller){
        this.scroller = scroller;
    }

    protected Context getContext(){
        return scroller.getContext();
    }


    public abstract ImageView provideHandleView(ViewGroup container);

    @Nullable
    protected abstract ViewBehavior provideHandleBehavior();

    private ViewBehavior getHandleBehavior(){
        if(handleBehavior==null) handleBehavior = provideHandleBehavior();
        return handleBehavior;
    }

    void onHandleGrabbed(){
        if(getHandleBehavior()!=null) getHandleBehavior().onHandleGrabbed();
    }

    void onHandleReleased(){
        if(getHandleBehavior()!=null) getHandleBehavior().onHandleReleased();
    }

    void onScrollStarted(){
        if(getHandleBehavior()!=null) getHandleBehavior().onScrollStarted();
    }

    void onScrollFinished(){
        if(getHandleBehavior()!=null) getHandleBehavior().onScrollFinished();
    }

}
