package com.example.mynews;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.litepal.LitePal;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// 每个tab下的Fragment
public class NewsFragment extends Fragment {
    //新闻列表
    private ListView newsListView;
    //下拉刷新
    private SwipeRefreshLayout swipeRefreshLayout;
    //新闻子项
    private List<NewsBean.ResultBean.DataBean> contentItems = new ArrayList<>();

    private static final int UPNEWS_INSERT = 0;

    //用来保存当前tab的名字
    private String currentTabName = "top";

    //分页查询参数，每页显示10条新闻数据
    private int pageNo = 8;

    //每一个Fragment页面都有一个浮动按钮，用于快速回到顶部
    private FloatingActionButton fab;

    @SuppressLint("HandlerLeak")
    private final Handler newsHandler = new Handler() {
        // 主线程
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == UPNEWS_INSERT) {
                contentItems = ((NewsBean) msg.obj).getResult().getData();
                //构造一个适配器来填充新闻列表
                TabAdapter adapter = new TabAdapter(getActivity(), contentItems);
                newsListView.setAdapter(adapter);
                //当adapter中的数据被更改后必须马上调用notifyDataSetChanged予以更新。
                adapter.notifyDataSetChanged();
                //从服务器获取的数据缓存到本地数据库，注意去重插入的数据
                NewsInfoBean newsInfo;
                for (int i = 0, len = contentItems.size(); i < len; ++i) {
                    newsInfo = new NewsInfoBean(contentItems.get(i));
                    //测试是否请求的数据有重复
                    //将数据缓存到本地数据库
                    newsInfo.save();
                }
            }
        }
    };

    // 创建Fragment被添加到活动中时回调，且只会被调用一次
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //每次创建，绘制该Fragment的View组件时回调，将显示的View返回
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载新闻列表
        View view = inflater.inflate(R.layout.news_list, container, false);
        //获取每个实例之后，返回当前视图
        newsListView = view.findViewById(R.id.newsListView);
        //tv = (TextView) view.findViewById(R.id.text_response);
        fab = view.findViewById(R.id.fab);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        return view;
    }

    // 当NewsFragment所在的Activity启动完成后调用，声明周期紧接在onCreateView()之后
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        onAttach(Objects.requireNonNull(getContext()));
        Log.d("上下文：", "Context: " + getContext());
        Log.d("NewsFragment>>>", "Activity: " + getActivity());
        Bundle bundle = getArguments();
        //获取键对应的值，参数2表示默认填充的值，其用法和Intent差不多，但又有区别
        //data = bundle.getString("name", "top");
        final String category = Objects.requireNonNull(bundle).getString("name", "top");
        currentTabName = category;
        Log.d("点击tab小标题为：", "onActivityCreated: " + category);
        //实现置顶功能
        fab.setOnClickListener(v -> {
            //滚动到第一个可见的item位置，下标为0
            newsListView.smoothScrollToPosition(0);
        });
        //实现下拉刷新的功能
        //设置下拉刷新进度条的颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorRed);
        //实现下拉刷新的监听器
        swipeRefreshLayout.setOnRefreshListener(() -> threadLoaderData(category));

        //异步加载数据，传入条目
        getDataFromNet(category);

        // 轻度按监听新闻列表子项
        newsListView.setOnItemClickListener((parent, view, position, id) -> {
            Log.d("子项的数据为", "onItemClick: " + contentItems.get(position));
            //获取点击条目的路径，传值显示WebView页面
            String url = contentItems.get(position).getUrl();
//                Log.d("当前新闻子项的连接是：", "onItemClick: " + url);
            String uniquekey = contentItems.get(position).getUniquekey();
            String newsTitle = contentItems.get(position).getTitle();
            Intent intent = new Intent(getActivity(), WebActivity.class);
            intent.putExtra("pageUrl", url);
            intent.putExtra("uniquekey", uniquekey);
            intent.putExtra("news_title", newsTitle);
            startActivity(intent);
        });
    }

    private void threadLoaderData(final String category) {
        new Thread(() -> {
            try {
                //沉睡1.5s，本地刷新很快，以防看不到刷新效果
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //若快速点击tab，则会出现getActivity()为空的情况，但是第一次加载肯定不会出错，所以将要拦截，以防app崩溃
            if (getActivity() == null)
                return;
            //此处的用法：runOnUiThread必须是在主线程中调用，getActivity()获取主线程所在的活动，切换子线程到主线程
            getActivity().runOnUiThread(() -> {
                //重新生成数据，传入tab条目
                loaderRefreshData(category);
                //表示刷新事件结束，并隐藏刷新进度条
                swipeRefreshLayout.setRefreshing(false);
            });
        }).start();
    }

    //加载数据，实现从本地数据库中读取数据刷新到newsListView的适配器中
    private void loaderRefreshData(final String category) {
        String categoryName = "头条";
        switch (category) {
            case "top":
                categoryName = "头条";
                break;
            case "shehui":
                categoryName = "社会";
                break;
            case "guonei":
                categoryName = "国内";
                break;
            case "guoji":
                categoryName = "国际";
                break;
            case "yule":
                categoryName = "娱乐";
                break;
            case "tiyu":
                categoryName = "体育";
                break;
            case "junshi":
                categoryName = "军事";
                break;
            case "keji":
                categoryName = "科技";
                break;
            case "caijing":
                categoryName = "财经";
                break;
            case "shishang":
                categoryName = "时尚";
                break;
        }
        //页数加1
        ++pageNo;
        List<NewsBean.ResultBean.DataBean> dataBeanList = new ArrayList<>();
        NewsBean.ResultBean.DataBean bean;
        int pageSize = 10;
        int offsetV = (pageNo - 1) * pageSize;
        Log.d("pageNo", "页数为: " + pageNo);
        Log.d("offsetV", "偏移量为: " + offsetV);
        Log.d("offsetV", "以下开始查询");
        List<NewsInfoBean> beanList = LitePal.where("category = ?", categoryName).limit(pageSize).offset(offsetV).find(NewsInfoBean.class);
        Log.d("TAG", "查询的数量为：" + beanList.size());
        //若查询的结果为0，则重新定位页数为1
        if (beanList.size() == 0) {
            pageNo = 1;
            offsetV = (pageNo - 1) * pageSize;
            beanList = LitePal.where("category = ?", categoryName).limit(pageSize).offset(offsetV).find(NewsInfoBean.class);
            Log.d("分页查询", "loaderRefreshData: 已经超过最大页数，归零并重新查询！");
        }
        Log.d("刷新查到的数据大小", "run: " + beanList.size());
        for (int i = 0, len = beanList.size(); i < len; ++i) {
            bean = new NewsBean.ResultBean.DataBean();
            bean.setDataBean(beanList.get(i));
            dataBeanList.add(bean);
            Log.d("刷新id：", "run: " + beanList.get(i));
        }
        contentItems = dataBeanList;
        //将dataBeanList赋值给全局的contentItems，否则点击新闻子项会出错,并且contentItems之前要清空，不然起不到更新视图的作用
        TabAdapter adapter = new TabAdapter(getActivity(), contentItems);
        newsListView.setAdapter(adapter);
        //当adapter中的数据被更改后必须马上调用notifyDataSetChanged予以更新。
        adapter.notifyDataSetChanged();
    }

    //异步消息处理机制
    private void getDataFromNet(final String data) {
        @SuppressLint("StaticFieldLeak")
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            //子线程
            @Override //储备key：547ee75ef186fc55a8f015e38dcfdb9a
            protected String doInBackground(Void... params) { // 自己的key：af2d37d2ed31f7a074f1d49b5460a0b5，可以替换下面请求中的key
                String path = "https://v.juhe.cn/toutiao/index?type=" + data + "&key=23e3cb7604892e2ccf6b1bc9e4fbaae6";
                URL url;

                try {
                    url = new URL(path);
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url)
                            .get()
                            .build();
                    Call call = okHttpClient.newCall(request);
                    //通过execute()方法获得请求响应的Response对象
                    Response response = call.execute();
                    if (response.isSuccessful()) {
                        String res = response.body().string();
                        Log.i("MainActivity", ">>>" + res);
                        return res;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return 404 + data;
            }

            //当给后台任务执行完毕并通过return语句返回时，此方法将被调用，返回来的数据可以进行一些UI操作，并将处理的参数传入
            protected void onPostExecute(final String result) {
                new Thread(() -> {
                    //查看状态码是否为200，若不是（开子线程），然后从本地加载相应的数据
                    NewsBean newsBean;
                    //不包括endIndex
                    Log.d("后台处理的数据为：", "run: " + result);
                    if (!result.startsWith("404")) {
                        newsBean = new Gson().fromJson(result, NewsBean.class);
                        if ("0".equals("" + newsBean.getError_code())) {
                            //obtainmessage()方法是从消息池中拿来一个msg，不需要另开辟空间new，new需要重新申请，效率低，obtianmessage可以循环利用；
                            Message msg = newsHandler.obtainMessage();
                            msg.what = UPNEWS_INSERT;
                            msg.obj = newsBean;
                            //发送一个通知来填充数据，因为安卓不允许在子线程中进行UI操作
                            newsHandler.sendMessage(msg);
                        } else {
                            //{"resultcode":"112","reason":"超过每日可允许请求次数!","result":null,"error_code":10012}
                            //实现从数据库加载数据
                            Log.d("超过请求次数或者其他原因", "run: 现在从本地数据库中获取");
                            Log.d("当前tab名字为：", "run: " + currentTabName);
                            threadLoaderData(currentTabName);
                        }
                    } else {
                        threadLoaderData(result.substring(3));
                    }
                }).start();
            }

            //当后台任务中调用了publishProgress(Progress...)方法后，onProgressUpdate方法很快被执行
            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }
        };
        //启动异步加载任务
        task.execute();
    }
}