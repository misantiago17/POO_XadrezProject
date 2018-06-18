package Interface;

import java.awt.*;
import javax.swing.*;

import Controller.Control;

// Interface do Tabuleiro e Peças
public class Jogo extends Interface {
		
	private final int LARGURA = 700;
	private final int ALTURA = 700;
	private final String NOME = "XADREZ";
	
	private static Jogo _instance;
	
	private Control _ctrl;
	
	private JFrame _f;
	private JPanel _jogo;
	private JButton _saveButton;
	private JPopupMenu _popUp;
	
	private Jogo() {}
	
	public static Jogo getInstance() {
		if (_instance == null) {
			_instance = new Jogo();
		}
		return _instance;
	}
	
	public void cria() {
		
		// Pega instancia da classe que controla o jogo
		_ctrl = Control.getInstance();
		
		// Cria o Frame
		_f = criaJanela(ALTURA,LARGURA,NOME);	
		
		//Cria o Panel e Desenha o tabuleiro de xadrez
		_jogo = _ctrl.addChess(ALTURA, LARGURA,this);
		_jogo.setLayout(new BoxLayout(_jogo, BoxLayout.PAGE_AXIS));
		_jogo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		_f.getContentPane().add(_jogo, BorderLayout.CENTER);	
				
		// Cria um botão para salvar o jogo
		_saveButton = criaBotao("Salvar", 0, 0, _jogo);
		_jogo.add(_saveButton);		
	}
	
	
	
	// Salva o jogo atual
	public void salvaJogo() {
		if (!_ctrl.isPopUpAberto()) {
			System.out.println("SALVA");
		}
	}
	
	// Abre o popUp de promoção do peão
	public void criaPopUp() {
		
		// Cria o popUp
		_popUp = criaPopUpPromocao();
		_popUp.setLayout(new BoxLayout(_popUp,BoxLayout.PAGE_AXIS));
		_popUp.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		
		// Cria texto do popUp
		JLabel text = criaLabel("Escolha a peça da promoção do peão", Font.BOLD, "Dialog", 15);
		text.setAlignmentX(Component.CENTER_ALIGNMENT);
		_popUp.add(text);
		
		// Cria um espaço entre o texto e os botões
		_popUp.add(Box.createRigidArea(new Dimension(0,10)));
		
		// Cria os botão no popUp
		String[] pecas = {"Torre", "Cavalo", "Bispo", "Rainha"};
		
		for (int i=0; i<4;i++) {
			JButton bnt = criaBotao(pecas[i], 100, 30, _popUp);
			bnt.setAlignmentX(Component.CENTER_ALIGNMENT);
			_popUp.add(bnt);
			
			// Cria um espaço entre os botões
			_popUp.add(Box.createRigidArea(new Dimension(0,5)));
		}
		
		// posiciona o popUpMenu no centro do tabuleiro
		int [] pos = pegaMeioMonitor(LARGURA, ALTURA);
		_popUp.show(null, pos[0] + LARGURA/3 + 10, pos[1] + ALTURA/3);
	}
	
	// Fecha o popUp de promoção do peão
	public void fechaPopUp() {
		 _popUp.setVisible(false);
	}
	
}
