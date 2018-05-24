package Peca;

import java.awt.Image;
import Tabuleiro.*;

public class Bispo extends Peca {
	
	public Bispo(char cor, Image img, int posicaoX, int posicaoY, String nome) {
		super(cor,img,posicaoX,posicaoY, nome);
	}

	@Override
	public char[][] movsPossiveis() {
		Tabuleiro tabuleiro = new Tabuleiro();
		char tab[][];
		tab = tabuleiro.getTab();
		
		char mat[][];
		mat = iniciaPosMov();
		
		//Mov diagonal
		for(int i = 0; i < 7; i++){
			if(posX - i > 0 && posY - i > 0) {
				if(tab[posX - i][posY - i] == 'x') 
					mat[posX - i][posY - i] = 'v';
				else if(cor == 'B' && Character.isUpperCase(tab[posX - i][posY - i]))
					mat[posX - i][posY - i] = 'a';
				else if(cor == 'P' && !Character.isUpperCase(tab[posX - i][posY - i])) 
					mat[posX - i][posY - i] = 'a';
			}
			if(posX - i > 0 && posY + i < 8) {
				if(tab[posX - i][posY + i] == 'x') 
					mat[posX - i][posY + i] = 'v';
				else if(cor == 'B' && Character.isUpperCase(tab[posX - i][posY + i]))
					mat[posX - i][posY + i] = 'a';
				else if(cor == 'P' && !Character.isUpperCase(tab[posX - i][posY + i])) 
					mat[posX - i][posY + i] = 'a';
			}
			if(posX + i < 8 && posY - i > 0) {
				if(tab[posX + i][posY - i] == 'x') 
					mat[posX + i][posY - i] = 'v';
				else if(cor == 'B' && Character.isUpperCase(tab[posX + i][posY - i]))
					mat[posX + i][posY - i] = 'a';
				else if(cor == 'P' && !Character.isUpperCase(tab[posX + i][posY - i])) 
					mat[posX + i][posY - i] = 'a';
			}
			if(posX + i < 8 && posY + i <  8) {
				if(tab[posX + i][posY + i] == 'x') 
					mat[posX + i][posY + i] = 'v';
				else if(cor == 'B' && Character.isUpperCase(tab[posX + i][posY + i]))
					mat[posX + i][posY + i] = 'a';
				else if(cor == 'P' && !Character.isUpperCase(tab[posX + i][posY + i])) 
					mat[posX + i][posY + i] = 'a';
			}
		}
		return mat;
	}


}
