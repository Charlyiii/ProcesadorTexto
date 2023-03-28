import javax.swing.JFrame;


@SuppressWarnings("serial")
public class MarcoProcesadorPro extends JFrame {
public MarcoProcesadorPro() {
		
		setSize(600,420);
		setLocationRelativeTo(null);
		add(new LaminaProcesadorPro());
		setVisible(true);
	}
}
