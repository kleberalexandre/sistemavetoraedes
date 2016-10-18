package br.com.unitcorp.sistemavetoraedes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.unitcorp.sistemavetoraedes.model.Atividade;

/**
 * Created by Kleber on 28/09/2016.
 */

public class DaoAtividade {

    private Context context;

    public DaoAtividade(Context context) {
        this.context = context;
    }

    public boolean gravar(Atividade atividade) {
        ContentValues content = new ContentValues();

        content.put("nome", atividade.getNome());

        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

        long resultado = db.insert("atividade", null, content);

        DataBaseHelper.getInstance().closeDatabase();

        return resultado != -1;
    }

    public boolean alterar(Atividade atividade) {
        ContentValues content = new ContentValues();
        content.put("nome", atividade.getNome());


        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

        long resultado = db.update("atividae", content, "_id = ?",
                new String[]{"" + atividade.getId()});

        DataBaseHelper.getInstance().closeDatabase();

        return resultado != -1;
    }

    public boolean remover(Atividade atividade) {
        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();
        String where[] = new String[]{atividade.getId() + ""};
        long resultado = db.delete("atividade", "_id = ?", where);

        DataBaseHelper.getInstance().closeDatabase();

        return resultado != -1;
    }

    public List<Atividade> getAtividades() {
        List<Atividade> lista = new ArrayList<Atividade>();
        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();
        Cursor cursor = db.rawQuery("SELECT r._id, r.nome FROM Atividade r",null);

        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            Atividade atividade = new Atividade();
            atividade.setId(cursor.getInt(0));
            atividade.setNome(cursor.getString(1));
            lista.add(atividade);
            cursor.moveToNext();
        }

        cursor.close();
        DataBaseHelper.getInstance().closeDatabase();

        return lista;
    }

    public Atividade getAtividadeByID(long id) {
        Atividade atividade = null;
        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

        Cursor cursor =
                db.rawQuery("SELECT _id, nome FROM atividade  where _id = ?", new String[]{id + ""});
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            atividade = new Atividade();
            atividade.setId(cursor.getInt(0));
            atividade.setNome(cursor.getString(1));

        }

        cursor.close();
        DataBaseHelper.getInstance().closeDatabase();

        return atividade;
    }

}
