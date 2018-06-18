package Peca;

import java.awt.Image;
import Tabuleiro.*;

public class Rei extends Peca {
	
	public boolean hasMoved = false;
	
	public Rei(char cor, int posicaoX, int posicaoY, String nome, Image img, Coordenadas coord) {
		super(cor,posicaoX,posicaoY,nome, img, coord);
		
		String nomeImg;
		this.imagem = img;
		this.nome = nome;
	}
	
	@Override
	public Coordenadas[] getMovPossiveis(int Xi, int Yj) {	
		Coordenadas[] casasPossiveis = new Coordenadas[64];
		int index = 0;
		
		//Movimento vertical
		if(Yj > 0) {
			if(table[Xi][Yj - 1].peca == null) {
				if(!preveCheck(Xi, Yj, Xi, Yj - 1, cor)) {
					table[Xi][Yj - 1].movPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi,Yj - 1);
					index += 1;
				}
			}
			else if(table[Xi][Yj].peca.cor != table[Xi][Yj - 1].peca.cor) {
				if(!preveCheck(Xi, Yj, Xi, Yj - 1, cor)) {
					table[Xi][Yj - 1].atcPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi,Yj - 1);
					index += 1;
				}
			}
			
		}
		
		
		if(Yj < 7) {
			if(table[Xi][Yj + 1].peca == null) {
				if(!preveCheck(Xi, Yj, Xi, Yj + 1, cor)) {
					table[Xi][Yj + 1].movPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi,Yj+1);
					index += 1;
				}
			}
			else if(table[Xi][Yj].peca.cor != table[Xi][Yj + 1].peca.cor) {
				if(!preveCheck(Xi, Yj, Xi, Yj + 1, cor)) {
					table[Xi][Yj + 1].atcPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi,Yj+1);
					index += 1;
				}
			}

			
		}
		
		//movimento horizontal
		if(Xi > 0) {
			if(table[Xi - 1][Yj].peca == null) {
				if(!preveCheck(Xi, Yj, Xi - 1, Yj, cor)) {
					table[Xi - 1][Yj].movPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi - 1, Yj);
					index += 1;
				}
			}
			else if(table[Xi][Yj].peca.cor != table[Xi - 1][Yj].peca.cor) {
				if(!preveCheck(Xi, Yj, Xi - 1, Yj, cor)) {
					table[Xi - 1][Yj].atcPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi - 1, Yj);
					index += 1;
				}
			}

			
		}
		
		if(Xi < 7) {
			if(table[Xi + 1][Yj].peca == null) {
				if(!preveCheck(Xi, Yj, Xi + 1, Yj, cor)) {
					table[Xi + 1][Yj].movPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi + 1, Yj);
					index += 1;
				}
			}
			else if(table[Xi][Yj].peca.cor != table[Xi + 1][Yj].peca.cor) {
				if(!preveCheck(Xi, Yj, Xi + 1, Yj, cor)) {
					table[Xi + 1][Yj].atcPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi + 1, Yj);
					index += 1;
				}
			}

		}
		
		//Roque
		if(!hasMoved) {
			
			if(table[Xi - 1][Yj].peca == null && table[Xi - 2][Yj].peca == null && table[0][Yj].peca != null) {
				if(Torre.class.isInstance(table[0][Yj].peca)) {
					Torre temp = (Torre)table[0][Yj].peca;
					if(!temp.hasMoved) {
						if(!preveCheck(Xi, Yj, Xi - 2, Yj, cor)) {
							table[Xi - 2][Yj].movPossivel = true;
							casasPossiveis[index] = new Coordenadas(Xi - 2, Yj);
							index += 1;
						}
					}
				}
			}
			
			if(table[Xi + 1][Yj].peca == null && table[Xi + 2][Yj].peca == null && table[7][Yj].peca != null) {
				if(Torre.class.isInstance(table[7][Yj].peca)) {
					Torre temp = (Torre)table[7][Yj].peca;
					if(!temp.hasMoved) {
						if(!preveCheck(Xi, Yj, Xi + 2, Yj, cor)) {
							table[Xi + 2][Yj].movPossivel = true;
							casasPossiveis[index] = new Coordenadas(Xi + 2, Yj);
							index += 1;
						}
					}
						
				}
			}
		}
		
		//diagonal
		if(Xi > 0 && Yj > 0) {
			if(table[Xi - 1][Yj - 1].peca == null) {
				if(!preveCheck(Xi, Yj, Xi - 1, Yj - 1, cor)) {
					table[Xi - 1][Yj - 1].movPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi - 1, Yj - 1);
					index += 1;
				}
			}
			else if(table[Xi][Yj].peca.cor != table[Xi - 1][Yj - 1].peca.cor) {
				if(!preveCheck(Xi, Yj, Xi - 1, Yj - 1, cor)) {
					table[Xi - 1][Yj - 1].atcPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi - 1, Yj - 1);
					index += 1;
				}
			}
			
		}
		if(Xi > 0 && Yj < 7) {
			if(table[Xi - 1][Yj + 1].peca == null) {
				if(!preveCheck(Xi, Yj, Xi - 1, Yj + 1, cor)) {
					table[Xi - 1][Yj + 1].movPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi - 1, Yj + 1);
					index += 1;
				}
			}
			else if(table[Xi][Yj].peca.cor != table[Xi - 1][Yj + 1].peca.cor) {
				if(!preveCheck(Xi, Yj, Xi - 1, Yj + 1, cor)) {
					table[Xi - 1][Yj + 1].atcPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi - 1, Yj + 1);
					index += 1;
				}
			}
			
		}
		if(Xi < 7 && Yj > 0) {
			if(table[Xi + 1][Yj - 1].peca == null) {
				if(!preveCheck(Xi, Yj, Xi + 1, Yj - 1, cor)) {
					table[Xi + 1][Yj - 1].movPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi + 1, Yj - 1);
					index += 1;
				}
			}
			else if(table[Xi][Yj].peca.cor != table[Xi + 1][Yj - 1].peca.cor) {
				if(!preveCheck(Xi, Yj, Xi + 1, Yj - 1, cor)) {
					table[Xi + 1][Yj - 1].atcPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi + 1, Yj - 1);
					index += 1;
				}
			}
			
		}
		if(Xi < 7 && Yj <  7) {
			if(table[Xi + 1][Yj + 1].peca == null) {
				if(!preveCheck(Xi, Yj, Xi + 1, Yj + 1, cor)) {
					table[Xi + 1][Yj + 1].movPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi + 1, Yj + 1);
					index += 1;
				}
			}
			else if(table[Xi][Yj].peca.cor != table[Xi + 1][Yj + 1].peca.cor) {
				if(!preveCheck(Xi, Yj, Xi + 1, Yj + 1, cor)) {
					table[Xi + 1][Yj + 1].atcPossivel = true;
					casasPossiveis[index] = new Coordenadas(Xi + 1, Yj + 1);
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
		
		//Movimento vertical
		if(Yj > 0) {
			if(table[Xi][Yj - 1].peca == null) {
				casasPossiveis[index] = new Coordenadas(Xi,Yj - 1);
				index += 1;
			}
			else if(table[Xi][Yj].peca.cor != table[Xi][Yj - 1].peca.cor) {
				casasPossiveis[index] = new Coordenadas(Xi,Yj - 1);
				index += 1;
			}
			
		}
		
		
		if(Yj < 7) {
			if(table[Xi][Yj + 1].peca == null) {
				casasPossiveis[index] = new Coordenadas(Xi,Yj+1);
				index += 1;
			}
			else if(table[Xi][Yj].peca.cor != table[Xi][Yj + 1].peca.cor) {
				casasPossiveis[index] = new Coordenadas(Xi,Yj+1);
				index += 1;
			}

			
		}
		
		//movimento horizontal
		if(Xi > 0) {
			if(table[Xi - 1][Yj].peca == null) {
				casasPossiveis[index] = new Coordenadas(Xi - 1, Yj);
				index += 1;
			}
			else if(table[Xi][Yj].peca.cor != table[Xi - 1][Yj].peca.cor) {
				casasPossiveis[index] = new Coordenadas(Xi - 1, Yj);
				index += 1;
			}

			
		}
		
		if(Xi < 7) {
			if(table[Xi + 1][Yj].peca == null) {
				casasPossiveis[index] = new Coordenadas(Xi + 1, Yj);
				index += 1;
			}
			else if(table[Xi][Yj].peca.cor != table[Xi + 1][Yj].peca.cor) {
				casasPossiveis[index] = new Coordenadas(Xi + 1, Yj);
				index += 1;
			}

		}
		
		//Roque
		if(!hasMoved) {
			
			if(table[Xi - 1][Yj].peca == null && table[Xi - 2][Yj].peca == null && table[0][Yj].peca != null) {
				if(Torre.class.isInstance(table[0][Yj].peca)) {
					Torre temp = (Torre)table[0][Yj].peca;
					if(!temp.hasMoved) {
						casasPossiveis[index] = new Coordenadas(Xi - 2, Yj);
						index += 1;
					}
				}
			}
			
			if(table[Xi + 1][Yj].peca == null && table[Xi + 2][Yj].peca == null && table[7][Yj].peca != null) {
				if(Torre.class.isInstance(table[7][Yj].peca)) {
					Torre temp = (Torre)table[7][Yj].peca;
					if(!temp.hasMoved) {
						casasPossiveis[index] = new Coordenadas(Xi + 2, Yj);
						index += 1;
					}
						
				}
			}
		}
		
		//diagonal
		if(Xi > 0 && Yj > 0) {
			if(table[Xi - 1][Yj - 1].peca == null) {
				casasPossiveis[index] = new Coordenadas(Xi - 1, Yj - 1);
				index += 1;
			}
			else if(table[Xi][Yj].peca.cor != table[Xi - 1][Yj - 1].peca.cor) {
				casasPossiveis[index] = new Coordenadas(Xi - 1, Yj - 1);
				index += 1;
			}
			
		}
		if(Xi > 0 && Yj < 7) {
			if(table[Xi - 1][Yj + 1].peca == null) {
				casasPossiveis[index] = new Coordenadas(Xi - 1, Yj + 1);
				index += 1;
			}
			else if(table[Xi][Yj].peca.cor != table[Xi - 1][Yj + 1].peca.cor) {
				casasPossiveis[index] = new Coordenadas(Xi - 1, Yj + 1);
				index += 1;
			}
			
		}
		if(Xi < 7 && Yj > 0) {
			if(table[Xi + 1][Yj - 1].peca == null) {
				casasPossiveis[index] = new Coordenadas(Xi + 1, Yj - 1);
				index += 1;
			}
			else if(table[Xi][Yj].peca.cor != table[Xi + 1][Yj - 1].peca.cor) {
				casasPossiveis[index] = new Coordenadas(Xi + 1, Yj - 1);
				index += 1;
			}
			
		}
		if(Xi < 7 && Yj <  7) {
			if(table[Xi + 1][Yj + 1].peca == null) {
				casasPossiveis[index] = new Coordenadas(Xi + 1, Yj + 1);
				index += 1;
			}
			else if(table[Xi][Yj].peca.cor != table[Xi + 1][Yj + 1].peca.cor) {
				casasPossiveis[index] = new Coordenadas(Xi + 1, Yj + 1);
				index += 1;
			}			
		}
		
		return casasPossiveis;
	}

}
