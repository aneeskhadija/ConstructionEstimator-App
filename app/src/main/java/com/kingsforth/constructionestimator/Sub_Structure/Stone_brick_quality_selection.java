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


public class Stone_brick_quality_selection extends Fragment {

	Button btn_nxt;
	ImageView img_nxt;

	RadioButton radio_a
			,radio_b
			,radio_c
			,radio_input;

	String check_radio="";

	EditText ed_sand_input
			,ed_cement_input
			;

	TextView tv_for;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.two_quality, container, false);


		btn_nxt			= (Button) rootView.findViewById(R.id.btn_next);
		img_nxt			= (ImageView) rootView.findViewById(R.id.imageView);
		radio_a			= (RadioButton) rootView.findViewById(R.id.radio_a);
		radio_b			= (RadioButton) rootView.findViewById(R.id.radio_b);
		radio_c			= (RadioButton) rootView.findViewById(R.id.radio_c);
		radio_input		= (RadioButton) rootView.findViewById(R.id.radio_input);

		ed_sand_input	= (EditText) rootView.findViewById(R.id.sand_input);
		ed_cement_input	= (EditText) rootView.findViewById(R.id.cement_input);

		tv_for			= (TextView) rootView.findViewById(R.id.quality_for);


		tv_for.setText("For Stone brick..");



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


					}else {
						send_to_nxt_activity();
					}

				}



			}
		});


		return rootView;
	}




    public void send_to_nxt_activity(){



        Stone_brick_enter_values objFragment = new Stone_brick_enter_values();

        Bundle args = new Bundle();


        if(check_radio.equals("a")){


            args.putString("quality_a", "1");
            args.putString("quality_b", "2");



        }else if(check_radio.equals("b")){


            args.putString("quality_a", "1");
            args.putString("quality_b", "3");



        }else if(check_radio.equals("c")){

            args.putString("quality_a", "1");
            args.putString("quality_b", "4");



        }else if(check_radio.equals("input")){

            args.putString("quality_a", ed_cement_input.getText().toString().trim());
            args.putString("quality_b", ed_sand_input.getText().toString().trim());


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
                            .replace(R.id.content_frame, new Foundation())
                            .commit();

                    return true;
                }
                return false;
            }
        });
    }


}
