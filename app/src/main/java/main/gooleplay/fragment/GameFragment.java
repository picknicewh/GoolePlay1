package main.gooleplay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import main.gooleplay.R;
import main.gooleplay.adpater.AppListAdapter;

/**
 * 作者： wh
 * 时间： 2016/11/2
 * 名称：游戏
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class GameFragment extends Fragment {
    @Bind(R.id.rv_game)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, null);
        ButterKnife.bind(this, view);
        setRecyclerView();
        return view;
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