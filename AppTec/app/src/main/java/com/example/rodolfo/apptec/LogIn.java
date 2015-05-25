package com.example.rodolfo.apptec;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.RadioGroup;
import android.widget.Toast;

public class LogIn extends Activity {
    private String[] mNome_Usuarios = {"Jose", "Maria", "Joao", "Barbara", "Lucimar"};
    private static final String ERRO = "EXCEPTIONS";
    ProgressDialog dialog;
    EditText  mEdit_senha;
    AutoCompleteTextView mEdit_nome;
    Button mBtn_login;
    RadioGroup radioGroup;
    ProgressBar progressBar;
    int mProgresso = 0, mMax = 100;
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        radioGroup = (RadioGroup) findViewById(R.id.cad_radioGroup);

        mEdit_nome = (AutoCompleteTextView) findViewById(R.id.edit_cad_nome);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mNome_Usuarios);
        mEdit_nome.setAdapter(adapter);

        mEdit_senha = (EditText) findViewById(R.id.edit_cad_senha);
        mBtn_login = (Button) findViewById(R.id.btn_login);
        mBtn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.maintheme);
                verificarEntrada();
                startActivity(new Intent(LogIn.this, MainMenu.class));
            }
        });
    }

    private boolean verificarEntrada(){

        new Thread(){
            @Override
            public void run() {
                progressBar = (ProgressBar) findViewById(R.id.barra_de_progresso);
                try {
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
                } catch(InterruptedException ex){
                    Log.e(ERRO, ex.getMessage());
                }
            }
        }.start();

        if (R.id.ManterConectado == radioGroup.getCheckedRadioButtonId())
            Toast.makeText(this, "Você escolheu se manter conectado!", Toast.LENGTH_SHORT).show();

        return true;
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
