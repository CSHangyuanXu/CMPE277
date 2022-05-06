package com.xhy.shoppingmall.type.fragment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import com.xhy.shoppingmall.R;
import com.xhy.shoppingmall.app.MainActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.icu.util.Output;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.JsonWriter;
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.squareup.picasso.Picasso;
import com.xhy.shoppingmall.R;
import com.xhy.shoppingmall.app.MainActivity;
import com.xhy.shoppingmall.app.Tool;
import com.xhy.shoppingmall.cart.fragment.CartList;

public class Detail extends AppCompatActivity {
    String priceString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();


    }




    public void init() {
        Intent intent =this.getIntent();
        String msg=intent.getStringExtra("name");
        String kind=intent.getStringExtra("kind");
        TextView name = (TextView) findViewById(R.id.name);
        TextView price = (TextView) findViewById(R.id.price);
        TextView stock = (TextView) findViewById(R.id.stock);
        TextView details = (TextView) findViewById(R.id.details);
        ImageView imgview = (ImageView) findViewById(R.id.imgae);
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

            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            JSONArray jsonArray = jsonObject.getJSONArray(kind);
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String nameString = object.getString("name");
                if(msg.equals(nameString)) {
                    name.setText("name: " + nameString);

                    priceString = object.getString("price");
                    price.setText("price: " + priceString);

                    String stockString = object.getString("stock");
                    stock.setText("stock: " + stockString);

                    String detailsString = object.getString("details");
                    details.setText("details: " + detailsString);
                    String url = object.getString("imgae");

                    Picasso.get().load(url).into(imgview);


                }

            }

                findViewById(R.id.addcart).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        add(msg,priceString);
                    }
                });


        }catch(IOException e){
            e.printStackTrace();

        }catch (JSONException e) {

            e.printStackTrace();
        }
    }

    public boolean isStoragePermissionGranted() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else {

            return true;
        }
    }

    private boolean isExternalStorageAvailableForRW() {

        String extStorageState = Environment.getExternalStorageState();
        if(extStorageState.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }
        return false;
    }

    public void add(String msg, String priceString) {


        try {
            Tool tool = new Tool();

            JSONArray jsonArray=new JSONArray();
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("name",msg);
            jsonObject.put("price",priceString);
            jsonArray.put(jsonObject);

            String jsonString=jsonArray.toString();//将jsonarray数组转化为字符串
            String JsonString=tool.stringToJSON(jsonString);//将jsonarrray字符串格式化
            System.out.println(JsonString);
//            FileOutputStream fileout = openFileOutput("cart.json", MODE_PRIVATE);
//            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
//            outputWriter.write(JsonString);
//            outputWriter.close();




            String FILENAME = "cart.json";
            String string = "hello world!";
            FileOutputStream fileWriter = openFileOutput (FILENAME,Context.MODE_PRIVATE);
            fileWriter.write(string.getBytes());
            fileWriter.flush();
            fileWriter.close();

//            Toast.makeText(getBaseContext(), "File saved successfully!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }


}



//                            FileOutputStream fos = openFileOutput("cart.json", Context.MODE_PRIVATE);
////                            JsonWriter writer = JsonWriter(OutputStreamWriter(fos,"utf-8"));
////                            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fos,"utf-8");//将字符流转换为字节流
////                            BufferedWriter bufferedWriter= new BufferedWriter(outputStreamWriter);//创建字符缓冲输出流对象
////                            FileWriter file = new FileWriter("D:\\ShoppingMall\\app\\src\\main\\assets\\cart.json");
//                            JSONArray jsonArray=new JSONArray();
//                            JSONObject jsonObject=new JSONObject();
//                            jsonObject.put("name",msg);
//                            jsonObject.put("price",priceString);
//                            jsonArray.put(jsonObject);
//                            String jsonString=jsonArray.toString();//将jsonarray数组转化为字符串
//                            String JsonString=tool.stringToJSON(jsonString);//将jsonarrray字符串格式化
//                            System.out.println(JsonString);
//                            fos.write(JsonString.getBytes());//将格式化的jsonarray字符串写入文件
////                            bufferedWriter.flush();//清空缓冲区，强制输出数据
////                            bufferedWriter.close();//关闭输出流
//
//
//                            Intent in = new Intent();
//                            in.setClass(Detail.this, CartList.class);
//                            startActivity(in);
//                        try {
//                                Tool tool = new Tool();
//                                File myExternalFile = new File(getExternalFilesDir("D:\\ShoppingMall\\app\\src\\main\\asset"), "cart.json");
//                                FileOutputStream fos = null;
//
//                                fos = new FileOutputStream(myExternalFile);
//                                JSONArray jsonArray = new JSONArray();
//                                JSONObject jsonObject = new JSONObject();
//                                jsonObject.put("name", msg);
//                                jsonObject.put("price", priceString);
//                                jsonArray.put(jsonObject);
//                                String jsonString = jsonArray.toString();//将jsonarray数组转化为字符串
//                                String JsonString = tool.stringToJSON(jsonString);
//                                fos.write(JsonString.getBytes());
//
//                                fos.close();
//                                }
//                                catch (FileNotFoundException e){
//                                e.printStackTrace();
//                                }
//                                catch (JSONException e){
//                                e.printStackTrace();
//                                }
//                                catch (IOException e){
//                                e.printStackTrace();
//                                }