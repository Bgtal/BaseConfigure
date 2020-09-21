package blq.ssnb.baseconfigure.widget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.sql.Struct;

import blq.ssnb.snbutil.SnbLog;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019-07-06
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public class SnbSpacesDecoration extends RecyclerView.ItemDecoration {

    private Rect mRect;
    private Paint mPaint;

    private int leftColor;
    private int topColor;
    private int rightColor;
    private int bottomColor;


    public SnbSpacesDecoration(int space) {
        this(space, space, space, space);
    }

    public SnbSpacesDecoration(int left, int top, int right, int bottom) {
        setSpace(left, top, right, bottom);
        setSpaceColor(Color.TRANSPARENT);
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        //获得爸爸的边界

//        int parentPaddingLeft = parent.getPaddingLeft();
//        int parentPaddingRight = parent.getPaddingRight();
//        int parentPaddingTop = parent.getPaddingTop();
//        int parentPaddingBottom = parent.getPaddingBottom();

        int parentLeft = parent.getPaddingLeft();
        int parentRight = parent.getWidth() - parent.getPaddingRight();
        int parentTop = parent.getPaddingTop();
        int parentBottom = parent.getHeight() - parent.getPaddingBottom();
//        ---------
//       |  -----  |  parent的边界最外层边界
//       | | --- | |
//       | ||   || |  child的边界是最内层
//       | | --- | |
//       |  -----  |  childLimit 边界是中间层 是在child的边界基础上加间隔
//        ---------
        //循环画孩子的边界
        for (int i = 0; i < parent.getChildCount(); i++) {
//            if(i!=5){
//                continue;
//            }
            View child = parent.getChildAt(i);
            //获得孩子的边界
            int childLeft = child.getLeft();
            int childRight = child.getRight();
            int childTop = child.getTop();
            int childBottom = child.getBottom();

            //得到孩子的边界
            int childLimitLeft = childLeft - mRect.left;
            int childLimitTop = childTop - mRect.top;
            int childLimitRight = childRight + mRect.right;
            int childLimitBottom = childBottom + mRect.bottom;

//            int leftRectLeft = cLift - mRect.left;
//            int leftRectTop = cTop-mRect.top;
//            SnbLog.e(">>>>>>L"+(leftRectTop - parentTop));
//            if(leftRectTop-parentTop<0){
//                leftRectTop = parentTop;
//            }
//            int leftRectRight = cLift;
//            int leftRectBottom = cBottom +mRect.bottom;
//            if(leftRectBottom - parentBottom>0){
//                leftRectBottom = parentBottom;
//            }
            //判断四周实际可画的范围


            int leftOutDif = childLimitLeft - parentLeft;//子类画图外边距-父类内边距  >=0表示可以显示 <0 表示左边距已经超出了
            int canDrawLeftOut = leftOutDif < 0 ? parentLeft : childLimitLeft;

            int leftInDif = childLeft - parentLeft;
            int canDrawLeftIn = leftInDif < 0 ? parentLeft : childLeft;
            SnbLog.e(">>>>left:out-diff:" + leftOutDif + " -out: " + canDrawLeftOut + "|in-diff:" + leftInDif + " -in: " + canDrawLeftIn);

            int topOutDif = childLimitTop - parentTop;//子类外边距- 父类内边距
            int canDrawTopOut = topOutDif < 0 ? parentTop : childLimitTop;

            int topInDif = childTop - parentTop;
            int canDrawTopIn = topInDif < 0 ? parentTop : childTop;

            int rightOutDif = parentRight - childLimitRight;
            int canDrawRightOut = rightOutDif < 0 ? parentRight : childLimitRight;

            int rightInDif = parentRight - childRight;
            int canDrawRightIn = rightInDif < 0 ? parentRight : childRight;

            int bottomOutDif = parentBottom - childLimitBottom;
            int canDrawBottomOut = bottomOutDif < 0 ? parentBottom : childLimitBottom;

            int bottomInDif = parentBottom - childBottom;
            int canDrawBottomIn = bottomInDif < 0 ? parentBottom : childBottom;

            boolean isVEx = canDrawBottomOut - canDrawTopOut < 0;
            boolean isHEx = canDrawRightOut - canDrawLeftOut < 0;

            boolean isVIEx = canDrawBottomIn - canDrawBottomOut > 0;
            boolean isHIEx = canDrawRightIn - canDrawRightOut > 0;


            if(canDrawBottomOut-canDrawTopOut<0){

            }

            if (!isVEx && !isHEx && !isVIEx && !isHIEx) {
                //左边
                mPaint.setColor(Color.GREEN);
                c.drawRect(canDrawLeftOut, canDrawTopOut, canDrawLeftIn, canDrawBottomOut, mPaint);

                //右边
                mPaint.setColor(Color.YELLOW);
                c.drawRect(canDrawRightIn, canDrawTopOut, canDrawRightOut, canDrawBottomOut, mPaint);
//
                //上边
                mPaint.setColor(Color.BLUE);
                c.drawRect(canDrawLeftOut, canDrawTopOut, canDrawRightOut, canDrawTopIn, mPaint);


                //下边
                mPaint.setColor(Color.RED);
                c.drawRect(canDrawLeftOut, canDrawBottomIn, canDrawRightOut, canDrawBottomOut, mPaint);

            }
        }


    }

    private void drawLeft(Canvas c) {
        mPaint.setColor(Color.GREEN);
//        c.drawRect(parentLeft, parentTop, parentLeft + mRect.left, parentBottom, mPaint);
//        mPaint.setColor(Color.BLUE);
//        c.drawRect(parentLeft, parentTop, parentRight, parentTop + mRect.top, mPaint);
//        mPaint.setColor(Color.YELLOW);
//        c.drawRect(parentRight - mRect.right, parentTop, parentRight, parentBottom, mPaint);
//        mPaint.setColor(Color.RED);
//        c.drawRect(parentLeft, parentBottom - mRect.bottom, parentRight, parentBottom, mPaint);
//
    }

    @Override
    public void getItemOffsets(Rect outRect,
                               View view,
                               RecyclerView parent,
                               RecyclerView.State state) {
        outRect.left = mRect.left;
        outRect.right = mRect.right;
        outRect.top = mRect.top;
        outRect.bottom = mRect.bottom;
    }

    public void setSpace(int space) {
        setSpace(space, space, space, space);
    }

    public void setSpace(int left, int top, int right, int bottom) {
        if (mRect == null) {
            mRect = new Rect();
        }
        mRect.set(left, top, right, bottom);
    }

    public void setSpaceColor(@ColorInt int color) {
        setSpaceColor(color, color, color, color);
    }

    public void setSpaceColor(@ColorInt int leftColor,
                              @ColorInt int topColor,
                              @ColorInt int rightColor,
                              @ColorInt int bottomColor) {
        this.leftColor = leftColor;
        this.topColor = topColor;
        this.rightColor = rightColor;
        this.bottomColor = bottomColor;
    }

}