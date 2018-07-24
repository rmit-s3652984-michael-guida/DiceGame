package com.example.diceout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {

    //Player prediction
    int[] prediction;

    //Count for dice roll
    int timesRolled = 0;

    // Field to hold the roll result text
    TextView rollResult;

    // Field to hold the score
    int score;

    // Field to hold random number generator
    Random rand;

    // Fields to hold the die values
    int die1;
    int die2;
    int die3;


    // ArrayList to hold all three die values
    ArrayList<Integer> dice;

    //ArrayList to hold all three dice images
    ArrayList<ImageView>diceImageViews;

    //Field to hold the score text
    TextView scoreText;

//    Button dicePred;

    //Spinner 1
    private Spinner spinner1;
    private static final String[]paths1 = {"1", "2", "3", "4", "5", "6"};

    //Spinner2
    private Spinner spinner2;
    private static final String[]paths2 = {"1", "2", "3", "4", "5", "6"};

    //Spinner3
    private Spinner spinner3;
    private static final String[]paths3 = {"1", "2", "3", "4", "5", "6"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice(view, timesRolled);
            }
        });

        // Set initial score
        score = 0;

        // Create greeting
        Toast.makeText(getApplicationContext(),"Welcome to DiceOut!", Toast.LENGTH_SHORT).show();

        // Link instances to widgets in the activity view
        rollResult = (TextView) findViewById(R.id.rollResult);
        scoreText = (TextView) findViewById(R.id.scoreText);
//        dicePred = (Button) findViewById(R.id.button1);

        // Initialize the random number generator
        rand = new Random();

        // Create ArrayList container for the dice values
        dice = new ArrayList<Integer>();

        ImageView die1Image = (ImageView) findViewById(R.id.die1Image);
        ImageView die2Image = (ImageView) findViewById(R.id.die2Image);
        ImageView die3Image = (ImageView) findViewById(R.id.die3Image);


        diceImageViews = new ArrayList<ImageView>();
        diceImageViews.add(die1Image);
        diceImageViews.add(die2Image);
        diceImageViews.add(die3Image);


        //Stores prediction
        prediction = new int[4];


        //On Action for dicePred
//        dicePred.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                doSomething();
//            }
//        });





        spinner1 = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,paths1);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);

        spinner2 = (Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<String>adapter2 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,paths2);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(this);

        spinner3 = (Spinner)findViewById(R.id.spinner3);
        ArrayAdapter<String>adapter3 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,paths3);

        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter);
        spinner3.setOnItemSelectedListener(this);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

        //Gets value from spinner and converts it to int value to store in the predition array
        prediction[0] = Integer.parseInt(spinner1.getSelectedItem().toString());
        prediction[1] = Integer.parseInt(spinner2.getSelectedItem().toString());
        prediction[2] = Integer.parseInt(spinner3.getSelectedItem().toString());

//        Toast.makeText(parent.getContext(), "Dice 1 Prediction: " + prediction[0], Toast.LENGTH_LONG).show();
//        Toast.makeText(parent.getContext(), "Dice 2 Prediction: " + prediction[1], Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }




//    public void doSomething(){
//
//        Toast.makeText(this, "Dice 1: You Predicted 1",Toast.LENGTH_SHORT).show();
//    }

    public void rollDice(View v, int count) {

       // Toast.makeText(this, "Drop Val: " + dropVal,Toast.LENGTH_SHORT).show();
        if (count == 0) {
            die1 = rand.nextInt(6) + 1;
            String imageName = "die_" + die1 + ".png";
            try {
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream, null);
                diceImageViews.get(0).setImageDrawable(d);

            } catch (IOException e) {
                e.printStackTrace();
            }

            //Checks if prediction was correct
            if (prediction[0] == die1){
                Toast.makeText(this, "You predicted Dice 1 correctly",Toast.LENGTH_SHORT).show();
            }

            ++timesRolled;
        }

        if (count == 1) {
            die2 = rand.nextInt(6) + 1;
            String imageName = "die_" + die2 + ".png";
            try {
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream, null);
                diceImageViews.get(1).setImageDrawable(d);

            } catch (IOException e) {
                e.printStackTrace();
            }

            //Checks if prediction was correct
            if (prediction[1] == die2){
                Toast.makeText(this, "You predicted Dice 2 correctly",Toast.LENGTH_SHORT).show();
            }

            ++timesRolled;
        }

        if (count == 2) {
            die3 = rand.nextInt(6) + 1;
            String imageName = "die_" + die3 + ".png";
            try {
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream, null);
                diceImageViews.get(2).setImageDrawable(d);

            } catch (IOException e) {
                e.printStackTrace();
            }

            String msg;

            if (die1 == die2 && die2 == die3){
                //Tripple score
                int scoreDelta = die1 * 100;
                msg = "You rolled a tripple " + die1 + "! You score " + scoreDelta + " points!";
                score += scoreDelta;
            } else if (die1 == die2 && die1 != die3 || die1 == die3 && die1 != die2 || die2 == die3 && die2 != die1){
                //Double score
                msg = "You rolled a doubles for 50 points!";
                score += 50;
            } else {
                msg = "You didn't score this roll, Try again!";
            }

            if (prediction[2] == die3){
                Toast.makeText(this, "You predicted Dice 3 correctly",Toast.LENGTH_SHORT).show();
            }

            // Update the app to display the result message
            rollResult.setText(msg);
            scoreText.setText("Score: " + score);

            timesRolled = 0;
        }

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
