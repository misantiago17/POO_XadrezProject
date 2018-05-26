package Peca;

import java.awt.Image;

import Interface.XadrezFrame;
import Tabuleiro.*;

public class Rainha extends Peca {
	
	public Rainha(char cor, int posicaoX, int posicaoY, String nome, Image img) {
		super(cor,posicaoX,posicaoY,nome, img);
		
		String nomeImg;
		/*if(cor == 'B') 
			nomeImg = "CyanQ.png";
		else
			nomeImg = "PurpleQ.png";*/
				
		this.imagem = img;
		this.nome = nome;
	}

	@Override
	public Coordenadas[] getMovPossiveis(int Xi, int Yj) { // bloquear
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
