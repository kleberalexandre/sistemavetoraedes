package br.com.unitcorp.sistemavetoraedes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.unitcorp.sistemavetoraedes.model.RecipienteEnc;

/**
 * Created by Kleber on 28/09/2016.
 */

public class DaoRecipienteEnc {

    private Context context;

    public DaoRecipienteEnc(Context context) {
        this.context = context;
    }

    public boolean gravar(RecipienteEnc recipienteEnc) {
        ContentValues content = new ContentValues();

        content.put("nome", recipienteEnc.getNome());

        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

        long resultado = db.insert("recipineteEnc", null, content);

        DataBaseHelper.getInstance().closeDatabase();

        return resultado != -1;
    }

    public boolean alterar(RecipienteEnc recipienteEnc) {
        ContentValues content = new ContentValues();
        content.put("nome", recipienteEnc.getNome());


        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

        long resultado = db.update("recipienteEnc", content, "_id = ?",
                new String[]{"" + recipienteEnc.getId()});

        DataBaseHelper.getInstance().closeDatabase();

        return resultado != -1;
    }

    public boolean remover(RecipienteEnc recipienteEnc) {
        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();
        String where[] = new String[]{recipienteEnc.getId() + ""};
        long resultado = db.delete("recipineteEnc", "_id = ?", where);

        DataBaseHelper.getInstance().closeDatabase();

        return resultado != -1;
    }

    public List<RecipienteEnc> getRecipienteEnc() {
        List<RecipienteEnc> lista = new ArrayList<RecipienteEnc>();
        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(" SELECT r._id, r.nome, idRecipienteEnc" +
                " FROM RecipienteEnc r" +
                " where r._id = ? ", null);
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            RecipienteEnc recipienteEnc = new RecipienteEnc();
            recipienteEnc.setId(cursor.getInt(0));
            recipienteEnc.setNome(cursor.getString(1));
            lista.add(recipienteEnc);
            cursor.moveToNext();
        }

        cursor.close();
        DataBaseHelper.getInstance().closeDatabase();

        return lista;
    }

    public RecipienteEnc getRecipienteEnceByID(long id) {
        RecipienteEnc recipienteEnc = null;
        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

        Cursor cursor =
                db.rawQuery("SELECT _id, nome FROM recipienteEnc  where _id = ?", new String[]{id + ""});
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            recipienteEnc = new RecipienteEnc();
            recipienteEnc.setId(cursor.getInt(0));
            recipienteEnc.setNome(cursor.getString(1));

        }

        cursor.close();
        DataBaseHelper.getInstance().closeDatabase();

        return recipienteEnc;
    }

}
