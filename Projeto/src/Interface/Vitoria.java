package Interface;

import javax.swing.*;
import Controller.Control;

public class Vitoria {
	
	private static Vitoria _instance;
	
	private JPanel _p;
	
	private Vitoria() {}
	
	public static Vitoria getInstance() {
		if (_instance == null) {
			_instance = new Vitoria();
		}
		return _instance;
	}
	
	// Cria o JOptionPane final do jogo
	public void cria() {
		
		// Pega o panel do tabuleiro
		_p = Control.getInstance().pegaPanel();
		
		if (Control.getInstance().pegaVencedor() != "") {
			
			// Cria a parabenização
			JOptionPane.showMessageDialog(_p, "Parabéns!! O jogador " + Control.getInstance().pegaVencedor() + " venceu.");
			
		} else {
			
			// Cria o texto de empate
			JOptionPane.showMessageDialog(_p, "Foi um empate.");
		}
		
		// Cria botão de Jogar
		int resposta = JOptionPane.showConfirmDialog(_p,"Deseja voltar para o menu?", "Sair", JOptionPane.YES_NO_OPTION);

		if (resposta == JOptionPane.YES_OPTION) {
			voltaMenu();
		} else {
			 System.exit(0);
		}
	}
	
	// Volta ao menu inicial
	private void voltaMenu() {
			
		// cria o menu
		Menu.getInstance().cria();
	}

}
