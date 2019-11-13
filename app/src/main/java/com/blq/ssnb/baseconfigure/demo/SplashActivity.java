package com.blq.ssnb.baseconfigure.demo;

import android.widget.ImageView;
import android.widget.TextView;

import com.blq.ssnb.baseconfigure.R;
import com.bumptech.glide.Glide;

import blq.ssnb.baseconfigure.BaseActivity;
import blq.ssnb.baseconfigure.splash.SplashHelper;
import blq.ssnb.baseconfigure.splash.SqlTask;
import blq.ssnb.baseconfigure.splash.db.SplashDatabase;
import blq.ssnb.baseconfigure.splash.db.SplashEntity;
import blq.ssnb.snbutil.SnbLog;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019-11-08
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public class SplashActivity extends BaseActivity {

    private SplashHelper mSplashHelper;
    private ImageView mShowImg;

    private TextView clearBtn;

    @Override
    protected int contentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        mShowImg = findViewById(R.id.img_show_image);
        clearBtn = findViewById(R.id.tv_clear_btn);
    }

    @Override
    protected void initData() {
        super.initData();
        mSplashHelper = new SplashHelper() {
            @Override
            public void requestNetData() {

                new SqlTask<>(new SqlTask.CallBack<SplashEntity>() {
                    @Override
                    public SplashEntity doAction() {
                        SplashEntity entity = new SplashEntity();
                        entity.setSplashID("imgUrl");
                        entity.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573214911512&di=348bfe87bc1842828540b101fa6c7c04&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201501%2F22%2F20150122223726_XiSM8.jpeg");
                        entity.setStartTime(1573204881885L);
                        entity.setEndTime(1673304881885L);
                        entity.setExtMsg("我是外部拓展内容");
                        entity.setUpdateTime(System.currentTimeMillis());
                        long sleepTime = 1;//(long) (Math.random() * 1000);

                        try {
                            Thread.sleep(sleepTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        return entity;
                    }

                    @Override
                    public void onResult(SplashEntity resultData) {
                        entityInput(false, resultData);
                    }
                }).execute();
            }

            @Override
            public void showEntityInfo(SplashEntity entity) {
                Glide.with(getContext())
                        .load(entity.getUrl())
                        .into(mShowImg);
            }

            @Override
            public void showNullInfo() {
                SnbLog.e(">>>>>我是没数据显示的");
                mShowImg.setImageResource(R.drawable.icon_splash);
            }
        };
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindEvent() {
        clearBtn.setOnClickListener(v -> new SqlTask<>(new SqlTask.VoidCallBack() {
            @Override
            public Void doAction() {
                SplashDatabase.getInstance().mSplashDao().clearAll();
                return null;
            }
        }).execute());
    }

    @Override
    protected void operation() {
        super.operation();
        mSplashHelper.requestEntityData();
    }
}
