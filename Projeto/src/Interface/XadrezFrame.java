package Interface;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import Controller.Control;
import Peca.*;
import Tabuleiro.Tabuleiro;

// Interface do Tabuleiro e Peças
public class XadrezFrame extends Interface implements PopupMenuListener{
		
	private final int LARGURA = 700;
	private final int ALTURA = 700;
	private final String NOME = "XADREZ";
	
	private final File pasta = new File("./resources");
	private File[] listaArquivos = pasta.listFiles();
	
	public static Image[] imagens = new Image[12];
	public static String[] nomeImagens = new String[12];
	
	private Control ctrl;
	
	public void cria() {
		
		carregaImagem();
				
		JFrame fXadrez = criaJanela(ALTURA,LARGURA,NOME);	
			
		ctrl = Control.getInstance();
		fXadrez.getContentPane().add(ctrl.addChess(ALTURA, LARGURA,this));	
	}
	
	public void showPopUpPromocao() {
		String[] pecas = {"Torre", "Cavalo", "Bispo", "Rainha"};
		
		JPopupMenu popUp = criaPopUpPromocao();
		
		popUp.setLayout(new BoxLayout(popUp,BoxLayout.Y_AXIS));
		
		JLabel l = new JLabel("   Escolha a peça da promoção do peão   ");
		l.setAlignmentX(Component.CENTER_ALIGNMENT);
		popUp.add(l);
		
		for (int i=0; i<4;i++) {
			JButton b = criaBotao(pecas[i], popUp);
			b.setAlignmentX(Component.CENTER_ALIGNMENT);
			popUp.add(b);
		}
		
		popUp.show(null, LARGURA/2, ALTURA/2);
	}
	
	private void carregaImagem() {
				
		try {
			
			for (int i=0; i<listaArquivos.length;i++) {
				if (listaArquivos[i].isFile()) {					
					imagens[i] = ImageIO.read(listaArquivos[i]);
					nomeImagens[i] = listaArquivos[i].getName();					
				}
			}		
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	@Override
	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}

	@Override
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}

	@Override
	public void popupMenuCanceled(PopupMenuEvent e) {}
	
}
