package Peca;
import java.awt.Image;

import Tabuleiro.Tabuleiro;

public abstract class Peca {
	int _posX, _posY;
	char _cor;
	Image _imagem;
	
	public Peca(char cor, Image imagem, int posicaoX, int posicaoY) {
		_posX = posicaoX;
		_posY = posicaoY;
		_cor = cor;
		_imagem = imagem;	
	}
	
	public abstract char[][] movsPossiveis(int posX, int posY, char cor);
	
	protected char[][] iniciaPosMov() {
		char mat[][] = new char[8][8];
		
		for(int i=0; i< mat.length; i++) {
			for(int j=0; j< mat[i].length; j++) {
				mat[i][j] = 'x';
			}
		}
		return mat;
	}
	
	}
