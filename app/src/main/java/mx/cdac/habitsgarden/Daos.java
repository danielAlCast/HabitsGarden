package mx.cdac.habitsgarden;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;


@Dao
interface ActividadDao
{
    @Query("SELECT * FROM actividad")
    ListenableFuture<List<Actividad>> selectAll();

    @Query("SELECT * FROM actividad WHERE id LIKE :ID")
    ListenableFuture<List<Actividad>> selectById(String ID);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public ListenableFuture<List<Long>> insertActividad(List<Actividad> actividades);


}

