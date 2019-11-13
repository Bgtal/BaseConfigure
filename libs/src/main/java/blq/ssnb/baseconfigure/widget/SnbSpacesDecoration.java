package blq.ssnb.baseconfigure.widget;

import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
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
        outRect.top = mRect.top;
        outRect.bottom = mRect.bottom;
    }
}