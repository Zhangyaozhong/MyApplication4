package com.bwie.android.weekone.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.android.weekone.R;
import com.bwie.android.weekone.entity.HomeDataEntity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class SearchAdapter extends XRecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private Context context;
    private List<HomeDataEntity.DataBean> list;

    public SearchAdapter(Context context, List<HomeDataEntity.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_item_layout, parent, false);
        SearchViewHolder holder = new SearchViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.tv_title.setText(list.get(position).getTitle());
        holder.tv_price.setText("￥" + list.get(position).getPrice());
        String images = list.get(position).getImages();
        String[] split = images.split("!");
        if (split != null && split.length > 0) {
            Glide.with(context)
                    .load(split[0])
                    .into(holder.iv);
        } else {
            holder.iv.setImageResource(R.mipmap.ic_launcher);
        }

    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class SearchViewHolder extends XRecyclerView.ViewHolder {

        private final ImageView iv;
        private final TextView tv_title;
        private final TextView tv_price;

        public SearchViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.imageView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_price = itemView.findViewById(R.id.tv_price);
        }
    }
}
