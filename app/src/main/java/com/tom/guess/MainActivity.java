package com.tom.guess;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView num;
    private TextView count;
    private ImageView result;
    private Button guess;
    private int counter =0;
    int secret = new Random().nextInt(20)+1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        num = findViewById(R.id.number);
        result = findViewById(R.id.imageView);
        count = findViewById(R.id.count);
        guess = findViewById(R.id.button);
        Log.d("Ding","Secret: "+secret);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                secret = new Random().nextInt(20)+1;
                counter =0;
                count.setText(""+counter);
                num.setText("");
                guess.setVisibility(View.VISIBLE);
                result.setVisibility(View.INVISIBLE);
            }
        });
    }


    public void guess(View view){
        counter++;
        count.setText(""+counter);
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reset();
            }
        };
        try {
            int number = Integer.parseInt(num.getText().toString());
            result.setVisibility(View.VISIBLE);
            result.setAlpha(1.0f);
            if (counter > 7) {
                Toast.makeText(MainActivity.this, "Trash", Toast.LENGTH_SHORT).show();
            }
            if (number == secret && counter < 4) {
                Toast.makeText(MainActivity.this, "Genius", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "Bingo", Toast.LENGTH_SHORT).show();
                result.setImageResource(R.drawable.shit);
                result.setVisibility(View.VISIBLE);
            }
            if (number > secret) {
                Toast.makeText(MainActivity.this, "Smaller", Toast.LENGTH_SHORT).show();
                result.setImageResource(R.drawable.haha);
                result.animate().alpha(0.0f).setDuration(1000);
            } else if (number < secret) {
                Toast.makeText(MainActivity.this, "Bigger", Toast.LENGTH_SHORT).show();
                result.setImageResource(R.drawable.haha);
                result.animate().alpha(0.0f).setDuration(1000);
            } else {
                Toast.makeText(MainActivity.this, "Bingo", Toast.LENGTH_SHORT).show();
                result.setImageResource(R.drawable.shit);
                result.setVisibility(View.VISIBLE);
            }
            if (number != secret && counter > 10) {
                result.setImageResource(R.drawable.middle);
                result.animate().alpha(0.0f).setDuration(2000);
                guess.setVisibility(View.INVISIBLE);
            }
        }catch (Exception e){
            counter--;
            count.setText(""+counter);
           new AlertDialog.Builder(MainActivity.this)
                   .setTitle("Don't lie me !!!")
                   .setMessage("You mother fucker")
                   .setPositiveButton("ok",listener)
                   .show();
        }
    }

    private void reset() {
        secret = new Random().nextInt(20)+1;
        counter = 0;
        count.setText(""+counter);
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
