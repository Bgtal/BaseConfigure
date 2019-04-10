package blq.ssnb.baseconfigure.simple;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blq.snbview.listener.OnIntervalClickListener;

import blq.ssnb.baseconfigure.BaseActivity;
import blq.ssnb.baseconfigure.R;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/4/3
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public abstract class SimpleMenuActivity extends BaseActivity {

    private View navBackBtn;
    private TextView navTitleView;

    private RecyclerView mRecyclerView;
    private MenuAdapter mMenuAdapter;

    @Override
    protected int contentView() {
        return R.layout.activity_simple_menu;
    }

    @Override
    protected void initView() {
        navBackBtn = findViewById(R.id.img_navigation_back_btn);
        navTitleView = findViewById(R.id.tv_navigation_title);
        mRecyclerView = findViewById(R.id.rv_menu_content);
    }

    @Override
    protected void bindData() {
        navTitleView.setText(navigationTitle());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mMenuAdapter = new MenuAdapter(getContext());
        mMenuAdapter.replace(getMenuBeans());
        mRecyclerView.setAdapter(mMenuAdapter);
    }

    protected abstract String navigationTitle();

    protected abstract List<MenuBean> getMenuBeans();

    @Override
    protected void bindEvent() {
        navBackBtn.setOnClickListener(v -> onBackPressed());
    }


    private static class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MViewHolder> {
        private List<MenuBean> mMenuBeans;
        private Context mContext;

        private MenuAdapter(Context context) {
            this.mContext = context;
            mMenuBeans = new ArrayList<>();
        }

        @NonNull
        @Override
        public MViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View rootView = LayoutInflater
                    .from(mContext)
                    .inflate(R.layout.item_simple_menu, parent, false);
            return new MViewHolder(rootView);
        }

        @Override
        public void onBindViewHolder(@NonNull MViewHolder holder, int position) {
            MenuBean bean = mMenuBeans.get(position);
            holder.setTitle(bean.getMenuTitle());
            holder.setSubTitle(bean.getMenuSubTitle());
            holder.itemView.setOnClickListener(new OnIntervalClickListener() {
                @Override
                public void onEffectiveClick(View v) {
                    Class<? extends Activity> activityClass = bean.getActivityClass();
                    if (activityClass != null) {
                        mContext.startActivity(new Intent(mContext, activityClass));
                    } else if (bean.getOnClickListener() != null) {
                        bean.getOnClickListener().onClick(v);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mMenuBeans.size();
        }

        public void addMenuBean(MenuBean menuBean) {
            mMenuBeans.add(menuBean);
            notifyItemInserted(getItemCount());
        }

        public void addMenuBenas(List<MenuBean> menuBeans) {
            if (menuBeans != null && menuBeans.size() != 0) {
                int start = getItemCount();
                mMenuBeans.addAll(menuBeans);
                notifyItemRangeInserted(start, menuBeans.size());
            }
        }

        public void replace(List<MenuBean> menuBeans) {
            if (menuBeans == null) {
                menuBeans = new ArrayList<>();
            }
            mMenuBeans.clear();
            mMenuBeans.addAll(menuBeans);
            notifyDataSetChanged();
        }

        public static class MViewHolder extends RecyclerView.ViewHolder {
            private TextView menuTitleView;
            private TextView menuSubTitleView;

            public MViewHolder(View itemView) {
                super(itemView);
                menuTitleView = itemView.findViewById(R.id.tv_title_view);
                menuSubTitleView = itemView.findViewById(R.id.tv_description_content);
            }

            private void setTitle(String title) {
                if (title == null || title.trim().equals("")) {
                    menuTitleView.setVisibility(View.GONE);
                } else {
                    menuTitleView.setText(title);
                    menuTitleView.setVisibility(View.VISIBLE);
                }
            }

            private void setSubTitle(String subTitle) {
                if (subTitle == null || subTitle.trim().equals("")) {
                    menuSubTitleView.setVisibility(View.GONE);
                } else {
                    menuSubTitleView.setText(subTitle);
                    menuSubTitleView.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}
