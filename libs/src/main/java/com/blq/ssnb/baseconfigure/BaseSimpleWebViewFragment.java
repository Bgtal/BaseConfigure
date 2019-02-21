package com.blq.ssnb.baseconfigure;

import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import blq.ssnb.snbutil.SnbToast;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/2/21
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public class BaseSimpleWebViewFragment extends BaseWebViewFragment {
    private static final String BUNDLE_KEY_WEB_INDEX_URL = "bundle_key_web_index_url";

    public static Bundle newArgument(String indexUrl) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_WEB_INDEX_URL, indexUrl);
        return bundle;
    }

    private Toolbar mToolbar;
    private ProgressBar loadingProgress;

    private ImageView backBtn;
    private ImageView closeBtn;

    private TextView titleView;

    private String indexUrl = "";

    @Override
    protected void initArgumentData(Bundle arguments) {
        super.initArgumentData(arguments);
        indexUrl = arguments.getString(BUNDLE_KEY_WEB_INDEX_URL, indexUrl);
    }

    @Override
    protected int rootLayout() {
        return R.layout.base_fragment_simple_web_view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mToolbar = view.findViewById(R.id.toolbar_navigation_bar);
        loadingProgress = view.findViewById(R.id.pb_loading_progress);
        backBtn = view.findViewById(R.id.img_back_btn);
        closeBtn = view.findViewById(R.id.img_close_btn);

        titleView = view.findViewById(R.id.tv_title_view);

    }

    /**
     * 设置menu
     * 如果设置了不生效
     * 参考:http://stackoverflow.com/questions/26511981/toolbar-inflatemenu-seems-to-do-nothing
     *
     * @param menus    menuID 传0 表示不显示
     * @param listener menu 点击监听
     */
    protected final void updateMenu(@MenuRes int menus, Toolbar.OnMenuItemClickListener listener) {
        mToolbar.getMenu().clear();
        if (menus != 0) {
            mToolbar.inflateMenu(menus);
            mToolbar.setOnMenuItemClickListener(listener);
        }
    }

    @Override
    protected WebView initWebView(View rootView) {
        return rootView.findViewById(R.id.wv_content);
    }

    @Override
    protected WebChromeClient initWebChromeClient() {
        return new WebChromeClient() {

            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                SnbToast.showSmart(getContext(), message);
                result.confirm();
                return true;
            }


            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                titleView.setText("正在加载...");
                loadingProgress.setVisibility(View.VISIBLE);
                loadingProgress.setProgress(newProgress);
                if (newProgress == 100) {
                    titleView.setText(view.getTitle());
                    loadingProgress.setVisibility(View.GONE);
                }
            }

        };
    }

    @Override
    protected String indexUrl() {
        return indexUrl;
    }

    @Override
    protected void bindEvent() {
        backBtn.setOnClickListener(v -> onBackPressed());

        closeBtn.setOnClickListener(v -> getActivity().finish());
    }

    @Override
    protected void showClose() {
        closeBtn.setVisibility(View.VISIBLE);
    }
}
