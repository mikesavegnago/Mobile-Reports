package com.example.mikesavegnago.mobilereport.datamodel;

/**
 * Created by Mike Savegnago on 25/11/2015.
 */
public class DataModel {
    private static final String DB_NAME = "dbEmpresa.sqlite";
    private static final String TBL_EMPRESA = "tbl_empresa";
    private static final String ID = "id";
    private static final String RAZAO_SOCIAL = "razao_social";
    private static final String NOME_FANTASIA = "nome_fantasia";
    private static final String ENDERECO = "endereco";
    private static final String CNPJ = "cnpj";

    public static String criarTabelaEmpresa(){
        String query = "CREATE TABLE " + getTblEmpresa();
        query += " (";
        query += getID() + " INTEGER PRIMARY KEY , ";
        query += getRAZAO_SOCIAL() + " TEXT , ";
        query += getNOME_FANTASIA() + " TEXT , ";
        query += getENDERECO() + " TEXT , ";
        query += getCNPJ() + " INT ";
        query += " );";

        return query;
    }

    public static String getDbName() {
        return DB_NAME;
    }

    public static String getTblEmpresa() {
        return TBL_EMPRESA;
    }

    public static String getID() {
        return ID;
    }

    public static String getRAZAO_SOCIAL() {
        return RAZAO_SOCIAL;
    }

    public static String getNOME_FANTASIA() {
        return NOME_FANTASIA;
    }

    public static String getENDERECO() {
        return ENDERECO;
    }

    public static String getCNPJ() {
        return CNPJ;
    }

}
