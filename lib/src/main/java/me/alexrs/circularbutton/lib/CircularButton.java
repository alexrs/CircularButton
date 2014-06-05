package me.alexrs.circularbutton.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Alejandro on 04/06/14.
 */
public class CircularButton extends ImageView {

    private Paint mButtonPaint;
    private float centerX;
    private float centerY;
    private int btnRadius;
    private int buttonColor = Color.WHITE;
    private int shadowColor = Color.GRAY;

    public CircularButton(Context context) {
        super(context);
        init(context, null);
    }

    public CircularButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CircularButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setScaleType(ScaleType.CENTER_INSIDE);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        mButtonPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mButtonPaint.setStyle(Paint.Style.FILL);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircularButton);
            buttonColor = a.getColor(R.styleable.CircularButton_buttonColor, buttonColor);
            shadowColor = a.getColor(R.styleable.CircularButton_shadowColor, shadowColor);
            a.recycle();
        }

        setButtonColor(buttonColor);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(centerX, centerY, btnRadius - (btnRadius * 0.15F), mButtonPaint);
        super.onDraw(canvas);
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
        btnRadius = Math.min(w, h) / 2;
        setShadowColor(shadowColor);
    }

    public void setButtonColor(int color) {
        this.buttonColor = color;
        mButtonPaint.setColor(buttonColor);
        invalidate();
    }

    public void setShadowColor(int color) {
        this.shadowColor = color;
        mButtonPaint.setShadowLayer(btnRadius * 0.15F, 0, 0, shadowColor);
        invalidate();
    }
}
