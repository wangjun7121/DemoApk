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
import com.example.wangjun.demoapk.Tools.LogUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by small_qi on 2017/9/13.
 */

public class CBTCS_RvAdapter extends RecyclerView.Adapter<CBTCS_RvAdapter.RvHolder>{
    private Context mContext;
    private List<BluetoothDevice> mDevices;
    private OnItemClickListener onItemClickListener;

    // 设置按键处理回调
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CBTCS_RvAdapter(Context mContext) {
        this.mContext = mContext;
        mDevices = new ArrayList<>();
    }

    @Override
    // 负责为 Item 创建视图
    public RvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RvHolder(LayoutInflater.from(mContext).inflate(R.layout.hardwaredemo_cbtcs_item,parent,false));
    }

    @Override
    // 负责将数据绑定到 item 的视图上
    public void onBindViewHolder(RvHolder holder, final int position) {
        holder.nameTv.setText(mDevices.get(position).getName()+":   "+mDevices.get(position).getAddress());
        LogUtil.d(mDevices.get(position).getName()+" "+position+":   "+mDevices.get(position).getAddress());


        //点击事件 点击配对
        if (onItemClickListener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LogUtil.d(mDevices.get(position)+" "+position);

                    // 回调到主程序中进行按键响应
                    onItemClickListener.onClick(mDevices.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDevices.size();
    }

    public void addDevice(BluetoothDevice device) {
        mDevices.add(device);
        notifyItemInserted(mDevices.size()-1);
    }

    public void clearDevices(){
        mDevices.clear();
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onClick(BluetoothDevice device);
    }

    /**
     * 希望读者有良好的编码习惯，将ViewHolder类写成静态的.
     * 用于提供内容布局显示的
     **/
    class RvHolder extends RecyclerView.ViewHolder{
        private TextView nameTv;
        public RvHolder(View itemView) {
            super(itemView);
            nameTv = (TextView)itemView.findViewById(R.id.name);
        }
    }
}