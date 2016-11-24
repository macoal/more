package com.one.more.see.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.one.more.doit.R;
import com.one.more.see.activity.MainActivity;
import com.one.more.see.bean.todo.TodoBean;
import com.one.util.ui.LoadingMore;
import com.one.util.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;


public class TodoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements LoadingMore {

    private static final int TYPE_LOADING_MORE = -1;
    private static final int NOMAL_ITEM = 1;

    boolean showLoadingMore;
    float width;
    int widthPx;
    int heighPx;
    private ArrayList<TodoBean> todoItems = new ArrayList<>();
    private Context mContext;


    public TodoAdapter(Context context) {

        this.mContext = context;
        width = 93;
        widthPx = DensityUtil.dip2px(mContext, width);
        heighPx = widthPx * 3 / 4;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case NOMAL_ITEM:
                return new TopNewsViewHolder(LayoutInflater.from(mContext).inflate(R.layout.fragment_doit_item, parent, false));

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
                bindViewHolderNormal((TopNewsViewHolder) holder, position);
                break;
            case TYPE_LOADING_MORE:
                bindLoadingViewHold((LoadingMoreHolder) holder, position);
                break;
        }
    }

    private void bindLoadingViewHold(LoadingMoreHolder holder, int position) {
        holder.progressBar.setVisibility(showLoadingMore? View.VISIBLE : View.INVISIBLE);
    }

    private void bindViewHolderNormal(final TopNewsViewHolder holder, final int position) {

        final TodoBean todoBeanItem = todoItems.get(holder.getAdapterPosition());

            holder.textView.setTextColor(Color.BLACK);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewsActivity(todoBeanItem, holder );
            }
        });
        holder.textView.setText(todoBeanItem.getThing());
    }

    private void startNewsActivity(TodoBean todoBeanItem , RecyclerView.ViewHolder holder){

            Intent intent = new Intent(mContext, MainActivity.class);
        Log.e("Activity", "startNewsActivity: +"+todoBeanItem.getThing());
            mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return todoItems.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (position < getDataItemCount()
                && getDataItemCount() > 0) {

            return NOMAL_ITEM;
        }
        return TYPE_LOADING_MORE;
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


    public void addItems(ArrayList<TodoBean> list) {
        list.remove(0);
        int n = list.size();
        todoItems.addAll(list);
        notifyDataSetChanged();
    }

    private int getDataItemCount() {

        return todoItems.size();
    }

    private int getLoadingMoreItemPosition() {
        return showLoadingMore ? getItemCount() - 1 : RecyclerView.NO_POSITION;
    }

    public void clearData() {
        todoItems.clear();
        notifyDataSetChanged();
    }

    public static class LoadingMoreHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public LoadingMoreHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView;
        }
    }

    class TopNewsViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView = null;

        TopNewsViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.doit_cardView);
            textView = (TextView) itemView.findViewById(R.id.doit_textView);
        }
    }


}
