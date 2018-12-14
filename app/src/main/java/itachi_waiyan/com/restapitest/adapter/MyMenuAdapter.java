package itachi_waiyan.com.restapitest.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import itachi_waiyan.com.restapitest.R;

public class MyMenuAdapter extends RecyclerView.Adapter<MyMenuAdapter.MenuViewHolder> {

    private OnObjectSelectListener onObjectSelectListener;

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_menu,parent,false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {

        public MenuViewHolder(View itemView) {
            super(itemView);
        }
    }
    public void setOnObjectSelectListener(OnObjectSelectListener onObjectSelectListener){
        this.onObjectSelectListener = onObjectSelectListener;
    }
}
