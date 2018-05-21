package br.edu.ifro.vilhena.ads.tarefas;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.edu.ifro.vilhena.ads.tarefas.DAO.AppDatabase;
import br.edu.ifro.vilhena.ads.tarefas.model.Tarefa;

public class CadastrarTarefaActivity extends AppCompatActivity {

    private TextInputLayout edtDescricao;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_tarefa);

        edtDescricao = (TextInputLayout) findViewById(R.id.edt_descricao);
        btnSalvar = (Button) findViewById(R.id.btn_salvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tarefa tarefa = new Tarefa();
                tarefa.setDescricao(edtDescricao.getEditText().getText().toString());

                AppDatabase.getAppDatabase(CadastrarTarefaActivity.this).tarefaDAO().inserir(tarefa);
                finish();
            }
        });
    }
}

