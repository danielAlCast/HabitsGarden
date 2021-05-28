package mx.cdac.habitsgarden;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PomodoroActi extends AppCompatActivity {



    private TextView countdownText;
    private Button countdownBtn;
    private Button cancelBtn;
    private ProgressBar progressBar;


    private CountDownTimer countDownTimer;
    private long timeLeftinMs=60000;//25 minutos
    private boolean timeRunning;
    private long time=timeLeftinMs;


    private boolean relaxTime=false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent;



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);
        countdownText = findViewById(R.id.countdown_text);
        countdownBtn= findViewById(R.id.buttonComenzarPomodoro);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setProgress(100);


       /* cancelBtn=findViewById(R.id.cancel_button);
        cancelBtn.setEnabled(false);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),PromoActivity.class);
                startActivity(intent);
                stopTimer();
                //detenerPomodoro();

            }
        });*/
    }

    public void btninfoPomodoro(View view){
        Intent intent= new Intent(getApplicationContext(),PopPomodorobtn.class);
        startActivity(intent);

    }

    public void detenerPomodoro(View view)
    {
        Intent intent = null;
        Intent intent1=null;
        Stop();
        Log.println(Log.ASSERT,"MESSAGE","Pos le diste click a detener el pomodoro ");
        intent = new Intent(this, ServicioP.class);
        stopService(intent);
        intent1=new Intent(this,PromoActivity.class);
        startActivity(intent1);

    }

    public void ComenzarPomodoro(View view) {
        Intent intent = null;

        intent = new Intent(this, ServicioP.class);
        intent.putExtra("DURACION", "60");
        intent.putExtra("ACTIVITY", "TRABAJO");
        startService(intent);
        intent.putExtra("DURACION", "5");
        intent.putExtra("ACTIVITY", "DESCANSO");
        startService(intent);
        Log.println(Log.ASSERT,"MESSAGE","Pos le diste click al pomodoro");

        // mostrarNotificacion();
        startStop();


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
                Log.println(Log.ASSERT,"TIEMPO","FALTA"+l);
                if(l<=800 && !relaxTime){
                    countdownText.setText("1:00");
                    relaxTime=true;
                    timeLeftinMs=60000;
                    startTimer();

                }

            }

            @Override
            public void onFinish() {

            }
        }.start();
        timeRunning = true;
        // cancelBtn.setEnabled(true);
        countdownBtn.setEnabled(false);
    }
    private void updateProgressBar() {
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
        countdownText.setText("1:00");
        countdownBtn.setEnabled(true);
//        cancelBtn.setEnabled(false);
    }
}