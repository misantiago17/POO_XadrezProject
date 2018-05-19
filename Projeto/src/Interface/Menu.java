package Interface;

//Primeiro menu do jogo
public class Menu extends Interface {
	
	private final int LARGURA = 400;
	private final int ALTURA = 400;
	private final String NOME = "MENU";
	
	public void cria() {
		criaJanela(ALTURA,LARGURA,NOME);
	}

}
