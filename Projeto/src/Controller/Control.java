package Controller;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import Interface.DrawChess;
import Interface.Jogo;
import Observers.*;
import Peca.Bispo;
import Peca.Cavalo;
import Peca.Peca;
import Peca.Rainha;
import Peca.Torre;
import Tabuleiro.*;

public class Control implements MouseListener, ObservadorTabuleiro {
	
	private static Control _instance;

	private DrawChess _dc;
	private Jogo _j;
	private Tabuleiro _t;
	
	private Casa[][] _tabuleiro;	// O tabuleiro
	
	// --------------------------------------------------------------------------------------------
	private boolean _selecioneiPeca = false;
	private Coordenadas _pecaSelecionada;
		
	private Coordenadas[] casasPos = new Coordenadas[64];
	
	private boolean popupAberto = false;
	private Casa peaoSelecionado;
	
	public static boolean turnoBranco = true;
	
	// --------------------------------------------------------------------------------------------

	
	private Control() {
		_t = new Tabuleiro();
		registra(this);
	}
	
	public static Control getInstance() {
		if (_instance == null) {
			_instance = new Control();
		}
		return _instance;
	}
	
	// adiciona observadores ao tabuleiro
	public void registra (ObservadorTabuleiro o) {
		_t.add(o);	
	}

	// Adiciona o tabuleiro desenhado ao frame
	public JPanel addChess(int alt, int larg, Jogo j) {
		_j = j;
		_dc = new DrawChess(larg,alt);
		return _dc;
	}
	
	// Redesenha o tabuleiro
	public void repaintTable() {
		_dc.repaint();
	}
	
	// Preenche o tabuleiro com as peças e suas casas
	public void preencheTabuleiro(int x, int y, Rectangle2D[][] ret, Color[][] cor) {
		_t.FillTabuleiro(ret, cor, x, y);	
	}
	
	// Salva jogo
	public void salvaJogo() {
		if (popupAberto) {
			System.out.println("SALVA");
		}
	}
	
	// Carrega jogo salvo
	public void carregaJogo() {
		System.out.println("CARREGA");
	}
	
	
	public void promocaoPeao(Casa tab) {
		peaoSelecionado = tab;
		popupAberto = true;
		_j.criaPopUp();
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
	
	private void tiraCor(int i, int j) {
		
		int p = 0;
		while (casasPos[p] != null) {
			if (i == -1 && j == -1) {
				_tabuleiro[casasPos[p].x][casasPos[p].y].cor = _tabuleiro[casasPos[p].x][casasPos[p].y].corOriginal;
			} else {
				_tabuleiro[i][j].cor = _tabuleiro[i][j].corOriginal;
			}
			_tabuleiro[casasPos[p].x][casasPos[p].y].cor = _tabuleiro[casasPos[p].x][casasPos[p].y].corOriginal;
			if (_tabuleiro[casasPos[p].x][casasPos[p].y].atcPossivel) {
				_tabuleiro[casasPos[p].x][casasPos[p].y].atcPossivel = false;
			} else if (_tabuleiro[casasPos[p].x][casasPos[p].y].movPossivel) {
				_tabuleiro[casasPos[p].x][casasPos[p].y].movPossivel = false;
			} 
			casasPos[p] = null;
			p += 1;
		}
		_pecaSelecionada = null;
	}
	
	private void atualizaCasa(Peca pecaSelecionada) {
		
		_tabuleiro[peaoSelecionado.peca.coord.x][peaoSelecionado.peca.coord.y].peca = pecaSelecionada;
	    _t.atualizaTabCasa(_tabuleiro);
	    repaintTable();
	    
	    Jogo.getInstance().fechaPopUp();
	    popupAberto = false;
	}
	
	public void promovePeao(String pecaEscolhida) {
		
		Peca peao = null;
	    if (peaoSelecionado != null) {
	    	peao = peaoSelecionado.peca;
	    }
	    Peca pecaSelecionada;
	    
	    switch (pecaEscolhida) {
	    case "Torre":
	    	if (peao != null && peao.cor == 'P') {
	    		pecaSelecionada = new Torre(peao.cor, peao.posX, peao.posY, "Torre", Tabuleiro.imagens[11], peao.coord);
	    	} else {
	    		pecaSelecionada = new Torre(peao.cor, peao.posX, peao.posY, "Torre", Tabuleiro.imagens[5], peao.coord);
	    	}
	    	atualizaCasa(pecaSelecionada);
	    	break;
	    case "Cavalo":
	    	if (peao != null && peao.cor == 'P') {
	    		pecaSelecionada = new Cavalo(peao.cor, peao.posX, peao.posY, "Cavalo", Tabuleiro.imagens[8], peao.coord);
	    	} else {
	    		pecaSelecionada = new Cavalo(peao.cor, peao.posX, peao.posY, "Cavalo", Tabuleiro.imagens[2], peao.coord);
	    	}
	    	atualizaCasa(pecaSelecionada);
	    	break;
	    case "Bispo":
	    	if (peao != null && peao.cor == 'P') {
	    		pecaSelecionada = new Bispo(peao.cor, peao.posX, peao.posY, "Bispo", Tabuleiro.imagens[6], peao.coord);
	    	} else {
	    		pecaSelecionada = new Bispo(peao.cor, peao.posX, peao.posY, "Bispo", Tabuleiro.imagens[0], peao.coord);
	    	}
	    	atualizaCasa(pecaSelecionada);
	    	break;
	    case "Rainha":
	    	if (peao != null && peao.cor == 'P') {
	    		pecaSelecionada = new Rainha(peao.cor, peao.posX, peao.posY, "Rainha", Tabuleiro.imagens[10], peao.coord);
	    	} else {
	    		pecaSelecionada = new Rainha(peao.cor, peao.posX, peao.posY, "Rainha", Tabuleiro.imagens[4], peao.coord);
	    	}
	    	atualizaCasa(pecaSelecionada);
    		break;
    		
	    	default:
	    		break;
	    }
		
	}
	
	
	// Action events do mouse
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (!popupAberto) {
			for (int i=0;i<8;i++) {
				for (int j=0;j<8;j++) {
					if (checkMatrix(_tabuleiro[i][j], e.getX(), e.getY())) {
						
							// Verifica se há uma peça na casa clicada
							if (_tabuleiro[i][j].peca != null) {
								
								if ((turnoBranco && _tabuleiro[i][j].peca.cor == 'B') || (turnoBranco == false && _tabuleiro[i][j].peca.cor == 'P')) {
									// Verifica se está movendo/atacando ou está selecionando uma peça
									if (_selecioneiPeca) {
										
										if (_tabuleiro[i][j].peca.selecionada) { // Verifica se a peça já está selecionada
											_pecaSelecionada = null;
											_tabuleiro[i][j].cor = _tabuleiro[i][j].corOriginal;
											
											_selecioneiPeca = false;
											_tabuleiro[i][j].peca.selecionada = false;
											
											tiraCor(-1,-1);
											
											repaintTable();
											break;
										} else if (!_tabuleiro[i][j].peca.selecionada) { // Clicou numa peça que não estava selecionada
											_tabuleiro[_pecaSelecionada.x][_pecaSelecionada.y].peca.selecionada = false;
											_tabuleiro[_pecaSelecionada.x][_pecaSelecionada.y].cor = _tabuleiro[_pecaSelecionada.x][_pecaSelecionada.y].corOriginal;
											
											tiraCor(-1,-1);
											
											_pecaSelecionada = new Coordenadas(i,j);
											_tabuleiro[i][j].peca.selecionada = true;
											
											_tabuleiro[i][j].cor = Color.BLUE;
											
											casasPos = _tabuleiro[i][j].peca.getMovPossiveis(i,j);
											
											int p = 0;
											while (casasPos[p] != null) {
												if (_tabuleiro[casasPos[p].x][casasPos[p].y].atcPossivel) {
													_tabuleiro[casasPos[p].x][casasPos[p].y].cor = Color.RED;
												} else if (_tabuleiro[casasPos[p].x][casasPos[p].y].movPossivel) {
													_tabuleiro[casasPos[p].x][casasPos[p].y].cor = Color.GREEN;
												}
												p += 1;
											}
											
											repaintTable();
											break;
										}
										
									} else {
											
										if (_tabuleiro[i][j].peca.selecionada == false)
											_selecioneiPeca = true;
											_pecaSelecionada = new Coordenadas(i,j);
											_tabuleiro[i][j].peca.selecionada = true;
											_tabuleiro[i][j].cor = Color.BLUE;
											
											
											casasPos = _tabuleiro[i][j].peca.getMovPossiveis(i,j);
											
											int p = 0;
											
											while (casasPos[p] != null) {
												if (_tabuleiro[casasPos[p].x][casasPos[p].y].atcPossivel) {
													_tabuleiro[casasPos[p].x][casasPos[p].y].cor = Color.RED;
												} else if (_tabuleiro[casasPos[p].x][casasPos[p].y].movPossivel) {
													_tabuleiro[casasPos[p].x][casasPos[p].y].cor = Color.GREEN;
												}
												p += 1;
											}
											
											repaintTable();
											break;
									}
									
								} else {
									// Verifica se o local escolhido é válido para ataque
									if (_selecioneiPeca) {
										if (_tabuleiro[i][j].atcPossivel) {
											
											_selecioneiPeca = false;
											_tabuleiro[i][j].peca.selecionada = false;
											
											_t.atacaPeca (_pecaSelecionada.x, _pecaSelecionada.y, i, j);
											tiraCor(_pecaSelecionada.x, _pecaSelecionada.y);
																											
											repaintTable();
											break;
																	
										}
									}
								}
							}  else {
							
								// Se não há peças no lugar e há uma peça selecionada
								if (_selecioneiPeca) {
									if (_tabuleiro[i][j].movPossivel) {
									
										_selecioneiPeca = false;
										_tabuleiro[_pecaSelecionada.x][_pecaSelecionada.y].peca.selecionada = false;
									
										_t.movePeca (_pecaSelecionada.x, _pecaSelecionada.y, i, j);
									
										tiraCor(_pecaSelecionada.x, _pecaSelecionada.y);
																										
										repaintTable();
										break;
										
									} else {	// Deseleciona peça
										_tabuleiro[_pecaSelecionada.x][_pecaSelecionada.y].peca.selecionada = false;
										_tabuleiro[_pecaSelecionada.x][_pecaSelecionada.y].cor = _tabuleiro[_pecaSelecionada.x][_pecaSelecionada.y].corOriginal;
										
										_pecaSelecionada = null;
										_selecioneiPeca = false;
										
										tiraCor(-1,-1);
										
										repaintTable();
										break;
									}
								}
							}
						}
					
				}
			}
		}
		
	}

	// Ações do MouseListener
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

	// Observador do tabuleiro
	@Override
	public void notify(ObservadoTabuleiro o) {
		_tabuleiro = o.get();
	}
}
