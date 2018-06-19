package Controller;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


import Interface.*;
import Observers.*;
import Peca.*;
import Tabuleiro.*;

public class Control implements MouseListener, ObservadorTabuleiro {
	
	private static Control _instance;

	private DrawChess _dc;
	private Jogo _j;
	private Tabuleiro _t;
	
	private static Casa[][] _tabuleiro;	// O tabuleiro
	
	private boolean _selecioneiPeca = false;
	private Coordenadas _pecaSelecionada;
		
	// Casa que a peça selecionada pode se mover/atacar
	private Coordenadas[] _casasPossiveis = new Coordenadas[64];
	
	private boolean _popupAberto = false;
	private Casa _peaoSelecionado;
	
	private String _vencedor;
	
	public static boolean turnoBranco = true;
	
	public static boolean blackKingCheckMate = false;
	public static boolean whiteKingCheckMate = false;
	public static boolean blackKingCheck = false;
	public static boolean whiteKingCheck = false;
	public static boolean empate = false;
	// --------------- Public --------------------
	
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
	
	// Obtem o panel do tabuleiro
	public JPanel pegaPanel() {
		return _dc;
	}
	
	// Preenche o tabuleiro com as peças e suas casas
	public void preencheTabuleiro(int x, int y, Rectangle2D[][] ret, Color[][] cor) {
		_t.fillTabuleiro(ret, cor, x, y);	
	}
	
	// Salva jogo
	public void salvaJogo() {
		if (!_popupAberto) {
			System.out.println("SALVA");
		}
	}
	
	// Carrega jogo salvo
	public void carregaJogo() {
		System.out.println("CARREGA");
	}
	
	// mostra o popUp da promoção do peão em questão
	public void promocaoPeao(Casa tab) {
		_peaoSelecionado = tab;
		_popupAberto = true;
		_j.criaPopUp();
	}
	
	// Executa a troca do peão pela peça selecionada na promoção
	public void promovePeao(String pecaEscolhida) {
		
		Peca peao = null;
	    if (_peaoSelecionado != null) {
	    	peao = _peaoSelecionado.peca;
	    }
	    Peca pecaSelecionada;
	    
	    switch (pecaEscolhida) {
	    case "Torre":
	    	if (peao != null && peao.cor == 'P') {
	    		pecaSelecionada = new Torre(peao.cor, peao.posX, peao.posY, "Torre", Tabuleiro.imagens[11], peao.coord);
	    	} else {
	    		pecaSelecionada = new Torre(peao.cor, peao.posX, peao.posY, "Torre", Tabuleiro.imagens[5], peao.coord);
	    	}
	    	atualizaPeao(pecaSelecionada);
	    	break;
	    case "Cavalo":
	    	if (peao != null && peao.cor == 'P') {
	    		pecaSelecionada = new Cavalo(peao.cor, peao.posX, peao.posY, "Cavalo", Tabuleiro.imagens[8], peao.coord);
	    	} else {
	    		pecaSelecionada = new Cavalo(peao.cor, peao.posX, peao.posY, "Cavalo", Tabuleiro.imagens[2], peao.coord);
	    	}
	    	atualizaPeao(pecaSelecionada);
	    	break;
	    case "Bispo":
	    	if (peao != null && peao.cor == 'P') {
	    		pecaSelecionada = new Bispo(peao.cor, peao.posX, peao.posY, "Bispo", Tabuleiro.imagens[6], peao.coord);
	    	} else {
	    		pecaSelecionada = new Bispo(peao.cor, peao.posX, peao.posY, "Bispo", Tabuleiro.imagens[0], peao.coord);
	    	}
	    	atualizaPeao(pecaSelecionada);
	    	break;
	    case "Rainha":
	    	if (peao != null && peao.cor == 'P') {
	    		pecaSelecionada = new Rainha(peao.cor, peao.posX, peao.posY, "Rainha", Tabuleiro.imagens[10], peao.coord);
	    	} else {
	    		pecaSelecionada = new Rainha(peao.cor, peao.posX, peao.posY, "Rainha", Tabuleiro.imagens[4], peao.coord);
	    	}
	    	atualizaPeao(pecaSelecionada);
    		break;
    		
	    	default:
	    		break;
	    }
	    testaCheck(peao.cor, false);
		
	}
	
	// Mostra atela de vitória
	public void Vitoria(String vencedor) {
		_vencedor = vencedor;
		_j.exibeVitoria();
	}
	
	// pega o nome do vencedor do jogo
	public String pegaVencedor() {
		return _vencedor;
	}
	
	// --------------- Private --------------------
	
	// Atualiza no tabuleiro para a peça que promoveu o peão
	private void atualizaPeao(Peca pecaSelecionada) {
		
		_tabuleiro[_peaoSelecionado.peca.coord.x][_peaoSelecionado.peca.coord.y].peca = pecaSelecionada;
	    _t.atualizaTabCasa(_tabuleiro);
	    repaintTable();
	    
	    Jogo.getInstance().fechaPopUp();
	    _popupAberto = false;
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
	
	// Tira o highlight das casas de movimenção e ataque de uma peça
	private void tiraCor(int i, int j) {
		
		int p = 0;
		while (_casasPossiveis[p] != null) {
			if (i == -1 && j == -1) {
				_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].cor = _tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].corOriginal;
			} else {
				_tabuleiro[i][j].cor = _tabuleiro[i][j].corOriginal;
			}
			_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].cor = _tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].corOriginal;
			if (_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].atcPossivel) {
				_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].atcPossivel = false;
			} else if (_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].movPossivel) {
				_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].movPossivel = false;
			} 
			_casasPossiveis[p] = null;
			p += 1;
		}
		_pecaSelecionada = null;
	}
	
	// Action events do mouse
	@Override
	public void mouseClicked(MouseEvent e) {
		
		// Pode mover apenas se o popUp não está aberto
		if (!_popupAberto) {
			if(SwingUtilities.isLeftMouseButton(e))
				leftClick(e);
			else if(SwingUtilities.isRightMouseButton(e)){
				save();
			}
		}
	}
	private void load() {
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text document", "txt");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
	    }
	}
	private void save() {
		JFrame parentFrame = new JFrame();
		 
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");   
		 
		int userSelection = fileChooser.showSaveDialog(parentFrame);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		  //  try (BufferedWriter writer = Files.newBufferedWriter(fileToSave.getAbsolutePath())) {
		     //   writer.write(s, 0, s.length());
		   // } catch (IOException x) {
		   //     System.err.format("IOException: %s%n", x);
		   // }
		}
	}
	public static void testaCheck(char cor, boolean roque) {
		char testaCor;
		if(cor == 'P')
			testaCor = 'B';
		else
			testaCor = 'P';
		if(Peca.verificaCheck(_tabuleiro, testaCor) && !roque) {
			System.out.println("Teste1");
			if(!Peca.verificaExisteMovPossiveis(testaCor)) {
				if(testaCor == 'P')
					blackKingCheckMate = true;
				else
					whiteKingCheckMate = true;
				System.out.println("teste2  " + testaCor);
			}
			else {
				if(testaCor == 'P')
					blackKingCheck = true;
				else
					whiteKingCheck = true;
				System.out.println("teste3");
			}
		}
		else if(!Peca.verificaExisteMovPossiveis(testaCor) && !roque) {
			empate = true;
			System.out.println("teste4");
		}
		else {
			blackKingCheck = false;
			whiteKingCheck = false;
			System.out.println("teste5");
					
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
	
	private void leftClick(MouseEvent e) {
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				
				// Verifica se clicou em alguma casa do tabuleiro
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
										
									_casasPossiveis = _tabuleiro[i][j].peca.getMovPossiveis(i,j);
										
									int p = 0;
									while (_casasPossiveis[p] != null) {
										if (_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].atcPossivel) {
											_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].cor = Color.RED;
										} else if (_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].movPossivel) {
											_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].cor = Color.GREEN;
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
									
									_casasPossiveis = _tabuleiro[i][j].peca.getMovPossiveis(i,j);
										
									int p = 0;
										
									while (_casasPossiveis[p] != null) {
										if (_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].atcPossivel) {
											_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].cor = Color.RED;
										} else if (_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].movPossivel) {
											_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].cor = Color.GREEN;
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
					} else {
						
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
	// Observador do tabuleiro
	@Override
	public void notify(ObservadoTabuleiro o) {
		_tabuleiro = o.get();
	}
}
