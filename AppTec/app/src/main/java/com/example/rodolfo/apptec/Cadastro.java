package com.example.rodolfo.apptec;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Cadastro extends Activity {

    String[] mAreas = {"Mecânica", "Elétrica", "Moto a disel"};
    Spinner spinner;
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.cadastro_layout);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mAreas);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner) findViewById(R.id.spinner1);
        spinner.setAdapter(adapter);
    }
}
