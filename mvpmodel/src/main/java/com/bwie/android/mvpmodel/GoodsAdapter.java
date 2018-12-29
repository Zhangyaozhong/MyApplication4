package com.bwie.android.mvpmodel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.android.mvpmodel.entity.GoodsPojo;

import java.util.List;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.GoodsViewHolder> {
    private Context context;
    private List<GoodsPojo.Product> list;

    public GoodsAdapter(Context context, List<GoodsPojo.Product> list) {
        this.context = context;
        this.list = list;
    }

    /**
     * 创建viewholder实例
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public GoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.goods_item_layout, parent, false);
        GoodsViewHolder holder = new GoodsViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsViewHolder holder, final int position) {
        holder.tv.setText(list.get(position).title);
        String images = list.get(position).images;
//        holder.itemView.setTag(list.get(position));
        String[] imgArr = images.split("\\|");//转义字符
        if (imgArr != null && imgArr.length > 0) {
            Glide.with(context)
                    .load(imgArr[0])
                    .into(holder.iv);
        } else {
//            没有图片的时候
            holder.iv.setImageResource(R.mipmap.ic_launcher);
        }
        //        点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(position,view);
            }
        });
//        长按事件
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                clickListener.onItemLongClick(position,view);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public class GoodsViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tv;

        public GoodsViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.imageView);
            tv = itemView.findViewById(R.id.textView);

        }
    }

    public interface ClickListener {
        void onItemClick(int position,View view);

        void onItemLongClick(int position,View view);
    }

    private ClickListener clickListener;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
