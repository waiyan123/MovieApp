package itachi_waiyan.com.restapitest.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import itachi_waiyan.com.restapitest.R;
import itachi_waiyan.com.restapitest.activity.AboutAppActivity;
import itachi_waiyan.com.restapitest.activity.ShowAllMovieActivity;
import itachi_waiyan.com.restapitest.adapter.MyMenuAdapter;

public class MenuFragment extends Fragment {


    MyMenuAdapter myMenuAdapter ;

    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu,container,false);


        recyclerView = view.findViewById(R.id.menu_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myMenuAdapter = new MyMenuAdapter();
        myMenuAdapter.setOnItemClickListener(new MyMenuAdapter.OnItemClickListener() {
            @Override
            public void onItemSelected(int selectedItem) {
                Intent intent = new Intent(getContext(),ShowAllMovieActivity.class);
                switch (selectedItem){
                    case 0 :
                        intent.putExtra("key","topRated");
                        startActivity(intent);
                        break;
                    case 1 :
                        intent.putExtra("key","popular");
                        startActivity(intent);
                        break;
                    case 2 :
                        intent.putExtra("key","nowPlaying");
                        startActivity(intent);
                        break;
                    case 3 :
                        intent.putExtra("key","upcoming");
                        startActivity(intent);
                        break;
                    case 4 :
                        showDialog(getActivity());
                        break;
                    case 5 :
                        Intent in = new Intent(getContext(),AboutAppActivity.class);
                        startActivity(in);
                }
            }
        });
        recyclerView.setAdapter(myMenuAdapter);

        return view;
    }
    public void showDialog(Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_credits);

        TextView tvLogo = dialog.findViewById(R.id.tv_logo_icon);
        tvLogo.setText(getContext().getString(R.string.logo_icon));

        TextView tvOpenSource = dialog.findViewById(R.id.tv_open_source);
        tvOpenSource.setText(getContext().getString(R.string.opensource));

        TextView tvApi = dialog.findViewById(R.id.tv_api);
        tvApi.setText(getContext().getString(R.string.api));

        TextView tvImage = dialog.findViewById(R.id.tv_image);
        tvImage.setText(getContext().getString(R.string.image));

        TextView tvDatabase = dialog.findViewById(R.id.tv_database);
        tvDatabase.setText(getContext().getString(R.string.database));

        TextView tvDatatransfer = dialog.findViewById(R.id.tv_datatransfer);
        tvDatatransfer.setText(getContext().getString(R.string.data_transfer));

        TextView tvUi = dialog.findViewById(R.id.tv_ui);
        tvUi.setText(getContext().getString(R.string.ui));

        TextView tvGotit = dialog.findViewById(R.id.tv_gotit);
        tvGotit.setText(getContext().getString(R.string.got_it));

        tvGotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}
