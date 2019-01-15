package br.com.perez.hash;

import javax.swing.JDialog;


public class Sobre extends JDialog {
	
	private static final long serialVersionUID = 1L;

	public Sobre() {
		
		
		JDialog sobre = new JDialog();
		
		sobre.setSize (400,400);
		sobre.setResizable(true);
		sobre.setModalityType(ModalityType.APPLICATION_MODAL);
		sobre.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		sobre.setVisible(false);
	}

}
