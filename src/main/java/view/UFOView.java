package view;

import java.awt.*;

import model.UFO;
import model.Asteroid;
import model.Traveller;

/**
 * Az osztály felelőssége, hogy az általa mutatott model.UFO objektumhoz tartozó képernyő koordinátákat eltárolja,
 * és az általa mutatott model.UFO grafikus megjelenítésével kapcsolatos feladatokat végezze/menedzselje.
 * A view.TravellerView leszármazottja, így a view.View interfészt ő is megvalósítja.
 */
public class UFOView extends TravellerView {

    /**
     * Az osztály konstruktora, bemeneti paraméter az u, amit a mutatott model.UFO attribútumának ad értékül.
     * Meghívja az ős (view.TravellerView) konstruktorát. (az x y koordináta az Update hívással lesz inicializálva)
     * @param u a mutatott model.Robot objektum.
     */
    public UFOView(UFO u, LevelView lv) {
        super(lv);
        ufo = u;
    }

    /**
     * A mutatott ufo objektum.
     */
    private UFO ufo;

    /**
     * A getAsteroid metódussal elkéri az ufo-tól az aszteroidáját. 
     * Az örökölt levelView-tól elkéri az aszteroida view.View objektumát a getAsteroidView metódussal.
     * A kapott view.AsteroidView objektumra meghívja a getTravellerX és Y metódusokat magát adva paraméterül,
     * majd a kapott értékeket beírja az x és y attribútumaiba.
     */
    @Override
    public void Update() {
        Asteroid a = ufo.getAsteroid();
        AsteroidView av = levelView.getAsteroidView(a);
        x = av.getTravellerX(ufo);
        y = av.getTravellerY(ufo);
    }

    /**
     * A paraméterben megadott model.Traveller t objektummal összehasonlítja ufo-val,
     * és ha megegyeznek akkor true, ha nem, akkor false a visszatérési értéke.
     * @param t a model.Traveller, amivel összehasonlítjuk.
     * @return igaz vagy hamis aszerint, hogy a paraméterben megadott traveller megegyezik-e this objektummal.
     */
    @Override
    public boolean identify(Traveller t) {
        return t == ufo;
    }

    /**
     * Négyzetet rajzol model.UFO-nak megfelelő módon (zöld) az örökölt x,y attribútumok szerinti koordinátákra.
     * @param g Graphics típusú objektum a rajzoláshoz.
     */
    @Override
    public void draw(Graphics g){
    	g.setColor(new Color(1, 255, 55));
        g.fillRect(x, y, 16, 16);
    }


}