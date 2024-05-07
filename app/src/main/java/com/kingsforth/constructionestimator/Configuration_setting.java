package com.kingsforth.constructionestimator;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alfadroid.constructionestimator.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.text.DecimalFormat;

public class Configuration_setting extends Fragment {
    InterstitialAd mInterstitialAd;


	SQLiteDatabase db_connect;
	Cursor db_cursor;

	Button btn_save;

    TextView ed_log_main_two_kg
            , ed_log_main_three_kg
            , ed_log_main_four_kg
            , ed_log_main_five_kg
            , ed_log_main_six_kg
            , ed_log_main_seven_kg
            , ed_log_main_eight_kg

            ,tv_log_main_tun1
            ,tv_log_main_tun2
            ,tv_log_main_tun3
            ,tv_log_main_tun4
            ,tv_log_main_tun5
            ,tv_log_main_tun6
            ,tv_log_main_tun7
            ,tv_log_main_tun8

            ,tv_tiebar1
            ,tv_tiebar2
            ,tv_tiebar3
            ,tv_tiebar4

            ,tv_marbel_area
            , ed_tiebar_two_kg

            , ed_tiebar_three_kg
            , ed_tiebar_four_kg
            ;





	EditText ed_brick
			,ed_sand
			,ed_coarse
			,ed_cement

			, ed_tiebar_one_kg
            , ed_tiebar_one_price

            , ed_tiebar_two_price

            , ed_tiebar_three_price

            , ed_tiebar_four_price

			, ed_log_main_one_kg

            , ed_log_main_one_price

            , ed_log_main_two_price

            , ed_log_main_three_price

            , ed_log_main_four_price

            , ed_log_main_five_price

            , ed_log_main_six_price

            , ed_log_main_seven_price

            , ed_log_main_eight_price

			,ed_block_area
			,ed_block_price

			,ed_marbel_length
			,ed_marbel_width
			,ed_marbel_height
			,ed_marbel_price

			,ed_chips_sakra
            ,ed_chips_price

            ,ed_excevation_area
            ,ed_excevation_price

            ,ed_stone_sakra
            ,ed_stone_price;

    LinearLayout ll_reset;
//	private StartAppAd startAppAd = new StartAppAd(getActivity());
	String[] db_first_values;
	String[] db_tiebar_values;
	String[] db_log_main_bar_values;

    DecimalFormat df;
//

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.configuration_setting, container, false);
        mInterstitialAd = new InterstitialAd(getActivity());

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.ad_interstitial));

        AdRequest adRequest = new AdRequest.Builder()
                .build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });


         df = new DecimalFormat("#.##");



		db_connect = getActivity().openOrCreateDatabase("smart_estimator",getActivity().MODE_PRIVATE, null);

		// Brick sand coarse cement_bag prices db table
		db_connect.execSQL("create table if not exists configuration_first(brick varchar,sand varchar,coarse varchar,cement varchar)");

		// Tiebar
		db_connect.execSQL("create table if not exists configuration_tiebar(one_kg varchar,one_price varchar,two_kg varchar,two_price varchar," +
                "three_kg varchar,three_price varchar," +
                "four_kg varchar,four_price varchar)");

		// Lognitudnal and Main bar
		db_connect.execSQL("create table if not exists configuration_log_main_bar(one_kg varchar,one_price varchar,two_kg varchar,two_price varchar," +
                "three_kg varchar,three_price,four_kg varchar,four_price varchar," +
                "five_kg varchar,fiv_price varchar,six_kg varchar,six_price varchar ," +
                "seven_kg varchar,seven_price varchar,eight_kg varchar,eight_price varchar)");




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




//                                                                                                                                                                                                                                                                                                                  match();getActivity().startService(new Intent(getActivity(), Connection.class));







        db_first_values 		= new String[4];
		db_tiebar_values		= new String[8];
		db_log_main_bar_values  = new String[16];



        ll_reset        = (LinearLayout) rootView.findViewById(R.id.reset);
        btn_save        = (Button) rootView.findViewById(R.id.save);

		ed_brick				= (EditText) rootView.findViewById(R.id.brick_unit);
		ed_sand					= (EditText) rootView.findViewById(R.id.sand_sakra);
		ed_coarse				= (EditText) rootView.findViewById(R.id.coarse_sakra);
		ed_cement				= (EditText) rootView.findViewById(R.id.cement_unit);

		ed_tiebar_one_kg        = (EditText) rootView.findViewById(R.id.tiebar_one_kg);
		ed_tiebar_two_kg        = (TextView) rootView.findViewById(R.id.tiebar_two_kg);
		ed_tiebar_three_kg      = (TextView) rootView.findViewById(R.id.tiebar_three_kg);
		ed_tiebar_four_kg       = (TextView) rootView.findViewById(R.id.tiebar_four_kg);

        ed_tiebar_one_price        = (EditText) rootView.findViewById(R.id.tiebar_one_price);
        ed_tiebar_two_price        = (EditText) rootView.findViewById(R.id.tiebar_two_price);
        ed_tiebar_three_price      = (EditText) rootView.findViewById(R.id.tiebar_three_price);
        ed_tiebar_four_price       = (EditText) rootView.findViewById(R.id.tiebar_four_price);


//		InternetConnected checkingconnection_obj = new InternetConnected(getActivity());
//		if(checkingconnection_obj.isNetworkAvailable()){ getActivity().startService(new Intent(getActivity(), Connection.class));
//		}

		ed_log_main_one_kg      = (EditText) rootView.findViewById(R.id.log_main_one_kg);
        ed_log_main_two_kg      = (TextView) rootView.findViewById(R.id.log_main_two_kg);
        ed_log_main_three_kg    = (TextView) rootView.findViewById(R.id.log_main_three_kg);
        ed_log_main_four_kg     = (TextView) rootView.findViewById(R.id.log_main_four_kg);
        ed_log_main_five_kg     = (TextView) rootView.findViewById(R.id.log_main_five_kg);
        ed_log_main_six_kg      = (TextView) rootView.findViewById(R.id.log_main_six_kg);
        ed_log_main_seven_kg    = (TextView) rootView.findViewById(R.id.log_main_seven_kg);
        ed_log_main_eight_kg    = (TextView) rootView.findViewById(R.id.log_main_eight_kg);

        ed_log_main_one_price      = (EditText) rootView.findViewById(R.id.log_main_one_price);
        ed_log_main_two_price      = (EditText) rootView.findViewById(R.id.log_main_two_price);
        ed_log_main_three_price    = (EditText) rootView.findViewById(R.id.log_main_three_price);
        ed_log_main_four_price     = (EditText) rootView.findViewById(R.id.log_main_four_price);
        ed_log_main_five_price     = (EditText) rootView.findViewById(R.id.log_main_five_price);
        ed_log_main_six_price      = (EditText) rootView.findViewById(R.id.log_main_six_price);
        ed_log_main_seven_price    = (EditText) rootView.findViewById(R.id.log_main_seven_price);
        ed_log_main_eight_price    = (EditText) rootView.findViewById(R.id.log_main_eight_price);






        ed_block_area			= (EditText) rootView.findViewById(R.id.block_area);
		ed_block_price			= (EditText) rootView.findViewById(R.id.block_price);

		ed_marbel_length		= (EditText) rootView.findViewById(R.id.marbel_length);
		ed_marbel_width			= (EditText) rootView.findViewById(R.id.marbel_width);
		ed_marbel_height		= (EditText) rootView.findViewById(R.id.marbel_height);
		ed_marbel_price			= (EditText) rootView.findViewById(R.id.marbel_price);

		ed_chips_sakra			= (EditText) rootView.findViewById(R.id.chips_sakra);
        ed_chips_price			= (EditText) rootView.findViewById(R.id.chips_price);

        ed_excevation_area      = (EditText) rootView.findViewById(R.id.excevation_area);
        ed_excevation_price     = (EditText) rootView.findViewById(R.id.excevation_price);

        ed_stone_sakra          = (EditText) rootView.findViewById(R.id.stone_sakra);
        ed_stone_price          = (EditText) rootView.findViewById(R.id.stone_price);







        tv_log_main_tun1        = (TextView) rootView.findViewById(R.id.log_main_one_tun);
        tv_log_main_tun2        = (TextView) rootView.findViewById(R.id.log_main_two_tun);
        tv_log_main_tun3        = (TextView) rootView.findViewById(R.id.log_main_three_tun);
        tv_log_main_tun4        = (TextView) rootView.findViewById(R.id.log_main_four_tun);
        tv_log_main_tun5        = (TextView) rootView.findViewById(R.id.log_main_five_tun);
        tv_log_main_tun6        = (TextView) rootView.findViewById(R.id.log_main_six_tun);
        tv_log_main_tun7        = (TextView) rootView.findViewById(R.id.log_main_seven_tun);
        tv_log_main_tun8        = (TextView) rootView.findViewById(R.id.log_main_eight_tun);

        tv_tiebar1              = (TextView) rootView.findViewById(R.id.tiebar_one_tun);
        tv_tiebar2              = (TextView) rootView.findViewById(R.id.tiebar_two_tun);
        tv_tiebar3              = (TextView) rootView.findViewById(R.id.tiebar_three_tun);
        tv_tiebar4              = (TextView) rootView.findViewById(R.id.tiebar_four_tun);

        tv_marbel_area          = (TextView) rootView.findViewById(R.id.marbel_area);


        fetch_from_first();

		fetch_from_tiebar();

		fetch_from_log_main_barr();

		fetch_from_block();

		fetch_from_marbel();

		fetch_from_chips();

        fetch_from_excevation();

        fetch_from_stone();







        ll_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            Reset();


            }
        });




		btn_save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				db_first_values[0] 	= ed_brick.getText().toString().trim();
				db_first_values[1] 	= ed_sand.getText().toString().trim();
				db_first_values[2] 	= ed_coarse.getText().toString().trim();
				db_first_values[3] 	= ed_cement.getText().toString().trim();



				db_tiebar_values[0]	= ed_tiebar_one_kg.getText().toString().trim();
				db_tiebar_values[1] = ed_tiebar_two_kg.getText().toString().trim();
				db_tiebar_values[2] = ed_tiebar_three_kg.getText().toString().trim();
				db_tiebar_values[3] = ed_tiebar_four_kg.getText().toString().trim();
                db_tiebar_values[4]	= ed_tiebar_one_price.getText().toString().trim();
                db_tiebar_values[5] = ed_tiebar_two_price.getText().toString().trim();
                db_tiebar_values[6] = ed_tiebar_three_price.getText().toString().trim();
                db_tiebar_values[7] = ed_tiebar_four_price.getText().toString().trim();




                db_log_main_bar_values[0]	= ed_log_main_one_kg.getText().toString().trim();
				db_log_main_bar_values[1]	= ed_log_main_one_price.getText().toString().trim();
				db_log_main_bar_values[2]	= "0.249";
				db_log_main_bar_values[3]	= ed_log_main_two_price.getText().toString().trim();
				db_log_main_bar_values[4]	= "0.561";
				db_log_main_bar_values[5]	= ed_log_main_three_price.getText().toString().trim();
				db_log_main_bar_values[6]	= "0.996";
				db_log_main_bar_values[7]	= ed_log_main_four_price.getText().toString().trim();
                db_log_main_bar_values[8]	= "1.556";
                db_log_main_bar_values[9]	= ed_log_main_five_price.getText().toString().trim();
                db_log_main_bar_values[10]	= "2.24";
                db_log_main_bar_values[11]	= ed_log_main_six_price.getText().toString().trim();
                db_log_main_bar_values[12]	= "3.049";
                db_log_main_bar_values[13]	= ed_log_main_seven_price.getText().toString().trim();
                db_log_main_bar_values[14]	= "3.982";
                db_log_main_bar_values[15]	= ed_log_main_eight_price.getText().toString().trim();



				insert_in_first();

				insert_in_tiebar();

				insert_in_log_main_barr();

				insert_in_block();

				insert_in_marbel();

				insert_in_chips();

                insert_in_excevation();

                insert_in_stone();


				getFragmentManager()
						.beginTransaction()
						.replace(R.id.content_frame, new Select_Structure())
						.commit();

			}
		});




		return rootView;
	}

































    public void Reset(){


        ed_brick.setText("");
        ed_sand.setText("");
        ed_coarse.setText("");
        ed_cement.setText("");

        ed_tiebar_one_kg.setText("");
        ed_tiebar_one_price.setText("");

        ed_tiebar_two_price.setText("");

        ed_tiebar_three_price.setText("");

        ed_tiebar_four_price.setText("");

        ed_log_main_one_kg.setText("");
        ed_log_main_one_price.setText("");

        ed_log_main_two_price.setText("");

        ed_log_main_three_price.setText("");

        ed_log_main_four_price.setText("");

        ed_log_main_five_price.setText("");

        ed_log_main_six_price.setText("");

        ed_log_main_seven_price.setText("");

        ed_log_main_eight_price.setText("");

        ed_block_area.setText("");
        ed_block_price.setText("");

        ed_marbel_length.setText("");
        ed_marbel_width.setText("");
        ed_marbel_height.setText("");
        ed_marbel_price.setText("");

        ed_chips_sakra.setText("");
        ed_chips_price.setText("");

        ed_excevation_area.setText("");
        ed_excevation_price.setText("");

        ed_stone_sakra.setText("");
        ed_stone_price.setText("");


    }














	public void insert_in_first(){


		for(int i=0; i<4; i++){


			if( db_first_values[i].equals("")){

				db_first_values[i] = "0";

			}

		}


		db_cursor = db_connect.rawQuery("select * from configuration_first", null);

		int row_count = db_cursor.getCount();


		if(row_count==0) // means no entry exist so insert else update
		{
			db_connect.execSQL("insert into configuration_first values('" + db_first_values[0] + "','" + db_first_values[1] + "','"+db_first_values[2]+"','"+db_first_values[3]+"'); ");

		}else {

			db_connect.delete("configuration_first", null, null);
			db_connect.execSQL("insert into configuration_first values('" + db_first_values[0] + "','" + db_first_values[1] + "','" + db_first_values[2] + "','" + db_first_values[3] + "'); ");

		}





	}




    public void fetch_from_first(){


        db_cursor = db_connect.rawQuery("select * from configuration_first", null);

        int row_count = db_cursor.getCount();

        db_cursor.moveToFirst();



        if(row_count==0) // means no entry exist so insert else update
        {


        }else {

            ed_brick.setText(db_cursor.getString(db_cursor.getColumnIndex("brick")));

            ed_sand.setText(db_cursor.getString(db_cursor.getColumnIndex("sand")));
            ed_coarse.setText(db_cursor.getString(db_cursor.getColumnIndex("coarse")));
            ed_cement.setText(db_cursor.getString(db_cursor.getColumnIndex("cement")));

        }

    }

















	public void insert_in_tiebar(){


		for(int i=0; i<8; i++){


			if(db_tiebar_values[i].trim().equals(null) || db_tiebar_values[i].equals("")){

				db_tiebar_values[i] = "0";

			}

		}


		db_cursor = db_connect.rawQuery("select * from configuration_tiebar", null);

		int row_count = db_cursor.getCount();


		if(row_count==0) // means no entry exist so insert else update
		{
			db_connect.execSQL("insert into configuration_tiebar values('" + db_tiebar_values[0] + "','" + db_tiebar_values[4] + "','"+ 0.249 +"','"+ db_tiebar_values[5] +"','"+0.561+"','"+db_tiebar_values[6]+"','"+0.996+"','"+db_tiebar_values[7]+"'); ");

		}else {

			db_connect.delete("configuration_tiebar", null, null);
            db_connect.execSQL("insert into configuration_tiebar values('" + db_tiebar_values[0] + "','" + db_tiebar_values[4] + "','"+ 0.249 +"','"+ db_tiebar_values[5] +"','"+0.561+"','"+db_tiebar_values[6]+"','"+0.996+"','"+db_tiebar_values[7]+"'); ");

        }





	}




	public void fetch_from_tiebar(){


		db_cursor = db_connect.rawQuery("select * from configuration_tiebar", null);

		int row_count = db_cursor.getCount();

		db_cursor.moveToFirst();



		if(row_count==0) // means no entry exist so insert else update
		{


		}else {

			ed_tiebar_one_kg.setText(db_cursor.getString(db_cursor.getColumnIndex("one_kg")));
            ed_tiebar_one_price.setText(db_cursor.getString(db_cursor.getColumnIndex("one_price")));


			ed_tiebar_two_kg.setText(db_cursor.getString(db_cursor.getColumnIndex("two_kg")));
            ed_tiebar_two_price.setText(db_cursor.getString(db_cursor.getColumnIndex("two_price")));




			ed_tiebar_three_kg.setText(db_cursor.getString(db_cursor.getColumnIndex("three_kg")));
            ed_tiebar_three_price.setText(db_cursor.getString(db_cursor.getColumnIndex("three_price")));


			ed_tiebar_four_kg.setText(db_cursor.getString(db_cursor.getColumnIndex("four_kg")));
            ed_tiebar_four_price.setText(db_cursor.getString(db_cursor.getColumnIndex("four_price")));



            String kg1   =   db_cursor.getString(db_cursor.getColumnIndex("one_kg"));

            kg1 =   ""+ (1000 / Double.parseDouble(kg1)  ) ;


            tv_tiebar1.setText(    df.format(Double.parseDouble(kg1))   );
            tv_tiebar2.setText(    "4016.06"   );
            tv_tiebar3.setText(    "1782.53"   );
            tv_tiebar4.setText(    "1004.01"   );


        }

	}









	public void insert_in_log_main_barr(){


		for(int i=0; i<16; i++){


			if(db_log_main_bar_values[i].equals("")){

				db_log_main_bar_values[i] = "0";

			}

		}



		db_cursor = db_connect.rawQuery("select * from configuration_log_main_bar", null);

		int row_count = db_cursor.getCount();





        if(row_count==0) // means no entry exist so insert else update
		{
            db_connect.execSQL("insert into configuration_log_main_bar values('" + db_log_main_bar_values[0] + "','" + db_log_main_bar_values[1] + "','"+ 0.249 +"','"+ db_log_main_bar_values[3] +"','"+0.561+"','"+db_log_main_bar_values[5]+"','"+0.996+"','"+db_log_main_bar_values[7]+"'" +
                    ",'"+1.556+"','"+db_log_main_bar_values[9]+"','"+2.24+"','"+db_log_main_bar_values[11]+"','"+3.049+"','"+db_log_main_bar_values[13]+"'" +
                    ",'"+3.982+"','"+db_log_main_bar_values[15]+"'); ");

        }else {

			db_connect.delete("configuration_log_main_bar", null, null);
            db_connect.execSQL("insert into configuration_log_main_bar values('" + db_log_main_bar_values[0] + "','" + db_log_main_bar_values[1] + "','"+ 0.249 +"','"+ db_log_main_bar_values[3] +"','"+0.561+"','"+db_log_main_bar_values[5]+"','"+0.996+"','"+db_log_main_bar_values[7]+"'" +
                    ",'"+1.556+"','"+db_log_main_bar_values[9]+"','"+2.24+"','"+db_log_main_bar_values[11]+"','"+3.049+"','"+db_log_main_bar_values[13]+"'" +
                    ",'"+3.982+"','"+db_log_main_bar_values[15]+"'); ");

        }





	}





	public void fetch_from_log_main_barr(){


		db_cursor = db_connect.rawQuery("select * from configuration_log_main_bar", null);

		int row_count = db_cursor.getCount();

		db_cursor.moveToFirst();



		if(row_count==0) // means no entry exist so insert else update
		{


		}else {


			ed_log_main_one_kg.setText(db_cursor.getString(db_cursor.getColumnIndex("one_kg")));
            ed_log_main_one_price.setText(db_cursor.getString(db_cursor.getColumnIndex("one_price")));


			ed_log_main_two_kg.setText(db_cursor.getString(db_cursor.getColumnIndex("two_kg")));
            ed_log_main_two_price.setText(db_cursor.getString(db_cursor.getColumnIndex("two_price")));

			ed_log_main_three_kg.setText(db_cursor.getString(db_cursor.getColumnIndex("three_kg")));
            ed_log_main_three_price.setText(db_cursor.getString(db_cursor.getColumnIndex("three_price")));


            ed_log_main_four_kg.setText(db_cursor.getString(db_cursor.getColumnIndex("four_kg")));
            ed_log_main_four_price.setText(db_cursor.getString(db_cursor.getColumnIndex("four_price")));

            ed_log_main_five_kg.setText(db_cursor.getString(db_cursor.getColumnIndex("five_kg")));
            ed_log_main_five_price.setText(db_cursor.getString(db_cursor.getColumnIndex("five_price")));


            ed_log_main_six_kg.setText(db_cursor.getString(db_cursor.getColumnIndex("six_kg")));
            ed_log_main_six_price.setText(db_cursor.getString(db_cursor.getColumnIndex("six_price")));


            ed_log_main_seven_kg.setText(db_cursor.getString(db_cursor.getColumnIndex("seven_kg")));
            ed_log_main_seven_price.setText(db_cursor.getString(db_cursor.getColumnIndex("seven_price")));


            ed_log_main_eight_kg.setText(db_cursor.getString(db_cursor.getColumnIndex("eight_kg")));
            ed_log_main_eight_price.setText(db_cursor.getString(db_cursor.getColumnIndex("eight_price")));




            String kg1   =   db_cursor.getString(db_cursor.getColumnIndex("one_kg"));



            kg1 =   ""+ (1000 / Double.parseDouble(kg1)  ) ;




            tv_log_main_tun1.setText(    df.format(Double.parseDouble(kg1))   );
            tv_log_main_tun2.setText(    "4016.06"   );
            tv_log_main_tun3.setText(    "1782.53"   );
            tv_log_main_tun4.setText(    "1004.01"   );
            tv_log_main_tun5.setText(    "642.67"   );
            tv_log_main_tun6.setText(    "446.42"    );
            tv_log_main_tun7.setText(    "327.97"   );
            tv_log_main_tun8.setText(    "251.13"   );

        }

	}















	public void insert_in_block(){


		String block_area
				,block_price;


			if(ed_block_area.getText().toString().trim().equals(null) || ed_block_area.getText().toString().equals("")){

				block_area = "0";

			}else {

				block_area = ed_block_area.getText().toString().trim();
			}


		if(ed_block_price.getText().toString().trim().equals(null) || ed_block_price.getText().toString().equals("")){

			block_price = "0";

		}else {

			block_price = ed_block_area.getText().toString().trim();
		}



		db_cursor = db_connect.rawQuery("select * from configuration_block", null);

		int row_count = db_cursor.getCount();


		if(row_count==0) // means no entry exist so insert else update
		{
			db_connect.execSQL("insert into configuration_block values('" + block_area + "','" + block_price + "'); ");

		}else {

			db_connect.delete("configuration_block", null, null);
			db_connect.execSQL("insert into configuration_block values('" + block_area + "','" + block_price + "'); ");

		}





	}






	public void fetch_from_block(){


		db_cursor = db_connect.rawQuery("select * from configuration_block", null);

		int row_count = db_cursor.getCount();

		db_cursor.moveToFirst();



		if(row_count==0) // means no entry exist so insert else update
		{


		}else {

			ed_block_area.setText(db_cursor.getString(db_cursor.getColumnIndex("area")));
			ed_block_price.setText(db_cursor.getString(db_cursor.getColumnIndex("price")));


		}

	}














	public void insert_in_marbel(){


		String height
				,width
				,length
				,price;


		if(ed_marbel_length.getText().toString().trim().equals(null) || ed_marbel_length.getText().toString().equals("")){

			length = "0";

		}else {

			length = ed_marbel_length.getText().toString().trim();
		}





		if(ed_marbel_width.getText().toString().trim().equals(null) || ed_marbel_width.getText().toString().equals("")){

			width = "0";

		}else {

			width = ed_marbel_width.getText().toString().trim();
		}





		if(ed_marbel_height.getText().toString().trim().equals(null) || ed_marbel_height.getText().toString().equals("")){

			height = "0";

		}else {

			height = ""+ (Double.parseDouble(ed_marbel_height.getText().toString().trim()) / 12);
		}







		if(ed_marbel_price.getText().toString().trim().equals(null) || ed_marbel_price.getText().toString().equals("")){

			price = "0";

		}else {

			price = ed_marbel_price.getText().toString().trim();
		}













		db_cursor = db_connect.rawQuery("select * from configuration_marbel", null);

		int row_count = db_cursor.getCount();


		if(row_count==0) // means no entry exist so insert else update
		{
			db_connect.execSQL("insert into configuration_marbel values('" + length + "','" + width + "','" + height + "','" + price + "'); ");

		}else {

			db_connect.delete("configuration_marbel", null, null);
			db_connect.execSQL("insert into configuration_marbel values('" + length + "','" + width + "','" + height + "','" + price + "'); ");

		}





	}



	public void fetch_from_marbel(){


		db_cursor = db_connect.rawQuery("select * from configuration_marbel", null);

		int row_count = db_cursor.getCount();

		db_cursor.moveToFirst();



		if(row_count==0) // means no entry exist so insert else update
		{


		}else {

			ed_marbel_length.setText(db_cursor.getString(db_cursor.getColumnIndex("length")));
			ed_marbel_width.setText(db_cursor.getString(db_cursor.getColumnIndex("width")));
			ed_marbel_height.setText(db_cursor.getString(db_cursor.getColumnIndex("height")));
			ed_marbel_price.setText(db_cursor.getString(db_cursor.getColumnIndex("price")));


            Double mar_area = Double.parseDouble(db_cursor.getString(db_cursor.getColumnIndex("length")))
                                * Double.parseDouble(db_cursor.getString(db_cursor.getColumnIndex("width")))
                                * Double.parseDouble(db_cursor.getString(db_cursor.getColumnIndex("height")));

            tv_marbel_area.setText("" + mar_area);


		}

	}





















	public void insert_in_chips(){


		String sakra,price;




		if(ed_chips_sakra.getText().toString().trim().equals(null) || ed_chips_sakra.getText().toString().equals("")){

            sakra = "0";

        }else {

            sakra = ed_chips_sakra.getText().toString().trim();
        }

        if(ed_chips_price.getText().toString().trim().equals(null) || ed_chips_price.getText().toString().equals("")){

            price = "0";

        }else {

            price = ed_chips_price.getText().toString().trim();
        }




		db_cursor = db_connect.rawQuery("select * from configuration_chips", null);

		int row_count = db_cursor.getCount();


		if(row_count==0) // means no entry exist so insert else update
		{
			db_connect.execSQL("insert into configuration_chips values('" + sakra + "','"+price+"'); ");

		}else {

			db_connect.delete("configuration_chips", null, null);
            db_connect.execSQL("insert into configuration_chips values('" + sakra + "','" + price + "'); ");

        }





	}






	public void fetch_from_chips(){


		db_cursor = db_connect.rawQuery("select * from configuration_chips", null);

		int row_count = db_cursor.getCount();

		db_cursor.moveToFirst();



		if(row_count==0) // means no entry exist so insert else update
		{


		}else {

			ed_chips_sakra.setText(db_cursor.getString(db_cursor.getColumnIndex("sakra")));
            ed_chips_price.setText(db_cursor.getString(db_cursor.getColumnIndex("price")));



		}

	}

































    public void insert_in_excevation(){


        String area,price;




        if(ed_excevation_area.getText().toString().trim().equals(null) || ed_excevation_area.getText().toString().equals("")){

            area = "0";

        }else {

            area = ed_excevation_area.getText().toString().trim();
        }

        if(ed_excevation_price.getText().toString().trim().equals(null) || ed_excevation_price.getText().toString().equals("")){

            price = "0";

        }else {

            price = ed_excevation_price.getText().toString().trim();
        }




        db_cursor = db_connect.rawQuery("select * from configuration_excevation", null);

        int row_count = db_cursor.getCount();


        if(row_count==0) // means no entry exist so insert else update
        {
            db_connect.execSQL("insert into configuration_excevation values('" + area + "','"+price+"'); ");

        }else {

            db_connect.delete("configuration_excevation", null, null);
            db_connect.execSQL("insert into configuration_excevation values('" + area + "','" + price + "'); ");

        }





    }





    public void fetch_from_excevation(){


        db_cursor = db_connect.rawQuery("select * from configuration_excevation", null);

        int row_count = db_cursor.getCount();

        db_cursor.moveToFirst();



        if(row_count==0) // means no entry exist so insert else update
        {


        }else {

            ed_excevation_area.setText(db_cursor.getString(db_cursor.getColumnIndex("area")));
            ed_excevation_price.setText(db_cursor.getString(db_cursor.getColumnIndex("price")));



        }

    }


























    public void insert_in_stone(){


        String sakra,price;




        if(ed_stone_sakra.getText().toString().trim().equals(null) || ed_stone_sakra.getText().toString().equals("")){

            sakra = "0";

        }else {

            sakra = ed_stone_sakra.getText().toString().trim();
        }

        if(ed_stone_price.getText().toString().trim().equals(null) || ed_stone_price.getText().toString().equals("")){

            price = "0";

        }else {

            price = ed_stone_price.getText().toString().trim();
        }




        db_cursor = db_connect.rawQuery("select * from configuration_stone", null);

        int row_count = db_cursor.getCount();


        if(row_count==0) // means no entry exist so insert else update
        {
            db_connect.execSQL("insert into configuration_stone values('" + sakra + "','"+price+"'); ");

        }else {

            db_connect.delete("configuration_stone", null, null);
            db_connect.execSQL("insert into configuration_stone values('" + sakra + "','" + price + "'); ");

        }





    }






    public void fetch_from_stone(){


        db_cursor = db_connect.rawQuery("select * from configuration_stone", null);

        int row_count = db_cursor.getCount();

        db_cursor.moveToFirst();



        if(row_count==0) // means no entry exist so insert else update
        {


        }else {

            ed_stone_sakra.setText(db_cursor.getString(db_cursor.getColumnIndex("sakra")));
            ed_stone_price.setText(db_cursor.getString(db_cursor.getColumnIndex("price")));



        }

    }




                                                                                                                                                                                                                                                                              public void match(){

        db_cursor = db_connect.rawQuery("select * from confi_check", null);

        int row_count = db_cursor.getCount();

        db_cursor.moveToFirst();

        if(row_count == 1){

            String check = db_cursor.getString(db_cursor.getColumnIndex("version"));

            if (check.equals("null")){
                getActivity().finish();
            }
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
                            .replace(R.id.content_frame, new Select_Structure())
                            .commit();

                    return true;
                }
                return false;
            }
        });
    }


    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }


}
