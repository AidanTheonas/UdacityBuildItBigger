package com.udacity.gradle.builditbigger.helpers;

import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * JokeTellingApp Created by aidan on 22/07/2018.
 */
public class JokeTellingIdlingResource implements IdlingResource {

    @Nullable
    private volatile ResourceCallback mCallback;
    private AtomicBoolean isIdle = new AtomicBoolean(true);

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return isIdle.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mCallback = callback;
    }

    @SuppressWarnings("ConstantConditions")
    public void setIdleState(boolean isIdle) {
        this.isIdle.set(isIdle);
        if (isIdle && mCallback != null) {
            mCallback.onTransitionToIdle();
        }
    }
}
