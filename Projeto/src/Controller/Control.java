package Controller;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

import Interface.DrawChess;
import Interface.XadrezFrame;
import Peca.Peca;
import Tabuleiro.*;

public class Control implements MouseListener {
	
	// Pega a posição do mouse a calcula se está dentro do quadrado do desenho da matriz, deixa ele cinza,
	// mostra as posições possíveis da peça. Seleciona a posição desejada e move a peça.
	
	// Observer, quando mudar o tabuleiro troca ele na classe tabuleiro tbm
	
	private XadrezFrame _xf;
	private DrawChess _dc;
	private Tabuleiro _tb = new Tabuleiro();
	
	private Casa[][] _matrix;
	
	private static Control _instance;
	
	private Control() {

	}
	
	public static Control getInstance() {
		if (_instance == null) {
			_instance = new Control();
		}
		return _instance;
	}
	
	// Adiciona o tabuleiro desenhado ao frame
	public JPanel addChess(int alt, int larg, int x, int y) {
		_dc = new DrawChess(larg,alt,x,y);
		return _dc;
	}
	
	public void atualizaPecas(Peca[] preta, Peca[] branca) {
		_dc.atualizaPecas(preta, branca);
		_dc.repaint();
	}
	
	// Verifica se clicou dentro do retângulo
	private boolean checkMatrix(Casa matrix, float x, float y) {
				
		if (x >= matrix.retangulo.getMinX() && x <= matrix.retangulo.getMaxX()) {
			if (y >= matrix.retangulo.getMinY() && y <= matrix.retangulo.getMaxY()) {
				return true;
			}
		}
		return false;		
	}

	
	// Action events do mouse
	@Override
	public void mouseClicked(MouseEvent e) {
		_matrix = _dc.getTabuleiro();
		
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				if (checkMatrix(_matrix[i][j], e.getX(), e.getY())) {
					
					// pINTA ESSE RETANGULO DE CINZA
					// Na real verifica se tem uma peça ali e pinta
					
					// clica na casa verifica se tem peca, caso sim, chama movspossiveis, retorna matriz de char se 
					// se v é valido, se a é ataque, atualizar matriz real com os boolean e manda pintar
					
					_matrix[i][j].cor = Color.LIGHT_GRAY;
										
					System.out.print("Cliquei");
				}
			}
		}		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
