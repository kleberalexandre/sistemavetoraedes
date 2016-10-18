package br.com.unitcorp.sistemavetoraedes.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.com.unitcorp.sistemavetoraedes.R;
import br.com.unitcorp.sistemavetoraedes.database.DaoBairro;
import br.com.unitcorp.sistemavetoraedes.database.DaoRua;
import br.com.unitcorp.sistemavetoraedes.database.DaoSituacaoImovel;
import br.com.unitcorp.sistemavetoraedes.model.Atividade;
import br.com.unitcorp.sistemavetoraedes.model.Bairro;
import br.com.unitcorp.sistemavetoraedes.model.Rua;
import br.com.unitcorp.sistemavetoraedes.model.SituacaoImovel;
import br.com.unitcorp.sistemavetoraedes.model.RecipienteEnc;

public class boletim_informcoes extends AppCompatActivity {


    //private Spinner ;
    // codigo da pagina de informaçoes

    private Spinner spSituacaoImovel;
    private ListView listRecepEnc;
    private Button btFinalizar;
    private EditText ednumeEditText;
    private EditText edresponsEditText;
    private ListView getListRecepEnc;

    //Aqui eu vou guardar o que vem da outra tela
    private int idBairro;
    private int idRua;
    private int radio;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_boletim_informcoes);


        //Iniciando componentes
        ednumeEditText = (EditText) findViewById(R.id.ednumero);
        edresponsEditText = (EditText) findViewById(R.id.edrespons);
        spSituacaoImovel = (Spinner) findViewById(R.id.spSituacaoImovel);
        listRecepEnc = (ListView) findViewById(R.id.listRecepEnc);

        spSituacaoImovel = (Spinner) findViewById(R.id.spSituacaoImovel);
        listRecepEnc = (ListView) findViewById(R.id.listRecepEnc);
        btFinalizar = (Button) findViewById(R.id.btFinalizar);

        buscarsituacaoImovel();

        //Pegando parametros vindo a outra tela
        Bundle b = getIntent().getExtras();
        idBairro = b.getInt("idBairro");
        idRua = b.getInt("idRua");
        radio = b.getInt("radio");

        botaoFinalizar();

    }



    private void buscarsituacaoImovel() {
        DaoSituacaoImovel daoSituacaoImovel = new DaoSituacaoImovel(getBaseContext());
        //Busca situações
        List<SituacaoImovel> situacaoImovels = daoSituacaoImovel.getSituacaoImovels();

        //Carrega ArrayAdpter de SituacaoImovel
        ArrayAdapter<SituacaoImovel> atividadeArrayAdapter = new ArrayAdapter<SituacaoImovel>(getBaseContext(), android.R.layout.simple_list_item_1, situacaoImovels);
        //Insere lista no spinner
        spSituacaoImovel.setAdapter(atividadeArrayAdapter);
    }


    //Aqui vou criar um método para o botão
    public void botaoFinalizar() {
        //Vou implementar o click do botão
        btFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //A validação acontece aqui, antes de chamar a proxima activity

                //getSelecteditem retorna o objeto selecionado, se retornar nulo é por que
                //não existe nada selecionado

                if (ednumeEditText.getText() == null) {
                    Toast.makeText(getBaseContext(), "Entre com o numero do imovel", Toast.LENGTH_LONG).show();
                } else if (edresponsEditText.getText() == null) {
                    Toast.makeText(getBaseContext(), "Entre com o nome do responsável", Toast.LENGTH_LONG).show();
                } else if (spSituacaoImovel.getSelectedItem() == null) {
                    Toast.makeText(getBaseContext(), "Informe a situaação do Imóvel", Toast.LENGTH_LONG).show();
                    Toast.makeText(getBaseContext(), "Informe o Recipiente Encontrado", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(getBaseContext(), boletim_informcoes.class);

                    SituacaoImovel situacaoImovel = (SituacaoImovel) spSituacaoImovel.getSelectedItem();



                }
            }
        });
    }
}

