package mx.cdac.habitsgarden;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class Habitos extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpomo);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        //windowActionB
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.insert:
                intent = new Intent(this, InsertActividad.class);
                startActivity(intent);
                return true;
            case R.id.select:
                intent = new Intent(this, consultar.class);
                startActivity(intent);
                return true;
            case R.id.pomodoro:
                intent = new Intent(this, ServicioP.class);
                intent.putExtra("DURACION", "20");
                intent.putExtra("ACTIVITY", "TRABAJO");
                startService(intent);
                intent.putExtra("DURACION", "5");
                intent.putExtra("ACTIVITY", "DESCANSO");
                startService(intent);
                Log.println(Log.ASSERT,"MESSAGE","Pos le diste click al pomodoro");

                // mostrarNotificacion();
            default:
                return super.onOptionsItemSelected(item);
        }
    }





    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }*/
}
