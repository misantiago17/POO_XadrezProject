package Peca;

import java.awt.Image;
import Tabuleiro.*;


public class Torre extends Peca {

	public boolean hasMoved = false;

	public Torre(char cor, int posicaoX, int posicaoY, String nome, Image img, Coordenadas coord) {
		super(cor,posicaoX,posicaoY,nome, img, coord);

		this.imagem = img;
		this.nome = nome;
	}

	@Override
	public Coordenadas[] getMovPossiveis(int Xi, int Yj) { 
		Coordenadas[] casasPossiveis = new Coordenadas[64];
		int index = 0;

		// bloquear de ir alem de uma pe�a
		boolean encontrouVertCima = false;
		boolean encontrouVertBaixo = false;
		boolean encontrouHorzDireita = false;
		boolean encontrouHorzEsquerda = false;

		for (int i=1; i <= 8; i++) {
			// vertical
			if(!encontrouVertCima && Yj + i < 8) {
				if (table[Xi][Yj + i].peca == null) {
					if(!preveCheck(Xi, Yj, Xi, Yj + i, cor)) {
						table[Xi][Yj + i].movPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi,Yj + i);
						index += 1;
					}
				}
				else if(table[Xi][Yj].peca.cor != table[Xi][Yj + i].peca.cor) {
					if(!preveCheck(Xi, Yj, Xi, Yj + i, cor)) {
						table[Xi][Yj + i].atcPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi,Yj + i);
						index += 1;
					}
					encontrouVertCima = true;
				}
				else
					encontrouVertCima = true;				
			}
			if(!encontrouVertBaixo && Yj - i >= 0) {
				if (table[Xi][Yj - i].peca == null) {
					if(!preveCheck(Xi, Yj, Xi, Yj - i, cor)) {
						table[Xi][Yj - i].movPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi,Yj - i);
						index += 1;
					}
				}
				else if(table[Xi][Yj].peca.cor != table[Xi][Yj - i].peca.cor) {
					if(!preveCheck(Xi, Yj, Xi, Yj - i, cor)) {
						table[Xi][Yj - i].atcPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi,Yj - i);
						index += 1;
					}
					encontrouVertBaixo = true;
				}
				else 
					encontrouVertBaixo = true;

			}


			// horizontal
			if(!encontrouHorzDireita && Xi + i < 8) {
				if (table[Xi + i][Yj].peca == null) {
					if(!preveCheck(Xi, Yj, Xi + i, Yj, cor)) {
						table[Xi + i][Yj].movPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi + i,Yj);
						index += 1;
					}
				}
				else if(table[Xi][Yj].peca.cor != table[Xi + i][Yj].peca.cor) {
					if(!preveCheck(Xi, Yj, Xi + i, Yj, cor)) {
						table[Xi + i][Yj].atcPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi + i,Yj);
						index += 1;
					}
					encontrouHorzDireita = true;
				}
				else 
					encontrouHorzDireita = true;

			}
			if(!encontrouHorzEsquerda && Xi - i >= 0) {
				if (table[Xi - i][Yj].peca == null) {
					if(!preveCheck(Xi, Yj, Xi - i, Yj, cor)) {
						table[Xi - i][Yj].movPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi - i,Yj);
						index += 1;		
					}
				}
				else if(table[Xi][Yj].peca.cor != table[Xi - i][Yj].peca.cor) {
					if(!preveCheck(Xi, Yj, Xi - i, Yj, cor)) {
						table[Xi - i][Yj].atcPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi - i,Yj);
						index += 1;		
					}
					encontrouHorzEsquerda = true;
				}
				else 
					encontrouHorzEsquerda = true;


			}

			
		}
		return casasPossiveis;
	}
	
	@Override
	public Coordenadas[] testaMov(int Xi, int Yj, Casa[][] table) { 
		Coordenadas[] casasPossiveis = new Coordenadas[64];
		int index = 0;

		// bloquear de ir alem de uma pe�a
		boolean encontrouVertCima = false;
		boolean encontrouVertBaixo = false;
		boolean encontrouHorzDireita = false;
		boolean encontrouHorzEsquerda = false;

		for (int i=1; i <= 8; i++) {
			// vertical
			if(!encontrouVertCima && Yj + i < 8) {
				if (table[Xi][Yj + i].peca == null) {
					casasPossiveis[index] = new Coordenadas(Xi,Yj + i);
					index += 1;
				}
				else if(table[Xi][Yj].peca.cor != table[Xi][Yj + i].peca.cor) {
					encontrouVertCima = true;
					casasPossiveis[index] = new Coordenadas(Xi,Yj + i);
					index += 1;
				}
				else
					encontrouVertCima = true;				
			}
			if(!encontrouVertBaixo && Yj - i >= 0) {
				if (table[Xi][Yj - i].peca == null) {
					casasPossiveis[index] = new Coordenadas(Xi,Yj - i);
					index += 1;
				}
				else if(table[Xi][Yj].peca.cor != table[Xi][Yj - i].peca.cor) {
					encontrouVertBaixo = true;
					casasPossiveis[index] = new Coordenadas(Xi,Yj - i);
					index += 1;
				}
				else 
					encontrouVertBaixo = true;

			}


			// horizontal
			if(!encontrouHorzDireita && Xi + i < 8) {
				if (table[Xi + i][Yj].peca == null) {
					casasPossiveis[index] = new Coordenadas(Xi + i,Yj);
					index += 1;
				}
				else if(table[Xi][Yj].peca.cor != table[Xi + i][Yj].peca.cor) {
					encontrouHorzDireita = true;
					casasPossiveis[index] = new Coordenadas(Xi + i,Yj);
					index += 1;
				}
				else 
					encontrouHorzDireita = true;

			}
			if(!encontrouHorzEsquerda && Xi - i >= 0) {
				if (table[Xi - i][Yj].peca == null) {
					casasPossiveis[index] = new Coordenadas(Xi - i,Yj);
					index += 1;		
				}
				else if(table[Xi][Yj].peca.cor != table[Xi - i][Yj].peca.cor) {
					encontrouHorzEsquerda = true;
					casasPossiveis[index] = new Coordenadas(Xi - i,Yj);
					index += 1;		
				}
				else 
					encontrouHorzEsquerda = true;


			}

			
		}
		return casasPossiveis;
	}
	
}
