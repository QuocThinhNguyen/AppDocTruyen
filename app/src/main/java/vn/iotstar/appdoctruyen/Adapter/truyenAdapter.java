package vn.iotstar.appdoctruyen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.iotstar.appdoctruyen.HomeFragment;
import vn.iotstar.appdoctruyen.R;
import vn.iotstar.appdoctruyen.model.truyen;

public class truyenAdapter extends RecyclerView.Adapter<truyenAdapter.MyViewHolder> {
    Context context;
    List<truyen> array;

    public truyenAdapter(Context context, List<truyen> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        truyen truyen1 = array.get(position);
        holder.tenTruyen.setText(truyen1.getTentruyen());
        Glide.with(context).load(truyen1.getLinkanh()).into(holder.images);
    }

    @Override
    public int getItemCount() {
        return array == null ? 0 :array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView images;
        public TextView tenTruyen;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            images = (ImageView) itemView.findViewById(R.id.img_truyen);
            tenTruyen = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
