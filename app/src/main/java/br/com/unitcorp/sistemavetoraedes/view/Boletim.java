package br.com.unitcorp.sistemavetoraedes.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.com.unitcorp.sistemavetoraedes.R;
import br.com.unitcorp.sistemavetoraedes.database.DaoBairro;
import br.com.unitcorp.sistemavetoraedes.database.DaoRua;
import br.com.unitcorp.sistemavetoraedes.model.Bairro;
import br.com.unitcorp.sistemavetoraedes.model.Rua;

public class Boletim extends AppCompatActivity {

    private Spinner spBairro;
    private Spinner spRua;
    private Button btProximo;
    private RadioButton checkpsf;
    private RadioButton checkrotina;
    private RadioButton checkpedencia;
    private RadioButton checkdemanda;
    private RadioGroup gprbTipoAmostragem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boletim);

        //Iniciando componentes
        spBairro = (Spinner) findViewById(R.id.spBairro);
        spRua = (Spinner) findViewById(R.id.spRua);
        btProximo = (Button) findViewById(R.id.btProximo);
        checkpsf = (RadioButton) findViewById(R.id.checkPsf);
        checkrotina = (RadioButton) findViewById(R.id.checkRotina);
        checkpedencia = (RadioButton) findViewById(R.id.checkPendencia);
        checkdemanda = (RadioButton) findViewById(R.id.checkDemanda);
        gprbTipoAmostragem = (RadioGroup) findViewById(R.id.gprbTipoAmostragem);

        //Procedimentos
        buscarbairro();
        buscarRuas();
        //onCreate é o primeiro método a ser executado em um activity,
        //por esse motivo tudo parte daqui.
        botaoProximo();
    }

    public void buscarbairro() {
        DaoBairro daoBairro = new DaoBairro(getBaseContext());
        List<Bairro> bairros = daoBairro.getBairros();

        ArrayAdapter<Bairro> bairroArrayAdapter = new ArrayAdapter<Bairro>(this, android.R.layout.simple_list_item_1, bairros);
        spBairro.setAdapter(bairroArrayAdapter);
    }

    public void buscarRuas() {
        final Context context = getBaseContext();
        spBairro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Bairro b = (Bairro) spBairro.getItemAtPosition(position);

                DaoRua daoRua = new DaoRua(getBaseContext());
                List<Rua> ruas = daoRua.getRuas(b.getId());

                ArrayAdapter<Rua> ruaArrayAdapter = new ArrayAdapter<Rua>(context, android.R.layout.simple_list_item_1, ruas);
                spRua.setAdapter(ruaArrayAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    //Aqui vou criar um método para o botão
    public void botaoProximo(){
        //Vou implementar o click do botão
        btProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //A validação acontece aqui, antes de chamar a proxima activity

                //getSelecteditem retorna o objeto selecionado, se retornar nulo é por que
                //não existe nada selecionado
                //Só um minuto, vou oerguntar ao pai
                if (spBairro.getSelectedItem() == null){
                    Toast.makeText(getBaseContext(),"Entre com o bairro",Toast.LENGTH_LONG).show();
                }else if (spRua.getSelectedItem() == null){
                    Toast.makeText(getBaseContext(),"Entre com a rua",Toast.LENGTH_LONG).show();
                }else if (!checkpsf.isChecked() && !checkdemanda.isChecked() &&
                        !checkrotina.isChecked() && !checkpedencia.isChecked()){
                    Toast.makeText(getBaseContext(),"Entre com o tipo de amostragem",Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(getBaseContext(), boletim_informcoes.class);

                    Bairro bairro = (Bairro) spBairro.getSelectedItem();
                    Rua rua = (Rua) spRua.getSelectedItem();
                    //Retorna o ID que representa o radiogroup selecionado
                    int tipoAmostra = gprbTipoAmostragem.getCheckedRadioButtonId();

                    //Passando parametros para a próxima tela
                    intent.putExtra("idBairro", bairro.getId());
                    intent.putExtra("idRua",rua.getId());
                    intent.putExtra("radio",tipoAmostra);

                    startActivity(intent);
                }
            }
        });
    }
}
