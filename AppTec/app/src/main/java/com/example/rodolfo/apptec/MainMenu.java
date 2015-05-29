package com.example.rodolfo.apptec;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

public class MainMenu extends TabActivity {
    public static final int MENU_ADD_ENTERPRISE = 1;
    AlertDialog.Builder dialog;

    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);

        TabHost tabHost = getTabHost();

        TabHost.TabSpec tab1_report = tabHost.newTabSpec("Relatório");
        tab1_report.setIndicator("Relatório");
        tab1_report.setContent(new Intent(this, RelatorioFot_activity.class));
        tabHost.addTab(tab1_report);

        TabHost.TabSpec tab2_searchForReport = tabHost.newTabSpec("Buscar Relatório");
        tab2_searchForReport.setIndicator("Buscar Relatório");
        tab2_searchForReport.setContent(new Intent(this, SearchForReport.class));

        tabHost.addTab(tab2_searchForReport);

    }
    @Override
      public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_enterprise, menu);
        MenuItem add_enterprise = menu.add(0, MENU_ADD_ENTERPRISE, 0, "Add Empresa");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case MENU_ADD_ENTERPRISE:
                setDialogFeturesAndShow();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void setDialogFeturesAndShow(){
        dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        dialog.setView(inflater.inflate(R.layout.dialog_layout, null)).
                setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // adicionar empresa no DB
                    }
                });
        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do fucking nothing
            }
        });
        dialog.show();
    }

}
