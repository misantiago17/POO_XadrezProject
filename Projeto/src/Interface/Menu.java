package Interface;

import javax.swing.*;
import javax.swing.event.PopupMenuListener;

import com.sun.glass.events.WindowEvent;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Primeiro menu do jogo
public class Menu extends Interface implements ActionListener {
	
	private final int LARGURA = 400;
	private final int ALTURA = 400;
	private final String NOME = "MENU";
	
	private JFrame _f;
	
	public void cria() {
		_f = criaJanela(ALTURA,LARGURA,NOME);
		JPanel p = criaPainel(Color.WHITE,_f);
		
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
		
		JButton b1 = criaBotao("Jogar", p);		
		b1.setAlignmentX(Component.CENTER_ALIGNMENT);
		b1.setActionCommand("Jogar");
		b1.addActionListener(this);

		JButton b2 = criaBotao("Carregar Jogo", p);
		b2.setAlignmentX(Component.CENTER_ALIGNMENT);
		b2.setActionCommand("Load");
		b2.addActionListener(this);

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton button = (JButton) e.getSource();
	    String command = button.getActionCommand();
	    
	    if (command == "Jogar") {
	    	
	    	// Inicializa Xadrez
			Interface xadrez = new XadrezFrame();
			xadrez.cria();
			
			// fecha a tela do menu
			 _f.setVisible(false);
			 dispose();
			
	    	
	    } else {
	    	// Carrega jogo salvo
	    }
	}

}
