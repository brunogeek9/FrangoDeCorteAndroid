package com.example.jamelli.frangodecorte.fuzzy;

/**
 * Created by jamelli on 24/09/17.
 */

import com.fuzzylite.*;
import com.fuzzylite.imex.FclImporter;
import com.fuzzylite.variable.InputVariable;
import com.fuzzylite.variable.OutputVariable;

import java.io.File;
import java.io.IOException;

public class FrangoFuzzyLite {
    private Engine engine = null;
    private InputVariable idade,temperatura,umidade;
    private OutputVariable cr,ca,gp;
    public FrangoFuzzyLite(){
        File file = new File( "src/main/assets/regras.fcl" );

        try {
            engine = new FclImporter().fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder status = new StringBuilder();
        if (! engine.isReady(status))
            throw new RuntimeException("[engine error] engine is not ready:n" + status);

        idade = engine.getInputVariable("idadeAve");
        temperatura = engine.getInputVariable("temperaturaDoAr");
        umidade = engine.getInputVariable("umidadeRelativaDoAr");

        cr = engine.getOutputVariable("CR");
        ca = engine.getOutputVariable("CA");
        gp = engine.getOutputVariable("GP");

    }

    public void setEntradas(FrangoDeCorte f){
        idade.setValue(f.getIdadeAve());
        temperatura.setValue(f.getTemperaturaDoAr());
        umidade.setValue(f.getUmidadeRelativa());

    }

    public double[] getSaidas(){
        engine.process();
        double saidas[] = new double[3];
        saidas[0] = cr.getValue();
        saidas[1] = ca.getValue();
        saidas[2] = gp.getValue();
        return saidas;
    }

}
