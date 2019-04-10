package blq.ssnb.baseconfigure;

import android.view.View;

import com.blq.snbview.listener.OnIntervalClickListener;

import blq.ssnb.baseconfigure.demo.MyBaseActivity;
import blq.ssnb.baseconfigure.demo.MyBaseFragment;
import blq.ssnb.baseconfigure.demo.refresh.RefreshMenuActivity;
import blq.ssnb.baseconfigure.search.SimpleSearchActivity;
import blq.ssnb.baseconfigure.simple.MenuBean;
import blq.ssnb.baseconfigure.simple.SimpleMenuActivity;
import blq.ssnb.baseconfigure.webview.SimpleWebViewFragment;

import java.util.ArrayList;
import java.util.List;

import blq.ssnb.baseconfigure.demo.SearchFragment;

public class MainActivity extends SimpleMenuActivity {

    @Override
    protected String navigationTitle() {
        return "基础类的合集";
    }

    @Override
    protected List<MenuBean> getMenuBeans() {
        List<MenuBean> menuBeans = new ArrayList<>();
        menuBeans.add(new MenuBean().setMenuTitle("BaseActivity")
                .setMenuSubTitle("一个基础的activity 继承自 AppCompatActivity")
                .setActivityClass(MyBaseActivity.class));

        menuBeans.add(new MenuBean()
                .setMenuTitle("BaseFragment")
                .setMenuSubTitle("一个基础的Fragment,继承V4包的Fragment")
                .setOnClickListener(new OnIntervalClickListener() {
                    @Override
                    public void onEffectiveClick(View v) {
                        startActivity(BaseFragmentContainerActivity.newIntent(
                                getBaseContext(),
                                MyBaseFragment.class,
                                null));
                    }
                })
        );

        menuBeans.add(new MenuBean()
                .setMenuTitle("BaseWebViewFragment")
                .setMenuSubTitle("该fragment主要实现了WebView基本的逻辑，" +
                        "但是和view分离，需要继承者自己去实现initWebView()方法返回WebView对象" +
                        "，可以灵活的配置布局,具体可看SimpleWebViewFragment的实现")
                .setOnClickListener(v -> startActivity(BaseFragmentContainerActivity.newIntent(
                        getBaseContext(),
                        SimpleWebViewFragment.class,
                        SimpleWebViewFragment.newArgument("https://www.baidu.com")))));

        menuBeans.add(new MenuBean()
                .setMenuTitle("BaseSearchActivity")
                .setMenuSubTitle("该activity 里面封装了搜索的逻辑,不包含view布局,调用SimpleSearchActivity.newIntent()方法来获取启动intent," +
                        "传入的Fragment只需要实现ISearchAction接口，具体使用可见SimpleSearchActivity" +
                        "(要改变SnbSmartSearchEdit的样式 只需在style设置<item name=\"sview_SnbSmartSearchEditStyle\">@style/自定义的搜索栏样式</item>就可以了)")
                .setOnClickListener(v -> startActivity(SimpleSearchActivity.newIntent(
                        getBaseContext(),
                        SimpleSearchActivity.newBundle(SearchFragment.class, null)))));

        menuBeans.add(new MenuBean()
                .setMenuTitle("下拉刷新和上拉加载控件")
                .setMenuSubTitle("里面包含了单独的下拉刷新，上拉加载和下拉刷新和上拉加载的例子")
                .setActivityClass(RefreshMenuActivity.class));

        return menuBeans;
    }
}
