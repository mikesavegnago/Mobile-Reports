package com.example.mikesavegnago.mobilereport.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.mikesavegnago.mobilereport.datamodel.DataModel;
import com.example.mikesavegnago.mobilereport.datasource.DataSource;
import com.example.mikesavegnago.mobilereport.model.Empresa;

import java.util.ArrayList;
/**
 * Created by Mike Savegnago on 25/11/2015.
 */
public class EmpresaDAO {
    private static DataSource ds;
    ContentValues valores;

    public EmpresaDAO(Context context){
        ds = new DataSource(context,"",null,1);

    }

    public boolean adicionar(Empresa e) throws Exception {
        boolean retorno = false;
        valores = new ContentValues();

        valores.put(DataModel.getNOME_FANTASIA(),e.getNomeFantasia());
        valores.put(DataModel.getRAZAO_SOCIAL(),e.getRazaoSocial());
        valores.put(DataModel.getENDERECO(), e.getEndereco());
        valores.put(String.valueOf(DataModel.getCNPJ()),e.getCnpj());

        ds.inserir(valores,DataModel.getTblEmpresa());
        retorno = true;

        return retorno;
    }

    public ArrayList<Empresa> getDados(){
        Cursor empresaDB = ds.getEmpresas();
        ArrayList<Empresa> empresas = new ArrayList<Empresa>();
        if(empresaDB.moveToFirst()){
            for(int i=0; i< empresaDB.getCount(); i++){
                int id = empresaDB.getInt(empresaDB.getColumnIndex("id"));
                String razao_social = empresaDB.getString(empresaDB.getColumnIndex("razao_social"));
                String nome_fantasia = empresaDB.getString(empresaDB.getColumnIndex("nome_fantasia"));
                String endereco = empresaDB.getString(empresaDB.getColumnIndex("endereco"));
                String cnpj = empresaDB.getString(empresaDB.getColumnIndex("cnpj"));

                Empresa tmp = new Empresa(id,razao_social,nome_fantasia,endereco,cnpj);
                empresas.add(tmp);
                empresaDB.moveToNext();

            }

        }
        return empresas;
    }
}
