package com.blq.ssnb.baseconfigure.demo;

import android.view.View;
import android.widget.TextView;

import com.blq.ssnb.baseconfigure.R;

import blq.ssnb.baseconfigure.BaseFragment;
import blq.ssnb.baseconfigure.search.ISearchAction;

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
public class SearchFragment extends BaseFragment implements ISearchAction {
    private TextView historyView;
    private StringBuilder mBuilder;

    @Override
    protected int rootLayout() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initView(View view) {
        historyView = view.findViewById(R.id.tv_search_history);
    }

    @Override
    protected void initData() {
        mBuilder = new StringBuilder();
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void onSearch(String msg) {
        mBuilder.append("搜索:").append(msg).append("\n");
        historyView.setText(mBuilder.toString());
    }

    @Override
    public void onChange(String msg) {
        mBuilder.append("改变:").append(msg).append("\n");
        historyView.setText(mBuilder.toString());
    }

    @Override
    public void onClear() {
        mBuilder.append("清理\n");
        historyView.setText(mBuilder.toString());
    }
}
