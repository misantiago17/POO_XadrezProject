package Peca;

import java.awt.Image;
import Tabuleiro.*;

public class Rainha extends Peca {
	
	public Rainha(char cor, Image imagem, int posicaoX, int posicaoY, String nome) {
		super(cor,imagem,posicaoX,posicaoY, nome);
	}

	@Override
	public char[][] movsPossiveis() {
		Tabuleiro tabuleiro = new Tabuleiro();
		char tab[][];
		tab = tabuleiro.getTab();
		
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
