package Interface;

import javax.swing.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;


//Primeiro menu do jogo
public class Menu extends Interface {
		
	private final int LARGURA = 400;
	private final int ALTURA = 400;
	private final String NOME = "MENU";
	
	private static Menu _instance;
	
	private JFrame _f;
	private JPanel _p;
	private JLabel _xadrezLabel;
	private JButton _playButton;
	private JButton _loadButton;
	
	private Menu() {}
	
	public static Menu getInstance() {
		if (_instance == null) {
			_instance = new Menu();
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
		
		// Cria o título do jogo
		_xadrezLabel = criaLabel("Xadrez", Font.BOLD, "Courier", 60);
		_xadrezLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		_p.add(_xadrezLabel);
		
		// Adiciona um espaçamento entre o título do jogo e os botões
		_p.add(Box.createRigidArea(new Dimension(0,60)));
		
		// Cria botão de Jogar
		_playButton = criaBotao("Jogar", 120, 30, _p);	
		_playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Adiciona um espaçamento entre os botões
		_p.add(Box.createRigidArea(new Dimension(0,10)));

		// Cria o botão de carregar um jogo
		_loadButton = criaBotao("Carregar Jogo", 120, 30, _p);
		_loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);				
	}
	
	// Inicia o jogo
	public void iniciaJogo() {
		
		// Inicializa Jogo
		Jogo.getInstance().cria();
					
		// fecha a tela do menu
		_f.setVisible(false);
		dispose();
	}

}
