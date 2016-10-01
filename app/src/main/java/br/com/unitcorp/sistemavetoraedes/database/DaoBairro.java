package br.com.unitcorp.sistemavetoraedes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.unitcorp.sistemavetoraedes.model.Bairro;

/**
 * Created by Kleber on 28/09/2016.
 */

public class DaoBairro {

    private Context context;

    public DaoBairro(Context context) {
        this.context = context;
    }

    public boolean gravar(Bairro bairro) {
        ContentValues content = new ContentValues();

        content.put("nome", bairro.getNome());

        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

        long resultado = db.insert("bairro", null, content);

        DataBaseHelper.getInstance().closeDatabase();

        return resultado != -1;
    }

    public boolean alterar(Bairro bairro) {
        ContentValues content = new ContentValues();
        content.put("nome", bairro.getNome());


        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

        long resultado = db.update("bairro", content, "_id = ?",
                new String[]{"" + bairro.getId()});

        DataBaseHelper.getInstance().closeDatabase();

        return resultado != -1;
    }

    public boolean remover(Bairro bairro) {
        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();
        String where[] = new String[]{bairro.getId() + ""};
        long resultado = db.delete("bairro", "_id = ?", where);

        DataBaseHelper.getInstance().closeDatabase();

        return resultado != -1;
    }

    public List<Bairro> getBairros() {
        List<Bairro> lista = new ArrayList<Bairro>();
        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();
        Cursor cursor = db.rawQuery("SELECT _id, nome FROM bairro ", null);
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            Bairro bairro = new Bairro();
            bairro.setId(cursor.getInt(0));
            bairro.setNome(cursor.getString(1));
            lista.add(bairro);
            cursor.moveToNext();
        }

        cursor.close();
        DataBaseHelper.getInstance().closeDatabase();

        return lista;
    }

    public Bairro getBairroByID(long id) {
        Bairro bairro = null;
        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

        Cursor cursor =
                db.rawQuery("SELECT _id, nome FROM bairro  where _id = ?", new String[]{id + ""});
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            bairro = new Bairro();
            bairro.setId(cursor.getInt(0));
            bairro.setNome(cursor.getString(1));

        }

        cursor.close();
        DataBaseHelper.getInstance().closeDatabase();

        return bairro;
    }

}
