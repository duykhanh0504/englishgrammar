package com.Tornado.englishgrammar.fast_scroller;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.support.annotation.AnimatorRes;
import android.view.View;
import android.widget.ImageView;

import com.Tornado.englishgrammar.R;

class VisibilityAnimationManager {

    protected final ImageView view;

    AnimatorSet hideAnimator;
    private AnimatorSet showAnimator;

    private float pivotXRelative;
    private float pivotYRelative;

    private VisibilityAnimationManager(final ImageView view, @AnimatorRes int showAnimator, @AnimatorRes int hideAnimator, float pivotXRelative, float pivotYRelative, int hideDelay){
        this.view = view;
        this.pivotXRelative = pivotXRelative;
        this.pivotYRelative = pivotYRelative;
        this.hideAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(view.getContext(), hideAnimator);
        this.hideAnimator.setStartDelay(hideDelay);
        this.hideAnimator.setTarget(view);
        this.showAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(view.getContext(), showAnimator);
        this.showAnimator.setTarget(view);
        this.hideAnimator.addListener(new AnimatorListenerAdapter() {

            //because onAnimationEnd() goes off even for canceled animations
            boolean wasCanceled;

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if(!wasCanceled) view.setVisibility(View.INVISIBLE);
                wasCanceled = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                wasCanceled = true;
            }
        });

        updatePivot();
    }

    void show(){
        hideAnimator.cancel();
        if (view.getVisibility() == View.INVISIBLE) {
            view.setVisibility(View.VISIBLE);
            updatePivot();
            showAnimator.start();
        }
    }

    void hide(){
        updatePivot();
        hideAnimator.start();
    }

    private void updatePivot() {
        view.setPivotX(pivotXRelative*view.getMeasuredWidth());
        view.setPivotY(pivotYRelative*view.getMeasuredHeight());
    }

    static abstract class AbsBuilder<T extends VisibilityAnimationManager> {
        protected final ImageView view;
        int showAnimatorResource =  R.anim.fastscroll__default_show;
        int hideAnimatorResource = R.anim.fastscroll__default_hide;
        int hideDelay = 0;
        float pivotX = 0.5f;
        float pivotY = 0.5f;

        AbsBuilder(ImageView view) {
            this.view = view;
        }

        AbsBuilder<T> withPivotX(float pivotX){
            this.pivotX = pivotX;
            return this;
        }

        AbsBuilder<T> withPivotY(float pivotY){
            this.pivotY = pivotY;
            return this;
        }

        public abstract T build();
    }

    static class Builder extends AbsBuilder<VisibilityAnimationManager> {

        Builder(ImageView view) {
            super(view);
        }

        public VisibilityAnimationManager build(){
            return new VisibilityAnimationManager(view, showAnimatorResource, hideAnimatorResource, pivotX, pivotY, hideDelay);
        }

    }

}
