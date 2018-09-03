package com.Tornado.englishgrammar.fast_scroller;

import com.Tornado.englishgrammar.home_page.LessonListFragment;

class DefaultHandleBehavior implements ViewBehavior {

    private final VisibilityAnimationManager animationManager;

    DefaultHandleBehavior(VisibilityAnimationManager animationManager) {
        this.animationManager = animationManager;
    }

    @Override
    public void onHandleGrabbed() {
        animationManager.show();
        LessonListFragment.hideFloatingMenu();
    }

    @Override
    public void onHandleReleased() {
        animationManager.hide();
        LessonListFragment.showFloatingMenu();
    }

    @Override
    public void onScrollStarted() {
        animationManager.show();
    }

    @Override
    public void onScrollFinished() {
        animationManager.hideAnimator.setStartDelay(1500);
        animationManager.hide();
    }

}
