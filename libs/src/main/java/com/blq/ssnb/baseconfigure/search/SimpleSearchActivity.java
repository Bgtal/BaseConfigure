package com.blq.ssnb.baseconfigure.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.blq.snbview.SnbSmartSearchEdit;
import com.blq.ssnb.baseconfigure.R;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/2/22
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public class SimpleSearchActivity extends BaseSearchActivity {

    public static Intent newIntent(Context context, Bundle extras) {
        Intent intent = new Intent(context, SimpleSearchActivity.class);
        intent.putExtras(extras);
        return intent;
    }

    private ImageView backBtn;
    private SnbSmartSearchEdit mSearchEdit;
    private TextView searchBtn;

    @Override
    protected int contentView() {
        return R.layout.activity_simple_search;
    }

    @Override
    protected int getContainerID() {
        return R.id.fl_container;
    }

    @Override
    protected void initView() {
        backBtn = findViewById(R.id.img_back_btn);
        mSearchEdit = findViewById(R.id.snb_search_edit);
        searchBtn = findViewById(R.id.tv_search_btn);
    }

    @Override
    protected void bindEvent() {
        backBtn.setOnClickListener(v -> onBackPressed());
        searchBtn.setOnClickListener(v -> mSearchEdit.focusLose());

        mSearchEdit.setOnSearchActionListener(new SnbSmartSearchEdit.OnSearchActionListener() {
            @Override
            public void onSoftInputSearch(String searchKey) {
                onSearch(searchKey);
            }

            @Override
            public void onLostFocusSearch(String searchKey) {
                onSearch(searchKey);
            }

            @Override
            public void onAutoSearch(String searchKey) {
                if ("".equals(searchKey)) {
                    onClear();
                } else {
                    onChange(searchKey);
                }
            }
        });
    }

}
