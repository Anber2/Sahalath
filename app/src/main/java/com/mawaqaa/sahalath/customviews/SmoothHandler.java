package com.mawaqaa.sahalath.customviews;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import com.mawaqaa.sahalath.customviews.interfaces.ISmoothTarget;

import junit.framework.Assert;

import java.lang.ref.WeakReference;


public class SmoothHandler extends Handler {
    final WeakReference<ISmoothTarget> targetWeakReference;

    private float aimPercent;
    private float minInternalPercent = 0.03f; // 3%
    private float smoothInternalPercent = 0.01f; // 1%
    private int smoothIncreaseDelayMillis = 1; // 1ms

    private final String TAG = "SmoothHandler";
    public static boolean NEED_LOG = false;

    public float getMinInternalPercent() {
        return minInternalPercent;
    }


    public void setMinInternalPercent(float minInternalPercent) {
        Assert.assertTrue("the min internal percent must more than 0", minInternalPercent > 0);
        Assert.assertTrue("the min internal percent must less than 1", minInternalPercent <= 1);
        Assert.assertTrue("the min internal percent must more than the smooth internal percent",
                minInternalPercent > this.smoothInternalPercent);
        this.minInternalPercent = minInternalPercent;
    }

    public float getSmoothInternalPercent() {
        return smoothInternalPercent;
    }


    public void setSmoothInternalPercent(float smoothInternalPercent) {
        Assert.assertTrue("the smooth internal percent must more than 0", minInternalPercent > 0);
        Assert.assertTrue("the smooth internal percent must less than 0.5", minInternalPercent < 0.5);
        Assert.assertTrue("the smooth internal percent must less than the min internal percent",
                smoothInternalPercent < this.minInternalPercent);
        this.smoothInternalPercent = smoothInternalPercent;
    }

    public int getSmoothIncreaseDelayMillis() {
        return smoothIncreaseDelayMillis;
    }

    public void setSmoothIncreaseDelayMillis(int smoothIncreaseDelayMillis) {
        Assert.assertTrue("the delay of increase duration must more than 0", minInternalPercent > 0);
        this.smoothIncreaseDelayMillis = smoothIncreaseDelayMillis;
    }


    public SmoothHandler(WeakReference<ISmoothTarget> targetWeakReference) {
        this(targetWeakReference, Looper.getMainLooper());
    }

    public SmoothHandler(WeakReference<ISmoothTarget> targetWeakReference, Looper looper) {
        super(looper);
        this.targetWeakReference = targetWeakReference;
        this.aimPercent = targetWeakReference.get().getPercent();
        clear();
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (this.targetWeakReference == null || this.targetWeakReference.get() == null) {
            return;
        }

        final ISmoothTarget target = targetWeakReference.get();

        final float currentPercent = target.getPercent();
        final float desiredPercentDelta = calculatePercent(currentPercent);
        setPercent2Target(Math.min(currentPercent + desiredPercentDelta, aimPercent));
        final float realPercentDelta = target.getPercent() - currentPercent;


        if (target.getPercent() >= this.aimPercent || target.getPercent() >= 1 ||
                (target.getPercent() == 0 && this.aimPercent == 0)) {
            if (NEED_LOG) {
                Log.d(TAG, String.format("finish aimPercent(%f) durationMillis(%d)",
                        this.aimPercent, this.tempDurationMillis));
            }
            clear();
            return;
        }

        sendEmptyMessageDelayed(0, calculateDelay(realPercentDelta, desiredPercentDelta));
    }

    private void clear() {
        resetTempDelay();
        this.ignoreCommit = false;
        removeMessages(0);
    }

    private boolean ignoreCommit = false;

    public void commitPercent(float percent) {
        if (this.ignoreCommit) {
            this.ignoreCommit = false;
            return;
        }
        this.aimPercent = percent;
    }

    private void setPercent2Target(final float percent) {
        if (targetWeakReference == null || targetWeakReference.get() == null) {
            return;
        }

        this.ignoreCommit = true;
        targetWeakReference.get().setPercent(percent);
        this.ignoreCommit = false;
    }

    public void loopSmooth(float percent) {
        loopSmooth(percent, -1);
    }


    public void loopSmooth(float percent, long durationMillis) {
        if (this.targetWeakReference == null || this.targetWeakReference.get() == null) {
            return;
        }

        if (NEED_LOG) {
            Log.d(TAG,
                    String.format("start loopSmooth lastAimPercent(%f), aimPercent(%f)" +
                            " durationMillis(%d)", aimPercent, percent, durationMillis));
        }

        final ISmoothTarget target = targetWeakReference.get();

        setPercent2Target(this.aimPercent);
        clear();

        this.aimPercent = percent;

        if (this.aimPercent - target.getPercent() > minInternalPercent) {
            if (durationMillis >= 0) {


                tempStartTimestamp = SystemClock.uptimeMillis();
                tempDurationMillis = durationMillis;
                tempRemainDurationMillis = durationMillis;
            }
            sendEmptyMessage(0);
        } else {
            setPercent2Target(percent);
        }
    }

    private void resetTempDelay() {
        tempLastConsumeMillis = smoothIncreaseDelayMillis;
        tempStartTimestamp = -1;
        tempDurationMillis = -1;
        tempRemainDurationMillis = -1;
        tempWarnedAccuracyProblem = false;
    }

    private float calculatePercent(final float currentPercent) {
        if (tempDurationMillis < 0) {
            return smoothInternalPercent;
        }

        float internalPercent;

        final long usedDuration = SystemClock.uptimeMillis() - tempStartTimestamp;
        final long lastRemainDurationMillis = tempRemainDurationMillis;

        tempRemainDurationMillis = tempDurationMillis - usedDuration;
        tempLastConsumeMillis = Math.max(lastRemainDurationMillis - tempRemainDurationMillis, 1);

        final long splitByDelay = Math.max(tempRemainDurationMillis / tempLastConsumeMillis, 1);
        final float percentDelta = this.aimPercent - currentPercent;

        internalPercent = percentDelta / splitByDelay;

        return internalPercent;
    }

    private long calculateDelay(final float realPercentDelta, final float desiredPercentDelta) {
        if (tempDurationMillis < 0) {
            return smoothIncreaseDelayMillis;
        }

        if (realPercentDelta - desiredPercentDelta <= ALLOWED_PRECISION_ERROR) {
            return smoothIncreaseDelayMillis;
        }

        //Accuracy Problem in target smooth progress.
        if (!tempWarnedAccuracyProblem) {
            tempWarnedAccuracyProblem = true;
            Log.w(TAG,
                    String.format("Occur Accuracy Problem in %s, (real percent delta is %f, but" +
                                    " desired percent delta is %f), so we use delay to handle the" +
                                    " temporary duration, as result the processing will not smooth",
                            targetWeakReference.get(), realPercentDelta, desiredPercentDelta));
        }

        long remedyDelayMillis;
        final float delta = realPercentDelta - desiredPercentDelta;
        remedyDelayMillis = (long) ((delta / desiredPercentDelta) * tempLastConsumeMillis);
        return remedyDelayMillis + smoothIncreaseDelayMillis;
    }

    private long tempStartTimestamp;
    private long tempDurationMillis;
    private long tempRemainDurationMillis;
    private long tempLastConsumeMillis;
    private boolean tempWarnedAccuracyProblem;
    public static float ALLOWED_PRECISION_ERROR = 0.00001f;
}