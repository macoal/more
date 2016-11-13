package com.one.more.ui.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.one.more.R;
import com.one.more.activity.ZHDescribeActivity;
import com.one.more.bean.zhihu.ZhihuDailyItem;
import com.one.more.ui.widget.LoadingMore;
import com.one.more.util.DensityUtil;

import java.util.ArrayList;


public class ZhihuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements LoadingMore {

    private static final int TYPE_LOADING_MORE = -1;
    private static final int NOMAL_ITEM = 1;
    boolean showLoadingMore;
    float width;
    int widthPx;
    int heighPx;
    private ArrayList<ZhihuDailyItem> zhihuDailyItems = new ArrayList<>();
    private Context mContext;
    private String mImageUrl;


    public ZhihuAdapter(Context context) {

        this.mContext = context;
        width = 93;
        widthPx = DensityUtil.dip2px(mContext, width);
        heighPx = widthPx * 3 / 4;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case NOMAL_ITEM:
                return new ZhihuViewHolder(LayoutInflater.from(mContext).inflate(R.layout.zhihu_layout_item, parent, false));

            case TYPE_LOADING_MORE:
                return new LoadingMoreHolder(LayoutInflater.from(mContext).inflate(R.layout.infinite_loading, parent, false));

        }
        return null;

    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        int type = getItemViewType(position);
        switch (type) {
            case NOMAL_ITEM:
                bindViewHolderNormal((ZhihuViewHolder) holder, position);
                break;
            case TYPE_LOADING_MORE:
                bindLoadingViewHold((LoadingMoreHolder) holder, position);
                break;
        }


    }

    private void bindLoadingViewHold(LoadingMoreHolder holder, int position) {
        holder.progressBar.setVisibility(showLoadingMore == true ? View.VISIBLE : View.INVISIBLE);
    }

    private void bindViewHolderNormal(final ZhihuViewHolder holder, final int position) {

        final ZhihuDailyItem zhihuDailyItem = zhihuDailyItems.get(holder.getAdapterPosition());

        holder.textView.setTextColor(Color.BLACK);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               goDescribeActivity(holder,zhihuDailyItem);

            }
        });
        holder.textView.setText(zhihuDailyItem.getTitle());
        holder.linearLayout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goDescribeActivity(holder,zhihuDailyItem);
                    }
                });


        Glide.with(mContext)
                .load(zhihuDailyItems.get(position).getImages()[0])
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop().override(widthPx, heighPx)
                .into(holder.imageView);

    }

    private void goDescribeActivity(ZhihuViewHolder holder,ZhihuDailyItem zhihuDailyItem){

        holder.textView.setTextColor(Color.GRAY);
        Intent intent = new Intent(mContext, ZHDescribeActivity.class);
        intent.putExtra("id", zhihuDailyItem.getId());
        intent.putExtra("title", zhihuDailyItem.getTitle());
        intent.putExtra("image",mImageUrl);
        mContext.startActivity(intent);

    }


    @Override
    public int getItemCount() {
        return zhihuDailyItems.size();
    }


    public void addItems(ArrayList<ZhihuDailyItem> list) {

        int n = list.size();
        zhihuDailyItems.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        if (position < getDataItemCount()
                && getDataItemCount() > 0) {

            return NOMAL_ITEM;
        }
        return TYPE_LOADING_MORE;
    }

    private int getDataItemCount() {

        return zhihuDailyItems.size();
    }

    private int getLoadingMoreItemPosition() {
        return showLoadingMore ? getItemCount() - 1 : RecyclerView.NO_POSITION;
    }

    @Override
    public void loadingStart() {
        if (showLoadingMore) return;
        showLoadingMore = true;
        notifyItemInserted(getLoadingMoreItemPosition());
    }

    @Override
    public void loadingfinish() {
        if (!showLoadingMore) return;
        final int loadingPos = getLoadingMoreItemPosition();
        showLoadingMore = false;
        notifyItemRemoved(loadingPos);
    }


    public void clearData() {
        zhihuDailyItems.clear();
        notifyDataSetChanged();
    }

    public static class LoadingMoreHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public LoadingMoreHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView;
        }
    }

    class ZhihuViewHolder extends RecyclerView.ViewHolder {
        final TextView textView;
        final LinearLayout linearLayout;
        final ImageView imageView;

        ZhihuViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_image_id);
            textView = (TextView) itemView.findViewById(R.id.item_text_id);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.zhihu_item_layout);
        }
    }


}
