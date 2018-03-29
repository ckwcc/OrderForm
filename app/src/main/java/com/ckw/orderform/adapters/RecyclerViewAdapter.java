package com.ckw.orderform.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ckw.orderform.R;
import com.ckw.orderform.repository.OrderBean;

import java.util.List;

/**
 * Created by ckw
 * on 2018/3/20.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.OrderViewHolder>{

    private List<OrderBean> mData;
    private Context mContext;

    private ButtonClickListener mListener;

    public RecyclerViewAdapter(List<OrderBean> mData, Context mContext,ButtonClickListener mListener) {
        this.mData = mData;
        this.mContext = mContext;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_view,parent,false);
        OrderViewHolder orderViewHolder = new OrderViewHolder(view);
        return orderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderViewHolder holder, final int position) {
        OrderBean orderBean = mData.get(position);

        holder.mCustomName.setText(String.format(mContext.getResources().getString(R.string.customer_name),orderBean.getCustomerName()));
        holder.mClassify.setText(String.format(mContext.getResources().getString(R.string.classify),orderBean.getKind()));
        holder.mNumber.setText(String.format(mContext.getResources().getString(R.string.number),String.valueOf(orderBean.getNum())));
        holder.mUnitPrice.setText(String.format(mContext.getResources().getString(R.string.unit_price),String.valueOf(orderBean.getPrice())));
        holder.mLayer.setText(String.format(mContext.getResources().getString(R.string.layer),orderBean.getCeng()));
        holder.mBoxSize.setText(String.format(mContext.getResources().getString(R.string.box_size),orderBean.getSizeXiang()));
        holder.mPlankSize.setText(String.format(mContext.getResources().getString(R.string.plank_size),orderBean.getSizeBan()));
        holder.mLineSize.setText(String.format(mContext.getResources().getString(R.string.line_size),orderBean.getSizeYa()));
        Integer isYinshua = orderBean.getIsYinshua();
        if(isYinshua != null){
            if(isYinshua == 1){
                holder.mShouldPrint.setText(String.format(mContext.getResources().getString(R.string.is_print),"是"));
                holder.mPrintContent.setText(String.format(mContext.getResources().getString(R.string.print_content),orderBean.getYinshuaText()));
            }else {
                holder.mShouldPrint.setText(String.format(mContext.getResources().getString(R.string.is_print),"否"));
                holder.mPrintContent.setVisibility(View.INVISIBLE);
            }
        }

        holder.mConfiguration.setText(String.format(mContext.getResources().getString(R.string.configuration),orderBean.getPeizhi()));

        holder.mPriority.setText(String.format(mContext.getResources().getString(R.string.priotity),String.valueOf(orderBean.getPriority())));

        if(orderBean.getState() != null){
            if(orderBean.getState() == 1){
                holder.mState.setText(String.format(mContext.getResources().getString(R.string.state),"已确认"));
                holder.mConfirm.setText("已确认");
                holder.mConfirm.setClickable(false);
            }else {
                holder.mState.setText(String.format(mContext.getResources().getString(R.string.state),"未确认"));
                holder.mConfirm.setText("确认");
            }
        }else {
            holder.mConfirm.setText("确认");
        }

        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onDeleteButtonClick(position);
            }
        });

        if(orderBean.getState() != null){
            if(orderBean.getState() == 2){//2是未确认
                holder.mConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.onConfirmButtonClick(position,holder.mConfirm);
                    }
                });
            }
        }

        holder.mFix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onUpdateButtonClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder{
        private TextView mCustomName;
        private TextView mClassify;
        private TextView mNumber;
        private TextView mUnitPrice;
        private TextView mLayer;
        private TextView mBoxSize;
        private TextView mPlankSize;
        private TextView mLineSize;
        private TextView mShouldPrint;
        private TextView mConfiguration;
        private TextView mPrintContent;
        private TextView mState;
        private TextView mPriority;
        private Button mConfirm;
        private Button mDelete;
        private Button mFix;//修改
        public OrderViewHolder(View itemView) {
            super(itemView);
            mCustomName = itemView.findViewById(R.id.tv_customer_name);
            mClassify       = itemView.findViewById(R.id.tv_classify);
            mNumber         = itemView.findViewById(R.id.tv_number);
            mUnitPrice      = itemView.findViewById(R.id.tv_unit_price);
            mLayer          = itemView.findViewById(R.id.tv_layer);
            mBoxSize        = itemView.findViewById(R.id.tv_box_size);
            mPlankSize      = itemView.findViewById(R.id.tv_plank_size);
            mLineSize       = itemView.findViewById(R.id.tv_line_size);
            mShouldPrint    = itemView.findViewById(R.id.tv_should_print);
            mConfiguration  = itemView.findViewById(R.id.tv_configuration);
            mPrintContent   = itemView.findViewById(R.id.tv_print_content);
            mState = itemView.findViewById(R.id.tv_state);
            mPriority = itemView.findViewById(R.id.tv_priority);
            mConfirm = itemView.findViewById(R.id.btn_confirm);
            mDelete = itemView.findViewById(R.id.btn_delete);
            mFix = itemView.findViewById(R.id.btn_fix);

        }
    }


    public interface ButtonClickListener{
        void onDeleteButtonClick(int position);
        void onConfirmButtonClick(int position,Button view);
        void onUpdateButtonClick(int position);
    }
}
