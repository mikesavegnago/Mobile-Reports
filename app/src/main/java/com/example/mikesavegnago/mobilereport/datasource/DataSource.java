package com.example.mikesavegnago.mobilereport.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mikesavegnago.mobilereport.datamodel.DataModel;

/**
 * Created by Mike Savegnago on 25/11/2015.
 */

public class DataSource extends SQLiteOpenHelper {


    SQLiteDatabase db;

    public DataSource(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DataModel.getDbName(), null, 1);

        db = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataModel.criarTabelaEmpresa());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("DB",
                "Atualizando versão do DB " + oldVersion + " para "
                        + newVersion + ", o que apagará todos os dados");
        db.execSQL("DROP TABLE IF EXISTS "+DataModel.getTblEmpresa());
        onCreate(db);

    }
    public void inserir(ContentValues valores, String tabela){

        db.insert(tabela, null, valores);
    }


    public void atualizar(ContentValues valores, String tabela){
        if(valores.containsKey("id") &&
                valores.getAsInteger("id") != null &&
                valores.getAsInteger("id") != 0){
            Integer id = valores.getAsInteger("id");
            db.update(tabela, valores, "id = "+ id, null);

        }
    }

    public Cursor getEmpresas()
    {
        Cursor c;
        c = db.query(DataModel.getTblEmpresa(),
                new String[] {String.valueOf(DataModel.getID()),
                        String.valueOf(DataModel.getRAZAO_SOCIAL()),
                        String.valueOf(DataModel.getNOME_FANTASIA()),
                        String.valueOf(DataModel.getENDERECO()),
                        String.valueOf(DataModel.getCNPJ())
                        },
                (DataModel.getID() + " > 0"),
                null,
                null,
                null,
                DataModel.getID()+" DESC");

        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getEmpresa()
    {
        Cursor c;
        c = db.query(DataModel.getTblEmpresa(),
                new String[] {DataModel.getID(),
                        DataModel.getNOME_FANTASIA(),
                        DataModel.getRAZAO_SOCIAL(),
                        DataModel.getENDERECO(),
                        DataModel.getCNPJ(),
                },
                (DataModel.getID() + " > 0"),
                null,
                null,
                null,
                DataModel.getID()+" DESC");

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }
    public boolean temEmpresa(int idEmpresa)
    {

        Cursor c = db.rawQuery("select COUNT(*) FROM "+ DataModel.getTblEmpresa() +
                " where " + DataModel.getID() + "= "+idEmpresa, null);

        if (!c.moveToFirst())
        {
            return false;
        }
        int count = c.getInt(0);

        return count > 0;
    }
}
