package com.example.mikesavegnago.mobilereport;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mikesavegnago.mobilereport.datamodel.DataModel;
import com.example.mikesavegnago.mobilereport.datasource.DataSource;
import com.example.mikesavegnago.mobilereport.model.Empresa;

/**
 * Created by Mike Savegnago on 23/11/2015.
 */
public class DadosActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    protected EditText editRazao;
    protected EditText editNome;
    protected EditText editEndereco;
    protected EditText editCnpj;
    protected Button btSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        DataSource dataSource = new DataSource(getApplicationContext(),"",null,1);
        Cursor cursor = dataSource.getEmpresas();

        editNome = (EditText) findViewById(R.id.edit_nome);
        editRazao = (EditText) findViewById(R.id.edit_razao);
        editEndereco = (EditText) findViewById(R.id.edit_endereco);
        editCnpj = (EditText) findViewById(R.id.edit_cnpj);
        btSalvar = (Button) findViewById(R.id.button_salvar);

        if (cursor.getCount() >0 ) {
            editRazao.setText(cursor.getString(cursor.getColumnIndex("razao_social")));
            editNome.setText(cursor.getString(cursor.getColumnIndex("nome_fantasia")));
            editEndereco.setText(cursor.getString(cursor.getColumnIndex("endereco")));
            editCnpj.setText(cursor.getString(cursor.getColumnIndex("cnpj")));
        }

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Empresa empresa = new Empresa();
                empresa.setRazaoSocial(editRazao.getText().toString());
                empresa.setNomeFantasia(editNome.getText().toString());
                empresa.setEndereco(editEndereco.getText().toString());
                empresa.setCnpj(editCnpj.getText().toString());

                DataSource dataSource = new DataSource(getApplicationContext(),"",null,1);
                Cursor cursor = dataSource.getEmpresas();

                ContentValues valores = new ContentValues();
                valores.put(DataModel.getNOME_FANTASIA(),empresa.getNomeFantasia());
                valores.put(DataModel.getRAZAO_SOCIAL(), empresa.getRazaoSocial());
                valores.put(DataModel.getENDERECO(), empresa.getEndereco());
                valores.put(String.valueOf(DataModel.getCNPJ()), empresa.getCnpj());
                if (cursor.getCount() > 0) {
                    valores.put("id",cursor.getString(cursor.getColumnIndex("id")));
                    dataSource.atualizar(valores, DataModel.getTblEmpresa());
                    Toast.makeText(getApplicationContext(), "Dados Alterados!", Toast.LENGTH_LONG).show();
                } else {
                    dataSource.inserir(valores, DataModel.getTblEmpresa());
                    Toast.makeText(getApplicationContext(), "Dados Adicionados!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "Autores: Mike Savegnago, Lindzionei Zanin!", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(this.getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();

            return true;
        } else if (id == R.id.nav_dados) {
            Intent i = new Intent(this.getApplicationContext(), DadosActivity.class);
            startActivity(i);
            finish();

            return true;
        } else if (id == R.id.nav_grafico) {
            Intent i = new Intent(this.getApplicationContext(), GraphActivity.class);
            startActivity(i);
            finish();

            return true;
        } else if (id == R.id.nav_itens) {
            Intent i = new Intent(this.getApplicationContext(), ItensActivity.class);
            startActivity(i);
            finish();

            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
