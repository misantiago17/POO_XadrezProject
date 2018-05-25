package Controller;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

import Interface.DrawChess;
import Interface.XadrezFrame;
import Peca.Peca;

public class Control implements MouseListener {
	
	// Pega a posição do mouse a calcula se está dentro do quadrado do desenho da matriz, deixa ele cinza,
	// mostra as posições possíveis da peça. Seleciona a posição desejada e move a peça.
	
	private XadrezFrame _xf;
	private DrawChess _dc;
	
	private Rectangle2D[][] _matrix;
	
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
		_matrix = _dc.getTabuleiro();
		return _dc;
	}
	
	public void atualizaPecas(Peca[] preta, Peca[] branca) {
		_dc.atualizaPecas(preta, branca);
		_dc.repaint();
	}
	
	
	// Verifica se clicou dentro do retângulo
	private boolean checkMatrix(Rectangle2D ret, float x, float y) {
		
		if (x >= ret.getMinX() && x <= ret.getMaxX()) {
			if (y >= ret.getMinY() && y <= ret.getMaxY()) {
				return true;
			}
		}
		return false;		
	}

	
	// Action events do mouse
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				if (checkMatrix(_matrix[i][j], e.getX(), e.getY())) {
					
					// pINTA ESSE RETANGULO DE CINZA
					// Na real verifica se tem uma peça ali e pinta
					
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
