package Peca;
import Tabuleiro.Tabuleiro;

public abstract class Peca {
	int posX, posY;
	char cor;
	
	protected abstract Tabuleiro movsPossiveis();
	
	
	
	}
