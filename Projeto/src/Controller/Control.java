package Controller;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import Interface.DrawChess;
import Interface.XadrezFrame;
import Peca.Peca;
import Tabuleiro.*;

public class Control implements MouseListener {
	
	// Observer, quando mudar o tabuleiro troca ele na classe tabuleiro tbm
	
	private DrawChess _dc;
	private Tabuleiro _tb;
	
	private Casa[][] _matrix;
	
	private boolean _selecioneiPeca = false;
	private Coordenadas _pecaSelecionada;
	
	private static Control _instance;
	
	private Coordenadas[] casasPos = new Coordenadas[64];
	
	private Control() {

	}
	
	public static Control getInstance() {
		if (_instance == null) {
			_instance = new Control();
		}
		return _instance;
	}
	
	// Adiciona o tabuleiro desenhado ao frame
	public JPanel addChess(int alt, int larg) {
		_dc = new DrawChess(larg,alt);
		return _dc;
	}
	
	// Verifica se clicou dentro de uma casa
	private boolean checkMatrix(Casa matrix, float x, float y) {			
		if (x >= matrix.retangulo.getMinX() && x <= matrix.retangulo.getMaxX()) {
			if (y >= matrix.retangulo.getMinY() && y <= matrix.retangulo.getMaxY()) {
				return true;
			}
		}
		return false;		
	}

	
	// Action events do mouse
	@Override
	public void mouseClicked(MouseEvent e) {
		_matrix = Tabuleiro.getTabCasa();
		
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				if (checkMatrix(_matrix[i][j], e.getX(), e.getY())) {
					
					// Verifica se h� uma pe�a na casa clicada
					if (_matrix[i][j].peca != null) {
						
						// Verifica se est� movendo/atacando ou est� selecionando uma pe�a
						if (_selecioneiPeca) {
							
							// Verifica se o local escolhido � v�lido para ataque
							if (_matrix[i][j].atcPossivel) {
								System.out.println("Ataquei");
								break;
						
							} else if (_matrix[i][j].peca.selecionada) { // Verifica se a pe�a j� est� selecionada
								_selecioneiPeca = false;
								_pecaSelecionada = null;
								_matrix[i][j].peca.selecionada = false;
								_matrix[i][j].cor = _matrix[i][j].corOriginal;
								
								int p = 0;
								while (casasPos[p] != null) {
									_matrix[casasPos[p].x][casasPos[p].y].cor = _matrix[casasPos[p].x][casasPos[p].y].corOriginal;
									if (_matrix[casasPos[p].x][casasPos[p].y].atcPossivel) {
										_matrix[casasPos[p].x][casasPos[p].y].atcPossivel = false;
									} else if (_matrix[casasPos[p].x][casasPos[p].y].movPossivel) {
										_matrix[casasPos[p].x][casasPos[p].y].movPossivel = false;
									}
									casasPos[p] = null;
									p += 1;
								}
								
								_dc.repaint();
								break;
							}
							
						} else {
								
							if (_matrix[i][j].peca.selecionada == false)
								_selecioneiPeca = true;
								_pecaSelecionada = new Coordenadas(i,j);
								_matrix[i][j].peca.selecionada = true;
								_matrix[i][j].cor = Color.BLUE;
								
								casasPos = _matrix[i][j].peca.getMovPossiveis(i,j);
								
								int p = 0;
								
								while (casasPos[p] != null) {
									if (_matrix[casasPos[p].x][casasPos[p].y].atcPossivel) {
										_matrix[casasPos[p].x][casasPos[p].y].cor = Color.RED;
									} else if (_matrix[casasPos[p].x][casasPos[p].y].movPossivel) {

										System.out.println(casasPos[p].x);
										_matrix[casasPos[p].x][casasPos[p].y].cor = Color.YELLOW;
									}
									p += 1;
								}
								
								_dc.repaint();
								break;
							}
						} else {
							
							// Se n�o h� pe�as no lugar e h� uma pe�a selecionada
							if (_selecioneiPeca) {
								if (_matrix[i][j].movPossivel) {
									System.out.println("Movi");
									_selecioneiPeca = false;
									_matrix[_pecaSelecionada.x][_pecaSelecionada.y].peca.selecionada = false;
									
									Tabuleiro.currentTable.movePeca (_pecaSelecionada.x, _pecaSelecionada.y, i, j);
									
									int p = 0;
									while (casasPos[p] != null) {
										_matrix[_pecaSelecionada.x][_pecaSelecionada.y].cor = _matrix[_pecaSelecionada.x][_pecaSelecionada.y].corOriginal;
										_matrix[casasPos[p].x][casasPos[p].y].cor = _matrix[casasPos[p].x][casasPos[p].y].corOriginal;
										if (_matrix[casasPos[p].x][casasPos[p].y].atcPossivel) {
											_matrix[casasPos[p].x][casasPos[p].y].atcPossivel = false;
										} else if (_matrix[casasPos[p].x][casasPos[p].y].movPossivel) {
											_matrix[casasPos[p].x][casasPos[p].y].movPossivel = false;
										} 
										casasPos[p] = null;
										p += 1;
									}
									_pecaSelecionada = null;
									
																	
									_dc.repaint();
									break;
								}
							}
						} 
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
