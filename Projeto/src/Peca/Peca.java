package Peca;
import Tabuleiro.Tabuleiro;

//Interface capitulo 7

public abstract class Peca {
	protected int posX, posY;
	
	protected abstract Tabuleiro movsPossiveis();
	
	private char[][] iniciaPosMov() {
		char mat[][] = new char[9][9];
		
		for(int i=0; i< mat.length; i++) {
			for(int j=0; j< mat[i].length; j++) {
				mat[i][j] = 'x';
			}
		}
		return mat;
	}
	
	protected char[][] vertical() {
		Tabuleiro tabuleiro = new Tabuleiro();
		char tab[][];
		tab = tabuleiro.getTab();
		
		char mat[][];
		mat = iniciaPosMov();
		
		if(tab[posX][posY - 1] == 'x') {
			mat[posX][posY - 1] = 'v';
		}
		if(tab[posX][posY + 1] == 'x') {
			mat[posX][posY + 1] = 'v';
		}
		
		return mat;
	}
	
	protected char[][] horizontal() {
		Tabuleiro tabuleiro = new Tabuleiro();
		char tab[][];
		tab = tabuleiro.getTab();
		
		char mat[][];
		mat = iniciaPosMov();
		
		if(tab[posX - 1][posY] == 'x') {
			mat[posX + 1][posY] = 'v';
		}
		if(tab[posX + 1][posY] == 'x') {
			mat[posX + 1][posY] = 'v';
		}
		
		return mat;
	}
	
	protected char[][] diagonal() {
		Tabuleiro tabuleiro = new Tabuleiro();
		char tab[][];
		tab = tabuleiro.getTab();
		
		char mat[][];
		mat = iniciaPosMov();
		
		if(tab[posX - 1][posY - 1] == 'x') {
			mat[posX - 1][posY - 1] = 'v';
		}
		if(tab[posX - 1][posY + 1] == 'x') {
			mat[posX - 1][posY + 1] = 'v';
		}
		if(tab[posX + 1][posY - 1] == 'x') {
			mat[posX + 1][posY - 1] = 'v';
		}
		if(tab[posX + 1][posY + 1] == 'x') {
			mat[posX + 1][posY + 1] = 'v';
		}
		
		return mat;
	}
}
