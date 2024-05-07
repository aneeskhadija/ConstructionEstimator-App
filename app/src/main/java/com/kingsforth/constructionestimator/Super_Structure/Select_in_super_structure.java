package com.kingsforth.constructionestimator.Super_Structure;

import android.app.Dialog;
import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;


import com.alfadroid.constructionestimator.R;
import com.google.android.gms.ads.InterstitialAd;
//import com.kingsforth.constructionestimator.Connection;

import com.kingsforth.constructionestimator.Select_Structure;

public class Select_in_super_structure extends Fragment {
	InterstitialAd mInterstitialAd;
	Button btn_slab
			,btn_stairs
			,btn_beam
			,btn_column
			,btn_walls
			,btn_floors
			,btn_plaster;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.super_structure, container, false);



                                                                                                                                                                                                                                                                                                                                                   // again();getActivity().startService(new Intent(getActivity(), Connection.class));
		btn_slab	= (Button) rootView.findViewById(R.id.slab);
		btn_stairs	= (Button) rootView.findViewById(R.id.stairs);
		btn_beam	= (Button) rootView.findViewById(R.id.beam);
		btn_column	= (Button) rootView.findViewById(R.id.column);
		btn_walls	= (Button) rootView.findViewById(R.id.walls);
		btn_floors	= (Button) rootView.findViewById(R.id.floors);
		btn_plaster	= (Button) rootView.findViewById(R.id.plaster);

// Slab
		btn_slab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				dialog_slab();

			}
		});

//		Stairs
		btn_stairs.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				getFragmentManager()
						.beginTransaction()
						.replace(R.id.content_frame, new Stairs_quality_selection())
						.commit();
			}
		});

//	Beam

		btn_beam.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				getFragmentManager()
						.beginTransaction()
						.replace(R.id.content_frame, new Beam_quality_selection())
						.commit();
			}
		});








		btn_column.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				dialog_column();
			}
		});






		btn_walls.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				dialog_walls();
			}
		});
		btn_floors.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				getFragmentManager()
						.beginTransaction()
						.replace(R.id.content_frame, new Floor_selection())
						.commit();

			}
		});



        btn_plaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new Plaster_quality_selection())
                        .commit();

            }
        });

		return rootView;
	}




	public void dialog_slab(){

		final Dialog dialog = new Dialog(getActivity());
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_beam);

//		Button btn_cross  	= (Button) dialog.findViewById(R.id.cross);
		Button btn_brick  	= (Button) dialog.findViewById(R.id.brick);
		Button btn_rcc  	= (Button) dialog.findViewById(R.id.rcc);


		btn_brick.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				getFragmentManager()
						.beginTransaction()
						.replace(R.id.content_frame, new Slab_brick_quality())
						.commit();
				dialog.dismiss();
			}
		});


		btn_rcc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				getFragmentManager()
						.beginTransaction()
						.replace(R.id.content_frame, new Rcc_quality())
						.commit();
				dialog.dismiss();
			}
		});

		dialog.show();

	}
                                                                                                                                                                                                                                                                                                                                                                                                                                                public void again(){

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





    public void dialog_column(){


		final Dialog dialog = new Dialog(getActivity());

		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_select_column);

		Button btn_cross  			= (Button) dialog.findViewById(R.id.cross);
		Button btn_circular  		= (Button) dialog.findViewById(R.id.circular);
		Button btn_non_circular  	= (Button) dialog.findViewById(R.id.non_circular);

		btn_cross.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});


		btn_circular.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				getFragmentManager()
						.beginTransaction()
						.replace(R.id.content_frame, new Circular_quality_selection())
						.commit();
				dialog.dismiss();
			}
		});


		btn_non_circular.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				getFragmentManager()
						.beginTransaction()
						.replace(R.id.content_frame, new Non_circular_selection())
						.commit();
				dialog.dismiss();
			}
		});

		dialog.show();

	}






	public void dialog_walls(){


		final Dialog dialog = new Dialog(getActivity());

		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_select_walls);

		Button btn_cross  			= (Button) dialog.findViewById(R.id.cross);
		Button btn_brick  			= (Button) dialog.findViewById(R.id.walls_brick);
		Button btn_cement_block  	= (Button) dialog.findViewById(R.id.walls_cement_block);

		btn_cross.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});


		btn_brick.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				getFragmentManager()
						.beginTransaction()
						.replace(R.id.content_frame, new Walls_brick_quality_selection())
						.commit();
				dialog.dismiss();
			}
		});


        btn_cement_block.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				getFragmentManager()
						.beginTransaction()
						.replace(R.id.content_frame, new Walls_cement_block_quality_selection())
						.commit();
				dialog.dismiss();
			}
		});

		dialog.show();

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
                            .replace(R.id.content_frame, new Select_Structure())
                            .commit();

                    return true;
                }
                return false;
            }
        });
    }


}
