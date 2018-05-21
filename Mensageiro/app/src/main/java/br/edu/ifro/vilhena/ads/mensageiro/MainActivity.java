package br.edu.ifro.vilhena.ads.mensageiro;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText edtMensagem;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.edtMensagem = (TextInputEditText) findViewById(R.id.edtMensagem);
        this.btnEnviar = (Button) findViewById(R.id.btnEnviar);
    }

    public void enviarMensagem(View view) {
        Intent intent = new Intent(this, ExibirMensagemActivity.class);
        String mensagem = this.edtMensagem.getText().toString();
        intent.putExtra("MENSAGEM", mensagem);
        startActivity(intent);
    }
}

