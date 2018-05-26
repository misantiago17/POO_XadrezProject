package Peca;

import java.awt.Image;
import Interface.XadrezFrame;
import Tabuleiro.*;

public class Rei extends Peca {
	
	public Rei(char cor, int posicaoX, int posicaoY, String nome, Image img) {
		super(cor,posicaoX,posicaoY,nome, img);
		
		String nomeImg;
		/*if(cor == 'B') 
			nomeImg = "CyanK.png";
		else
			nomeImg = "PurpleK.png";
				
		this.imagem = buscaNomeImg(nomeImg);*/
		this.imagem = img;
		this.nome = nome;
	}
	
	@Override
	public Coordenadas[] getMovPossiveis(int Xi, int Yj) { // só anda uma casa 
		Tabuleiro tabuleiro = Tabuleiro.currentTable;
		Casa[][] table = tabuleiro.getTabCasa();
		
		Coordenadas[] casasPossiveis = new Coordenadas[64];
		int index = 0;
		
		// bloquear de ir alem de uma peça?
		boolean encontrouVert = false;
		boolean encontrouHorz = false;
		boolean encontrouDiag = false;
		
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				
				// vertical
				if (i == Xi && j != Yj) {
					if (table[i][j].peca != null) {
						table[i][j].atcPossivel = true;
					}else {
						table[i][j].movPossivel = true;
					}
					casasPossiveis[index] = new Coordenadas(i,j);
					index += 1;
				}
				
				// horizontal -- falta limitar o movimento quando encontra uma peça
				if (i != Xi && j == Yj) {
					if (table[i][j].peca != null) {
						table[i][j].atcPossivel = true;
					}else {
						table[i][j].movPossivel = true;
					}
					casasPossiveis[index] = new Coordenadas(i,j);
					index += 1;
				}
				
				// diagonal
				if (i != Xi && j != Yj && ((i - Xi == j - Yj) || (Xi - i == j - Yj))) {
					if (table[i][j].peca != null) {
						table[i][j].atcPossivel = true;
					}else {
						table[i][j].movPossivel = true;
					}
					casasPossiveis[index] = new Coordenadas(i,j);
					index += 1;
				}
			}
		}
		return casasPossiveis;
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
