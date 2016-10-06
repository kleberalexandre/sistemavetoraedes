package br.com.unitcorp.sistemavetoraedes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.unitcorp.sistemavetoraedes.model.SituacaoImovel;

/**
 * Created by Kleber on 28/09/2016.
 */

public class DaoSituacaoImovel {

    private Context context;

    public DaoSituacaoImovel(Context context) {
        this.context = context;
    }

    public boolean gravar(SituacaoImovel situacaoImovel) {
        ContentValues content = new ContentValues();

        content.put("nome", situacaoImovel.getNome());

        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

        long resultado = db.insert("situacaoImovel", null, content);

        DataBaseHelper.getInstance().closeDatabase();

        return resultado != -1;
    }

    public boolean alterar(SituacaoImovel situacaoImovel) {
        ContentValues content = new ContentValues();
        content.put("nome", situacaoImovel.getNome());


        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

        long resultado = db.update("situacaoImovel", content, "_id = ?",
                new String[]{"" + situacaoImovel.getId()});

        DataBaseHelper.getInstance().closeDatabase();

        return resultado != -1;
    }

    public boolean remover(SituacaoImovel situacaoImovel) {
        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();
        String where[] = new String[]{situacaoImovel.getId() + ""};
        long resultado = db.delete("situacaoImovel", "_id = ?", where);

        DataBaseHelper.getInstance().closeDatabase();

        return resultado != -1;
    }

    public List<SituacaoImovel> getSituacaoImovels() {
        List<SituacaoImovel> lista = new ArrayList<SituacaoImovel>();
        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();
        Cursor cursor = db.rawQuery("SELECT _id, nome FROM situacaoImovel ", null);
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            SituacaoImovel situacaoImovel = new SituacaoImovel();
            situacaoImovel.setId(cursor.getInt(0));
            situacaoImovel.setNome(cursor.getString(1));
            lista.add(situacaoImovel);
            cursor.moveToNext();
        }

        cursor.close();
        DataBaseHelper.getInstance().closeDatabase();

        return lista;
    }

    public SituacaoImovel getSituacaoImoveloByID(long id) {
        SituacaoImovel situacaoImovel = null;
        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

        Cursor cursor =
                db.rawQuery("SELECT _id, nome FROM situacaoImovel  where _id = ?", new String[]{id + ""});
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            situacaoImovel = new SituacaoImovel();
            situacaoImovel.setId(cursor.getInt(0));
            situacaoImovel.setNome(cursor.getString(1));

        }

        cursor.close();
        DataBaseHelper.getInstance().closeDatabase();

        return situacaoImovel;
    }

}
