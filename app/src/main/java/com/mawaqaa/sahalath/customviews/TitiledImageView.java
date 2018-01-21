package com.mawaqaa.sahalath.customviews;
/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return- Anson
 * ----------------------------------------------------------------------------
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.mawaqaa.sahalath.R;

/**
 * Created by anson on 3/17/2017.
 */

public class TitiledImageView extends ImageView {

    private static final String TAG = "TitiledImageView";
    private static final int DEFAULT_TEXT_STYLE = Typeface.NORMAL;
    private static final int DEFAULT_TEXT_COLOR = Color.WHITE;
    private static final int DEFAULT_SCREEN_LOCATION = 5;
    private static final int DEFAULT_X_OFFSET = 30;
    private static final int DEFAULT_Y_OFFSET = 5;
    private static final float DEFAULT_TEXT_SIZE = 25f;

    private float textSize, xPos, yPos, xOffset, yOffset;
    private int textStyle, textColor, labelLocation, arrayLocation;
    private String customFont, text;
    private Paint mTextPaint;

    public TitiledImageView(Context context) {
        this(context, null);
        initWithDefautls();

    }

    public TitiledImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWithAttrs(attrs, context);
    }

    private void initWithDefautls() {
        textSize = DEFAULT_TEXT_SIZE;
        textStyle = DEFAULT_TEXT_STYLE;
        textColor = DEFAULT_TEXT_COLOR;
        labelLocation = DEFAULT_SCREEN_LOCATION;
        xOffset = DEFAULT_X_OFFSET;
        yOffset = DEFAULT_Y_OFFSET;
        setTextPaint();
    }

    private void setTextPaint() {
        mTextPaint = new Paint();
        mTextPaint.setTextSize(textSize);
        mTextPaint.setColor(textColor);
        mTextPaint.setTypeface(Typeface.defaultFromStyle(textStyle));
    }

    private void initWithAttrs(AttributeSet attrs, Context context) {
        initAttrs(context, attrs);
        setTextPaint();
        if (customFont != null) {
            setCustomFont(context);
        }
    }

    private void setCustomFont(Context ctx) {
        Typeface tf = null;
        try {
            tf = Typeface.createFromAsset(ctx.getAssets(), customFont);
        } catch (Exception e) {
            Log.e(TAG, "Could not get typeface: " + e.getMessage());
        }
        mTextPaint.setTypeface(tf);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.AnsonImageView, 0, 0);

        try {
            text = a.getString(R.styleable.AnsonImageView_text);
            textSize = a.getFloat(R.styleable.AnsonImageView_textSizePx,
                    DEFAULT_TEXT_SIZE);
            textStyle = a.getInt(R.styleable.AnsonImageView_textStyle,
                    DEFAULT_TEXT_STYLE);
            textColor = a.getInt(R.styleable.AnsonImageView_textColor,
                    DEFAULT_TEXT_COLOR);
            labelLocation = a.getInt(
                    R.styleable.AnsonImageView_labelPosition,
                    DEFAULT_SCREEN_LOCATION);
            customFont = a.getString(R.styleable.AnsonImageView_customFont);
            xOffset = a.getFloat(R.styleable.AnsonImageView_xOffset,
                    DEFAULT_X_OFFSET);
            yOffset = a.getFloat(R.styleable.AnsonImageView_yOffset,
                    DEFAULT_Y_OFFSET);
            ;
        } finally {
            a.recycle();
        }
    }

    public void setArrayLocation(int arrayLocation) {
        this.arrayLocation = arrayLocation;
    }

    public int getArrayLocation() {
        return this.arrayLocation;
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setLabelLocation();
    }

    private void setLabelLocation() {

        switch (labelLocation) {
            case 0: // Top Left
                xPos = xOffset;
                yPos = yOffset;
                break;
            case 1: // Top Right
                xPos = getWidth() - mTextPaint.measureText(text) - xOffset;
                yPos = yOffset;
                break;
            case 2: // Bottom Left
                xPos = xOffset;
                yPos = getHeight() - yOffset;
                break;
            case 3: // Bottom Right
                xPos = getWidth() - mTextPaint.measureText(text) - xOffset;
                yPos = getHeight() - yOffset;
                break;
            case 4: // Top Center
                xPos = (getWidth() / 2) - (mTextPaint.measureText(text) / 2);
                yPos = yOffset;
                break;
            case 5: // Bottom Center
                xPos = (getWidth() / 2) - (mTextPaint.measureText(text) / 2);
                yPos = getHeight() - yOffset;
                break;
            case 6: // Center
                xPos = (getWidth() / 2) - (mTextPaint.measureText(text) / 2);
                yPos = (getHeight() / 2);
            default:
                break;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (text != null) {
            canvas.drawText(text, xPos, yPos, mTextPaint);
        }
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public void setCustomFont(String customFont) {
        this.customFont = customFont;
    }

    public void setLabelLocation(int labelLocation) {
        this.labelLocation = labelLocation;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTextStyle(int textStyle) {
        this.textStyle = textStyle;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}
