package mx.cdac.habitsgarden;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityTimer extends AppCompatActivity {

    private TextView countdownText;
    private Button countdownBtn;
    private Button cancelBtn;
    private ProgressBar progressBar;


    private CountDownTimer countDownTimer;

    private boolean timeRunning;

    private String TIEMPO;
    private long timeLeftinMs;//25 minutos
    private long time;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // Intent intent=;
        //onStartCommand(intent);
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int duracion =Integer.valueOf(intent.getStringExtra("DURACION"));

        timeLeftinMs=(duracion*60)*1000;
        time=timeLeftinMs;

        setContentView(R.layout.activity_pomodoro);
        countdownText = findViewById(R.id.countdown_text);
        countdownText.setText(Integer.toString(duracion)+":00");
        countdownBtn= findViewById(R.id.buttonComenzarPomodoro);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setProgress(100);

        cancelBtn=findViewById(R.id.cancel_button);
        cancelBtn.setEnabled(false);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),PromoActivity.class);
                startActivity(intent);
                stopTimer();

            }
        });
        Log.println(Log.ASSERT,"MESSAGE2","timeleftinMS"+timeLeftinMs);
    }



    public void ComenzarPomodoro(View view) {
        Intent intent = null;

        intent = new Intent(this, ServicioP.class);
        intent.putExtra("DURACION", "20");
        intent.putExtra("ACTIVITY", "TRABAJO");
        startService(intent);
        intent.putExtra("DURACION", "5");
        intent.putExtra("ACTIVITY", "DESCANSO");
        startService(intent);
        Log.println(Log.ASSERT,"MESSAGE","Pos le diste click al pomodoro");


        // mostrarNotificacion();
        startStop();



    }
    private void startStop() {
        if(timeRunning){
            stopTimer();
        }else {
            startTimer();
        }
    }
    private void startTimer() {
        countDownTimer=new CountDownTimer(timeLeftinMs,1000) {
            @Override
            public void onTick(long l) {
                timeLeftinMs=l;
                updateTimer();
                updateProgressBar();
                Log.println(Log.ASSERT,"TIEMPO","FALTA"+l);

            }

            @Override
            public void onFinish() {

            }
        }.start();
        timeRunning = true;
        cancelBtn.setEnabled(true);
        countdownBtn.setEnabled(false);
    }
    private void updateProgressBar() {
        Log.println(Log.ASSERT,"MESSAGE2","ms : "+time);
        int percentage= (int )((timeLeftinMs*100)/time);

        progressBar.setProgress(percentage);


    }

    private void updateTimer() {
        int minutes=(int) timeLeftinMs / 60000;
        int seconds=(int) timeLeftinMs % 60000 / 1000;

        String timeLeftText;

        timeLeftText=""+minutes;
        timeLeftText+=":";
        if(seconds<10) timeLeftText +="0";
        timeLeftText +=seconds;

        countdownText.setText(timeLeftText);


    }
    private void stopTimer() {
        countDownTimer.cancel();
        timeRunning=false;
        progressBar.setProgress(100);
        timeLeftinMs=time;
        //countdownText.setText("1:00");
        countdownBtn.setEnabled(true);
        cancelBtn.setEnabled(false);
    }
}

