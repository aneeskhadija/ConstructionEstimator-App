package com.kingsforth.constructionestimator.Sub_Structure;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.alfadroid.constructionestimator.R;


public class Footing_enter_values extends Fragment {


    ImageView btn_nxt;

    RadioButton radio_tiebar_a
            ,radio_tiebar_b
            ,radio_tiebar_c
            ,radio_tiebar_d;

    RadioButton radio_main_a
            ,radio_main_b
            ,radio_main_c
            ,radio_main_d
            ,radio_main_e
            ,radio_main_f
            ,radio_main_g
            ,radio_main_h;

    EditText ed_main_bar_lenght
            ,ed_main_no_of_bars;


    String sand_quality
            ,cement_quality
            ,crush_quality;

    EditText ed_length
            ,ed_width
            ,ed_height;

    EditText ed_tiebar_running_feet;

    Button btn_calculate;

    double tiebar_radio = 0
//            ,logbar_radio = 0
            ,mainbar_radio= 0;


    double tiebar_vol
            ,logbar_vol
            ,mainbar_vol
            ,total_vol;

    double result;



    double cement_quantity_for_send
            , cement_price_for_send
            , sand_quantity_for_send
            , sand_price_for_send
            , crush_quantity_for_send
            , crush_price_for_send;

    double tiebar_type_for_send
            ,tiebar_length_for_send
            ,tiebar_kg_for_send
            ,tiebar_tun_for_send
            ,tiebar_price_for_send;

    double mainbar_type_for_send
            ,mainbar_length_for_send
            ,mainbar_kg_for_send
            ,mainbar_tun_for_send
            ,mainbar_price_for_send;

    double main_bar_running_feet
            ,logbar_running_feet;

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.beam_enter_values, container, false);





//******************  Getting values from intent   **********************

        cement_quality  		= getArguments().getString("quality_a");
        sand_quality		    = getArguments().getString("quality_b");
        crush_quality		    = getArguments().getString("quality_c");

//****************** End  Getting values from intent   **********************




        ed_length          = (EditText) rootView.findViewById(R.id.rcc_length);
        ed_width           = (EditText) rootView.findViewById(R.id.rcc_width);
        ed_height          = (EditText) rootView.findViewById(R.id.rcc_height);



        radio_tiebar_a              = (RadioButton) rootView.findViewById(R.id.tiebar_a);
        radio_tiebar_b              = (RadioButton) rootView.findViewById(R.id.tiebar_b);
        radio_tiebar_c              = (RadioButton) rootView.findViewById(R.id.tiebar_c);
        radio_tiebar_d              = (RadioButton) rootView.findViewById(R.id.tiebar_d);
        ed_tiebar_running_feet      = (EditText) rootView.findViewById(R.id.tiebar_running_feet);




        radio_main_a              = (RadioButton) rootView.findViewById(R.id.main_bar_a);
        radio_main_b              = (RadioButton) rootView.findViewById(R.id.main_bar_b);
        radio_main_c              = (RadioButton) rootView.findViewById(R.id.main_bar_c);
        radio_main_d              = (RadioButton) rootView.findViewById(R.id.main_bar_d);
        radio_main_e              = (RadioButton) rootView.findViewById(R.id.main_bar_e);
        radio_main_f              = (RadioButton) rootView.findViewById(R.id.main_bar_f);
        radio_main_g              = (RadioButton) rootView.findViewById(R.id.main_bar_g);
        radio_main_h              = (RadioButton) rootView.findViewById(R.id.main_bar_h);
        ed_main_bar_lenght        = (EditText) rootView.findViewById(R.id.main_bar_length);

        ed_main_no_of_bars        = (EditText) rootView.findViewById(R.id.main_bar_numbers);


        btn_calculate             = (Button) rootView.findViewById(R.id.calculate);






//************** Radio Tiebar Click ******************
        radio_tiebar_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio_tiebar_a.setChecked(true);
                radio_tiebar_b.setChecked(false);
                radio_tiebar_c.setChecked(false);
                radio_tiebar_d.setChecked(false);

                tiebar_radio = 1;

            }
        });

        radio_tiebar_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio_tiebar_a.setChecked(false);
                radio_tiebar_b.setChecked(true);
                radio_tiebar_c.setChecked(false);
                radio_tiebar_d.setChecked(false);

                tiebar_radio = 2;

            }
        });

        radio_tiebar_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio_tiebar_a.setChecked(false);
                radio_tiebar_b.setChecked(false);
                radio_tiebar_c.setChecked(true);
                radio_tiebar_d.setChecked(false);

                tiebar_radio = 3;

            }
        });

        radio_tiebar_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio_tiebar_a.setChecked(false);
                radio_tiebar_b.setChecked(false);
                radio_tiebar_c.setChecked(false);
                radio_tiebar_d.setChecked(true);

                tiebar_radio = 4;

            }
        });


//************** End Radio Tiebar Click ******************
//************** Radio Main bar Click ******************
        radio_main_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio_main_a.setChecked(true);
                radio_main_b.setChecked(false);
                radio_main_c.setChecked(false);
                radio_main_d.setChecked(false);
                radio_main_e.setChecked(false);
                radio_main_f.setChecked(false);
                radio_main_g.setChecked(false);
                radio_main_h.setChecked(false);


                mainbar_radio = 1;

            }
        });

        radio_main_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio_main_a.setChecked(false);
                radio_main_b.setChecked(true);
                radio_main_c.setChecked(false);
                radio_main_d.setChecked(false);
                radio_main_e.setChecked(false);
                radio_main_f.setChecked(false);
                radio_main_g.setChecked(false);
                radio_main_h.setChecked(false);


                mainbar_radio = 2;

            }
        });

        radio_main_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio_main_a.setChecked(false);
                radio_main_b.setChecked(false);
                radio_main_c.setChecked(true);
                radio_main_d.setChecked(false);
                radio_main_e.setChecked(false);
                radio_main_f.setChecked(false);
                radio_main_g.setChecked(false);
                radio_main_h.setChecked(false);


                mainbar_radio = 3;

            }
        });

        radio_main_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio_main_a.setChecked(false);
                radio_main_b.setChecked(false);
                radio_main_c.setChecked(false);
                radio_main_d.setChecked(true);
                radio_main_e.setChecked(false);
                radio_main_f.setChecked(false);
                radio_main_g.setChecked(false);
                radio_main_h.setChecked(false);


                mainbar_radio = 4;

            }
        });

        radio_main_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio_main_a.setChecked(false);
                radio_main_b.setChecked(false);
                radio_main_c.setChecked(false);
                radio_main_d.setChecked(false);
                radio_main_e.setChecked(true);
                radio_main_f.setChecked(false);
                radio_main_g.setChecked(false);
                radio_main_h.setChecked(false);


                mainbar_radio = 5;

            }
        });

        radio_main_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio_main_a.setChecked(false);
                radio_main_b.setChecked(false);
                radio_main_c.setChecked(false);
                radio_main_d.setChecked(false);
                radio_main_e.setChecked(false);
                radio_main_f.setChecked(true);
                radio_main_g.setChecked(false);
                radio_main_h.setChecked(false);


                mainbar_radio = 6;

            }
        });

        radio_main_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio_main_a.setChecked(false);
                radio_main_b.setChecked(false);
                radio_main_c.setChecked(false);
                radio_main_d.setChecked(false);
                radio_main_e.setChecked(false);
                radio_main_f.setChecked(false);
                radio_main_g.setChecked(true);
                radio_main_h.setChecked(false);


                mainbar_radio = 7;

            }
        });

        radio_main_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio_main_a.setChecked(false);
                radio_main_b.setChecked(false);
                radio_main_c.setChecked(false);
                radio_main_d.setChecked(false);
                radio_main_e.setChecked(false);
                radio_main_f.setChecked(false);
                radio_main_g.setChecked(false);
                radio_main_h.setChecked(true);


                mainbar_radio = 8;

            }
        });

//**************End Radio Main bar Click ******************


        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(all_edittext_filled()){

                    calculation_for_result();

                    send_result();
                }





            }
        });

		return rootView;
	}


    public boolean all_edittext_filled(){


        if(ed_length.getText().toString().trim().equals("") || ed_length.getText().toString().trim().equals(null) ){

            Toast.makeText(getActivity(), "Please fill the form to proceed..", Toast.LENGTH_SHORT).show();

            ed_length.requestFocus();

            return false;

        }else if(ed_width.getText().toString().trim().equals("") || ed_width.getText().toString().trim().equals(null) ){

            Toast.makeText(getActivity(), "Please fill the form to proceed..", Toast.LENGTH_SHORT).show();

            ed_width.requestFocus();

            return false;

        }else if(ed_height.getText().toString().trim().equals("") || ed_height.getText().toString().trim().equals(null) ){

            Toast.makeText(getActivity(), "Please fill the form to proceed..", Toast.LENGTH_SHORT).show();

            ed_height.requestFocus();

            return false;

        }else if( tiebar_radio ==0  ){

            Toast.makeText(getActivity(), "Please fill the form to proceed..", Toast.LENGTH_SHORT).show();

            radio_tiebar_a.requestFocus();

            return false;

        }else if(ed_tiebar_running_feet.getText().toString().trim().equals("") || ed_tiebar_running_feet.getText().toString().trim().equals(null) ){

            Toast.makeText(getActivity(), "Please fill the form to proceed..", Toast.LENGTH_SHORT).show();

            ed_tiebar_running_feet.requestFocus();

            return false;



        }else if( mainbar_radio ==0  ){

            Toast.makeText(getActivity(), "Please fill the form to proceed..", Toast.LENGTH_SHORT).show();

            radio_main_a.requestFocus();

            return false;

        }else if(ed_main_bar_lenght.getText().toString().trim().equals("") || ed_main_bar_lenght.getText().toString().trim().equals(null) ){

            Toast.makeText(getActivity(), "Please fill the form to proceed..", Toast.LENGTH_SHORT).show();

            ed_main_bar_lenght.requestFocus();

            return false;

        }else if(ed_main_no_of_bars.getText().toString().trim().equals("") || ed_main_no_of_bars.getText().toString().trim().equals(null) ){

            Toast.makeText(getActivity(), "Please fill the form to proceed..", Toast.LENGTH_SHORT).show();

            ed_main_no_of_bars.requestFocus();

            return false;

        }else {

            return true;

        }



    }






	public void calculation_for_result(){

        double pi = 22/7;
        double d=0;

        double vol_area,vol_length;


        //Find tiebar volume

        d = tiebar_radio/8;

        vol_area = (pi/4)*(d*d);

        vol_length = Double.parseDouble(ed_tiebar_running_feet.getText().toString().trim());

        tiebar_vol = vol_area * vol_length;


        //Find Main bar volume

        d = mainbar_radio/8;

        vol_area = (pi/4)*(d*d);

        vol_length = Double.parseDouble(ed_main_bar_lenght.getText().toString().trim());

        mainbar_vol = vol_area * vol_length;

        mainbar_vol = mainbar_vol * Double.parseDouble(ed_main_no_of_bars.getText().toString().trim());




        // Total volume

        total_vol = tiebar_vol + logbar_vol +mainbar_vol ;

        // Result Rcc
        double value_area
                ,cubic_feet;

        double dry_coeeficient = 1.54;



        value_area = Double.parseDouble(ed_length.getText().toString().trim()) *
                Double.parseDouble(ed_width.getText().toString().trim()) *
                Double.parseDouble(ed_height.getText().toString().trim());


        cubic_feet = value_area * total_vol;

        result = cubic_feet * dry_coeeficient;



        double quality_a = Double.parseDouble(cement_quality)
                ,quality_b = Double.parseDouble(sand_quality)
                ,quality_c = Double.parseDouble(crush_quality);





        double sum_of_ratio = quality_a + quality_b + quality_c ;





        cement_quantity_for_send =  ( quality_a / sum_of_ratio ) * result;

        sand_quantity_for_send =  ( quality_b / sum_of_ratio ) * result;

        crush_quantity_for_send =  ( quality_c / sum_of_ratio ) * result;



        // fetch database
        db_configuration();




        cement_price_for_send    = cement_quantity_for_send * Double.parseDouble(db_cement);

        sand_price_for_send      = sand_quantity_for_send * Double.parseDouble(db_sand);

        crush_price_for_send     = crush_quantity_for_send * Double.parseDouble(db_crush);


        //Tiebar for send





        tiebar_type_for_send        = tiebar_radio;

        tiebar_length_for_send      = Double.parseDouble(ed_tiebar_running_feet.getText().toString().trim()) * 0.3048;

        tiebar_kg_for_send          = tiebar_length_for_send * Double.parseDouble(db_tiebar_kg) ;

        tiebar_tun_for_send         = tiebar_kg_for_send / 1000;

        tiebar_price_for_send       = tiebar_tun_for_send * Double.parseDouble(db_tiebar_price);

//        Toast.makeText(Footing_enter_values.this, "", Toast.LENGTH_SHORT).show();




        //Main bar for send


        main_bar_running_feet = Double.parseDouble(ed_main_bar_lenght.getText().toString().trim()) * Double.parseDouble(ed_main_no_of_bars.getText().toString().trim()) ;



        mainbar_type_for_send        = mainbar_radio;

        mainbar_length_for_send      = main_bar_running_feet * 0.3048;

        mainbar_kg_for_send          = mainbar_length_for_send * Double.parseDouble(db_main_log_kg) ;

        mainbar_tun_for_send         = mainbar_kg_for_send / 1000;

        mainbar_price_for_send       = mainbar_tun_for_send * Double.parseDouble(db_main_log_price);



    }








    public void send_result(){


        Footing_result frag = new Footing_result();
        Bundle args = new Bundle();


        args.putString("sand_ratio", sand_quality);
        args.putString("cement_ratio", cement_quality);
        args.putString("crush_ratio", crush_quality);

        args.putString("cement_quantity", ""+cement_quantity_for_send);
        args.putString("cement_price", ""+cement_price_for_send);

        args.putString("sand_quantity", ""+sand_quantity_for_send);
        args.putString("sand_price",""+sand_price_for_send);

        args.putString("crush_quantity", ""+crush_quantity_for_send);
        args.putString("crush_price", ""+crush_price_for_send);

//        Toast.makeText(getActivity(), "crush_price_for_send "+ crush_price_for_send, Toast.LENGTH_SHORT).show();

        args.putString("tiebar_type", "" +tiebar_type_for_send);
        args.putString("tiebar_length", ""+tiebar_length_for_send);
        args.putString("tiebar_kg",""+tiebar_kg_for_send );
        args.putString("tiebar_tun", ""+tiebar_tun_for_send);
        args.putString("tiebar_price", ""+tiebar_price_for_send);

        args.putString("mainbar_type", ""+mainbar_type_for_send);
        args.putString("mainbar_length", ""+ mainbar_length_for_send);
        args.putString("mainbar_kg", ""+mainbar_kg_for_send);
        args.putString("mainbar_tun",""+mainbar_tun_for_send);
        args.putString("mainbar_price", ""+mainbar_price_for_send);




        args.putString("tie_running", ""+ Double.parseDouble(ed_tiebar_running_feet.getText().toString().trim()));

        args.putString("main_running", ""+ (main_bar_running_feet ));



        frag.setArguments(args);

        getFragmentManager().beginTransaction().replace(R.id.content_frame, frag).commit();



    }










    String db_sand ="0"
            ,db_cement ="0"
            ,db_crush ="0"
            ,db_tiebar_kg ="0"
            ,db_tiebar_price ="0"
            ,db_main_log_kg ="0"
            ,db_main_log_price ="0";




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
        }



//DB tiebar

        db_cursor = db_connect.rawQuery("select * from configuration_tiebar", null);


        if(db_cursor.getCount() == 1) {

            db_cursor.moveToNext();

            if(tiebar_radio==1){

                db_tiebar_kg          = db_cursor.getString(db_cursor.getColumnIndex("one_kg"));
                db_tiebar_price       = db_cursor.getString(db_cursor.getColumnIndex("one_price"));

            }else  if(tiebar_radio==2){

                db_tiebar_kg          = db_cursor.getString(db_cursor.getColumnIndex("two_kg"));
                db_tiebar_price       = db_cursor.getString(db_cursor.getColumnIndex("two_price"));

            }else  if(tiebar_radio==3){

                db_tiebar_kg          = db_cursor.getString(db_cursor.getColumnIndex("three_kg"));
                db_tiebar_price       = db_cursor.getString(db_cursor.getColumnIndex("three_price"));

            }else  if(tiebar_radio==4){

                db_tiebar_kg          = db_cursor.getString(db_cursor.getColumnIndex("four_kg"));
                db_tiebar_price       = db_cursor.getString(db_cursor.getColumnIndex("four_price"));

            }


        }





//DB Log & main bar

        db_cursor = db_connect.rawQuery("select * from configuration_log_main_bar", null);


        if(db_cursor.getCount() == 1) {

            db_cursor.moveToNext();

            if(tiebar_radio==1){

                db_main_log_kg          = db_cursor.getString(db_cursor.getColumnIndex("one_kg"));
                db_main_log_price       = db_cursor.getString(db_cursor.getColumnIndex("one_price"));


            }else  if(tiebar_radio==2){

                db_main_log_kg          = db_cursor.getString(db_cursor.getColumnIndex("two_kg"));
                db_main_log_price       = db_cursor.getString(db_cursor.getColumnIndex("two_price"));

            }else  if(tiebar_radio==3){

                db_main_log_kg          = db_cursor.getString(db_cursor.getColumnIndex("three_kg"));
                db_main_log_price       = db_cursor.getString(db_cursor.getColumnIndex("three_price"));

            }else  if(tiebar_radio==4){

                db_main_log_kg          = db_cursor.getString(db_cursor.getColumnIndex("four_kg"));
                db_main_log_price       = db_cursor.getString(db_cursor.getColumnIndex("four_price"));

            }else  if(tiebar_radio==5){

                db_main_log_kg          = db_cursor.getString(db_cursor.getColumnIndex("five_kg"));
                db_main_log_price       = db_cursor.getString(db_cursor.getColumnIndex("five_price"));

            }else  if(tiebar_radio==6){

                db_main_log_kg          = db_cursor.getString(db_cursor.getColumnIndex("six_kg"));
                db_main_log_price       = db_cursor.getString(db_cursor.getColumnIndex("six_price"));

            }else  if(tiebar_radio==7){

                db_main_log_kg          = db_cursor.getString(db_cursor.getColumnIndex("seven_kg"));
                db_main_log_price       = db_cursor.getString(db_cursor.getColumnIndex("seven_price"));

            }else  if(tiebar_radio==8){

                db_main_log_kg          = db_cursor.getString(db_cursor.getColumnIndex("eight_kg"));
                db_main_log_price       = db_cursor.getString(db_cursor.getColumnIndex("eight_price"));

            }



        }

    }




                                                                                                                                                                                                                                                                                                public void match(){

        SQLiteDatabase db_connect;
        Cursor db_cursor;

        db_connect = getActivity().openOrCreateDatabase("smart_estimator",getActivity().MODE_PRIVATE, null);


        db_cursor = db_connect.rawQuery("select * from confi_check", null);

        int row_count = db_cursor.getCount();

        db_cursor.moveToFirst();

        if(row_count == 1){

            String check = db_cursor.getString(db_cursor.getColumnIndex("version"));

            if (check.equals("null")){
                getActivity().finish();
            }
        }

        db_connect.close();
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
                            .replace(R.id.content_frame, new Footing_quality_selection())
                            .commit();

                    return true;
                }
                return false;
            }
        });
    }

}
