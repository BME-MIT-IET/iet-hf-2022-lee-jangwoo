package view;

import model.*;
import java.awt.*;
import java.util.*;

/**
 * Felelőssége, hogy közös interfészt biztosít a grafikus osztályoknak, és néheny közös
 * függvényt definiál (pl. draw)
 */
public interface View {

    /**
     * Kirajzolásért felelős, a leszérmazott osztályokban kifejtve
     * @param g A graphics objektum, amire a rajzolés történik.
     */
    void draw(Graphics g);

}