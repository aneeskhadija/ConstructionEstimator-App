package com.kingsforth.constructionestimator.Super_Structure;

import android.app.Dialog;
import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.alfadroid.constructionestimator.R;
import com.kingsforth.constructionestimator.Select_Structure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

public class Chips_result extends Fragment {


    TextView tv_cement_ratio
            ,tv_sand_ratio


            ,tv_cement_quantity
            ,tv_cement_price

            ,tv_sand_quantity
            ,tv_sand_price


            , tv_no_of_sakra
            , tv_price_of_sakra;



    String sand_ratio
            ,cement_ratio


            ,cement_quantity
            ,cement_price

            ,sand_quantity
            ,sand_price


            , no_of_sakra
            , price_of_sakra;

    Button btn_save,btn_back;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.chips_result, container, false);










        tv_cement_ratio         = (TextView) rootView.findViewById(R.id.ratio_a);
        tv_sand_ratio           = (TextView) rootView.findViewById(R.id.ratio_b);

        tv_cement_quantity      = (TextView) rootView.findViewById(R.id.cement_bag_quantity);
        tv_cement_price         = (TextView) rootView.findViewById(R.id.cement_bag_price);

        tv_sand_quantity        = (TextView) rootView.findViewById(R.id.sand_quantity);
        tv_sand_price           = (TextView) rootView.findViewById(R.id.sand_price);


        tv_no_of_sakra         = (TextView) rootView.findViewById(R.id.sakra_number);
        tv_price_of_sakra      = (TextView) rootView.findViewById(R.id.sakra_price);



        btn_save                = (Button) rootView.findViewById(R.id.save);
        btn_back                = (Button) rootView.findViewById(R.id.back);
















        // Getting values through Intent

        sand_ratio  		= getArguments().getString("sand_ratio");
        cement_ratio  		= getArguments().getString("cement_ratio");

        cement_quantity  	= getArguments().getString("cement_quantity");
        cement_price  		= getArguments().getString("cement_price");

        sand_quantity  		= getArguments().getString("sand_quantity");
        sand_price  		= getArguments().getString("sand_price");


        no_of_sakra        = getArguments().getString("no_of_sakra");
        price_of_sakra     = getArguments().getString("sakra_price");







        DecimalFormat df = new DecimalFormat("#.####");




        tv_cement_ratio.setText(df.format(Double.parseDouble(cement_ratio)));
        tv_sand_ratio.setText(df.format(Double.parseDouble(sand_ratio)));


        tv_cement_quantity.setText(df.format(Double.parseDouble(cement_quantity)));
        tv_cement_price.setText(df.format(Double.parseDouble(cement_price)));

        tv_sand_quantity.setText(df.format(Double.parseDouble(sand_quantity)));
        tv_sand_price.setText(df.format(Double.parseDouble(sand_price)));


        tv_no_of_sakra.setText(df.format(Double.parseDouble(no_of_sakra)));
        tv_price_of_sakra.setText(df.format(Double.parseDouble(price_of_sakra)));




        fetch_all_record_from_Db();





        db_cement_quantity      = df.format( Double.parseDouble(db_cement_quantity) + Double.parseDouble(cement_quantity));
        db_cement_price         = df.format( Double.parseDouble(db_cement_price) + Double.parseDouble(cement_price));
        db_sand_quantity        = df.format( Double.parseDouble(db_sand_quantity) + Double.parseDouble(sand_quantity));
        db_sand_price           = df.format( Double.parseDouble(db_sand_price) + Double.parseDouble(sand_price));

        db_sakra_of_chips_number = df.format( Double.parseDouble(db_sakra_of_chips_number) + Double.parseDouble(no_of_sakra));
        db_sakra_of_chips_price  = df.format( Double.parseDouble(db_sakra_of_chips_price) + Double.parseDouble(price_of_sakra));


        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());




        fetch_db_main_save_name();

        fetch_data_from_main_save_file();




        String line_space = "\r\n";




        save_body   = currentDateTimeString +  line_space +line_space;


        Save_body_making();






        save_txt_file_all(main_file_name, save_body);


        inset_all_record_in_Db();


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog_save();
            }
        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new Select_Structure())
                        .commit();
            }
        });







        return rootView;
	}




    public void dialog_save(){

        final Dialog dialog = new Dialog(getActivity());


        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.txt_dialog);


        //***********************     Bridging to alert layout         **************************************


        Button btn_ok           = (Button) dialog.findViewById(R.id.ok);
        Button btn_cancel       = (Button) dialog.findViewById(R.id.cancel);
        final TextView ed_name        = (TextView) dialog.findViewById(R.id.file_name);

        //***********************   End   Bridging to alert layout         **************************************








        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ed_name.getText().toString().trim().equals("")){

                    ed_name.findFocus();
                    Toast.makeText(getActivity(), "Enter file name to save", Toast.LENGTH_SHORT).show();

                }else {

                    dialog.dismiss();
                    String file_name = ed_name.getText().toString().trim();
                    Save_indivisual_body_making();

                    save_txt_file_indivisual(file_name, indivisual_save_body);
                }



            }

        });


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });


        dialog.show();
    }




    String save_body;


    SQLiteDatabase db_connect;
    Cursor db_cursor;

    String main_file_name;

    StringBuilder sd_card_file_text;






    String indivisual_save_body;







    public void Save_indivisual_body_making(){

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());


        indivisual_save_body = currentDateTimeString +line_space
                +"Slab Brick Result" +line_space+line_space;

        indivisual_save_body = indivisual_save_body + "Cement bag              "+"Quantity : "+df.format(Double.parseDouble(cement_quantity)) + "     Price : " + df.format(Double.parseDouble(cement_price)) +line_space;


        indivisual_save_body = indivisual_save_body + "Fine agregade (Sand)    "+"Quantity : "+df.format(Double.parseDouble(sand_quantity)) + "     Price : " + df.format(Double.parseDouble(sand_price)) +line_space;



        indivisual_save_body = indivisual_save_body +line_space+line_space

                + "Sakra of chips          "+df.format(Double.parseDouble(no_of_sakra)) + "     Price : "+df.format(Double.parseDouble(price_of_sakra));



        indivisual_save_body   = indivisual_save_body + line_space+line_space
                + "********************************************************************************************************"+line_space;




    }






    String db_cement_quantity ,db_cement_price ,db_sand_quantity ,db_sand_price ,db_crush_quantity ,db_crush_price

            ,db_bar_1_runningfeet ,db_bar_1_length ,db_bar_1_kg ,db_bar_1_ton ,db_bar_1_price
            ,db_bar_2_runningfeet ,db_bar_2_length ,db_bar_2_kg ,db_bar_2_ton ,db_bar_2_price
            ,db_bar_3_runningfeet ,db_bar_3_length ,db_bar_3_kg ,db_bar_3_ton ,db_bar_3_price
            ,db_bar_4_runningfeet ,db_bar_4_length ,db_bar_4_kg ,db_bar_4_ton ,db_bar_4_price
            ,db_bar_5_runningfeet ,db_bar_5_length ,db_bar_5_kg ,db_bar_5_ton ,db_bar_5_price
            ,db_bar_6_runningfeet ,db_bar_6_length ,db_bar_6_kg ,db_bar_6_ton ,db_bar_6_price
            ,db_bar_7_runningfeet ,db_bar_7_length ,db_bar_7_kg ,db_bar_7_ton ,db_bar_7_price
            ,db_bar_8_runningfeet ,db_bar_8_length ,db_bar_8_kg ,db_bar_8_ton ,db_bar_8_price


            ,db_no_of_blocks_number ,db_no_of_blocks_price
            ,db_sakra_of_chips_number ,db_sakra_of_chips_price
            ,db_no_of_bricks_number ,db_no_of_bricks_price
            ,db_sakra_of_stone_num ,db_sakra_of_stone_price

            ,db_excuvation_days ,db_excuvation_labour ,db_excuvation_price

            ,db_marble_number ,db_marble_length ,db_marble_width ,db_marble_height ,db_marble_cost
            ;


    String line_space = "\r\n";
    DecimalFormat df = new DecimalFormat("#.####");

    public void Save_body_making(){



        if(db_cement_quantity != null && !db_cement_quantity.isEmpty() && !db_cement_quantity.equals("0")){

            save_body = save_body + "Cement bag              "+"Quantity : "+df.format(Double.parseDouble(db_cement_quantity)) + "     Price : " + df.format(Double.parseDouble(db_cement_price)) +line_space;

            Toast.makeText(getActivity(), "db_cement_quantity " , Toast.LENGTH_SHORT).show();
        }

        if(db_sand_quantity != null && !db_sand_quantity.isEmpty() && !db_sand_quantity.equals("0")){

            save_body = save_body + "Fine agregade (Sand)    "+"Quantity : "+df.format(Double.parseDouble(db_sand_quantity)) + "     Price : " + df.format(Double.parseDouble(db_sand_price)) +line_space;
            Toast.makeText(getActivity(), "db_sand_quantity " , Toast.LENGTH_SHORT).show();
        }

        if(db_crush_quantity != null && !db_crush_quantity.isEmpty() && !db_crush_quantity.equals("0")) {

            save_body = save_body + "Coarse agregade (Crush) "+"Quantity : "+df.format(Double.parseDouble(db_crush_quantity)) + "     Price : " + df.format(Double.parseDouble(db_crush_price)) +line_space
                    +line_space;
            Toast.makeText(getActivity(), "db_crush_quantity " , Toast.LENGTH_SHORT).show();
        }




        if(db_no_of_bricks_number != null && !db_no_of_bricks_number.isEmpty() && !db_no_of_bricks_number.equals("0")) {

            save_body = save_body + "No. of brick            "+df.format(Double.parseDouble(db_no_of_bricks_number)) + "     Price : "+df.format(Double.parseDouble(db_no_of_bricks_price));
            Toast.makeText(getActivity(), "db_no_of_bricks_number " , Toast.LENGTH_SHORT).show();
        }



        if(db_no_of_blocks_number != null && !db_no_of_blocks_number.isEmpty() && !db_no_of_blocks_number.equals("0")) {

            save_body = save_body +line_space+line_space

                    + "No. of blocks           "+df.format(Double.parseDouble(db_no_of_blocks_number)) + "     Price : "+df.format(Double.parseDouble(db_no_of_blocks_price));
            Toast.makeText(getActivity(), "db_no_of_blocks_number " , Toast.LENGTH_SHORT).show();
        }



        if(db_sakra_of_chips_number != null && !db_sakra_of_chips_number.isEmpty() && !db_sakra_of_chips_number.equals("0")) {

            save_body = save_body +line_space+line_space

                    + "Sakra of chips          "+df.format(Double.parseDouble(db_sakra_of_chips_number)) + "     Price : "+df.format(Double.parseDouble(db_sakra_of_chips_price));
            Toast.makeText(getActivity(), "db_sakra_of_chips_number " , Toast.LENGTH_SHORT).show();
        }


        if(db_sakra_of_stone_num != null && !db_sakra_of_stone_num.isEmpty() && !db_sakra_of_stone_num.equals("0")) {

            save_body = save_body +line_space+line_space

                    + "Sakra of stones         "+df.format(Double.parseDouble(db_sakra_of_stone_num)) + "     Price : "+df.format(Double.parseDouble(db_sakra_of_stone_price));
            Toast.makeText(getActivity(), "db_sakra_of_stone_num " , Toast.LENGTH_SHORT).show();

        }




        if((db_excuvation_days != null && !db_excuvation_days.isEmpty() && !db_excuvation_days.equals("0")) ||
                (db_excuvation_labour != null && !db_excuvation_labour.isEmpty() && !db_excuvation_labour.equals("0"))
                ) {

            save_body = save_body +line_space+line_space

                    + "Excavation "+line_space
                    + "         Days :           "+db_excuvation_days +line_space
                    + "         Labour :         "+db_excuvation_labour      +line_space
                    + "         Price :          "+db_excuvation_price      +line_space;

        }



        if(db_marble_number != null && !db_marble_number.isEmpty() && !db_marble_number.equals("0")) {

            save_body = save_body +line_space+line_space

                    + "marble "+line_space
                    + "         Num of marble :      "+db_marble_number +line_space
                    + "         Length        :      "+db_marble_length      +line_space
                    + "         Width         :      "+db_marble_width      +line_space
                    + "         Height        :      "+db_marble_height      +line_space
                    + "         Cost          :      "+db_marble_cost      +line_space;

        }





        save_body   = save_body +line_space+line_space

                + "Tiebar / Mainbar / Logbar "+line_space+line_space;




        if(db_bar_1_runningfeet != null && !db_bar_1_runningfeet.isEmpty() && !db_bar_1_runningfeet.equals("0")) {

            save_body = save_body + "Type  # 1 "+line_space
                    + "         Running feet :          "+db_bar_1_runningfeet +line_space
                    + "         Length       :          "+db_bar_1_length      +line_space
                    + "         Kg's         :          "+db_bar_1_kg      +line_space
                    + "         Ton          :          "+db_bar_1_ton      +line_space
                    + "         price        :          "+db_bar_1_price      +line_space+line_space;

        }



        if(db_bar_2_runningfeet != null && !db_bar_2_runningfeet.isEmpty() && !db_bar_2_runningfeet.equals("0")) {

            save_body = save_body + "Type  # 2 "+line_space
                    + "         Running feet :          "+db_bar_2_runningfeet +line_space
                    + "         Length       :          "+db_bar_2_length      +line_space
                    + "         Kg's         :          "+db_bar_2_kg      +line_space
                    + "         Ton          :          "+db_bar_2_ton      +line_space
                    + "         price        :          "+db_bar_2_price      +line_space+line_space;

        }


        if(db_bar_3_runningfeet != null && !db_bar_3_runningfeet.isEmpty() && !db_bar_3_runningfeet.equals("0")) {

            save_body = save_body + "Type  # 3 "+line_space
                    + "         Running feet :          "+db_bar_3_runningfeet +line_space
                    + "         Length       :          "+db_bar_3_length      +line_space
                    + "         Kg's         :          "+db_bar_3_kg      +line_space
                    + "         Ton          :          "+db_bar_3_ton      +line_space
                    + "         price        :          "+db_bar_3_price      +line_space+line_space;

        }


        if(db_bar_4_runningfeet != null && !db_bar_4_runningfeet.isEmpty() && !db_bar_4_runningfeet.equals("0")) {

            save_body = save_body + "Type  # 4 "+line_space
                    + "         Running feet :          "+db_bar_4_runningfeet +line_space
                    + "         Length       :          "+db_bar_4_length      +line_space
                    + "         Kg's         :          "+db_bar_4_kg      +line_space
                    + "         Ton          :          "+db_bar_4_ton      +line_space
                    + "         price        :          "+db_bar_4_price      +line_space+line_space;

        }


        if(db_bar_5_runningfeet != null && !db_bar_5_runningfeet.isEmpty() && !db_bar_5_runningfeet.equals("0")) {

            save_body = save_body + "Type  # 5 "+line_space
                    + "         Running feet :          "+db_bar_5_runningfeet +line_space
                    + "         Length       :          "+db_bar_5_length      +line_space
                    + "         Kg's         :          "+db_bar_5_kg      +line_space
                    + "         Ton          :          "+db_bar_5_ton      +line_space
                    + "         price        :          "+db_bar_5_price      +line_space+line_space;

        }


        if(db_bar_6_runningfeet != null && !db_bar_6_runningfeet.isEmpty() && !db_bar_6_runningfeet.equals("0")) {

            save_body = save_body + "Type  # 6 "+line_space
                    + "         Running feet :          "+db_bar_6_runningfeet +line_space
                    + "         Length       :          "+db_bar_6_length      +line_space
                    + "         Kg's         :          "+db_bar_6_kg      +line_space
                    + "         Ton          :          "+db_bar_6_ton      +line_space
                    + "         price        :          "+db_bar_6_price      +line_space+line_space;


        }


        if(db_bar_7_runningfeet != null && !db_bar_7_runningfeet.isEmpty() && !db_bar_7_runningfeet.equals("0")) {

            save_body = save_body + "Type  # 7 "+line_space
                    + "         Running feet :          "+db_bar_7_runningfeet +line_space
                    + "         Length       :          "+db_bar_7_length      +line_space
                    + "         Kg's         :          "+db_bar_7_kg      +line_space
                    + "         Ton          :          "+db_bar_7_ton      +line_space
                    + "         price        :          "+db_bar_7_price      +line_space+line_space;

        }


        if(db_bar_8_runningfeet != null && !db_bar_8_runningfeet.isEmpty() && !db_bar_8_runningfeet.equals("0")) {

            save_body = save_body + "Type  # 8 "+line_space
                    + "         Running feet :          "+db_bar_8_runningfeet +line_space
                    + "         Length       :          "+db_bar_8_length      +line_space
                    + "         Kg's         :          "+db_bar_8_kg      +line_space
                    + "         Ton          :          "+db_bar_8_ton      +line_space
                    + "         price        :          "+db_bar_8_price      +line_space+line_space;
        }













        save_body   = save_body + line_space+line_space
                + "********************************************************************************************************"+line_space;




    }



//
//
//    public void add_tiebar_to_bars(){
//
//        DecimalFormat df = new DecimalFormat("#.####");
//
//        switch (tiebar_type.charAt(0)){
//
//            case '1':
//                db_bar_1_runningfeet        =    df.format(Double.parseDouble(db_bar_1_runningfeet) + Double.parseDouble(tie_running_feet));
//                db_bar_1_length             =    df.format(Double.parseDouble(db_bar_1_length) + Double.parseDouble(tiebar_length));
//                db_bar_1_kg                 =    df.format(Double.parseDouble(db_bar_1_kg) + Double.parseDouble(tiebar_kg));
//                db_bar_1_ton                =    df.format(Double.parseDouble(db_bar_1_ton) + Double.parseDouble(tiebar_tun));
//                db_bar_1_price              =    df.format(Double.parseDouble(db_bar_1_price) + Double.parseDouble(tiebar_price));
//
//                break;
//
//            case '2':
//                db_bar_2_runningfeet        =    df.format( Double.parseDouble(db_bar_2_runningfeet) + Double.parseDouble(tie_running_feet));
//                db_bar_2_length             =    df.format( Double.parseDouble(db_bar_2_length) + Double.parseDouble(tiebar_length));
//                db_bar_2_kg                 =    df.format( Double.parseDouble(db_bar_2_kg) + Double.parseDouble(tiebar_kg));
//                db_bar_2_ton                =    df.format( Double.parseDouble(db_bar_2_ton) + Double.parseDouble(tiebar_tun));
//                db_bar_2_price              =    df.format( Double.parseDouble(db_bar_2_price) + Double.parseDouble(tiebar_price));
//
//                break;
//
//
//
//            case '3':
//                db_bar_3_runningfeet        =    df.format( Double.parseDouble(db_bar_3_runningfeet) + Double.parseDouble(tie_running_feet));
//                db_bar_3_length             =    df.format( Double.parseDouble(db_bar_3_length) + Double.parseDouble(tiebar_length));
//                db_bar_3_kg                 =    df.format( Double.parseDouble(db_bar_3_kg) + Double.parseDouble(tiebar_kg));
//                db_bar_3_ton                =    df.format( Double.parseDouble(db_bar_3_ton) + Double.parseDouble(tiebar_tun));
//                db_bar_3_price              =    df.format( Double.parseDouble(db_bar_3_price) + Double.parseDouble(tiebar_price));
//
//                break;
//
//
//
//            case '4':
//                db_bar_4_runningfeet        =    df.format( Double.parseDouble(db_bar_4_runningfeet) + Double.parseDouble(tie_running_feet));
//                db_bar_4_length             =    df.format( Double.parseDouble(db_bar_4_length) + Double.parseDouble(tiebar_length));
//                db_bar_4_kg                 =    df.format( Double.parseDouble(db_bar_4_kg) + Double.parseDouble(tiebar_kg));
//                db_bar_4_ton                =    df.format( Double.parseDouble(db_bar_4_ton) + Double.parseDouble(tiebar_tun));
//                db_bar_4_price              =    df.format( Double.parseDouble(db_bar_4_price) + Double.parseDouble(tiebar_price));
//
//                break;
//
//
//
//            case '5':
//                db_bar_5_runningfeet        =    df.format( Double.parseDouble(db_bar_5_runningfeet) + Double.parseDouble(tie_running_feet));
//                db_bar_5_length             =    df.format( Double.parseDouble(db_bar_5_length) + Double.parseDouble(tiebar_length));
//                db_bar_5_kg                 =    df.format( Double.parseDouble(db_bar_5_kg) + Double.parseDouble(tiebar_kg));
//                db_bar_5_ton                =    df.format( Double.parseDouble(db_bar_5_ton) + Double.parseDouble(tiebar_tun));
//                db_bar_5_price              =    df.format( Double.parseDouble(db_bar_5_price) + Double.parseDouble(tiebar_price));
//
//                break;
//
//
//            case '6':
//                db_bar_6_runningfeet        =    df.format( Double.parseDouble(db_bar_6_runningfeet) + Double.parseDouble(tie_running_feet));
//                db_bar_6_length             =    df.format( Double.parseDouble(db_bar_6_length) + Double.parseDouble(tiebar_length));
//                db_bar_6_kg                 =    df.format( Double.parseDouble(db_bar_6_kg) + Double.parseDouble(tiebar_kg));
//                db_bar_6_ton                =    df.format( Double.parseDouble(db_bar_6_ton) + Double.parseDouble(tiebar_tun));
//                db_bar_6_price              =    df.format( Double.parseDouble(db_bar_6_price) + Double.parseDouble(tiebar_price));
//
//                break;
//
//
//            case '7':
//                db_bar_7_runningfeet        =    df.format( Double.parseDouble(db_bar_7_runningfeet) + Double.parseDouble(tie_running_feet));
//                db_bar_7_length             =    df.format( Double.parseDouble(db_bar_7_length) + Double.parseDouble(tiebar_length));
//                db_bar_7_kg                 =    df.format( Double.parseDouble(db_bar_7_kg) + Double.parseDouble(tiebar_kg));
//                db_bar_7_ton                =    df.format( Double.parseDouble(db_bar_7_ton) + Double.parseDouble(tiebar_tun));
//                db_bar_7_price              =    df.format( Double.parseDouble(db_bar_7_price) + Double.parseDouble(tiebar_price));
//
//                break;
//
//
//            case '8':
//                db_bar_8_runningfeet        =    df.format( Double.parseDouble(db_bar_8_runningfeet) + Double.parseDouble(tie_running_feet));
//                db_bar_8_length             =    df.format( Double.parseDouble(db_bar_8_length) + Double.parseDouble(tiebar_length));
//                db_bar_8_kg                 =    df.format( Double.parseDouble(db_bar_8_kg) + Double.parseDouble(tiebar_kg));
//                db_bar_8_ton                =    df.format( Double.parseDouble(db_bar_8_ton) + Double.parseDouble(tiebar_tun));
//                db_bar_8_price              =    df.format( Double.parseDouble(db_bar_8_price) + Double.parseDouble(tiebar_price));
//
//                break;
//        }
//    }
//
//
//
//
//
//
//
//
//
//    public void add_logbar_to_bars(){
//
//
//
//        switch (logbar_type.charAt(0)){
//
//            case '1':
//                db_bar_1_runningfeet        =    df.format(Double.parseDouble(db_bar_1_runningfeet) + Double.parseDouble(log_running_feet));
//                db_bar_1_length             =    df.format(Double.parseDouble(db_bar_1_length) + Double.parseDouble(logbar_length));
//                db_bar_1_kg                 =    df.format(Double.parseDouble(db_bar_1_kg) + Double.parseDouble(logbar_kg));
//                db_bar_1_ton                =    df.format(Double.parseDouble(db_bar_1_ton) + Double.parseDouble(logbar_tun));
//                db_bar_1_price              =    df.format(Double.parseDouble(db_bar_1_price) + Double.parseDouble(logbar_price));
//
//                break;
//
//            case '2':
//                db_bar_2_runningfeet        =    df.format( Double.parseDouble(db_bar_2_runningfeet) + Double.parseDouble(log_running_feet));
//                db_bar_2_length             =    df.format( Double.parseDouble(db_bar_2_length) + Double.parseDouble(logbar_length));
//                db_bar_2_kg                 =    df.format( Double.parseDouble(db_bar_2_kg) + Double.parseDouble(logbar_kg));
//                db_bar_2_ton                =    df.format( Double.parseDouble(db_bar_2_ton) + Double.parseDouble(logbar_tun));
//                db_bar_2_price              =    df.format( Double.parseDouble(db_bar_2_price) + Double.parseDouble(logbar_price));
//
//                break;
//
//
//
//            case '3':
//                db_bar_3_runningfeet        =    df.format( Double.parseDouble(db_bar_3_runningfeet) + Double.parseDouble(log_running_feet));
//                db_bar_3_length             =    df.format( Double.parseDouble(db_bar_3_length) + Double.parseDouble(logbar_length));
//                db_bar_3_kg                 =    df.format( Double.parseDouble(db_bar_3_kg) + Double.parseDouble(logbar_kg));
//                db_bar_3_ton                =    df.format( Double.parseDouble(db_bar_3_ton) + Double.parseDouble(logbar_tun));
//                db_bar_3_price              =    df.format( Double.parseDouble(db_bar_3_price) + Double.parseDouble(logbar_price));
//
//                break;
//
//
//
//            case '4':
//                db_bar_4_runningfeet        =    df.format( Double.parseDouble(db_bar_4_runningfeet) + Double.parseDouble(log_running_feet));
//                db_bar_4_length             =    df.format( Double.parseDouble(db_bar_4_length) + Double.parseDouble(logbar_length));
//                db_bar_4_kg                 =    df.format( Double.parseDouble(db_bar_4_kg) + Double.parseDouble(logbar_kg));
//                db_bar_4_ton                =    df.format( Double.parseDouble(db_bar_4_ton) + Double.parseDouble(logbar_tun));
//                db_bar_4_price              =    df.format( Double.parseDouble(db_bar_4_price) + Double.parseDouble(logbar_price));
//
//                break;
//
//
//
//            case '5':
//                db_bar_5_runningfeet        =    df.format( Double.parseDouble(db_bar_5_runningfeet) + Double.parseDouble(log_running_feet));
//                db_bar_5_length             =    df.format( Double.parseDouble(db_bar_5_length) + Double.parseDouble(logbar_length));
//                db_bar_5_kg                 =    df.format( Double.parseDouble(db_bar_5_kg) + Double.parseDouble(logbar_kg));
//                db_bar_5_ton                =    df.format( Double.parseDouble(db_bar_5_ton) + Double.parseDouble(logbar_tun));
//                db_bar_5_price              =    df.format( Double.parseDouble(db_bar_5_price) + Double.parseDouble(logbar_price));
//
//                break;
//
//
//            case '6':
//                db_bar_6_runningfeet        =    df.format( Double.parseDouble(db_bar_6_runningfeet) + Double.parseDouble(log_running_feet));
//                db_bar_6_length             =    df.format( Double.parseDouble(db_bar_6_length) + Double.parseDouble(logbar_length));
//                db_bar_6_kg                 =    df.format( Double.parseDouble(db_bar_6_kg) + Double.parseDouble(logbar_kg));
//                db_bar_6_ton                =    df.format( Double.parseDouble(db_bar_6_ton) + Double.parseDouble(logbar_tun));
//                db_bar_6_price              =    df.format( Double.parseDouble(db_bar_6_price) + Double.parseDouble(logbar_price));
//
//                break;
//
//
//            case '7':
//                db_bar_7_runningfeet        =    df.format( Double.parseDouble(db_bar_7_runningfeet) + Double.parseDouble(log_running_feet));
//                db_bar_7_length             =    df.format( Double.parseDouble(db_bar_7_length) + Double.parseDouble(logbar_length));
//                db_bar_7_kg                 =    df.format( Double.parseDouble(db_bar_7_kg) + Double.parseDouble(logbar_kg));
//                db_bar_7_ton                =    df.format( Double.parseDouble(db_bar_7_ton) + Double.parseDouble(logbar_tun));
//                db_bar_7_price              =    df.format( Double.parseDouble(db_bar_7_price) + Double.parseDouble(logbar_price));
//
//                break;
//
//
//            case '8':
//                db_bar_8_runningfeet        =    df.format( Double.parseDouble(db_bar_8_runningfeet) + Double.parseDouble(log_running_feet));
//                db_bar_8_length             =    df.format( Double.parseDouble(db_bar_8_length) + Double.parseDouble(logbar_length));
//                db_bar_8_kg                 =    df.format( Double.parseDouble(db_bar_8_kg) + Double.parseDouble(logbar_kg));
//                db_bar_8_ton                =    df.format( Double.parseDouble(db_bar_8_ton) + Double.parseDouble(logbar_tun));
//                db_bar_8_price              =    df.format( Double.parseDouble(db_bar_8_price) + Double.parseDouble(logbar_price));
//
//                break;
//        }
//    }
//
//
//    public void add_mainbar_to_bars(){
//
//        DecimalFormat df = new DecimalFormat("#.####");
//
//        switch (mainbar_type.charAt(0)){
//
//            case '1':
//                db_bar_1_runningfeet        =    df.format(Double.parseDouble(db_bar_1_runningfeet) + Double.parseDouble(main_running_feet));
//                db_bar_1_length             =    df.format(Double.parseDouble(db_bar_1_length) + Double.parseDouble(mainbar_length));
//                db_bar_1_kg                 =    df.format(Double.parseDouble(db_bar_1_kg) + Double.parseDouble(mainbar_kg));
//                db_bar_1_ton                =    df.format(Double.parseDouble(db_bar_1_ton) + Double.parseDouble(mainbar_tun));
//                db_bar_1_price              =    df.format(Double.parseDouble(db_bar_1_price) + Double.parseDouble(mainbar_price));
//
//                break;
//
//            case '2':
//                db_bar_2_runningfeet        =    df.format( Double.parseDouble(db_bar_2_runningfeet) + Double.parseDouble(main_running_feet));
//                db_bar_2_length             =    df.format( Double.parseDouble(db_bar_2_length) + Double.parseDouble(mainbar_length));
//                db_bar_2_kg                 =    df.format( Double.parseDouble(db_bar_2_kg) + Double.parseDouble(mainbar_kg));
//                db_bar_2_ton                =    df.format( Double.parseDouble(db_bar_2_ton) + Double.parseDouble(mainbar_tun));
//                db_bar_2_price              =    df.format( Double.parseDouble(db_bar_2_price) + Double.parseDouble(mainbar_price));
//
//                break;
//
//
//
//            case '3':
//                db_bar_3_runningfeet        =    df.format( Double.parseDouble(db_bar_3_runningfeet) + Double.parseDouble(main_running_feet));
//                db_bar_3_length             =    df.format( Double.parseDouble(db_bar_3_length) + Double.parseDouble(mainbar_length));
//                db_bar_3_kg                 =    df.format( Double.parseDouble(db_bar_3_kg) + Double.parseDouble(mainbar_kg));
//                db_bar_3_ton                =    df.format( Double.parseDouble(db_bar_3_ton) + Double.parseDouble(mainbar_tun));
//                db_bar_3_price              =    df.format( Double.parseDouble(db_bar_3_price) + Double.parseDouble(mainbar_price));
//
//                break;
//
//
//
//            case '4':
//                db_bar_4_runningfeet        =    df.format( Double.parseDouble(db_bar_4_runningfeet) + Double.parseDouble(main_running_feet));
//                db_bar_4_length             =    df.format( Double.parseDouble(db_bar_4_length) + Double.parseDouble(mainbar_length));
//                db_bar_4_kg                 =    df.format( Double.parseDouble(db_bar_4_kg) + Double.parseDouble(mainbar_kg));
//                db_bar_4_ton                =    df.format( Double.parseDouble(db_bar_4_ton) + Double.parseDouble(mainbar_tun));
//                db_bar_4_price              =    df.format( Double.parseDouble(db_bar_4_price) + Double.parseDouble(mainbar_price));
//
//                break;
//
//
//
//            case '5':
//                db_bar_5_runningfeet        =    df.format( Double.parseDouble(db_bar_5_runningfeet) + Double.parseDouble(main_running_feet));
//                db_bar_5_length             =    df.format( Double.parseDouble(db_bar_5_length) + Double.parseDouble(mainbar_length));
//                db_bar_5_kg                 =    df.format( Double.parseDouble(db_bar_5_kg) + Double.parseDouble(mainbar_kg));
//                db_bar_5_ton                =    df.format( Double.parseDouble(db_bar_5_ton) + Double.parseDouble(mainbar_tun));
//                db_bar_5_price              =    df.format( Double.parseDouble(db_bar_5_price) + Double.parseDouble(mainbar_price));
//
//                break;
//
//
//            case '6':
//                db_bar_6_runningfeet        =    df.format( Double.parseDouble(db_bar_6_runningfeet) + Double.parseDouble(main_running_feet));
//                db_bar_6_length             =    df.format( Double.parseDouble(db_bar_6_length) + Double.parseDouble(mainbar_length));
//                db_bar_6_kg                 =    df.format( Double.parseDouble(db_bar_6_kg) + Double.parseDouble(mainbar_kg));
//                db_bar_6_ton                =    df.format( Double.parseDouble(db_bar_6_ton) + Double.parseDouble(mainbar_tun));
//                db_bar_6_price              =    df.format( Double.parseDouble(db_bar_6_price) + Double.parseDouble(mainbar_price));
//
//                break;
//
//
//            case '7':
//                db_bar_7_runningfeet        =    df.format( Double.parseDouble(db_bar_7_runningfeet) + Double.parseDouble(main_running_feet));
//                db_bar_7_length             =    df.format( Double.parseDouble(db_bar_7_length) + Double.parseDouble(mainbar_length));
//                db_bar_7_kg                 =    df.format( Double.parseDouble(db_bar_7_kg) + Double.parseDouble(mainbar_kg));
//                db_bar_7_ton                =    df.format( Double.parseDouble(db_bar_7_ton) + Double.parseDouble(mainbar_tun));
//                db_bar_7_price              =    df.format( Double.parseDouble(db_bar_7_price) + Double.parseDouble(mainbar_price));
//
//                break;
//
//
//            case '8':
//                db_bar_8_runningfeet        =    df.format( Double.parseDouble(db_bar_8_runningfeet) + Double.parseDouble(main_running_feet));
//                db_bar_8_length             =    df.format( Double.parseDouble(db_bar_8_length) + Double.parseDouble(mainbar_length));
//                db_bar_8_kg                 =    df.format( Double.parseDouble(db_bar_8_kg) + Double.parseDouble(mainbar_kg));
//                db_bar_8_ton                =    df.format( Double.parseDouble(db_bar_8_ton) + Double.parseDouble(mainbar_tun));
//                db_bar_8_price              =    df.format( Double.parseDouble(db_bar_8_price) + Double.parseDouble(mainbar_price));
//
//                break;
//        }
//    }
//
//
//





    public void fetch_all_record_from_Db(){

        db_connect= getActivity().openOrCreateDatabase("smart_estimator",getActivity().MODE_PRIVATE, null);

        db_cursor = db_connect.rawQuery("select * from all_records", null);

        int row_count = db_cursor.getCount();

        db_cursor.moveToFirst();



        if(row_count==0) // means no entry exist so insert else update
        {

            db_cement_quantity      = "0";
            db_cement_price         = "0";
            db_sand_quantity        = "0";
            db_sand_price           = "0";
            db_crush_quantity       = "0";
            db_crush_price          = "0";






            db_bar_1_runningfeet    = "0";
            db_bar_1_length         = "0";
            db_bar_1_kg             = "0";
            db_bar_1_ton            = "0";
            db_bar_1_price          = "0";

            db_bar_2_runningfeet    = "0";
            db_bar_2_length         = "0";
            db_bar_2_kg             = "0";
            db_bar_2_ton            = "0";
            db_bar_2_price          = "0";

            db_bar_3_runningfeet    = "0";
            db_bar_3_length         = "0";
            db_bar_3_kg             = "0";
            db_bar_3_ton            = "0";
            db_bar_3_price          = "0";

            db_bar_4_runningfeet    = "0";
            db_bar_4_length         = "0";
            db_bar_4_kg             = "0";
            db_bar_4_ton            = "0";
            db_bar_4_price          = "0";

            db_bar_5_runningfeet    = "0";
            db_bar_5_length         = "0";
            db_bar_5_kg             = "0";
            db_bar_5_ton            = "0";
            db_bar_5_price          = "0";

            db_bar_6_runningfeet    = "0";
            db_bar_6_length         = "0";
            db_bar_6_kg             = "0";
            db_bar_6_ton            = "0";
            db_bar_6_price          = "0";

            db_bar_7_runningfeet    = "0";
            db_bar_7_length         = "0";
            db_bar_7_kg             = "0";
            db_bar_7_ton            = "0";
            db_bar_7_price          = "0";

            db_bar_8_runningfeet    = "0";
            db_bar_8_length         = "0";
            db_bar_8_kg             = "0";
            db_bar_8_ton            = "0";
            db_bar_8_price          = "0";








            db_no_of_blocks_number  = "0";
            db_no_of_blocks_price   = "0";

            db_sakra_of_chips_number = "0";
            db_sakra_of_chips_price  = "0";

            db_no_of_bricks_number   = "0";
            db_no_of_bricks_price    = "0";

            db_sakra_of_stone_num    = "0";
            db_sakra_of_stone_price  = "0";


            db_excuvation_days       = "0";
            db_excuvation_labour     = "0";
            db_excuvation_price      = "0";


            db_marble_number         = "0";
            db_marble_length         = "0";
            db_marble_width          = "0";
            db_marble_height         = "0";
            db_marble_cost           = "0";

        }else {


            db_cement_quantity      = db_cursor.getString(db_cursor.getColumnIndex("cement_quantity"));
            db_cement_price         = db_cursor.getString(db_cursor.getColumnIndex("cement_price"));
            db_sand_quantity        = db_cursor.getString(db_cursor.getColumnIndex("sand_quantity"));
            db_sand_price           = db_cursor.getString(db_cursor.getColumnIndex("sand_price"));
            db_crush_quantity       = db_cursor.getString(db_cursor.getColumnIndex("crush_quantity"));
            db_crush_price          = db_cursor.getString(db_cursor.getColumnIndex("crush_price"));








            db_bar_1_runningfeet        =    db_cursor.getString(db_cursor.getColumnIndex("bar_1_runningfeet"));
            db_bar_1_length             =    db_cursor.getString(db_cursor.getColumnIndex("bar_1_length"));
            db_bar_1_kg                 =    db_cursor.getString(db_cursor.getColumnIndex("bar_1_kg"));
            db_bar_1_ton                =    db_cursor.getString(db_cursor.getColumnIndex("bar_1_ton"));
            db_bar_1_price              =    db_cursor.getString(db_cursor.getColumnIndex("bar_1_price"));

            db_bar_2_runningfeet        =    db_cursor.getString(db_cursor.getColumnIndex("bar_2_runningfeet"));
            db_bar_2_length             =    db_cursor.getString(db_cursor.getColumnIndex("bar_2_length"));
            db_bar_2_kg                 =    db_cursor.getString(db_cursor.getColumnIndex("bar_2_kg"));
            db_bar_2_ton                =    db_cursor.getString(db_cursor.getColumnIndex("bar_2_ton"));
            db_bar_2_price              =    db_cursor.getString(db_cursor.getColumnIndex("bar_2_price"));

            db_bar_3_runningfeet        =    db_cursor.getString(db_cursor.getColumnIndex("bar_3_runningfeet"));
            db_bar_3_length             =    db_cursor.getString(db_cursor.getColumnIndex("bar_3_length"));
            db_bar_3_kg                 =    db_cursor.getString(db_cursor.getColumnIndex("bar_3_kg"));
            db_bar_3_ton                =    db_cursor.getString(db_cursor.getColumnIndex("bar_3_ton"));
            db_bar_3_price              =    db_cursor.getString(db_cursor.getColumnIndex("bar_3_price"));


            db_bar_4_runningfeet        =    db_cursor.getString(db_cursor.getColumnIndex("bar_4_runningfeet"));
            db_bar_4_length             =    db_cursor.getString(db_cursor.getColumnIndex("bar_4_length"));
            db_bar_4_kg                 =    db_cursor.getString(db_cursor.getColumnIndex("bar_4_kg"));
            db_bar_4_ton                =    db_cursor.getString(db_cursor.getColumnIndex("bar_4_ton"));
            db_bar_4_price              =    db_cursor.getString(db_cursor.getColumnIndex("bar_4_price"));


            db_bar_5_runningfeet        =    db_cursor.getString(db_cursor.getColumnIndex("bar_5_runningfeet"));
            db_bar_5_length             =    db_cursor.getString(db_cursor.getColumnIndex("bar_5_length"));
            db_bar_5_kg                 =    db_cursor.getString(db_cursor.getColumnIndex("bar_5_kg"));
            db_bar_5_ton                =    db_cursor.getString(db_cursor.getColumnIndex("bar_5_ton"));
            db_bar_5_price              =    db_cursor.getString(db_cursor.getColumnIndex("bar_5_price"));


            db_bar_6_runningfeet        =    db_cursor.getString(db_cursor.getColumnIndex("bar_6_runningfeet"));
            db_bar_6_length             =    db_cursor.getString(db_cursor.getColumnIndex("bar_6_length"));
            db_bar_6_kg                 =    db_cursor.getString(db_cursor.getColumnIndex("bar_6_kg"));
            db_bar_6_ton                =    db_cursor.getString(db_cursor.getColumnIndex("bar_6_ton"));
            db_bar_6_price              =    db_cursor.getString(db_cursor.getColumnIndex("bar_6_price"));


            db_bar_7_runningfeet        =    db_cursor.getString(db_cursor.getColumnIndex("bar_7_runningfeet"));
            db_bar_7_length             =    db_cursor.getString(db_cursor.getColumnIndex("bar_7_length"));
            db_bar_7_kg                 =    db_cursor.getString(db_cursor.getColumnIndex("bar_7_kg"));
            db_bar_7_ton                =    db_cursor.getString(db_cursor.getColumnIndex("bar_7_ton"));
            db_bar_7_price              =    db_cursor.getString(db_cursor.getColumnIndex("bar_7_price"));


            db_bar_8_runningfeet        =    db_cursor.getString(db_cursor.getColumnIndex("bar_8_runningfeet"));
            db_bar_8_length             =    db_cursor.getString(db_cursor.getColumnIndex("bar_8_length"));
            db_bar_8_kg                 =    db_cursor.getString(db_cursor.getColumnIndex("bar_8_kg"));
            db_bar_8_ton                =    db_cursor.getString(db_cursor.getColumnIndex("bar_8_ton"));
            db_bar_8_price              =    db_cursor.getString(db_cursor.getColumnIndex("bar_8_price"));





            db_no_of_blocks_number  = db_cursor.getString(db_cursor.getColumnIndex("no_of_blocks_number"));
            db_no_of_blocks_price   = db_cursor.getString(db_cursor.getColumnIndex("no_of_blocks_price"));

            db_sakra_of_chips_number = db_cursor.getString(db_cursor.getColumnIndex("sakra_of_chips_number"));
            db_sakra_of_chips_price  = db_cursor.getString(db_cursor.getColumnIndex("sakra_of_chips_price"));

            db_no_of_bricks_number   = db_cursor.getString(db_cursor.getColumnIndex("no_of_bricks_number"));
            db_no_of_bricks_price    = db_cursor.getString(db_cursor.getColumnIndex("no_of_bricks_price"));

            db_sakra_of_stone_num    = db_cursor.getString(db_cursor.getColumnIndex("sakra_of_stone_num"));
            db_sakra_of_stone_price  = db_cursor.getString(db_cursor.getColumnIndex("sakra_of_stone_price"));


            db_excuvation_days       = db_cursor.getString(db_cursor.getColumnIndex("excuvation_days"));
            db_excuvation_labour     = db_cursor.getString(db_cursor.getColumnIndex("excuvation_labour"));
            db_excuvation_price      = db_cursor.getString(db_cursor.getColumnIndex("excuvation_price"));


            db_marble_number         = db_cursor.getString(db_cursor.getColumnIndex("marble_number"));
            db_marble_length         = db_cursor.getString(db_cursor.getColumnIndex("marble_length"));
            db_marble_width          = db_cursor.getString(db_cursor.getColumnIndex("marble_width"));
            db_marble_height         = db_cursor.getString(db_cursor.getColumnIndex("marble_height"));
            db_marble_cost           = db_cursor.getString(db_cursor.getColumnIndex("marble_cost"));

        }


    }











    public void inset_all_record_in_Db(){

        db_connect= getActivity().openOrCreateDatabase("smart_estimator",getActivity().MODE_PRIVATE, null);

        db_cursor = db_connect.rawQuery("select * from configuration_log_main_bar", null);

        int row_count = db_cursor.getCount();


        if(row_count==0) // means no entry exist so insert else update
        {
            db_connect.execSQL("insert into all_records values('" + db_cement_quantity + "','" + db_cement_price + "','"+ db_sand_quantity +"','"+ db_sand_price +"','"+db_crush_quantity+"','"+db_crush_price+"'" +

                    ",'"+db_bar_1_runningfeet+"','"+db_bar_1_length+"','"+db_bar_1_kg+"','"+db_bar_1_ton+"','"+db_bar_1_price+"'" +
                    ",'"+db_bar_2_runningfeet+"','"+db_bar_2_length+"','"+db_bar_2_kg+"','"+db_bar_2_ton+"','"+db_bar_2_price+"'" +
                    ",'"+db_bar_3_runningfeet+"','"+db_bar_3_length+"','"+db_bar_3_kg+"','"+db_bar_3_ton+"','"+db_bar_3_price+"'" +
                    ",'"+db_bar_4_runningfeet+"','"+db_bar_4_length+"','"+db_bar_4_kg+"','"+db_bar_4_ton+"','"+db_bar_4_price+"'" +
                    ",'"+db_bar_5_runningfeet+"','"+db_bar_5_length+"','"+db_bar_5_kg+"','"+db_bar_5_ton+"','"+db_bar_5_price+"'" +
                    ",'"+db_bar_6_runningfeet+"','"+db_bar_6_length+"','"+db_bar_6_kg+"','"+db_bar_6_ton+"','"+db_bar_6_price+"'" +
                    ",'"+db_bar_7_runningfeet+"','"+db_bar_7_length+"','"+db_bar_7_kg+"','"+db_bar_7_ton+"','"+db_bar_7_price+"'" +
                    ",'"+db_bar_8_runningfeet+"','"+db_bar_8_length+"','"+db_bar_8_kg+"','"+db_bar_8_ton+"','"+db_bar_8_price+"'" +



                    ",'"+db_no_of_blocks_number+"','"+db_no_of_blocks_price+"'" +

                    ",'"+db_sakra_of_chips_number+"','"+db_sakra_of_chips_price+"'" +

                    ",'"+db_no_of_bricks_number+"','"+db_no_of_bricks_price+"'" +

                    ",'"+db_sakra_of_stone_num+"','"+db_sakra_of_stone_price+"'" +


                    ",'"+db_excuvation_days+"','"+db_excuvation_labour+"' ,'"+db_excuvation_price+"'" +

                    ",'"+db_marble_number+"','"+db_marble_length+"','"+db_marble_width+"','"+db_marble_height+"','"+db_marble_cost+"'" +



                    "); ");

        }
        else {
            db_connect.delete("all_records", null, null);

            db_connect.execSQL("insert into all_records values('" + db_cement_quantity + "','" + db_cement_price + "','"+ db_sand_quantity +"','"+ db_sand_price +"','"+db_crush_quantity+"','"+db_crush_price+"'" +

                    ",'"+db_bar_1_runningfeet+"','"+db_bar_1_length+"','"+db_bar_1_kg+"','"+db_bar_1_ton+"','"+db_bar_1_price+"'" +
                    ",'"+db_bar_2_runningfeet+"','"+db_bar_2_length+"','"+db_bar_2_kg+"','"+db_bar_2_ton+"','"+db_bar_2_price+"'" +
                    ",'"+db_bar_3_runningfeet+"','"+db_bar_3_length+"','"+db_bar_3_kg+"','"+db_bar_3_ton+"','"+db_bar_3_price+"'" +
                    ",'"+db_bar_4_runningfeet+"','"+db_bar_4_length+"','"+db_bar_4_kg+"','"+db_bar_4_ton+"','"+db_bar_4_price+"'" +
                    ",'"+db_bar_5_runningfeet+"','"+db_bar_5_length+"','"+db_bar_5_kg+"','"+db_bar_5_ton+"','"+db_bar_5_price+"'" +
                    ",'"+db_bar_6_runningfeet+"','"+db_bar_6_length+"','"+db_bar_6_kg+"','"+db_bar_6_ton+"','"+db_bar_6_price+"'" +
                    ",'"+db_bar_7_runningfeet+"','"+db_bar_7_length+"','"+db_bar_7_kg+"','"+db_bar_7_ton+"','"+db_bar_7_price+"'" +
                    ",'"+db_bar_8_runningfeet+"','"+db_bar_8_length+"','"+db_bar_8_kg+"','"+db_bar_8_ton+"','"+db_bar_8_price+"'" +



                    ",'"+db_no_of_blocks_number+"','"+db_no_of_blocks_price+"'" +

                    ",'"+db_sakra_of_chips_number+"','"+db_sakra_of_chips_price+"'" +

                    ",'"+db_no_of_bricks_number+"','"+db_no_of_bricks_price+"'" +

                    ",'"+db_sakra_of_stone_num+"','"+db_sakra_of_stone_price+"'" +


                    ",'"+db_excuvation_days+"','"+db_excuvation_labour+"' ,'"+db_excuvation_price+"'" +

                    ",'"+db_marble_number+"','"+db_marble_length+"','"+db_marble_width+"','"+db_marble_height+"','"+db_marble_cost+"'" +



                    "); ");




        }

    }











    public void fetch_db_main_save_name(){



        db_connect= getActivity().openOrCreateDatabase("smart_estimator",getActivity().MODE_PRIVATE, null);

        db_cursor = db_connect.rawQuery("select * from save", null);

        int row_count = db_cursor.getCount();

        db_cursor.moveToFirst();



        if(row_count==0) // means no entry exist so insert else update
        {

            main_file_name = "1.txt";

        }else {

            main_file_name = db_cursor.getString(db_cursor.getColumnIndex("num"));
            main_file_name = main_file_name + ".txt";

        }

    }




    public void fetch_data_from_main_save_file(){

        File root = new File(Environment.getExternalStorageDirectory(), "Smart Building Estimator/all");
        if (!root.exists()) {
            root.mkdirs();
        }



        //Get the text file
        File file = new File(root,main_file_name);
        // i have kept text.txt in the sd-card

        if(file.exists())   // check if file exist
        {
            //Read text from file
            sd_card_file_text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null) {
                    sd_card_file_text.append(line);
                    sd_card_file_text.append('\r');
                    sd_card_file_text.append('\n');
                }
            }
            catch (Exception e) {
                //You'll need to add proper error handling here
            }

        }

    }






    public void save_txt_file_all(String sFileName, String sBody){
        try
        {
            File root = new File(Environment.getExternalStorageDirectory(), "Smart Building Estimator/all");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();

        }
        catch(IOException e)
        {
            e.printStackTrace();

        }
    }








    public void save_txt_file_indivisual(String sFileName, String sBody){
        try
        {
            File root = new File(Environment.getExternalStorageDirectory(), "Smart Building Estimator/indivisual");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName+".txt");
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();

        }
        catch(IOException e)
        {
            e.printStackTrace();

        }
    }












    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    // handle back button's click listener

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, new Chips_quality_selection())
                            .commit();

                    return true;
                }
                return false;
            }
        });
    }


}
