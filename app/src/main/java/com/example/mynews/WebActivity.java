package com.example.mynews;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.mynews.tools.BaseActivity;

@SuppressLint("SetJavaScriptEnabled")
public class WebActivity extends BaseActivity {
    private WebView webView;

    private Toolbar navToolbar, commentToolBar;

    private String urlData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = findViewById(R.id.webView);
        navToolbar = findViewById(R.id.toolbar_webView);
        commentToolBar = findViewById(R.id.toolbar_webComment);
        //将底部评论框的toolbar放在主界面上
        findViewById(R.id.toolbar_webComment).bringToFront();
    }

    //活动由不可见变为可见时调用
    @Override
    protected void onStart() {
        super.onStart();
        // 获取html页面的连接
        urlData = getIntent().getStringExtra("pageUrl");
        String pageUniquekey = getIntent().getStringExtra("uniquekey");
        String pageTtile = getIntent().getStringExtra("news_title");

        Log.i("WebActivity", "Url>>>:" + urlData);

        // 通过WebView中的getSettings方法获得一个WebSettings对象
        WebSettings settings = webView.getSettings();

        // 开启javascript：h5页要一般都有js,设置为true才允许h5页面执行js，但开启js非常耗内存，经常会导致oom，
        // 为了解决这个问题，可以在onStart方法中开启，在onStop中关闭。
        settings.setJavaScriptEnabled(true);

        //设置支持缩放
        settings.setSupportZoom(true);
        // 设置出现缩放工具。若为false，则该WebView不可缩放
        settings.setBuiltInZoomControls(true);
        // 设置允许js弹出alert对话框
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

        // 设置webview推荐使用的窗口，使html界面自适应屏幕
        settings.setUseWideViewPort(true);
        // 设置WebView底层的布局算法，参考LayoutAlgorithm#NARROW_COLUMNS，将会重新生成WebView布局
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        // 设置缩放至屏幕的大小
        settings.setLoadWithOverviewMode(true);
        // 隐藏webview缩放按钮
        settings.setDisplayZoomControls(false);
        // 加载网页连接
        webView.loadUrl(urlData);

        // Toolbar通过setSupportActionBar(toolbar) 被修饰成了actionbar。
        setSupportActionBar(commentToolBar);
        // 设置菜单栏标题
        navToolbar.setTitle("新闻");
        setSupportActionBar(navToolbar);
        commentToolBar.inflateMenu(R.menu.tool_webbottom);
        commentToolBar.setTitle("感谢观看");

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            // 在页面加载结束时调用
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // 通过查看每个新闻的网页发现网页广告的div样式的选择器为body > div.top-wrap.gg-item.J-gg-item 然后去除这个样式，使其加载网页时去掉广告
                view.loadUrl("javascript:function setTop1(){document.querySelector('body > div.top-wrap.gg-item.J-gg-item').style.display=\"none\";}setTop1();");
                view.loadUrl("javascript:function setTop4(){document.querySelector('body > a.piclick-link').style.display=\"none\";}setTop4();");
                view.loadUrl("javascript:function setTop2(){document.querySelector('#news_check').style.display=\"none\";}setTop2();");
                view.loadUrl("javascript:function setTop3(){document.querySelector('body > div.articledown-wrap gg-item J-gg-item').style.display=\"none\";}setTop3();");
            }

            // 重写此方法可以让webView处理https请求。
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // 默认的处理方式，WebView变成空白页
                // handler.cancel();
                // 接受所有网站的证书，忽略SSL错误，执行访问网页
                handler.proceed();
            }
        });

        // 重写执行执行去广告的js代码
        webView.setWebChromeClient(new WebChromeClient() {
            // 每次网页加载进度改变时，就会执行一次js代码，保证广告一出来就被干掉
            // 缺点也很明显，会执行很多次无效的js代码。
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                // 去除加载热点新闻
                view.loadUrl("javascript:function setTop1(){document.querySelector('body > div.top-wrap.gg-item.J-gg-item').style.display=\"none\";}setTop1();");
                view.loadUrl("javascript:function setTop4(){document.querySelector('body > a.piclick-link').style.display=\"none\";}setTop4();");
                view.loadUrl("javascript:function setTop2(){document.querySelector('#news_check').style.display=\"none\";}setTop2();");
                view.loadUrl("javascript:function setTop3(){document.querySelector('body > div.articledown-wrap gg-item J-gg-item').style.display=\"none\";}setTop3();");
            }
        });


        commentToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.news_share:
                        // 这里有bug，点击之后没反应
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_SUBJECT, urlData);
                        // 分享的文本类型
                        intent.setType("text/plain");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(Intent.createChooser(intent, getTitle()));
                        break;

                }
                return true;
            }
        });
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置返回图标
            actionBar.setHomeAsUpIndicator(R.drawable.ic_return_left);
        }
    }

    // 活动不可见时关闭脚本，否则可能会导致oom
    @Override
    protected void onStop() {
        super.onStop();
        webView.getSettings().setJavaScriptEnabled(false);
    }

    // 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。 返回true则显示该menu, false则不显示;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_webview, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // 左上角的id
            case android.R.id.home:
                Intent returnIntent = new Intent();
                setResult(RESULT_OK, returnIntent);
                // 结束当前活动
                WebActivity.this.finish();
                break;
            case R.id.news_setting:
                Toast.makeText(this, "夜间模式", Toast.LENGTH_SHORT).show();
                break;
            case R.id.news_feedback:
                Toast.makeText(this, "举报！", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
