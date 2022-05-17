
import java.awt.*;

/**
 * Felelõssége, hogy közös interfészt biztosít a grafikus osztályoknak, és néhány közös
 * függvényt definiál (pl. draw)
 */
public interface View {

    /**
     * Kirajzolásért felelõs, a leszármazott osztályokban kifejtve
     * @param g A graphics objektum, amire a rajzolás történik.
     */
    void draw(Graphics g);

}