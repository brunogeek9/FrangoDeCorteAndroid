package com.example.jamelli.frangodecorte.fuzzy;

import android.content.Context;

import com.example.jamelli.frangodecorte.MainActivity;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

import java.io.InputStream;

public class FuzzyFrangoDeCorte {
	private Context context;
	private FIS fis;
	//construtor da classe que carrega as regras para o objeto fis
	public FuzzyFrangoDeCorte(MainActivity tela){
		context = tela;
	}
	
	//altera as entradas do objeto fis
	public void setEntradas(FrangoDeCorte fdc){
		try{
			InputStream inputStream = context.getAssets().open("regras.fcl");
			FIS fis = FIS.load(inputStream, true);
		}catch(Exception e){
			System.err.println("Can't load file" + e);
		}

		fis.setVariable("idadeAve", fdc.getIdadeAve());
        
        fis.setVariable("temperaturaDoAr", fdc.getTemperaturaDoAr());
        
        fis.setVariable("umidadeRelativaDoAr", fdc.getUmidadeRelativa());
        //chama as regras apos alterar as entradas
		fis.evaluate();
	}
	
	//retorna os conjuntos fuzzy modelados em forma de grafico
	public void graficosConjuntos(){
		JFuzzyChart.get().chart(fis.getFunctionBlock("frangoDeCorte"));
	}
	
	//retorna os conjuntos fuzzy de saida do sistema(grafico)
	public void graficosSaidas(){
		Variable cr = fis.getFunctionBlock("frangoDeCorte").getVariable("CR");
	    Variable ca = fis.getFunctionBlock("frangoDeCorte").getVariable("CA");
	    Variable gp = fis.getFunctionBlock("frangoDeCorte").getVariable("GP");
		JFuzzyChart.get().chart(cr, cr.getDefuzzifier(), true);
		JFuzzyChart.get().chart(ca, ca.getDefuzzifier(), true);
		JFuzzyChart.get().chart(gp, gp.getDefuzzifier(), true);
	}
	
	//retorna as saidas de maneira numerica, com [0] = cr, [1] = ca, [2] = gp
	public Double[] getSaidas(){

		Variable cr = fis.getVariable("CR");
	    Variable ca = fis.getVariable("CA");
	    Variable gp = fis.getVariable("GP");
		
		Double[] saidas = new Double[3];
		saidas[0] = cr.getValue();
		saidas[1] = ca.getValue();
		saidas[2] = gp.getValue();
		return saidas;
		
	}
	
	
}
