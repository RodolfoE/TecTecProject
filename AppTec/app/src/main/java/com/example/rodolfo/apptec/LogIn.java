package com.example.rodolfo.apptec;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import Databases.Db_employees;
import db_Lines.Employee;

public class LogIn extends Activity {
    public static final String FileName = "SharedForRadioButton";

    private static final String ERRO = "EXCEPTIONS";
    ProgressDialog dialog;
    EditText  mEdit_senha;
    AutoCompleteTextView mEdit_nome;
    Button mBtn_login;
    RadioGroup radioGroup;
    ProgressBar progressBar;
    int mProgresso = 0, mMax = 100;
    Handler handler = new Handler();
    SharedPreferences preferences;
    Db_employees mDbEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        setAutoCompleteTextView();
        setRadioGroup();
        setLoggin();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //For each time the user get back to this activity, the database will be rechecked, and if any update had been done, it'll populate the autocomplete once again.
        setAutoCompleteTextView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDbEmployee.close();
    }

    private void setLoggin() {
        mEdit_senha = (EditText) findViewById(R.id.edit_cad_senha);
        mBtn_login = (Button) findViewById(R.id.btn_login);
        mBtn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verificarEntrada())
                    startActivity(new Intent(LogIn.this, MainMenu.class));
            }
        });
    }

    private void setRadioGroup() {
        preferences= getSharedPreferences(FileName, 0);

        if (preferences.contains("currentChecked")) {
            if (preferences.getBoolean("currentChecked", false)) {
                ((RadioButton) this.findViewById(R.id.ManterConectado)).setChecked(true);
            } else {
                ((RadioButton) this.findViewById(R.id.naoManterConectado)).setChecked(true);
            }
        }

        radioGroup = (RadioGroup) findViewById(R.id.cad_radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SharedPreferences.Editor edit = preferences.edit();

                if (checkedId == R.id.ManterConectado) {
                    edit.putBoolean("currentChecked", true);

                } else {
                    edit.putBoolean("currentChecked", false);
                }
                edit.commit();
            }
        });
    }

    private void setAutoCompleteTextView() {
        mDbEmployee = new Db_employees(this);
        mEdit_nome = (AutoCompleteTextView) findViewById(R.id.edit_cad_nome);
        ArrayList<Employee> employees = mDbEmployee.getAllEmployee();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        for (Employee i : employees){
            adapter.add(i.getName());
        }
        mEdit_nome.setAdapter(adapter);
    }

    //mudar essa lógica dps
    boolean flag = false;
    private boolean verificarEntrada(){



        new Thread(){
            @Override
            public void run() {
                progressBar = (ProgressBar) findViewById(R.id.barra_de_progresso);
                try {
                    String name = mEdit_nome.getText().toString();
                    String password = mEdit_senha.getText().toString();

                    if (mDbEmployee.verifyLogIn(new Employee(name, password, null)))
                    {
                        flag = true;
                        setContentView(R.layout.maintheme);
                        while (mProgresso < mMax) {
                            Thread.sleep(2000);
                            mProgresso = addProgresso(mProgresso);

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(mProgresso);
                                }
                            });
                        }
                    } else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LogIn.this, "Usuário não existe", Toast.LENGTH_LONG).show();
                                flag = false;
                            }
                        });
                    }
                } catch(Exception ex){
                    Log.e(ERRO, ex.getMessage());
                }
            }
        }.start();

        return flag;
    }

    private int addProgresso(int progresso){
        progresso++;
        try{
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return progresso;
    }

}
