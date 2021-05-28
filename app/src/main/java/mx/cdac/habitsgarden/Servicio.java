package mx.cdac.habitsgarden;


import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Servicio extends Service {
    private Looper serviceLooper;
    private ServiceHandler serviceHandler;
    private Context context;
    private String TIEMPO;
    private String TIM;
    private String NOMBRE;
    private int cont=0;


    public Servicio(){
        context = this;
    }


    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);

        }
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void handleMessage(Message msg) {
            notificacionBarraProgreso();
            stopSelf(msg.arg1);
        }
    }

    @Override
    public void onCreate() {
        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        serviceLooper = thread.getLooper();
        serviceHandler = new ServiceHandler(serviceLooper);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        TIEMPO = intent.getStringExtra("DURACION");
        NOMBRE = intent.getStringExtra("ACTIVITY");

        if (NOMBRE == "DESCANSO")
            cont =1;

        int time = Integer.parseInt(TIEMPO);

        time = time / 60000;
        TIM = String.valueOf(time);

        TIEMPO = String.valueOf(time);

        Toast.makeText(this, "Servicio Iniciado cuenta " +TIM+" Minutos ", Toast.LENGTH_SHORT).show();

        //Toast.makeText(this, "Servicio Iniciado cuenta " , Toast.LENGTH_SHORT).show();

        Message msg = serviceHandler.obtainMessage();
        msg.arg1 = startId;
        serviceHandler.sendMessage(msg);



        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Servicio terminado", Toast.LENGTH_SHORT).show();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void notificacionToast()
    {
        try {
            Intent notificationIntent = new Intent(context, mx.cdac.habitsgarden.Servicio.class);
            PendingIntent pendingIntent =
                    PendingIntent.getActivity(context, 0, notificationIntent, 0);

            Notification notification =
                    new Notification.Builder(context, "CANAL_MIO")
                            .setContentTitle("Anuncio")
                            .setContentText("Info del servicio")
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setContentIntent(pendingIntent)
                            .setTicker("117")
                            .build();

            startForeground(1,notification);

            Thread.sleep(5000);


            int t = Integer.parseInt(TIEMPO);
            Thread.sleep(t*1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public void notificacionBarraProgreso()
    {

        try {


            int MAX = Integer.parseInt(TIEMPO)*60;
            int MIN = 0;

            Notification.Builder builder = new Notification.Builder(context, "CANAL_MIO")
                    .setContentTitle(""+NOMBRE+" en Progreso")
                    .setContentText("Vamos tu puedes")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setProgress(MAX, MIN, false);

            Notification noticia = builder.build();

            startForeground(100,noticia);

            while(MIN < MAX){
                Thread.sleep(1000);
                MIN ++;
                builder.setProgress(MAX, MIN, false);
                noticia = builder.build();
                startForeground(100,noticia);
            }

            builder.setProgress(0, 0, false);
            noticia = builder.setContentText(""+NOMBRE+" Completado ").build();
            startForeground(100,noticia);


            mostrarNotificacion();


        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void mostrarNotificacion(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CANAL_MIO")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Comercial:")
                .setContentText("Compra las nuevas TIC TAC")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(117, builder.build());
    }


}