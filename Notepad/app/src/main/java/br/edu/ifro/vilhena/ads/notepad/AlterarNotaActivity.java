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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import br.edu.ifro.vilhena.ads.notepad.DAO.RoomDatabase.AppDatabase;
import br.edu.ifro.vilhena.ads.notepad.model.Nota;

public class AlterarNotaActivity extends AppCompatActivity {
    private TextInputLayout tilAlterarDescricao;
    private TextView txtAlterarData;
    private TextView txtAlterarHora;
    private Switch swtRealizado;
    private Button btnAlterar;
    private Calendar dataHora = Calendar.getInstance();
    private Nota nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_nota);
        tilAlterarDescricao = (TextInputLayout) findViewById(R.id.til_alterar_descricao);
        txtAlterarData = (TextView) findViewById(R.id.txt_alterar_data);
        txtAlterarHora = (TextView) findViewById(R.id.txt_alterar_hora);
        swtRealizado = (Switch) findViewById(R.id.swt_realizado);
        btnAlterar = (Button) findViewById(R.id.btn_alterar);

        Intent intent = getIntent();
        Bundle args = intent.getExtras();
        int id = args.getInt("id_nota");
        nota = AppDatabase.getAppDatabase(this).notaDAO().listarUm(id);
        tilAlterarDescricao.getEditText().setText(nota.getDescricao());
        SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");
        txtAlterarData.setText(formatarData.format(nota.getDataHora()));
        SimpleDateFormat formatarHora = new SimpleDateFormat("HH:mm");
        txtAlterarHora.setText(formatarHora.format(nota.getDataHora()));
        swtRealizado.setChecked(nota.isRealizado());

        final DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dataHora.set(Calendar.YEAR, year);
                dataHora.set(Calendar.MONTH, month);
                dataHora.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat formatacao = new SimpleDateFormat("dd/MM/yyyy");
                txtAlterarData.setText(formatacao.format(dataHora.getTime()));
            }
        };
        final TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                dataHora.set(Calendar.HOUR, hourOfDay);
                dataHora.set(Calendar.MINUTE, minute);
                SimpleDateFormat formatacao = new SimpleDateFormat("HH:mm");
                txtAlterarHora.setText(formatacao.format(dataHora.getTime()));
            }
        };

        txtAlterarData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AlterarNotaActivity.this, d, dataHora.get(Calendar.YEAR), dataHora.get(Calendar.MONTH), dataHora.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        txtAlterarHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(AlterarNotaActivity.this, t, dataHora.get(Calendar.HOUR), dataHora.get(Calendar.MINUTE), true).show();
            }
        });

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nota.setDescricao(tilAlterarDescricao.getEditText().getText().toString().trim());
                nota.setDataHora(dataHora.getTimeInMillis());
                nota.setRealizado(swtRealizado.isChecked());
                AppDatabase.getAppDatabase(AlterarNotaActivity.this).notaDAO().alterar(nota);
                Intent intent = new Intent();
                intent.putExtra("resposta", "OK");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}

