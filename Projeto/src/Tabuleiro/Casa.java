package Tabuleiro;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import Peca.Peca;

public class Casa {
	
	public Rectangle2D retangulo;
	public Peca peca;
	public Color cor;
	public Color corOriginal;
	public int teste = 5;
	
	public boolean movPossivel = false; 
	public boolean atcPossivel = false; 
	
	public Casa(Peca peca) {
		this.peca = peca;
		this.teste = 0;
		
		this.retangulo = null;
		this.cor = null;
		this.corOriginal = null;
	}
	
	public Casa(Rectangle2D ret, Peca peca, Color cor) {
		this.retangulo = ret;
		this.peca = peca;
		this.cor = cor;
		this.corOriginal = cor;
	}

}
