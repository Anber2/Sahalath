package com.mawaqaa.sahalath.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.mawaqaa.sahalath.R;


/**
 * Created by anson on 3/20/2017.
 */

public class TextViewWithId extends TextView {
    private String textId;
    private int arrayPosition;

    public TextViewWithId(Context context) {
        this(context, null);
    }

    public TextViewWithId(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextViewWithId(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (attrs != null) {
            TypedArray array = getContext().obtainStyledAttributes(attrs,
                    R.styleable.TextViewWithId);

            this.textId = array.getString(R.styleable.TextViewWithId_textId);
            array.recycle();
        }
    }

    public String getTextId() {
        return textId;
    }

    public void setTextId(String name) {
        this.textId = name;
    }

    public void setArrayPosition(int arrayPosition) {
        this.arrayPosition = arrayPosition;
    }

    public int getArrayPosition() {
        return this.arrayPosition;
    }

}
