package Controller;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import Interface.*;
import Interface.Menu;
import Observers.*;
import Peca.*;
import Tabuleiro.*;

public class Control implements MouseListener, ObservadorTabuleiro {

	private static Control _instance;

	private DrawChess _dc;
	private Jogo _j;
	private Tabuleiro _t;

	private static Casa[][] _tabuleiro; // O tabuleiro

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

	public static void resetaJogo() {
		turnoBranco = true;

		blackKingCheckMate = false;
		whiteKingCheckMate = false;
		blackKingCheck = false;
		whiteKingCheck = false;
		empate = false;
	}

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

	// reinicia o menu inicial
	public void iniciaMenuInicial() {
		Menu.getInstance().cria();
	}

	// adiciona observadores ao tabuleiro
	public void registra(ObservadorTabuleiro o) {
		_t.add(o);
	}

	// Adiciona o tabuleiro desenhado ao frame
	public JPanel addChess(int alt, int larg, Jogo j) {
		_j = j;
		_dc = new DrawChess(larg, alt);
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
		Tabuleiro.load();
		Menu.getInstance().iniciaJogo();
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
				pecaSelecionada = new Cavalo(peao.cor, peao.posX, peao.posY, "Cavalo", Tabuleiro.imagens[8],
						peao.coord);
			} else {
				pecaSelecionada = new Cavalo(peao.cor, peao.posX, peao.posY, "Cavalo", Tabuleiro.imagens[2],
						peao.coord);
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
				pecaSelecionada = new Rainha(peao.cor, peao.posX, peao.posY, "Rainha", Tabuleiro.imagens[10],
						peao.coord);
			} else {
				pecaSelecionada = new Rainha(peao.cor, peao.posX, peao.posY, "Rainha", Tabuleiro.imagens[4],
						peao.coord);
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
		verificaVitoria();
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
			if (SwingUtilities.isLeftMouseButton(e))
				leftClick(e);
			else if (SwingUtilities.isRightMouseButton(e)) {
				Tabuleiro.save();
			}
		}
	}

	public static void testaCheck(char cor, boolean roque) {
		char testaCor;
		if (cor == 'P')
			testaCor = 'B';
		else
			testaCor = 'P';
		if (Peca.verificaCheck(_tabuleiro, testaCor) && !roque) {
			if (!Peca.verificaExisteMovPossiveis(testaCor)) {
				if (testaCor == 'P')
					blackKingCheckMate = true;
				else
					whiteKingCheckMate = true;
			} else {
				if (testaCor == 'P')
					blackKingCheck = true;
				else
					whiteKingCheck = true;
			}
		} else if (!Peca.verificaExisteMovPossiveis(testaCor) && !roque) {
			empate = true;
		} else {
			blackKingCheck = false;
			whiteKingCheck = false;

		}
	}

	// Ações do MouseListener
	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	private void leftClick(MouseEvent e) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				// Verifica se clicou em alguma casa do tabuleiro
				if (checkMatrix(_tabuleiro[i][j], e.getX(), e.getY())) {

					// Verifica se há uma peça na casa clicada
					if (_tabuleiro[i][j].peca != null) {

						if ((turnoBranco && _tabuleiro[i][j].peca.cor == 'B')
								|| (turnoBranco == false && _tabuleiro[i][j].peca.cor == 'P')) {
							// Verifica se está movendo/atacando ou está selecionando uma peça
							if (_selecioneiPeca) {

								if (_tabuleiro[i][j].peca.selecionada) { // Verifica se a peça já está selecionada
									_pecaSelecionada = null;
									_tabuleiro[i][j].cor = _tabuleiro[i][j].corOriginal;

									_selecioneiPeca = false;
									_tabuleiro[i][j].peca.selecionada = false;

									tiraCor(-1, -1);

									repaintTable();
									verificaVitoria();
									break;
								} 
								else if(_tabuleiro[i][j].roquePossivel) {

									_t.movePeca(_pecaSelecionada.x, _pecaSelecionada.y,i,j);
									tiraCor(_pecaSelecionada.x,_pecaSelecionada.y);
									
									_pecaSelecionada = null;
									_tabuleiro[i][j].cor = _tabuleiro[i][j].corOriginal;
	
									_selecioneiPeca = false;
									_tabuleiro[i][j].roquePossivel = false;

									repaintTable();
									verificaVitoria();
									break;
								}
								else if (!_tabuleiro[i][j].peca.selecionada) { // Clicou numa peça que não estava
																					// selecionada
									_tabuleiro[_pecaSelecionada.x][_pecaSelecionada.y].peca.selecionada = false;
									_tabuleiro[_pecaSelecionada.x][_pecaSelecionada.y].cor = _tabuleiro[_pecaSelecionada.x][_pecaSelecionada.y].corOriginal;

									tiraCor(-1, -1);

									_pecaSelecionada = new Coordenadas(i, j);
									_tabuleiro[i][j].peca.selecionada = true;

									_tabuleiro[i][j].cor = Color.BLUE;

									_casasPossiveis = _tabuleiro[i][j].peca.getMovPossiveis(i, j);

									int p = 0;
									while (_casasPossiveis[p] != null) {
										if (_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].atcPossivel) {
											_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].cor = Color.RED;
										} else if (_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].movPossivel) {
											_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].cor = Color.GREEN;
										} else if (_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].roquePossivel) {
											_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].cor = Color.YELLOW;
										}
										p += 1;
									}

									repaintTable();
									verificaVitoria();
									break;
								}

							} else {

								if (_tabuleiro[i][j].peca.selecionada == false)
									_selecioneiPeca = true;
								_pecaSelecionada = new Coordenadas(i, j);
								_tabuleiro[i][j].peca.selecionada = true;
								_tabuleiro[i][j].cor = Color.BLUE;

								_casasPossiveis = _tabuleiro[i][j].peca.getMovPossiveis(i, j);

								int p = 0;

								while (_casasPossiveis[p] != null) {
									if (_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].atcPossivel) {
										_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].cor = Color.RED;
									} else if (_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].movPossivel) {
										_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].cor = Color.GREEN;
									}
									else if (_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].roquePossivel) {
										_tabuleiro[_casasPossiveis[p].x][_casasPossiveis[p].y].cor = Color.YELLOW;
									}
									p += 1;
								}

								repaintTable();
								verificaVitoria();
								break;
							}

						} else {
							// Verifica se o local escolhido é válido para ataque
							if (_selecioneiPeca) {
								if (_tabuleiro[i][j].atcPossivel) {

									_selecioneiPeca = false;
									_tabuleiro[i][j].peca.selecionada = false;

									_t.atacaPeca(_pecaSelecionada.x, _pecaSelecionada.y, i, j);
									tiraCor(_pecaSelecionada.x, _pecaSelecionada.y);

									repaintTable();
									verificaVitoria();
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

								_t.movePeca(_pecaSelecionada.x, _pecaSelecionada.y, i, j);

								tiraCor(_pecaSelecionada.x, _pecaSelecionada.y);

								repaintTable();
								verificaVitoria();
								break;

							} else { // Deseleciona peça
								_tabuleiro[_pecaSelecionada.x][_pecaSelecionada.y].peca.selecionada = false;
								_tabuleiro[_pecaSelecionada.x][_pecaSelecionada.y].cor = _tabuleiro[_pecaSelecionada.x][_pecaSelecionada.y].corOriginal;

								_pecaSelecionada = null;
								_selecioneiPeca = false;

								tiraCor(-1, -1);

								repaintTable();

								verificaVitoria();
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

	private void verificaVitoria() {
		if (Control.whiteKingCheckMate) {
			Control.getInstance().Vitoria("preto");
		} else if (Control.empate) {
			Control.getInstance().Vitoria("");
		} else if (Control.blackKingCheckMate) {
			Control.getInstance().Vitoria("branco");
		}
	}
}
