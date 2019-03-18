package com.github.cyc.wanandroid.behavior;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.github.cyc.wanandroid.app.NoProguard;

/**
 * 底部导航的Behavior
 */
public class BottomNavigationBehavior extends CoordinatorLayout.Behavior<View> implements NoProguard {

    private ObjectAnimator mAnimatorOut;

    private ObjectAnimator mAnimatorIn;

    public BottomNavigationBehavior(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child,
                                       @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child,
                                  @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        // 上滑隐藏底部导航，下滑显示底部导航
        if (dy > 0 && child.getTranslationY() == 0) {
            if (mAnimatorOut == null) {
                mAnimatorOut = ObjectAnimator.ofFloat(child, "translationY", 0, child.getHeight())
                        .setDuration(300);
            }

            if (!mAnimatorOut.isRunning()) {
                mAnimatorOut.start();
            }
        } else if (dy < 0 && child.getTranslationY() == child.getHeight()) {
            if (mAnimatorIn == null) {
                mAnimatorIn = ObjectAnimator.ofFloat(child, "translationY", child.getHeight(), 0)
                        .setDuration(300);
            }

            if (!mAnimatorIn.isRunning()) {
                mAnimatorIn.start();
            }
        }
    }
}
