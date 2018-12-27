package com.bwie.android.zhangyaozhong1227.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.android.zhangyaozhong1227.R;
import com.bwie.android.zhangyaozhong1227.entity.GoodsEntity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<GoodsEntity.DataBean> list;
    public static final int TYPE_ONE = 0;
    public static final int TYPE_TWO = 1;
    private final DisplayImageOptions options;

    public MyAdapter(Context context, List<GoodsEntity.DataBean> list) {
        this.context = context;
        this.list = list;
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true) //设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                .displayer(new CircleBitmapDisplayer(2, 1))
                .build();
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
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return TYPE_ONE;
        } else {
            return TYPE_TWO;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int type = getItemViewType(i);
        if (type == TYPE_ONE) {
            OneViewHolder holder = new OneViewHolder();
            if (view == null) {
                view = View.inflate(context, R.layout.one_layout, null);
                holder.imageView = view.findViewById(R.id.img);
                holder.one_tv1 = view.findViewById(R.id.oneTV1);
                holder.one_tv2 = view.findViewById(R.id.oneTV2);
                view.setTag(holder);
            } else {
                holder = (OneViewHolder) view.getTag();
            }
            String images = list.get(i).getImages();
            String[] split = images.split("!");
            List<String> imgList = new ArrayList<>();
            for (String s : split) {
                imgList.add(s);
            }
            ImageLoader.getInstance().displayImage(imgList.get(0), holder.imageView,options );
            holder.one_tv1.setText(list.get(i).getTitle());
            holder.one_tv2.setText(list.get(i).getPrice() + "");
        } else {
            TwoViewHolder holder2 = new TwoViewHolder();
            if (view == null) {
                view = View.inflate(context, R.layout.two_layout, null);
                holder2.two_tv = view.findViewById(R.id.twoTV);
                view.setTag(holder2);
            } else {
                holder2 = (TwoViewHolder) view.getTag();
            }
            holder2.two_tv.setText(list.get(i).getTitle());
        }
        return view;
    }

    class OneViewHolder {
        public ImageView imageView;
        public TextView one_tv1, one_tv2;
    }

    class TwoViewHolder {
        public TextView two_tv;
    }
}
