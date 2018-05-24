package Peca;

import java.awt.Image;

import Tabuleiro.*;

public class Torre extends Peca {
	
	public Torre(char cor, Image imagem, int posicaoX, int posicaoY, String nome) {
		super(cor,imagem,posicaoX,posicaoY, nome);
	}
	
	@Override
	public char[][] movsPossiveis(int posX, int posY, char cor) {
		Tabuleiro tabuleiro = new Tabuleiro();
		char tab[][];
		tab = tabuleiro.getTab();
		
		char mat[][];
		mat = iniciaPosMov();
		
		//movimento vertical
		for(int i = 0; i < 7; i++){
			if(posY - i > 0) {
				if(tab[posX][posY - 1] == 'x') 
					mat[posX][posY - 1] = 'v';
				else if(cor == 'B' && Character.isUpperCase(tab[posX][posY - 1]))
					mat[posX][posY - 1] = 'a';
				else if(cor == 'P' && !Character.isUpperCase(tab[posX][posY - 1])) 
					mat[posX][posY - 1] = 'a';
			}
			if(posY + i < 8) {
				if(tab[posX][posY + 1] == 'x') 
					mat[posX][posY + 1] = 'v';
				else if(cor == 'B' && Character.isUpperCase(tab[posX][posY - 1]))
					mat[posX][posY + 1] = 'a';
				else if(cor == 'P' && !Character.isUpperCase(tab[posX][posY - 1])) 
					mat[posX][posY + 1] = 'a';
			}
		}
		//movimento horizontal
		for(int i = 0; i < 7; i++){
			if(posX > 0) {
				if(tab[posX - 1][posY] == 'x') 
					mat[posX - 1][posY] = 'v';
				else if(cor == 'B' && Character.isUpperCase(tab[posX - 1][posY]))
					mat[posX - 1][posY] = 'a';
				else if(cor == 'P' && !Character.isUpperCase(tab[posX - 1][posY])) 
					mat[posX - 1][posY] = 'a';
			}
			if(posX < 8) {
				if(tab[posX + 1][posY] == 'x') 
					mat[posX + 1][posY] = 'v';
				else if(cor == 'B' && Character.isUpperCase(tab[posX + 1][posY]))
					mat[posX + 1][posY] = 'a';
				else if(cor == 'P' && !Character.isUpperCase(tab[posX + 1][posY])) 
					mat[posX + 1][posY] = 'a';
			}
		}
		
		return mat;
	}

}
