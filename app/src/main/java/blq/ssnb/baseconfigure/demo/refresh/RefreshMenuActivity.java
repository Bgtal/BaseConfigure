package blq.ssnb.baseconfigure.demo.refresh;

import java.util.ArrayList;
import java.util.List;

import blq.ssnb.baseconfigure.simple.MenuBean;
import blq.ssnb.baseconfigure.simple.SimpleMenuActivity;

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
public class RefreshMenuActivity extends SimpleMenuActivity {

    @Override
    protected String navigationTitle() {
        return "刷新和加载更多";
    }

    @Override
    protected List<MenuBean> getMenuBeans() {
        List<MenuBean> menuBeans = new ArrayList<>();
        menuBeans.add(new MenuBean().setMenuTitle("单个下拉刷新控件布局")
                .setMenuSubTitle("使用SwipeRefreshLayout刷新控件")
                .setActivityClass(SwipeRefreshActivity.class));
        menuBeans.add(new MenuBean().setMenuTitle("单个下拉刷新控件布局")
                .setMenuSubTitle("使用WaveSwipeRefreshLayout刷新控件")
                .setActivityClass(SmaretSwipeRefreshActivity.class));
        menuBeans.add(new MenuBean().setMenuTitle("单个上拉加载控件布局")
                .setMenuSubTitle("使用BaseQuickAdapter刷新控件")
                .setActivityClass(QuickLoadMoreActivity.class));
        menuBeans.add(new MenuBean().setMenuTitle("下拉刷新和上拉加载布局")
                .setMenuSubTitle("因为使用了相应的helper," +
                        "所以以后修改的话逻辑只需要替换helper就可以了," +
                        "相应的布局可以能改一下,这个activity里面代码还可以抽象一层，作为基类使用")
                .setActivityClass(RefreshAndLoadMoreActivity.class));
        return menuBeans;
    }
}
