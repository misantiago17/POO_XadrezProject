package Interface;

import javax.swing.*;
import java.awt.Color;
import java.awt.Component;

//Primeiro menu do jogo
public class Menu extends Interface {
	
	private final int LARGURA = 400;
	private final int ALTURA = 400;
	private final String NOME = "MENU";
	
	public void cria() {
		JFrame f = criaJanela(ALTURA,LARGURA,NOME);
		JPanel p = criaPainel(Color.WHITE,f);
		JButton b1 = criaBotao("Jogar", p);
		JButton b2 = criaBotao("Carregar Jogo", p);
		
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
		b1.setAlignmentX(Component.CENTER_ALIGNMENT);
		b2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
	}

}
