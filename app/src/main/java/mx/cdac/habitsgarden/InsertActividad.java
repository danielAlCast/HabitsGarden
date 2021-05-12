package mx.cdac.habitsgarden;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class InsertActividad extends AppCompatActivity {

    public Db DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_actividad);
        DB = Room.databaseBuilder(getApplicationContext(),
                Db.class, "database-proyectoF").build();
    }

    public void insertActividad(View view)
    {
        TextView txt1= (TextView)findViewById(R.id.txt1);
        TextView txt2= (TextView)findViewById(R.id.txt2);

        Actividad actividad =  new Actividad();
        actividad.nombre=txt1.getText().toString();
        actividad.tiempo = txt2.getText().toString();

        List<Actividad> actividades = new ArrayList<>();
        actividades.add(actividad);
        DB.actividadDao().insertActividad(actividades);
    }
    public void consultar(View view){
        Intent intent = null;
        intent = new Intent(this, consultar.class);
        startActivity(intent);
        //return true;

        ListenableFuture<List<Actividad>> actividadesListener = DB.actividadDao().selectAll();
        try {
            List<Actividad> actividades = actividadesListener.get();
            for (Actividad actividad:actividades) {
                Log.println(Log.ASSERT, "MESSAGE", actividad.id +": "+actividad.nombre+": "+actividad.tiempo);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
