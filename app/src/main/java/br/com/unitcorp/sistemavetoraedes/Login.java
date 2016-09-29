package br.com.unitcorp.sistemavetoraedes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import database.DaoUsuario;
import model.Usuario;

public class Login extends AppCompatActivity {

    private EditText edLogin;
    private EditText edSenha;
    private Button btEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Agenda");

        edLogin = (EditText) findViewById(R.id.edLogin);
        edSenha = (EditText) findViewById(R.id.edSenha);
        btEntrar = (Button) findViewById(R.id.btEntrar);

        botaoEntrar();
    }

    public void botaoEntrar() {
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //////////////////
                if (edLogin.getText().toString().trim().equals("") || edSenha.getText().toString().trim().equals("")) {
                    Toast.makeText(getBaseContext(), "Usuário/Senha inválido", Toast.LENGTH_LONG).show();
                } else {
                    DaoUsuario daoUsuario = new DaoUsuario(getBaseContext());
                    Usuario usuario = daoUsuario.getUsuarioByLoginSenha(edLogin.getText().toString(), edSenha.getText().toString());
                    if (usuario == null) {
                        Toast.makeText(getBaseContext(), "Usuário/Senha inválido", Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(getBaseContext(), Principal.class);
                        startActivity(intent);
                    }
                }
                /////////////////////////
            }
        });
    }
}
