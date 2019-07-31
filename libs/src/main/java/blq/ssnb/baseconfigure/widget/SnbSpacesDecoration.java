package blq.ssnb.baseconfigure.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

    public SnbSpacesDecoration(int space) {
        this(space, space, space, space);
    }

    public SnbSpacesDecoration(int left, int top, int right, int bottom) {
        mRect = new Rect(left, top, right, bottom);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = mRect.left;
        outRect.right = mRect.right;
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = mRect.top * 2;
        } else {
            outRect.top = mRect.top;
        }
        if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
            outRect.bottom = mRect.bottom * 2;
        } else {
            outRect.bottom = mRect.bottom;
        }
    }
}