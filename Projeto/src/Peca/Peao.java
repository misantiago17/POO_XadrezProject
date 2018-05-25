package Peca;

import java.awt.Image;

import Interface.XadrezFrame;
import Tabuleiro.*;

public class Peao extends Peca {
	
	private boolean hasMoved;
	
	public Peao(char cor, int posicaoX, int posicaoY) {
		super(cor,posicaoX,posicaoY);
		
		String nomeImg;
		if(cor == 'B') 
			nomeImg = "CyanP";
		else
			nomeImg = "PurpleP";
		
		int posImg = buscaNomeImg(nomeImg);
		
		this.imagem = XadrezFrame.imagens[posImg];
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
