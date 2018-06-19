package Peca;

import java.awt.Image;
import Tabuleiro.*;

public class Bispo extends Peca {

	public Bispo(char cor, int posicaoX, int posicaoY, String nome, Image img, Coordenadas coord) {
		super(cor, posicaoX, posicaoY, nome, img, coord);

		String nomeImg;
		this.imagem = img;
		this.nome = nome;
	}

	@Override
	public Coordenadas[] getMovPossiveis(int Xi, int Yj) {
		Coordenadas[] casasPossiveis = new Coordenadas[64];
		int index = 0;

		// bloquear de ir alem de uma peça
		boolean encontrouDiagCimaDir = false;
		boolean encontrouDiagBaixoDir = false;
		boolean encontrouDiagCimaEsq = false;
		boolean encontrouDiagBaixoEsq = false;
		
		for (int i=1; i <= 8;i++) {
			// diagonal
			if(!encontrouDiagCimaDir && Xi + i < 8 && Yj + i < 8) {
				if (table[Xi + i][Yj + i].peca == null) {
					if(!preveCheck(Xi, Yj, Xi + i, Yj + i, cor)) {
						table[Xi + i][Yj + i].movPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi + i,Yj + i);
						index += 1;	
					}
				}
				else if(table[Xi][Yj].peca.cor != table[Xi + i][Yj + i].peca.cor) {
					if(!preveCheck(Xi, Yj, Xi + i, Yj + i, cor)) {
						table[Xi + i][Yj + i].atcPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi + i,Yj + i);
						index += 1;	
					}
					encontrouDiagCimaDir = true;
				}
				else 
					encontrouDiagCimaDir = true;

			}
			
			if(!encontrouDiagBaixoDir && Xi + i < 8 && Yj - i >= 0) {
				if (table[Xi + i][Yj - i].peca == null) {
					if(!preveCheck(Xi, Yj, Xi + i, Yj - i, cor)) {
						table[Xi + i][Yj - i].movPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi + i,Yj - i);
						index += 1;	
					}
				}
				else if(table[Xi][Yj].peca.cor != table[Xi + i][Yj - i].peca.cor) {
					if(!preveCheck(Xi, Yj, Xi + i, Yj - i, cor)) {
						table[Xi + i][Yj - i].atcPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi + i,Yj - i);
						index += 1;	
					}
					encontrouDiagBaixoDir = true;
				}
				else 
					encontrouDiagBaixoDir = true;

			}
			if(!encontrouDiagCimaEsq && Xi - i >= 0 && Yj + i < 8) {
				if (table[Xi - i][Yj + i].peca == null) {
					if(!preveCheck(Xi, Yj, Xi - i, Yj + i, cor)) {
						table[Xi - i][Yj + i].movPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi - i,Yj + i);
						index += 1;	
					}
				}
				else if(table[Xi][Yj].peca.cor != table[Xi - i][Yj + i].peca.cor) {
					if(!preveCheck(Xi, Yj, Xi - i, Yj + i, cor)) {
						table[Xi - i][Yj + i].atcPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi - i,Yj + i);
						index += 1;	
					}
					encontrouDiagCimaEsq = true;
				}
				else 
					encontrouDiagCimaEsq = true;

			}
			if(!encontrouDiagBaixoEsq && Xi - i >= 0 && Yj - i >= 0) {
				if (table[Xi- i][Yj - i].peca == null) {
					if(!preveCheck(Xi, Yj, Xi - i, Yj - i, cor)) {
						table[Xi- i][Yj - i].movPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi - i, Yj - i);
						index += 1;
					}
				}
				else if(table[Xi][Yj].peca.cor != table[Xi- i][Yj - i].peca.cor) {
					if(!preveCheck(Xi, Yj, Xi - i, Yj - i, cor)) {
						table[Xi- i][Yj - i].atcPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi - i, Yj - i);
						index += 1;
					}
					encontrouDiagBaixoEsq = true;
				}
				else 
					encontrouDiagBaixoEsq = true;
			}
			
		}
		return casasPossiveis;
	}
	
	@Override
	public Coordenadas[] testaMov(int Xi, int Yj, Casa[][] table) {
		Coordenadas[] casasPossiveis = new Coordenadas[64];
		int index = 0;

		// bloquear de ir alem de uma peça
		boolean encontrouDiagCimaDir = false;
		boolean encontrouDiagBaixoDir = false;
		boolean encontrouDiagCimaEsq = false;
		boolean encontrouDiagBaixoEsq = false;

		for (int i=1; i <= 8;i++) {
			// diagonal
			if(!encontrouDiagCimaDir && Xi + i < 8 && Yj + i < 8) {
				if (table[Xi + i][Yj + i].peca == null) {
					casasPossiveis[index] = new Coordenadas(Xi + i,Yj + i);
					index += 1;	
				}
				else if(table[Xi][Yj].peca.cor != table[Xi + i][Yj + i].peca.cor) {
					encontrouDiagCimaDir = true;
					casasPossiveis[index] = new Coordenadas(Xi + i,Yj + i);
					index += 1;	
				}
				else 
					encontrouDiagCimaDir = true;

			}
			if(!encontrouDiagBaixoDir && Xi + i < 8 && Yj - i >= 0) {
				if (table[Xi + i][Yj - i].peca == null) {
					casasPossiveis[index] = new Coordenadas(Xi + i,Yj - i);
					index += 1;	
				}
				else if(table[Xi][Yj].peca.cor != table[Xi + i][Yj - i].peca.cor) {
					encontrouDiagBaixoDir = true;
					casasPossiveis[index] = new Coordenadas(Xi + i,Yj - i);
					index += 1;	
				}
				else 
					encontrouDiagBaixoDir = true;

			}
			if(!encontrouDiagCimaEsq && Xi - i >= 0 && Yj + i < 8) {
				if (table[Xi - i][Yj + i].peca == null) {
					casasPossiveis[index] = new Coordenadas(Xi - i,Yj + i);
					index += 1;	
				}
				else if(table[Xi][Yj].peca.cor != table[Xi - i][Yj + i].peca.cor) {
					encontrouDiagCimaEsq = true;
					casasPossiveis[index] = new Coordenadas(Xi - i,Yj + i);
					index += 1;	
				}
				else 
					encontrouDiagCimaEsq = true;

			}
			if(!encontrouDiagBaixoEsq && Xi - i >= 0 && Yj - i >= 0) {
				if (table[Xi- i][Yj - i].peca == null) {
					casasPossiveis[index] = new Coordenadas(Xi - i, Yj - i);
					index += 1;
				}
				else if(table[Xi][Yj].peca.cor != table[Xi- i][Yj - i].peca.cor) {
					encontrouDiagBaixoEsq = true;
					casasPossiveis[index] = new Coordenadas(Xi - i, Yj - i);
					index += 1;
				}
				else 
					encontrouDiagBaixoEsq = true;
			}
			
		}
		return casasPossiveis;
	}
	
}
