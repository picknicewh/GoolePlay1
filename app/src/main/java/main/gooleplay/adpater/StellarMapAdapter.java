package main.gooleplay.adpater;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.shitou.googleplay.lib.randomlayout.StellarMap;

import java.util.List;
import java.util.Random;


/**
 * Created by wanghua on 2016/12/23.
 */
public class StellarMapAdapter implements StellarMap.Adapter {
    private Context context;
    private List<String> list;
    public StellarMapAdapter(Context context,List<String> list){
     this.context = context;
        this.list = list;
    }
    /**
     * 返回多少组数据
     */
    @Override
    public int getGroupCount() {
        return list.size()/getCount(0);
    }
    /**
     * 每组多少个数据.每组11个
     */
    @Override
    public int getCount(int group) {
        return 11;
    }
    /**
     * group: 当前是第几组 position:是当前组的position
     */
    @Override
    public View getView(int group, int position, View convertView) {
        final TextView textView = new TextView(context);
        // 根据group和组中的position计算出对应的在list中的位置
        int listPosition = group * getCount(group) + position;
        textView.setText(position+list.get(listPosition));
        // 1.设置随机的字体大小(随机大小)
        Random random = new Random();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, random.nextInt(15) + 14);// 14-29
        // 2.上色，设置随机的字体颜色
        // 如果三原色的值过大会偏白色，过小会偏黑色，所以应该随机一个中间的颜色的值
        int red = random.nextInt(150) + 50;// 50-199
        int green = random.nextInt(150) + 50;// 50-199
        int blue = random.nextInt(150) + 50;// 50-199
        int textColor = Color.rgb(red, green, blue);// 在rgb三原色的基础上混合出一种新的颜色
        textView.setTextColor(textColor);
        return textView;
    }

    /**
     * 虽然定义了，但是并没有什么乱用
     */
    @Override
    public int getNextGroupOnPan(int group, float degree) {
        return 0;
    }
    /**
     * 当前组缩放完成之后下一组加载哪一组的数据 group： 表示当前是第几组
     */
    @Override
    public int getNextGroupOnZoom(int group, boolean isZoomIn) {
        // 0->1->2->0
        return (group + 1) % getGroupCount();
    }
}
