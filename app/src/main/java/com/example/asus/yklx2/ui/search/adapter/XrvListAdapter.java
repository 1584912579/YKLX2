package com.example.asus.yklx2.ui.search.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asus.yklx2.R;
import com.example.asus.yklx2.bean.SearchBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by asus on 2018/5/11.
 */

public class XrvListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<SearchBean.DataBean> list;
    private LayoutInflater inflater;
    private int type=1;
    private OnListItemClickListener onListItemClickListener;


    public interface OnListItemClickListener {
        void OnItemClick(int position);
    }

    public XrvListAdapter(Context context, List<SearchBean.DataBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener) {
        this.onListItemClickListener = onListItemClickListener;
    }
    //点击切换布局的时候通过这个方法设置type
    public void setType(int type) {
        this.type = type;
        notifyDataSetChanged();
    }

    @Override
//用来获取当前项Item是哪种类型的布局
    public int getItemViewType(int position) {
        return type;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        XlvViewHolder xlvViewHolder;
        if (viewType == 1) {
            View view = inflater.inflate(R.layout.item2, parent, false);
            xlvViewHolder = new XlvViewHolder(view);
        }else {
            View view = inflater.inflate(R.layout.item3, parent, false);
            xlvViewHolder = new XlvViewHolder(view);
        }
        //rvrecommend_item
        return xlvViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        XlvViewHolder xlvViewHolder = (XlvViewHolder) holder;
        final SearchBean.DataBean dataBean = list.get(position);
        String imgUrl = dataBean.getImages().split("\\|")[0];
       // Glide.with(context).load(imgUrl).into(xlvViewHolder.iv);
        xlvViewHolder.iv.setImageURI(imgUrl);
        xlvViewHolder.tvTitle.setText(dataBean.getTitle());
        xlvViewHolder.tvPrice.setText("¥" + dataBean.getBargainPrice() + "");

        //设置条目监听
        xlvViewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onListItemClickListener != null) {
                    onListItemClickListener.OnItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }


    class XlvViewHolder extends RecyclerView.ViewHolder {

        private final LinearLayout ll;
        private final SimpleDraweeView iv;
        private final TextView tvTitle;
        private final TextView tvPrice;

        public XlvViewHolder(View itemView) {
            super(itemView);
            ll = itemView.findViewById(R.id.ll);
            iv = itemView.findViewById(R.id.iv);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }

    /**
     * 刷新
     * @param templist
     */
    public void refresh(List<SearchBean.DataBean> templist) {
        this.list.clear();
        this.list.addAll(templist);
        notifyDataSetChanged();
    }

    /**
     * 加载更多
     * @param list
     */
    public void loadMore(List<SearchBean.DataBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

}
