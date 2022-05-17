package model;

/**
 * Interf�szt biztos�t az Aszteroida �s model.Teleport oszt�lyoknak. Ezzel val�sul meg a telepes vagy robot
 * mozgat�sa aszteroid�r�l aszteroid�ra ak�r teleporton kereszt�l, vagy an�lk�l. Ennek seg�ts�g�vel van ki�p�tve az 
 * aszteroid�k �s teleportkapuk szomsz�ds�gi h�l�ja. Seg�ts�g�vel el lehet t�vol�tani szomsz�dokat. Tudja napsz�l �rni.
 */
public interface INeighbour {

    /**
     * �thelyezi a traveller-t egy m�sik aszteroid�ra, kifejt�se az egyes interf�sz megval�s�t�sokn�l.
     * @param traveller - az �thelyezend? traveller
     */
    void placeTraveller(Traveller traveller);

    /**
     * Egy szomsz�d megsz�n�s�r�l �rtes�t, kifejt�se az egyes interf�sz megval�s�t�sokn�l.
     * @param neighbour a megsz?n? neighbour	
     */
    void removeNeighbour(INeighbour neighbour);
    
    /**
     * A napsz�lr�l �rtes�tik ezzel egym�st a megval�s�tott interf�szek.
     * @param i napsz�l m�lys�ge (hogy mekkora ter�letet �r majd el)
     */
    void solarWind(int i);

    /**
     * A teleport mozgat�s��rt felel�s met�dus
     * @param t a mozgatand� teleport
     * @return a mozgat�s sikeress�ge
     */
    boolean moveTeleport(Teleport t);
}