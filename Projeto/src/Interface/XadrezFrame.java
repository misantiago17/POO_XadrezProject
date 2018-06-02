package Interface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import Controller.Control;
import Peca.*;
import Tabuleiro.Casa;
import Tabuleiro.Coordenadas;
import Tabuleiro.Tabuleiro;

// Interface do Tabuleiro e Peças
public class XadrezFrame extends Interface implements PopupMenuListener, ActionListener {
		
	private final int LARGURA = 700;
	private final int ALTURA = 700;
	private final String NOME = "XADREZ";
	
	private final File pasta = new File("./resources");
	private File[] listaArquivos = pasta.listFiles();
	
	public static Image[] imagens = new Image[12];
	public static String[] nomeImagens = new String[12];
	
	public Casa peaoSelecionado;
	
	private Control ctrl;
	private JPopupMenu popUp;
	
	public void cria() {
		
		carregaImagem();
				
		JFrame fXadrez = criaJanela(ALTURA,LARGURA,NOME);	
			
		ctrl = Control.getInstance();
		fXadrez.getContentPane().add(ctrl.addChess(ALTURA, LARGURA,this));	
	}
	
	public void showPopUpPromocao(Casa casa) {
		peaoSelecionado = casa;
		String[] pecas = {"Torre", "Cavalo", "Bispo", "Rainha"};
		
		popUp = criaPopUpPromocao();
		
		popUp.setLayout(new BoxLayout(popUp,BoxLayout.Y_AXIS));
		
		JLabel l = new JLabel("   Escolha a peça da promoção do peão   ");
		l.setAlignmentX(Component.CENTER_ALIGNMENT);
		popUp.add(l);
		
		for (int i=0; i<4;i++) {
			String stringCommand = Integer.toString(i);
			JButton b = criaBotao(pecas[i], popUp);
			b.setAlignmentX(Component.CENTER_ALIGNMENT);
			b.setActionCommand(stringCommand);
			b.addActionListener(this);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton button = (JButton) e.getSource();
	    String command = button.getActionCommand();
	    Peca peao = peaoSelecionado.peca;
	    Peca pecaSelecionada;
	    
	    switch (command) {
	    case "0":
	    	if (peao.cor == 'P') {
	    		pecaSelecionada = new Torre(peao.cor, peao.posX, peao.posY, "Torre", Tabuleiro._imgs[11], peao.coord);
	    	} else {
	    		pecaSelecionada = new Torre(peao.cor, peao.posX, peao.posY, "Torre", Tabuleiro._imgs[5], peao.coord);
	    	}
	    	break;
	    case "1":
	    	if (peao.cor == 'P') {
	    		pecaSelecionada = new Cavalo(peao.cor, peao.posX, peao.posY, "Cavalo", Tabuleiro._imgs[8], peao.coord);
	    	} else {
	    		pecaSelecionada = new Cavalo(peao.cor, peao.posX, peao.posY, "Cavalo", Tabuleiro._imgs[2], peao.coord);
	    	}
	    	break;
	    case "2":
	    	if (peao.cor == 'P') {
	    		pecaSelecionada = new Bispo(peao.cor, peao.posX, peao.posY, "Bispo", Tabuleiro._imgs[6], peao.coord);
	    	} else {
	    		pecaSelecionada = new Bispo(peao.cor, peao.posX, peao.posY, "Bispo", Tabuleiro._imgs[0], peao.coord);
	    	}
	    	break;
	    	default:
	    		if (peao.cor == 'P') {
		    		pecaSelecionada = new Rainha(peao.cor, peao.posX, peao.posY, "Rainha", Tabuleiro._imgs[10], peao.coord);
		    	} else {
		    		pecaSelecionada = new Rainha(peao.cor, peao.posX, peao.posY, "Rainha", Tabuleiro._imgs[4], peao.coord);
		    	}
	    		break;
	    }
	    
	    Casa[][] tabuleiro = Tabuleiro.getTabCasa();
	    tabuleiro[peao.coord.x][peao.coord.y].peca = pecaSelecionada;
	    Tabuleiro.atualizaTabCasa(tabuleiro);
	    Control.getInstance().repaintTable();
	    
	    popUp.setVisible(false);
	}
	
}
