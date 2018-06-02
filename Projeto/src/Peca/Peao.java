package Peca;

import java.awt.Image;

import Interface.XadrezFrame;
import Tabuleiro.*;

public class Peao extends Peca {
	
	public boolean hasMoved = false;
	
	public Peao(char cor, int posicaoX, int posicaoY, String nome, Image img) {
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
		
		int maxMov;
		if(hasMoved)
			maxMov = 1;
		else
			maxMov = 2;
		
		// bloquear de ir alem de uma peça
		boolean encontrou = false;
		for(int i = 1; i <= maxMov; i++){
			if(Yj > 0 && table[Xi][Yj].peca.cor == 'B' && !encontrou) {
				if(table[Xi][Yj - i].peca == null) {
					if(!preveCheck(Xi, Yj, Xi, Yj - i)) {
						table[Xi][Yj - i].movPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi,Yj - i);
						index += 1;
					}
				}
				else
					encontrou = true;
				
				
			}
			else if(Yj < 8 && table[Xi][Yj].peca.cor == 'P' && !encontrou) {
				if(table[Xi][Yj + i].peca == null) {
					if(!preveCheck(Xi, Yj, Xi, Yj + i)) {
						table[Xi][Yj + i].movPossivel = true;
						casasPossiveis[index] = new Coordenadas(Xi,Yj + i);
						index += 1;
					}
				}
				else
					encontrou = true;
				
			}
			
		}
		
		if(table[Xi][Yj].peca.cor == 'B') {
			if(Xi > 0 && Yj > 0) {
				if(table[Xi - 1][Yj - 1].peca != null) {
					if(table[Xi - 1][Yj - 1].peca. cor != table[Xi][Yj].peca.cor) {
						if(!preveCheck(Xi, Yj, Xi - 1, Yj - 1)) {
							table[Xi - 1][Yj - 1].atcPossivel = true;
							casasPossiveis[index] = new Coordenadas(Xi - 1,Yj - 1);
							index += 1;
						}
					}
				}
			}
			if(Xi < 7 && Yj > 0) {
				if(table[Xi + 1][Yj - 1].peca != null) {
					if(table[Xi + 1][Yj - 1].peca. cor != table[Xi][Yj].peca.cor) {
						if(!preveCheck(Xi, Yj, Xi + 1, Yj - 1)) {
							table[Xi + 1][Yj - 1].atcPossivel = true;
							casasPossiveis[index] = new Coordenadas(Xi + 1,Yj - 1);
							index += 1;
						}
					}
				}
			}
		}
		else if(table[Xi][Yj].peca.cor == 'P') {
			if(Xi > 0 && Yj < 7) {
				if(table[Xi - 1][Yj + 1].peca != null) {
					if(table[Xi - 1][Yj + 1].peca. cor != table[Xi][Yj].peca.cor) {
						if(!preveCheck(Xi, Yj, Xi - 1, Yj + 1)) {
							table[Xi - 1][Yj + 1].atcPossivel = true;
							casasPossiveis[index] = new Coordenadas(Xi - 1,Yj + 1);
							index += 1;
						}
					}
				}
			}
			if(Xi < 7 && Yj < 7) {
				if(table[Xi + 1][Yj + 1].peca != null) {
					if(table[Xi + 1][Yj + 1].peca. cor != table[Xi][Yj].peca.cor) {
						if(!preveCheck(Xi, Yj, Xi + 1, Yj + 1)) {
							table[Xi + 1][Yj + 1].atcPossivel = true;
							casasPossiveis[index] = new Coordenadas(Xi + 1,Yj + 1);
							index += 1;
						}
					}
				}
			}
		}
		return casasPossiveis;
	}
	
	@Override
	public Coordenadas[] testaMov(int Xi, int Yj, Casa[][] table) { 
				
		Coordenadas[] casasPossiveis = new Coordenadas[64];
		int index = 0;
		
		int maxMov;
		if(hasMoved)
			maxMov = 1;
		else
			maxMov = 2;
		
		// bloquear de ir alem de uma peça
		boolean encontrou = false;
		for(int i = 1; i <= maxMov; i++){
			if(Yj > 0 && table[Xi][Yj].peca.cor == 'B' && !encontrou) {
				if(table[Xi][Yj - i].peca == null) {
					casasPossiveis[index] = new Coordenadas(Xi,Yj - i);
					index += 1;
				}
				else
					encontrou = true;
				
				
			}
			else if(Yj < 8 && table[Xi][Yj].peca.cor == 'P' && !encontrou) {
				if(table[Xi][Yj + i].peca == null) {
					casasPossiveis[index] = new Coordenadas(Xi,Yj + i);
					index += 1;
				}
				else
					encontrou = true;
				
			}
			
		}
		
		if(table[Xi][Yj].peca.cor == 'B') {
			if(Xi > 0 && Yj > 0) {
				if(table[Xi - 1][Yj - 1].peca != null)
					if(table[Xi - 1][Yj - 1].peca. cor != table[Xi][Yj].peca.cor) {
						casasPossiveis[index] = new Coordenadas(Xi - 1,Yj - 1);
						index += 1;
					}
			}
			if(Xi < 7 && Yj > 0) {
				if(table[Xi + 1][Yj - 1].peca != null)
					if(table[Xi + 1][Yj - 1].peca. cor != table[Xi][Yj].peca.cor) {
						casasPossiveis[index] = new Coordenadas(Xi + 1,Yj - 1);
						index += 1;
					}
			}
		}
		else if(table[Xi][Yj].peca.cor == 'P') {
			if(Xi > 0 && Yj < 7) {
				if(table[Xi - 1][Yj + 1].peca != null)
					if(table[Xi - 1][Yj + 1].peca. cor != table[Xi][Yj].peca.cor) {
						casasPossiveis[index] = new Coordenadas(Xi - 1,Yj + 1);
						index += 1;
					}
			}
			if(Xi < 7 && Yj < 7) {
				if(table[Xi + 1][Yj + 1].peca != null)
					if(table[Xi + 1][Yj + 1].peca. cor != table[Xi][Yj].peca.cor) {
						casasPossiveis[index] = new Coordenadas(Xi + 1,Yj + 1);
						index += 1;
					}
			}
		}
		return casasPossiveis;
	}
}
