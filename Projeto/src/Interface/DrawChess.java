package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import Peca.*;

public class DrawChess extends JPanel {
	
	private int _largura;
	private int _altura;
	private int _x;
	private int _y;
	
	private boolean _isWhite = true;
	
	// Tabuleiro sempre no centro da tela
	private float _offSetX = (int)((_largura - 64*8)/2);;
	private float _offSetY = (int)((_altura - 64*8)/2);;
	
	private Peca[] _pecasPretas;
	private Peca[] _pecasBrancas;
		
	
	public DrawChess(int largura, int altura, int x, int y/*,int offSetX,int offSetY*/, Peca[] pretas, Peca[] brancas){
		_largura = largura;
		_altura = altura;
		_x = x;
		_y = y;	
		//_offSetX = offSetX;
		//_offSetY = offSetY;
		_pecasPretas = pretas;
		_pecasBrancas = brancas;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d=(Graphics2D) g;
		
		System.out.println("kd");
				
		Rectangle2D background = new Rectangle2D.Float(0, 0, _largura, _altura);
		g2d.setColor(Color.getHSBColor(0.92f, 1.0f, 0.23f));
		g2d.fill(background);
		
		createBoard(g2d,_offSetX,_offSetY);
		//drawPecas(g2d, _pecasPretas);
		//drawPecas(g2d, _pecasBrancas);
	}
	
	public void drawPecas(Graphics2D g, Peca[] pecas) {
		
		for (int i=0;i<pecas.length;i++) {
			System.out.println(pecas[i].nome);
			g.drawImage(pecas[i].imagem, 0, 0, null);
		}
		
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

}
