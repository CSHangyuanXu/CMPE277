package com.xhy.shoppingmall.user.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import com.xhy.shoppingmall.R;
import com.xhy.shoppingmall.base.BaseFragment;

public class UserFragment extends BaseFragment {
    private TextView textView;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.user, null);
//        TextView name =  (TextView) view.findViewById(R.id.username);
        view.findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setClass(getActivity(), EditActivity.class);
                startActivity(in);
            }
        });
        Intent intent = getActivity().getIntent();
        TextView name = (TextView) view.findViewById(R.id.username);
        String msg = intent.getStringExtra("editname");
        if (msg != null) {
            name.setText("name: " +msg );
        }
        return view;
    }


    public void initData() {
        super.initData();
//        Log.e("TAG","UserFragment Date has been init");
//        textView.setText("UserFragment page xxxxx");
    }




}
