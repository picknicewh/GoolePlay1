package main.gooleplay.view;

import android.support.v4.app.Fragment;

import java.util.List;
import java.util.Map;

/**
 * Created by wanghua on 2016/12/21.
 */
public interface MainView {
    void setData(List<Fragment> fragmentList,String[] titles);
    void setLeftMenuData(List<Map<String,Object>> lists);
}
