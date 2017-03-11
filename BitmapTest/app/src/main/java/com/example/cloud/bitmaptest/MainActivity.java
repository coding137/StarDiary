package com.example.cloud.bitmaptest;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Manifest;

import static android.R.attr.value;

public class MainActivity extends AppCompatActivity {
 EditText editName, editPrice;
    Button btnChoose, btnAdd, btnList;
    ImageView imageView;

    final int REQUEST_CODE_GALLERY = 999 ;

    public static SQLiteHelper sqLiteHelper;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                init();
                sqLiteHelper= new SQLiteHelper(this,"MemoDB.sqlite", null,1);

                sqLiteHelper.queryDate("CREATE TABLE IF NOT EXISTS  MEMO (Tid INTEGER PRIMARY KEY AUTOINCREMENT,name varchar, price varchar,image blog)");

                btnChoose.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        ActivityCompat.requestPermissions(
                                MainActivity.this,
                                new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                                REQUEST_CODE_GALLERY
                                );
                    }
                });

                btnAdd.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {

                        try{
                            sqLiteHelper.insertData(editName.getText().toString().trim(),
                                    editPrice.getText().toString().trim(),
                                    imageViewToByte(imageView));
                            Toast.makeText(getApplicationContext(),"added success",Toast.LENGTH_SHORT).show();
                            editName.setText("");
                            editPrice.setText("");
                            imageView.setImageResource(R.mipmap.ic_launcher);
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),"added fail",Toast.LENGTH_SHORT).show();

                            e.printStackTrace();
                        }


                    }
                });

                btnList.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent =new Intent(MainActivity.this, Itemlist.class);
                        startActivity(intent);
                    }
                });
        }//oncreate end

    private byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream =new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray=stream.toByteArray();
        return byteArray;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUEST_CODE_GALLERY){
            if(grantResults.length>0&& grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Intent intent =new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }else{
                Toast.makeText(getApplicationContext(),"you dont have permission",Toast.LENGTH_SHORT).show();
            }
            return;

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("my pic","mypicss in ");

        if(requestCode== REQUEST_CODE_GALLERY&& resultCode==RESULT_OK &&data!=null){
            Uri uri = data.getData();
            Log.i("my pic","mypicss in ");

            try {
                Log.i("my pic",uri+" ");
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private  void init(){
        editName =(EditText) findViewById(R.id.editText);
        editPrice=(EditText) findViewById(R.id.editText2);
        btnChoose=(Button)findViewById(R.id.btnChoose);

        btnAdd=(Button) findViewById(R.id.btnAdd);
        btnList=(Button) findViewById(R.id.btnList);

        imageView=(ImageView) findViewById(R.id.myimg);


    }
}
