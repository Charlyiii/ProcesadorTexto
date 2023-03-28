import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;


@SuppressWarnings("serial")
public class LaminaProcesadorPro extends JPanel {

public LaminaProcesadorPro() {
		
		setLayout(new BorderLayout());
		
		JPanel panelBarra=new JPanel();
		
		barra=new JMenuBar();
		
		fuente=new JMenu("Fuente");
		estilo=new JMenu("Estilo");
		tamano=new JMenu("Tamaño");
		
		barra.add(fuente);
		barra.add(estilo);
		barra.add(tamano);
		
		configurarBarra("Fuente", "Arial", "Arial", 0, 12, "");
		configurarBarra("Fuente", "Courier", "Courier new", 0, 12, "");
		configurarBarra("Fuente", "Verdana", "Verdana", 0, 12, "");
		
		configurarBarra("Estilo", "Negrita", "", Font.BOLD, 12, "ComponentesSwing/ProcesadorTextosPro/negrita.gif");
		configurarBarra("Estilo", "Cursiva", "", Font.ITALIC, 12, "ComponentesSwing/ProcesadorTextosPro/cursiva.gif");
		
		configurarBarra("Tamaño", "12", "", 0, 12, "" );
		configurarBarra("Tamaño", "14", "", 0, 14, "" );
		configurarBarra("Tamaño", "16", "", 0, 16, "" );
		configurarBarra("Tamaño", "20", "", 0, 20, "" );
		configurarBarra("Tamaño", "24", "", 0, 24, "" );
				
		
		panelBarra.add(barra);
		add(panelBarra, BorderLayout.NORTH);

		texto=new JTextPane();
		
		add(texto, BorderLayout.CENTER);
		
		emergente=new JPopupMenu("Estilo");
		JMenuItem negritaE=new JMenuItem("Negrita");
		JMenuItem cursivaE=new JMenuItem("Cursiva");
		negritaE.addActionListener(new StyledEditorKit.BoldAction());
		cursivaE.addActionListener(new StyledEditorKit.ItalicAction());
		emergente.add(negritaE);
		emergente.add(cursivaE);
		texto.setComponentPopupMenu(emergente);

		barraHerramientas=new JToolBar();
		barraHerramientas.setOrientation(JToolBar.VERTICAL);
		
		
		configurarBarraHerramientas("negrita.gif").addActionListener(new StyledEditorKit.BoldAction());
		configurarBarraHerramientas("cursiva.gif").addActionListener(new StyledEditorKit.ItalicAction());
		configurarBarraHerramientas("subrayado.gif").addActionListener(new StyledEditorKit.UnderlineAction());
		barraHerramientas.addSeparator();
		configurarBarraHerramientas("azul.gif").addActionListener(new StyledEditorKit.ForegroundAction("Azul", Color.BLUE));
		configurarBarraHerramientas("rojo.gif").addActionListener(new StyledEditorKit.ForegroundAction("rojo", Color.RED));
		configurarBarraHerramientas("verde.gif").addActionListener(new StyledEditorKit.ForegroundAction("verde", Color.GREEN));
		barraHerramientas.addSeparator();
		configurarBarraHerramientas("izquierda.gif").addActionListener(new StyledEditorKit.AlignmentAction("izquierda",StyleConstants.ALIGN_LEFT));
																						//javax.swing.text.StyleConstants, podría poner 0
		configurarBarraHerramientas("centrar.gif").addActionListener(new StyledEditorKit.AlignmentAction("centrado",StyleConstants.ALIGN_CENTER));
		configurarBarraHerramientas("derecha.gif").addActionListener(new StyledEditorKit.AlignmentAction("derecha",StyleConstants.ALIGN_RIGHT));
		configurarBarraHerramientas("justificar.gif").addActionListener(new StyledEditorKit.AlignmentAction("justificado",StyleConstants.ALIGN_JUSTIFIED));
		
		add(barraHerramientas, BorderLayout.WEST);	
	}
	
	private void configurarBarra(String nombreEstilo, String etiqueta, String tipoLetra, int estiloLetra, int tamanoLetra, String UrlIcono )  {
		
		JMenuItem elemento=new JMenuItem(etiqueta);
		
		if(nombreEstilo=="Fuente") {
			fuente.add(elemento);
			elemento.addActionListener(new StyledEditorKit.FontFamilyAction("No se usa", tipoLetra));
		}
		
		else if(nombreEstilo=="Estilo") {
			
			//así no saldría en el jar ejecutable, sin usat ToolKit. Mirar que así hay que usar la ruta completa.
			//Desde donde está el paquete.
			elemento=new JMenuItem(etiqueta, new ImageIcon(UrlIcono));
			estilo.add(elemento);
			if(estiloLetra==Font.BOLD) {
				
				elemento.addActionListener(new StyledEditorKit.BoldAction());
			
				//FACIL PARA ASIGNAR TECLAS A UN JMENUITEM, ADEMÁS APARECE LA COMBINACIÓN EN EL BOTÓN
				elemento.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
			}
			else if(estiloLetra==Font.ITALIC) {
				
				elemento.addActionListener(new StyledEditorKit.ItalicAction());
				elemento.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_DOWN_MASK));
			}
		}
		else 
		{ 
			JRadioButton radio=new JRadioButton(etiqueta);
			tamanos.add(radio);
			tamano.add(radio);
			radio.addActionListener(new StyledEditorKit.FontSizeAction("No se usa", tamanoLetra));
		}	
	}
	
	private JButton configurarBarraHerramientas(String UriIcono) {
		
		//Para que salgan los iconos al crear un jar ejecutable. Mirar la ruta de como coge el toolkit la imagen 
		//Imagenes en la misma carpeta que el archivo.java y solo hace falta poner el nombre y extensión.
		//Si no uso ToolKit, hay que usar la ruta completa desde donde está el paquete, como los iconos de la barra
		
		Toolkit miEquipo=Toolkit.getDefaultToolkit();
		
		URL rutaImagen=MarcoProcesadorPro.class.getResource(UriIcono);
		
		Image miImagen=miEquipo.getImage(rutaImagen);
		
		ImageIcon miIcono = new ImageIcon(miImagen);
			
		JButton boton=new JButton(miIcono);
		
		barraHerramientas.add(boton);
		return boton;
		
	}
		
	JMenuBar barra;
	JMenu fuente, estilo, tamano;
	ButtonGroup tamanos=new ButtonGroup();
	JTextPane texto;
	JToolBar barraHerramientas;
	JPopupMenu emergente;
}
