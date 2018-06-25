package br.edu.ifro.vilhena.ads.notepad.model;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "notas")
public class Nota {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String descricao;
    private boolean realizado = false;
    private long dataHora;

    @Override
    public String toString() {
        return "Nota{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", dataHora=" + dataHora +
                '}';
    }

    public Nota(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Nota() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public long getDataHora() {
        return dataHora;
    }

    public void setDataHora(long dataHora) {
        this.dataHora = dataHora;
    }
}

