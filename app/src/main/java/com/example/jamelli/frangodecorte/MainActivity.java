package com.example.jamelli.frangodecorte;

import android.content.Context;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jamelli.frangodecorte.fuzzy.FrangoDeCorte;
import com.example.jamelli.frangodecorte.fuzzy.FuzzyFrangoDeCorte;

public class MainActivity extends AppCompatActivity {
    Button bnt_salvar;

    EditText et_idade;
    EditText et_temperatura;
    EditText et_umidade;
    double idade = 0,temperatura = 0,umidade = 0;
    Context context = getApplicationContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnt_salvar = (Button) findViewById(R.id.button);

        et_idade = (EditText) findViewById(R.id.editText);
        et_temperatura = (EditText) findViewById(R.id.editText2);
        et_umidade = (EditText) findViewById(R.id.editText3);
        bnt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idade = Double.parseDouble(et_idade.getText().toString());
                temperatura = Double.parseDouble(et_temperatura.getText().toString());
                umidade = Double.parseDouble(et_umidade.getText().toString());

                new Thread(new Runnable() {
                    public void run() {
                        FrangoDeCorte f = new FrangoDeCorte(idade,temperatura,umidade);
                        FuzzyFrangoDeCorte fz = new FuzzyFrangoDeCorte();
                        fz.setEntradas(f);

                        //mostrando um toast com o resultado
                        String text = String.valueOf("CR "+fz.getSaidas()[0] + "\nCA "+fz.getSaidas()[1] +
                                "\nGP " + fz.getSaidas()[2]);
                        //Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                        //toast.show();
                        Log.i("fuzzy",text);

                    }
                }).start();
            }
        });
    }
}
