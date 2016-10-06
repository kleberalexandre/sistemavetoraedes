package br.com.unitcorp.sistemavetoraedes.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Kleber on 28/09/2016.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "db1";
    private static int VERSAO = 1;
    private static DataBaseHelper instance;
    private static SQLiteOpenHelper mDatabaseHelper;
    private AtomicInteger mOpenCounter = new AtomicInteger();
    private SQLiteDatabase mDatabase;

    public DataBaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    public static synchronized void initializeInstance(Context context) {
        if (instance == null) {
            instance = new DataBaseHelper(context);
            mDatabaseHelper = instance;
        }
    }

    public static synchronized DataBaseHelper getInstance() {
        if (instance == null) {
            throw new IllegalStateException(DataBaseHelper.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Create", "Create");
        db.execSQL("CREATE TABLE usuario (_id INTEGER PRIMARY KEY," +
                " nome VARCHAR(50), " +
                " login VARCHAR(50)," +
                " senha VARCHAR(50));");

        db.execSQL("CREATE TABLE bairro (_id INTEGER PRIMARY KEY," +
                " nome VARCHAR(50));");


        db.execSQL("CREATE TABLE quadra (_id INTEGER PRIMARY KEY," +
                " nome VARCHAR(50), " +
                "idbairro int);");

        db.execSQL("CREATE TABLE rua (_id INTEGER PRIMARY KEY," +
                " nome VARCHAR(50), " +
                "idquadra int);");

        db.execSQL("CREATE TABLE atividade (_id INTEGER PRIMARY KEY," +
                " nome VARCHAR(50) );");

        db.execSQL("CREATE TABLE situacaoImovel (_id INTEGER PRIMARY KEY," +
                " nome VARCHAR(50) );");

        db.execSQL("CREATE TABLE recipienteEnc (_id INTEGER PRIMARY KEY," +
                " nome VARCHAR(50) );");

        db.execSQL("insert into usuario (nome, login, senha) values ('Admin','admin','123');");

        db.execSQL("insert into bairro (_id,nome) values (1,'Bom Pastor');");
        db.execSQL("insert into bairro (_id,nome) values (2,'Santa Paula');");

        db.execSQL("insert into quadra (nome,_id,idbairro) values ('q1',1,'1');");
        db.execSQL("insert into quadra (nome,_id,idbairro) values ('q1',2,'2');");

        db.execSQL("insert into rua (nome,idquadra) values ('Terra Roxa',1);");
        db.execSQL("insert into rua (nome, idquadra) values ('Sao Bento ',2);");

        db.execSQL("insert into Atividade (nome,idatividade) values ('Ponto Estratégico',1);");
        db.execSQL("insert into Atividade (nome,idatividade) values ('Pesquisa de Armadilhas',2);");

        db.execSQL("insert into RecipienteEnc (nome,idrecipienteEnc) values ('Pneus',1);");
        db.execSQL("insert into RecipienteEnc (nome,idrecipienteEnc) values ('Lata-Frascos-Plásticos ',2);");


        db.execSQL("insert into SituacaoImovel (nome,idsituacaoImovel) values ('Fechado',1);");
        db.execSQL("insert into SituacaoImovel (nome,idsituacaoImovel) values ('Desocupado',2);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
        }
    }

    public synchronized SQLiteDatabase openDatabase() {
        if (mOpenCounter.incrementAndGet() == 1) {
            // Opening new database
            mDatabase = mDatabaseHelper.getWritableDatabase();
        }
        return mDatabase;
    }

    public synchronized void closeDatabase() {
        if (mOpenCounter.decrementAndGet() == 0) {
            // Closing database
            mDatabase.close();

        }
    }
}
