package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import Peca.*;
import Tabuleiro.*;
import Controller.Control;


public final class DrawChess extends JPanel {
	
	private int _largura;
	private int _altura;
	
	// Tabuleiro sempre no centro da tela
	private float _offSetX;
	private float _offSetY;
		
	// Usados para a criação do tabuleiro
	private Rectangle2D[][] _ret = new Rectangle2D[8][8]; 
	private Color[][] _cor = new Color[8][8]; 
	
	// O tabuleiro
	private Tabuleiro _tableClass;
	private Casa[][] _tabuleiro = new Casa[8][8];
		
	private boolean _isWhite = true;
	private static boolean _comecou = false;
	
	public DrawChess(int largura, int altura){	
		_largura = largura;
		_altura = altura;
		
		_offSetX = (_largura - 64*8)/2;
		_offSetY = (_altura - 64*8)/2;
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.addMouseListener(Control.getInstance());
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d=(Graphics2D) g;
						
		Rectangle2D background = new Rectangle2D.Float(0, 0, _largura, _altura);
		g2d.setColor(Color.getHSBColor(0.92f, 1.0f, 0.23f));
		g2d.fill(background);
			
			
		if (_comecou) {
			repaintChess(g2d); //
		} else {
			createBoard(g2d,_offSetX,_offSetY);
			_comecou = true;
		}
		
		if (_comecou) {
			drawPecas(g2d);
		}
		
		
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Helvetica", Font.PLAIN, 18)); 
		if (Control.turnoBranco) {
			g2d.drawString("Turno do Player 1 (Branco)", (float)(_offSetX + 64*5/2), (float) (_offSetY + 64*8.5));
		} else {
			g2d.drawString("Turno do Player 2 (Preto)", (float)(_offSetX + 64*5/2), (float) (_offSetY - 64*0.35));
		}
				
	}
	
	// Cria o tabuleiro pela primeira vez
	private void createBoard(Graphics2D g, float offSetX, float offSetY) {
		
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				
				Color c = (_isWhite) ? Color.WHITE : Color.BLACK;
								
				Rectangle2D ret = new Rectangle2D.Float(64*i + offSetX, 64*j + offSetY, 64, 64);
				g.setColor(c);
				g.fill(ret);
				
				_ret[i][j] = ret;
				_cor[i][j] = c;
												
				_isWhite = (_isWhite) ? false : true;
			}
			_isWhite = (_isWhite) ? false : true;
		}
		
		_tableClass = new Tabuleiro(_ret, _cor, (int)(_offSetX), (int)(_offSetY));
		_tabuleiro = _tableClass.getTabCasa();
	}
	
	// Adiciona as peças conforme suas posições
	private void drawPecas(Graphics2D g) {
		_tabuleiro = _tableClass.getTabCasa();
		
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				if (_tabuleiro[i][j].peca != null) {
					g.drawImage(_tabuleiro[i][j].peca.imagem, _tabuleiro[i][j].peca.posX, _tabuleiro[i][j].peca.posY, this);
				}
			}
		}
	}
	
	// Atualiza o tabuleiro conforme o clique
	private void repaintChess(Graphics2D g) {
		_tabuleiro = _tableClass.getTabCasa();
		
		for (int i=0;i<8;i++) {
			for (int j=0; j<8; j++) {
				g.setColor(_tabuleiro[i][j].corOriginal);
				g.fill(_tabuleiro[i][j].retangulo);
			}
		}
		
		for (int i=0;i<8;i++) {
			for (int j=0; j<8; j++) {
				if (_tabuleiro[i][j].cor != _tabuleiro[i][j].corOriginal) {
					g.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
					g.setColor(_tabuleiro[i][j].cor);
					g.draw(_tabuleiro[i][j].retangulo);
				}
			}
		}
	}

}
