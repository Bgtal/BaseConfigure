package com.blq.ssnb.baseconfigure.demo;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.blq.ssnb.baseconfigure.R;
import com.blq.ssnb.baseconfigure.bean.SpaceBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import blq.ssnb.baseconfigure.BaseFragment;
import blq.ssnb.baseconfigure.widget.SnbSpacesDecoration;
import blq.ssnb.snbutil.SnbDateCreateUtil;
import blq.ssnb.snbutil.SnbDisplayUtil;
import blq.ssnb.snbview.listener.OnIntervalClickListener;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2020/9/19
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public class SpacesDecorationFragment extends BaseFragment {


    View space8;
    View space16;

    CheckBox leftSpace;
    CheckBox topSpace;
    CheckBox rightSpace;
    CheckBox bottomSpace;

    View colorRed200;
    View colorGreen300;
    View colorAmber400;

    CheckBox leftColor;
    CheckBox topColor;
    CheckBox rightColor;
    CheckBox bottomColor;

    RecyclerView listView;
    RecyclerView listView2;

    @Override
    protected int rootLayout() {
        return R.layout.fragment_spaces_decoration;
    }

    @Override
    protected void initView(View view) {
        space8 = view.findViewById(R.id.tv_space_8);
        space16 = view.findViewById(R.id.tv_space_16);

        leftSpace = view.findViewById(R.id.cb_left_space);
        topSpace = view.findViewById(R.id.cb_top_space);
        rightSpace = view.findViewById(R.id.cb_right_space);
        bottomSpace = view.findViewById(R.id.cb_bottom_space);

        colorRed200 = view.findViewById(R.id.tv_color_red_200);
        colorGreen300 = view.findViewById(R.id.tv_color_green_300);
        colorAmber400 = view.findViewById(R.id.tv_color_amber_400);

        leftColor = view.findViewById(R.id.cb_left_color);
        topColor = view.findViewById(R.id.cb_top_color);
        rightColor = view.findViewById(R.id.cb_right_color);
        bottomColor = view.findViewById(R.id.cb_bottom_color);

        listView = view.findViewById(R.id.rv_content_list);
        listView2 = view.findViewById(R.id.rv_content_list_2);
    }

    private ListAdapter vListAdapter;
    private ListAdapter hListAdapter;
    private int dp8;
    private SnbSpacesDecoration mSpacesDecoration;

    @Override
    protected void initData() {
        dp8 = SnbDisplayUtil.dp2Px(getContext(), 8);
        mSpacesDecoration = new SnbSpacesDecoration(dp8);

        List<SpaceBean> data = requestData();

        listView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        vListAdapter = new ListAdapter(StaggeredGridLayoutManager.VERTICAL);

        listView.setAdapter(vListAdapter);
        listView.addItemDecoration(mSpacesDecoration);

        hListAdapter = new ListAdapter(StaggeredGridLayoutManager.HORIZONTAL);
        listView2.setAdapter(hListAdapter);
        listView2.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));
        listView2.addItemDecoration(mSpacesDecoration);


        vListAdapter.replaceData(data);
        hListAdapter.replaceData(data);

    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindEvent() {
        space8.setOnClickListener(new OnIntervalClickListener() {
            @Override
            public void onEffectiveClick(View v) {
                mSpacesDecoration.setSpace(dp8);
                vListAdapter.notifyDataSetChanged();
                hListAdapter.notifyDataSetChanged();
            }
        });
        space16.setOnClickListener(new OnIntervalClickListener() {
            @Override
            public void onEffectiveClick(View v) {
                mSpacesDecoration.setSpace(dp8 * 2);
                vListAdapter.notifyDataSetChanged();
                hListAdapter.notifyDataSetChanged();
            }
        });

        leftSpace.setOnCheckedChangeListener((buttonView, isChecked) -> {

        });

        topSpace.setOnCheckedChangeListener((buttonView, isChecked) -> {

        });

        rightSpace.setOnCheckedChangeListener((buttonView, isChecked) -> {

        });

        bottomSpace.setOnCheckedChangeListener((buttonView, isChecked) -> {

        });


        colorRed200.setOnClickListener(new OnIntervalClickListener() {
            @Override
            public void onEffectiveClick(View v) {

            }
        });
        colorGreen300.setOnClickListener(new OnIntervalClickListener() {
            @Override
            public void onEffectiveClick(View v) {

            }
        });
        colorAmber400.setOnClickListener(new OnIntervalClickListener() {
            @Override
            public void onEffectiveClick(View v) {

            }
        });

        leftColor.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {

            }
        });

        topColor.setOnCheckedChangeListener((buttonView, isChecked) -> {

        });

        rightColor.setOnCheckedChangeListener((buttonView, isChecked) -> {

        });

        bottomColor.setOnCheckedChangeListener((buttonView, isChecked) -> {

        });
    }


    private List<SpaceBean> requestData() {
        return SnbDateCreateUtil.createListData(new SnbDateCreateUtil.DataCreateFactory<SpaceBean>() {
            @Override
            public SpaceBean createData(int index) {
                SpaceBean spaceBean = new SpaceBean();
                spaceBean.setText("我是数据:" + index);
                spaceBean.setHeight(dp8 * 8 + dp8 * 4 * (index % 4));
                spaceBean.setWight(dp8 * 8 + dp8 * 4 * (index % 4));
                return spaceBean;
            }

            @Override
            public long minDelayTime() {
                return 0;
            }

            @Override
            public int dataSize() {
                return 50;
            }
        });
    }

    private static class ListAdapter extends BaseQuickAdapter<SpaceBean, BaseViewHolder> {

        int orientation;

        public ListAdapter(int orientation) {
            super(R.layout.item_space_item);
            this.orientation = orientation;
        }

        @Override
        protected void convert(BaseViewHolder helper, SpaceBean item) {
            helper.setText(R.id.tv_text, item.getText());

            if (orientation == LinearLayout.VERTICAL) {
                ViewGroup.LayoutParams params = helper.itemView.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                params.height = item.getHeight();
                helper.itemView.setLayoutParams(params);
            } else {
                ViewGroup.LayoutParams params = helper.itemView.getLayoutParams();
                params.height = ViewGroup.LayoutParams.MATCH_PARENT;
                params.width = item.getWight();
                helper.itemView.setLayoutParams(params);
            }
        }
    }
}
