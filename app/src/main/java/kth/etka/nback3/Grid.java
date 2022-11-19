package kth.etka.nback3;

import static kth.etka.nback3.Logic.*;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.core.content.res.ResourcesCompat;

//The Grid is gotten from https://github.com/yeetivity/MobileApplicationDevelopment/tree/main/example-apps/tic-tac-toe

public class Grid extends View {
    private Paint paint;
    private Drawable imgbox;

    private float boardSize = getWidth();
    private float cellSize = boardSize / SIZE;

    private Logic logic;

    public Grid(Context context, AttributeSet attributeSet){
        super(context, attributeSet);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        Resources res = context.getResources();
        imgbox = ResourcesCompat.getDrawable(res, R.drawable.ic_imgbox, null);

        logic = Logic.getInstance();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Method called when Android tries to figure out (new) size for this view
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int squareSide = Math.min(getMeasuredWidth(), getMeasuredHeight());

        cellSize = (float) squareSide / SIZE;
        setMeasuredDimension(squareSide, squareSide); // making sure the view is squared
    }

    public float getBoardSize(){return boardSize;}

    public float getCellSize(){return cellSize;}

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(16);

        float boardSize = canvas.getWidth();
        float cellSize = boardSize / 3;
        //int row = 0;
        //int col = 0;

        for (int step = 1; step < SIZE; step++) {
            canvas.drawLine(
                    0, cellSize * step, boardSize, cellSize * step, paint);
            canvas.drawLine(
                    cellSize * step, 0, cellSize * step, boardSize, paint);
        }
    }

    private void drawImage(Canvas canvas, int row, int col){
            Drawable img = imgbox;

            float left = cellSize * (col + 0.2F);
            float top = cellSize * (row + 0.2F);
            float right = left + cellSize * 0.6F;
            float bottom = top + cellSize * 0.6F;

            Rect imgBounds = new Rect((int) left, (int) top, (int) right, (int) bottom); //pos and scale
            img.setBounds(imgBounds);
            img.draw(canvas);
    }

    public void fadeInDrawable(){
        ValueAnimator alphaAnimator = ValueAnimator.ofInt(0,255);
        alphaAnimator.setDuration(500);
        alphaAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int newAlpha = (Integer) valueAnimator.getAnimatedValue();
                imgbox.setAlpha(newAlpha);
                // Update the view, i.e. request a call to ondraw()
                Grid.this.invalidate();
            }
        });
        alphaAnimator.start();
    }
}
