package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainLoginView extends JFrame {
	
	/**
	 *  Tela inicial da aplica��o, conter� os campos para o usu�rio logar na aplica��o
	**/
	private static final long serialVersionUID = 1L;

		public MainLoginView() {
			this.initUI();
		}
		
		// inicia a parte gr�fica da aplica��o
		private void initUI() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			JPanel contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			this.setVisible(true);
		}

}
