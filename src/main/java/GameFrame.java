import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A levelview �s az inventoryview paneleket tartalmazza. A men�t is tartalmazza.
 */
public class GameFrame extends JFrame {
    /**
     * A levelview objektum, ami haszn�latban van.
     */
    private LevelView lv;

    /**
     * Az inventoryview objektum, ami haszn�latban van.
     */
    private InventoryView iv;

    /**
     * A men�.
     */
    private JMenuBar menuB = new JMenuBar();

    /**
     * Az esem�nykezel�, akit ismer.
     */
    private ActionListener actionListener;

    /**
     * Inicializ�lja a men�t a megfelel�men�pontokkal.
     * Be�ll�tja a hozz�juk tartoz� actionCommand-ot.
     */
    private void initMenu(){
        JMenu file = new JMenu("File");
        JMenuItem temp = new JMenuItem("Load");
        temp.setActionCommand("load");
        temp.addActionListener(actionListener);
        file.add(temp);
        temp = new JMenuItem("Save");
        temp.setActionCommand("save");
        temp.addActionListener(actionListener);
        file.add(temp);
        temp = new JMenuItem("New Game");
        temp.setActionCommand("newgame");
        temp.addActionListener(actionListener);
        file.add(temp);
        temp = new JMenuItem("Give up");
        temp.setActionCommand("giveup");
        temp.addActionListener(actionListener);
        file.add(temp);
        menuB.add(file);

        JMenu check = new JMenu("Check");
        temp = new JMenuItem("Check Win");
        temp.setActionCommand("checkwin");
        temp.addActionListener(actionListener);
        check.add(temp);
        temp = new JMenuItem("Check Lose");
        temp.setActionCommand("checklose");
        temp.addActionListener(actionListener);
        check.add(temp);
        menuB.add(check);
    }

    /**
     * Be�ll�tja az ablak m�ret�t, elk�sz�ti a levelview-t �s az inventoryviewt.
     * @param c A kontroller.
     * @param game A j�t�k.
     */
    public GameFrame(Control c, Game game) {
        super();
        actionListener = c;
        setTitle("Asteroid Game");
        initMenu();
        setJMenuBar(menuB);
        setMinimumSize(new Dimension(1000, 600));
        setPreferredSize(new Dimension(1000, 600));
        lv = new LevelView(game);
        iv = new InventoryView(lv, c);
        lv.addMouseListener(c);
        add(lv, BorderLayout.CENTER);
        add(iv, BorderLayout.SOUTH);
        pack();
    }

    /**
     * Getter a levelview attr�b�tumhoz.
     * @return A levelview.
     */
    public LevelView getLevelView(){
        return lv;
    }
}
