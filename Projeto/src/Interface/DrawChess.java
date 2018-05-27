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
		
	// Usados para a cria��o do tabuleiro
	private Rectangle2D[][] _ret = new Rectangle2D[8][8]; 
	private Color[][] _cor = new Color[8][8]; 
	
	// O tabuleiro
	private Tabuleiro _tableClass;
	private Casa[][] _tabuleiro = new Casa[8][8];
		
	private boolean _isWhite = true;
	private static boolean comecou = false;
	
	public DrawChess(int largura, int altura){	
		_largura = largura;
		_altura = altura;
		
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
			
			
		if (comecou) {
			repaintChess(g2d); //
		} else {
			createBoard(g2d,_offSetX,_offSetY);
			comecou = true;
		}
		
		if (comecou) {
			drawPecas(g2d);
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
	
	// Adiciona as pe�as conforme suas posi��es
	private void drawPecas(Graphics2D g) {
		_tabuleiro = _tableClass.getTabCasa();
		
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				if (_tabuleiro[i][j].peca != null) {
					//System.out.println("T� DANDO ERRO AQUI");
					//Tabuleiro.currentTable.printTabuleiro()
					if (_tabuleiro[4][4].peca != null) {
						System.out.println(_tabuleiro[4][4].peca.imagem);
						System.out.println(_tabuleiro[4][4].peca.posX);
						System.out.println(_tabuleiro[4][4].peca.posY);
					}

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
				g.setColor(_tabuleiro[i][j].cor);
				g.fill(_tabuleiro[i][j].retangulo);
			}
		}
	}

}
