package com.example.admin.x5webview;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class MainActivity extends Activity {
    private X5WebView mWebView;
    private ViewGroup mViewParent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewParent = (ViewGroup) findViewById(R.id.webView1);

        mWebView = new X5WebView(this, null);

        mViewParent.addView(mWebView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.FILL_PARENT,
                FrameLayout.LayoutParams.FILL_PARENT));


        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // mTestHandler.sendEmptyMessage(MSG_OPEN_TEST_URL);
				/* mWebView.showLog("test Log"); */
            }
        });

        WebSettings webSetting = mWebView.getSettings();
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0)
                .getPath());
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);

        mWebView.loadUrl("https://www.jd.com/?cu=true&utm_source=vip.baidu.com&utm_medium=tuiguang&utm_campaign=t_298046589_&utm_term=708ecdb871504761b13007494b297a8e&abt=3");
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().sync();
    }

    /**
     * 确保注销配置能够被释放
     */
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        if (this.mWebView != null) {
            mWebView.destroy();
        }
        super.onDestroy();
    }

}
