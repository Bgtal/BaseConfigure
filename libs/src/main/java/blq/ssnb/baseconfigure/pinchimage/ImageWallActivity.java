package blq.ssnb.baseconfigure.pinchimage;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

import blq.ssnb.baseconfigure.BaseActivity;
import blq.ssnb.baseconfigure.R;
import blq.ssnb.snbutil.SnbCountDownTimer;
import blq.ssnb.snbutil.SnbLog;
import blq.ssnb.snbview.SnbPinchImageView;
import blq.ssnb.snbview.gridview.IGridItemBean;

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
public abstract class ImageWallActivity<Data extends IGridItemBean> extends BaseActivity {

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private View backBtn;
    private TextView mNavTitleView;

    private PinchImageVPAdapter<Data> mVPAdapter;
    private SnbCountDownTimer mCountDownTimer;

    private int currentIndex;

    @Override
    protected int contentView() {
        return R.layout.activity_pinch_image;
    }

    @Override
    protected void initView() {
        mToolbar = findViewById(R.id.toolbar_navigation_bar);
        backBtn = findViewById(R.id.img_navigation_back_btn);
        mNavTitleView = findViewById(R.id.tv_navigation_title);

        mViewPager = findViewById(R.id.vp_image_content);
    }


    @Override
    protected void initData() {
        super.initData();
        mCountDownTimer = new SnbCountDownTimer(3000, 3000) {

            @Override
            protected void onTick(long remainingMillisecond) {

            }

            @Override
            protected void onFinish() {
                mToolbar.clearAnimation();
                Animation animation = new AlphaAnimation(1, 0);
                animation.setDuration(1000);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mToolbar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                mToolbar.startAnimation(animation);
            }
        };
        mVPAdapter = new PinchImageVPAdapter<Data>() {
            @Override
            public void onBindView(SnbPinchImageView picImage, int position) {
                onVpBindView(mVPAdapter,picImage,position);
            }
        };
    }

    protected abstract void onVpBindView(PinchImageVPAdapter<Data> vpAdapter, SnbPinchImageView picImage, int position);


    public void setCurrentIndex(int index) {
        currentIndex = index;
        mViewPager.setCurrentItem(index);
    }


    protected PinchImageVPAdapter getImageAdapter(){
        return mVPAdapter;
    }

    @Override
    protected void bindData() {
        mViewPager.setAdapter(mVPAdapter);
        mVPAdapter.replaceData(bindImageData());
        mViewPager.setCurrentItem(currentIndex);
        updateNavTitle(currentIndex);
    }

    protected abstract List<Data> bindImageData();

    protected void updateNavTitle(int position) {
        mNavTitleView.setText((position + 1) + "/" + mVPAdapter.getCount());
    }

    @Override
    protected void bindEvent() {
        backBtn.setOnClickListener(v -> onBackPressed());

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mToolbar.setVisibility(View.VISIBLE);
                SnbLog.e(">>>>>>viewpager:scroll");
            }

            @Override
            public void onPageSelected(int position) {
                updateNavTitle(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                SnbLog.e(">>>>>>viewpager:"+state);
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    mCountDownTimer.restart();
                }
            }
        });
    }

}
