package Peca;

import java.awt.Image;

import Tabuleiro.*;

public class Rainha extends Peca {
	
	public Rainha(char cor, int posicaoX, int posicaoY, String nome, Image img, Coordenadas coord) {
		super(cor,posicaoX,posicaoY,nome, img, coord);
		
		String nomeImg;
		this.imagem = img;
		this.nome = nome;
	}

	@Override
	public Coordenadas[] getMovPossiveis(int Xi, int Yj) {
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
		
		for (int i=1 ;i <= 8; i++) {
				
			// vertical
			
			if(!encontrouVertBaixo && Yj - i >= 0) {
				if (table[Xi][Yj - i].peca == null) {
					if(!preveCheck(Xi, Yj, Xi, Yj - i)) {
						table[Xi][Yj - i].movPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi,Yj - i);
						index += 1;
					}
				}
				else if(table[Xi][Yj].peca.cor != table[Xi][Yj - i].peca.cor) {
					if(!preveCheck(Xi, Yj, Xi, Yj - i)) {
						table[Xi][Yj - i].atcPossivel = true;
						encontrouVertBaixo = true;
						casasPossiveis[index] = new Coordenadas(Xi,Yj - i);
						index += 1;
					}
				}
				else 
					encontrouVertBaixo = true;
				
			}
			
			if(!encontrouVertCima && Yj + i < 7) {
				if (table[Xi][Yj + i].peca == null) {
					if(!preveCheck(Xi, Yj, Xi, Yj + i)) {
						table[Xi][Yj + i].movPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi,Yj + i);
						index += 1;
					}
				}
				else if(table[Xi][Yj].peca.cor != table[Xi][Yj + i].peca.cor) {
					if(!preveCheck(Xi, Yj, Xi, Yj + i)) {
						table[Xi][Yj + i].atcPossivel = true;
						encontrouVertCima = true;
						casasPossiveis[index] = new Coordenadas(Xi,Yj + i);
						index += 1;
					}
				}
				else 
					encontrouVertCima = true;
				
			}
					
			
			// horizontal
			if(!encontrouHorzDireita && Xi + i < 8) {
				if (table[Xi + i][Yj].peca == null) {
					if(!preveCheck(Xi, Yj, Xi + i, Yj)) {
						table[Xi + i][Yj].movPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi + i,Yj);
						index += 1;
					}
				}
				else if(table[Xi][Yj].peca.cor != table[Xi + i][Yj].peca.cor) {
					if(!preveCheck(Xi, Yj, Xi + i, Yj)) {
						table[Xi + i][Yj].atcPossivel = true;
						encontrouHorzDireita = true;
						casasPossiveis[index] = new Coordenadas(Xi + i,Yj);
						index += 1;
					}
				}
				else 
					encontrouHorzDireita = true;
				
			}
			if(!encontrouHorzEsquerda && Xi - i >= 0) {
				if (table[Xi - i][Yj].peca == null) {
					if(!preveCheck(Xi, Yj, Xi - i, Yj)) {
						table[Xi - i][Yj].movPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi - i,Yj);
						index += 1;		
					}
				}
				else if(table[Xi][Yj].peca.cor != table[Xi - i][Yj].peca.cor) {
					if(!preveCheck(Xi, Yj, Xi - i, Yj)) {
						table[Xi - i][Yj].atcPossivel = true;
						encontrouHorzEsquerda = true;
						casasPossiveis[index] = new Coordenadas(Xi - i,Yj);
						index += 1;		
					}
				}
				else 
					encontrouHorzEsquerda = true;
				
					
			}
			
			// diagonal
			if(!encontrouDiagCimaDir && Xi + i < 8 && Yj + i < 8) {
				if (table[Xi + i][Yj + i].peca == null) {
					if(!preveCheck(Xi, Yj, Xi + i, Yj + i)) {
						table[Xi + i][Yj + i].movPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi + i,Yj + i);
						index += 1;	
					}
				}
				else if(table[Xi][Yj].peca.cor != table[Xi + i][Yj + i].peca.cor) {
					if(!preveCheck(Xi, Yj, Xi + i, Yj + i)) {
						table[Xi + i][Yj + i].atcPossivel = true;
						encontrouDiagCimaDir = true;
						casasPossiveis[index] = new Coordenadas(Xi + i,Yj + i);
						index += 1;	
					}
				}
				else 
					encontrouDiagCimaDir = true;
				
			}
			if(!encontrouDiagBaixoDir && Xi + i < 8 && Yj - i >= 0) {
				if (table[Xi + i][Yj - i].peca == null) {
					if(!preveCheck(Xi, Yj, Xi + i, Yj - i)) {
						table[Xi + i][Yj - i].movPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi + i,Yj - i);
						index += 1;	
					}
				}
				else if(table[Xi][Yj].peca.cor != table[Xi + i][Yj - i].peca.cor) {
					if(!preveCheck(Xi, Yj, Xi + i, Yj - i)) {
						table[Xi + i][Yj - i].atcPossivel = true;
						encontrouDiagBaixoDir = true;
						casasPossiveis[index] = new Coordenadas(Xi + i,Yj - i);
						index += 1;	
					}
				}
				else 
					encontrouDiagBaixoDir = true;
				
			}
			if(!encontrouDiagCimaEsq && Xi - i >= 0 && Yj + i < 8) {
				if (table[Xi - i][Yj + i].peca == null) {
					if(!preveCheck(Xi, Yj, Xi - i, Yj + i)) {
						table[Xi - i][Yj + i].movPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi - i,Yj + i);
						index += 1;	
					}
				}
				else if(table[Xi][Yj].peca.cor != table[Xi - i][Yj + i].peca.cor) {
					if(!preveCheck(Xi, Yj, Xi - i, Yj + i)) {
						table[Xi - i][Yj + i].atcPossivel = true;
						encontrouDiagCimaEsq = true;
						casasPossiveis[index] = new Coordenadas(Xi - i,Yj + i);
						index += 1;	
					}
				}
				else 
					encontrouDiagCimaEsq = true;
				
			}
			if(!encontrouDiagBaixoEsq && Xi - i >= 0 && Yj - i >= 0) {
				if (table[Xi- i][Yj - i].peca == null) {
					if(!preveCheck(Xi, Yj, Xi - i, Yj - i)) {
						table[Xi- i][Yj - i].movPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi - i, Yj - i);
						index += 1;
					}
				}
				else if(table[Xi][Yj].peca.cor != table[Xi- i][Yj - i].peca.cor) {
					if(!preveCheck(Xi, Yj, Xi - i, Yj - i)) {
						table[Xi- i][Yj - i].atcPossivel = true;
						encontrouDiagBaixoEsq = true;
						casasPossiveis[index] = new Coordenadas(Xi - i, Yj - i);
						index += 1;
					}
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
		boolean encontrouVertCima = false;
		boolean encontrouVertBaixo = false;
		boolean encontrouHorzDireita = false;
		boolean encontrouHorzEsquerda = false;
		boolean encontrouDiagCimaDir = false;
		boolean encontrouDiagBaixoDir = false;
		boolean encontrouDiagCimaEsq = false;
		boolean encontrouDiagBaixoEsq = false;
		
		for (int i=1 ;i <= 8; i++) {
				
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
