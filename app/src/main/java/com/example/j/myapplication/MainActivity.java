package com.example.j.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.InputMismatchException;
import java.util.Random;


public class MainActivity extends ActionBarActivity {
    private Button button01,button02;
    private TextView textView01,textView02;
    private EditText editTextIN;
    int iGuestID;
    String show="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button01 = (Button)findViewById(R.id.buttonCreateGuestID);
        button02 = (Button)findViewById(R.id.buttonGuset);
        textView01 = (TextView)findViewById(R.id.textViewGuestID);
        textView02= (TextView)findViewById(R.id.textViewShow);
        editTextIN= (EditText)findViewById(R.id.editTextIN);


        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random gen= new Random();
                iGuestID= 0;
                while(hasDupes(iGuestID= (gen.nextInt(9000) + 1000)));
                String cGuestID=String.valueOf(iGuestID);
                textView01.setText(cGuestID);

            }
        });

        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //textView02.setText(editTextIN.getText().toString());

                int guesses = 0;
                int bulls = 0;
                int cows = 0;

                String guessStr=editTextIN.getText().toString();
                String targetStr=String.valueOf(iGuestID);

                //textView02.setText(guessStr+"= "+targetStr);

               /* if(guessStr.equals(targetStr))
                                {
                                     textView02.setText("GO");
                                }
                                */
                for(int i= 0;i < 4;i++){
                    if(guessStr.charAt(i) == targetStr.charAt(i)){
                        bulls++;
                    }else if(targetStr.contains(guessStr.charAt(i)+"")){
                        cows++;
                    }
                }

                if(bulls == 4)
                {
                    textView02.setText("GOOD");
                }
                else
                {
                    show=show+guessStr+"-"+bulls+"A"+cows+"B ";
                    textView02.setText(show);
                }

                editTextIN.setText("");
            }
        });


    }
    ///hasDupes
    public static boolean hasDupes(int num){
        boolean[] digs = new boolean[10];
        while(num > 0){
            if(digs[num%10]) return true;
            digs[num%10] = true;
            num/= 10;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
