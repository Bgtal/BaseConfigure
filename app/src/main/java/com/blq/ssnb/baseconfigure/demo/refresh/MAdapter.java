package com.blq.ssnb.baseconfigure.demo.refresh;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import blq.ssnb.baseconfigure.R;

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
public class MAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MAdapter() {
        super(R.layout.item_simple_menu);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_title_view, item);
        helper.setGone(R.id.tv_description_content, false);
    }
}
