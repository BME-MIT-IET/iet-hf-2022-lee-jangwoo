package view;

import java.awt.*;

import model.Robot;
import model.Traveller;
import model.Asteroid;

/**
 * Az osztály felelőssége, hogy az általa mutatott robot objektumhoz tartozó képernyő koordinátákat eltárolja,
 * és az általa mutatott robot grafikus megjelenítésével kapcsolatos feladatokat végezze/menedzselje.
 * A view.TravellerView leszármazottja, így a view.View interfészt ő is megvalósítja.
 */
public class RobotView extends TravellerView {

    /**
     * Az osztály konstruktora, bemeneti paraméter az r, amit a mutatott model.Robot attribútumának ad értékül.
     * Meghívja az ős (view.TravellerView) konstruktorát. (az x y koordináta az Update hívással lesz inicializálva)
     * @param r a mutatott model.Robot objektum.
     */
    public RobotView(Robot r, LevelView lv) {
        super(lv);
        robot = r;
    }

    /**
     * A mutatott robot objektum, akinek a rajzolásáért felel.
     */
    private Robot robot;

    /**
     * Négyzetet rajzol model.Robot-nak megfelelő módon (szürke) az örökölt x,y attribútumok szerinti koordinátákra.
     * @param g Graphics típusú objektum a rajzoláshoz.
     */
    @Override
    public void draw(Graphics g) {
    	g.setColor(new Color(102, 102, 102));
        g.fillRect(x, y, 16, 16);
    }

    /**
     * A getAsteroid metódussal elkéri a robot-tól az aszteroidáját. 
     * Az örökölt levelView-tól elkéri az aszteroida view.View objektumát a getAsteroidView metódussal.
     * A kapott view.AsteroidView objektumra meghívja a getTravellerX és Y metódusokat magát adva paraméterül,
     * majd a kapott értékeket beírja az x és y attribútumaiba.
     */
    @Override
    public void Update() {
        Asteroid a = robot.getAsteroid();
        AsteroidView av = levelView.getAsteroidView(a);
        x = av.getTravellerX(this.robot);
        y = av.getTravellerY(this.robot);
    }

    /**
     * A paraméterben megadott model.Traveller t objektummal összehasonlítja robot-tal,
     * és ha megegyeznek akkor true, ha nem, akkor false a visszatérési értéke.
     * @param t a model.Traveller, amivel összehasonlítjuk.
     * @return igaz vagy hamis aszerint, hogy a paraméterben megadott traveller megegyezik-e this objektummal.
     */
    @Override
    public boolean identify(Traveller t) {
        return t == robot;
    }

}