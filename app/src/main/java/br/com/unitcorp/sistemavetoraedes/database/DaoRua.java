package br.com.unitcorp.sistemavetoraedes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.unitcorp.sistemavetoraedes.model.Rua;

/**
 * Created by Kleber on 28/09/2016.
 */

public class DaoRua {

    private Context context;

    public DaoRua(Context context) {
        this.context = context;
    }

    public boolean gravar(Rua rua) {
        ContentValues content = new ContentValues();

        content.put("nome", rua.getNome());

        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

        long resultado = db.insert("rua", null, content);

        DataBaseHelper.getInstance().closeDatabase();

        return resultado != -1;
    }

    public boolean alterar(Rua rua) {
        ContentValues content = new ContentValues();
        content.put("nome", rua.getNome());


        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

        long resultado = db.update("rua", content, "_id = ?",
                new String[]{"" + rua.getId()});

        DataBaseHelper.getInstance().closeDatabase();

        return resultado != -1;
    }

    public boolean remover(Rua rua) {
        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();
        String where[] = new String[]{rua.getId() + ""};
        long resultado = db.delete("rua", "_id = ?", where);

        DataBaseHelper.getInstance().closeDatabase();

        return resultado != -1;
    }

    public List<Rua> getRuas(int idBairro) {
        List<Rua> lista = new ArrayList<Rua>();
        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();
        Cursor cursor = db.rawQuery("SELECT _id, nome, idBairro FROM rua where idBairro = ? ", new String[]{idBairro + ""});
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            Rua rua = new Rua();
            rua.setId(cursor.getInt(0));
            rua.setNome(cursor.getString(1));
            lista.add(rua);
            cursor.moveToNext();
        }

        cursor.close();
        DataBaseHelper.getInstance().closeDatabase();

        return lista;
    }

    public Rua getRuaByID(long id) {
        Rua rua = null;
        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

        Cursor cursor =
                db.rawQuery("SELECT _id, nome FROM rua  where _id = ?", new String[]{id + ""});
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            rua = new Rua();
            rua.setId(cursor.getInt(0));
            rua.setNome(cursor.getString(1));

        }

        cursor.close();
        DataBaseHelper.getInstance().closeDatabase();

        return rua;
    }

}
