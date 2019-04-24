package blq.ssnb.baseconfigure.webview;

import android.net.http.SslError;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import blq.ssnb.baseconfigure.BaseFragment;

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

    private WebViewHelper mWebViewHelper;

    protected WebView getWebView() {
        return mWebViewHelper.getWebView();
    }

    @Override
    protected void initData() {
        mWebViewHelper = initWebViewHelper();
    }

    protected abstract WebViewHelper initWebViewHelper();

    @Override
    protected void operation() {
        mWebViewHelper.loadUrl(indexUrl());
    }

    /**
     * 初始化加载的主页
     *
     * @return 主页url
     */
    protected abstract String indexUrl();

    @Override
    public void onResume() {
        super.onResume();
        mWebViewHelper.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mWebViewHelper.onPause();
    }

    @Override
    public boolean onBackPressed() {
        boolean isIntercept = mWebViewHelper.onBackPressed();
        if (isIntercept) {
            showClose();
        }
        return isIntercept;
    }

    protected abstract void showClose();
}
