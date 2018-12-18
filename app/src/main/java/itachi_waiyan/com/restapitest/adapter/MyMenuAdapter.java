package itachi_waiyan.com.restapitest.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import itachi_waiyan.com.restapitest.R;

public class MyMenuAdapter extends RecyclerView.Adapter<MyMenuAdapter.MenuViewHolder> {

    private OnItemClickListener onItemClickListener;

    String [] menuList = {"Get Top rated Movies","Get Popular Movies","Get Now Playing Movies","Get Upcoming Movies","Credits","About App"};

    int itemIndex;


    public static class MenuViewHolder extends RecyclerView.ViewHolder {

        final View mView;
        TextView tvItem;
        public MenuViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            tvItem = itemView.findViewById(R.id.tv_item);
        }
    }
    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_menu,parent,false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, final int position) {


        holder.tvItem.setText(menuList[position]);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener!=null) onItemClickListener.onItemSelected(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuList.length;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemSelected(int selectedItem);
    }
}
