package br.com.unitcorp.sistemavetoraedes.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boletim);


        spBairro = (Spinner) findViewById(R.id.spBairro);
        spRua = (Spinner) findViewById(R.id.spRua);
        btProximo = (Button) findViewById(R.id.btProximo);
        buscarbairro();
        buscarRuas();
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
            {
                public void onClick (View v){
                Intent it = new Intent(MainActivity.this, NovaTela.class);
                startActivity(it);
            }
            }

            btProximo.setOnClickListener(new View.OnClickListener()
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Bairro b = (Bairro) spBairro.getItemAtPosition(i);

                DaoRua daoRua = new DaoRua(getBaseContext());
                List<Rua> ruas = daoRua.getRuas(b.getId());

                ArrayAdapter<Rua> ruaArrayAdapter = new ArrayAdapter<Rua>(context, android.R.layout.simple_list_item_1, ruas);
                spRua.setAdapter(ruaArrayAdapter);
            }

            )


            // @Override
            //public void onNothingSelected(AdapterView<?> adapterView) {
            //}


        });

    }
}
