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
	
	private static Control _instance;
	
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
	
	// -------------------------------------------------------------
	
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
					
					// Verifica se há uma peça na casa clicada
					if (_matrix[i][j].peca != null) {
						
						// Verifica se está movendo/atacando ou está selecionando uma peça
						if (_selecioneiPeca) {
							
							// Verifica se o local escolhido é válido para ataque
							if (_matrix[i][j].atcPossivel) {
								System.out.println("Ataquei");
								break;
						
							} else if (_matrix[i][j].peca.selecionada) { // Verifica se a peça já está selecionada
								_selecioneiPeca = false;
								_matrix[i][j].peca.selecionada = false;
								_matrix[i][j].cor = _matrix[i][j].corOriginal;
								
								// tira as opções de movimento do tabuleiro
								
								_dc.repaint();
								break;
							}
							
						} else {
								
							if (_matrix[i][j].peca.selecionada == false)
								_selecioneiPeca = true;
								_matrix[i][j].peca.selecionada = true;
								_matrix[i][j].cor = Color.BLUE;
								
								// marca as posicoes de  movimento possíveis
								
								_dc.repaint();
								break;
							}
						} else {
							
							// Se não há peças no lugar e há uma peça selecionada
							if (_selecioneiPeca) {
								if (_matrix[i][j].movPossivel) {
									System.out.println("Movi");
									break;
								}
							}
						
					} 
					
					/*//Se NAO tem uma peca selecionada no tabuleiro
					if(!Tabuleiro.pecaSelecionada) {
						// pINTA ESSE RETANGULO DE CINZA
						// Na real verifica se tem uma peça ali e pinta
						
						// clica na casa verifica se tem peca, caso sim, chama movspossiveis, retorna matriz de char se 
						// se v é valido, se a é ataque, atualizar matriz real com os boolean e manda pintar
						System.out.println("Cliquei " + i + " " + j);
						//System.out.println(_matrix[i][j].teste);
						
						if(_matrix[i][j].peca != null) {
							System.out.println(" numa peca");
							_matrix[i][j].peca.movsPossiveis();
							_matrix[i][j].cor = Color.LIGHT_GRAY;
							Tabuleiro.pecaSelecionada = true;
						}
						break;
					}
					//Se JA tem uma peca selecionada no tabuleiro
					else {
						
						if(_matrix[i][j].movPossivel || _matrix[i][j].atcPossivel) {
							//Tabuleiro.getInstance().anda(, , i, j);
						}
					}*/
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
