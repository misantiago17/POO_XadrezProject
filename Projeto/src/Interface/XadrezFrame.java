package Interface;

import java.awt.*;
import javax.swing.*;

// Interface do Tabuleiro e Peças
public class XadrezFrame extends Interface {
	
	private final int LARGURA = 700;
	private final int ALTURA = 700;
	private final String NOME = "XADREZ";
	
	public void cria() {
		
		JFrame fXadrez = criaJanela(ALTURA,LARGURA,NOME);
		
		int[] center = pegaMeioMonitor(LARGURA,ALTURA); // 0 -> x, 1 -> y
		fXadrez.getContentPane().add(new DrawChess(LARGURA,ALTURA,center[0],center[1]));

		
		
		// Eu tava tentando conseguir um verde escuro mas saiu essa cor e ela é bonita 
		//JPanel pXadrez = criaPainel(Color.getHSBColor(0.92f, 1.0f, 0.23f),fXadrez);
		
		//LayoutManager overlay = new OverlayLayout(pXadrez);
		//pXadrez.setLayout(overlay);
		
		//pXadrez.add(new DrawChess());

		//pXadrez.add(new DrawChess());
		//getContentPane().add(new DrawChess());

		
		//criaBotao("Jogar", pXadrez);
	}
	
}
