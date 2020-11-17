package com.example.mynews;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.mynews.tools.BaseActivity;
import com.example.mynews.tools.DataCleanManager;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);

        //获取抽屉布局实例
        mDrawerLayout = findViewById(R.id.drawer_layout);

        //获取菜单控件实例
        navigationView = findViewById(R.id.nav_design);

        //无法直接通过findViewById方法获取header布局id
        View v = navigationView.getHeaderView(0);


        tabLayout = findViewById(R.id.tabLayout);

        viewPager = findViewById(R.id.viewPager);

        list = new ArrayList<>();
    }

    // 在活动由不可见变为可见的时候调用
    @Override
    protected void onStart() {
        super.onStart();
        //设置标题栏标题
        toolbar.setTitle("新闻");
        //设置自定义的标题栏实例
        setSupportActionBar(toolbar);
        //获取到ActionBar的实例
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //通过HomeAsUp来让导航按钮显示出来
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置Indicator来添加一个点击图标（默认图标是一个返回的箭头）
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        //设置默认选中第一个
        //设置菜单项的监听事件
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            //逻辑页面处理
            mDrawerLayout.closeDrawers();
            if (menuItem.getItemId() == R.id.nav_clear_cache) {// 清除缓存
                // Toast.makeText(MainActivity.this,"你点击了清除缓存，下步实现把",Toast.LENGTH_SHORT).show();
                clearCacheData();
            }
            return true;
        });
        // 新闻种类tab标题
        list.add("头条");
        list.add("社会");
        list.add("国内");
        list.add("国际");
        list.add("娱乐");
        list.add("体育");
        list.add("军事");
        list.add("科技");
        list.add("财经");
        list.add("时尚");

        // 表示ViewPager（默认）预加载一页
        viewPager.setOffscreenPageLimit(1);

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager(), 1) {
            //得到当前页的标题，也就是设置当前页面显示的标题是tabLayout对应标题
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return list.get(position);
            }

            //返回position位置关联的Fragment。
            @Override
            public Fragment getItem(int position) {
                NewsFragment newsFragment = new NewsFragment();
                Log.d("MainActivity", "新建fragment" + position);
                //判断所选的标题，进行传值显示
                //Bundle主要用于传递数据；它保存的数据，是以key-value(键值对)的形式存在的。
                Bundle bundle = new Bundle();
                switch (list.get(position)) {
                    case "头条":
                        bundle.putString("name", "top");
                        break;
                    case "社会":
                        bundle.putString("name", "shehui");
                        break;
                    case "国内":
                        bundle.putString("name", "guonei");
                        break;
                    case "国际":
                        bundle.putString("name", "guoji");
                        break;
                    case "娱乐":
                        bundle.putString("name", "yule");
                        break;
                    case "体育":
                        bundle.putString("name", "tiyu");
                        break;
                    case "军事":
                        bundle.putString("name", "junshi");
                        break;
                    case "科技":
                        bundle.putString("name", "keji");
                        break;
                    case "财经":
                        bundle.putString("name", "caijing");
                        break;
                    case "时尚":
                        bundle.putString("name", "shishang");
                        break;
                }
                Log.d("MainActivity", "传值：" + list.get(position));
                //设置当前newsFragment的bundle
                newsFragment.setArguments(bundle);
                return newsFragment;
            }

            // 创建指定位置的页面视图
            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                return (NewsFragment) super.instantiateItem(container, position);
            }

            @Override
            public int getItemPosition(@NonNull Object object) {
                return FragmentStatePagerAdapter.POSITION_NONE;
            }

            //返回当前有效视图的数量，这其实也就是将list和tab选项卡关联起来
            @Override
            public int getCount() {
                return list.size();
            }
        });
        //将TabLayout与ViewPager关联显示
        tabLayout.setupWithViewPager(viewPager);
        // 加载上次登录的账号，起到记住会话的功能
        String inputText = load();
        View v = navigationView.getHeaderView(0);

    }


    // 通过登录来接收值
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        View v = navigationView.getHeaderView(0);
    }


    public void clearCacheData() {
        // 缓存目录为 /data/user/0/com.example.viewnews/cache
        File file = new File(MainActivity.this.getCacheDir().getPath());
//        System.out.println("缓存目录为：" + MainActivity.this.getCacheDir().getPath());
        String cacheSize = null;
        try {
            cacheSize = DataCleanManager.getCacheSize(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println("缓存大小为：" + cacheSize);
        new MaterialDialog.Builder(MainActivity.this)
                .title("提示")
                .content("当前缓存大小一共为" + cacheSize + "。确定要删除所有缓存？离线内容及其图片均会被清除。")
                .positiveText("确认")
                .onPositive((dialog, which) -> {
                    // dialog 此弹窗实例共享父实例
                    // 没起作用
                    DataCleanManager.cleanInternalCache(MainActivity.this);
                    Toast.makeText(MainActivity.this, "成功清除缓存。", Toast.LENGTH_SHORT).show();
                })
                .negativeText("取消")
                .show();

    }

    //加载标题栏的菜单布局
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //获取toolbar菜单项
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    //监听标题栏的菜单item的选择事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //R.id.home修改导航按钮的点击事件为打开侧滑
            case android.R.id.home:
                //打开侧滑栏，注意要与xml中保持一致START
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.userFeedback:
                //填写用户反馈
                new MaterialDialog.Builder(MainActivity.this)
                        .title("用户反馈")
                        .inputRangeRes(1, 50, R.color.colorBlack)
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .input(null, null, (dialog, input) -> Toast.makeText(MainActivity.this, "反馈成功！反馈内容为：" + input, Toast.LENGTH_SHORT).show())
                        .positiveText("确定")
                        .negativeText("取消")
                        .show();
                break;
            default:
        }
        return true;
    }

    // 加载数据
    public String load() {
        //读取我们之前存储到data文件中的账号，方便下次启动app时直接使用
        FileInputStream in;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}