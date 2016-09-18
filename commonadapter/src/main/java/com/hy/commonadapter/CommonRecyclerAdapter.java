package com.hy.commonadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.hy.commonadapter.interfaces.IAdapter;
import com.hy.commonadapter.interfaces.IData;

import java.util.ArrayList;
import java.util.List;

import static com.hy.commonadapter.BaseAdapterHelper.get;


/**
 * 项目名称: CommonAdapter
 * 包 名 称: com.hy.commonadapter
 * 类 描 述: 通用Adapter,适用于RecyclerView,简化大量重复代码
 * 创 建 人: qq8585083
 * 创建时间: 2016/1/27 17:50.
 */
public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter
    implements IAdapter<T>, IData<T> {
    protected final Context mContext;
    protected final int     mLayoutResId;
    protected final List<T> mData;


    public CommonRecyclerAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);
    }


    public CommonRecyclerAdapter(Context context, int layoutResId, List<T> data) {
        this.mData = data == null ? new ArrayList<T>() : new ArrayList<>(data);
        this.mContext = context;
        this.mLayoutResId = layoutResId;
    }


    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final BaseAdapterHelper helper = get(mContext, null, parent, viewType, -1);
        return new RecyclerViewHolder(helper.getView(), helper);
    }


    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseAdapterHelper helper = ((RecyclerViewHolder) holder).adapterHelper;
        helper.setAssociatedObject(getItem(position));
        onUpdate(helper, getItem(position), position);
    }

    @Override public int getItemViewType(int position) {
        return getLayoutResId(getItem(position), position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    public T getItem(int position) {
        return position >= mData.size() ? null : mData.get(position);
    }

    @Override public int getItemCount() {
        return mData.size();
    }

    @Override public int getLayoutResId(T item, int position) {
        return this.mLayoutResId;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        BaseAdapterHelper adapterHelper;

        public RecyclerViewHolder(View itemView, BaseAdapterHelper adapterHelper) {
            super(itemView);
            this.adapterHelper = adapterHelper;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if (null != onItemClickListener) {
                        onItemClickListener.onItemClick(RecyclerViewHolder.this, v,
                            getAdapterPosition());
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override public boolean onLongClick(View v) {
                    if (null != onItemLongClickListener) {
                        onItemLongClickListener.onItemLongClick(RecyclerViewHolder.this, v,
                            getAdapterPosition());
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(RecyclerView.ViewHolder viewHolder, View view, int position);
    }

    @Override public void add(T elem) {
        mData.add(elem);
        notifyItemInserted(mData.size());
    }

    @Override public void addAll(List<T> elem) {
        mData.addAll(elem);
        notifyItemRangeInserted(mData.size() - elem.size(), elem.size());
    }

    @Override public void set(T oldElem, T newElem) {
        set(mData.indexOf(oldElem), newElem);
    }

    @Override public void set(int index, T elem) {
        mData.set(index, elem);
        notifyItemChanged(index);
    }

    @Override public void remove(T elem) {
        final int position = mData.indexOf(elem);
        mData.remove(elem);
        notifyItemRemoved(position);
    }

    @Override public void remove(int index) {
        mData.remove(index);
        notifyItemRemoved(index);
    }

    @Override public void replaceAll(List<T> elem) {
        mData.clear();
        mData.addAll(elem);
        notifyDataSetChanged();
    }

    @Override public boolean contains(T elem) {
        return mData.contains(elem);
    }

    @Override public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }
}
