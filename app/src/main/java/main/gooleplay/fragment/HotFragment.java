package main.gooleplay.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import main.gooleplay.R;
import main.gooleplay.util.G;

/**
 * 作者： wh
 * 时间： 2016/11/2
 * 名称：排行
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class HotFragment extends Fragment {
    /**
     * 数据加载控件
     */
    @Bind(R.id.ll_hot)
    FlowLayout llHot;
    /**
     * 数据列表
     */
    private String[] texts;
    /**
     * 字体颜色列表
     */
    private int[] images;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, null);
        ButterKnife.bind(this, view);
        setLinearlayout();
        return view;
    }

    private void setLinearlayout() {

        texts = G.getHotText();
        images = G.getHotImages();
        for (int i = 0; i < texts.length; i++) {
            TextView textView = getTextView(i);
            llHot.addView(textView);
        }
    }
    private TextView getTextView(int i) {
        TextView textView = new TextView(getActivity());
        textView.setText(texts[i]);
        textView.setTextColor(Color.WHITE);
        LinearLayout.LayoutParams lps = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int marginLeft = G.dp2px(getActivity(), 5);
        int padding = G.dp2px(getActivity(),10);
        lps.setMargins(marginLeft,marginLeft,0,0);
        textView.setLayoutParams(lps);
        textView.setPadding(padding,padding,padding,padding);

        int t = (int) (Math.random() * 10);
        textView.setBackgroundResource(images[t]);
        return textView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
