package com.kingsforth.constructionestimator.Sub_Structure;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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

public class Stone_percentage_result extends Fragment {

	ImageView btn_nxt;




    EditText ed_length
            ,ed_width
            ,ed_height
            ,ed_percentage;



    TextView tv_ratio_a
            ,tv_ratio_b
            ,tv_ratio_c

            ,tv_cement_quantity
            ,tv_cement_price

            ,tv_sand_quantity
            ,tv_sand_price

            ,tv_crush_quantity
            ,tv_crush_price

            ,tv_no_of_sakra
            ,tv_sakra_price
            ;

    Double cement_quantity
            ,cement_price

            ,sand_quantity
            ,sand_price

            ,crush_quantity
            ,crush_price;




    CheckBox chk;

    Boolean check_box = false;



    String intent_area
            ,intent_percentage
            ,intent_sakra
            ,intent_price
            ,intent_quality_a
            ,intent_quality_b
            ,intent_quality_c;

    Double percentage_area;



    String intent_for;

    Button btn_save,btn_back;


    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = null;


         df = new DecimalFormat("#.##");


        intent_for = getArguments().getString("for");


        // Getting values through Intent
        intent_area  		    = getArguments().getString("area");
        intent_percentage      	= getArguments().getString("percentage");
        intent_sakra      		= getArguments().getString("sakra");
        intent_price      		= getArguments().getString("price");
        percentage_area = (Double.parseDouble(intent_area) * Double.parseDouble(intent_percentage) ) / 100;

        percentage_area = percentage_area * 1.54;
        if(intent_for.equals("three")){

            rootView = inflater.inflate(R.layout.stone_result_for_percentage_three, container, false);



            intent_quality_a      	    = getArguments().getString("quality_a");
            intent_quality_b      		= getArguments().getString("quality_b");
            intent_quality_c      		= getArguments().getString("quality_c");
            tv_ratio_a                  = (TextView) rootView.findViewById(R.id.ratio_a);
            tv_ratio_b                  = (TextView) rootView.findViewById(R.id.ratio_b);
            tv_ratio_c                  = (TextView) rootView.findViewById(R.id.ratio_c);

            tv_cement_quantity          = (TextView) rootView.findViewById(R.id.cement_bag_quantity);
            tv_cement_price             = (TextView) rootView.findViewById(R.id.cement_bag_price);

            tv_sand_quantity            = (TextView) rootView.findViewById(R.id.sand_quantity);
            tv_sand_price               = (TextView) rootView.findViewById(R.id.sand_price);

            tv_crush_quantity           = (TextView) rootView.findViewById(R.id.crush_quantity);
            tv_crush_price              = (TextView) rootView.findViewById(R.id.crush_price);

            tv_no_of_sakra              = (TextView) rootView.findViewById(R.id.no_of_sakra);
            tv_sakra_price              = (TextView) rootView.findViewById(R.id.price_sakra);


            btn_save                = (Button) rootView.findViewById(R.id.save);
            btn_back                = (Button) rootView.findViewById(R.id.back);
            tv_ratio_a.setText(intent_quality_a);
            tv_ratio_b.setText(intent_quality_b);
            tv_ratio_c.setText(intent_quality_c);
            calculation_for_three_result();
        }else  if(intent_for.equals("two")){

           rootView = inflater.inflate(R.layout.stone_result_for_percentage_two, container, false);


            tv_ratio_a                  = (TextView) rootView.findViewById(R.id.ratio_a);
            tv_ratio_b                  = (TextView) rootView.findViewById(R.id.ratio_b);



            tv_cement_quantity          = (TextView) rootView.findViewById(R.id.cement_bag_quantity);
            tv_cement_price             = (TextView) rootView.findViewById(R.id.cement_bag_price);

            tv_sand_quantity            = (TextView) rootView.findViewById(R.id.sand_quantity);
            tv_sand_price               = (TextView) rootView.findViewById(R.id.sand_price);

            tv_no_of_sakra              = (TextView) rootView.findViewById(R.id.no_of_sakra);
            tv_sakra_price              = (TextView) rootView.findViewById(R.id.price_sakra);
            btn_save                = (Button) rootView.findViewById(R.id.save);
            btn_back                = (Button) rootView.findViewById(R.id.back);
            intent_quality_a      	    = getArguments().getString("quality_n_a");
            intent_quality_b      		= getArguments().getString("quality_n_b");
            tv_ratio_a.setText(intent_quality_a);
            tv_ratio_b.setText(intent_quality_b);


//            Toast.makeText(getActivity(), "intent_quality_a "+intent_quality_a, Toast.LENGTH_SHORT).show();
            
            calculation_for_two_result();
        }

		return rootView;
	}
    public void calculation_for_three_result(){


        Double result;


        result = percentage_area;

        // Ratio

        double quality_a        = Double.parseDouble(intent_quality_a)
                ,quality_b      = Double.parseDouble(intent_quality_b)
                ,quality_c      = Double.parseDouble(intent_quality_c);





        double sum_of_ratio = quality_a + quality_b + quality_c ;





        cement_quantity  =  ( quality_a / sum_of_ratio ) * result;

        cement_quantity = cement_quantity / 1.25;



        sand_quantity =  ( quality_b / sum_of_ratio ) * result;

        sand_quantity = sand_quantity / 100;


        crush_quantity =  ( quality_c / sum_of_ratio ) * result;

        crush_quantity = crush_quantity / 100;




        // fetch database
        db_configuration();




        cement_price    = cement_quantity * Double.parseDouble(db_cement);

        sand_price      = sand_quantity * Double.parseDouble(db_sand);

        crush_price     = crush_quantity * Double.parseDouble(db_crush);




        tv_cement_quantity.setText(df.format(cement_quantity));
        tv_cement_price.setText(df.format(cement_price));

        tv_sand_quantity.setText(df.format(sand_quantity));
        tv_sand_price.setText(df.format(sand_price));

        tv_crush_quantity.setText(df.format(crush_quantity));
        tv_crush_price.setText(df.format(crush_price));



        tv_no_of_sakra.setText(df.format(Double.parseDouble(intent_sakra)));
        tv_sakra_price.setText(df.format(Double.parseDouble(intent_price)));

        fetch_all_record_from_Db();





        db_cement_quantity      = df.format( Double.parseDouble(db_cement_quantity) + cement_quantity);
        db_cement_price         = df.format( Double.parseDouble(db_cement_price) + cement_price);
        db_sand_quantity        = df.format( Double.parseDouble(db_sand_quantity) + sand_quantity);
        db_sand_price           = df.format( Double.parseDouble(db_sand_price) + sand_price);

        db_sakra_of_stone_num    = df.format( Double.parseDouble(db_sakra_of_stone_num) + Double.parseDouble(intent_sakra));
        db_sakra_of_stone_price  = df.format( Double.parseDouble(db_sakra_of_stone_price) + Double.parseDouble(intent_price));


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



    }


    public void calculation_for_two_result(){


        Double result;


        result = percentage_area;




        // Ratio

        double quality_a        = Double.parseDouble(intent_quality_a)
                ,quality_b      = Double.parseDouble(intent_quality_b)
                ;





        double sum_of_ratio = quality_a + quality_b  ;





        cement_quantity  =  ( quality_a / sum_of_ratio ) * result;

        cement_quantity = cement_quantity / 1.25;



        sand_quantity =  ( quality_b / sum_of_ratio ) * result;

        sand_quantity = sand_quantity / 100;


        // fetch database
        db_configuration();




        cement_price    = cement_quantity * Double.parseDouble(db_cement);

        sand_price      = sand_quantity * Double.parseDouble(db_sand);

//        crush_price     = crush_quantity * Double.parseDouble(db_crush);

        tv_cement_quantity.setText(df.format(cement_quantity));
        tv_cement_price.setText(df.format(cement_price));

        tv_sand_quantity.setText(df.format(sand_quantity));
        tv_sand_price.setText(df.format(sand_price));


        tv_no_of_sakra.setText(df.format(Double.parseDouble(intent_sakra)));
        tv_sakra_price.setText(df.format(Double.parseDouble(intent_price)));
        fetch_all_record_from_Db();

        db_cement_quantity      = df.format( Double.parseDouble(db_cement_quantity) + cement_quantity);
        db_cement_price         = df.format( Double.parseDouble(db_cement_price) + cement_price);
        db_sand_quantity        = df.format( Double.parseDouble(db_sand_quantity) + sand_quantity);
        db_sand_price           = df.format( Double.parseDouble(db_sand_price) + sand_price);
//        db_crush_quantity       = df.format( Double.parseDouble(db_crush_quantity) + crush_quantity);
//        db_crush_price          = df.format( Double.parseDouble(db_crush_price) + crush_price);

        db_sakra_of_stone_num    = df.format( Double.parseDouble(db_sakra_of_stone_num) + Double.parseDouble(intent_sakra));
        db_sakra_of_stone_price  = df.format( Double.parseDouble(db_sakra_of_stone_price) + Double.parseDouble(intent_price));

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








    }




    String db_sand ="0"
            ,db_cement ="0"
            ,db_crush ="0"
            ,db_brick ="0"
            ;





    public void db_configuration(){

        SQLiteDatabase db_connect;
        Cursor db_cursor;

        db_connect = getActivity().openOrCreateDatabase("smart_estimator",getActivity().MODE_PRIVATE, null);



        db_cursor = db_connect.rawQuery("select * from configuration_first", null);


        if(db_cursor.getCount() == 1) {

            db_cursor.moveToNext();


            db_sand         = db_cursor.getString(db_cursor.getColumnIndex("sand"));
            db_cement       = db_cursor.getString(db_cursor.getColumnIndex("cement"));
            db_crush        = db_cursor.getString(db_cursor.getColumnIndex("coarse"));
            db_brick        = db_cursor.getString(db_cursor.getColumnIndex("brick"));
        }


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

    String indivisual_save_body;
    public void Save_indivisual_body_making(){

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());


        indivisual_save_body = currentDateTimeString +line_space
                +"Stone Work Result" +line_space+line_space;

        indivisual_save_body = indivisual_save_body + "Cement bag              "+"Quantity : "+df.format(Double.parseDouble(""+cement_quantity)) + "     Price : " + df.format(Double.parseDouble(""+cement_price)) +line_space;


        indivisual_save_body = indivisual_save_body + "Fine agregade (Sand)    "+"Quantity : "+df.format(Double.parseDouble(""+sand_quantity)) + "     Price : " + df.format(Double.parseDouble(""+sand_price)) +line_space;




        if(intent_for.equals("three")){

            indivisual_save_body = indivisual_save_body + "Coarse agregade (Crush) "+"Quantity : "+df.format(Double.parseDouble(""+crush_quantity)) + "     Price : " + df.format(Double.parseDouble(""+crush_price)) +line_space
                    +line_space;


        }








        indivisual_save_body = indivisual_save_body +line_space+line_space

                + "Sakra of stones         "+df.format(Double.parseDouble(intent_sakra)) + "     Price : "+df.format(Double.parseDouble(intent_price));






        indivisual_save_body   = indivisual_save_body + line_space+line_space
                + "********************************************************************************************************"+line_space;




    }

    String save_body;


    SQLiteDatabase db_connect;
    Cursor db_cursor;

    String main_file_name;

    StringBuilder sd_card_file_text;










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

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button's click listener

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, new Stone_enter_values())
                            .commit();

                    return true;
                }
                return false;
            }
        });
    }



}
