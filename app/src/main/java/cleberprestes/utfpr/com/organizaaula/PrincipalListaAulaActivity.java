package cleberprestes.utfpr.com.organizaaula;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;

public class PrincipalListaAulaActivity extends AppCompatActivity {

    private ListView listViewAulas;
    private AulasViewAdapter listaAdapter;
    private ArrayList<Aula>    listaAulas;


    private ActionMode actionMode;
    private int        posicaoSelecionada = -1;
    private View       viewSelecionada;

    private  String periodoString;
    private String numeroAula;
    private String disciplina;
    private  String  dia;

    private ActionMode.Callback actionModelCall = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflate = mode.getMenuInflater();
            inflate.inflate(R.menu.principal_item_selecionado, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch(item.getItemId()){
                case R.id.menuItemAlterar:
                    alterarAulas();
                    mode.finish();
                    return true;

                case R.id.menuItemExcluir:
                    excluirAula();
                    mode.finish();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

            if (viewSelecionada != null){
                viewSelecionada.setBackgroundColor(Color.TRANSPARENT);
            }
            actionMode         = null;
            viewSelecionada    = null;

            listViewAulas.setEnabled(true);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_lista_aula);

        listViewAulas = findViewById(R.id.listViewAulas);

        listViewAulas.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view,
                                            int position,
                                            long id) {

                        posicaoSelecionada = position;
                        alterarAulas();
                    }
                });

        listViewAulas.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listViewAulas.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {

                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent,
                                                   View view,
                                                   int position,
                                                   long id) {

                        if (actionMode != null){
                            return false;
                        }

                        posicaoSelecionada = position;

                        view.setBackgroundColor(Color.GREEN);

                        viewSelecionada = view;

                        listViewAulas.setEnabled(false);


                        actionMode = startSupportActionMode(actionModelCall);

                        return true;
                    }
                });
        popularLista();

    }

    private void popularLista(){

     listaAulas = new ArrayList<>();
       listaAdapter = new AulasViewAdapter(this, listaAulas);
         listViewAulas.setAdapter(listaAdapter);

    }

    private void excluirAula(){

        listaAulas.remove(posicaoSelecionada);
        listaAdapter.notifyDataSetChanged();
    }

    private void alterarAulas(){

        Aula aula = listaAulas.get(posicaoSelecionada);

        CadastraAulaActivity.alterarAula(this, aula);
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            Bundle bundle = data.getExtras();

           disciplina = bundle.getString(CadastraAulaActivity.DISCIPLINA);
           dia      = bundle.getString(CadastraAulaActivity.DIADASEMANA);
            int periodo = bundle.getInt(CadastraAulaActivity.PERIODO);

            boolean aula1 = bundle.getBoolean(CadastraAulaActivity.AULAUM);
            boolean aula2 = bundle.getBoolean(CadastraAulaActivity.AULADOIS);
            boolean aula3 = bundle.getBoolean(CadastraAulaActivity.AULATRES);
            boolean aula4 = bundle.getBoolean(CadastraAulaActivity.AULAQUATRO);
            boolean aula5 = bundle.getBoolean(CadastraAulaActivity.AULACINCO);

        switch(periodo){

                case 1:
                    periodoString = "Manhã";
                    break;
                case 2:
                    periodoString = "Tarde";
                    break;
                case 3:
                    periodoString = "Noite";
                    break;
                default:

            }

            if(aula1){
                numeroAula = "Aula 1";

            }
            if(aula2){
                numeroAula = "Aula 2";

            }
            if(aula3){
                numeroAula = "Aula 3";

            }
            if(aula4){
                numeroAula = "Aula 4";

            }
            if(aula5){
                numeroAula = "Aula 5";

            }

         if (requestCode == CadastraAulaActivity.ALTERAR) {

                //todo para devolver
                Aula aula = listaAulas.get(posicaoSelecionada);

                aula.setDisciplina(disciplina);
                aula.setDia(dia);
                aula.setAula(numeroAula);
                aula.setPeriodo(periodoString);

                posicaoSelecionada = -1;

            } else {
                listaAulas.add(new Aula(disciplina, dia, periodoString, numeroAula));
            }

            listaAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal_opcoes, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.menuItemNovo:
                CadastraAulaActivity.novaAula(this);
                return true;
            case R.id.menuItemSobre:
                SobreActivity.sobre(this);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}