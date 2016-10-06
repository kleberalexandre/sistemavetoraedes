package br.com.unitcorp.sistemavetoraedes.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

import br.com.unitcorp.sistemavetoraedes.R;
import br.com.unitcorp.sistemavetoraedes.database.DaoBairro;
import br.com.unitcorp.sistemavetoraedes.model.Bairro;

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
        /*final Context context = getBaseContext();
        spBairro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        {


            btProximo.setOnClickListener(new View.OnClickListener()
            public void onClick(View v) {
                Intent it = new Intent(boletim_informcoes.this, boletim_informcoes.class);
                startActivity(it);
            }
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Bairro b = (Bairro) spBairro.getItemAtPosition(i);

                DaoRua daoRua = new DaoRua(getBaseContext());
                List<Rua> ruas = daoRua.getRuas(b.getId());

                ArrayAdapter<Rua> ruaArrayAdapter = new ArrayAdapter<Rua>(context, android.R.layout.simple_list_item_1, ruas);
                spRua.setAdapter(ruaArrayAdapter);
            })


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }


        });*/

    }
}
