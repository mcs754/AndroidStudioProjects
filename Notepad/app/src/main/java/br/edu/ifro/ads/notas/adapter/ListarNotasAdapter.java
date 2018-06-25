package br.edu.ifro.ads.notas.adapter;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.List;
import br.edu.ifro.vilhena.ads.notepad.R;
import br.edu.ifro.vilhena.ads.notepad.model.Nota;

public class ListarNotasAdapter extends BaseAdapter {
    private final List<Nota> notas;
    private final Activity activity;

    public ListarNotasAdapter(List<Nota> notas, Activity activity) {
        this.notas = notas;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return notas.size();
    }

    @Override
    public Object getItem(int position) {
        return notas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return notas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.adapter_listar_notas, parent, false);
        Nota nota = notas.get(position);
        TextView txtItemDescricao = (TextView) view.findViewById(R.id.txt_item_descricao);
        TextView txtItemDataHora = (TextView) view.findViewById(R.id.txt_item_data_hora);
        txtItemDescricao.setText(nota.getDescricao());
        SimpleDateFormat formatacao = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        txtItemDataHora.setText(formatacao.format(nota.getDataHora()));

        if (nota.isRealizado()){
            txtItemDescricao.setTextColor(Color.RED);
            txtItemDescricao.setPaintFlags(txtItemDescricao.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        }

        return view;
    }
}

