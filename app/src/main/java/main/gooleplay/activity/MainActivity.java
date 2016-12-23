package main.gooleplay.activity;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import main.gooleplay.R;
import main.gooleplay.adpater.LeftMenuAdapter;
import main.gooleplay.adpater.SimplePagerAdapter;
import main.gooleplay.base.BaseActivity;
import main.gooleplay.presenter.MianPresenterCompl;
import main.gooleplay.util.G;
import main.gooleplay.view.MainView;
import main.gooleplay.widget.PagerSlidingTabStrip;

public class MainActivity extends BaseActivity implements MainView{
    /**
     * 页面
     */
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    /**
     * 页面的头部tab
     */
    @Bind(R.id.psts)
    PagerSlidingTabStrip psts;
    /**
     * 侧滑菜单
     */
    @Bind(R.id.lv_left)
    ListView lvLeft;
    /**
     * 抽屉布局
     */
    @Bind(R.id.dl_left)
    DrawerLayout drawerLayout;
    /**
     * 适配器
     */
    private SimplePagerAdapter adapter;
    /**
     *开关
     */
    private ActionBarDrawerToggle toggle;
    /**
     * 数据处理
     */
    private MianPresenterCompl compl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        G.initDisplaySize(this);
        ButterKnife.bind(this);
        initView();
    }
    @Override
    protected void setToolBar() {
     //  setLiftImage(R.mipmap.ic_more);
        setCententTitle("谷歌市场");
    }
    private void initView(){
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
              //  setLiftImage(R.mipmap.ic_back);
                invalidateOptionsMenu();
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
              //  setLiftImage(R.mipmap.ic_more);
                invalidateOptionsMenu();
            }
        };
        toggle.syncState();
        drawerLayout.setDrawerListener(toggle);
        initData();
    }
    private void initData(){
        compl = new MianPresenterCompl(this,MainActivity.this);
        compl.getFragmentList();
        compl.getLeftMenuData();
    }
    @Override
    public void setData(List<Fragment> fragmentList, String[] titles) {
            adapter = new SimplePagerAdapter(getSupportFragmentManager(), fragmentList, titles);
            viewpager.setAdapter(adapter);
            psts.setViewPager(viewpager);
    }

    @Override
    public void setLeftMenuData(List<Map<String, Object>> lists) {
        LeftMenuAdapter leftMenuAdapter  = new LeftMenuAdapter(this,lists);
        lvLeft.setAdapter(leftMenuAdapter);
        lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
             if (position==1){
                 viewpager.setCurrentItem(0);
             }
            }
        });
    }

}
