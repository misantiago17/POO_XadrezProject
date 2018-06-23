package Tabuleiro;

import java.awt.Color;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

//import com.sun.prism.Image;
import Controller.Control;
import Observers.*;
import Peca.*;

public final class Tabuleiro implements ObservadoTabuleiro {
	
	// Lista de observadores do tabuleiro
	private List<ObservadorTabuleiro> lst = new ArrayList<ObservadorTabuleiro>();	
	private static Casa[][] _tabuleiroCasa = new Casa[8][8];	// O tabuleiro observado
		
	private Peca[] pecasPerdidas = new Peca[64];
	
	public static Image[] imagens;
	public static int offsetX;
	public static int offsetY;
	private static Color[][] _cor;
	private static Rectangle2D[][] _ret;
	
	public static boolean _newGame;
	
	public Tabuleiro() {}
	
	// Completa o tabuleiro com suas peças
	public void fillTabuleiro(Rectangle2D[][] ret, Color[][] cor, int x, int y) {
		
		offsetX = x;
		offsetY = y;
		imagens = carregaImagem();
		_cor = cor;
		_ret = ret;
		 // CyanB, CyanK,  CyanN,  CyanP,  CyanQ,  CyanR, 
		// PurpleB, PurpleK, PurpleN, PurpleP, PurpleQ, PurpleR 
		char[][] mat;
		if(_newGame)
			mat = inicializaMatriz();
		else
			mat = load();
		
		_tabuleiroCasa = carregaTabuleiro(mat);
		/*
		_tabuleiroCasa[0][7] = new Casa(ret[0][7], new Torre('B', 64*0 + x, 64*7 + y, "Torre", imagens[5], new Coordenadas (0,7)),cor[0][7]);
		_tabuleiroCasa[1][7] = new Casa(ret[1][7], new Cavalo('B', 64*1 + x, 64*7 + y, "Cavalo", imagens[2], new Coordenadas (1,7)),cor[1][7]);
		_tabuleiroCasa[2][7] = new Casa(ret[2][7], new Bispo('B', 64*2 + x, 64*7 + y, "Bispo", imagens[0], new Coordenadas (2,7)),cor[2][7]);
		_tabuleiroCasa[3][7] = new Casa(ret[3][7], new Rei('B', 64*3 + x, 64*7 + y, "Rei", imagens[1], new Coordenadas (3,7)),cor[3][7]);
		_tabuleiroCasa[4][7] = new Casa(ret[4][7], new Rainha('B', 64*4 + x, 64*7 + y, "Rainha", imagens[4], new Coordenadas (4,7)),cor[4][7]);
		_tabuleiroCasa[5][7] = new Casa(ret[5][7], new Bispo('B', 64*5 + x, 64*7 + y, "Bispo", imagens[0], new Coordenadas (5,7)),cor[5][7]);
		_tabuleiroCasa[6][7] = new Casa(ret[6][7], new Cavalo('B', 64*6 + x, 64*7 + y, "Cavalo", imagens[2], new Coordenadas (6,7)),cor[6][7]);
		_tabuleiroCasa[7][7] = new Casa(ret[7][7], new Torre('B', 64*7 + x, 64*7 + y, "Torre", imagens[5], new Coordenadas (7,7)),cor[7][7]);
		
		for(int i = 0; i < 8; i++) {
			_tabuleiroCasa[i][6] = new Casa(ret[i][6], new Peao('B', 64*i + x, 64*6 + y, "Peao", imagens[3], new Coordenadas (i,6)),cor[i][6]);
		}
		
		for(int i = 2; i < 6; i++) {
			for(int j = 0; j < 8; j++) {
				_tabuleiroCasa[j][i] = new Casa(ret[j][i],null,cor[j][i]);
			}
		}
		
		for(int i = 0; i < 8; i++) {
			_tabuleiroCasa[i][1] = new Casa(ret[i][1], new Peao('P', 64*i + x, 64*1 + y, "Peao", imagens[9], new Coordenadas (i,1)),cor[i][1]);
		}
		
		_tabuleiroCasa[0][0] = new Casa(ret[0][0], new Torre('P', 64*0 + x, 64*0 + y, "Torre", imagens[11], new Coordenadas (0,0)),cor[0][0]);
		_tabuleiroCasa[1][0] = new Casa(ret[1][0], new Cavalo('P', 64*1 + x, 64*0 + y, "Cavalo", imagens[8], new Coordenadas (1,0)),cor[1][0]);
		_tabuleiroCasa[2][0] = new Casa(ret[2][0], new Bispo('P', 64*2 + x, 64*0 + y, "Bispo", imagens[6], new Coordenadas (2,0)),cor[2][0]);
		_tabuleiroCasa[3][0] = new Casa(ret[3][0], new Rei('P', 64*3 + x, 64*0 + y, "Rei", imagens[7], new Coordenadas (3,0)),cor[3][0]);
		_tabuleiroCasa[4][0] = new Casa(ret[4][0], new Rainha('P', 64*4 + x, 64*0 + y, "Rainha", imagens[10], new Coordenadas (4,0)),cor[4][0]);
		_tabuleiroCasa[5][0] = new Casa(ret[5][0], new Bispo('P', 64*5 + x, 64*0 + y, "Bispo", imagens[6], new Coordenadas (5,0)),cor[5][0]);
		_tabuleiroCasa[6][0] = new Casa(ret[6][0], new Cavalo('P', 64*6 + x, 64*0 + y, "Cavalo", imagens[8], new Coordenadas (6,0)),cor[6][0]);
		_tabuleiroCasa[7][0] = new Casa(ret[7][0], new Torre('P', 64*7 + x, 64*0 + y, "Torre", imagens[11], new Coordenadas (7,0)),cor[7][0]);
		*/
		atualiza();
	}
	
	private char[][] inicializaMatriz(){
		char[][] mat = new char[8][8];
		
		mat[0][7] = mat[7][7] = 't'; 
		mat[1][7] = mat[6][7] = 'c'; 
		mat[2][7] = mat[5][7] = 'b'; 
		mat[3][7] = 'q';
		mat[4][7] = 'k';
		
		for(int i = 0; i < 8; i++) {
			mat[i][6] = 'p';
		}
		
		for(int i = 0; i < 8; i++) {
			for(int j = 2; j < 6; j++) {
				mat[i][j] = 'x';
			}
		}
		
		for(int i = 0; i < 8; i++) {
			mat[i][1] = 'P';
		}
		
		mat[0][0] = mat[7][0] = 'T'; 
		mat[1][0] = mat[6][0] = 'C'; 
		mat[2][0] = mat[5][0] = 'B'; 
		mat[3][0] = 'Q';
		mat[4][0] = 'K';
				
		return mat;
	}
	// Carrega as imagens das peças dentro do arquivo
	public static Image[] carregaImagem() {
		Image[] img = new Image[12];
		
		File pasta = new File("./resources");
		File[] listaArquivos = pasta.listFiles();
				
		try {
			for (int i=0; i<listaArquivos.length;i++) {
				if (listaArquivos[i].isFile()) {					
					img[i] = ImageIO.read(listaArquivos[i]);
				}
			}		
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return img;
	}
	
	// atualiza o tabuleiro
	public void atualizaTabCasa(Casa[][] tab){
		_tabuleiroCasa = tab;
		atualiza();		
	}

	// Move a peça até o local marcado
	public void movePeca(int originX, int originY, int destX, int destY) {
		Peca p = _tabuleiroCasa[originX][originY].peca;
		int newPosX = 64*destX + offsetX;
		int newPosY = 64*destY + offsetY;
		
		boolean roque = false;
		
		switch (p.nome) {
		case "Torre":
			Torre torre = new Torre(p.cor,newPosX,newPosY,p.nome, p.imagem, new Coordenadas (destX,destY));
			torre.hasMoved = true;
			_tabuleiroCasa[destX][destY].peca = torre;
			break;
		case "Cavalo":
			_tabuleiroCasa[destX][destY].peca = new Cavalo(p.cor,newPosX,newPosY,p.nome, p.imagem, new Coordenadas (destX,destY));
			break;
		case "Bispo":
			_tabuleiroCasa[destX][destY].peca = new Bispo(p.cor,newPosX,newPosY,p.nome, p.imagem, new Coordenadas (destX,destY));
			break;
		case "Rainha":
			_tabuleiroCasa[destX][destY].peca = new Rainha(p.cor,newPosX,newPosY,p.nome, p.imagem, new Coordenadas (destX,destY));
			break;
		case "Rei":

			Rei rei = new Rei(p.cor,newPosX,newPosY,p.nome, p.imagem, new Coordenadas (destX,destY));
			rei.hasMoved = true;
			_tabuleiroCasa[destX][destY].peca = rei;
			
			//Roque
			if(destX > originX + 1) {
				movePeca(7, originY, destX - 1, destY);
				roque = true;
			}
			else if(destX < originX - 1) {
				movePeca(0, originY, destX + 1, destY);
				roque = true;
			}
			
			break;
			default:
				Peao peao = new Peao(p.cor,newPosX,newPosY,p.nome, p.imagem, new Coordenadas (destX,destY));
				peao.hasMoved = true;
				_tabuleiroCasa[destX][destY].peca = peao;
								
				// Promoção do peao
				if(peao.cor == 'P') {
					if (destY == 7) {
						Control.getInstance().promocaoPeao(_tabuleiroCasa[destX][destY]);
					}
				} else {
					if (destY == 0) {
						Control.getInstance().promocaoPeao(_tabuleiroCasa[destX][destY]);
						Control.getInstance().repaintTable();
					}
				}
								
				break;
		}	

		_tabuleiroCasa[originX][originY].peca = null;
		
		if (Control.turnoBranco && !roque) {
			Control.turnoBranco = false;
		} else if(!Control.turnoBranco && !roque){
			Control.turnoBranco = true;
		}
		
		//Verifica  se ocorreu um check, checkmate ou empate
		Control.testaCheck(p.cor, roque);
		
		atualiza();
		printTabuleiro();
	}
	
	// Retira a peça do oponente
	public void atacaPeca(int originX, int originY, int destX, int destY) {
		int i = 0;
		
		if (pecasPerdidas[0] == null) {
			pecasPerdidas[0] = _tabuleiroCasa[destX][destY].peca;
		} else {
			while (pecasPerdidas[i] != null) {
				pecasPerdidas[i] = _tabuleiroCasa[destX][destY].peca;
				i++;
			}
		}
		
		pecasPerdidas[i] = _tabuleiroCasa[destX][destY].peca = null;
		movePeca(originX, originY, destX, destY);	
	}
	
	// Desenha o tabuleiro no console - suporte de Debug
	public void printTabuleiro() {
		int current = 0;
		
		System.out.println(" ");
		System.out.println("|----------------------- ");
		
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				
				char letra;
				
				if (_tabuleiroCasa[j][i].peca != null) {
					String peca = _tabuleiroCasa[j][i].peca.nome;
					switch (peca){
					case "Torre":
						if (_tabuleiroCasa[j][i].peca.cor == 'P') {
							letra = 'T';
						} else {
							letra = 't';
						}
						break;
					case "Cavalo":
						if (_tabuleiroCasa[j][i].peca.cor == 'P') {
							letra = 'C';
						} else {
							letra = 'c';
						}
						break;
					case "Bispo":
						if (_tabuleiroCasa[j][i].peca.cor == 'P') {
							letra = 'B';
						} else {
							letra = 'b';
						}
						break;
					case "Rainha":
						if (_tabuleiroCasa[j][i].peca.cor == 'P') {
							letra = 'Q';
						} else {
							letra = 'q';
						}
						break;
					case "Rei":
						if (_tabuleiroCasa[j][i].peca.cor == 'P') {
							letra = 'K';
						} else {
							letra = 'k';
						}
					break;
						default:
							if (_tabuleiroCasa[j][i].peca.cor == 'P') {
								letra = 'P';
							} else {
								letra = 'p';
							}
							break;
					}
				} else {
					letra = 'x';
				}
				
				
				
				if (i == current) {
					System.out.print(" " + letra + " ");
				} else {
					current = i;
					System.out.println(" ");
					System.out.print(" " + letra + " ");
				}
				
			}
		}
		
	}
	
	// Entrega o Tabuleiro aos observadores
	public void atualiza() {
		java.util.ListIterator<ObservadorTabuleiro> li = lst.listIterator();
		
		while(li.hasNext()) {

			li.next().notify(this);
		}
	}

	// Obaservador do tabuleiro
	@Override
	public void add(ObservadorTabuleiro o) {
		lst.add(o);
	}

	@Override
	public void remove(ObservadorTabuleiro o) {
		lst.remove(o);
	}

	@Override
	public Casa[][] get() {
		return _tabuleiroCasa;
	}
	
	
	public static char[][] load() {
		String path = null;
		
		JFileChooser fs = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text document", "txt");
	    fs.setFileFilter(filter);
	    int returnVal = fs.showOpenDialog(fs);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	path = fs.getSelectedFile().getAbsolutePath();
	       System.out.println("You chose to open this file: " + fs.getSelectedFile().getName());
	    }
	    else
			System.out.print("Nenhum arquivo foi escolhido");

		char[][] mat = new char[8][8];
		
	    try {
			String leitura;
			
	    	FileReader arq = new FileReader(new File(path));
	    	BufferedReader carrega = new BufferedReader(arq);
			leitura = carrega.readLine();
			if(leitura.compareTo("true") >= 0)
				Control.turnoBranco = true;
			else
				Control.turnoBranco = false;
			
			for(int i = 0; i < 8; i++) {
				System.out.println("Linha " + i);
				leitura = carrega.readLine();
				System.out.println(leitura);
				String[] partes = leitura.split("-");

				for(int j = 0, k = 0; j < 8; k++, j++) {
					if(partes[k].charAt(0) == ' ')
						k++;
					mat[j][i] = partes[k].charAt(0);
				}
			}
			
			carrega.close();
	    }
	    catch(IOException ioe){ 
	    	System.out.println("Erro ao abrir o arquivo");
	    }
	    return mat;
	}
	
	public static void save() {
		String path = null;
		
		JFileChooser fs = new JFileChooser();	
		int returnVal = fs.showSaveDialog(fs);	
		if(returnVal == JFileChooser.APPROVE_OPTION)
			{
				path = fs.getSelectedFile().getAbsolutePath();
			}
		else
			System.out.print("Nenhum arquivo foi escolhido");
		
		try {
		
				PrintWriter salva = new PrintWriter(new File(path + ".txt"));
				salva.println(Control.turnoBranco);
				salvaTabuleiro(salva);
				salva.flush();
		}
		catch(IOException ioe){ 
			System.out.println("Erro ao salvar o arquivo");
		
		}
	}
	
	private static Casa[][] carregaTabuleiro(char[][] mat) {
		Casa[][] tab = new Casa[8][8];
		Image[] imagens = carregaImagem();
		
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
								
				char peca = mat[i][j];
				switch (peca){
				case 'T':
					tab[i][j] = new Casa(_ret[i][j], new Torre('P', 64*i + offsetX, 64*j + offsetY, "Torre", imagens[11], new Coordenadas (i,j)),_cor[i][j]);
					break;
				case 't':
					tab[i][j] = new Casa(_ret[i][j], new Torre('B', 64*i + offsetX, 64*j + offsetY, "Torre", imagens[5], new Coordenadas (i,j)),_cor[i][j]);
					break;
				case 'C':
					tab[i][j] = new Casa(_ret[i][j], new Cavalo('P', 64*i + offsetX, 64*j + offsetY, "Cavalo", imagens[8], new Coordenadas (i,j)),_cor[i][j]);
					break;
				case 'c':
					tab[i][j] = new Casa(_ret[i][j], new Cavalo('B', 64*i + offsetX, 64*j + offsetY, "Cavalo", imagens[2], new Coordenadas (i,j)),_cor[i][j]);
					break;
				case 'B':
					tab[i][j] = new Casa(_ret[i][j], new Bispo('P', 64*i + offsetX, 64*j + offsetY, "Bispo", imagens[6], new Coordenadas (i,j)),_cor[i][j]);
					break;
				case 'b':
					tab[i][j] = new Casa(_ret[i][j], new Bispo('B', 64*i + offsetX, 64*j + offsetY, "Bispo", imagens[0], new Coordenadas (i,j)),_cor[i][j]);
					break;
				case 'Q':
					tab[i][j] = new Casa(_ret[i][j], new Rainha('P', 64*i + offsetX, 64*j + offsetY, "Rainha", imagens[10], new Coordenadas (i,j)),_cor[i][j]);
					break;
				case 'q':
					tab[i][j] = new Casa(_ret[i][j], new Rainha('B', 64*i + offsetX, 64*j + offsetY, "Rainha", imagens[4], new Coordenadas (i,j)),_cor[i][j]);
					break;
				case 'K':
					tab[i][j] = new Casa(_ret[i][j], new Rei('P', 64*i + offsetX, 64*j + offsetY, "Rei", imagens[7], new Coordenadas (i,j)),_cor[i][j]);
					break;
				case 'k':
					tab[i][j] = new Casa(_ret[i][j], new Rei('B', 64*i + offsetX, 64*j + offsetY, "Rei", imagens[1], new Coordenadas (i,j)),_cor[i][j]);
					break;
				case 'P':
					tab[i][j] = new Casa(_ret[i][j], new Peao('P', 64*i + offsetX, 64*j + offsetY, "Peao", imagens[9], new Coordenadas (i,j)),_cor[i][j]);
					break;
				case 'p':
					tab[i][j] = new Casa(_ret[i][j], new Peao('B', 64*i + offsetX, 64*j + offsetY, "Peao", imagens[3], new Coordenadas (i,j)),_cor[i][j]);
					break;
				default:
					tab[i][j] = new Casa(_ret[i][j],null,_cor[i][j]);
					break;
				}
				
			}
		}
		return tab;
	}
	
	private static void salvaTabuleiro(PrintWriter salva) {			
						
			for (int i=0;i<8;i++) {
				for (int j=0;j<8;j++) {
					
					char letra;
					
					if (_tabuleiroCasa[j][i].peca != null) {
						String peca = _tabuleiroCasa[j][i].peca.nome;
						switch (peca){
						case "Torre":
							if (_tabuleiroCasa[j][i].peca.cor == 'P') {
								letra = 'T';
							} else {
								letra = 't';
							}
							break;
						case "Cavalo":
							if (_tabuleiroCasa[j][i].peca.cor == 'P') {
								letra = 'C';
							} else {
								letra = 'c';
							}
							break;
						case "Bispo":
							if (_tabuleiroCasa[j][i].peca.cor == 'P') {
								letra = 'B';
							} else {
								letra = 'b';
							}
							break;
						case "Rainha":
							if (_tabuleiroCasa[j][i].peca.cor == 'P') {
								letra = 'Q';
							} else {
								letra = 'q';
							}
							break;
						case "Rei":
							if (_tabuleiroCasa[j][i].peca.cor == 'P') {
								letra = 'K';
							} else {
								letra = 'k';
							}
						break;
							default:
								if (_tabuleiroCasa[j][i].peca.cor == 'P') {
									letra = 'P';
								} else {
									letra = 'p';
								}
								break;
						}
					} else {
						letra = 'x';
					}
					
					
					
					if (j != 7)
						salva.print(letra + "-");
					else 
						salva.println(letra);
						
				}
			}
			
		
	}
}
