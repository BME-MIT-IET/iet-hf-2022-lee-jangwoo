package view;

import java.awt.*;
import model.Settler;
import model.Asteroid;
import model.Traveller;

/**
 * Az osztály felelőssége, hogy az általa mutatott telepes objektumhoz tartozó képernyő koordinátákat eltárolja,
 * és az általa mutatott telepes grafikus megjelenítésével kapcsolatos feladatokat végezze/menedzselje.
 *  A view.TravellerView leszármazottja, így a view.View interfészt ő is megvalósítja.
 */
public class SettlerView extends TravellerView {

    /**
     * Az osztály konstruktora, bemeneti paraméter az s, amit a mutatott model.Settler attribútumának ad értékül,
     * és defaulban hamis az active attribútuma. 
     * Meghívja az ős (view.TravellerView) konstruktorát. (az x y koordináta az Update hívással lesz inicializálva)
     * @param s a mutatott model.Settler objektum.
     */
    public SettlerView(Settler s, LevelView lv) {
        super(lv);
        settler = s;
    }

    /**
     * Értéke true, ha az általa mutatott objektum a jelenleg aktív telepes, különben false.
     */
    private boolean active;

    /**
     * A mutatott telepes objektum, akinek a rajzolásáért felel.
     */
    private Settler settler;

    /**
     * Négyzetet rajzol model.Settler-nek megfelelő módon (narancssárga) az örökölt x,y attribútumok szerinti koordinátákra.
     * Amennyiben az active attribútuma true, egy sárga keretet rajzol a négyzete köré.
     * @param g Graphics típusú objektum a rajzoláshoz.
     */
    @Override
    public void draw(Graphics g) {
    	//ez eleg fapados igy tudom, atirhatjuk de igy h nem fut a kod meg nem mertem borderrel------------------------------
    	if (active) {
    		g.setColor(new Color(233, 233, 13));
    		g.fillRect(x, y, 16, 16);
    		g.setColor(new Color(227, 164, 97));
    		g.fillRect(x+2 , y+2, 12, 12);
    	} else {
    		g.setColor(new Color(227, 164, 97));
    		g.fillRect(x, y, 16, 16);
    	}
    }

    /**
     * A getAsteroid metódussal elkéri a settler-től az aszteroidáját. 
     * Az örökölt levelView-tól elkéri az aszteroida view.View objektumát a getAsteroidView metódussal.
     * A kapott view.AsteroidView objektumra meghívja a getTravellerX és Y metódusokat magát adva paraméterül,
     * majd a kapott értékeket beírja az x és y attribútumaiba.
     */
    @Override
    public void Update() {
        Asteroid a = settler.getAsteroid();
        AsteroidView av = levelView.getAsteroidView(a);
        x = av.getTravellerX(this.settler);
        y = av.getTravellerY();
    }

    /**
     * Beállítja az active változót a paraméterül kapottra.
     * @param active az új active érték
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * A paraméterben megadott model.Traveller t objektummal összehasonlítja settlerrel,
     * és ha megegyeznek akkor true, ha nem, akkor false a visszatérési értéke.
     * @param t a model.Traveller, amivel összehasonlítjuk.
     * @return igaz vagy hamis aszerint, hogy a paraméterben megadott traveller megegyezik-e this objektummal.
     */
    @Override
    public boolean identify(Traveller t) {
        return t == settler;
    }

}