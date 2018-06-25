package br.edu.ifro.vilhena.ads.notepad.DAO;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;
import br.edu.ifro.vilhena.ads.notepad.model.Nota;

@Dao
public interface NotaDAO {
    @Query("select * from notas")
    List<Nota> listarTodos();

    @Query("select * from notas where id = :id")
    Nota listarUm(int id);

    @Insert
    void inserir(Nota nota);

    @Update
    void alterar(Nota nota);

    @Delete
    void deletar(Nota nota);
}

