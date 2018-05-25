package Tabuleiro;

import Peca.*;

public class Tabuleiro {
	
	public Casa[][] tabuleiro;
		
	
	private static char _tabuleiroChar[][] = new char[8][8];
	private static Peca _tabuleiroPeca[] = new Peca[32];
	
	public Tabuleiro() {
		Image img;
		
		_tabuleiroChar[0][0] = 't';
		_tabuleiroPeca[0] = new Torre('B', img, 0, 0, null);
		_tabuleiroChar[1][0] = 'c';
		_tabuleiroPeca[1] = new Cavalo('B', img, 1, 0, null);
		_tabuleiroChar[2][0] = 'b';
		_tabuleiroPeca[2] = new Bispo('B', img, 2, 0, null);
		_tabuleiroChar[3][0] = 'k';
		_tabuleiroPeca[3] = new Rei('B', img, 3, 0, null);
		_tabuleiroChar[4][0] = 'q';
		_tabuleiroPeca[4] = new Rainha('B', img, 4, 0, null);
		_tabuleiroChar[5][0] = 'b';
		_tabuleiroPeca[5] = new Bispo('B', img, 5, 0, null);
		_tabuleiroChar[6][0] = 'c';
		_tabuleiroPeca[6] = new Cavalo('B', img, 6, 0, null);
		_tabuleiroChar[7][0] = 't';
		_tabuleiroPeca[7] = new Torre('B', img, 7, 0, null);
		for(int i = 0; i < 8; i++) {
			_tabuleiroChar[i][1] = 'p';
			_tabuleiroPeca[8 + i] = new Peao('B', img, i, 1, null);
		}
		
		for(int i = 2; i < 6; i++) {
			for(int j = 0; j < 8; j++)
				_tabuleiroChar[j][i] = 'x';
		}
		
		for(int i = 0; i < 8; i++) {
			_tabuleiroChar[i][6] = 'P';
			_tabuleiroPeca[16 + i] = new Peao('P', img, i, 1, null);
		}
		_tabuleiroChar[0][7] = 'T';
		_tabuleiroPeca[24] = new Torre('P', img, 0, 7, null);
		_tabuleiroChar[1][7] = 'C';
		_tabuleiroPeca[25] = new Cavalo('P', img, 1, 7, null);
		_tabuleiroChar[2][7] = 'B';
		_tabuleiroPeca[26] = new Bispo('P', img, 2, 7, null);
		_tabuleiroChar[3][7] = 'K';
		_tabuleiroPeca[27] = new Rei('P', img, 3, 7, null);
		_tabuleiroChar[4][7] = 'Q';
		_tabuleiroPeca[28] = new Rainha('P', img, 4, 7, null);
		_tabuleiroChar[5][7] = 'B';
		_tabuleiroPeca[29] = new Bispo('P', img, 5, 7, null);
		_tabuleiroChar[6][7] = 'C';
		_tabuleiroPeca[30] = new Cavalo('P', img, 6, 7, null);
		_tabuleiroChar[7][7] = 'T';
		_tabuleiroPeca[31] = new Torre('P', img, 7, 7, null);
	}
	
	public void anda(int posXinicial, int posYinicial, int posXfinal, int posYfinal) {
		
		_tabuleiroChar[posXfinal][posYfinal] = _tabuleiroChar[posXinicial][posYinicial];
		_tabuleiroChar[posXinicial][posYinicial] = 'x';
	}
	
	public char[][] getTab() {
		return _tabuleiroChar;
	}
	
	public Peca[] getTabPeca() {
		return _tabuleiroPeca;
	}
	
}
