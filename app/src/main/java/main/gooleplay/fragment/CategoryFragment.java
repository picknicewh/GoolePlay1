package main.gooleplay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import main.gooleplay.R;
import main.gooleplay.adpater.CategoryListAdapter;
import main.gooleplay.util.G;

/**
 * 作者： wh
 * 时间： 2016/11/2
 * 名称：分类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class CategoryFragment extends Fragment {
    /**
     * 显示列表
     */
    @Bind(R.id.rv_categroy)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, null);
        ButterKnife.bind(this, view);
        setRecyclerView();
        return view;
    }
    private void setRecyclerView(){
        G.initDisplaySize(getActivity());
        recyclerView.setAdapter(new CategoryListAdapter(getActivity()));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
