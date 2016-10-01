package br.com.unitcorp.sistemavetoraedes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.unitcorp.sistemavetoraedes.model.Usuario;

/**
 * Created by Kleber on 28/09/2016.
 */

public class DaoUsuario {

    private Context context;

    public DaoUsuario(Context context) {
        this.context = context;
    }

    public boolean gravar(Usuario usuario) {
        ContentValues content = new ContentValues();

        content.put("nome", usuario.getNome());
        content.put("login", usuario.getLogin());
        content.put("senha", usuario.getSenha());

        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

        long resultado = db.insert("usuario", null, content);

        DataBaseHelper.getInstance().closeDatabase();

        return resultado != -1;
    }

    public boolean alterar(Usuario usuario) {
        ContentValues content = new ContentValues();
        content.put("nome", usuario.getNome());
        content.put("login", usuario.getLogin());
        content.put("senha", usuario.getSenha());

        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

        long resultado = db.update("usuario", content, "_id = ?",
                new String[]{"" + usuario.getId()});

        DataBaseHelper.getInstance().closeDatabase();

        return resultado != -1;
    }

    public boolean remover(Usuario usuario) {
        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();
        String where[] = new String[]{usuario.getId() + ""};
        long resultado = db.delete("categoria", "_id = ?", where);

        DataBaseHelper.getInstance().closeDatabase();

        return resultado != -1;
    }

    public List<Usuario> getUsuarios() {
        List<Usuario> lista = new ArrayList<Usuario>();
        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();
        Cursor cursor = db.rawQuery("SELECT _id, nome, login, senha FROM usuario ", null);
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            Usuario usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNome(cursor.getString(1));
            usuario.setLogin(cursor.getString(2));
            usuario.setSenha(cursor.getString(3));
            lista.add(usuario);
            cursor.moveToNext();
        }

        cursor.close();
        DataBaseHelper.getInstance().closeDatabase();

        return lista;
    }

    public Usuario getUsuarioByID(long id) {
        Usuario usuario = null;
        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

        Cursor cursor =
                db.rawQuery("SELECT _id, nome, login, senha FROM usuario d where d._id = ?", new String[]{id + ""});
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNome(cursor.getString(1));
            usuario.setLogin(cursor.getString(2));
            usuario.setSenha(cursor.getString(3));
        }

        cursor.close();
        DataBaseHelper.getInstance().closeDatabase();

        return usuario;
    }

    public Usuario getUsuarioByLoginSenha(String login, String senha) {
        Usuario usuario = null;
        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

        Cursor cursor =
                db.rawQuery("SELECT _id, nome, login, senha FROM usuario d where d.login = ? and d.senha = ?",
                        new String[]{login, senha});
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNome(cursor.getString(1));
            usuario.setLogin(cursor.getString(2));
            usuario.setSenha(cursor.getString(3));
        }

        cursor.close();
        DataBaseHelper.getInstance().closeDatabase();

        return usuario;
    }


}
