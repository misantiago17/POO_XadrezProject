package Peca;

import java.awt.Image;

import Interface.XadrezFrame;
import Tabuleiro.*;


public class Torre extends Peca {
	
	public Torre(char cor, int posicaoX, int posicaoY) {
		super(cor,posicaoX,posicaoY);
		
		String nomeImg;
		if(cor == 'B') 
			nomeImg = "CyanR.png";
		else
			nomeImg = "PurpleR.png";
				
		this.imagem = buscaNomeImg(nomeImg);
	}
	
	@Override
	public char[][] movsPossiveis() {
		Tabuleiro tabuleiro = Tabuleiro.currentTable;
		char tab[][];
		tab = tabuleiro.getTabChar();
		
		char mat[][];
		mat = iniciaPosMov();
		
		//movimento vertical
		for(int i = 0; i < 7; i++){
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
		//movimento horizontal
		for(int i = 0; i < 7; i++){
			if(posX - i > 0) {
				if(tab[posX - i][posY] == 'x') 
					mat[posX - i][posY] = 'v';
				else if(cor == 'B' && Character.isUpperCase(tab[posX - i][posY]))
					mat[posX - i][posY] = 'a';
				else if(cor == 'P' && !Character.isUpperCase(tab[posX - i][posY])) 
					mat[posX - i][posY] = 'a';
			}
			if(posX + i < 8) {
				if(tab[posX + i][posY] == 'x') 
					mat[posX + i][posY] = 'v';
				else if(cor == 'B' && Character.isUpperCase(tab[posX + i][posY]))
					mat[posX + i][posY] = 'a';
				else if(cor == 'P' && !Character.isUpperCase(tab[posX + i][posY])) 
					mat[posX + i][posY] = 'a';
			}
		}
		
		return mat;
	}


}
