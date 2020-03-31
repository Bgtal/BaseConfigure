package blq.ssnb.baseconfigure.pinchimage;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

import blq.ssnb.snbview.SnbPinchImageView;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2020-01-07
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public abstract class PinchImageVPAdapter<Data> extends PagerAdapter {
    private List<SnbPinchImageView> cacheData;
    private List<Data> mData;

    public PinchImageVPAdapter() {
        cacheData = new ArrayList<>();
        mData = new ArrayList<>();
    }

    @SuppressWarnings("ReferenceEquality")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        SnbPinchImageView picImage;
        if (cacheData.size() > 0) {
            picImage = cacheData.remove(0);
        } else {
            picImage = new SnbPinchImageView(container.getContext());
            picImage.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        picImage.reset();
        onBindView(picImage, position);
        container.addView(picImage);
        return picImage;
    }

    public abstract void onBindView(SnbPinchImageView picImage, int position);

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (object instanceof SnbPinchImageView) {
            SnbPinchImageView pinchImageView = (SnbPinchImageView) object;
            container.removeView(pinchImageView);
            cacheData.add(pinchImageView);
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return object == view;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    public void replaceData(List<Data> data) {
        if (mData != data) {
            mData.clear();
            if (data != null) {
                mData.addAll(data);
            }
            notifyDataSetChanged();
        }
    }

    public void addData(Data data) {
        mData.add(data);
        notifyDataSetChanged();
    }

    public void addDatas(List<Data> datas) {
        if (datas != null) {
            mData.addAll(datas);
        }
    }

    public List<Data> getData() {
        return mData;
    }

}
