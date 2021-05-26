package mx.cdac.habitsgarden;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class PopActivityRosas extends Activity {

    Button close_btn;
    Button open_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_rosas);
        close_btn=(Button)  findViewById(R.id.okbtnRosas);
        open_btn=(Button) findViewById(R.id.Open);

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        open_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri link =Uri.parse("https://drive.google.com/file/d/1j6hWgALWfcS4RpfIS19GttMb68CyCl4Z/view?usp=sharing");
                Intent rosalink= new Intent(Intent.ACTION_VIEW, link);
                startActivity(rosalink);

            }
        });

        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.7));

        WindowManager.LayoutParams params= getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
        params.x=0;
        params.y=-20;
        getWindow().setAttributes(params);


        ActionBar actionBar = getActionBar();
        actionBar.hide();

    }


}