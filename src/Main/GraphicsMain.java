
package Main;

import java.awt.*;


public class GraphicsMain {

    /**
     * it starts the game
     * @param args initialise the game
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> new GameFrame().initialize());
    }

}
