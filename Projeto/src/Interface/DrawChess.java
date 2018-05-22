package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class DrawChess extends JPanel {
	
	private int _largura;
	private int _altura;
	private int _x;
	private int _y;
	
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
		
		Rectangle2D ret = new Rectangle2D.Float(0, 0, 50, 50);
		g2d.setColor(Color.getHSBColor(1.0f, 1.0f, 1.0f));
		g2d.fill(ret);
		
		
		
		//g.fillRect(_x, _y, _largura, _altura);
		//g.col
		//this.setBackground(Color.getHSBColor(0.92f, 1.0f, 0.23f));
		//g2d.drawString("FRASE AAA", 120, 140);
		
	}

}
