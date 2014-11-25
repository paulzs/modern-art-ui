package com.example.android.modernartui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends Activity {

    static private final String URL = "http://www.MoMA.org";
    SeekBar sb = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Get data from previous instance if not first session

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize seek bar

        sb = (SeekBar) findViewById(R.id.slider);
        sb.setMax(100);

        //Initialize colored boxes

        final TextView redbox1 = (TextView) findViewById(R.id.redbox1);
        final TextView yellowbox1 = (TextView) findViewById(R.id.yellowbox1);
        final TextView redbox2 = (TextView) findViewById(R.id.redbox2);
        final TextView yellowbox2 = (TextView) findViewById(R.id.yellowbox2);
        final TextView bluebox2 = (TextView) findViewById(R.id.bluebox2);
        final TextView redbox3 = (TextView) findViewById(R.id.redbox3);
        final TextView yellowbox3 = (TextView) findViewById(R.id.yellowbox3);
        final TextView bluebox3 = (TextView) findViewById(R.id.bluebox3);
        //final int redVal = getResources().getColor(R.color.red);
        //final int blueVal = getResources().getColor(R.color.blue);
        //final int yellowVal = getResources().getColor(R.color.yellow);

        //Manage seek bar slide behavior

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            //Target colors
            //Turquoise is #00E5EE R0 G229 B238
            //Orange is #FF6600 R255 G102 B0
            //Purple is #BE00FF R130 G0 B130


            int progChange = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                //Initial color state values

                int[] redArray = {255, 0, 0};
                int[] blueArray = {0, 0, 255};
                int[] yellowArray = {255, 255, 0};

                progChange = progress;

                //Make incremental color value changes

                redArray[0] = redArray[0] - (255/100)*progChange;
                redArray[1] = redArray[1] + (229/100)*progChange;
                redArray[2] = redArray[2] + (238/100)*progChange;
                blueArray[0] = blueArray[0] + (255/100)*progChange;
                blueArray[1] = blueArray[1] + (102/100)*progChange;
                blueArray[2] = blueArray[2] - (255/100)*progChange;
                yellowArray[0] = yellowArray[0] - (125/100)*progChange;
                yellowArray[1] = yellowArray[1] - (255/100)*progChange;
                yellowArray[2] = yellowArray[2] + (130/100)*progChange;

                //Set the boxes to new colors

                redbox1.setBackgroundColor(Color.rgb(redArray[0],redArray[1],redArray[2]));
                redbox2.setBackgroundColor(Color.rgb(redArray[0],redArray[1],redArray[2]));
                redbox3.setBackgroundColor(Color.rgb(redArray[0],redArray[1],redArray[2]));
                bluebox2.setBackgroundColor(Color.rgb(blueArray[0],blueArray[1],blueArray[2]));
                bluebox3.setBackgroundColor(Color.rgb(blueArray[0],blueArray[1],blueArray[2]));
                yellowbox1.setBackgroundColor(Color.rgb(yellowArray[0],yellowArray[1],yellowArray[2]));
                yellowbox2.setBackgroundColor(Color.rgb(yellowArray[0],yellowArray[1],yellowArray[2]));
                yellowbox3.setBackgroundColor(Color.rgb(yellowArray[0],yellowArray[1],yellowArray[2]));

                /*int redValue = redVal + progChange;
                int blueValue = blueVal + progChange;
                int yellowValue = yellowVal + progChange;

                redbox1.setBackgroundColor(redValue);
                redbox2.setBackgroundColor(redValue);
                redbox3.setBackgroundColor(redValue);
                bluebox2.setBackgroundColor(blueValue);
                bluebox3.setBackgroundColor(blueValue);
                yellowbox1.setBackgroundColor(yellowValue);
                yellowbox2.setBackgroundColor(yellowValue);
                yellowbox3.setBackgroundColor(yellowValue);*/

                /*LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast,
                        (ViewGroup) findViewById(R.id.toast_layout_root));
                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setText("Red: " + redArray[0] + "," + redArray[1] + "," + redArray[2] +
                        " Blue: " + blueArray[0] + "," + blueArray[1] + "," + blueArray[2] +
                        " Yellow: " + yellowArray[0] + "," + yellowArray[1] + "," + yellowArray[2]);
                Toast colorToast = new Toast(getApplicationContext());
                colorToast.setView(layout);
                colorToast.show();*/

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_more_info) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            //Initialize and format dialog
            //
            TextView dialog_title = new TextView(this);
            dialog_title.setText(R.string.dialog_title);
            dialog_title.setGravity(Gravity.CENTER_HORIZONTAL);
            dialog_title.setPadding(100,30,100,30);
            dialog_title.setTextSize(20);
            builder.setCustomTitle(dialog_title);

            /*TextView dialog_msg = new TextView(this);
            dialog_msg.setText(R.string.dialog_message);
            dialog_msg.setGravity(Gravity.CENTER_HORIZONTAL);
            dialog_msg.setPadding(10,10,10,10);
            dialog_msg.setTextSize(20);*/
            builder.setMessage(R.string.dialog_message);

            builder.setPositiveButton(R.string.not_now, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });
            builder.setNegativeButton(R.string.visit_moma, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked Visit MOMA button
                    Intent momaIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                    startActivity(momaIntent);
                }
            });

            AlertDialog dialog = builder.show();

            TextView dialog_msg = (TextView) dialog.findViewById(android.R.id.message);

            //dialog_title.setGravity(Gravity.CENTER_HORIZONTAL);
            dialog_msg.setGravity(Gravity.CENTER_HORIZONTAL);
            dialog_msg.setTextSize(14);
            dialog_msg.setPadding(10,60,10,0);

            /*Intent momaIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
            startActivity(momaIntent);
            return true;*/
        }
        return super.onOptionsItemSelected(item);
    }

}
