package com.yixingu.recyclerviewwithhb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yixingu.recyclerviewwithhb.R;

/**
 * Created by likaisong on 17-3-20.
 */

public class HeadBottomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;
    //模拟数据
    public String [] texts={"item1","item2","item3","item4","item5","item6","item7","item8","item9","item10","item11","item12"};
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private int mHeaderCount=1;//头部View个数
    private int mBottomCount=1;//底部View个数
    public HeadBottomAdapter(final Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    //内容长度
    public int getContentItemCount(){
        return texts.length;
    }
    //判断当前item是否是HeadView
    public boolean isHeaderView(int position) {
        return mHeaderCount != 0 && position < mHeaderCount;
    }
    //判断当前item是否是FooterView
    public boolean isBottomView(int position) {
        return mBottomCount != 0 && position >= (mHeaderCount + getContentItemCount());
    }


    //判断当前item类型
    @Override
    public int getItemViewType(int position) {
        int dataItemCount = getContentItemCount();
        if (mHeaderCount != 0 && position < mHeaderCount) {
            //头部View
            return ITEM_TYPE_HEADER;
        } else if (mBottomCount != 0 && position >= (mHeaderCount + dataItemCount)) {
            //底部View
            return ITEM_TYPE_BOTTOM;
        } else {
            //内容View
            return ITEM_TYPE_CONTENT;
        }
    }

    //内容 ViewHolder
     static class ContentViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ContentViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.tv_item_text);
        }
    }
    //头部 ViewHolder
     static class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
    //底部 ViewHolder
     static class BottomViewHolder extends RecyclerView.ViewHolder {
        public BottomViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (viewType ==ITEM_TYPE_HEADER) {
            holder = new HeaderViewHolder(mLayoutInflater.inflate(R.layout.rv_header, parent, false));
        } else if (viewType == mHeaderCount) {
            holder = new ContentViewHolder(mLayoutInflater.inflate(R.layout.rv_item, parent, false));
        } else if (viewType == ITEM_TYPE_BOTTOM) {
            holder = new BottomViewHolder(mLayoutInflater.inflate(R.layout.rv_bottom, parent, false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeaderViewHolder) {
            //如果是头部

        } else if (holder instanceof ContentViewHolder) {
            //如果是item
            ((ContentViewHolder) holder).textView.setText(texts[position-mHeaderCount]);

        } else if (holder instanceof BottomViewHolder) {
            //如果是底部
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            String text = null;
            @Override
            public void onClick(View view) {
                if(isHeaderView(position)){
                    text = "Header";
                }else if(isBottomView(position)){
                    text = "Bottom";
                }else {
                    text = texts[position - mHeaderCount];
                }
                Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHeaderCount + getContentItemCount() + mBottomCount;
    }
}