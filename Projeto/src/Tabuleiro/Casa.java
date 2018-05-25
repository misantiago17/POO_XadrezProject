package Tabuleiro;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import Peca.Peca;

public class Casa {
	
	public Rectangle2D retangulo;
	public Peca peca;
	public Color cor;
	
	public boolean movPossivel = false; 
	public boolean atcPossivel = false; 
	
	public Casa(Rectangle2D ret, Peca peca, Color cor) {
		this.retangulo = ret;
		this.peca = peca;
		this.cor = cor;
	}

}
