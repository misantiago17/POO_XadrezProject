package Interface;

import javax.swing.*;
import java.awt.*;

public abstract class Interface extends JFrame {
	
	public abstract void cria();
	
	public void criaJanela(int altura,int largura, String nome) {
		setSize(largura,altura);
		setTitle(nome);
		int[] coordenadas = pegaMeioMonitor(largura, altura); 	// Width, height, x, y
		setBounds(coordenadas[0],coordenadas[1],largura,altura);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setVisible(true);
	}
	
	/* pegaMeioMonitor
	 *  Função que pega o centro da tela e retorna um array de inteiros contendo:
	 *  posição x na janela na tela, posição y da janela na tela, a largura do monitor e a altura do monitor
	 */
	private int[] pegaMeioMonitor(int larg, int alt) {
		int[] coord = new int[2];
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		 
		coord[0] = screenSize.width/2 - larg/2; 
		coord[1] = screenSize.height/2 - alt/2; 
		
		return coord;
	}
	
	
	// JFrame Contrutores
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

}
