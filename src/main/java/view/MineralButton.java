package view;

import model.Mineral;
import view.LevelView;

import javax.swing.*;
import java.awt.*;

/**
 * Egy nyersanyaghoz tartoz� gomb. A nyersanyagnak megfelel� sz�n�.
 */
public class MineralButton extends JButton {

    /**
     * A nyersanyag, amit ismer.
     */
    private Mineral mineral;

    /**
     * Inicializ�lja a gomb m�reteit.
     */
    public MineralButton() {
        super();
        setSize(20, 20);
        setPreferredSize(new Dimension(20 , 20));
        setMaximumSize(new Dimension(20 , 20));
        setMinimumSize(new Dimension(20 , 20));
        setBackground(LevelView.mineralColor(mineral));
        if (mineral == null) {
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        } else {
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
        }
        setOpaque(true);
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
    }

    /**
     * Visszaadja az ismert nyersanyagot.
     * @return A nyersanyag.
     */
    public Mineral getMineral() {
        return mineral;
    }

    /**
     * Be�ll�tja az ismert nyersanyagot �s friss�ti a gomb sz�n�t.
     * @param m Az �j nyersanyag.
     */
    public void setMineral(Mineral m) {
        this.mineral = m;
        setBackground(LevelView.mineralColor(mineral));
        if (mineral == null) {
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        } else {
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
        }
    }
}
