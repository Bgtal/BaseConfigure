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

    private Rect parentRect;
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
        parentRect = new Rect();
        setSpace(left, top, right, bottom);
        setSpaceColor(Color.TRANSPARENT);
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (mRect.left == 0
                && mRect.right == 0
                && mRect.top == 0
                && mRect.bottom == 0) {
            return;
        }

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

            //判断四周实际可画的范围
            //判断需要结合上下 左右 即一个边界需要判断在两边的情况下的值
            int leftOutDifL = childLimitLeft - parentLeft;//子左外边-父左外边 > 0 表示画图范围内 否者超出边界
            int leftOutDifR = parentRight - childLimitLeft;//父右外边-子左外边 >0 表示画图范围内 否者超出边界
            //上面情况只能满足其中一种，如果都满足，说明这个布局有问题了，不管了
            int canDrawLeftOut = childLimitLeft;
            if (leftOutDifL < 0) {
                canDrawLeftOut = parentLeft;
            } else if (leftOutDifR < 0) {
                canDrawLeftOut = parentRight;
            }

            int leftInDifL = childLeft - parentLeft;
            int leftInDifR = parentRight - childLeft;
            int canDrawLeftIn = childLeft;
            if (leftInDifL < 0) {
                canDrawLeftIn = parentLeft;
            } else if (leftInDifR < 0) {
                canDrawLeftIn = parentRight;
            }

            int topOutDifT = childLimitTop - parentTop; //子上外边 - 父上外边 > 0 表示在画图范围内 否者超出边界
            int topOutDifB = parentBottom - childLimitTop;//父下外边 - 子上外边 > 0 表示在画图范围内
            int canDrawTopOut = childLimitTop;
            if (topOutDifT < 0) {
                canDrawTopOut = parentTop;
            } else if (topOutDifB < 0) {
                canDrawTopOut = parentBottom;
            }

            int topInDifT = childTop - parentTop;
            int topInDifB = parentBottom - childTop;
            int canDrawTopIn = childTop;
            if (topInDifT < 0) {
                canDrawTopIn = parentTop;
            } else if (topInDifB < 0) {
                canDrawTopIn = parentBottom;
            }

            int rightOutDifL = childLimitRight - parentLeft;//子右外边 - 父左外边 > 0 表示画图范围内 否者超出边界
            int rightOutDifR = parentRight - childLimitRight;//父右外边 - 子右外边 >0 表示画图范围内 否者超出边界
            int canDrawRightOut = childLimitRight;
            if (rightOutDifL < 0) {
                canDrawRightOut = parentLeft;
            } else if (rightOutDifR < 0) {
                canDrawRightOut = parentRight;
            }

            int rightInDifL = childRight - parentLeft;
            int rightInDifR = parentRight - childRight;
            int canDrawRightIn = childRight;
            if (rightInDifL < 0) {
                canDrawRightIn = parentLeft;
            } else if (rightInDifR < 0) {
                canDrawRightIn = parentRight;
            }


            int bottomOutDifT = childLimitBottom - parentTop; //子下外边 - 父上外边 > 0 表示在画图范围内 否者超出边界
            int bottomOutDifB = parentBottom - childLimitBottom;//父下外边 - 子下外边 > 0 表示在画图范围内
            int canDrawBottomOut = childLimitBottom;
            if (bottomOutDifT < 0) {
                canDrawBottomOut = parentTop;
            } else if (bottomOutDifB < 0) {
                canDrawBottomOut = parentBottom;
            }

            int bottomInDifT = childBottom - parentTop;
            int bottomInDifB = parentBottom - childBottom;
            int canDrawBottomIn = childBottom;
            if (bottomInDifT < 0) {
                canDrawBottomIn = parentTop;
            } else if (bottomInDifB < 0) {
                canDrawBottomIn = parentBottom;
            }

            if (canDrawBottomOut - canDrawTopOut > 0 && canDrawLeftIn - canDrawLeftOut > 0) {
                //左边
                mPaint.setColor(leftColor);
                c.drawRect(canDrawLeftOut, canDrawTopOut, canDrawLeftIn, canDrawBottomOut, mPaint);
            }

            if (canDrawBottomOut - canDrawTopOut > 0 && canDrawRightOut - canDrawRightIn > 0) {
                //右边
                mPaint.setColor(rightColor);
                c.drawRect(canDrawRightIn, canDrawTopOut, canDrawRightOut, canDrawBottomOut, mPaint);
            }

            if (canDrawTopIn - canDrawTopOut > 0 && canDrawRightOut - canDrawLeftOut > 0) {
                //上边
                mPaint.setColor(topColor);
                c.drawRect(canDrawLeftOut, canDrawTopOut, canDrawRightOut, canDrawTopIn, mPaint);
            }

            if (canDrawBottomOut - canDrawBottomIn > 0 && canDrawRightOut - canDrawLeftOut > 0) {
                //下边
                mPaint.setColor(bottomColor);
                c.drawRect(canDrawLeftOut, canDrawBottomIn, canDrawRightOut, canDrawBottomOut, mPaint);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect,
                               View view,
                               RecyclerView parent,
                               RecyclerView.State state) {
        SnbLog.e(">>>>>>>getItemOffsets");
        int parentLeft = parent.getPaddingLeft();
        int parentRight = parent.getWidth() - parent.getPaddingRight();
        int parentTop = parent.getPaddingTop();
        int parentBottom = parent.getHeight() - parent.getPaddingBottom();

        parentRect.left =  parentLeft;
        parentRect.right = parentRight;
        parentRect.top = parentTop;
        parentRect.bottom = parentBottom;

        SnbLog.e(">>>parent:"+parentRect.toString());

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