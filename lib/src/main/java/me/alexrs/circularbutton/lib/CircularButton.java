package me.alexrs.circularbutton.lib;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Alejandro on 06/06/14.
 */

/**
 * A Google Plus like, circular button for Android.
 * See https://github.com/Alexrs95/CircularButton
 */
public class CircularButton extends ImageView {

    /**
     * The dimension of the shadow is a 15% of the radius of the button
     */
    private static float SHADOW_CONSTANT = 0.15F;

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

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void init(Context context, AttributeSet attrs) {
        setScaleType(ScaleType.CENTER_INSIDE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

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
        canvas.drawCircle(centerX, centerY, btnRadius - (btnRadius * SHADOW_CONSTANT), mButtonPaint);
        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        centerX = width / 2;
        centerY = height / 2;
        btnRadius = Math.min(width, height) / 2;

        //the shadow color is settled here because its dimension depends on the radius of the button
        setShadowColor(shadowColor);
    }


    public void setButtonColor(int color) {
        this.buttonColor = color;
        mButtonPaint.setColor(buttonColor);
        invalidate();
    }

    public void setShadowColor(int color) {
        this.shadowColor = color;
        mButtonPaint.setShadowLayer(btnRadius * SHADOW_CONSTANT, 0, 0, shadowColor);
        invalidate();
    }

    public int getButtonColor() {
        return buttonColor;
    }

    public int getShadowColor() {
        return shadowColor;
    }
}
