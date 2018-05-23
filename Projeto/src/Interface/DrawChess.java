package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class DrawChess extends JPanel {
	
	private int _largura;
	private int _altura;
	private int _x;
	private int _y;
	
	private boolean _isWhite = true;
	
	public DrawChess(int largura, int altura, int x, int y) {
		_largura = largura;
		_altura = altura;
		_x = x;
		_y = y;	
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d=(Graphics2D) g;
				
		Rectangle2D background = new Rectangle2D.Float(0, 0, _largura, _altura);
		g2d.setColor(Color.getHSBColor(0.92f, 1.0f, 0.23f));
		g2d.fill(background);
		
		// Tabuleiro sempre no centro da tela
		float offSetX = (_largura - 64*8)/2;
		float offSetY = (_altura - 64*8)/2;
		
		System.out.println(offSetX);
		System.out.println(offSetY);
		
		createBoard(g2d,offSetX,offSetY);
		
		
		
		//g.fillRect(_x, _y, _largura, _altura);
		//g.col
		//this.setBackground(Color.getHSBColor(0.92f, 1.0f, 0.23f));
		//g2d.drawString("FRASE AAA", 120, 140);
		
	}
	
	private void createBoard(Graphics2D g, float offSetX, float offSetY) {
		
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				
				Color c = (_isWhite) ? Color.WHITE : Color.BLACK;
				
				Rectangle2D ret = new Rectangle2D.Float(64*i + offSetX, 64*j + offSetY, 64, 64);
				g.setColor(c);
				g.fill(ret);
				
				_isWhite = (_isWhite) ? false : true;
			}
			_isWhite = (_isWhite) ? false : true;
		}
	}
	
	private void carregaImagem() {
		Image i;
		try {
		i=ImageIO.read(new File("b_dama.gif"));
		}
		catch(IOException e) {
		System.out.println(e.getMessage());
		System.exit(1);
		}
	}

}
