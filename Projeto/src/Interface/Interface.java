package Interface;

import javax.swing.*;
import java.awt.*;

public abstract class Interface extends JFrame {
	
	public abstract void cria();
	
	public JFrame criaJanela(int altura,int largura, String nome) {
		JFrame frame = new JFrame();
		frame.setSize(largura, altura);
		frame.setMaximizedBounds(new Rectangle(largura, altura));
		frame.setTitle(nome);
		int[] coordenadas = pegaMeioMonitor(largura, altura); 	// Width, height, x, y
		frame.setBounds(coordenadas[0],coordenadas[1],largura,altura);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//frame.setExtendedState(frame.getExtendedState() | frame.MAXIMIZED_BOTH);
		frame.setVisible(true);
						
		return frame;
	}
	
	public JPanel criaPainel(Color c, JFrame f) {
		JPanel p = new JPanel();
		
		p.setBackground(c);		
		f.getContentPane().add(p);
		
		return p;
	}
	
	public JButton criaBotao(String nome, JPanel painel) {
		JButton btn = new JButton(nome);
		// Falta mil coisas
		painel.add(btn);
		return btn;
	}
	
	public JButton criaBotao(String nome, JPopupMenu painel) {
		JButton btn = new JButton(nome);
		// Falta mil coisas
		painel.add(btn);
		return btn;
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
	
}
