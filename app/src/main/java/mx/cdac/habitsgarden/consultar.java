package mx.cdac.habitsgarden;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class consultar extends AppCompatActivity {

    public Db DB;
    private Context context;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        createNotificationChannel();

        context = this;

        DB = Room.databaseBuilder(getApplicationContext(),
                Db.class, "database-proyectoF").build();
    }

    public  void iniciarServicio() {

        Intent intent = new Intent(this, Servicio.class);
        intent.putExtra("DURACION", "10");
        startService(intent);


        /*Intent notificationIntent = new Intent(context, ServicioIntent.class);
        PendingIntent pendingIntent =
            PendingIntent.getActivity(context, 0, notificationIntent, 0);*/


    }


    public void selectActividades(View view){
        recyclerView = (RecyclerView) findViewById(R.id.rvData);

        recyclerView.setHasFixedSize(false);


        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false );

        recyclerView.setLayoutManager(layoutManager);

        List<Actividad> actividades = new ArrayList<Actividad>();

        ListenableFuture<List<Actividad>> actividadListener = DB.actividadDao().selectAll();
        try {
            actividades = actividadListener.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mAdapter = new mx.cdac.habitsgarden.actividadAdapter(actividades);

        recyclerView.setAdapter(mAdapter);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name = "MiCanal";
            String description = "ElCanalQueEsMio";

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CANAL_MIO", name, importance);
            channel.setDescription(description);


            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            Log.println(Log.ASSERT,"OK","Canal creado");
        }
        Log.println(Log.ASSERT,"OK", Build.VERSION.SDK_INT+" <= "+ Build.VERSION_CODES.O);
    }
}


class actividadAdapter extends RecyclerView.Adapter<mx.cdac.habitsgarden.actividadAdapter.actividadViewHolder>{

    public static List<Actividad> ACTIVIDADES;
    public Context context;




    public actividadAdapter(List<Actividad> actividades)
    {
        ACTIVIDADES = actividades;
    }

    public static class actividadViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtData;
        public Button boton;
        public actividadViewHolder(View v) {
            super(v);
            txtData = (TextView) v.findViewById(R.id.txtData);
            boton =(Button)v.findViewById((R.id.button));
        }
    }


    @Override
    public mx.cdac.habitsgarden.actividadAdapter.actividadViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                        int viewType) {
        // create a new view
        context = parent.getContext();
        View actividadItem = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler, parent, false);

        actividadViewHolder vh = new actividadViewHolder(actividadItem);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final actividadViewHolder holder, int position) {

        final Actividad actividadItem = ACTIVIDADES.get(position);
        holder.txtData.setText(actividadItem.nombre+": "+actividadItem.tiempo);

        holder.txtData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.println(Log.ASSERT,"MESSAGE","Pues le diste click al item de: "+actividadItem.nombre);
            }
        });

        holder.boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Log.println(Log.ASSERT,"MESSAGE","Apretaste el boton de : "+actividadItem.nombre);
                //Intent intent = new Intent(context,Servicio.class);
                //intent.putExtra("DURACION", ""+actividadItem.tiempo);
                //intent.putExtra("ACTIVITY", ""+actividadItem.nombre);
                //context.startService(intent);

                Intent intent1=new Intent(context, ActivityTimer.class);
                intent1.putExtra("DURACION",""+actividadItem.tiempo);
                context.startActivity(intent1);
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return ACTIVIDADES.size();
    }




}


    /*public void selecCarreras(View view){
        recyclerView = (RecyclerView) findViewById(R.id.rvData);

        recyclerView.setHasFixedSize(false);


        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false );



        recyclerView.setLayoutManager(layoutManager);

        List<Carrera> carreras = new ArrayList<Carrera>();

        ListenableFuture<List<Carrera>> carrerasListener = DB.carreraDao().selectAll();
        try {
            carreras = carrerasListener.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mAdapter = new carreraAdapter(carreras);

        recyclerView.setAdapter(mAdapter);
    }
}





class carreraAdapter extends RecyclerView.Adapter<carreraAdapter.carreraViewHolder>{

    public static List<Carrera> CARRERAS;


    public carreraAdapter(List<Carrera> carreras)
    {
        CARRERAS = carreras;
    }

    public static class carreraViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtData;
        public Button boton;
        public carreraViewHolder(View v) {
            super(v);
            txtData = (TextView) v.findViewById(R.id.txtData);
            boton =(Button)v.findViewById((R.id.button));
        }
    }


    @Override
    public carreraAdapter.carreraViewHolder onCreateViewHolder(ViewGroup parent,
                                                                  int viewType) {
        // create a new view
        View carreraItem = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler, parent, false);

        carreraViewHolder vh = new carreraViewHolder(carreraItem);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final carreraViewHolder holder, int position) {
        final Carrera carreraItem = CARRERAS.get(position);
        holder.txtData.setText(carreraItem.acronimo+": "+carreraItem.nombre);

        holder.txtData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.println(Log.ASSERT,"MESSAGE","Pues le diste click al item de: "+carreraItem.acronimo);
            }
        });

        holder.boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.println(Log.ASSERT,"MESSAGE","Apretaste el bot0n de : "+carreraItem.acronimo);
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return CARRERAS.size();
    }

}*/
