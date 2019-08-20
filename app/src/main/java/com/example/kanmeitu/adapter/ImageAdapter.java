package com.example.kanmeitu.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kanmeitu.R;
import com.example.kanmeitu.domain.Image;
import com.example.kanmeitu.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter <ImageAdapter.ViewHolder>{

    private OnItemClickListener onItemClickListener;
    private List<Image> datas = new ArrayList<Image>();
    private final LayoutInflater inflater;
    private final Context context;

    public ImageAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //注意这里一定要将parent传递到inflater方法
        return new ViewHolder(inflater.inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(datas.get(position));

        if(onItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setDatas(List<Image> datas){
        this.datas.clear();
        this.datas.addAll(datas);

        //刷新数据，只有刷新数据RecycleView才知道数据变更了
        notifyItemRangeInserted(0, this.datas.size());

    }

    public Image getData(int position) {
        return datas.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
        }

        public void bindData(Image image){
            //显示图片
            ImageUtil.show((Activity) context, iv, image.getUri());
        }

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
