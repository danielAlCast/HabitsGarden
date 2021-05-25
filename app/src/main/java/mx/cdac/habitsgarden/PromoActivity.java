package mx.cdac.habitsgarden;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PromoActivity extends AppCompatActivity {
    Button okaybutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo);

        okaybutton=findViewById(R.id.okay_button);
    }
}
