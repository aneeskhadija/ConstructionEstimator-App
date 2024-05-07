package com.kingsforth.constructionestimator.Sub_Structure;

import android.app.Fragment;
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


public class Stone_percentage_quality_selection extends Fragment {

    Button btn_nxt;
    ImageView img_nxt;

	RadioButton radio_a
			,radio_b
			,radio_c
			,radio_input
            ,radio_two_a
            ,radio_two_b
            ,radio_two_c
            ,radio_two_input;

	String check_radio="";

	EditText ed_sand_input
			,ed_cement_input
            ,ed_crush

            ,ed_two_sand_input
            ,ed_two_cement_input
			;

    String intent_area
            ,intent_percentage
            ,intent_sakra
            ,intent_price;

	TextView tv_for;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.three_and_two_quality, container, false);
        // Getting values through Intent
        intent_area  		    = getArguments().getString("area");
        intent_percentage      	= getArguments().getString("percentage");
        intent_sakra      		= getArguments().getString("sakra");
        intent_price      		= getArguments().getString("price");
        btn_nxt			= (Button) rootView.findViewById(R.id.btn_next);
        img_nxt			= (ImageView) rootView.findViewById(R.id.imageView);
		radio_a			= (RadioButton) rootView.findViewById(R.id.radio_a);
		radio_b			= (RadioButton) rootView.findViewById(R.id.radio_b);
		radio_c			= (RadioButton) rootView.findViewById(R.id.radio_c);
		radio_input		= (RadioButton) rootView.findViewById(R.id.radio_input);

        radio_two_a			= (RadioButton) rootView.findViewById(R.id.radio_two_a);
        radio_two_b			= (RadioButton) rootView.findViewById(R.id.radio_two_b);
        radio_two_c			= (RadioButton) rootView.findViewById(R.id.radio_two_c);
        radio_two_input		= (RadioButton) rootView.findViewById(R.id.radio_two_input);



		ed_sand_input	= (EditText) rootView.findViewById(R.id.sand_input);
        ed_cement_input	= (EditText) rootView.findViewById(R.id.cement_input);
        ed_crush    	= (EditText) rootView.findViewById(R.id.crush_input);

        ed_two_sand_input	= (EditText) rootView.findViewById(R.id.sand_two_input);
        ed_two_cement_input	= (EditText) rootView.findViewById(R.id.cement_two_input);

		tv_for			= (TextView) rootView.findViewById(R.id.quality_for);


		tv_for.setText("For Stone work..");


//******************   Radio Button Clicks   *************************
		radio_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio_a.setChecked(true);
                radio_b.setChecked(false);
                radio_c.setChecked(false);
                radio_input.setChecked(false);

                radio_two_a.setChecked(false);
                radio_two_b.setChecked(false);
                radio_two_c.setChecked(false);
                radio_two_input.setChecked(false);


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

                radio_two_a.setChecked(false);
                radio_two_b.setChecked(false);
                radio_two_c.setChecked(false);
                radio_two_input.setChecked(false);

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

                radio_two_a.setChecked(false);
                radio_two_b.setChecked(false);
                radio_two_c.setChecked(false);
                radio_two_input.setChecked(false);


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

                radio_two_a.setChecked(false);
                radio_two_b.setChecked(false);
                radio_two_c.setChecked(false);
                radio_two_input.setChecked(false);


                check_radio = "input";
            }
        });

        radio_two_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio_a.setChecked(false);
                radio_b.setChecked(false);
                radio_c.setChecked(false);
                radio_input.setChecked(false);

                radio_two_a.setChecked(true);
                radio_two_b.setChecked(false);
                radio_two_c.setChecked(false);
                radio_two_input.setChecked(false);


                check_radio = "n_a";
            }
        });




        radio_two_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio_a.setChecked(false);
                radio_b.setChecked(false);
                radio_c.setChecked(false);
                radio_input.setChecked(false);

                radio_two_a.setChecked(false);
                radio_two_b.setChecked(true);
                radio_two_c.setChecked(false);
                radio_two_input.setChecked(false);

                check_radio = "n_b";
            }
        });



        radio_two_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio_a.setChecked(false);
                radio_b.setChecked(false);
                radio_c.setChecked(false);
                radio_input.setChecked(false);

                radio_two_a.setChecked(false);
                radio_two_b.setChecked(false);
                radio_two_c.setChecked(true);
                radio_two_input.setChecked(false);


                check_radio = "n_c";
            }
        });







        radio_two_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio_a.setChecked(false);
                radio_b.setChecked(false);
                radio_c.setChecked(false);
                radio_input.setChecked(false);

                radio_two_a.setChecked(false);
                radio_two_b.setChecked(false);
                radio_two_c.setChecked(false);
                radio_two_input.setChecked(true);


                check_radio = "n_input";
            }
        });




//******************  End Radio Button Clicks   *************************







		btn_nxt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if(check_radio.equals("")){


					Toast.makeText(getActivity(), "Select Quality to Proceed..", Toast.LENGTH_SHORT).show();

				}else {


                    if(check_radio.equals("input")){


                        if(ed_sand_input.getText().toString().trim().equals("")  || ed_cement_input.getText().toString().trim().equals("") )
                        {
                            Toast.makeText(getActivity(), "Enter values for Selected Quality", Toast.LENGTH_SHORT).show();

                        }else {

                            send_to_nxt_activity();
                        }


                    }else if(check_radio.equals("n_input")){


                        if(ed_two_sand_input.getText().toString().trim().equals("")  || ed_two_cement_input.getText().toString().trim().equals("") )
                        {
                            Toast.makeText(getActivity(), "Enter values for Selected Quality", Toast.LENGTH_SHORT).show();

                        }else {

                            send_to_nxt_activity();
                        }


                    }else {
                        send_to_nxt_activity();
                    }

				}



			}
		});

        img_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check_radio.equals("")){


                    Toast.makeText(getActivity(), "Select Quality to Proceed..", Toast.LENGTH_SHORT).show();

                }else {


                    if(check_radio.equals("input")){


                        if(ed_sand_input.getText().toString().trim().equals("")  || ed_cement_input.getText().toString().trim().equals("") )
                        {
                            Toast.makeText(getActivity(), "Enter values for Selected Quality", Toast.LENGTH_SHORT).show();

                        }else {

                            send_to_nxt_activity();
                        }


                    }else if(check_radio.equals("n_input")){


                        if(ed_two_sand_input.getText().toString().trim().equals("")  || ed_two_cement_input.getText().toString().trim().equals("") )
                        {
                            Toast.makeText(getActivity(), "Enter values for Selected Quality", Toast.LENGTH_SHORT).show();

                        }else {

                            send_to_nxt_activity();
                        }


                    }else {
                        send_to_nxt_activity();
                    }

                }



            }
        });

		return rootView;
	}




    public void send_to_nxt_activity(){


        Stone_percentage_result objFragment = new Stone_percentage_result();


        Bundle args = new Bundle();


        if(check_radio.equals("a")){

            args.putString("for", "three");
            args.putString("area", ""+intent_area);
            args.putString("percentage", intent_percentage);
            args.putString("sakra", intent_sakra);
            args.putString("price", intent_price);

            args.putString("quality_a", "1");
            args.putString("quality_b", "2");
            args.putString("quality_c", "4");


        }else if(check_radio.equals("b")){

            args.putString("for", "three");
            args.putString("area", intent_area);
            args.putString("percentage", intent_percentage);
            args.putString("sakra", intent_sakra);
            args.putString("price", intent_price);

            args.putString("quality_a", "1");
            args.putString("quality_b", "3");
            args.putString("quality_c", "6");



        }else if(check_radio.equals("c")){

            args.putString("for", "three");
            args.putString("area", intent_area);
            args.putString("percentage", intent_percentage);
            args.putString("sakra", intent_sakra);
            args.putString("price", intent_price);

            args.putString("quality_a", "1");
            args.putString("quality_b", "4");
            args.putString("quality_c", "8");



        }else if(check_radio.equals("input")){

            args.putString("for", "three");
            args.putString("area", intent_area);
            args.putString("percentage", intent_percentage);
            args.putString("sakra", intent_sakra);
            args.putString("price", intent_price);

            args.putString("quality_a", ed_cement_input.getText().toString().trim());
            args.putString("quality_b", ed_sand_input.getText().toString().trim());
            args.putString("quality_c", ed_crush.getText().toString().trim());

        }




        if(check_radio.equals("n_a")){


            args.putString("for", "two");
            args.putString("area", intent_area);
            args.putString("percentage", intent_percentage);
            args.putString("sakra", intent_sakra);
            args.putString("price", intent_price);

            args.putString("quality_n_a", "1");
            args.putString("quality_n_b", "2");



        }else if(check_radio.equals("n_b")){

            args.putString("for", "two");
            args.putString("area", intent_area);
            args.putString("percentage", intent_percentage);
            args.putString("sakra", intent_sakra);
            args.putString("price", intent_price);

            args.putString("quality_n_a", "1");
            args.putString("quality_n_b", "3");


        }else if(check_radio.equals("n_c")){

            args.putString("for", "two");
            args.putString("area", intent_area);
            args.putString("percentage", intent_percentage);
            args.putString("sakra", intent_sakra);
            args.putString("price", intent_price);

            args.putString("quality_n_a", "1");
            args.putString("quality_n_b", "4");



        }else if(check_radio.equals("n_input")){

            args.putString("for", "two");
            args.putString("area", intent_area);
            args.putString("percentage", intent_percentage);
            args.putString("sakra", intent_sakra);
            args.putString("price", intent_price);

            args.putString("quality_n_a", ed_two_cement_input.getText().toString().trim());
            args.putString("quality_n_b", ed_two_sand_input.getText().toString().trim());

        }




        objFragment.setArguments(args);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, objFragment)
                .commit();

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
