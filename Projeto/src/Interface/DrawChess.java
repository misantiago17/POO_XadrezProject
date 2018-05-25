package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import Peca.*;
import Controller.Control;

// fazer dessa classe um singleton

public final class DrawChess extends JPanel {
	
	private int _largura;
	private int _altura;
	private int _x;
	private int _y;
	
	private boolean _isWhite = true;
	
	// Tabuleiro sempre no centro da tela
	private float _offSetX;
	private float _offSetY;
	
	private Peca[] _pecasPretas = new Peca[16];
	private Peca[] _pecasBrancas = new Peca[16];
	
	private Rectangle2D[][] _tabuleiro = new Rectangle2D[8][8];
	
	private static DrawChess _instance;
		
	// Deixar isso privado para ser um singleton de verdade (pegar as coisas daclasse XadrezFrame?)
	public DrawChess(int largura, int altura, int x, int y){
		_largura = largura;
		_altura = altura;
		_x = x;
		_y = y;	
		
		_offSetX = (_largura - 64*8)/2;
		_offSetY = (_altura - 64*8)/2;
		
		this.addMouseListener(new Control());
	}
	
	public static DrawChess getInstance() {
		if (_instance == null) {
			_instance = new DrawChess(0,0,0,0);
		}
		return _instance;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d=(Graphics2D) g;
						
		Rectangle2D background = new Rectangle2D.Float(0, 0, _largura, _altura);
		g2d.setColor(Color.getHSBColor(0.92f, 1.0f, 0.23f));
		g2d.fill(background);
		
		createBoard(g2d,_offSetX,_offSetY);
		
		if (_pecasPretas[0] != null && _pecasBrancas[0] != null) {
			drawPecas(g2d, _pecasPretas);
			drawPecas(g2d, _pecasBrancas);
		}
	}
	
	public void atualizaPecas(Peca[] preta, Peca[] branca) {
		_pecasPretas = preta;
		_pecasBrancas = branca;
	}
	
	public Rectangle2D[][] getTabuleiro() {
		return _tabuleiro;
	}
	
	private void createBoard(Graphics2D g, float offSetX, float offSetY) {
		
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				
				Color c = (_isWhite) ? Color.WHITE : Color.BLACK;
								
				Rectangle2D ret = new Rectangle2D.Float(64*i + offSetX, 64*j + offSetY, 64, 64);
				g.setColor(c);
				g.fill(ret);
								
				_tabuleiro[i][j] = ret;
				
				_isWhite = (_isWhite) ? false : true;
			}
			_isWhite = (_isWhite) ? false : true;
		}
	}
	
	private void drawPecas(Graphics2D g, Peca[] pecas) {
		
		for (int i=0;i<pecas.length;i++) {
			g.drawImage(pecas[i].imagem, pecas[i].posX, pecas[i].posY, null);
		}
		
	}

}
