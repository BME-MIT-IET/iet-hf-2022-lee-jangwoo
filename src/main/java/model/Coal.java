package model;

/**
 * A szén nyersanyagot reprezentálja, a model.Mineral származik le.
 * Használható Robotok építésére,valamint még bővíthető további funkciókkal vagy attribútumokkal.
 * Kibányászható aszteroidákból Settlerilletve model.UFO által, valamint eltárolásra kerülhet egy
 * model.Settler hátizsákjában.
 */
public class Coal extends Mineral {

    /**
     * Default constructor
     */
    public Coal() {
        // No parameters to set in the constructor
    }
    
    /**
     * Az adott nyersanyag tipusát adja vissza Stringben. 
     * A mineralban lévőt írja felül.
     * @return a típus neve szövegként
     */
    @Override
    public String toString() { 
    	return "coal";
    }
}