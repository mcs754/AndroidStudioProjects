package br.edu.ifro.vilhena.ads.notepad;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import br.edu.ifro.vilhena.ads.notepad.DAO.RoomDatabase.AppDatabase;
import br.edu.ifro.vilhena.ads.notepad.model.Nota;

public class CadastrarNotaActivity extends AppCompatActivity {
    private Calendar dataHora = Calendar.getInstance();
    private TextInputLayout tilDescricao;
    private TextView txtData;
    private TextView txtHora;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_nota);

        tilDescricao = (TextInputLayout) findViewById(R.id.til_descricao);
        txtData = (TextView) findViewById(R.id.txt_data);
        txtHora = (TextView) findViewById(R.id.txt_hora);
        btnSalvar = (Button) findViewById(R.id.btn_salvar);

        final DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dataHora.set(Calendar.YEAR, year);
                dataHora.set(Calendar.MONTH, month);
                dataHora.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat formatacao = new SimpleDateFormat("dd/MM/yyyy");
                txtData.setText(formatacao.format(dataHora.getTime()));
            }
        };

        final TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                dataHora.set(Calendar.HOUR, hourOfDay);
                dataHora.set(Calendar.MINUTE, minute);
                SimpleDateFormat formatacao = new SimpleDateFormat("HH:mm");
                txtHora.setText(formatacao.format(dataHora.getTime()));
            }
        };

        txtData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CadastrarNotaActivity.this, d, dataHora.get(Calendar.YEAR), dataHora.get(Calendar.MONTH), dataHora.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        txtHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(CadastrarNotaActivity.this, t, dataHora.get(Calendar.HOUR), dataHora.get(Calendar.MINUTE), true).show();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarDescricao()) {
                    Nota nota = new Nota();
                    nota.setDescricao(tilDescricao.getEditText().getText().toString().trim());
                    nota.setDataHora(dataHora.getTimeInMillis());
                    AppDatabase.getAppDatabase(CadastrarNotaActivity.this).notaDAO().inserir(nota);
                    Intent intent = new Intent();
                    intent.putExtra("resposta", "OK");
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }

    public boolean validarDescricao(){
        if (tilDescricao.getEditText().getText().toString().trim().equals("")){
            tilDescricao.setError("A descrição do lembrete não pode estar em branco!");
            return false;
        }
        tilDescricao.setError(null);
        return true;
    }
}

