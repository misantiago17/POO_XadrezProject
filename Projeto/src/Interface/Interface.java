package Interface;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import Controller.Control;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Interface extends JFrame implements ActionListener, PopupMenuListener {
	
	public abstract void cria();
	
	// Cria o Frame
	public JFrame criaJanela(int altura,int largura, String nome) {
		JFrame frame = new JFrame();
		frame.setSize(largura, altura);
		frame.setMaximizedBounds(new Rectangle(largura, altura));
		frame.setTitle(nome);
		int[] coordenadas = pegaMeioMonitor(largura, altura); 	// Width, height, x, y
		frame.setBounds(coordenadas[0],coordenadas[1],largura,altura);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
						
		return frame;
	}
	
	// Cria o painel
	public JPanel criaPainel(Color c, JFrame f) {
		JPanel p = new JPanel();
		
		p.setBackground(c);		
		f.getContentPane().add(p);
		
		return p;
	}
	
	// Cria botão no painel
	public JButton criaBotao(String nome, int sizeX, int sizeY, JPanel painel) {
		JButton btn = new JButton(nome);
		if (sizeX != 0 && sizeY != 0) {
			btn.setMaximumSize(new Dimension (120,30));
		}
		btn.setActionCommand(nome);
		btn.addActionListener(this);
		painel.add(btn);
		
		return btn;
	}
	
	// Cria botão no menu de PopUp
	public JButton criaBotao(String nome, int sizeX, int sizeY, JPopupMenu painel) {
		JButton btn = new JButton(nome);
		if (sizeX != 0 && sizeY != 0) {
			btn.setMaximumSize(new Dimension (120,30));
		}
		btn.setActionCommand(nome);
		btn.addActionListener(this);
		painel.add(btn);
		return btn;
	}
	
	public JLabel criaLabel(String nome, int fontType, String fontName, int size) {
		JLabel l = new JLabel(nome);
		l.setFont(new Font(fontName, fontType, size));
		return l;
	}
	
	
	public JPopupMenu criaPopUpPromocao() {
		JPopupMenu popUp = new JPopupMenu();
		return popUp;
	}
	
	/* pegaMeioMonitor
	*  Função que pega o centro da tela e retorna um array de inteiros contendo:
	*  posição x na janela na tela, posição y da janela na tela, a largura do monitor e a altura do monitor
	*/
	public int[] pegaMeioMonitor(int larg, int alt) {
		int[] coord = new int[2];
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		 
		coord[0] = screenSize.width/2 - larg/2; 
		coord[1] = screenSize.height/2 - alt/2; 
		
		return coord;
	}
	
	// Ações do ActionListener
	@Override 
	public void actionPerformed(ActionEvent e) {
		
		JButton button = (JButton) e.getSource();
	    String command = button.getActionCommand();
	    
	    switch(command) {
	    
	    // Inicia o jogo
	    case "Jogar":
	    	Menu.getInstance().iniciaJogo();
	    	break;
	    
	    // Carrega o jogo salvo
	    case "Carregar Jogo":
	    	Control.getInstance().carregaJogo();
	    	break;
	    
	    // Salva o jogo atual
	    case "Salvar":
	    	Control.getInstance().salvaJogo();
	    	break;
	    	
	    // Escolhas da troca da promoção do peão
	    case "Torre":
	    	Control.getInstance().promovePeao("Torre");
	    	break;
	    case "Cavalo":
	    	Control.getInstance().promovePeao("Cavalo");
	    	break;
	    case "Bispo":
	    	Control.getInstance().promovePeao("Bispo");
	    	break;
	    case "Rainha":
	    	Control.getInstance().promovePeao("Rainha");
	    	break;
	    	
	    	default:
	    		System.out.println("Opção inválida");
	    		break;
	    }

	}
	
	// Ações do PopupMenuListener
	@Override
	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
	@Override
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
	@Override
	public void popupMenuCanceled(PopupMenuEvent e) {}
	
}
