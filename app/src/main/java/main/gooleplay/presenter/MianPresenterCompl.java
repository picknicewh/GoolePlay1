package main.gooleplay.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.gooleplay.R;
import main.gooleplay.fragment.AppFragment;
import main.gooleplay.fragment.CategoryFragment;
import main.gooleplay.fragment.GameFragment;
import main.gooleplay.fragment.HomeFragment;
import main.gooleplay.fragment.HotFragment;
import main.gooleplay.fragment.RecommendFragment;
import main.gooleplay.fragment.SubjectFragment;
import main.gooleplay.view.MainView;

/**
 * Created by wanghua on 2016/12/22.
 */
public class MianPresenterCompl implements MianPresenter {
    private MainView mainView;
    private Context context;
    public MianPresenterCompl(MainView mainView, Context context){
       this.mainView = mainView;
        this.context = context;
    }
    @Override
    public void getFragmentList() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new AppFragment());
        fragments.add(new GameFragment());
        fragments.add(new SubjectFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new CategoryFragment());
        fragments.add(new HotFragment());
        String[]  titles = context.getResources().getStringArray(R.array.tab_title);
        mainView.setData(fragments,titles);
    }

    @Override
    public void getLeftMenuData() {
        List<Map<String,Object>> lists = new ArrayList<>();
        int[] image = new int[]{R.mipmap.ic_anchor,R.mipmap.ic_my,R.mipmap.ic_copy_right,
                R.mipmap.ic_dynamic,R.mipmap.ic_photo,R.mipmap.ic_anchor,R.mipmap.ic_copy_right,R.mipmap.ic_collection};
        String[] text = context.getResources().getStringArray(R.array.left_menu);
        for (int i = 0 ; i <text.length;i++){
            Map<String,Object> map  = new HashMap<>();
            map.put("image",image[i]);
            map.put("text",text[i]);
            lists.add(map);
        }
        mainView.setLeftMenuData(lists);
    }
}
