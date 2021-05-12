package mx.cdac.habitsgarden;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
class Actividad{

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @ColumnInfo(name="nombre")
    public String nombre;

    @ColumnInfo(name="tiempo")
    public String tiempo;

}
