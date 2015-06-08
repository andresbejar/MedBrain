package com.medbrain.fuzzy.medbrain;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.NumberPicker;

/**
 * Created by Julio on 6/7/15.
 */
public class CustomNumberPicker extends NumberPicker
{
    public CustomNumberPicker(Context context, AttributeSet attrs, int
            defStyle)
    {
        super(context, attrs, defStyle);
        processAttributeSet(attrs);
    }

    public CustomNumberPicker(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        processAttributeSet(attrs);
    }

    public CustomNumberPicker(Context context)
    {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        /* Prevent parent controls from stealing our events once we've
gotten a touch down */
        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN)
        {
            ViewParent p = getParent();
            if (p != null)
                p.requestDisallowInterceptTouchEvent(true);
        }

        return false;
    }

    private void processAttributeSet(AttributeSet attrs) {
        //This method reads the parameters given in the xml file and sets the properties according to it
        this.setMinValue(attrs.getAttributeIntValue(null, "min", 0));
        this.setMaxValue(attrs.getAttributeIntValue(null, "max", 0));
        this.setWrapSelectorWheel(false);
    }
}