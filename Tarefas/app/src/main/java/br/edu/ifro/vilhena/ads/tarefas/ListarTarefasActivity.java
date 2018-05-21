package br.edu.ifro.vilhena.ads.tarefas;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifro.vilhena.ads.tarefas.DAO.AppDatabase;
import br.edu.ifro.vilhena.ads.tarefas.model.Tarefa;

public class ListarTarefasActivity extends AppCompatActivity {

    private ListView lsvlistaTarefas;
    private FloatingActionButton fabCadastrarTarefa;
    private List<Tarefa> tarefas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_tarefas);

        this.lsvlistaTarefas = (ListView) findViewById(R.id.lsv_lista_tarefas);
        this.fabCadastrarTarefa = (FloatingActionButton) findViewById(R.id.fab_cadastrar_tarefa);
        this.fabCadastrarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CadastrarTarefaActivity.class);
                startActivity(intent);
            }
        });

        lsvlistaTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tarefa tarefa = tarefas.get(position);
                AppDatabase.getAppDatabase(ListarTarefasActivity.this).tarefaDAO().deletar(tarefa);
                atualizarLista();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        atualizarLista();
    }

    public void atualizarLista(){
        tarefas = AppDatabase.getAppDatabase(ListarTarefasActivity.this).tarefaDAO().listarTodos();
        ArrayAdapter<Tarefa> adapter = new ArrayAdapter<Tarefa>(this, android.R.layout.simple_list_item_1, tarefas);
        this.lsvlistaTarefas.setAdapter(adapter);
    }
}
