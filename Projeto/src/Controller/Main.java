package Controller;

import Interface.*;

public class Main {
	
	public static void main(String[] args) {
		
		// Inicializa o menu
		//Interface menu = new Menu();
		//menu.cria();
		
		// Inicializa Xadrez
		Interface xadrez = new XadrezFrame();
		xadrez.cria();
	}

}
