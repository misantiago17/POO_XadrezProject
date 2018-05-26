package Peca;

import java.awt.Image;
import Interface.XadrezFrame;
import Tabuleiro.Casa;
import Tabuleiro.Coordenadas;



public abstract class Peca {
	public int posX, posY;
	public char cor;
	public Image imagem;
	public boolean selecionada = false;
	
	public Peca(char cor, int posicaoX, int posicaoY) {
		this.posX = posicaoX;
		this.posY = posicaoY;
		this.cor = cor;
	}
	
	public abstract Coordenadas[] getMovPossiveis(int Xi, int Yj);
	public abstract char[][] movsPossiveis();
	
	/*protected Casa[][] iniciaPosMov() {
		Casa mat[][] = new char[8][8];
		
		for(int i=0; i< mat.length; i++) {
			for(int j=0; j< mat[i].length; j++) {
				mat[i][j] = 'x';
			}
		}
		return mat;
	}*/
	
	protected char[][] iniciaPosMov() {
		char mat[][] = new char[8][8];
		
		for(int i=0; i< mat.length; i++) {
			for(int j=0; j< mat[i].length; j++) {
				mat[i][j] = 'x';
			}
		}
		return mat;
	}
	
	protected Image buscaNomeImg(String nome) {		
		for(int i = 0 ; i < XadrezFrame.nomeImagens.length; i++) {
			if(XadrezFrame.nomeImagens[i].equals(nome)) {
				return XadrezFrame.imagens[i];
			}
		}
		return null;
	}
}
