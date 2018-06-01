package com.example.wangjun.demoapk.HardwareDemo;

/**
 * Created by wangjun on 2018/6/1.
 */

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.wangjun.demoapk.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by small_qi on 2017/9/13.
 */

public class CBTCS_MsgAdapter extends RecyclerView.Adapter<CBTCS_MsgAdapter.MsgHolder>{

    private Context mContext;
    private List<String> msgList;


    public CBTCS_MsgAdapter(Context mContext) {
        this.mContext = mContext;
        msgList = new ArrayList<>();
    }

    @Override
    public CBTCS_MsgAdapter.MsgHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CBTCS_MsgAdapter.MsgHolder(LayoutInflater.from(mContext).inflate(R.layout.hardwaredemo_cbtcs_item,parent,false));
    }

    @Override
    public void onBindViewHolder(CBTCS_MsgAdapter.MsgHolder holder, final int position) {
        holder.nameTv.setText(msgList.get(position));
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    public void addMessage(String msg) {
        msgList.add(msg);
        notifyItemInserted(msgList.size()-1);
    }

    public void clearMsgList(){
        msgList.clear();
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onClick(BluetoothDevice device);
    }

    class MsgHolder extends RecyclerView.ViewHolder{
        private TextView nameTv;
        public MsgHolder(View itemView) {
            super(itemView);
            nameTv = (TextView)itemView.findViewById(R.id.name);
        }
    }
}