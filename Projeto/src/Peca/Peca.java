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
	
	public abstract Coordenadas[] getMovPossiveis(int Xi, int Yj);
	
	boolean verificaCheck(char cor){
		
		Casa[][] table = Tabuleiro.getTabCasa();
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
						casasPos = getMovPossiveis(i, j);
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
	boolean preveCheck(char cor, Coordenadas atual, Coordenadas nova) {
		
		Tabuleiro./*Pseudo*/movePeca(atual.x, atual.y, nova.x, nova.y);
		
		return verificaCheck(cor);
	}
	
	boolean verificaCheckMate(char cor) {
		Casa[][] table = Tabuleiro.getTabCasa();
		boolean isInCheckMate = false;
		Coordenadas[] casasPos = new Coordenadas[64];
		
		if(verificaCheck(cor)) {
			
			isInCheckMate = true;
			
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					
					if(table[i][j].peca != null) {
						if(table[i][j].peca.cor == cor) {
							casasPos = getMovPossiveis(i, j);
							for(Coordenadas c: casasPos) {
								Coordenadas atual = new Coordenadas(i, j);
								if(isInCheckMate)
									isInCheckMate = preveCheck(cor, atual, c);
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
}
