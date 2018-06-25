package br.edu.ifro.vilhena.ads.notepad;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.List;
import br.edu.ifro.ads.notas.adapter.ListarNotasAdapter;
import br.edu.ifro.vilhena.ads.notepad.DAO.RoomDatabase.AppDatabase;
import br.edu.ifro.vilhena.ads.notepad.model.Nota;

public class ListarNotasActivity extends AppCompatActivity {
    private FloatingActionButton fabCadastrarNota;
    private ListView lsvListarNotas;
    private List<Nota> notas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_notas);
        lsvListarNotas = (ListView) findViewById(R.id.lsv_listar_notas);
        fabCadastrarNota = (FloatingActionButton) findViewById(R.id.fab_cadastrar_nota);

        fabCadastrarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarNotasActivity.this, CadastrarNotaActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        lsvListarNotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListarNotasActivity.this, AlterarNotaActivity.class);
                intent.putExtra("id_nota", notas.get(position).getId());
                startActivityForResult(intent, 2);
            }
        });

        lsvListarNotas.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                MenuItem compartilhar = menu.add("Compartilhar");
                MenuItem deletar = menu.add("Deletar");

                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                final Nota notaSelecionada = (Nota) lsvListarNotas.getAdapter().getItem(info.position);

                deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        AlertDialog a = new AlertDialog.Builder(ListarNotasActivity.this)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setTitle("Deletar nota selecionada")
                                .setMessage("Deseja realmente excluir?")
                                .setPositiveButton("Sim",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                AppDatabase.getAppDatabase(ListarNotasActivity.this).notaDAO().deletar(notaSelecionada);
                                                atualizarLista();
                                                Snackbar.make(findViewById(R.id.layout_listar_notas), "Deletado com sucesso!", Snackbar.LENGTH_LONG).show();
                                            }
                                        })
                                .setNegativeButton("Não", null)
                                .show();
                        return false;
                    }
                });
            }});
    }

    public void atualizarLista(){
        notas = AppDatabase.getAppDatabase(this).notaDAO().listarTodos();
        ListarNotasAdapter adapter = new ListarNotasAdapter(notas, this);
        lsvListarNotas.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        atualizarLista();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1){
            Snackbar.make(findViewById(R.id.layout_listar_notas), "Lembrete cadastrado com sucesso!", Snackbar.LENGTH_LONG).show();
        } else if (resultCode == RESULT_OK && requestCode == 2){
            Snackbar.make(findViewById(R.id.layout_listar_notas), "Lembrete alterado com sucesso!", Snackbar.LENGTH_LONG).show();
        }
    }

}

