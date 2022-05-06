package com.xhy.shoppingmall.cart.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;


import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.xhy.shoppingmall.R;
import com.xhy.shoppingmall.type.fragment.Detail;


public class CartList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        init();

    }

    public void init() {
        TextView total = (TextView) findViewById(R.id.total);
        int price = 0;

        try{
            InputStreamReader inputStreamReader = new InputStreamReader(getAssets().open("cart.json"), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
            List<String> list = new ArrayList<>();
            ListView lv = (ListView) findViewById(R.id.cartlist);

            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("item");
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                price += object.getInt("price");
                try {
                    list.add(object.getString("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            total.setText("total: " + price);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
            if(lv != null) {
                lv.setAdapter(adapter);
            }

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Object o = lv.getItemAtPosition(position);
                    String str=(String)o;
//                    System.out.println(str);
                    Intent intent_delete = new Intent(CartList.this, Detail.class);
                    intent_delete.putExtra("name", str);
                    startActivity(intent_delete);


                }
            });



        }catch(IOException e){
            e.printStackTrace();

        }catch (JSONException e) {

            e.printStackTrace();
        }
    }

}

