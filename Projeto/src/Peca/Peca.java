package Peca;
import Tabuleiro.Tabuleiro;

public abstract class Peca {
	int posX, posY;
	char cor;
	
	public abstract char[][] movsPossiveis();
	
	protected char[][] iniciaPosMov() {
		char mat[][] = new char[8][8];
		
		for(int i=0; i< mat.length; i++) {
			for(int j=0; j< mat[i].length; j++) {
				mat[i][j] = 'x';
			}
		}
		return mat;
	}
	
	}
