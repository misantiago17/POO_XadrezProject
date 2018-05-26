package Peca;

import java.awt.Image;
import Interface.XadrezFrame;
import Tabuleiro.*;

public class Rei extends Peca {
	
	public Rei(char cor, int posicaoX, int posicaoY) {
		super(cor,posicaoX,posicaoY);
		
		String nomeImg;
		if(cor == 'B') 
			nomeImg = "CyanK.png";
		else
			nomeImg = "PurpleK.png";
				
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
		if(posY - 1 > 0) {
			if(tab[posX][posY - 1] == 'x') 
				mat[posX][posY - 1] = 'v';
			else if(cor == 'B' && Character.isUpperCase(tab[posX][posY - 1]))
				mat[posX][posY - 1] = 'a';
			else if(cor == 'P' && !Character.isUpperCase(tab[posX][posY - 1])) 
				mat[posX][posY - 1] = 'a';
		}
		if(posY + 1 < 8) {
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
		
		//diagonal
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
