package main.gooleplay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.shitou.googleplay.lib.randomlayout.StellarMap;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import main.gooleplay.R;
import main.gooleplay.adpater.StellarMapAdapter;
import main.gooleplay.util.G;

/**
 * 作者： wh
 * 时间： 2016/11/2
 * 名称：推荐
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class RecommendFragment extends Fragment {
    /**
     * 主布局
     */
    @Bind(R.id.fl)
    FrameLayout fl;
    /**
     * 数据列表
     */
    private List<String> list;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommed, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }
    private void setList() {
        list = new ArrayList<>();
        list.add("WIFI 万能钥匙");
        list.add("小说");
        list.add("优酷");
        list.add("电子书");
        list.add("放开那三国");
        list.add("视频");
        list.add("斗地主");
        list.add("网游");
        list.add("单机");
        list.add("QQ");
        list.add("机票");
        list.add("扑鱼达人2");
        list.add("游戏");
        list.add("优酷");
    }

    private void initView() {
        setList();
        StellarMap stellarMap = new StellarMap(getActivity());
        int padding = G.dp2px(getActivity(), 15);
        stellarMap.setInnerPadding(padding, padding, padding, padding);
        stellarMap.setAdapter(new StellarMapAdapter(getActivity(), list));
        stellarMap.setGroup(0, true);
        stellarMap.setRegularity(15, 15);
        fl.addView(stellarMap);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
