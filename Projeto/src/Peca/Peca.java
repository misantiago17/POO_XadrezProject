package Peca;

import java.awt.Image;
import Interface.XadrezFrame;



public abstract class Peca {
	public int posX, posY;
	public char cor;
	public Image imagem;
	
	public Peca(char cor, int posicaoX, int posicaoY) {
		this.posX = posicaoX;
		this.posY = posicaoY;
		this.cor = cor;
	}
	
	public abstract char[][] movsPossiveis();
	
	protected char[][] iniciaPosMov() {
		char mat[][] = new char[8][8];
		
		for(int i=0; i< mat.length; i++) {
			for(int j=0; j< mat[i].length; j++) {
				mat[i][j] = 'x';
			}
		}
		return mat;
	}
	
	protected int buscaNomeImg(String nome) {
		
		int i = -1;
		
		for(i = 0 ; i < XadrezFrame.nomeImagens.length; i++) {
			if(XadrezFrame.nomeImagens[i] == nome) 
				break;
		}
		
		return i;
	}
}
