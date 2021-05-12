package mx.cdac.habitsgarden;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {
                Actividad.class
        },
        version = 1
)
public abstract class Db extends RoomDatabase {
    public abstract ActividadDao actividadDao();

}
