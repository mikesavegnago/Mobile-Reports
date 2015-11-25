package com.example.mikesavegnago.mobilereport;

/**
 * Created by Mike Savegnago on 20/11/2015.
 */
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ItensActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    private List<Itens> itensList;
    private RecyclerView mRecyclerView;
    private TextView textTotal;
    private TextView textData;
    private static final DecimalFormat formato = new DecimalFormat("#.00");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itens_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textTotal = (TextView) findViewById(R.id.num_total);
        textData = (TextView) findViewById(R.id.num_data);
        Calendar dataAtual = Calendar.getInstance();
        textData.setText(dateFormat.format(dataAtual.getTime()));

        // Carrega lista de itens
        itensList = gerarListaItens();

        // RECYCLER VIEW
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        // Faz o primeiro carregamento da lista recycle view
        ItensRecycleAdapter adapter = new ItensRecycleAdapter(getApplicationContext(), itensList);
        mRecyclerView.setAdapter(adapter);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
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

    private List<Itens> gerarListaItens() {
        String[] nomes = new String[] {"Cereal","Martelo","Sabão","Luminária","Teclado","Mouse","Cadeira",
                "Mesa","Cerâmica","Monitor"};
        Double[] valores = new Double[] {2.49, 7.05, 5.10, 46.90, 113.89, 22.10, 65.00, 98.30, 230.90, 33.00};
        List<Itens> itensArrayList = new ArrayList<>();
        Double soma = 0.0;

        for (int i = 0; i < 10; i++) {
            soma += valores[i];
            Itens itens = new Itens(nomes[i], valores[i]);
            itensArrayList.add(itens);
        }
        textTotal.setText(String.valueOf(formato.format(soma)));
        return itensArrayList;
    }
}
