package Peca;

import java.awt.Image;

import Interface.XadrezFrame;
import Tabuleiro.*;

public class Cavalo extends Peca {
	
	public Cavalo(char cor, int posicaoX, int posicaoY, String nome, Image img) {
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
		
		if(Xi - 2 >= 0 && Yj - 1 >= 0) {
			if(table[Xi - 2][Yj - 1].peca == null) 
				table[Xi - 2][Yj - 1].movPossivel = true;
			else if(table[Xi][Yj].peca.cor != table[Xi - 2][Yj -1].peca.cor)
				table[Xi - 2][Yj - 1].atcPossivel = true;
			casasPossiveis[index] = new Coordenadas(Xi - 2, Yj - 1);
			index += 1;
		}
		if(Xi - 2 >= 0 && Yj + 1 < 8) {
			if(table[Xi - 2][Yj + 1].peca == null) 
				table[Xi - 2][Yj + 1].movPossivel = true;
			else if(table[Xi][Yj].peca.cor != table[Xi - 2][Yj + 1].peca.cor)
				table[Xi - 2][Yj + 1].atcPossivel = true;
			casasPossiveis[index] = new Coordenadas(Xi - 2, Yj + 1);
			index += 1;
		}
		if(Xi + 2 < 8 && Yj - 1 >= 0) {
			if(table[Xi + 2][Yj - 1].peca == null) 
				table[Xi + 2][Yj - 1].movPossivel = true;
			else if(table[Xi][Yj].peca.cor != table[Xi + 2][Yj - 1].peca.cor)
				table[Xi + 2][Yj - 1].atcPossivel = true;
			casasPossiveis[index] = new Coordenadas(Xi + 2, Yj - 1);
			index += 1;
		}
		if(Xi + 2 < 8 && Yj + 1 < 8) {
			if(table[Xi + 2][Yj + 1].peca == null) 
				table[Xi + 2][Yj + 1].movPossivel = true;
			else if(table[Xi][Yj].peca.cor != table[Xi + 2][Yj + 1].peca.cor)
				table[Xi + 2][Yj + 1].atcPossivel = true;
			casasPossiveis[index] = new Coordenadas(Xi + 2, Yj + 1);
			index += 1;
		}


		if(Xi - 1 >= 0 && Yj - 2 >= 0) {
			if(table[Xi - 1][Yj - 2].peca == null) 
				table[Xi - 1][Yj - 2].movPossivel = true;
			else if(table[Xi][Yj].peca.cor != table[Xi - 1][Yj - 2].peca.cor)
				table[Xi - 1][Yj - 2].atcPossivel = true;
			casasPossiveis[index] = new Coordenadas(Xi - 1, Yj - 2);
			index += 1;
		}
		if(Xi - 1 >= 0 && Yj + 2 < 8) {
			if(table[Xi - 1][Yj + 2].peca == null) 
				table[Xi - 1][Yj + 2].movPossivel = true;
			else if(table[Xi][Yj].peca.cor != table[Xi - 1][Yj + 2].peca.cor)
				table[Xi - 1][Yj + 2].atcPossivel = true;
			casasPossiveis[index] = new Coordenadas(Xi - 1, Yj + 2);
			index += 1;
		}
		if(Xi + 1 < 8 && Yj - 2 >= 0) {
			if(table[Xi + 1][Yj - 2].peca == null) 
				table[Xi + 1][Yj - 2].movPossivel = true;
			else if(table[Xi][Yj].peca.cor != table[Xi + 1][Yj - 2].peca.cor)
				table[Xi + 1][Yj - 2].atcPossivel = true;
			casasPossiveis[index] = new Coordenadas(Xi + 1, Yj - 2);
			index += 1;
		}
		if(Xi + 1 < 8 && Yj + 2 < 8) {
			if(table[Xi + 1][Yj + 2].peca == null) 
				table[Xi + 1][Yj + 2].movPossivel = true;
			else if(table[Xi][Yj].peca.cor != table[Xi + 1][Yj + 2].peca.cor)
				table[Xi + 1][Yj + 2].atcPossivel = true;
			casasPossiveis[index] = new Coordenadas(Xi + 1, Yj + 2);
			index += 1;
		}
		return casasPossiveis;
	}

}
