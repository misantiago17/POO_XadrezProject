package Interface;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;
import javax.swing.*;

import Controller.Control;
import Peca.*;
import Tabuleiro.Tabuleiro;

// Interface do Tabuleiro e Peças
public class XadrezFrame extends Interface {
		
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
		fXadrez.getContentPane().add(ctrl.addChess(ALTURA, LARGURA));	
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
	
}
