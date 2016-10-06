package br.com.unitcorp.sistemavetoraedes.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Spinner;

import br.com.unitcorp.sistemavetoraedes.R;

public class boletim_informcoes extends AppCompatActivity {

    private Spinner sp;
    private Spinner spRua;
    private Button btProximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boletim_informcoes);
    }
}
