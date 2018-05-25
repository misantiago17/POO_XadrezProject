package Peca;

import java.awt.Image;

import Interface.XadrezFrame;
import Tabuleiro.*;

public class Cavalo extends Peca {
	
	public Cavalo(char cor, int posicaoX, int posicaoY) {
		super(cor,posicaoX,posicaoY);
		
		String nomeImg;
		if(cor == 'B') 
			nomeImg = "CyanN.png";
		else
			nomeImg = "PurpleN.png";
		
		int posImg = buscaNomeImg(nomeImg);
		
		this.imagem = XadrezFrame.imagens[posImg];
	}

	@Override
	public char[][] movsPossiveis() {
		Tabuleiro tabuleiro = new Tabuleiro();
		char tab[][];
		tab = tabuleiro.getTabChar();
		
		char mat[][];
		mat = iniciaPosMov();
		
		if(posX - 2 > 0 && posY - 1 > 0) {
			if(tab[posX - 2][posY - 1] == 'x') 
				mat[posX - 2][posY - 1] = 'v';
			else if(cor == 'B' && Character.isUpperCase(tab[posX - 2][posY - 1]))
				mat[posX - 2][posY - 1] = 'a';
			else if(cor == 'P' && !Character.isUpperCase(tab[posX - 2][posY - 1])) 
				mat[posX - 2][posY - 1] = 'a';
		}
		if(posX - 2 > 0 && posY + 1 < 8) {
			if(tab[posX - 2][posY + 1] == 'x') 
				mat[posX - 2][posY + 1] = 'v';
			else if(cor == 'B' && Character.isUpperCase(tab[posX - 2][posY + 1]))
				mat[posX - 2][posY + 1] = 'a';
			else if(cor == 'P' && !Character.isUpperCase(tab[posX - 2][posY + 1])) 
				mat[posX - 2][posY + 1] = 'a';
		}
		if(posX + 2 < 8 && posY - 1 > 0) {
			if(tab[posX + 2][posY - 1] == 'x') 
				mat[posX + 2][posY - 1] = 'v';
			else if(cor == 'B' && Character.isUpperCase(tab[posX + 2][posY - 1]))
				mat[posX + 2][posY - 1] = 'a';
			else if(cor == 'P' && !Character.isUpperCase(tab[posX + 2][posY - 1])) 
				mat[posX + 2][posY - 1] = 'a';
		}
		if(posX + 2 < 8 && posY + 1 <  8) {
			if(tab[posX + 2][posY + 1] == 'x') 
				mat[posX + 2][posY + 1] = 'v';
			else if(cor == 'B' && Character.isUpperCase(tab[posX + 2][posY + 1]))
				mat[posX + 2][posY + 1] = 'a';
			else if(cor == 'P' && !Character.isUpperCase(tab[posX + 2][posY + 1])) 
				mat[posX + 2][posY + 1] = 'a';
		}
		
		return mat;
	}


}
