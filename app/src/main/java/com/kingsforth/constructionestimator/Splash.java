package com.kingsforth.constructionestimator;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.alfadroid.constructionestimator.R;


public class Splash extends AppCompatActivity {
    ProgressBar pb;
    SQLiteDatabase db_connect;
    Cursor db_cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        db_connect=openOrCreateDatabase("smart_estimator",MODE_PRIVATE, null);
        // Brick sand coarse cement_bag prices db table
        db_connect.execSQL("create table if not exists configuration_first(brick varchar,sand varchar,coarse varchar,cement varchar)");
        // Tiebar
        db_connect.execSQL("create table if not exists configuration_tiebar(one_kg varchar,one_price varchar,two_kg varchar,two_price varchar," +
                "three_kg varchar,three_price varchar," +
                "four_kg varchar,four_price varchar)");
        // Lognitudnal and Main bar
        db_connect.execSQL("create table if not exists configuration_log_main_bar(one_kg varchar,one_price varchar,two_kg varchar,two_price varchar," +
                "three_kg varchar,three_price,four_kg varchar,four_price varchar," +
                "five_kg varchar,five_price varchar,six_kg varchar,six_price varchar ," +
                "seven_kg varchar,seven_price varchar,eight_kg varchar,eight_price varchar)");
        // Confi_setting files
        db_connect.execSQL("create table if not exists confi_check(version varchar)");
        // Block
        db_connect.execSQL("create table if not exists configuration_block(area varchar,price varchar)");
        // Marbel
        db_connect.execSQL("create table if not exists configuration_marbel(length varchar,width varchar,height varchar,price varchar)");
        // Chips
        db_connect.execSQL("create table if not exists configuration_chips(sakra varchar,price varchar)");
        // Excevation
        db_connect.execSQL("create table if not exists configuration_excevation(area varchar,price varchar)");
        // Stone
        db_connect.execSQL("create table if not exists configuration_stone(sakra varchar,price varchar)");
        // save files
        db_connect.execSQL("create table if not exists save(num varchar)");
        // save all_records
        db_connect.execSQL("create table if not exists all_records(cement_quantity varchar, cement_price varchar, sand_quantity varchar, sand_price varchar, crush_quantity varchar, crush_price varchar" +
                ",bar_1_runningfeet varchar ,  bar_1_length varchar ,  bar_1_kg varchar ,  bar_1_ton varchar ,  bar_1_price varchar" +
                ",bar_2_runningfeet varchar ,  bar_2_length varchar ,  bar_2_kg varchar ,  bar_2_ton varchar ,  bar_2_price varchar" +
                ",bar_3_runningfeet varchar ,  bar_3_length varchar ,  bar_3_kg varchar ,  bar_3_ton varchar ,  bar_3_price varchar" +
                ",bar_4_runningfeet varchar ,  bar_4_length varchar ,  bar_4_kg varchar ,  bar_4_ton varchar ,  bar_4_price varchar" +
                ",bar_5_runningfeet varchar ,  bar_5_length varchar ,  bar_5_kg varchar ,  bar_5_ton varchar ,  bar_5_price varchar" +
                ",bar_6_runningfeet varchar ,  bar_6_length varchar ,  bar_6_kg varchar ,  bar_6_ton varchar ,  bar_6_price varchar" +
                ",bar_7_runningfeet varchar ,  bar_7_length varchar ,  bar_7_kg varchar ,  bar_7_ton varchar ,  bar_7_price varchar" +
                ",bar_8_runningfeet varchar ,  bar_8_length varchar ,  bar_8_kg varchar ,  bar_8_ton varchar ,  bar_8_price varchar" +
                ",no_of_blocks_number varchar ,  no_of_blocks_price varchar" +
                ",sakra_of_chips_number varchar ,  sakra_of_chips_price varchar" +
                ",no_of_bricks_number varchar ,  no_of_bricks_price varchar" +
                ",sakra_of_stone_num varchar ,  sakra_of_stone_price varchar" +

                ",excuvation_days varchar ,  excuvation_labour varchar ,  excuvation_price varchar" +

                ",marble_number varchar ,  marble_length varchar ,  marble_width varchar ,  marble_height varchar ,  marble_cost varchar" +

                " )");

        db_connect.delete("all_records",null,null);
        db_cursor = db_connect.rawQuery("select * from configuration_tiebar", null);

        int row_count = db_cursor.getCount();
        if(row_count==0) // means no entry exist so insert else update
        {
            db_connect.execSQL("insert into configuration_tiebar values('" + 0 + "','" + 0 + "','"+ 0.249 +"','"+ 0 +"','"+0.561+"','"+0+"','"+0.996+"','"+0+"'); ");

        }

        db_cursor = db_connect.rawQuery("select * from configuration_log_main_bar", null);
        row_count = db_cursor.getCount();
        if(row_count==0) // means no entry exist so insert else update
        {
            db_connect.execSQL("insert into configuration_log_main_bar values('" + 0 + "','" + 0 + "','"+ 0.249 +"','"+ 0 +"','"+0.561+"','"+0+"','"+0.996+"','"+0+"'" +
                    ",'"+1.556+"','"+0+"','"+2.24+"','"+0+"','"+3.049+"','"+0+"'" +
                    ",'"+3.982+"','"+0+"'); ");

        }

        auto_increment_save();
        pb  = (ProgressBar) findViewById(R.id.progressBar);
        final Thread th1 =new Thread(){

            public void run(){

                try {

                    for(int i=1;i<101;i++) {
                        sleep(5);
                        pb.setProgress(i);


                    }

                    Intent n = new Intent(Splash.this, MainActivity.class);
                    startActivity(n);
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        };
        th1.start();

    }

    public void auto_increment_save(){

        Cursor db_cursor;
        db_connect= openOrCreateDatabase("smart_estimator",MODE_PRIVATE, null);
        db_cursor = db_connect.rawQuery("select * from save", null);
        int row_count = db_cursor.getCount();
        db_cursor.moveToFirst();

        if(row_count==0) // means no entry exist so insert else update
        {

            db_connect.execSQL("insert into save values('" + "1" + "'); ");

        }else {

            int db_n = Integer.parseInt(db_cursor.getString(db_cursor.getColumnIndex("num")))+1;
            String n = ""+db_n ;
            db_connect.delete("save", null, null);
            db_connect.execSQL("insert into save values('" + n + "'); ");

        }
    }

}