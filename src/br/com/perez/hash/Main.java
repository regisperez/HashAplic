package br.com.perez.hash;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Dialog.ModalityType;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Main extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	JButton pasta;
	JButton hash;
	JButton fechar;
	JTextField pastaSelecionada;
	JFileChooser chooser;
	String choosertitle;
	int resultado;
	private JMenuBar menuBar;

	public Main() {
		
			
		JFrame f = new JFrame("Hash Aplic Tce-MT");
	    f.setSize(480, 320);	
	    f.setResizable(false);
	    f.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    f.setContentPane(new JLabel(new ImageIcon(Main.class.getResource("/images/xml.jpg"))));
	    f.setIconImage(new ImageIcon("/images/xml.jpg").getImage());
		pasta = new JButton("Selecionar pasta");
		pasta.setBounds(274, 83, 166, 23);
		pasta.addActionListener(this);
		hash = new JButton("Hash");
		hash.setFont(new Font("Tahoma", Font.BOLD, 11));
		hash.setBounds(63, 166, 201, 23);
		
		
		hash.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if ( chooser ==null){
			    	JOptionPane.showMessageDialog(null, "Nenhuma pasta selecionada");		
			 }else{
				 try{
					    PrintWriter writer = new PrintWriter(chooser.getSelectedFile()+"\\HASH_ARQUIVOS_XMLS.txt", "UTF-8");
					    File[] selectedFiles = chooser.getSelectedFile().listFiles(); 
					   
					    for (int i = 0;i<selectedFiles.length;i++){
					    	if (selectedFiles[i].getName().contains(".xml")||(selectedFiles[i].getName().contains(".XML"))){
					    		String hex = Hash.generate(selectedFiles[i]);
					    		writer.println(selectedFiles[i].getName()+";"+hex);	
					    	}		    		
					    }
					    JOptionPane.showMessageDialog(null, "HashCode gerado com sucesso");			
					    writer.close();
					    
						} catch (IOException ex) {
						   // do something
						} catch (NoSuchAlgorithmException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	 				 
			 }				
			}
		});
		fechar = new JButton("Fechar");
		fechar.setBounds(274, 166, 166, 23);
		fechar.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
	 f.getContentPane().setLayout(null);	 
	 f.getContentPane().add(pasta);
	 f.getContentPane().add(hash);
	 f.getContentPane().add(fechar);	 
	 pastaSelecionada= new JTextField();
	 pastaSelecionada.setFont(new Font("Tahoma", Font.PLAIN, 12));
	 pastaSelecionada.setEditable(false);
	 pastaSelecionada.setBounds(63, 84, 201, 19);
	 f.getContentPane().add(pastaSelecionada);
	 
	 menuBar = new JMenuBar();
	 menuBar.setBounds(0, 0, 91, 21);
	 JMenu arquivo = new JMenu("Arquivo");
	 JMenuItem sair = new JMenuItem("Sair");
	 sair.addActionListener(new ActionListener() {
	 	public void actionPerformed(ActionEvent e) {
	 		System.exit(0);
	 	}
	 });
	 arquivo.add(sair);
	 menuBar.add(arquivo);
	 JMenu ajuda = new JMenu("Ajuda");
	 JMenuItem versao = new JMenuItem("Sobre");
	 versao.addActionListener(new ActionListener() {
	 	
		public void actionPerformed(ActionEvent e) {
			JDialog sobre = new JDialog();
			sobre.setTitle("Sobre");
			sobre.setSize (250,200);
			sobre.setResizable(true);
			sobre.setModalityType(ModalityType.APPLICATION_MODAL);
			sobre.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			sobre.setLocationRelativeTo(null);
			
			JLabel versao = new JLabel("<HTML> Hash Aplic TCE-MT <BR>Versão 1.0.0.0 <BR> Copyright @2017<BR><BR>"
									+ "Desenvolvido por Régis Perez <BR> "
									+ "https://github.com/regisperez </HTML>");
			sobre.add(versao, null);
			sobre.setVisible(true);
	 	}
	 });
	 ajuda.add(versao);
	 menuBar.add(ajuda);
	 f.getContentPane().add(menuBar);
	 f.setLocationRelativeTo(null);
	 f.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
	     
	 chooser = new JFileChooser(); 
	 chooser.setCurrentDirectory(new java.io.File("."));
	 chooser.setDialogTitle(choosertitle);
	 chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	 chooser.setAcceptAllFileFilterUsed(true);
	     
	 if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
		 pasta.setText("Pasta Selecionada");	
		 pastaSelecionada.setText(chooser.getSelectedFile().getPath());
	 }else {
	     pasta.setText("Selecionar pasta");
	     pastaSelecionada.setText("");
	     chooser =null;
	   }
	}

	public static void main(String s[]) {
	 new Main();
	 }
	}
