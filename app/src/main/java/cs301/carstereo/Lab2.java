package cs301.carstereo;

import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.HashMap;


public class Lab2 extends ActionBarActivity implements View.OnLongClickListener{

    public TextView Display;
    public ImageButton stop;
    public ImageButton play;
    public ImageButton pause;
    public Boolean isOn;
    public Boolean isAM;
    public int AM;
    public double FM;
    public TextView AMText;
    public TextView FMText;
    public DecimalFormat precision;
    public int[] amPresets;
    public double[] fmPresets;
    public HashMap<Button, Integer> buttonMap;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2);
        Display =  (TextView)findViewById(R.id.Display);
        stop = (ImageButton)findViewById(R.id.StopButton);
        play = (ImageButton)findViewById(R.id.PlayButton);
        pause = (ImageButton)findViewById(R.id.Pausebutton);
        isOn=true;
        isAM = true;
        AM = 530;
        FM = 88.1;
        AMText = (TextView)findViewById(R.id.AMText);
        FMText = (TextView)findViewById(R.id.FMText);
        AMText.setText("Tuner: " + ""+AM);
        FMText.setText("Tuner: " + ""+FM);
        precision = new DecimalFormat("0.0");
        amPresets =  new int[6];
        buttonMap = new HashMap<Button, Integer>();


        fmPresets = new double[6];
        for(int i = 0; i<6; i++)
        {
            amPresets[i]=550+i*50;
            fmPresets[i]=90.9+(double)i*2;
        }

        setPresets();

        Button b1 = (Button) findViewById(R.id.Preset1);
        b1.setOnLongClickListener(this);
        Button b2 = (Button) findViewById(R.id.Preset2);
        b2.setOnLongClickListener(this);
        Button b3 = (Button) findViewById(R.id.Preset3);
        b3.setOnLongClickListener(this);
        Button b4 = (Button) findViewById(R.id.Preset4);
        b4.setOnLongClickListener(this);
        Button b5 = (Button) findViewById(R.id.Preset5);
        b5.setOnLongClickListener(this);
        Button b6 = (Button) findViewById(R.id.Preset6);
        b6.setOnLongClickListener(this);
        buttonMap.put(b1, 0);
        buttonMap.put(b2, 1);
        buttonMap.put(b3, 2);
        buttonMap.put(b4, 3);
        buttonMap.put(b5, 4);
        buttonMap.put(b6, 5);







    }

    public void setStation(View view)
    {
        Button setText = (Button)view;
        if(isAM)
            AMText.setText("Tuner: " + ""+setText.getText());
        else
            FMText.setText("Tuner: "+ ""+setText.getText());

    }

    private void setPresets()
    {
        Button preset1 = (Button) findViewById(R.id.Preset1);
        Button preset2 = (Button) findViewById(R.id.Preset2);
        Button preset3 = (Button) findViewById(R.id.Preset3);
        Button preset4 = (Button) findViewById(R.id.Preset4);
        Button preset5 = (Button) findViewById(R.id.Preset5);
        Button preset6 = (Button) findViewById(R.id.Preset6);


        if(isAM)
        {

            preset1.setText("" + amPresets[0]);
            preset2.setText("" + amPresets[1]);
            preset3.setText("" + amPresets[2]);
            preset4.setText("" + amPresets[3]);
            preset5.setText("" + amPresets[4]);
            preset6.setText("" + amPresets[5]);
        }

        else
        {
            preset1.setText("" + precision.format(fmPresets[0]));
            preset2.setText("" + precision.format(fmPresets[1]));
            preset3.setText("" + precision.format(fmPresets[2]));
            preset4.setText("" + precision.format(fmPresets[3]));
            preset5.setText("" + precision.format(fmPresets[4]));
            preset6.setText("" + precision.format(fmPresets[5]));



        }

    }

    public void OnOffButton(View view)
    {

        if(isOn)
        {
            AMText.setVisibility(View.INVISIBLE);
            FMText.setVisibility(View.INVISIBLE);
        }

        else
        {
            AMText.setVisibility(View.VISIBLE);
            FMText.setVisibility(View.VISIBLE);
        }

        if(isOn)
        {
            Display.setBackgroundColor(Color.BLACK);
            stop.setBackgroundResource(R.mipmap.black);
            pause.setBackgroundResource(R.mipmap.black);
            play.setBackgroundResource(R.mipmap.black);
        }

        if(!isOn)
        {
            Display.setBackgroundColor(0xff285fff);
            stop.setBackgroundResource(R.mipmap.stop_button);
            pause.setBackgroundResource(R.mipmap.pause_button);
            play.setBackgroundResource(R.mipmap.play_button);
        }
        isOn=!isOn;
    }

    public void ToggleTuner(View view)
    {
        isAM = !isAM;
        setPresets();
    }

    public void increaseTuner(View view)
    {
        if(isAM)
        {
            AM += 10;
            if(AM > 1700)
            {
                AM = 530;
            }
            AMText.setText("Tuner: " + "" + AM);

        }
        if (!isAM)
        {
            FM += 0.2;
            if(FM > 107.9)
            {
                FM = 88.1;
            }
            FMText.setText("Tuner: " + "" + precision.format(FM));
        }


    }

    public void decreaseTuner(View view)
    {
            if(isAM)
            {
                AM -= 10;
                if(AM < 530)
                {
                    AM = 1700;
                }
                AMText.setText("Tuner: " + "" + AM);
            }
            if (!isAM)
            {
                FM -= 0.2;
                if(FM < 88.1)
                {
                    FM = 107.9;
                }
                FMText.setText("Tuner: " + "" + precision.format(FM));
            }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lab2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onLongClick(View v) {
        Button b = (Button)v;
        int index =0;

        for(Button key: buttonMap.keySet())
        {
            if(key.getId()==b.getId())
            {
                index=buttonMap.get(key);
            }
        }

        if(isAM)
        {
            b.setText(""+ AM);
            amPresets[index]=AM;
        }

        else
        {
            fmPresets[index]=FM;
            b.setText("" + precision.format(FM));
        }
        return true;
    }
}
