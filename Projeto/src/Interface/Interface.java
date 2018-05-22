package Interface;

import javax.swing.*;
import java.awt.*;

public abstract class Interface extends JFrame {
	
	public abstract void cria();
	
	public JFrame criaJanela(int altura,int largura, String nome) {
		JFrame frame = new JFrame();
		frame.setSize(largura,altura);
		frame.setTitle(nome);
		int[] coordenadas = pegaMeioMonitor(largura, altura); 	// Width, height, x, y
		frame.setBounds(coordenadas[0],coordenadas[1],largura,altura);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);		
		frame.setVisible(true);
				
		return frame;
	}
	
	public JPanel criaPainel(Color c, JFrame f) {
		JPanel p = new JPanel();
		
		p.setBackground(c);		
		f.getContentPane().add(p);
		
		return p;
	}
	
	public void criaBotao(String nome, JPanel painel) {
		JButton btn = new JButton(nome);
		// Falta mil coisas
		painel.add(btn);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		System.out.println("ASKfjvUYLEADVFG");
		
		g.drawString("FRASE AAA", 120, 140);
		
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
	
	
	// JFrame Construtores -- JANELA
		/*
		 * Jframe(String title)
		 * JFrame()
		 */
		
		// JFrame Métodos importantes
		/*
		 * void setBounds(int x, int y, int width, int height)
		 * void setSize(int width,int height)
		 * void setLocation(int x, int y)
		 * void setVisible(boolean b)
		 * void setTitle(String title)
		 * Container getContentPane()
		 * void setJManuBar(JMenuBar menubar)
		 * JmanuBar getJManuBar()
		 * void setResizable(boolean resizable)
		 * void setDefaultCloseOperation(int op)
		 */
		
		// Valores validos para op
		/*
		 * EXIT_ON_CLOSE
		 * HIDE_ON_CLOSE
		 * DISPOSE_ON_CLOSE
		 * DO_NOTHING_ON_CLOSE
		 */
	
	
	// JPanel Construtores -- BOTÃO
			/*
			 * JFrame(LayoutManager layout)
			 * JPanel()
			 */
			
			// JPanel Métodos importantes
			/*
			 * void setSize(int width,int height)
			 * void setLayout(LayoutManager layout)
			 * Component add(Component comp)
			 * void add(Component c, Object constraints)
			 * void setEnabled(boolean b)
			 * void setBackground(Color c)
			 * void paintComponent(Graphics g)
			 * void repaint()
			 */
			
			// Valores validos para op
			/*
			 * EXIT_ON_CLOSE
			 * HIDE_ON_CLOSE
			 * DISPOSE_ON_CLOSE
			 * DO_NOTHING_ON_CLOSE
			 */

}
