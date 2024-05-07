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
import android.widget.TextView;
import android.widget.Toast;

import com.alfadroid.constructionestimator.R;


public class Pile_circular_quality_selection extends Fragment {

    Button btn_nxt;
    ImageView img_nxt;

	RadioButton radio_a
			,radio_b
			,radio_c
			,radio_input;

	String check_radio="";

	EditText ed_sand_input
			,ed_cement_input
			,ed_crush_input;

	TextView tv_for;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.three_quality, container, false);




        btn_nxt			= (Button) rootView.findViewById(R.id.btn_next);
        img_nxt			= (ImageView) rootView.findViewById(R.id.imageView);
		radio_a			= (RadioButton) rootView.findViewById(R.id.radio_a);
		radio_b			= (RadioButton) rootView.findViewById(R.id.radio_b);
		radio_c			= (RadioButton) rootView.findViewById(R.id.radio_c);
		radio_input		= (RadioButton) rootView.findViewById(R.id.radio_input);

		ed_sand_input	= (EditText) rootView.findViewById(R.id.sand_input);
		ed_cement_input	= (EditText) rootView.findViewById(R.id.cement_input);
		ed_crush_input	= (EditText) rootView.findViewById(R.id.crush_input);

		tv_for			= (TextView) rootView.findViewById(R.id.quality_for);



		tv_for.setText("For Circular Pile..");

//******************   Radio Button Clicks   *************************
		radio_a.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				radio_a.setChecked(true);
				radio_b.setChecked(false);
				radio_c.setChecked(false);
				radio_input.setChecked(false);

				check_radio = "a";
			}
		});




		radio_b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				radio_a.setChecked(false);
				radio_b.setChecked(true);
				radio_c.setChecked(false);
				radio_input.setChecked(false);
				check_radio = "b";
			}
		});



		radio_c.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				radio_a.setChecked(false);
				radio_b.setChecked(false);
				radio_c.setChecked(true);
				radio_input.setChecked(false);
				check_radio = "c";
			}
		});







		radio_input.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				radio_a.setChecked(false);
				radio_b.setChecked(false);
				radio_c.setChecked(false);
				radio_input.setChecked(true);
				check_radio = "input";
			}
		});

//******************  End Radio Button Clicks   *************************







        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check_radio.equals("")){ // if check radio is empty


                    Toast.makeText(getActivity(), "Select Quality to Proceed..", Toast.LENGTH_SHORT).show();

                }else if (check_radio.equals("input")) {


                    if (ed_sand_input.getText().toString().trim().equals("")) {
                        Toast.makeText(getActivity(), "Enter quality for Sand", Toast.LENGTH_SHORT).show();
                        ed_sand_input.findFocus();

                    } else if (ed_cement_input.getText().toString().trim().equals("")) {
                        Toast.makeText(getActivity(), "Enter quality for Cement", Toast.LENGTH_SHORT).show();
                        ed_cement_input.findFocus();

                    } else if (ed_crush_input.getText().toString().trim().equals("")) {
                        Toast.makeText(getActivity(), "Enter quality for Crush", Toast.LENGTH_SHORT).show();
                        ed_crush_input.findFocus();

                    } else {

                        Pile_circular_enter_values objFragment = new Pile_circular_enter_values();

                        Bundle args = new Bundle();

                        args.putString("quality_a", ed_cement_input.getText().toString().trim());
                        args.putString("quality_b", ed_sand_input.getText().toString().trim());
                        args.putString("quality_c", ed_crush_input.getText().toString().trim());

                        objFragment.setArguments(args);

                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.content_frame, objFragment)
                                .commit();
                    }

                } else {  // if check radio is not empty



                    Pile_circular_enter_values objFragment = new Pile_circular_enter_values();

                    Bundle args = new Bundle();


                    if (check_radio.equals("a")) {


                        args.putString("quality_a", "1");
                        args.putString("quality_b", "2");
                        args.putString("quality_c", "4");


                    } else if (check_radio.equals("b")) {


                        args.putString("quality_a", "1");
                        args.putString("quality_b", "3");
                        args.putString("quality_c", "6");


                    } else if (check_radio.equals("c")) {

                        args.putString("quality_a", "1");
                        args.putString("quality_b", "4");
                        args.putString("quality_c", "8");


                    }


                    objFragment.setArguments(args);

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, objFragment)
                            .commit();



                }

            }
        });

        img_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check_radio.equals("")){ // if check radio is empty

                    Toast.makeText(getActivity(), "Select Quality to Proceed..", Toast.LENGTH_SHORT).show();
                }else if (check_radio.equals("input")) {

                    if (ed_sand_input.getText().toString().trim().equals("")) {
                        Toast.makeText(getActivity(), "Enter quality for Sand", Toast.LENGTH_SHORT).show();
                        ed_sand_input.findFocus();
                    } else if (ed_cement_input.getText().toString().trim().equals("")) {
                        Toast.makeText(getActivity(), "Enter quality for Cement", Toast.LENGTH_SHORT).show();
                        ed_cement_input.findFocus();
                    } else if (ed_crush_input.getText().toString().trim().equals("")) {
                        Toast.makeText(getActivity(), "Enter quality for Crush", Toast.LENGTH_SHORT).show();
                        ed_crush_input.findFocus();
                    } else {
                        Pile_circular_enter_values objFragment = new Pile_circular_enter_values();
                        Bundle args = new Bundle();
                        args.putString("quality_a", ed_cement_input.getText().toString().trim());
                        args.putString("quality_b", ed_sand_input.getText().toString().trim());
                        args.putString("quality_c", ed_crush_input.getText().toString().trim());
                        objFragment.setArguments(args);
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.content_frame, objFragment)
                                .commit();
                    }

                } else {  // if check radio is not empty

                    Pile_circular_enter_values objFragment = new Pile_circular_enter_values();
                    Bundle args = new Bundle();

                    if (check_radio.equals("a")) {
                        args.putString("quality_a", "1");
                        args.putString("quality_b", "2");
                        args.putString("quality_c", "4");
                    } else if (check_radio.equals("b")) {
                        args.putString("quality_a", "1");
                        args.putString("quality_b", "3");
                        args.putString("quality_c", "6");

                    } else if (check_radio.equals("c")) {
                        args.putString("quality_a", "1");
                        args.putString("quality_b", "4");
                        args.putString("quality_c", "8");
                    }
                    objFragment.setArguments(args);
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, objFragment)
                            .commit();
                }

            }
        });








		return rootView;
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

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    // handle back button's click listener

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, new Foundation())
                            .commit();

                    return true;
                }
                return false;
            }
        });
    }



}
