package com.xhy.shoppingmall.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.usage.NetworkStatsManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.fragment.app.Fragment;


import com.xhy.shoppingmall.R;
import com.xhy.shoppingmall.base.BaseFragment;
import com.xhy.shoppingmall.cart.fragment.CartFragment;
import com.xhy.shoppingmall.home.fragment.HomeFragment;
import com.xhy.shoppingmall.type.fragment.TypeFragment;
import com.xhy.shoppingmall.user.fragment.UserFragment;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    private int position = 0;
    private BaseFragment tempFragment;
    private RadioGroup rg_main;

    private ArrayList<BaseFragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        rg_main = (RadioGroup)findViewById(R.id.rg_main);
        initFragment();
        initListener();

//        rg_main.check(R.id.rb_home);
    }

    private void initListener() {
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;

                    case R.id.rb_type:
                        position = 1;
                        break;


                    case R.id.rb_cart:
                        position = 2;
                        break;

                    case R.id.rb_user:
                        position = 3;
                        break;
                }
                System.out.println(position);
                BaseFragment baseFragment = getFragment(position);
                switchFragment(tempFragment, baseFragment);
            }
        });

        rg_main.check(R.id.rb_home);

    }

    private BaseFragment getFragment(int position) {

        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }

        return null;
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CartFragment());
        fragments.add(new UserFragment());
    }

    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {

        if (tempFragment != nextFragment) {

            tempFragment = nextFragment;

            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                if (!nextFragment.isAdded()) {

                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }

    }
}