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
    private int duracion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Intent intent=;
        //onStartCommand(intent);
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        duracion =Integer.valueOf(intent.getStringExtra("DURACION"));


        timeLeftinMs=(duracion*60)*1000;

        Log.println(Log.ASSERT,"MESSAGE","Duracion = "+ timeLeftinMs);
        time=timeLeftinMs;


        setContentView(R.layout.activity_pomodoro);
        countdownText = findViewById(R.id.countdown_text);
        countdownText.setText(Integer.toString(duracion)+":00");
        countdownBtn= findViewById(R.id.buttonComenzarPomodoro);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setProgress(100);

        cancelBtn=findViewById(R.id.cancel_button);
        cancelBtn.setEnabled(false);

    }



    public void ComenzarPomodoro(View view) {
        Intent intent = null;

        intent = new Intent(this, Servicio.class);
        intent.putExtra("DURACION", ""+timeLeftinMs);
        intent.putExtra("ACTIVITY", "TRABAJO");
        startService(intent);
        Log.println(Log.ASSERT,"MESSAGE","Pos le diste click al pomodoro");


        // mostrarNotificacion();
        startStop();

    }


    public void detenerPomodoro(View view)
    {
        Intent intent = null;
        Intent intent1=null;
        Stop();
        Log.println(Log.ASSERT,"MESSAGE","Pos le diste click a detener el pomodoro ");
        intent = new Intent(this, Servicio.class);
        stopService(intent);
        intent1=new Intent(this,PromoActivity.class);
        startActivity(intent1);

    }

    private void Stop() {
        stopTimer();
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
                cancelBtn.setEnabled(true);

            }

            @Override
            public void onFinish() {

            }
        }.start();
        timeRunning = true;
        //cancelBtn.setEnabled(true);
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
        //int duracion =Integer.valueOf(intent.getStringExtra("DURACION"));
        countdownText.setText(Integer.toString(duracion)+":00");
        countdownBtn.setEnabled(true);
        cancelBtn.setEnabled(false);
        // cancelBtn.setEnabled(false);
    }
}


