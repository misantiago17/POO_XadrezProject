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
				
		this.imagem = buscaNomeImg(nomeImg);
	}
	
	@Override
	public Coordenadas[] getMovPossiveis(int Xi, int Yj) {
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
				
				System.out.println("i " + i + " j " + j);
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
