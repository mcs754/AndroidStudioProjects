package br.edu.ifro.vilhena.ads.notepad.DAO.RoomDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import br.edu.ifro.vilhena.ads.notepad.DAO.NotaDAO;
import br.edu.ifro.vilhena.ads.notepad.model.Nota;

@Database(entities = {Nota.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NotaDAO notaDAO();

    private static final String DB_NAME = "db_notas";
    private static AppDatabase appDatabase;

    public static AppDatabase getAppDatabase(Context context){
        if (appDatabase == null){
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        } return appDatabase;
    }

}


