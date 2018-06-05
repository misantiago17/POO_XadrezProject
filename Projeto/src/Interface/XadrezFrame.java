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

// Interface do Tabuleiro e Pe�as
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
		fXadrez.getContentPane().add(ctrl.addChess(ALTURA, LARGURA,this), BorderLayout.CENTER);	
				
		JButton bSave = criaBotao("Salvar", ctrl.getPanel());
	
		bSave.setActionCommand("Save");
		bSave.addActionListener(this);
		
		ctrl.getPanel().add(bSave);
		
	}
	
	public void showPopUpPromocao(Casa casa) {
		peaoSelecionado = casa;
		String[] pecas = {"Torre", "Cavalo", "Bispo", "Rainha"};
		
		popUp = criaPopUpPromocao();
		
		popUp.setLayout(new BoxLayout(popUp,BoxLayout.PAGE_AXIS));
		popUp.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		
		JLabel l = new JLabel("Escolha a pe�a da promo��o do pe�o");
		l.setFont(new Font("Dialog", Font.BOLD, 15));
		l.setAlignmentX(Component.CENTER_ALIGNMENT);
		popUp.add(l);
		
		popUp.add(Box.createRigidArea(new Dimension(0,10)));
		
		for (int i=0; i<4;i++) {
			String stringCommand = Integer.toString(i);
			JButton b = criaBotao(pecas[i], popUp);
			b.setAlignmentX(Component.CENTER_ALIGNMENT);
			b.setMaximumSize(new Dimension(100,30));
			b.setActionCommand(stringCommand);
			b.addActionListener(this);
			popUp.add(b);
			
			popUp.add(Box.createRigidArea(new Dimension(0,5)));

		}
		
		int [] pos = pegaMeioMonitor(LARGURA, ALTURA);
		popUp.show(null, pos[0] + LARGURA/3 + 10, pos[1] + ALTURA/3);
		ctrl.popUpAberto(true);
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
	
	private void atualizaCasa(Peca pecaSelecionada) {
		
		Casa[][] tabuleiro = Tabuleiro.getTabCasa();
	    tabuleiro[peaoSelecionado.peca.coord.x][peaoSelecionado.peca.coord.y].peca = pecaSelecionada;
	    Tabuleiro.atualizaTabCasa(tabuleiro);
	    ctrl.repaintTable();
	    
	    popUp.setVisible(false);
	    ctrl.popUpAberto(false);
	}

	@Override
	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
	}

	@Override
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
	}

	@Override
	public void popupMenuCanceled(PopupMenuEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton button = (JButton) e.getSource();
	    String command = button.getActionCommand();
	    Peca peao = null;
	    if (peaoSelecionado != null) {
	    	peao = peaoSelecionado.peca;
	    }
	    Peca pecaSelecionada;
	    
	    switch (command) {
	    case "0":
	    	if (peao != null && peao.cor == 'P') {
	    		pecaSelecionada = new Torre(peao.cor, peao.posX, peao.posY, "Torre", Tabuleiro._imgs[11], peao.coord);
	    	} else {
	    		pecaSelecionada = new Torre(peao.cor, peao.posX, peao.posY, "Torre", Tabuleiro._imgs[5], peao.coord);
	    	}
	    	atualizaCasa(pecaSelecionada);
	    	break;
	    case "1":
	    	if (peao != null && peao.cor == 'P') {
	    		pecaSelecionada = new Cavalo(peao.cor, peao.posX, peao.posY, "Cavalo", Tabuleiro._imgs[8], peao.coord);
	    	} else {
	    		pecaSelecionada = new Cavalo(peao.cor, peao.posX, peao.posY, "Cavalo", Tabuleiro._imgs[2], peao.coord);
	    	}
	    	atualizaCasa(pecaSelecionada);
	    	break;
	    case "2":
	    	if (peao != null && peao.cor == 'P') {
	    		pecaSelecionada = new Bispo(peao.cor, peao.posX, peao.posY, "Bispo", Tabuleiro._imgs[6], peao.coord);
	    	} else {
	    		pecaSelecionada = new Bispo(peao.cor, peao.posX, peao.posY, "Bispo", Tabuleiro._imgs[0], peao.coord);
	    	}
	    	atualizaCasa(pecaSelecionada);
	    	break;
	    case "3":
	    	if (peao != null && peao.cor == 'P') {
	    		pecaSelecionada = new Rainha(peao.cor, peao.posX, peao.posY, "Rainha", Tabuleiro._imgs[10], peao.coord);
	    	} else {
	    		pecaSelecionada = new Rainha(peao.cor, peao.posX, peao.posY, "Rainha", Tabuleiro._imgs[4], peao.coord);
	    	}
	    	atualizaCasa(pecaSelecionada);
    		break;
    		
	    	default:
	    		if (!ctrl.isPopUpAberto()) {
	    			System.out.println("SALVA");
	    		}
	    		break;
	    }
	    
	}
	
}
