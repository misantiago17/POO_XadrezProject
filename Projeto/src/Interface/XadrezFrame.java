package Interface;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;
import javax.swing.*;
import Peca.*;

// Interface do Tabuleiro e Peças
public class XadrezFrame extends Interface {
	
	private DrawChess desenhaXadrez;
	
	private final int LARGURA = 700;
	private final int ALTURA = 700;
	private final String NOME = "XADREZ";
	
	private final File pasta = new File("./resources");
	private File[] listaArquivos = pasta.listFiles();
	private Image[] imagens = new Image[12];
	private String[] nomeImagens = new String[12];
	
	private Peca[] PecasPretas = new Peca[16];
	private Peca[] PecasBrancas = new Peca[16];
	
	private int primCasaX = (int)((LARGURA - 64*8)/2);
	private int primCasaY = (int)((ALTURA - 64*8)/2);
	
	public void cria() {
		
		JFrame fXadrez = criaJanela(ALTURA,LARGURA,NOME);
				
		int[] center = pegaMeioMonitor(LARGURA,ALTURA); // 0 -> x, 1 -> y
		desenhaXadrez = new DrawChess(LARGURA,ALTURA,center[0],center[1]);
		fXadrez.getContentPane().add(desenhaXadrez);	
		
		carregaImagem();
		criaPecas();
		
		desenhaXadrez.atualizaPecas(PecasPretas, PecasBrancas);
		desenhaXadrez.repaint();
		
		
		
		// Eu tava tentando conseguir um verde escuro mas saiu essa cor e ela é bonita 
		//JPanel pXadrez = criaPainel(Color.getHSBColor(0.92f, 1.0f, 0.23f),fXadrez);
		
		//LayoutManager overlay = new OverlayLayout(pXadrez);
		//pXadrez.setLayout(overlay);
		
		//pXadrez.add(new DrawChess());

		//pXadrez.add(new DrawChess());
		//getContentPane().add(new DrawChess());

		
		//criaBotao("Jogar", pXadrez);
	}
	
	private void carregaImagem() {
		
		try {
			
			for (int i=0; i<listaArquivos.length;i++) {
				if (listaArquivos[i].isFile()) {					
					imagens[i] = ImageIO.read(listaArquivos[i]);
					nomeImagens[i] = listaArquivos[i].getName();
				}
			}		
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	
	private void criaPecas() {
		
		for (int i=0;i<nomeImagens.length;i++) {
			
			// Peça preta
			if (nomeImagens[i].contains("Purple")) {
				verificaPeca(imagens[i], nomeImagens[i], 'P', 0, PecasPretas);
			} else { // Peça branca
				verificaPeca(imagens[i], nomeImagens[i], 'B', 7, PecasBrancas);
			}
			
		}
	}
	
	// posY verifica se a peça vai ficar em começar do tabuleiro ou em baixo
	private void verificaPeca(Image img, String nome, char cor, int posY, Peca[] ordemPecas) {
		
		String tipo;
		
		if (cor == 'P') {
			tipo = nome.substring(nome.length() -5, nome.length() -4);
		} else {
			tipo = nome.substring(nome.length() -5, nome.length() -4);
		}
		
		switch (tipo) {
		case "B": // Bispo
			ordemPecas[2] = new Bispo(cor, img, primCasaX + 64*2, primCasaY + 64*posY, nome);
			ordemPecas[5] = new Bispo(cor, img, primCasaX + 64*5, primCasaY + 64*posY, nome);
			break;
		case "K": // Rei
			ordemPecas[3] = new Rei(cor, img, primCasaX + 64*3, primCasaY + 64*posY, nome);
			break;
		case "N": // Cavalo
			ordemPecas[1] = new Cavalo(cor, img, primCasaX + 64*1, primCasaY + 64*posY, nome);
			ordemPecas[6] = new Cavalo(cor, img, primCasaX + 64*6, primCasaY + 64*posY, nome);
			break;
		case "Q": // Rainha
			ordemPecas[4] = new Rainha(cor, img, primCasaX + 64*4, primCasaY + 64*posY, nome);
			break;
		case "R": // Torre
			ordemPecas[0] = new Torre(cor, img, primCasaX, primCasaY + 64*posY, nome);
			ordemPecas[7] = new Torre(cor, img, primCasaX + 64*7, primCasaY + 64*posY, nome);
			break;
			default: // Peao
				
				// -1 ou +1
				int local = (cor == 'P') ? 1 : -1;
				
				for (int i=0;i<8;i++) {
					ordemPecas[i+8] = new Peao(cor, img, primCasaX + 64*i, primCasaY + 64*(posY + local), nome);
				}
		}
	
	}
}
