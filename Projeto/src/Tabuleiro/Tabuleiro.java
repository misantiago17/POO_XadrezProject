package Tabuleiro;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import com.sun.prism.Image;
import Controller.Control;
import Interface.DrawChess;
import Peca.*;

public final class Tabuleiro {
	
	private static Tabuleiro INSTANCE;
	
	//
	private static Casa _tabuleiroCasa[][] = new Casa[8][8];
	private static char _tabuleiroChar[][] = new char[8][8]; // 
	
	public static Tabuleiro currentTable;
	
	public Tabuleiro(Rectangle2D[][] ret, Color[][] cor, int x, int y) {
		
		currentTable = this;
		
		_tabuleiroChar[0][0] = 't';
		_tabuleiroCasa[0][0] = new Casa(ret[0][0], new Torre('B', 64*0 + x, 64*0 + y),cor[0][0]);
		
		_tabuleiroChar[1][0] = 'c';
		_tabuleiroCasa[1][0] = new Casa(ret[1][0], new Cavalo('B', 64*1 + x, 64*0 + y),cor[1][0]);
		
		_tabuleiroChar[2][0] = 'b';
		_tabuleiroCasa[2][0] = new Casa(ret[2][0], new Bispo('B', 64*2 + x, 64*0 + y),cor[2][0]);
		
		_tabuleiroChar[3][0] = 'k';
		_tabuleiroCasa[3][0] = new Casa(ret[3][0], new Rei('B', 64*3 + x, 64*0 + y),cor[3][0]);
		
		_tabuleiroChar[4][0] = 'q';
		_tabuleiroCasa[4][0] = new Casa(ret[4][0], new Rainha('B', 64*4 + x, 64*0 + y),cor[4][0]);
		
		_tabuleiroChar[5][0] = 'b';
		_tabuleiroCasa[5][0] = new Casa(ret[5][0], new Bispo('B', 64*5 + x, 64*0 + y),cor[5][0]);
		
		_tabuleiroChar[6][0] = 'c';
		_tabuleiroCasa[6][0] = new Casa(ret[6][0], new Cavalo('B', 64*6 + x, 64*0 + y),cor[6][0]);
		
		_tabuleiroChar[7][0] = 't';
		_tabuleiroCasa[7][0] = new Casa(ret[7][0], new Torre('B', 64*7 + x, 64*0 + y),cor[7][0]);
		
		for(int i = 0; i < 8; i++) {
			_tabuleiroChar[i][1] = 'p';
			_tabuleiroCasa[i][1] = new Casa(ret[i][1], new Peao('B', 64*i + x, 64*1 + y),cor[i][1]);
		}
		
		for(int i = 2; i < 6; i++) {
			for(int j = 0; j < 8; j++) {
				_tabuleiroChar[j][i] = 'x';
				_tabuleiroCasa[j][i] = new Casa(ret[j][i],null,cor[j][i]);
			}
		}
		
		for(int i = 0; i < 8; i++) {
			_tabuleiroChar[i][6] = 'P';
			_tabuleiroCasa[i][6] = new Casa(ret[i][6], new Peao('P', 64*i + x, 64*6 + y),cor[i][6]);
		}
		
		_tabuleiroChar[0][7] = 'T';
		_tabuleiroCasa[0][7] = new Casa(ret[0][7], new Torre('P', 64*0 + x, 64*7 + y),cor[0][7]);
		
		_tabuleiroChar[1][7] = 'C';
		_tabuleiroCasa[1][7] = new Casa(ret[1][7], new Cavalo('P', 64*1 + x, 64*7 + y),cor[1][7]);
		
		_tabuleiroChar[2][7] = 'B';
		_tabuleiroCasa[2][7] = new Casa(ret[2][7], new Bispo('P', 64*2 + x, 64*7 + y),cor[2][7]);
		
		_tabuleiroChar[3][7] = 'K';
		_tabuleiroCasa[3][7] = new Casa(ret[3][7], new Rei('P', 64*3 + x, 64*7 + y),cor[3][7]);
		
		_tabuleiroChar[4][7] = 'Q';
		_tabuleiroCasa[4][7] = new Casa(ret[4][7], new Rainha('P', 64*4 + x, 64*7 + y),cor[4][7]);
		
		_tabuleiroChar[5][7] = 'B';
		_tabuleiroCasa[5][7] = new Casa(ret[5][7], new Bispo('P', 64*5 + x, 64*7 + y),cor[5][7]);
		
		_tabuleiroChar[6][7] = 'C';
		_tabuleiroCasa[6][7] = new Casa(ret[6][7], new Cavalo('P', 64*6 + x, 64*7 + y),cor[6][7]);
		
		_tabuleiroChar[7][7] = 'T';
		_tabuleiroCasa[7][7] = new Casa(ret[7][7], new Torre('P', 64*7 + x, 64*7 + y),cor[7][7]);
		
	}
	
	public void anda(int posXinicial, int posYinicial, int posXfinal, int posYfinal) { // que
		
		_tabuleiroChar[posXfinal][posYfinal] = _tabuleiroChar[posXinicial][posYinicial];
		_tabuleiroChar[posXinicial][posYinicial] = 'x';
	}
	
	public static char[][] getTabChar() {
		return _tabuleiroChar;
	}
	
	public static Casa[][] getTabCasa(){
		return _tabuleiroCasa;
	}
	
	/*public void atualizaCasa(Casa casa, int i, int j) {
		_tabuleiroCasa[i][j] = casa;
	}*/
	
	
	
}
