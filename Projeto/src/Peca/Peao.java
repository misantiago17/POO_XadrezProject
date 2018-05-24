package Peca;

import java.awt.Image;
import Tabuleiro.*;

public class Peao extends Peca {
	
	private boolean hasMoved;
	
	public Peao(char cor, Image imagem, int posicaoX, int posicaoY, String nome) {
		super(cor,imagem,posicaoX,posicaoY, nome);
	}

	@Override
	public char[][] movsPossiveis() {
		Tabuleiro tabuleiro = new Tabuleiro();
		char tab[][];
		tab = tabuleiro.getTab();
		
		char mat[][];
		mat = iniciaPosMov();
		
		int maxMov;
		
		if(hasMoved)
			maxMov = 1;
		else
			maxMov = 2;
		
		for(int i = 0; i < maxMov; i++){
			if(posY - i > 0) {
				if(tab[posX][posY - i] == 'x') 
					mat[posX][posY - i] = 'v';
				else if(cor == 'B' && Character.isUpperCase(tab[posX][posY - i]))
					mat[posX][posY - i] = 'a';
				else if(cor == 'P' && !Character.isUpperCase(tab[posX][posY - i])) 
					mat[posX][posY - i] = 'a';
			}
			if(posY + i < 8) {
				if(tab[posX][posY + i] == 'x') 
					mat[posX][posY + i] = 'v';
				else if(cor == 'B' && Character.isUpperCase(tab[posX][posY - i]))
					mat[posX][posY + i] = 'a';
				else if(cor == 'P' && !Character.isUpperCase(tab[posX][posY - i])) 
					mat[posX][posY + i] = 'a';
			}
		}
		
		return mat;
	}

}
