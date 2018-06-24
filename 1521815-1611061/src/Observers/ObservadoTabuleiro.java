package Observers;

import Tabuleiro.Casa;

public interface ObservadoTabuleiro {
	
	void add(ObservadorTabuleiro o);
	
	void remove (ObservadorTabuleiro o);
	
	Casa[][] get();

}
