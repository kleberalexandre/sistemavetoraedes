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
    }

    public void buscarbairro() {
        DaoBairro daoBairro = new DaoBairro(getBaseContext());
        List<Bairro> bairros = daoBairro.getBairros();

        ArrayAdapter<Bairro> bairroArrayAdapter = new ArrayAdapter<Bairro>(this, android.R.layout.simple_list_item_1, bairros);
        spBairro.setAdapter(bairroArrayAdapter);
    }
}
