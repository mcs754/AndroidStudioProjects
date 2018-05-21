package br.edu.ifro.vilhena.ads.olamundo2;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txtTitulo;
    private EditText edtTitulo;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Mapeando os componentes do layout
        this.txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        this.edtTitulo = (EditText) findViewById(R.id.edtTitulo);
        this.btnEnviar = (Button) findViewById(R.id.btnEnviar);

        //Realizar algo quando clicar no botão
        this.btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTitulo.setText(edtTitulo.getText());
                Snackbar mensagem = Snackbar.make(v, "Titulo setado", Snackbar.LENGTH_LONG);
                mensagem.show();
            }
        });
    }
}
