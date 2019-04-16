package com.blq.ssnb.baseconfigure.demo;

import android.widget.TextView;

import com.blq.ssnb.baseconfigure.R;

import blq.ssnb.baseconfigure.BaseActivity;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/4/10
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public class MyBaseActivity extends BaseActivity {

    private TextView contentView;

    @Override
    protected int contentView() {
        return R.layout.activity_my_base_activity;
    }

    @Override
    protected void initView() {
        contentView = findViewById(R.id.tv_content_view);
    }

    @Override
    protected void bindData() {
        StringBuilder builder = new StringBuilder();
        String content = builder
                .append("1.在onCreate中实现了如下方法，方便继承后直接调用，将逻辑分开\n")
                .append("\t contentView()返回布局id\n")
                .append("\t initSaveState(savedInstanceState) 用于布局缓存的读取恢复\n")
                .append("\t initView() 布局view的初始化\n")
                .append("\t initData() 布局数据的初始化\n")
                .append("\t bindData() 布局数据绑定\n")
                .append("\t bindEvent() 一些时间的绑定\n")
                .append("\t operation() 干完之后自己想干的一些事\n")
                .append("2.重写了onActivityResult方法并将该方法设置为final," +
                        "该方法内部将 fragment 启动的activity的requestCode 还原，" +
                        "然后调用onHandledActivityResult方法，" +
                        "主要解决的是子Fragment启动activity的result在activity需要监听的需求\n")
                .append("3.重写onBackPressed 方法,然后找出所有子view中，继承BaseFragment的Fragment," +
                        "判断该Fragment的onBackPressed是否需要拦截，只有子fragment都不要拦截才能真正的调用activity的返回")
                .toString();
        contentView.setText(content);
    }

    @Override
    protected void bindEvent() {

    }
}
