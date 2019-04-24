package blq.ssnb.baseconfigure.webview;

import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;
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
public class SimpleWebViewHelper extends WebViewHelper {

    private TextView mTitleView;
    private ProgressBar mProgressBar;

    public SimpleWebViewHelper(WebView webView) {
        super(webView);
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.mProgressBar = progressBar;
    }

    public void setTitleView(TextView titleView) {
        this.mTitleView = titleView;
    }

    @Override
    protected WebChromeClient initWebChromeClient() {
        return new WebChromeClient() {

            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                result.confirm();
                return true;
            }


            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                updateTitle("正在加载...");
                updateProgress(newProgress);
                if (newProgress == 100) {
                    updateTitle(view.getTitle());
                    updateProgress(100);

                }
            }

        };
    }

    private void updateTitle(String title) {
        if (mTitleView != null) {
            mTitleView.setText(title);
        }
    }

    private void updateProgress(int newProgress) {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(newProgress == 100 ? View.GONE : View.VISIBLE);
            mProgressBar.setProgress(newProgress);
        }
    }
}
