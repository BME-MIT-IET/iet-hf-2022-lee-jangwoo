package model;

/**
 * A model.Traveller leszármazottja. Képes bányászni és mozogni. Felelõssége csinálnia ezek közül
 * valamit körönként. Meg kell halnia, ha napszél éri, vagy ha felrobban az aszteroidája.
 *
 */
public class UFO extends Traveller {
	
	/**
     * Konstruktor amely a traveller ősre meghívja a konstruktort
     * @param a az aszteroida ahol az objektum lesz.
     * @param g a játék, amelyben az ufó van.
     */
    public UFO(Asteroid a, Game g) {
        super(a, g);
    }

    /**
     * Az model.UFO-t radioaktív robbanás éri, amitõl meghal.
     */
    @Override
    public void hitByBlast() {
        die();
    }

    /**
     * Az model.UFO meghal. Ekkor eltávolítjuk az aszteroida és a game tárolóiból.
     */
    @Override
    public void die() {
        asteroid.removeTraveller(this);
        game.removeUFO(this);
    }

    /**
     * makeAction függvény: vagy mozog, vagypedig bányászik az model.UFO.
     * ha a generált random igaz, akkor bányászik, egyébként pedig 
     * mozog az aszteroidájának egy random szomszédjára.
     */
    public void makeAction(){
        boolean randDecision = Game.rand.nextBoolean();
    	if(randDecision) {
    		mine();
    	} else {
            int randNeighbour = Game.rand.nextInt(asteroid.getNeighbourCount());
    		move(randNeighbour);
    	}

    }

    /**
     * Az model.UFO kiüríti az aszteroidát, amin tartózkodik, és eldobja annak magját eltárolás nélkül.
     */
    public void mine(){
        asteroid.onMine();
    }
}
