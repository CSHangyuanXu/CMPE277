package com.xhy.shoppingmall.user.fragment;

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

public class EditActivity extends AppCompatActivity {
    // Declare the View object references
    Button btnSave, btnLoad;
    EditText etInput;
    TextView tvLoad;
    // Define some String variables, initialized with empty string
    String filename = "";
    String filepath = "";
    String fileContent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        // Get handles for the views
        btnSave = findViewById(R.id.btnSave);
//        btnLoad = findViewById(R.id.btnLoad);
        etInput = findViewById(R.id.etInput);
        tvLoad = findViewById(R.id.tvLoad);

        filename = "myFile.txt";
        filepath = "MyFileDir";

        if(!isExternalStorageAvailableForRW()){
            btnSave.setEnabled(false);
        }
        // Attach OnClickListener with save button
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvLoad.setText("");
                fileContent = etInput.getText().toString().trim();
                if(isStoragePermissionGranted()){
                    if(!fileContent.equals("")){
                        File myExternalFile = new File(getExternalFilesDir(filepath), filename);
                        FileOutputStream fos = null;
                        try {
                            fos = new FileOutputStream(myExternalFile);

                            fos.write(fileContent.getBytes());

                            fos.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        etInput.setText("");

                        Intent in = new Intent();
                        System.out.println(fileContent);
                        in.putExtra("editname",fileContent);

                        in.setClass(EditActivity.this, MainActivity.class);
                        startActivity(in);
                    }else{

//                        Toast.makeText(EditActivity.this, "Text field can not be empty.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
//        btnLoad.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                FileReader fr = null;
//                File myExternalFile = new File(getExternalFilesDir(filepath), filename);
//
//                StringBuilder stringBuilder = new StringBuilder();
//                try {
//
//                    fr = new FileReader(myExternalFile);
//
//                    BufferedReader br = new BufferedReader(fr);
//
//                    String line = br.readLine();
//
//                    while(line != null){
//
//                        stringBuilder.append(line).append('\n');
//
//                        line = br.readLine();
//                    }
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//
//                    String fileContents = "File contents\n" + stringBuilder.toString();
//
//                    tvLoad.setText(fileContents);
//                }
//            }
//        });
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
}