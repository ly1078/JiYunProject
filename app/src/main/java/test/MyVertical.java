package test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class MyVertical extends HorizontalScrollView {

    private LinearLayout linearLayout;

    public MyVertical(Context context) {
        super(context,null);
        linearLayout = new LinearLayout(context);
        addView(linearLayout);
    }

    public MyVertical(Context context, AttributeSet attrs) {
        this(context,attrs,0);

    }

    public MyVertical(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context,attrs,defStyleAttr);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int childCount = linearLayout.getChildCount();
        float x = getX();
        float y = getY();
        for (int i = 0; i < childCount; i++) {
            View childAt = linearLayout.getChildAt(i);
            // getDrawingRect
            Rect rect = new Rect();
            childAt.getDrawingRect(rect);



        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
    public void addChiledView(View v){
        linearLayout.addView(v);
    }

    private boolean pointInRect(float x, float y, int w, int h) {
        if (x < 0) {
            return false;
        }
        if (x > w) {
            return false;
        }
        if (y < 0) {
            return false;
        }
        if (y > h) {
            return false;
        }
        return true;
    }

}
