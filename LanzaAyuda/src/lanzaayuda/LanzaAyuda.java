/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanzaayuda;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author Pablo Rodriguez Pino 2ºDAM
 */
public class LanzaAyuda {

    private static JFrame frame;
    private static JPanel panel;
    private static JMenuBar menuBar;
    private static JMenu menu;
    private static JMenuItem miAyuda;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Crea la ventana
        frame = new JFrame("LanzaAyuda");

        panel = new JPanel();
        panel.setSize(300, 300);

        menuBar = new JMenuBar();
        menu = new JMenu("Ayuda");
        menuBar.add(menu);

        miAyuda = new JMenuItem("Lanzar Ayuda");

        HelpSet hs = obtenFicAyuda();
        HelpBroker hb = hs.createHelpBroker();
        hb.enableHelpOnButton(miAyuda, "ayuda", hs);
        hb.setSize(new Dimension(800,600));
        miAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        hb.enableHelpKey(miAyuda, "ayuda", hs);

        menu.add(miAyuda);

        frame.setJMenuBar(menuBar);
        

        frame.add(panel);

        frame.setVisible(true);
        frame.setSize(300, 300);

    }

    public static HelpSet obtenFicAyuda() {
        try {
            //URL url = new URL("jar:file:LanzaAyuda.jar!/lanzaayuda/help/helpset.hs");
            // ClassLoader cl = LanzaAyuda.class.getClassLoader();
            File file = new File(LanzaAyuda.class.getResource("help/helpset.hs").getFile());
            URL url = file.toURI().toURL();
            // crea un objeto Helpset
            HelpSet hs = new HelpSet(null, url);
            return hs;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Fichero HelpSet no encontrado");
            return null;
        }
    }

}
