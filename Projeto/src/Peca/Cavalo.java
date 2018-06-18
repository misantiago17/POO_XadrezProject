package Peca;

import java.awt.Image;

import Tabuleiro.*;

public class Cavalo extends Peca {
	
	public Cavalo(char cor, int posicaoX, int posicaoY, String nome, Image img, Coordenadas coord) {
		super(cor,posicaoX,posicaoY,nome, img, coord);
		
		String nomeImg;
		this.imagem = img;
		this.nome = nome;
	}
	
	@Override
	public Coordenadas[] getMovPossiveis(int Xi, int Yj) {
		Coordenadas[] casasPossiveis = new Coordenadas[64];
		int index = 0;
		
		if(Xi - 2 >= 0 && Yj - 1 >= 0) {
			if(table[Xi - 2][Yj - 1].peca == null) {
				if(!preveCheck(Xi, Yj, Xi - 2, Yj - 1, cor)) {
					table[Xi - 2][Yj - 1].movPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi - 2, Yj - 1);
					index += 1;
				}
			}
			else if(table[Xi][Yj].peca.cor != table[Xi - 2][Yj -1].peca.cor) {
				if(!preveCheck(Xi, Yj, Xi - 2, Yj - 1, cor)) {
					table[Xi - 2][Yj - 1].atcPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi - 2, Yj - 1);
					index += 1;
				}
			}
			
		}
		if(Xi - 2 >= 0 && Yj + 1 < 8) {
			if(table[Xi - 2][Yj + 1].peca == null) {
				if(!preveCheck(Xi, Yj, Xi - 2, Yj + 1, cor)) {
					table[Xi - 2][Yj + 1].movPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi - 2, Yj + 1);
					index += 1;
				}
			}
			else if(table[Xi][Yj].peca.cor != table[Xi - 2][Yj + 1].peca.cor) {
				if(!preveCheck(Xi, Yj, Xi - 2, Yj + 1, cor)) {
					table[Xi - 2][Yj + 1].atcPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi - 2, Yj + 1);
					index += 1;
				}
			}
			
		}
		if(Xi + 2 < 8 && Yj - 1 >= 0) {
			if(table[Xi + 2][Yj - 1].peca == null) {
				if(!preveCheck(Xi, Yj, Xi + 2, Yj - 1, cor)) {
					table[Xi + 2][Yj - 1].movPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi + 2, Yj - 1);
					index += 1;
				}
			}
			else if(table[Xi][Yj].peca.cor != table[Xi + 2][Yj - 1].peca.cor) {
				if(!preveCheck(Xi, Yj, Xi + 2, Yj - 1, cor)) {
					table[Xi + 2][Yj - 1].atcPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi + 2, Yj - 1);
					index += 1;
				}
			}
			
		}
		if(Xi + 2 < 8 && Yj + 1 < 8) {
			if(table[Xi + 2][Yj + 1].peca == null) {
				if(!preveCheck(Xi, Yj, Xi + 2, Yj + 1, cor)) {
					table[Xi + 2][Yj + 1].movPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi + 2, Yj + 1);
					index += 1;
				}
			}
			else if(table[Xi][Yj].peca.cor != table[Xi + 2][Yj + 1].peca.cor) {
				if(!preveCheck(Xi, Yj, Xi + 2, Yj + 1, cor)) {
					table[Xi + 2][Yj + 1].atcPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi + 2, Yj + 1);
					index += 1;
				}
			}
			
		}


		if(Xi - 1 >= 0 && Yj - 2 >= 0) {
			if(table[Xi - 1][Yj - 2].peca == null) {
				if(!preveCheck(Xi, Yj, Xi - 1, Yj - 2, cor)) {
					table[Xi - 1][Yj - 2].movPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi - 1, Yj - 2);
					index += 1;
				}
			}
			else if(table[Xi][Yj].peca.cor != table[Xi - 1][Yj - 2].peca.cor) {
				if(!preveCheck(Xi, Yj, Xi - 1, Yj - 2, cor)) {
					table[Xi - 1][Yj - 2].atcPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi - 1, Yj - 2);
					index += 1;
				}
			}

		}
		if(Xi - 1 >= 0 && Yj + 2 < 8) {
			if(table[Xi - 1][Yj + 2].peca == null) {
				if(!preveCheck(Xi, Yj, Xi - 1, Yj + 2, cor)) {
					table[Xi - 1][Yj + 2].movPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi - 1, Yj + 2);
					index += 1;
				}
			}
			else if(table[Xi][Yj].peca.cor != table[Xi - 1][Yj + 2].peca.cor) {
				if(!preveCheck(Xi, Yj, Xi - 1, Yj + 2, cor)) {
					table[Xi - 1][Yj + 2].atcPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi - 1, Yj + 2);
					index += 1;
				}
			}

		}
		if(Xi + 1 < 8 && Yj - 2 >= 0) {
			if(table[Xi + 1][Yj - 2].peca == null) {
				if(!preveCheck(Xi, Yj, Xi + 1, Yj - 2, cor)) {
					table[Xi + 1][Yj - 2].movPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi + 1, Yj - 2);
					index += 1;
				}
			}
			else if(table[Xi][Yj].peca.cor != table[Xi + 1][Yj - 2].peca.cor) {
				if(!preveCheck(Xi, Yj, Xi + 1, Yj - 2, cor)) {
					table[Xi + 1][Yj - 2].atcPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi + 1, Yj - 2);
					index += 1;
				}
			}

		}
		if(Xi + 1 < 8 && Yj + 2 < 8) {
			if(table[Xi + 1][Yj + 2].peca == null) {
				if(!preveCheck(Xi, Yj, Xi + 1, Yj + 2, cor)) {
					table[Xi + 1][Yj + 2].movPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi + 1, Yj + 2);
					index += 1;
				}
			}
			else if(table[Xi][Yj].peca.cor != table[Xi + 1][Yj + 2].peca.cor) {
				if(!preveCheck(Xi, Yj, Xi + 1, Yj + 2, cor)) {
					table[Xi + 1][Yj + 2].atcPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi + 1, Yj + 2);
					index += 1;
				}
			}

		}
		return casasPossiveis;
	}
	
	@Override
	public Coordenadas[] testaMov(int Xi, int Yj, Casa[][] table) {				
		Coordenadas[] casasPossiveis = new Coordenadas[64];
		int index = 0;
		
		if(Xi - 2 >= 0 && Yj - 1 >= 0) {
			if(table[Xi - 2][Yj - 1].peca == null) {
				casasPossiveis[index] = new Coordenadas(Xi - 2, Yj - 1);
				index += 1;
			}
			else if(table[Xi][Yj].peca.cor != table[Xi - 2][Yj -1].peca.cor) {
				casasPossiveis[index] = new Coordenadas(Xi - 2, Yj - 1);
				index += 1;
			}
			
		}
		if(Xi - 2 >= 0 && Yj + 1 < 8) {
			if(table[Xi - 2][Yj + 1].peca == null) {
				casasPossiveis[index] = new Coordenadas(Xi - 2, Yj + 1);
				index += 1;
			}
			else if(table[Xi][Yj].peca.cor != table[Xi - 2][Yj + 1].peca.cor) {
				casasPossiveis[index] = new Coordenadas(Xi - 2, Yj + 1);
				index += 1;
			}
			
		}
		if(Xi + 2 < 8 && Yj - 1 >= 0) {
			if(table[Xi + 2][Yj - 1].peca == null) {
				casasPossiveis[index] = new Coordenadas(Xi + 2, Yj - 1);
				index += 1;
			}
			else if(table[Xi][Yj].peca.cor != table[Xi + 2][Yj - 1].peca.cor) {
				casasPossiveis[index] = new Coordenadas(Xi + 2, Yj - 1);
				index += 1;
			}
			
		}
		if(Xi + 2 < 8 && Yj + 1 < 8) {
			if(table[Xi + 2][Yj + 1].peca == null) {
				casasPossiveis[index] = new Coordenadas(Xi + 2, Yj + 1);
				index += 1;
			}
			else if(table[Xi][Yj].peca.cor != table[Xi + 2][Yj + 1].peca.cor) {
				casasPossiveis[index] = new Coordenadas(Xi + 2, Yj + 1);
				index += 1;
			}
			
		}


		if(Xi - 1 >= 0 && Yj - 2 >= 0) {
			if(table[Xi - 1][Yj - 2].peca == null) {
				casasPossiveis[index] = new Coordenadas(Xi - 1, Yj - 2);
				index += 1;
			}
			else if(table[Xi][Yj].peca.cor != table[Xi - 1][Yj - 2].peca.cor) {
				casasPossiveis[index] = new Coordenadas(Xi - 1, Yj - 2);
				index += 1;
			}
			
		}
		if(Xi - 1 >= 0 && Yj + 2 < 8) {
			if(table[Xi - 1][Yj + 2].peca == null) {
				casasPossiveis[index] = new Coordenadas(Xi - 1, Yj + 2);
				index += 1;
			}
			else if(table[Xi][Yj].peca.cor != table[Xi - 1][Yj + 2].peca.cor) {
				casasPossiveis[index] = new Coordenadas(Xi - 1, Yj + 2);
				index += 1;
			}
			
		}
		if(Xi + 1 < 8 && Yj - 2 >= 0) {
			if(table[Xi + 1][Yj - 2].peca == null) {
				casasPossiveis[index] = new Coordenadas(Xi + 1, Yj - 2);
				index += 1;
			}
			else if(table[Xi][Yj].peca.cor != table[Xi + 1][Yj - 2].peca.cor) {
				casasPossiveis[index] = new Coordenadas(Xi + 1, Yj - 2);
				index += 1;
			}
			
		}
		if(Xi + 1 < 8 && Yj + 2 < 8) {
			if(table[Xi + 1][Yj + 2].peca == null) {
				casasPossiveis[index] = new Coordenadas(Xi + 1, Yj + 2);
				index += 1;
			}
			else if(table[Xi][Yj].peca.cor != table[Xi + 1][Yj + 2].peca.cor) {
				casasPossiveis[index] = new Coordenadas(Xi + 1, Yj + 2);
				index += 1;
			}
			
		}
		return casasPossiveis;
	}

}
