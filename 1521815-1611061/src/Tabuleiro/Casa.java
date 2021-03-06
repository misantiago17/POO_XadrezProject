package Tabuleiro;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import Peca.*;

public class Casa {
	
	public Rectangle2D retangulo;
	public Peca peca;
	public Color cor;
	public Color corOriginal;

	public boolean movPossivel = false; 
	public boolean atcPossivel = false; 
	public boolean roquePossivel = false;
	
	public Casa(Rectangle2D ret, Peca peca, Color cor) {
		this.retangulo = ret;
		this.peca = peca;
		this.cor = cor;
		this.corOriginal = cor;
	}
	
	@Override
    public Casa clone(){
        Casa casa = new Casa(this.retangulo, this.peca, this.cor);
        return casa;
    }
	
}
