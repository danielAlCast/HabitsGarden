package mx.cdac.habitsgarden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Recompensas extends AppCompatActivity {
    ImageButton recRosas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recompensas);

        recRosas=(ImageButton) findViewById(R.id.RosasRecompensas);

        recRosas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),PopActivityRosas.class);
                startActivity(intent);
            }
        });
    }


}