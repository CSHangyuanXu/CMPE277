package com.xhy.shoppingmall.cart.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.xhy.shoppingmall.R;
import com.xhy.shoppingmall.base.BaseFragment;

public class CartFragment extends BaseFragment {
    private TextView textView;

    @Override
    public View initView(){
        View view = View.inflate(mContext, R.layout.cartlist,null);
        view.findViewById(R.id.cartbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setClass(getActivity(), CartList.class);
                startActivity(in);
            }
        });
        return view;
    }


    public void initData() {
        super.initData();

    }



}
