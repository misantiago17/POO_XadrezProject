package Tabuleiro;

import com.sun.prism.Image;
import Controller.Control;
import Peca.*;

public final class Tabuleiro {
	
	private static Tabuleiro INSTANCE;
	
	private static Casa _tabuleiroCasa[][] = new Casa[8][8];
	private static char _tabuleiroChar[][] = new char[8][8];
	private static Peca _tabuleiroPeca[] = new Peca[32];
	
	public Tabuleiro() {
		
		
		
		_tabuleiroChar[0][0] = 't';
		_tabuleiroPeca[0] = new Torre('B', 0, 0);
		_tabuleiroCasa[0][0] = new Casa(_tabuleiroPeca[0]);
		
		_tabuleiroChar[1][0] = 'c';
		_tabuleiroPeca[1] = new Cavalo('B', 1, 0);
		_tabuleiroCasa[1][0] = new Casa( _tabuleiroPeca[1]);
		
		_tabuleiroChar[2][0] = 'b';
		_tabuleiroPeca[2] = new Bispo('B', 2, 0);
		_tabuleiroCasa[2][0] = new Casa(_tabuleiroPeca[2]);
		
		_tabuleiroChar[3][0] = 'k';
		_tabuleiroPeca[3] = new Rei('B', 3, 0);
		_tabuleiroCasa[3][0] = new Casa(_tabuleiroPeca[3]);
		
		_tabuleiroChar[4][0] = 'q';
		_tabuleiroPeca[4] = new Rainha('B', 4, 0);
		_tabuleiroCasa[4][0] = new Casa(_tabuleiroPeca[4]);
		
		_tabuleiroChar[5][0] = 'b';
		_tabuleiroPeca[5] = new Bispo('B', 5, 0);
		_tabuleiroCasa[5][0] = new Casa( _tabuleiroPeca[5]);
		
		_tabuleiroChar[6][0] = 'c';
		_tabuleiroPeca[6] = new Cavalo('B', 6, 0);
		_tabuleiroCasa[6][0] = new Casa(_tabuleiroPeca[6]);
		
		_tabuleiroChar[7][0] = 't';
		_tabuleiroPeca[7] = new Torre('B', 7, 0);
		_tabuleiroCasa[7][0] = new Casa(_tabuleiroPeca[7]);
		
		for(int i = 0; i < 8; i++) {
			_tabuleiroChar[i][1] = 'p';
			_tabuleiroPeca[8 + i] = new Peao('B', i, 1);
			_tabuleiroCasa[i][1] = new Casa(_tabuleiroPeca[8 + i]);
		}
		
		for(int i = 2; i < 6; i++) {
			for(int j = 0; j < 8; j++) {
				_tabuleiroChar[j][i] = 'x';
				_tabuleiroCasa[j][i] = new Casa(null);
			}
		}
		
		for(int i = 0; i < 8; i++) {
			_tabuleiroChar[i][6] = 'P';
			_tabuleiroPeca[16 + i] = new Peao('P', i, 1);
			_tabuleiroCasa[i][6] = new Casa(_tabuleiroPeca[16 + i]);
		}
		
		_tabuleiroChar[0][7] = 'T';
		_tabuleiroPeca[24] = new Torre('P', 0, 7);
		_tabuleiroCasa[0][7] = new Casa(_tabuleiroPeca[24]);
		
		_tabuleiroChar[1][7] = 'C';
		_tabuleiroPeca[25] = new Cavalo('P', 1, 7);
		_tabuleiroCasa[1][7] = new Casa(_tabuleiroPeca[25]);
		
		_tabuleiroChar[2][7] = 'B';
		_tabuleiroPeca[26] = new Bispo('P', 2, 7);
		_tabuleiroCasa[2][7] = new Casa(_tabuleiroPeca[26]);
		
		_tabuleiroChar[3][7] = 'K';
		_tabuleiroPeca[27] = new Rei('P', 3, 7);
		_tabuleiroCasa[3][7] = new Casa(_tabuleiroPeca[27]);
		
		_tabuleiroChar[4][7] = 'Q';
		_tabuleiroPeca[28] = new Rainha('P', 4, 7);
		_tabuleiroCasa[4][7] = new Casa(_tabuleiroPeca[28]);
		
		_tabuleiroChar[5][7] = 'B';
		_tabuleiroPeca[29] = new Bispo('P', 5, 7);
		_tabuleiroCasa[5][7] = new Casa(_tabuleiroPeca[29]);
		
		_tabuleiroChar[6][7] = 'C';
		_tabuleiroPeca[30] = new Cavalo('P', 6, 7);
		_tabuleiroCasa[6][7] = new Casa(_tabuleiroPeca[30]);
		
		_tabuleiroChar[7][7] = 'T';
		_tabuleiroPeca[31] = new Torre('P', 7, 7);
		_tabuleiroCasa[7][7] = new Casa(_tabuleiroPeca[31]);
		
	}
	
	public static Tabuleiro getInstance() {
		if (INSTANCE == null) {
			System.out.println("nova instance");
			INSTANCE = new Tabuleiro();
		}
		return INSTANCE;
	}
	
	public void anda(int posXinicial, int posYinicial, int posXfinal, int posYfinal) {
		
		_tabuleiroChar[posXfinal][posYfinal] = _tabuleiroChar[posXinicial][posYinicial];
		_tabuleiroChar[posXinicial][posYinicial] = 'x';
	}
	
	public static char[][] getTabChar() {
		return _tabuleiroChar;
	}
	
	public static Peca[] getTabPeca() {
		return _tabuleiroPeca;
	}
	
	public static Casa[][] getTabCasa(){
		return _tabuleiroCasa;
	}
	
	public void atualizaCasa(Casa casa, int i, int j) {
		_tabuleiroCasa[i][j] = casa;
	}
	
	
	
}
