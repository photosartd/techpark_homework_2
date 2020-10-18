package ru.mail.techpark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finding view by id
        //text view is a view with
        final TextView view = findViewById(R.id.my_text);
        view.setTextColor(Color.RED);

        String s = getString(R.string.my_new_string);
        //for API lower 23
        int color = ResourcesCompat.getColor(getResources(), R.color.colorSuper, getTheme());
        //for API 23 and higher
        //getResources().getColor(R.color.colorSuper, getTheme());
        view.setTextColor(color);

        Button button = findViewById(R.id.counter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementCounter(view);
            }
        });

        Button button2 = findViewById(R.id.counter_decrement);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementCounter(view);
            }
        });
        final Button startActivity = findViewById(R.id.start_activity);
        startActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //packageContext - MainActivity
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra(Constants.MY_DATA_NUMBER, Integer.parseInt(view.getText().toString()));
                startActivity(i);
            }
        });

    }

    private void incrementCounter(TextView viewToIncrement) {
        String currentText = viewToIncrement.getText().toString();
        int currentNumber = Integer.parseInt(currentText);
        viewToIncrement.setText(String.valueOf(currentNumber + 1));
    }

    private void decrementCounter(TextView viewToIncrement) {
        String currentText = viewToIncrement.getText().toString();
        int currentNumber = Integer.parseInt(currentText);
        viewToIncrement.setText(String.valueOf(currentNumber - 1));
    }
}