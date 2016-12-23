package main.gooleplay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import main.gooleplay.R;
import main.gooleplay.adpater.AppListAdapter;
import main.gooleplay.widget.BannerView;

/**
 * 作者： wh
 * 时间： 2016/11/2
 * 名称：首页
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class HomeFragment extends Fragment {
    /**
     * 广告图
     */
    @Bind(R.id.bv_home)
    BannerView bannerView;
    /**
     * 显示列表
     */
    @Bind(R.id.rv_home)
    RecyclerView recyclerView;
    /**
     * 图片列表
     */
    private List<ImageView> viewList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        viewList = new ArrayList<>();
        int[] image = new int[]{R.mipmap.ic_banner1, R.mipmap.ic_banner2, R.mipmap.ic_banner3, R.mipmap.ic_banner4};
        for (int i = 0; i < image.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(image[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewList.add(imageView);
        }
        bannerView.setViewList(viewList);
        bannerView.addViewPager(getActivity());
        setRecyclerView();
        bannerView.requestFocus();
    }
    private void setRecyclerView(){
        recyclerView.setAdapter(new AppListAdapter(getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL,false));
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}