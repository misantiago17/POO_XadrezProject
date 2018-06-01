package Peca;

import java.awt.Image;
import Interface.XadrezFrame;
import Tabuleiro.*;

public abstract class Peca {
	public int posX, posY;
	public char cor;
	public Image imagem;
	public boolean selecionada = false;
	public String nome;
	
	public Peca(char cor, int posicaoX, int posicaoY, String nome, Image img) {
		this.posX = posicaoX;
		this.posY = posicaoY;
		this.cor = cor;
		this.nome = nome;
		this.imagem = img;
	}
	
	public abstract Coordenadas[] getMovPossiveis(int Xi, int Yj) throws CloneNotSupportedException;
	public abstract Coordenadas[] testaMov(int Xi, int Yj);
	
	boolean verificaCheck(Casa[][] table){
		
		Coordenadas coordRei = new Coordenadas();
		boolean isInCheck = false;
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(Rei.class.isInstance(table[i][j].peca) && table[i][j].peca.cor == cor) 
					coordRei = new Coordenadas(i, j);	
			}
		}
		
		Coordenadas[] casasPos = new Coordenadas[64];
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(table[i][j].peca != null) {
					if(table[i][j].peca.cor != cor) {
						casasPos = testaMov(i, j);
						for(Coordenadas c: casasPos) {
							if(c == coordRei)
								isInCheck = true;
						}
					}
				}
			}
		}
		return isInCheck;
	}
	
	//Verifica se uma movimentacao causaria um check no proprio rei -> movimento invalido
	boolean preveCheck(int posX,  int posY, int novoX, int novoY) throws CloneNotSupportedException {
		Casa[][] table;
		
		table = simulaMovimento(posX, posY, novoX, novoY);
		
		return verificaCheck(table);
	}
	
	boolean verificaCheckMate() throws CloneNotSupportedException {
		Casa[][] table = Tabuleiro.getTabCasa();
		boolean isInCheckMate = false;
		Coordenadas[] casasPos = new Coordenadas[64];
		
		if(verificaCheck(table)) {
			
			isInCheckMate = true;
			
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					
					if(table[i][j].peca != null) {
						if(table[i][j].peca.cor == cor) {
							casasPos = testaMov(i, j);
							for(Coordenadas c: casasPos) {
								if(isInCheckMate)
									isInCheckMate = preveCheck(i, j, c.x, c.y);
							}
						}
					}
					
				}
			}
			
		}
		return isInCheckMate;
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
	
	protected Image buscaNomeImg(String nome) {		
		for(int i = 0 ; i < XadrezFrame.nomeImagens.length; i++) {
			if(XadrezFrame.nomeImagens[i].equals(nome)) {
				return XadrezFrame.imagens[i];
			}
		}
		return null;
	}
	
	public Casa[][] simulaMovimento(int originX, int originY, int destX, int destY) throws CloneNotSupportedException {
		Casa table[][] = new Casa[8][8];
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				table[i][j] = Tabuleiro.getTabCasa()[i][j].clone();
			}
		}
		
		Peca p = table[originX][originY].peca;
		int newPosX = 64*destX + Tabuleiro._offsetX;
		int newPosY = 64*destY + Tabuleiro._offsetY;
				
		switch (p.nome) {
		case "Torre":
			table[destX][destY].peca = new Torre(p.cor,newPosX,newPosY,p.nome, p.imagem);
			break;
		case "Cavalo":
			table[destX][destY].peca = new Cavalo(p.cor,newPosX,newPosY,p.nome, p.imagem);
			break;
		case "Bispo":
			table[destX][destY].peca = new Bispo(p.cor,newPosX,newPosY,p.nome, p.imagem);
			break;
		case "Rainha":
			table[destX][destY].peca = new Rainha(p.cor,newPosX,newPosY,p.nome, p.imagem);
			break;
		case "Rei":

			table[destX][destY].peca = new Rei(p.cor,newPosX,newPosY,p.nome, p.imagem);
			
			//Roque
			if(destX > originX + 1) 
				simulaMovimento(7, originY, destX - 1, destY);
			else if(destX < originX - 1) 
				simulaMovimento(0, originY, destX + 1, destY);
			
			break;
			default:
				table[destX][destY].peca = new Peao(p.cor,newPosX,newPosY,p.nome, p.imagem);
				break;
		}	

		table[originX][originY].peca = null;

		return table;
	}
}
