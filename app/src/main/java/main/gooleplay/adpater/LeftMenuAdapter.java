package main.gooleplay.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import main.gooleplay.R;

/**
 * Created by wanghua on 2016/12/22.
 */
public class LeftMenuAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String,Object>> list;
    public LeftMenuAdapter(Context context,List<Map<String,Object>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.item_left_menu, null);
            new ViewHolder(view);
        }
        viewHolder = (ViewHolder) view.getTag();
        Map<String,Object> map = list.get(i);
        String text = (String) map.get("text");
        int image = (int) map.get("image");
        viewHolder.tvIcon.setImageResource(image);
        viewHolder.tvText.setText(text);
        return view;
    }

    static class ViewHolder {
       private ImageView tvIcon;
       private TextView tvText;
        ViewHolder(View view) {
           tvIcon = (ImageView) view.findViewById(R.id.tv_icon);
            tvText = (TextView) view.findViewById(R.id.tv_text);
            view.setTag(this);
        }
    }
}
