package Peca;

import java.awt.Image;

import Controller.Control;
import Observers.*;
import Tabuleiro.*;

public abstract class Peca implements ObservadorTabuleiro {
	
	public static Casa[][] table;
	
	public int posX, posY;
	public char cor;
	public Image imagem;
	public boolean selecionada = false;
	public String nome;
	public Coordenadas coord;
	
	public Peca(char cor, int posicaoX, int posicaoY, String nome, Image img, Coordenadas coord) {
		this.posX = posicaoX;
		this.posY = posicaoY;
		this.cor = cor;
		this.nome = nome;
		this.imagem = img;
		this.coord = coord;
		
		Control.getInstance().registra(this);
	}
	
	public abstract Coordenadas[] getMovPossiveis(int Xi, int Yj);
	public abstract Coordenadas[] testaMov(int Xi, int Yj, Casa[][] table);

	public static boolean verificaCheck(Casa[][] table, char cor){
	
		Coordenadas coordRei = new Coordenadas();
		boolean isInCheck = false;

		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(table[i][j].peca != null) {
					if(table[i][j].peca.nome == "Rei" && table[i][j].peca.cor == cor) {
						coordRei = new Coordenadas(i, j);
					}
				}
			}
		}

		Coordenadas[] casasPos = new Coordenadas[64];		

		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(table[i][j].peca != null) {
					if(table[i][j].peca.cor != cor) {
						casasPos = table[i][j].peca.testaMov(i, j, table);
						int k =0;
						while (casasPos[k] != null) {
							if(casasPos[k].x == coordRei.x && casasPos[k].y == coordRei.y) {
								isInCheck = true;
							}
							k++;
						}
					}
				}
			}
		}
		return isInCheck;
	}

	//Verifica se uma movimentacao causaria um check no proprio rei -> movimento invalido
	static boolean preveCheck(int posX,  int posY, int novoX, int novoY, char cor) {
		Casa[][] table = null;

		table = simulaMovimento(posX, posY, novoX, novoY);

		return verificaCheck(table, cor);
	}

	public static boolean verificaExisteMovPossiveis(char cor){
		Coordenadas[] casasPos;

		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(table[i][j].peca != null) {
					if(table[i][j].peca.cor == cor) {
						casasPos = table[i][j].peca.testaMov(i, j, table);
						
						int k = 0;
						while(casasPos[k] != null) {
						
							if(!preveCheck(i , j, casasPos[k].x, casasPos[k].y, cor)) {
								System.out.println(i + ", " + j + "// " + casasPos[k].x  + ", " + casasPos[k].y);
								return true;
							}
							k++;
						}
										
					}					
				}
			}
		}
			
		return false;
	}


	protected char[][] iniciaPosMov() {
		char mat[][] = new char[8][8];

		for(int i=0; i< mat.length; i++) {
			for(int j=0; j< mat[i].length; j++) {
				mat[i][j] = 'x';
			}
		}
		return mat;
	}


	public static Casa[][] simulaMovimento(int originX, int originY, int destX, int destY) {

		Casa tableSim[][] = new Casa[8][8];

		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				tableSim[i][j] = table[i][j].clone();
			}
		}

		Peca p = tableSim[originX][originY].peca;
		int newPosX = 64*destX + Tabuleiro.offsetX;
		int newPosY = 64*destY + Tabuleiro.offsetY;

		switch (p.nome) {
		case "Torre":
			tableSim[destX][destY].peca = new Torre(p.cor,newPosX,newPosY,p.nome, p.imagem, new Coordenadas(destX,destY));
			break;
		case "Cavalo":
			tableSim[destX][destY].peca = new Cavalo(p.cor,newPosX,newPosY,p.nome, p.imagem, new Coordenadas(destX,destY));
			break;
		case "Bispo":
			tableSim[destX][destY].peca = new Bispo(p.cor,newPosX,newPosY,p.nome, p.imagem, new Coordenadas(destX,destY));
			break;
		case "Rainha":
			tableSim[destX][destY].peca = new Rainha(p.cor,newPosX,newPosY,p.nome, p.imagem, new Coordenadas(destX,destY));
			break;
		case "Rei":

			tableSim[destX][destY].peca = new Rei(p.cor,newPosX,newPosY,p.nome, p.imagem, new Coordenadas(destX,destY));

			//Roque
			if(destX > originX + 1) 
				simulaMovimento(7, originY, destX - 1, destY);
			else if(destX < originX - 1) 
				simulaMovimento(0, originY, destX + 1, destY);

			break;
		default:
			tableSim[destX][destY].peca = new Peao(p.cor,newPosX,newPosY,p.nome, p.imagem, new Coordenadas(destX,destY));
			break;
		}	

		tableSim[originX][originY].peca = null;
		
		return tableSim;
	}
	
	public void notify (ObservadoTabuleiro o) {
		table = o.get();
	}
}
