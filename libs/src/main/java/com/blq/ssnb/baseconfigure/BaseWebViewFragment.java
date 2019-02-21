package com.blq.ssnb.baseconfigure;

import android.net.http.SslError;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;
import java.util.List;

import blq.ssnb.snbutil.SnbToast;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/2/20
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public abstract class BaseWebViewFragment extends BaseFragment {

    /**
     * webView
     */
    private WebView mWebView;

    /**
     * url 管理工具
     */
//    private List<String> mUrlsCollection = new ArrayList<>();
//
//    private String mCurrentUrl;
    @Override
    protected void initView(View view) {
        mWebView = initWebView(view);
    }

    /**
     * 初始化获得webview对象
     *
     * @return WebView 对象
     */
    protected abstract WebView initWebView(View rootView);

    @Override
    protected void initData() {
        initWebSetting(mWebView.getSettings());
        WebViewClient webViewClient = initWebViewClient();
        if (webViewClient != null) {
            mWebView.setWebViewClient(webViewClient);
        }
        WebChromeClient webChromeClient = initWebChromeClient();
        if (webChromeClient != null) {
            mWebView.setWebChromeClient(webChromeClient);
        }
        addInterface(mWebView);
    }


    /**
     * 设置 WebSettings
     */
    protected void initWebSetting(WebSettings webSettings) {
        //支持js交互
        webSettings.setJavaScriptEnabled(true);

        //支持插件
        webSettings.setPluginState(WebSettings.PluginState.ON);
        //设置适应屏幕
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        //支持缩放
        webSettings.setSupportZoom(false);
        //隐藏原生的缩放控件
        webSettings.setDisplayZoomControls(false);
        //支持内容重新布局
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.supportMultipleWindows();
        webSettings.setSupportMultipleWindows(true);
        //设置缓存模式
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAppCacheEnabled(true);
//        webSettings.setAppCachePath(webView.getContext().getCacheDir().getAbsolutePath());
        if (getActivity() != null) {
            webSettings.setAppCachePath(getActivity().getCacheDir().getPath());
        }

        //设置可访问文件
        webSettings.setAllowFileAccess(true);
        //当webview调用requestFocus时为webview设置节点
        webSettings.setNeedInitialFocus(true);
        //支持自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setNeedInitialFocus(true);
        //设置编码格式
        webSettings.setDefaultTextEncodingName("UTF-8");
    }

    protected WebViewClient initWebViewClient() {
        return new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        };
    }

    protected WebChromeClient initWebChromeClient() {
        return new WebChromeClient() {

            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                SnbToast.showSmart(getContext(), message);
                result.confirm();
                return true;
            }
        };
    }

    /**
     * 添加js回调接口
     *
     * @param webView WebView
     */
    protected void addInterface(WebView webView) {

    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void operation() {
        loadUrl(indexUrl());
    }

    /**
     * 初始化加载的主页
     *
     * @return 主页url
     */
    protected abstract String indexUrl();

    protected void loadUrl(String url) {
        if (url == null || url.trim().equals("")) {
            return;
        }
        if (mWebView != null) {
            mWebView.loadUrl(url);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public boolean onBackPressed() {
        boolean isIntercept = mWebView.canGoBack();
        if (isIntercept) {
            mWebView.goBack();
            showClose();
        }
        return isIntercept;
    }

    protected abstract void showClose();
}
