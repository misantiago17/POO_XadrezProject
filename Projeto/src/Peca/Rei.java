package Peca;

import java.awt.Image;
import Interface.XadrezFrame;
import Tabuleiro.*;

public class Rei extends Peca {
	
	public Rei(char cor, int posicaoX, int posicaoY, String nome, Image img) {
		super(cor,posicaoX,posicaoY,nome, img);
		
		String nomeImg;
		/*if(cor == 'B') 
			nomeImg = "CyanK.png";
		else
			nomeImg = "PurpleK.png";
				
		this.imagem = buscaNomeImg(nomeImg);*/
		this.imagem = img;
		this.nome = nome;
	}
	
	@Override
	public Coordenadas[] getMovPossiveis(int Xi, int Yj) { // só anda uma casa 
		Casa[][] table = Tabuleiro.getTabCasa();
		
		Coordenadas[] casasPossiveis = new Coordenadas[64];
		int index = 0;
		
		//Movimento vertical
		if(Yj > 0) {
			if(table[Xi][Yj - 1].peca == null) 
				table[Xi][Yj - 1].movPossivel = true;
			else if(table[Xi][Yj].peca.cor != table[Xi][Yj - 1].peca.cor)
				table[Xi][Yj + 1].atcPossivel = true;
			casasPossiveis[index] = new Coordenadas(Xi,Yj - 1);
			index += 1;
		}
		
		
		if(Yj < 8) {
			if(table[Xi][Yj + 1].peca == null) 
				table[Xi][Yj + 1].movPossivel = true;
			else if(table[Xi][Yj].peca.cor != table[Xi][Yj + 1].peca.cor)
				table[Xi][Yj + 1].atcPossivel = true;

			casasPossiveis[index] = new Coordenadas(Xi,Yj+1);
			index += 1;
		}
		
		//movimento horizontal
		if(Xi > 0) {
			if(table[Xi - 1][Yj].peca == null) 
				table[Xi - 1][Yj].movPossivel = true;
			else if(table[Xi][Yj].peca.cor != table[Xi - 1][Yj].peca.cor)
				table[Xi - 1][Yj].atcPossivel = true;

			casasPossiveis[index] = new Coordenadas(Xi - 1, Yj);
			index += 1;
		}
		
		if(Xi < 8) {
			if(table[Xi + 1][Yj].peca == null) 
				table[Xi + 1][Yj].movPossivel = true;
			else if(table[Xi][Yj].peca.cor != table[Xi + 1][Yj].peca.cor)
				table[Xi + 1][Yj].atcPossivel = true;

			casasPossiveis[index] = new Coordenadas(Xi + 1, Yj);
			index += 1;
		}
		
		//diagonal
		if(Xi > 0 && Yj > 0) {
			if(table[Xi - 1][Yj - 1].peca == null) 
				table[Xi - 1][Yj - 1].movPossivel = true;
			else if(table[Xi][Yj].peca.cor != table[Xi - 1][Yj - 1].peca.cor)
				table[Xi - 1][Yj - 1].atcPossivel = true;

			casasPossiveis[index] = new Coordenadas(Xi - 1, Yj - 1);
			index += 1;
		}
		if(Xi > 0 && Yj < 8) {
			if(table[Xi - 1][Yj + 1].peca == null) 
				table[Xi - 1][Yj + 1].movPossivel = true;
			else if(table[Xi][Yj].peca.cor != table[Xi - 1][Yj + 1].peca.cor)
				table[Xi - 1][Yj + 1].atcPossivel = true;

			casasPossiveis[index] = new Coordenadas(Xi - 1, Yj + 1);
			index += 1;
		}
		if(Xi < 8 && Yj > 0) {
			if(table[Xi + 1][Yj - 1].peca == null) 
				table[Xi + 1][Yj - 1].movPossivel = true;
			else if(table[Xi][Yj].peca.cor != table[Xi + 1][Yj - 1].peca.cor)
				table[Xi + 1][Yj - 1].atcPossivel = true;

			casasPossiveis[index] = new Coordenadas(Xi + 1, Yj - 1);
			index += 1;
		}
		if(Xi < 8 && Yj <  8) {
			if(table[Xi + 1][Yj + 1].peca == null) 
				table[Xi + 1][Yj + 1].movPossivel = true;
			else if(table[Xi][Yj].peca.cor != table[Xi + 1][Yj + 1].peca.cor)
				table[Xi + 1][Yj + 1].atcPossivel = true;

			casasPossiveis[index] = new Coordenadas(Xi + 1, Yj + 1);
			index += 1;
		}
		
		return casasPossiveis;
	}

}
