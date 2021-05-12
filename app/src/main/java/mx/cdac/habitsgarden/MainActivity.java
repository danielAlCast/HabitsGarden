package mx.cdac.habitsgarden;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    ImageButton btnInfo;
    ImageButton btnInfoBtns;
    ImageButton btnCalendario;
    ImageButton btnHabitos;
    ImageButton btnPomodoro;
    ImageButton btnLogros;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInfo=(ImageButton) findViewById(R.id.infoGardenButton);
        btnInfoBtns=(ImageButton) findViewById(R.id.infobtnsButton);
        btnCalendario=(ImageButton) findViewById(R.id.calendarioButton);
        btnHabitos=(ImageButton) findViewById(R.id.habitosButton);
        btnPomodoro=(ImageButton) findViewById(R.id.pomodoroButton);
        btnLogros=(ImageButton) findViewById(R.id.recompensasButton);


        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),PopActivity.class);
                startActivity(intent);
            }
        });

        btnInfoBtns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),PopInfBtnesActivity.class);
                startActivity(intent);
            }
        });
        btnCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),CalendarioActivity.class);
                startActivity(intent);
            }
        });
        btnHabitos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Habitos.class);
                startActivity(intent);
            }
        });

        btnPomodoro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PomodoroActi.class);
                startActivity(intent);
            }
        });


    }
}