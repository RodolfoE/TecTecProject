package com.example.rodolfo.apptec;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import Databases.Db_employees;
import db_Lines.Employee;

public class EmployeeRegistration extends Activity {
    EditText edit_cadastro_nome, edit_cadastro_senha;
    Db_employees mDb_employees;
    String[] mAreas = {"Mecânica", "Elétrica", "Moto a disel"};
    Spinner spinner;
    Button mSaveRegistration;

    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.cadastro_layout);

        instantiateDataBase();
        instantiateSaveRegistration();
        instantiateSpinner();
    }

    private void instantiateSpinner() {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mAreas);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner) findViewById(R.id.spinner1);
        spinner.setAdapter(adapter);
    }

    private void instantiateSaveRegistration() {
        //instantiating the edit_cadastro_nome and the edit_cadastro_senha
        edit_cadastro_nome = (EditText) findViewById(R.id.edit_cadastro_nome);
        edit_cadastro_senha = (EditText) findViewById(R.id.edit_cadastro_senha);

        //instantiating the saveRegistration button for latter use
        mSaveRegistration = (Button) findViewById(R.id.btn_save_registration);
        mSaveRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edit_cadastro_nome.getText().toString();
                String senha = edit_cadastro_senha.getText().toString();
                String area = spinner.getSelectedItem().toString();

                Employee employee = new Employee(nome, senha, area);

                if (mDb_employees.existsEmployee(nome)){
                    Toast.makeText(EmployeeRegistration.this, "Nome de usuário já existe\nInsira outro nome", Toast.LENGTH_LONG).show();
                } else {
                    mDb_employees.insertEmployee(employee);
                }
            }
        });
    }

    private void instantiateDataBase() {
        mDb_employees = new Db_employees(this);
    }
}
