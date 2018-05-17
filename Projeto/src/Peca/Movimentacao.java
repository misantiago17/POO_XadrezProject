package Peca;

import Tabuleiro.Tabuleiro;

public class Movimentacao {
	
	private char[][] iniciaPosMov() {
		char mat[][] = new char[8][8];
		
		for(int i=0; i< mat.length; i++) {
			for(int j=0; j< mat[i].length; j++) {
				mat[i][j] = 'x';
			}
		}
		return mat;
	}
	
	char[][] vertHor(int posX, int posY, char cor) {
		Tabuleiro tabuleiro = new Tabuleiro();
		char tab[][];
		tab = tabuleiro.getTab();
		
		char mat[][];
		mat = iniciaPosMov();
		
		//movimento vertical
		if(posY > 0) {
			if(tab[posX][posY - 1] == 'x') 
				mat[posX][posY - 1] = 'v';
			else if(cor == 'B' && Character.isUpperCase(tab[posX][posY - 1]))
				mat[posX][posY - 1] = 'a';
			else if(cor == 'P' && !Character.isUpperCase(tab[posX][posY - 1])) 
				mat[posX][posY - 1] = 'a';
		}
		if(posY < 8) {
			if(tab[posX][posY + 1] == 'x') 
				mat[posX][posY + 1] = 'v';
			else if(cor == 'B' && Character.isUpperCase(tab[posX][posY - 1]))
				mat[posX][posY + 1] = 'a';
			else if(cor == 'P' && !Character.isUpperCase(tab[posX][posY - 1])) 
				mat[posX][posY + 1] = 'a';
		}
		
		//movimento horizontal
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
		
		return mat;
	}
	

	
	char[][] diagonal(int posX, int posY, char cor) {
		Tabuleiro tabuleiro = new Tabuleiro();
		char tab[][];
		tab = tabuleiro.getTab();
		
		char mat[][];
		mat = iniciaPosMov();
		
		if(posX > 0 && posY > 0) {
			if(tab[posX - 1][posY - 1] == 'x') 
				mat[posX - 1][posY - 1] = 'v';
			else if(cor == 'B' && Character.isUpperCase(tab[posX - 1][posY - 1]))
				mat[posX - 1][posY - 1] = 'a';
			else if(cor == 'P' && !Character.isUpperCase(tab[posX - 1][posY - 1])) 
				mat[posX - 1][posY - 1] = 'a';
		}
		if(posX > 0 && posY < 8) {
			if(tab[posX - 1][posY + 1] == 'x') 
				mat[posX - 1][posY + 1] = 'v';
			else if(cor == 'B' && Character.isUpperCase(tab[posX - 1][posY + 1]))
				mat[posX - 1][posY + 1] = 'a';
			else if(cor == 'P' && !Character.isUpperCase(tab[posX - 1][posY + 1])) 
				mat[posX - 1][posY + 1] = 'a';
		}
		if(posX < 8 && posY > 0) {
			if(tab[posX + 1][posY - 1] == 'x') 
				mat[posX + 1][posY - 1] = 'v';
			else if(cor == 'B' && Character.isUpperCase(tab[posX + 1][posY - 1]))
				mat[posX + 1][posY - 1] = 'a';
			else if(cor == 'P' && !Character.isUpperCase(tab[posX + 1][posY - 1])) 
				mat[posX + 1][posY - 1] = 'a';
		}
		if(posX < 8 && posY <  8) {
			if(tab[posX + 1][posY + 1] == 'x') 
				mat[posX + 1][posY + 1] = 'v';
			else if(cor == 'B' && Character.isUpperCase(tab[posX + 1][posY + 1]))
				mat[posX + 1][posY + 1] = 'a';
			else if(cor == 'P' && !Character.isUpperCase(tab[posX + 1][posY + 1])) 
				mat[posX + 1][posY + 1] = 'a';
		}
		
		return mat;
	}
	
	

}
