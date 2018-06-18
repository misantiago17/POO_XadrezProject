package Interface;

import javax.swing.*;

import Controller.Control;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;


//Primeiro menu do jogo
public class Vitoria extends Interface {
		
	private final int LARGURA = 400;
	private final int ALTURA = 400;
	private final String NOME = "FIM DE JOGO";
	
	private static Vitoria _instance;
	
	private JFrame _f;
	private JPanel _p;
	private JLabel _vitoriaLabel;
	private JLabel _vencedorLabel;
	private JLabel _empateLabel;
	private JButton _playButton;
	private JButton _menuButton;
	
	private Vitoria() {}
	
	public static Vitoria getInstance() {
		if (_instance == null) {
			_instance = new Vitoria();
		}
		return _instance;
	}
	
	// Cria o menu inicial do jogo
	public void cria() {
		
		// Cria o Frame
		_f = criaJanela(ALTURA,LARGURA,NOME);
		
		// Cria o Panel
		_p = criaPainel(Color.WHITE,_f);
		_p.setLayout(new BoxLayout(_p,BoxLayout.PAGE_AXIS));
		_p.setBorder(BorderFactory.createEmptyBorder(LARGURA/8, 10, 10, 10));
		
		if (Control.getInstance().pegaVencedor() != "") {
			
			// Cria a parabenização
			_vitoriaLabel = criaLabel("Parabéns!!", Font.BOLD, "Courier", 40);
			_vitoriaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			_p.add(_vitoriaLabel);
			
			// Adiciona um espaçamento entre a parabenização e o time vencedor
			_p.add(Box.createRigidArea(new Dimension(0,30)));
			
			// Diz o nome do vencedor
			_vencedorLabel = criaLabel("O jogador " + Control.getInstance().pegaVencedor() + " venceu.", Font.BOLD, "Courier", 20);
			_vencedorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			_p.add(_vencedorLabel);
			
		} else {
			
			// Cria o texto de empate
			_empateLabel = criaLabel("Foi um empate.", Font.BOLD, "Courier", 40);
			_empateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			_p.add(_empateLabel);
		}
		
		
		// Adiciona um espaçamento entre o vencedor/empate e os botões
		_p.add(Box.createRigidArea(new Dimension(0,30)));
		
		// Cria botão de Jogar
		_playButton = criaBotao("Reiniciar", 120, 30, _p);	
		_playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Adiciona um espaçamento entre os botões
		_p.add(Box.createRigidArea(new Dimension(0,10)));

		// Cria o botão que volta ao menu inicial
		_menuButton = criaBotao("Voltar ao menu", 120, 30, _p);
		_menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);				
	}
	
	// Inicia o jogo
	public void iniciaJogo() {
		
		// Inicializa Jogo
		Jogo.getInstance().cria();
					
		// fecha a tela de vitória
		_f.setVisible(false);
		dispose();
	}
	
	// Volta ao menu inicial
	public void voltaMenu() {
			
		// cria o menu
		Menu.getInstance().cria();
						
		// fecha a tela de vitória
		_f.setVisible(false);
		dispose();
	}

}
