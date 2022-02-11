package cn.titansys.xview_pptv.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Button;

import cn.titansys.xview_pptv.R;


@SuppressLint("AppCompatCustomView")
public class ButtonStyle extends Button {

    public ButtonStyle(Context context) {
        super(context);
    }

    @SuppressLint("ResourceAsColor")
    public ButtonStyle(Context context, AttributeSet attrs) {
        super(context, attrs);
   }

    public ButtonStyle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("NewApi")
    public ButtonStyle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
