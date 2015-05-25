package com.example.rodolfo.apptec;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

public class Laucher extends TabActivity {

    public static final int ADD_NOME_EMPRESA = 1;
    AlertDialog.Builder dialog;

    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);

        TabHost tabHost = getTabHost();

        TabHost.TabSpec tab2 = tabHost.newTabSpec("Log in");
        tab2.setIndicator("Log In");
        tab2.setContent(new Intent(this, LogIn.class));
        tabHost.addTab(tab2);

        TabHost.TabSpec tab1 = tabHost.newTabSpec("Cadastro");
        tab1.setIndicator("Cadastro");
        tab1.setContent(new Intent(this, Cadastro.class));
        tabHost.addTab(tab1);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_register_enterprise, menu);
        MenuItem add_nome_empresa = menu.add(0, ADD_NOME_EMPRESA, 0, "Add Empresa");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id)
        {
            case ADD_NOME_EMPRESA:
                dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Digite o nome da empresa");
                dialog.setIcon(R.drawable.icon_tec);
                dialog.show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}