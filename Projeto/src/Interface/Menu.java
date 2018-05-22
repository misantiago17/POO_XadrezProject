package Interface;

import javax.swing.*;
import java.awt.Color;

//Primeiro menu do jogo
public class Menu extends Interface {
	
	private final int LARGURA = 400;
	private final int ALTURA = 400;
	private final String NOME = "MENU";
	
	public void cria() {
		JFrame f = criaJanela(ALTURA,LARGURA,NOME);
		JPanel p = criaPainel(Color.WHITE,f);
		criaBotao("Jogar", p);
	}

}
