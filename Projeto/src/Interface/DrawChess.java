package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import Peca.*;
import Tabuleiro.*;
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
	
	//private Rectangle2D[][] _tabuleiro = new Rectangle2D[8][8];
	//private Tabuleiro _tabuleiroClasse = new Tabuleiro();
	private Control _ctrl;
	
	private Casa[][] _tabuleiro = new Casa[8][8];
			
	public DrawChess(int largura, int altura, int x, int y){
		_largura = largura;
		_altura = altura;
		_x = x;
		_y = y;	
		
		_offSetX = (_largura - 64*8)/2;
		_offSetY = (_altura - 64*8)/2;
		
		this.addMouseListener(Control.getInstance());
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d=(Graphics2D) g;
						
		Rectangle2D background = new Rectangle2D.Float(0, 0, _largura, _altura);
		g2d.setColor(Color.getHSBColor(0.92f, 1.0f, 0.23f));
		g2d.fill(background);
				
		if (_tabuleiro[0][0] != null) {
			repaintChess(g2d);
		} else {
			createBoard(g2d,_offSetX,_offSetY);
		}
		
		if (_pecasPretas[0] != null && _pecasBrancas[0] != null) {
			drawPecas(g2d, _pecasPretas);
			drawPecas(g2d, _pecasBrancas);
		}
				
	}
	
	public void atualizaPecas(Peca[] preta, Peca[] branca) {
		_pecasPretas = preta;
		_pecasBrancas = branca;
	}
	
	public Casa[][] getTabuleiro() {
		return _tabuleiro;
	}
	
	// Cria o tabuleiro
	private void createBoard(Graphics2D g, float offSetX, float offSetY) {
		
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				
				Color c = (_isWhite) ? Color.WHITE : Color.BLACK;
				Peca p = null;
								
				Rectangle2D ret = new Rectangle2D.Float(64*i + offSetX, 64*j + offSetY, 64, 64);
				g.setColor(c);
				g.fill(ret);
							
				Casa casa = new Casa(ret,p,c);
								
				Tabuleiro.getInstance().atualizaCasa(casa, i, j);
				
				_isWhite = (_isWhite) ? false : true;
			}
			_isWhite = (_isWhite) ? false : true;
		}
		
		_tabuleiro = Tabuleiro.getInstance().pegaTabuleiro();
	}
	
	// Adciona as peças conforme suas posições
	private void drawPecas(Graphics2D g, Peca[] pecas) {
		
		for (int i=0;i<pecas.length;i++) {
			g.drawImage(pecas[i].imagem, pecas[i].posX, pecas[i].posY, null);
		}
		
	}
	
	private void repaintChess(Graphics2D g) {
		for (int i=0;i<8;i++) {
			for (int j=0; j<8; j++) {
				g.setColor(_tabuleiro[i][j].cor);
				g.fill(_tabuleiro[i][j].retangulo);
			}
		}
	}

}
