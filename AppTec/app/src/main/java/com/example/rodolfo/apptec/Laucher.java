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

    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);

        TabHost tabHost = getTabHost();

        TabHost.TabSpec tab2 = tabHost.newTabSpec("Log in");
        tab2.setIndicator("Log In");
        tab2.setContent(new Intent(this, LogIn.class));
        tabHost.addTab(tab2);

        TabHost.TabSpec tab1 = tabHost.newTabSpec("cadastro");
        tab1.setIndicator("cadastro");
        tab1.setContent(new Intent(this, EmployeeRegistration.class));
        tabHost.addTab(tab1);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
