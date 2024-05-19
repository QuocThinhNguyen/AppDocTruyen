package vn.iotstar.appdoctruyen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.iotstar.appdoctruyen.CTTruyen;
import vn.iotstar.appdoctruyen.R;
import vn.iotstar.appdoctruyen.model.truyen;

public class truyenAdapter extends RecyclerView.Adapter<truyenAdapter.MyViewHolder> {
    private Context context;
    private List<truyen> array;
    private String email;

    public truyenAdapter(Context context, List<truyen> array, String email) {
        this.context = context;
        this.array = array;
        this.email=email;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        truyen Truyen = array.get(position);
        if (Truyen == null){
            return;
        }
        holder.tenTruyen.setText(Truyen.getTentruyen());
        Glide.with(context).load(Truyen.getLinkanh()).into(holder.images);
        holder.ll_rcv.setOnClickListener(view -> {
            Intent intent=new Intent(holder.itemView.getContext(), CTTruyen.class);
            intent.putExtra("id_truyen",Truyen.getId());
            intent.putExtra("email",email);
            holder.itemView.getContext().startActivity(intent);
        });
    }



    @Override
    public int getItemCount() {
        if (array != null){
            return array.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView images;
        public TextView tenTruyen;
        private LinearLayout ll_rcv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            images = (ImageView) itemView.findViewById(R.id.img_truyen);
            tenTruyen = (TextView) itemView.findViewById(R.id.tv_title);
            ll_rcv=itemView.findViewById(R.id.ll_rcv);
        }
    }
}
