package com.xhy.shoppingmall.home.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xhy.shoppingmall.base.BaseFragment;
import com.xhy.shoppingmall.R;

//import okhttp3.OkHttpClient;

public class HomeFragment extends BaseFragment {
    private static  final String TAG = HomeFragment.class.getSimpleName();
    private RecyclerView rvHome;
    private ImageView ib_top;
    private TextView tv_search_home;
    private TextView tv_message_home;


    @Override
    public View initView(){
//        Log.e("TAG","Home UI has been init");
        View view = View.inflate(mContext, R.layout.fragment_home,null);

        return view;
    }


    public void initData() {
        super.initData();

    }



}
