package Peca;

import java.awt.Image;

import Interface.XadrezFrame;
import Tabuleiro.*;

public class Rainha extends Peca {
	
	public Rainha(char cor, int posicaoX, int posicaoY, String nome, Image img) {
		super(cor,posicaoX,posicaoY,nome, img);
		
		String nomeImg;
		this.imagem = img;
		this.nome = nome;
	}

	@Override
	public Coordenadas[] getMovPossiveis(int Xi, int Yj) { // bloquear
		
		Casa[][] table = Tabuleiro.getTabCasa();
		
		Coordenadas[] casasPossiveis = new Coordenadas[64];
		int index = 0;
		
		// bloquear de ir alem de uma peça
		boolean encontrouVertCima = false;
		boolean encontrouVertBaixo = false;
		boolean encontrouHorzDireita = false;
		boolean encontrouHorzEsquerda = false;
		boolean encontrouDiagCimaDir = false;
		boolean encontrouDiagBaixoDir = false;
		boolean encontrouDiagCimaEsq = false;
		boolean encontrouDiagBaixoEsq = false;
		
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				
				// vertical
				if (i == Xi && j != Yj) {
					if(!encontrouVertCima && i > Xi) {
						if (table[i][j].peca == null) 
							table[i][j].movPossivel = true;
						else if(table[Xi][Yj].peca.cor != table[i][j].peca.cor) {
							table[i][j].atcPossivel = true;
							encontrouVertCima = true;
						}
						else {
							encontrouVertCima = true;
						}
					}
					if(!encontrouVertBaixo && i < Xi) {
						if (table[i][j].peca == null) 
							table[i][j].movPossivel = true;
						else if(table[Xi][Yj].peca.cor != table[i][j].peca.cor) {
							table[i][j].atcPossivel = true;
							encontrouVertBaixo = true;
						}
						else 
							encontrouVertBaixo = true;
					
					}
					casasPossiveis[index] = new Coordenadas(i,j);
					index += 1;
				}
				
				// horizontal
				if (i != Xi && j == Yj) {
					if(!encontrouHorzDireita && j > Yj) {
						if (table[i][j].peca == null) 
							table[i][j].movPossivel = true;
						else if(table[Xi][Yj].peca.cor != table[i][j].peca.cor) {
							table[i][j].atcPossivel = true;
							encontrouHorzDireita = true;
						}
						else 
							encontrouHorzDireita = true;
					}
					if(!encontrouHorzEsquerda && j < Yj) {
						if (table[i][j].peca == null) 
							table[i][j].movPossivel = true;
						else if(table[Xi][Yj].peca.cor != table[i][j].peca.cor) {
							table[i][j].atcPossivel = true;
							encontrouHorzEsquerda = true;
						}
						else 
							encontrouHorzEsquerda = true;
					
					}
					casasPossiveis[index] = new Coordenadas(i,j);
					index += 1;
				}
				
				// diagonal
				if (i != Xi && j != Yj && ((i - Xi == j - Yj) || (Xi - i == j - Yj))) {
					if(!encontrouDiagCimaDir && i > Xi && j > Yj) {
						if (table[i][j].peca == null) 
							table[i][j].movPossivel = true;
						else if(table[Xi][Yj].peca.cor != table[i][j].peca.cor) {
							table[i][j].atcPossivel = true;
							encontrouDiagCimaDir = true;
						}
						else 
							encontrouDiagCimaDir = true;
					}
					if(!encontrouDiagBaixoDir && i > Xi && j < Yj) {
						if (table[i][j].peca == null) 
							table[i][j].movPossivel = true;
						else if(table[Xi][Yj].peca.cor != table[i][j].peca.cor) {
							table[i][j].atcPossivel = true;
							encontrouDiagBaixoDir = true;
						}
						else 
							encontrouDiagBaixoDir = true;
					
					}
					if(!encontrouDiagCimaEsq && i < Xi && j > Yj) {
						if (table[i][j].peca == null) 
							table[i][j].movPossivel = true;
						else if(table[Xi][Yj].peca.cor != table[i][j].peca.cor) {
							table[i][j].atcPossivel = true;
							encontrouDiagCimaEsq = true;
						}
						else 
							encontrouDiagCimaEsq = true;
					}
					if(!encontrouDiagBaixoEsq && i < Xi && j < Yj) {
						if (table[i][j].peca == null) 
							table[i][j].movPossivel = true;
						else if(table[Xi][Yj].peca.cor != table[i][j].peca.cor) {
							table[i][j].atcPossivel = true;
							encontrouDiagBaixoEsq = true;
						}
						else 
							encontrouDiagBaixoEsq = true;
					
					}
					casasPossiveis[index] = new Coordenadas(i,j);
					index += 1;
				}
			}
		}
		return casasPossiveis;
	}

}
