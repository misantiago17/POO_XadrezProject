package Peca;

import java.awt.Image;

import Interface.XadrezFrame;
import Tabuleiro.*;

public class Bispo extends Peca {
	
	public Bispo(char cor, int posicaoX, int posicaoY) {
		super(cor,posicaoX,posicaoY);
		
		String nomeImg;
		if(cor == 'B') 
			nomeImg = "CyanB.png";
		else
			nomeImg = "PurpleB.png";
				
		this.imagem = buscaNomeImg(nomeImg);
	}

	@Override
	public char[][] movsPossiveis() {
		Tabuleiro tabuleiro = Tabuleiro.currentTable;
		char tab[][];
		tab = Tabuleiro.getTabChar();
		
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
