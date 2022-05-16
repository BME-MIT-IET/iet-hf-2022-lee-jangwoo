package view;

import java.awt.*;
import java.util.*;

/**
 * Felel�ss�ge, hogy k�z�s interf�szt biztos�t a grafikus oszt�lyoknak, �s n�h�ny k�z�s
 * f�ggv�nyt defini�l (pl. draw)
 */
public interface View {

    /**
     * Kirajzol�s�rt felel�s, a lesz�rmazott oszt�lyokban kifejtve
     * @param g A graphics objektum, amire a rajzol�s t�rt�nik.
     */
    public void draw(Graphics g);

}