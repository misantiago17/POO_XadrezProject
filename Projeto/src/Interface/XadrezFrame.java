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
	
	private Peca[] PecasPretas = new Peca[16];
	private Peca[] PecasBrancas = new Peca[16];
	
	public void cria() {
		
		JFrame fXadrez = criaJanela(ALTURA,LARGURA,NOME);
		
		int[] center = pegaMeioMonitor(LARGURA,ALTURA); // 0 -> x, 1 -> y
		desenhaXadrez = new DrawChess(LARGURA,ALTURA,center[0],center[1],this);
		fXadrez.getContentPane().add(desenhaXadrez);
		
		carregaImagem();
		criaPecas();
		
		desenhaXadrez.drawPecas(PecasPretas);
		desenhaXadrez.drawPecas(PecasBrancas);
		
		
		
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
				}
			}		
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	
	private void criaPecas() {
		
		for (int i=0;i<imagens.length;i++) {
			
			for (int p=0;p<imagens.length;p++) {
				System.out.println(imagens[p].toString());
			}
			
			// Peça preta
			if (imagens[i].toString().contains("Purple")) {
				verificaPeca(imagens[i], 'P', 0, PecasPretas);
			} else { // Peça branca
				verificaPeca(imagens[i], 'B', 7, PecasBrancas);
			}
			
		}
	}
	
	// posY verifica se a peça vai ficar em começar do tabuleiro ou em baixo
	private void verificaPeca(Image img, char cor, int posY, Peca[] ordemPecas) {
		
		String tipo = img.toString().substring(img.toString().length() -1);
		int[] pos = pegaPrimeiraCasa();
		int primCasaX = pos[0];
		int primCasaY= pos[1];
		
		switch (tipo) {
		case "B": // Bispo
			ordemPecas[2] = new Bispo(cor, img, primCasaX + 64*2, primCasaY + 64*posY);
			ordemPecas[5] = new Bispo(cor, img, primCasaX + 64*5, primCasaY + 64*posY);
			break;
		case "K": // Rei
			ordemPecas[3] = new Rei(cor, img, primCasaX + 64*3, primCasaY + 64*posY);
			break;
		case "N": // Cavalo
			ordemPecas[1] = new Cavalo(cor, img, primCasaX + 64*1, primCasaY + 64*posY);
			ordemPecas[6] = new Cavalo(cor, img, primCasaX + 64*6, primCasaY + 64*posY);
			break;
		case "Q": // Rainha
			ordemPecas[4] = new Rainha(cor, img, primCasaX + 64*4, primCasaY + 64*posY);
			break;
		case "R": // Torre
			ordemPecas[0] = new Torre(cor, img, primCasaX, primCasaY*posY);
			ordemPecas[7] = new Torre(cor, img, primCasaX + 64*7, primCasaY + 64*posY);
			break;
			default: // Peao
				
				// -1 ou +1
				int local = (cor == 'P') ? 1 : -1;
				
				for (int i=0;i<8;i++) {
					ordemPecas[i+8] = new Peao(cor, img, primCasaX + 64*i, primCasaY + 64*(posY + local));
				}
		}
	
	}
	
	private int[] pegaPrimeiraCasa() {
		int[] valores = new int[2];
		
		valores[0] = desenhaXadrez.primCasaX;
		valores[1] = desenhaXadrez.primCasaY;
		
		return valores;
	}
}
