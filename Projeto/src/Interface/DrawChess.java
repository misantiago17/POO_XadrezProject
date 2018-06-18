package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import Tabuleiro.Casa;
import Controller.Control;
import Observers.*;


public final class DrawChess extends JPanel implements ObservadorTabuleiro {
		
	private int _largura;
	private int _altura;
	private float _offSetX;
	private float _offSetY;
	
	private Control _ctrl;
	
	private Casa[][] _tabuleiro;	// O Tabuleiro
		
	private boolean _comecou = false;	// caso falso é a primeira vez criando o tabuleiro

	public DrawChess(int largura, int altura){	
		
		_largura = largura;
		_altura = altura;
		
		// Tabuleiro sempre no centro da tela
		_offSetX = (_largura - 64*8)/2;
		_offSetY = (_altura - 64*8)/2;

		_ctrl = Control.getInstance();
		_ctrl.registra(this);
		this.addMouseListener(_ctrl);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d=(Graphics2D) g;
		
		// Cria a base do tabuleiro
		Rectangle2D background = new Rectangle2D.Float(0, 0, _largura, _altura);
		g2d.setColor(Color.getHSBColor(0.92f, 1.0f, 0.23f));
		g2d.fill(background);
			
		// Se for a primeira vez criando o tabuleiro ele criar a instancia de Tabuleiro, 
		// caso contrário ele vai receber a instancia do tabuleiro
		if (_comecou) {
			repaintChess(g2d);
		} else {
			createBoard(g2d,_offSetX,_offSetY);
			_comecou = true;
		}
		
		// Desenha as peças do tabuleiro
		drawPecas(g2d);
				
		// Cria uma frase indicando qual o jogador atual da partida
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Helvetica", Font.PLAIN, 18)); 
		if (_ctrl.turnoBranco) {
			g2d.drawString("Turno do Player 1 (Branco)", (float)(_offSetX + 64*2), (float) (_offSetY + 64*8.5));
			g2d.drawString("Rei Branco em check", (float)(_offSetX + 64*6), (float) (_offSetY + 64*8.5));
		} else {
			g2d.drawString("Turno do Player 2 (Preto)", (float)(_offSetX + 64*2), (float) (_offSetY - 64*0.35));
			g2d.drawString("Rei Preto em check", (float)(_offSetX + 64*6), (float) (_offSetY - 64*0.35));
		}
		
		
	}
	
	// Cria o tabuleiro pela primeira vez
	private void createBoard(Graphics2D g, float offSetX, float offSetY) {
		
		Rectangle2D[][] _ret = new Rectangle2D[8][8]; 
		Color[][] _cor = new Color[8][8]; 
		boolean _isWhite = true;
		
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
		
		_ctrl.preencheTabuleiro((int)(_offSetX), (int)(_offSetY), _ret, _cor);
	}
	
	// Atualiza o tabuleiro conforme o clique
	private void repaintChess(Graphics2D g) {
			
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
	
	// Adiciona as peças conforme suas posições
	private void drawPecas(Graphics2D g) {
		
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				if (_tabuleiro[i][j].peca != null) {
					g.drawImage(_tabuleiro[i][j].peca.imagem, _tabuleiro[i][j].peca.posX, _tabuleiro[i][j].peca.posY, this);
				}
			}
		}
	}

	// Observando o tabuleiro
	@Override
	public void notify(ObservadoTabuleiro o) {
		_tabuleiro = o.get();	
	}
	

}
