package mx.cdac.habitsgarden;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PomodoroActi extends AppCompatActivity {


    private TextView countdownTime;
    private Button countdownBtn;

    //private CountDownTimer countDownTimer;
    //private long timeLeftinMs=1500000;//25 minutos
    //private boolean timeRunning;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);
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



    }


}