package com.lzm.fusionnews.module.home.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lzm.fusionnews.R;
import com.lzm.fusionnews.module.home.model.ContentListItem;

import java.util.ArrayList;

/**
 * Created by lzm on 2018/4/13.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHodler> {

    private Context mContext;
    private ArrayList<ContentListItem> mDataList;

    public HomeAdapter(Context context, ArrayList<ContentListItem> data){
        mContext =  context;
        mDataList = data;
    }

    @Override
    public HomeViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_article, parent, false);
        return new HomeViewHodler(view);
    }

    @Override
    public void onBindViewHolder(HomeViewHodler holder, int position) {
        final ContentListItem bean = mDataList.get(position);
        holder.tv_title.setText(bean.title);
        holder.tv_desc.setText(bean.forward);
        holder.tv_like.setText(bean.likeCount + "");
        holder.tv_time.setText(bean.postDate.substring(5, 10));
        holder.cv_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ArticleDetailActivity.class);
                intent.putExtra("item_id", bean.itemId);
                intent.putExtra("category", bean.category);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class HomeViewHodler extends RecyclerView.ViewHolder{
        SimpleDraweeView sv_photo;
        TextView tv_title;
        TextView tv_desc;
        TextView tv_time;
        TextView tv_like;
        CardView cv_root;

        public HomeViewHodler(View itemView) {
            super(itemView);
            sv_photo = (SimpleDraweeView) itemView.findViewById(R.id.sv_article_photo);
            tv_title = (TextView) itemView.findViewById(R.id.tv_article_title);
            tv_desc = (TextView) itemView.findViewById(R.id.tv_article_desc);
            tv_time = (TextView) itemView.findViewById(R.id.tv_article_time);
            tv_like = (TextView) itemView.findViewById(R.id.tv_article_like);
            cv_root = (CardView) itemView.findViewById(R.id.cv_root);
        }
    }
}
