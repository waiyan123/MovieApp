package itachi_waiyan.com.restapitest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import itachi_waiyan.com.restapitest.service.model.ProductCompany;
import itachi_waiyan.com.restapitest.R;
import itachi_waiyan.com.restapitest.utils.Utils;

public class CompanyRecyclerAdapter extends RecyclerView.Adapter<CompanyRecyclerAdapter.ViewHolder> {

    List<ProductCompany>productCompanyList;
    Context context;

    public CompanyRecyclerAdapter(Context context, List<ProductCompany>list){
        productCompanyList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_product_company,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(productCompanyList!=null){
            ProductCompany company = productCompanyList.get(position);
            holder.tvComName.setText(company.getName());
            Glide.with(context).load(Utils.IMG_PATH_ORIGINAL+company.getLogo_path())
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(holder.imgCom);
        }
    }

    @Override
    public int getItemCount() {
        return productCompanyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvComName;
        ImageView imgCom;

        public ViewHolder(View itemView) {
            super(itemView);
            tvComName = itemView.findViewById(R.id.tvComName);
            imgCom = itemView.findViewById(R.id.comImg);
        }
    }
}
