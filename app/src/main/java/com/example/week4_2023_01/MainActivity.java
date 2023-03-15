package com.example.week4_2023_01;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.*;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase db =openOrCreateDatabase("MyDB",MODE_PRIVATE,null);

        db.execSQL("Create table if not exists  MyTable( id Integer Primary Key Autoincrement not null, " +
                "                                        name Varchar not null," +
                "                                        surname  char(50) not null," +
                "                                        nocars int(3) not null)" );

        db.execSQL("Insert into MyTable(name,surname,nocars) values ('Mustafa Cem', 'Kasapabaşı',1)");


        db.close();

        SQLiteDatabase db2 =openOrCreateDatabase("MyDB",MODE_PRIVATE,null);
        String[] arg={"@id"};
        Cursor c= db2.rawQuery("Select * From MyTable ",null);
            if(c!=null){
                c.moveToFirst();
               do{
                  String str= c.getString(1);
                  str+=" "+c.getString(2);
                  str+=" "+c.getInt(3);


                Log.d("DB",str);

               }while(c.moveToNext());



            }
       db2.close();
    }
}