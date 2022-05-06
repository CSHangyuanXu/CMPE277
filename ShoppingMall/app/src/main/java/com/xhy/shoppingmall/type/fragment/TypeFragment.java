package com.xhy.shoppingmall.type.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View;
import com.xhy.shoppingmall.R;

import com.xhy.shoppingmall.base.BaseFragment;

public class TypeFragment extends BaseFragment {
    private TextView textView;



    @Override
    public View initView(){
        View view = View.inflate(mContext, R.layout.fragment_type,null);
        view.findViewById(R.id.food).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent in = new Intent();
               in.putExtra("button", "food");
               in.setClass(getActivity(), FoodActivity.class);
               startActivity(in);
           }
       });
        view.findViewById(R.id.drink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.putExtra("button", "drink");
                in.setClass(getActivity(), DrinkActivity.class);
                startActivity(in);
            }
        });
        view.findViewById(R.id.ball).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.putExtra("button", "ball");
                in.setClass(getActivity(), BallActivity.class);
                startActivity(in);
            }
        });


        return view;
    }


    public void initData() {
        super.initData();
    }


}


//new AlertDialog.Builder(Detail.this)
//        .setTitle()
//        .setMessage()
//        .setCancelable()
//        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialog, int which) {
//
//        }
//        })
//        .setNegativeButton("No",null)
//        .show();
//        }
