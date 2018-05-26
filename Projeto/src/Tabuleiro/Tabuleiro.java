package Tabuleiro;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import com.sun.prism.Image;
import Controller.Control;
import Interface.DrawChess;
import Interface.XadrezFrame;
import Peca.*;

public final class Tabuleiro {
	
	private static Tabuleiro INSTANCE;
	
	//
	private static Casa _tabuleiroCasa[][] = new Casa[8][8];
	private static char _tabuleiroChar[][] = new char[8][8]; // 
	
	public static Tabuleiro currentTable;
	
	
	private int _offsetX;
	private int _offsetY;
	private java.awt.Image[] _imgs;
	
	public Tabuleiro(Rectangle2D[][] ret, Color[][] cor, int x, int y) {
		
		_offsetX = x;
		_offsetY = y;
		currentTable = this;
		_imgs = XadrezFrame.imagens; // CyanB, CyanK,  CyanN,  CyanP,  CyanQ,  CyanR, 
									 // PurpleB, PurpleK, PurpleN, PurpleP, PurpleQ, PurpleR  
				
		_tabuleiroChar[0][0] = 't';
		_tabuleiroCasa[0][0] = new Casa(ret[0][0], new Torre('B', 64*0 + x, 64*0 + y, "Torre", _imgs[5]),cor[0][0]);
		
		_tabuleiroChar[1][0] = 'c';
		_tabuleiroCasa[1][0] = new Casa(ret[1][0], new Cavalo('B', 64*1 + x, 64*0 + y, "Cavalo", _imgs[2]),cor[1][0]);
		
		_tabuleiroChar[2][0] = 'b';
		_tabuleiroCasa[2][0] = new Casa(ret[2][0], new Bispo('B', 64*2 + x, 64*0 + y, "Bispo", _imgs[0]),cor[2][0]);
		
		_tabuleiroChar[3][0] = 'k';
		_tabuleiroCasa[3][0] = new Casa(ret[3][0], new Rei('B', 64*3 + x, 64*0 + y, "Rei", _imgs[1]),cor[3][0]);
		
		_tabuleiroChar[4][0] = 'q';
		_tabuleiroCasa[4][0] = new Casa(ret[4][0], new Rainha('B', 64*4 + x, 64*0 + y, "Rainha", _imgs[4]),cor[4][0]);
		
		_tabuleiroChar[5][0] = 'b';
		_tabuleiroCasa[5][0] = new Casa(ret[5][0], new Bispo('B', 64*5 + x, 64*0 + y, "Bispo", _imgs[0]),cor[5][0]);
		
		_tabuleiroChar[6][0] = 'c';
		_tabuleiroCasa[6][0] = new Casa(ret[6][0], new Cavalo('B', 64*6 + x, 64*0 + y, "Cavalo", _imgs[2]),cor[6][0]);
		
		_tabuleiroChar[7][0] = 't';
		_tabuleiroCasa[7][0] = new Casa(ret[7][0], new Torre('B', 64*7 + x, 64*0 + y, "Torre", _imgs[5]),cor[7][0]);
		
		for(int i = 0; i < 8; i++) {
			_tabuleiroChar[i][1] = 'p';
			_tabuleiroCasa[i][1] = new Casa(ret[i][1], new Peao('B', 64*i + x, 64*1 + y, "Peao", _imgs[3]),cor[i][1]);
		}
		
		for(int i = 2; i < 6; i++) {
			for(int j = 0; j < 8; j++) {
				_tabuleiroChar[j][i] = 'x';
				_tabuleiroCasa[j][i] = new Casa(ret[j][i],null,cor[j][i]);
			}
		}
		
		for(int i = 0; i < 8; i++) {
			_tabuleiroChar[i][6] = 'P';
			_tabuleiroCasa[i][6] = new Casa(ret[i][6], new Peao('P', 64*i + x, 64*6 + y, "Peao", _imgs[9]),cor[i][6]);
		}
		
		_tabuleiroChar[0][7] = 'T';
		_tabuleiroCasa[0][7] = new Casa(ret[0][7], new Torre('P', 64*0 + x, 64*7 + y, "Torre", _imgs[11]),cor[0][7]);
		
		_tabuleiroChar[1][7] = 'C';
		_tabuleiroCasa[1][7] = new Casa(ret[1][7], new Cavalo('P', 64*1 + x, 64*7 + y, "Cavalo", _imgs[8]),cor[1][7]);
		
		_tabuleiroChar[2][7] = 'B';
		_tabuleiroCasa[2][7] = new Casa(ret[2][7], new Bispo('P', 64*2 + x, 64*7 + y, "Bispo", _imgs[6]),cor[2][7]);
		
		_tabuleiroChar[3][7] = 'K';
		_tabuleiroCasa[3][7] = new Casa(ret[3][7], new Rei('P', 64*3 + x, 64*7 + y, "Rei", _imgs[7]),cor[3][7]);
		
		_tabuleiroChar[4][7] = 'Q';
		_tabuleiroCasa[4][7] = new Casa(ret[4][7], new Rainha('P', 64*4 + x, 64*7 + y, "Rainha", _imgs[10]),cor[4][7]);
		
		_tabuleiroChar[5][7] = 'B';
		_tabuleiroCasa[5][7] = new Casa(ret[5][7], new Bispo('P', 64*5 + x, 64*7 + y, "Bispo", _imgs[6]),cor[5][7]);
		
		_tabuleiroChar[6][7] = 'C';
		_tabuleiroCasa[6][7] = new Casa(ret[6][7], new Cavalo('P', 64*6 + x, 64*7 + y, "Cavalo", _imgs[8]),cor[6][7]);
		
		_tabuleiroChar[7][7] = 'T';
		_tabuleiroCasa[7][7] = new Casa(ret[7][7], new Torre('P', 64*7 + x, 64*7 + y, "Torre", _imgs[11]),cor[7][7]);
		
	}
	
	public static char[][] getTabChar() {
		return _tabuleiroChar;
	}
	
	public static Casa[][] getTabCasa(){
		return _tabuleiroCasa;
	}
	
	public void movePeca(int originX, int originY, int destX, int destY) {
		Peca p = _tabuleiroCasa[originX][originY].peca;
		int newPosX = 64*destX + _offsetX;
		int newPosY = 64*destY + _offsetY;
		
		printTabuleiro();
				
		switch (p.nome) {
		case "Torre":
			_tabuleiroCasa[destX][destY].peca = new Torre(p.cor,newPosX,newPosY,p.nome, p.imagem);
			break;
		case "Cavalo":
			_tabuleiroCasa[destX][destY].peca = new Cavalo(p.cor,newPosX,newPosY,p.nome, p.imagem);
			break;
		case "Bispo":
			_tabuleiroCasa[destX][destY].peca = new Bispo(p.cor,newPosX,newPosY,p.nome, p.imagem);
			break;
		case "Rainha":
			_tabuleiroCasa[destX][destY].peca = new Rainha(p.cor,newPosX,newPosY,p.nome, p.imagem);
			break;
		case "Rei":
			_tabuleiroCasa[destX][destY].peca = new Rei(p.cor,newPosX,newPosY,p.nome, p.imagem);
			break;
			default:
				_tabuleiroCasa[destX][destY].peca = new Peao(p.cor,newPosX,newPosY,p.nome, p.imagem);
				break;
		}	

		_tabuleiroCasa[originX][originY].peca = null;
		printTabuleiro();
	}
	
	public static void printTabuleiro() {
		int current = 0;
		
		System.out.println(" ");
		System.out.println("|----------------------- ");
		
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				
				char letra;
				
				if (_tabuleiroCasa[j][i].peca != null) {
					String peca = _tabuleiroCasa[j][i].peca.nome;
					switch (peca){
					case "Torre":
						if (_tabuleiroCasa[j][i].peca.cor == 'P') {
							letra = 'T';
						} else {
							letra = 't';
						}
						break;
					case "Cavalo":
						if (_tabuleiroCasa[j][i].peca.cor == 'P') {
							letra = 'C';
						} else {
							letra = 'c';
						}
						break;
					case "Bispo":
						if (_tabuleiroCasa[j][i].peca.cor == 'P') {
							letra = 'B';
						} else {
							letra = 'b';
						}
						break;
					case "Rainha":
						if (_tabuleiroCasa[j][i].peca.cor == 'P') {
							letra = 'Q';
						} else {
							letra = 'q';
						}
						break;
					case "Rei":
						if (_tabuleiroCasa[j][i].peca.cor == 'P') {
							letra = 'K';
						} else {
							letra = 'k';
						}
					break;
						default:
							if (_tabuleiroCasa[j][i].peca.cor == 'P') {
								letra = 'P';
							} else {
								letra = 'p';
							}
							break;
					}
				} else {
					letra = 'x';
				}
				
				
				
				if (i == current) {
					System.out.print(" " + letra + " ");
				} else {
					current = i;
					System.out.println(" ");
					System.out.print(" " + letra + " ");
				}
				
			}
		}
		
	}
}
