package cleberprestes.utfpr.com.organizaaula;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.ArrayList;

public class CadastraAulaActivity extends AppCompatActivity {


    public static final String MODO    = "MODO";
    public static final String DISCIPLINA    = "DISCIPLINA";
    public static final String DIADASEMANA      = "DIADASEMANA";
    public static final String PERIODO       = "PERIODO";

    public static final String AULAUM = "AULAUM";
    public static final String AULADOIS = "AULADOIS";
    public static final String AULATRES = "AULATRES";
    public static final String AULAQUATRO = "AULAQUATRO";
    public static final String AULACINCO = "AULACINCO";



    public static final int    NOVO    = 1;
    public static final int    ALTERAR = 2;


    private int    modo;

    private String disciplinaOriginal;

    private EditText editTextDisciplina;
    private Spinner spinnerDiasSemana;
    private RadioGroup radioGroupPeriodos;
    private CheckBox cbAula1, cbAula2, cbAula3, cbAula4, cbAula5;


    public static void novaAula(AppCompatActivity activity){

        Intent intent = new Intent(activity, CadastraAulaActivity.class);

        intent.putExtra(MODO, NOVO);

        activity.startActivityForResult(intent, NOVO);
    }


    public static void alterarAula(AppCompatActivity activity, Aula aula){

        Intent intent = new Intent(activity, CadastraAulaActivity.class);

        intent.putExtra(MODO, ALTERAR);
        intent.putExtra(DISCIPLINA, aula.getDisciplina());
        intent.putExtra(DIADASEMANA,  aula.getDia());
        intent.putExtra(PERIODO,aula.getPeriodo());

        intent.putExtra(AULAUM, aula.getAula());
        intent.putExtra(AULADOIS, aula.getAula());
        intent.putExtra(AULATRES, aula.getAula());
        intent.putExtra(AULAQUATRO, aula.getAula());
        intent.putExtra(AULACINCO, aula.getAula());

       activity.startActivityForResult(intent, ALTERAR);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextDisciplina = findViewById(R.id.editTextDisciplina);
        spinnerDiasSemana = findViewById(R.id.spinnerDiasSemana);
        radioGroupPeriodos = findViewById(R.id.radioGroupPeriodo);

        cbAula1 = findViewById(R.id.checkBoxAulaUm);
        cbAula2 = findViewById(R.id.checkBoxAulaDois);
        cbAula3 = findViewById(R.id.checkBoxAulaTres);
        cbAula4 = findViewById(R.id.checkBoxAulaQuatro);
        cbAula5 = findViewById(R.id.checkBoxAulaCinco);

        popularSpinner();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();


        if (bundle != null){

            modo = bundle.getInt(MODO, NOVO);

            if (modo == NOVO){
                setTitle(getString(R.string.nova_aula));
            }else{

                String dia = bundle.getString(DIADASEMANA);

                for (int pos = 0; 0 < spinnerDiasSemana.getAdapter().getCount(); pos++){

                    String valor = (String) spinnerDiasSemana.getItemAtPosition(pos);

                    if (valor.equals(dia)){
                        spinnerDiasSemana.setSelection(pos);
                        break;
                    }
                }

                String peridoOriginal = bundle.getString(PERIODO);


                RadioButton button;
                switch(peridoOriginal){
                    case "Manhã":
                        button = findViewById(R.id.radioButtonManha);
                        button.setChecked(true);
                        break;

                    case "Tarde":
                        button = findViewById(R.id.radioButtonTarde);
                        button.setChecked(true);
                        break;

                    case "Noite":
                        button = findViewById(R.id.radioButtonNoite);
                        button.setChecked(true);
                        break;
                }


                String aula1 = bundle.getString(AULAUM);
                String aula2 = bundle.getString(AULADOIS);
                String aula3 = bundle.getString(AULATRES);
                String aula4 = bundle.getString(AULAQUATRO);
                String aula5 = bundle.getString(AULACINCO);


               if(aula1.equals("Aula 1")){
                   cbAula1.setChecked(true);
               }
                if(aula2.equals("Aula 2")){
                    cbAula2.setChecked(true);
                }
                if(aula3.equals("Aula 3")){
                    cbAula3.setChecked(true);
                }
                if(aula4.equals("Aula 4")){
                    cbAula4.setChecked(true);
                }
                if(aula5.equals("Aula 5")){
                    cbAula5.setChecked(true);
                }

                disciplinaOriginal = bundle.getString(DISCIPLINA);
                editTextDisciplina.setText(disciplinaOriginal);

                setTitle(getString(R.string.alterar_aula));
            }
        }

    }

    private void popularSpinner(){
        ArrayList<String> lista = new ArrayList<>();

        lista.add(getString(R.string.segunda_feira));
        lista.add(getString(R.string.terca_feira));
        lista.add(getString(R.string.quarta_feira));
        lista.add(getString(R.string.quinta_feira));
        lista.add(getString(R.string.sexta_feira));

        ArrayAdapter adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);

        spinnerDiasSemana.setAdapter(adapter);
    }

    private void salvar(){

        String dia = (String) spinnerDiasSemana.getSelectedItem();

        if (dia == null) {
            Toast.makeText(this,
                    getString(R.string.erro_dia),
                    Toast.LENGTH_SHORT).show();
            return;
        }

        //todo algum erra por ser int
        int periodo;

        switch (radioGroupPeriodos.getCheckedRadioButtonId()) {

            case R.id.radioButtonManha:
                periodo = Aula.MANHA;
                break;

            case R.id.radioButtonTarde:
                periodo = Aula.TARDE;
                break;

            case R.id.radioButtonNoite:
                periodo = Aula.NOITE;
                break;

            default:
                Toast.makeText(this,
                        getString(R.string.erro_periodo_nao_selecionado),
                        Toast.LENGTH_SHORT).show();
                return;
        }

        boolean aula1 = cbAula1.isChecked();
        boolean aula2 = cbAula2.isChecked();
        boolean aula3 = cbAula3.isChecked();
        boolean aula4 = cbAula4.isChecked();
        boolean aula5 = cbAula5.isChecked();



        String disciplina = editTextDisciplina.getText().toString();

        if (disciplina == null || disciplina.trim().isEmpty()){
            Toast.makeText(this,
                    R.string.erro_disciplina_vazia,
                    Toast.LENGTH_SHORT).show();

            editTextDisciplina.requestFocus();
            return;
        }

        Intent intent = new Intent();

        intent.putExtra(DISCIPLINA, disciplina);
        intent.putExtra(DIADASEMANA, dia);
        intent.putExtra(PERIODO, periodo);
        intent.putExtra(AULAUM, aula1);
        intent.putExtra(AULADOIS, aula2);
        intent.putExtra(AULATRES, aula3);
        intent.putExtra(AULAQUATRO, aula4);
        intent.putExtra(AULACINCO, aula5);

        setResult(Activity.RESULT_OK, intent);

        finish();
    }

    private void cancelar(){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    public void onBackPressed() {
        cancelar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.aula_opcoes, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.menuItemSalvar:
                salvar();
                return true;

            case android.R.id.home:
            case R.id.menuItemCancelar:
                cancelar();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}