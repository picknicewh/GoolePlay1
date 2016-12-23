package main.gooleplay.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import main.gooleplay.R;

/**
 * Created by wanghua on 2016/12/22.
 */
public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.MyViewHolder> {
    private Context context;

    public AppListAdapter(Context context) {
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_list,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return 10;
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivIcon;
        private TextView tvName;
        private TextView tvWeight;
        private TextView tvGetApp;
        private TextView tvDescrible;
        MyViewHolder(View view) {
            super(view);
            ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvWeight = (TextView) view.findViewById(R.id.tv_weight);
            tvGetApp = (TextView) view.findViewById(R.id.tv_get_app);
            tvDescrible = (TextView) view.findViewById(R.id.tv_describle);
        }
    }
}

