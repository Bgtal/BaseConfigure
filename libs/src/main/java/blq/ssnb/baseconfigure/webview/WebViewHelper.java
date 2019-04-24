package blq.ssnb.baseconfigure.webview;

import android.content.Context;
import android.net.http.SslError;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/4/23
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public class WebViewHelper {

    //    private WeakReference<WebView> mWebViewWeakReference;
    private WebView mWebView;
    private Context mContext;

    public WebViewHelper(WebView webView) {
        if (webView == null) {
            throw new NullPointerException("WebView 对象不能为空");
        }
//        mWebViewWeakReference = new WeakReference<>(webview);
        this.mWebView = webView;
        mContext = webView.getContext();

        initWebSetting(getWebSetting());
        setWebViewClient(initWebViewClient());
        setWebChromeClient(initWebChromeClient());
        addInterface(mWebView);
    }

    public WebView getWebView(){
        return mWebView;
    }
    protected Context getContext() {
        return mContext;
    }

    public WebSettings getWebSetting() {
        return mWebView.getSettings();
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        if (webViewClient != null) {
            mWebView.setWebViewClient(webViewClient);
        }
    }

    public void setWebChromeClient(WebChromeClient webChromeClient) {
        if (webChromeClient != null) {
            mWebView.setWebChromeClient(webChromeClient);
        }
    }


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
        if (mContext != null) {
            webSettings.setAppCachePath(mContext.getCacheDir().getPath());
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
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                result.confirm();
                return true;
            }
        };
    }

    protected void addInterface(WebView webView) {

    }


    public void loadUrl(String url) {
        if (url == null || url.trim().equals("")) {
            return;
        }
        if (mWebView != null) {
            mWebView.loadUrl(url);
        }
    }

    public void onResume() {
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    public void onPause() {
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    /**
     * 是否拦截返回按钮
     *
     * @return true 拦截掉
     */
    public boolean isInterceptBackPressed() {
        return mWebView.canGoBack();
    }

    public boolean onBackPressed() {
        if (isInterceptBackPressed()) {
            goBack();
            return true;
        }
        return false;
    }

    public void goBack() {
        if (mWebView != null) {
            mWebView.goBack();
        }
    }
}
