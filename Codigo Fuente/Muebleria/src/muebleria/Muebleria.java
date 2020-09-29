
package muebleria;

import InterfazGrafica.MenuPrincipal;
import java.awt.Frame;

public class Muebleria {
// main 
    public static void main(String[] args) {
        MenuPrincipal menu = new MenuPrincipal();
        menu.setExtendedState(Frame.MAXIMIZED_BOTH);
        menu.setVisible(true);

    }
    
}
