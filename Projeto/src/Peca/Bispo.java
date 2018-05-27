package Peca;

import java.awt.Image;

import Interface.XadrezFrame;
import Tabuleiro.*;

public class Bispo extends Peca {
	
	public Bispo(char cor, int posicaoX, int posicaoY, String nome, Image img) {
		super(cor,posicaoX,posicaoY,nome,img);
		
		String nomeImg;
		/*if(cor == 'B') 
			nomeImg = "CyanB.png";
		else
			nomeImg = "PurpleB.png";
				
		this.imagem = buscaNomeImg(nomeImg);*/
		this.imagem = img;
		this.nome = nome;
	}
	
	@Override
	public Coordenadas[] getMovPossiveis(int Xi, int Yj) {
		Casa[][] table = Tabuleiro.getTabCasa();
		
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
						
				casasPossiveis[index] = new Coordenadas(Xi - i,Yj + i);
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
