package ntnu.otorrillas.introexercise;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import ntnu.otorrillas.introexercise.task1.Task1Activity;
import ntnu.otorrillas.introexercise.task2.Task2Activity;
import ntnu.otorrillas.introexercise.task3.Task3Activity;
import ntnu.otorrillas.introexercise.task4.Task4Activity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    public void launchTask1(View v) {
        Intent i = new Intent(getApplicationContext(), Task1Activity.class);
        startActivity(i);
    }

    public void launchTask2(View v) {
        Intent i = new Intent(getApplicationContext(), Task2Activity.class);
        startActivity(i);
    }

    public void launchTask3(View v) {
        Intent i = new Intent(getApplicationContext(), Task3Activity.class);
        startActivity(i);
    }

    public void launchTask4(View v) {
        Intent i = new Intent(getApplicationContext(), Task4Activity.class);
        startActivity(i);
    }

}
