package Peca;
import java.awt.Image;



public abstract class Peca {
	public int posX, posY;
	public char cor;
	public Image imagem;
	public String nome;
	
	public Peca(char cor, Image img, int posicaoX, int posicaoY, String nome) {
		this.posX = posicaoX;
		this.posY = posicaoY;
		this.cor = cor;
		this.imagem = img;	
		this.nome = nome;
	}
	
	public abstract char[][] movsPossiveis();
	
	protected char[][] iniciaPosMov() {
		char mat[][] = new char[8][8];
		
		for(int i=0; i< mat.length; i++) {
			for(int j=0; j< mat[i].length; j++) {
				mat[i][j] = 'x';
			}
		}
		return mat;
	}
	
	}
