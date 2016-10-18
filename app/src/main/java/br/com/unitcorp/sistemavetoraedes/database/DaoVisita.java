package br.com.unitcorp.sistemavetoraedes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.unitcorp.sistemavetoraedes.model.Visita;

/**
 * Created by Kleber on 18/10/2016.
 */

public class DaoVisita {

        private Context context;

        public DaoVisita(Context context) {
            this.context = context;
        }

        public boolean gravar(Visita visita) {
            ContentValues content = new ContentValues();

            content.put("nome", visita.getNumero());

            DataBaseHelper.initializeInstance(this.context);
            SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

            long resultado = db.insert("visita", null, content);

            DataBaseHelper.getInstance().closeDatabase();

            return resultado != -1;
        }

        public boolean alterar(Visita visita) {
            ContentValues content = new ContentValues();
            content.put("nome", visita.getNumero());


            DataBaseHelper.initializeInstance(this.context);
            SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

            long resultado = db.update("atividae", content, "_id = ?",
                    new String[]{"" + visita.getId()});

            DataBaseHelper.getInstance().closeDatabase();

            return resultado != -1;
        }

        public boolean remover(Visita visita) {
            DataBaseHelper.initializeInstance(this.context);
            SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();
            String where[] = new String[]{visita.getId() + ""};
            long resultado = db.delete("visita", "_id = ?", where);

            DataBaseHelper.getInstance().closeDatabase();

            return resultado != -1;
        }

        public List<Visita> getVisitas() {
            List<Visita> lista = new ArrayList<>();
            DataBaseHelper.initializeInstance(this.context);
            SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();
            Cursor cursor = db.rawQuery("SELECT r._id, r.nome FROM Visita r",null);

            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {
                Visita visita = new Visita();
                visita.setId(cursor.getInt(0));
                visita.setNumero(cursor.getString(1));
                lista.add(visita);
                cursor.moveToNext();
            }

            cursor.close();
            DataBaseHelper.getInstance().closeDatabase();

            return lista;
        }

        public Visita geVisitaByID(long id) {
            Visita visita = null;
            DataBaseHelper.initializeInstance(this.context);
            SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

            Cursor cursor =
                    db.rawQuery("SELECT _id, nome FROM visita  where _id = ?", new String[]{id + ""});
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {
                visita = new Visita();
                visita.setId(cursor.getInt(0));
                visita.setNumero(cursor.getString(1));

            }

            cursor.close();
            DataBaseHelper.getInstance().closeDatabase();

            return visita;
        }

    }
