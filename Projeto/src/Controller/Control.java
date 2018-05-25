package Controller;

import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import Interface.DrawChess;

public class Control implements MouseListener {
	
	// Pega a posi��o do mouse a calcula se est� dentro do quadrado do desenho da matriz, deixa ele cinza,
	// mostra as posi��es poss�veis da pe�a. Seleciona a posi��o desejada e move a pe�a.
	
	private DrawChess _dc = DrawChess.getInstance();
	private Rectangle2D[][] _matrix = _dc.getTabuleiro();

	@Override
	public void mouseClicked(MouseEvent e) {
		
		for (int i=0;i<_matrix[i].length;i++) {
			for (int j=0;j<_matrix[j].length;i++) {
				if (checkMatrix(_matrix[i][j], e.getX(), e.getY())) {
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
	
	// Verifica se clicou dentro do ret�ngulo
	private boolean checkMatrix(Rectangle2D ret, float x, float y) {
		
		if (x >= ret.getMinX() && x <= ret.getMaxX()) {
			if (y >= ret.getMinY() && y <= ret.getMaxY()) {
				return true;
			}
		}
		return false;
	}
	
	

}
