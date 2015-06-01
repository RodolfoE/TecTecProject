package com.example.rodolfo.apptec;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;

import Databases.Db_phot_report;

public class RelatorioFot_activity extends Activity {
    Db_phot_report mDataBaseReport;
    AutoCompleteTextView mEdit_name_enterprise;

    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.relatoriofot_layout);
        mDataBaseReport = new Db_phot_report(this);
        setEditNameEnterprise();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDataBaseReport.close();
    }

    private void setEditNameEnterprise() {
        Cursor cursor = mDataBaseReport.getAllEnterprise();
        ArrayList<String> enterpriseNames = new ArrayList<String>();

        while (cursor.moveToNext()){
            enterpriseNames.add(cursor.getString(1));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, enterpriseNames);
        mEdit_name_enterprise = (AutoCompleteTextView) findViewById(R.id.edit_nome_empresa);
        mEdit_name_enterprise.setAdapter(adapter);
    }
}
