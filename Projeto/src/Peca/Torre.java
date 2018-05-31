package Peca;

import java.awt.Image;

import Interface.XadrezFrame;
import Tabuleiro.*;


public class Torre extends Peca {
	
	public boolean hasMoved = false;
	
	public Torre(char cor, int posicaoX, int posicaoY, String nome, Image img) {
		super(cor,posicaoX,posicaoY,nome, img);
		
		String nomeImg;
		this.imagem = img;
		this.nome = nome;
	}
	
	@Override
public Coordenadas[] getMovPossiveis(int Xi, int Yj) { 
		
		Casa[][] table = Tabuleiro.getTabCasa();
		
		Coordenadas[] casasPossiveis = new Coordenadas[64];
		int index = 0;
		
		// bloquear de ir alem de uma peça
		boolean encontrouVertCima = false;
		boolean encontrouVertBaixo = false;
		boolean encontrouHorzDireita = false;
		boolean encontrouHorzEsquerda = false;
		
		for (int i=1; i <= 8; i++) {
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
			if(!encontrouHorzEsquerda && Xi - i >= 0) {
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
		}
		
		return casasPossiveis;
	}

}
