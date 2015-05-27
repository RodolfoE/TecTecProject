package com.example.rodolfo.apptec;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class Laucher extends TabActivity {

    public static final int ADD_NOME_EMPRESA = 1;
    AlertDialog.Builder dialog;
    TabHost tabHost;

    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);

        tabHost = getTabHost();

        TabHost.TabSpec tab2 = tabHost.newTabSpec("Log in");
        tab2.setIndicator("Log In");
        tab2.setContent(new Intent(this, LogIn.class));
        tabHost.addTab(tab2);

        TabHost.TabSpec tab1 = tabHost.newTabSpec("cadastro");
        tab1.setIndicator("cadastro");
        tab1.setContent(new Intent(this, EmployeeRegistration.class));
        tabHost.addTab(tab1);


    }
    public void changeToTab(int i){
        tabHost.setCurrentTab(i);
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
