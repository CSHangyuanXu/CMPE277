package com.xhy.shoppingmall.type.fragment;


import androidx.appcompat.app.AppCompatActivity;
import com.xhy.shoppingmall.R;
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
import java.util.Map;
public class BallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball);
        init();
    }
    public void init() {
        Intent intent =this.getIntent();
        String msg=intent.getStringExtra("button");
        try{
            InputStreamReader inputStreamReader = new InputStreamReader(getAssets().open("data.json"), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
            List<String> list = new ArrayList<>();
            ListView lv = (ListView) findViewById(R.id.balllv);
            TextView name = (TextView) findViewById(R.id.name);
            TextView price = (TextView) findViewById(R.id.price);
            TextView stock = (TextView) findViewById(R.id.stock);
            TextView details = (TextView) findViewById(R.id.details);
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("ball");

            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                try {
                    list.add(object.getString("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
            if(lv != null) {
                lv.setAdapter(adapter);
            }

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Object o = lv.getItemAtPosition(position);
                    String str=(String)o;
                    Intent intent_delete = new Intent(BallActivity.this, Detail.class);
                    intent_delete.putExtra("name", str);
                    intent_delete.putExtra("kind", "ball");
                    startActivity(intent_delete);
                    //

                }
            });


        }catch(IOException e){
            e.printStackTrace();

        }catch (JSONException e) {

            e.printStackTrace();
        }
    }
}