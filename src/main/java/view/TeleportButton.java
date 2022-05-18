package view;

import javax.swing.*;
import java.awt.*;
import model.Teleport;

/**
 * Egy teleportkapuhoz tartoz� gomb. A teleportkapu-p�roknak megfelel� sz�n�.
 */
public class TeleportButton extends JButton {

    /**
     * A teleportkapu, akit ismer.
     */
    private transient Teleport teleport;

    private LevelView levelView;

    /**
     * Inicializ�lja a gomb m�reteit.
     * @param lv A levelview, amely a j�t�kot kirajzolja.
     */
    public TeleportButton(LevelView lv) {
        super();
        this.levelView = lv;
        setSize(30, 50);
        setPreferredSize(new Dimension(30 , 50));
        setMaximumSize(new Dimension(30 , 50));
        setMinimumSize(new Dimension(30 , 50));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 12));
        setBackground(new Color(120, 45, 76));
        setOpaque(true);
    }

    /**
     * Visszaadja az ismert teleportkaput
     * @return A teleportkapu
     */
    public Teleport getTeleport() {
        return teleport;
    }

    /**
     * Be�ll�tja az ismert teleportkaput.
     * @param t Az �j teleportkapu.
     */
    public void setTeleport(Teleport t) {
        this.teleport = t;
        Color c;
        if (teleport == null) {
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            c = new Color(120, 45, 76);
        }else {
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 12));
            c = levelView.getTeleportColor(t);
        }
        setBackground(c);
    }
}
