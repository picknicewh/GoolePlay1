package main.gooleplay.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import main.gooleplay.R;

/**
 * Created by wanghua on 2016/12/22.
 */
public class SubJectListAdapter extends RecyclerView.Adapter<SubJectListAdapter.MyViewHolder> {
    private Context context;

    public SubJectListAdapter(Context context) {
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_subject,null);
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
        private ImageView iv_subject;

        MyViewHolder(View view) {
            super(view);
            iv_subject = (ImageView) view.findViewById(R.id.iv_subject);
        }
    }
}

