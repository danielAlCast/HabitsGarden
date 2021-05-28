package mx.cdac.habitsgarden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Recompensas extends AppCompatActivity {
    ImageButton recRosas;
    ImageButton recSuculentas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recompensas);

        recRosas=(ImageButton) findViewById(R.id.RosasRecompensas);
        recSuculentas=(ImageButton) findViewById(R.id.SuculentasRecompensas);

        recRosas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),PopActivityRosas.class);
                startActivity(intent);
            }
        });
                recSuculentas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri link =Uri.parse("https://drive.google.com/file/d/1rgV57VqbEsWzl7-sEm4ZCpc775GE-sVl/view?usp=sharing");
                        Intent rosalink= new Intent(Intent.ACTION_VIEW, link);
                        startActivity(rosalink);
                    }
                }

        );
    }


}