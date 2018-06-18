package Observers;

import Controller.Control;


public class ControlInterface {
	
	private static ControlInterface _instance;
	
	private ControlInterface() {

	}
	
	public static ControlInterface getInstance() {
		if (_instance == null) {
			_instance = new ControlInterface();
		}
		return _instance;
	}
	
	public ObservadoTabuleiro registra(ObservadorTabuleiro o) {
		return null;
	}

}
