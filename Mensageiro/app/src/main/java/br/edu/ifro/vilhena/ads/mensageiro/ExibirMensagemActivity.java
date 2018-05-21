package br.edu.ifro.vilhena.ads.mensageiro;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ExibirMensagemActivity extends AppCompatActivity {
    private TextView txtMensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_mensagem);

        this.txtMensagem = (TextView) findViewById(R.id.txtMensagem);

        Intent intent = getIntent();
        String mensagem = intent.getStringExtra("MENSAGEM");

        this.txtMensagem.setText(mensagem);

        Snackbar snackbar = Snackbar.make(findViewById(R.id.layExibirMensagem), mensagem, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
