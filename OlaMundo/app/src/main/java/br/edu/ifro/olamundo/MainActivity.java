package br.edu.ifro.olamundo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtNome = (TextView) findViewById(R.id.txtNome);
    EditText edtNome = (EditText) findViewById(R.id.edtNome);
    Button btnNome = (Button) findViewById (R.id.btnNome);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNome.setText()
            }
        });
    }
}
