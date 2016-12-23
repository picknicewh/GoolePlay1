package main.gooleplay.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import main.gooleplay.R;
import main.gooleplay.util.G;

/**
 * Created by wanghua on 2016/12/23.
 */
public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.MyViewHolder> {

    private Context context;

    public CategoryListAdapter(Context context) {
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_list, null);
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

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_image;
        private TextView tv_name;
        private LinearLayout linearLayout;
        MyViewHolder(View view) {
            super(view);
            iv_image = (ImageView) view.findViewById(R.id.iv_image);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            linearLayout = (LinearLayout) view.findViewById(R.id.ll_item);
            setLinearLayout(linearLayout);
        }
        private void setLinearLayout(  LinearLayout linearLayout){
            int size = (G.size.W-G.dp2px(context,20))/3;
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(size,size);
            lp.gravity = Gravity.CENTER;
            linearLayout.setLayoutParams(lp);
        }
    }
}
