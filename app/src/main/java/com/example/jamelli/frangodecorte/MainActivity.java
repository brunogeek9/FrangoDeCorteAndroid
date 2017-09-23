package com.example.jamelli.frangodecorte;

import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jamelli.frangodecorte.fuzzy.FrangoDeCorte;
import com.example.jamelli.frangodecorte.fuzzy.FuzzyFrangoDeCorte;

public class MainActivity extends AppCompatActivity {
    Button bnt_salvar;

    EditText et_idade;
    EditText et_temperatura;
    EditText et_umidade;
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
                double idade = Double.parseDouble(et_idade.getText().toString());
                double temperatura = Double.parseDouble(et_temperatura.getText().toString());
                double umidade = Double.parseDouble(et_umidade.getText().toString());
                FrangoDeCorte f = new FrangoDeCorte(idade,temperatura,umidade);
                FuzzyFrangoDeCorte fz = new FuzzyFrangoDeCorte();
                fz.setEntradas(f);
                fz.graficosConjuntos();
                fz.graficosSaidas();
            }
        });
    }
}
