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
				
			// vertical
			if(!encontrouVertCima && Yj + i < 8) {
				if (table[Xi][Yj + i].peca == null) 
						table[Xi][Yj + i].movPossivel = true;
				else if(table[Xi][Yj].peca.cor != table[Xi][Yj + i].peca.cor) {
					table[Xi][Yj + i].atcPossivel = true;
					encontrouVertCima = true;
				}
				else
					encontrouVertCima = true;
				
				casasPossiveis[index] = new Coordenadas(Xi,Yj + i);
				index += 1;
			}
			if(!encontrouVertBaixo && Yj - i >= 0) {
				if (table[Xi][Yj - i].peca == null) 
					table[Xi][Yj - i].movPossivel = true;
				else if(table[Xi][Yj].peca.cor != table[Xi][Yj - i].peca.cor) {
					table[Xi][Yj - i].atcPossivel = true;
					encontrouVertBaixo = true;
				}
				else 
					encontrouVertBaixo = true;
				
				casasPossiveis[index] = new Coordenadas(Xi,Yj - i);
				index += 1;
			}
			
			
			
			
			// horizontal
			if(!encontrouHorzDireita && Xi + i < 8) {
				if (table[Xi + i][Yj].peca == null) 
					table[Xi + i][Yj].movPossivel = true;
				else if(table[Xi][Yj].peca.cor != table[Xi + i][Yj].peca.cor) {
					table[Xi + i][Yj].atcPossivel = true;
					encontrouHorzDireita = true;
				}
				else 
					encontrouHorzDireita = true;
				
				casasPossiveis[index] = new Coordenadas(Xi + i,Yj);
				index += 1;
			}
			if(!encontrouHorzEsquerda && Yj - i >= 0) {
				if (table[Xi - i][Yj].peca == null) 
					table[Xi - i][Yj].movPossivel = true;
				else if(table[Xi][Yj].peca.cor != table[Xi - i][Yj].peca.cor) {
					table[Xi - i][Yj].atcPossivel = true;
					encontrouHorzEsquerda = true;
				}
				else 
					encontrouHorzEsquerda = true;
				
				casasPossiveis[index] = new Coordenadas(Xi - i,Yj);
				index += 1;			
			}
			
			// diagonal
			if(!encontrouDiagCimaDir && Xi + i < 8 && Yj + i < 8) {
				if (table[Xi + i][Yj + i].peca == null) 
					table[Xi + i][Yj + i].movPossivel = true;
				else if(table[Xi][Yj].peca.cor != table[Xi + i][Yj + i].peca.cor) {
					table[Xi + i][Yj + i].atcPossivel = true;
					encontrouDiagCimaDir = true;
				}
				else 
					encontrouDiagCimaDir = true;
				
				casasPossiveis[index] = new Coordenadas(Xi + i,Yj + i);
				index += 1;	
			}
			if(!encontrouDiagBaixoDir && Xi + i < 8 && Yj - i >= 0) {
				if (table[Xi + i][Yj - i].peca == null) 
					table[Xi + i][Yj - i].movPossivel = true;
				else if(table[Xi][Yj].peca.cor != table[Xi + i][Yj - i].peca.cor) {
					table[Xi + i][Yj - i].atcPossivel = true;
					encontrouDiagBaixoDir = true;
				}
				else 
					encontrouDiagBaixoDir = true;
				
				casasPossiveis[index] = new Coordenadas(Xi + i,Yj - i);
				index += 1;	
			
			}
			if(!encontrouDiagCimaEsq && Xi - i >= 0 && Yj + i < 8) {
				if (table[Xi - i][Yj + i].peca == null) 
					table[Xi - i][Yj + i].movPossivel = true;
				else if(table[Xi][Yj].peca.cor != table[Xi - i][Yj + i].peca.cor) {
					table[Xi - i][Yj + i].atcPossivel = true;
					encontrouDiagCimaEsq = true;
				}
				else 
					encontrouDiagCimaEsq = true;
				
				casasPossiveis[index] = new Coordenadas(Xi + i,Yj - i);
				index += 1;	
			}
			if(!encontrouDiagBaixoEsq && Xi - i >= 0 && Yj - i >= 0) {
				if (table[Xi- i][Yj - i].peca == null) 
					table[Xi- i][Yj - i].movPossivel = true;
				else if(table[Xi][Yj].peca.cor != table[Xi- i][Yj - i].peca.cor) {
					table[Xi- i][Yj - i].atcPossivel = true;
					encontrouDiagBaixoEsq = true;
				}
				else 
					encontrouDiagBaixoEsq = true;
				
				casasPossiveis[index] = new Coordenadas(Xi - i, Yj - i);
				index += 1;
			}
		}
	
	
	return casasPossiveis;
	}

}
