package view;

import model.*;
import model.Robot;
import model.Settler;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * 
 */
public class LevelView extends JPanel implements View {

    /**
     * Default constructor
     */
    public LevelView() {
    }

    /**
     * Random objektum.
     */
    private Random random = new Random();

    /**
     * Megmondja, hogy az adott nyersanyaghoz milyen sz�n tartozik.
     * @param m A nyersanyag
     * @return A sz�n, ami a nyersanyaghoz tartozik
     */

    public static Color mineralColor(Mineral m) {
        HashMap<String, Color> colors = new HashMap<>();
        colors.put("uranium(0)", uranium0Color);
        colors.put("uranium(1)", uranium1Color);
        colors.put("uranium(2)", uranium2Color);
        colors.put("iron", ironColor);
        colors.put("ice", iceColor);
        colors.put("coal", coalColor);
        if (m != null) {
            return colors.getOrDefault(m.toString(), new Color(27, 20, 100));
        }
        return new Color(27,20,100);
    }

    /**
    * A h�tt�ren elhelyezett nap bet�lt�se
    */
    ImageIcon img = new ImageIcon("sun.png"); //nap betoltese
    
    /**
     * Az ur�n 0. �llapot�nak sz�ne.
     */
    public static Color uranium0Color = new Color(15, 147, 71);

    /**
     * Az ur�n 1. �llapot�nak sz�ne.
     */
    public static Color uranium1Color = new Color(16, 105, 55);

    /**
     * Az ur�n 2. �llapot�nak sz�ne.
     */
    public static Color uranium2Color = new Color(2, 73, 34);

    /**
     * A j�g sz�ne.
     */
    public static Color iceColor = new Color(195, 255, 255);

    /**
     * A sz�n sz�ne.
     */
    public static Color coalColor = new Color(0, 0, 0);

    /**
     * A vas sz�ne.
     */
    public static Color ironColor = new Color(179, 179, 179);

    /**
     * Az akt�v telepes, az aki most l�p.
     */
    private transient Settler activeSettler;

    /**
     * A j�t�k, amelyik �ppen zajlik.
     */
    private transient Game game;

    /**
     * A telepesek n�zeteinek list�ja.
     */
    private transient ArrayList<SettlerView> settlerViews = new ArrayList<>();

    /**
     * Az utaz�k n�zeteinek list�ja.
     */
    private transient ArrayList<TravellerView> travellerViews = new ArrayList<>();

    /**
     * A fel�leten l�v� inventoryview.
     */
    private InventoryView inventory;

    /**
     * Az aszteroid�k �s a hozz�juk tartoz� n�zetek �sszerendel�se.
     */
    private transient HashMap<Asteroid, AsteroidView> asteroidViews = new HashMap<>();

    /**
     * Az teleportkapuk �s a hozz�juk tartoz� n�zetek �sszerendel�se.
     */
    private transient HashMap<Teleport, TeleportView> teleportViews = new HashMap<>();


    /**
     * A teleportkapuk �s a hozz�juk tartoz� teleportkapu sz�nek �sszerendel�se.
     */
    private transient HashMap<Teleport, Color> teleportcolors = new HashMap<>();

    /**
     * Konstruktor, a j�t�kot kell megadni.
     * @param game A j�t�k
     */
    LevelView(Game game){
        this.game = game;
    }

    /**
     * Setter a game attr�b�tumhoz
     * @param game Az �j j�t�k
     */
    public void setGame(Game game){
        this.game = game;
    }

    /**
     * V�gigiter�l az asteroidViews list�n (a HashMap-b�l
     * elk�ri a values-t), a jelenleg kiv�lasztott aszteroida n�zetnek elk�ri az aszteroid�j�t a
     * getAsteroid met�dussal, �s a koordin�t�it a getterekkel. Az aszteroid�t nevezz�k
     * mondjuk currAsteroid-nak. Egy bels� ciklusban v�gigiter�l, a list�ban ezut�n
     * k�vetkez� aszteroida n�zeteken, �s megh�vja r�juk az isThisYourNeighbour met�dust
     * a currAsteroid-ot param�ter�l adva. Ha igazzal t�r vissza, akkor elk�ri ezen
     * asteroidView koordin�t�it getterekkel, �s ezek k�z� vonalat h�z. Ha ezzel a bels�
     * ciklussal v�gzett, a teleportkapu n�zeteken (teleportViews) iter�l v�gig, ezekre
     * megh�vja az isThisYourNeighbour met�dust a currAsteroid-ot adva param�ter�l, �s ha
     * ez igazzal t�r vissza, akkor elk�ri a view.TeleportView koordin�t�it getterekkel, �s vonalat
     * rajzol k�z�j�k. Ezen met�dus v�gezt�vel az �sszes szomsz�ds�g vonal be van h�zva.
     */
    private void drawNeighbourLines(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.GRAY);
        for (AsteroidView av : asteroidViews.values()){
            if (av == null) continue;
            Asteroid a = av.getAsteroid();
            int x1 = av.getX();
            int y1 = av.getY();
            for (AsteroidView av2 : asteroidViews.values())
                if (av2.isThisYourNeighbour(a)){
                    int x2 = av2.getX();
                    int y2 = av2.getY();
                    g2d.drawLine(x1, y1, x2, y2);
                }
            for (TeleportView tv : teleportViews.values())
                if (tv != null && tv.isThisYourNeighbour(a)){
                    int x2 = tv.getX();
                    int y2 = tv.getY();
                    g2d.drawLine(x1, y1, x2, y2);
                }
        }
    }

    /**
     * Be�ll�tja az inventoryview-t.
     * @param inventory Az inventory �j �rt�ke
     */
    public void setInventory(InventoryView inventory) {
        this.inventory = inventory;
    }

    /**
     * A param�terk�nt kapott teleportot
     * kulcsk�nt haszn�lva elk�ri az ehhez tartoz� view.TeleportView-t a teleportViews
     * HashMapb�l, �s ezt visszaadja.
     * @param t A teleport, amihez kell a n�zet
     * @return A teleporthoz tartoz� n�zet
     */
    public TeleportView getTeleportView(Teleport t) {
        return teleportViews.get(t);
    }

    /**
     * A param�terk�nt kapott aszteroid�t
     * kulcsk�nt haszn�lva elk�ri az ehhez tartoz� view.AsteroidView-t az asteroidViews
     * HashMapb�l, �s ezt visszaadja.
     * @param a Az aszteroida, amihez kell a n�zet
     * @return Az aszteroid�hoz tartoz� n�zet
     */
    public AsteroidView getAsteroidView(Asteroid a) {
        return asteroidViews.get(a);
    }

    /**
     * H�vja mag�ra az updateAsteroidView, updateTeleportView,
     * updateSettlerView, updateTravellerView met�dusokat. A travellerViews list�ban
     * megh�vja mindenkire az Update met�dust.
     */
    public void Update() {
        updateAsteroidView();
        updateTeleportView();
        updateSettlerView();
        updateTravellerView();
        for (TravellerView tv : travellerViews)
            tv.Update();
        inventory.Update();
    }

    /**
     * Getter az inventory attrib�tumhoz.
     * @return Az inventory.
     */
    public InventoryView getInventory() {
        return inventory;
    }

    /**
     * V�gigiter�l a teleportViews list�n, �s
     * mindegyikre megh�vja az isPair met�dust. Ha valamelyik igazzal t�r vissza, elk�ri t�le
     * a sz�n�t a getColor met�dussal, �s l�trehoz egy view.TeleportView objektumot a
     * param�ter�l kapott teleporttal, �s a megkapott sz�nnel. Ha nem tal�l ilyet, akkor egy
     * randomiz�lt sz�nt ad meg a view.TeleportView-nek. Ezut�n ezt beteszi a teleportViews
     * HashMap-j�be a param�ter�l kapott teleportot haszn�lva kulcsk�nt.
     * @param t A teleportkapu, amihez kell a n�zet
     */
    private void addTeleportView(Teleport t) {
        Color color = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
        for (TeleportView tv : teleportViews.values())
            if (tv != null && tv.isPair(t)){
                color = tv.getColor();
                break;
            }
        if (teleportcolors.containsKey(t))
            color = teleportcolors.get(t);
        else if(t.getPair() != null && teleportcolors.containsKey(t.getPair()))
            color = teleportcolors.get(t.getPair());
        AsteroidView av = getAsteroidView(t.getNeighbour());
        TeleportView tv = new TeleportView(t, color, av.getX() +30, av.getY()+50);
        teleportViews.put(t, tv);
        teleportcolors.put(t, color);
        if (t.getPair() != null)
            teleportcolors.put(t.getPair(), color);
    }

    /**
     * Megadja, hogy a param�terk�nt adott teleportkapunak milyen sz�ne van.
     * @param t A k�rd�ses teleportkapu
     * @return A teleportkapu sz�ne
     */
    public Color getTeleportColor(Teleport t ){
        if (teleportcolors.containsKey(t))
            return teleportcolors.get(t);
        if (t.getPair() != null && teleportcolors.containsKey(t.getPair()))
            return teleportcolors.get(t.getPair());
        Color color = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
        teleportcolors.put(t, color);
        if (t.getPair() != null)
            teleportcolors.put(t.getPair(), color);
        return color;
    }

    /**
     * Megmondja, hogy ki az akt�v telepes.
     * @return Az akt�v telepes.
     */
    public Settler getActiveSettler() {
        return activeSettler;
    }

    /**
     * Be�ll�tja az akt�v telepes �rt�k�t.
     * @param s Az �j akt�v telepes.
     */
    public void setActiveSettler(Settler s) {
        for (SettlerView sv : settlerViews){
            if (sv.identify(activeSettler))
                sv.setActive(false);
            if (sv.identify(s))
                sv.setActive(true);
        }
        activeSettler = s;
        Update();
    }

    /**
     * H�vja a game getGates met�dus�t. A megkapott
     * teleportkapukat kulcsk�nt haszn�lva a teleportViews mapj�n kigy�jti az �l� teleportok
     * n�zet�t, ezekb�l csin�l egy �j HashMap-et �s �rt�k�l adja a teleportViews-nek. Ha
     * volt olyan teleport, melyet kulcsk�nt haszn�lva nem volt tal�lat, megh�vja r� az
     * addTeleportView met�dust. (A felrobbant teleportkapuk kiker�lnek a game gates
     * list�j�b�l. Ezzel az update-el kisz�rj�k a m�r felrobbant kapuk n�zet�t, hogy azokat
     * m�r ne �br�zoljuk)
     */
    private void updateTeleportView() {
        List<Teleport> gates = game.getGates();
        HashMap<Teleport, TeleportView> remainingViews = new HashMap<>();
        ArrayList<Teleport> noView = new ArrayList<>();
        for (Teleport t : gates) {
            if (!teleportViews.containsKey(t) && t.getNeighbour() != null)
                noView.add(t);
            else if (teleportViews.containsKey(t))
                remainingViews.put(t, teleportViews.get(t));
        }
        teleportViews = remainingViews;
        for (Teleport t : noView) {
            if (t != null)
                addTeleportView(t);
        }
    }

    /**
     * H�vja a game getSettlers, getUFOs �s getRobots
     * met�dus�t. A travellerViews list�n megh�vja az �sszes elemre az identify met�dust az
     * �sszes settler-, robot- �s model.UFO-val param�ter�l. Az egyszer is true-val visszat�r�
     * view.TravellerView objektumokb�l list�t k�sz�t�nk, �s ezt kivessz�k a travellerViews
     * list�b�l (csak hogy gyorsabb legyen a bej�r�s). Ha volt olyan robot, melyn�l egy
     * identify met�dus sem t�rt vissza true-val, akkor l�trehoz egy view.RobotView objektumot a
     * robottal param�terk�nt, �s ezt hozz�f�zi az �j view.TravellerView t�pus� list�hoz. Ha
     * v�gig�rt, akkor az �jonnan k�sz�tett view.TravellerView list�t �rt�k�l adja a
     * travellerViews-nek. (Azaz ha l�trej�tt �j robot, azaz eddig nem volt r� n�zet, akkor
     * csin�lunk neki, �s bef�zz�k a list�nkba, a meghal model.UFO, model.Settler �s Robotokat pedig
     * kisz�rj�k azzal, hogy az � view.View-jukat m�r nem tessz�k bele az �j list�ba. Az �jonnan
     * l�trehozott robot majd az Update met�dus v�g�n kap koordin�t�t.)
     */
    private void updateTravellerView() {
        List<Settler> settlers = game.getSettlers();
        List<UFO> UFOs = game.getUFOs();
        List<Robot> robots = game.getRobots();
        Set<Robot> notFoundRobot = new HashSet<>(robots);
        ArrayList<TravellerView> remainingViews = new ArrayList<>();
        for (TravellerView tv : travellerViews){
            boolean didIdentify = false;
            for (Settler s : settlers) {
                didIdentify = didIdentify || tv.identify(s);
            }
            for (UFO u : UFOs) {
                didIdentify = didIdentify || tv.identify(u);
            }
            for (Robot r : robots) {
                if (didIdentify) break;
                if(tv.identify(r)) {
                    notFoundRobot.remove(r);
                    didIdentify = true;
                }
            }
            if (didIdentify)
                remainingViews.add(tv);
        }
        travellerViews = remainingViews;
        for (Robot r : notFoundRobot)
            travellerViews.add(new RobotView(r, this));
    }

    /**
     * H�vja a game a getSettlers met�dus�t. V�gigiter�l a
     * settlerViews list�n, �s megh�vja mindegyikre az identify met�dust, minden kapott
     * Settlerrel. Ha a view.SettlerView objektum identify met�dusa egyszer is true-val t�r vissza,
     * akkor bef�zz�k egy �j view.SettlerView list�ba. A v�g�l elk�sz�lt �j view.SettlerView list�t
     * �rt�k�l adjuk a settlerViews attrib�tumnak. (A meghalt model.Settler-ek kiker�lnek a game
     * settlers list�j�b�l. Ezzel az update-el kisz�rj�k a m�r meghalt Settlerek n�zet�t, hogy
     * azokat m�r ne �br�zoljuk)
     */
    private void updateSettlerView() {
        List<Settler> settlers = game.getSettlers();
        ArrayList<SettlerView> remainingViews = new ArrayList<>();
        for (SettlerView sv : settlerViews){
            boolean didIdentify = false;
            for (Settler s : settlers){
                if (didIdentify) break;
                didIdentify = sv.identify(s);
            }
            if (didIdentify)
                remainingViews.add(sv);
        }
        settlerViews = remainingViews;
    }

    /**
     * A game-re megh�vjuk a getSun met�dust, majd a
     * kapott model.Sun-ra a getAsteroids met�dust. A kapott aszteroid�kb�l, �s n�zeteikb�l �j
     * HashMap-et k�sz�t�nk (az asteroidViews seg�ts�g�vel), majd ha v�gezt�nk, a kapott
     * HashMap-et �rt�k�l adjuk az asteroidViews attrib�tumnak.
     */
    private void updateAsteroidView() {
        Sun sun = game.getSun();
        List<Asteroid> asteroids = sun.getAsteroids();
        HashMap<Asteroid, AsteroidView> remaining = new HashMap<>();
        for (Asteroid a : asteroids)
            remaining.put(a, asteroidViews.get(a));
        asteroidViews = remaining;
    }

    /**
     * Hozz�ad egy �j asteroidview-t a megadott aszteroid�hoz, a megadott koordin�t�kkal.
     * @param a Az aszteroida
     * @param x Az x koordin�ta
     * @param y Az y koordin�ta
     */
    public void addAsteroidView(Asteroid a, int x, int y){
        asteroidViews.put(a, new AsteroidView(a, x, y));
    }


    /**
     * Hozz�ad egy �j teleportview-t a megadott teleportkapuhoz, a megadott koordin�t�kkal.
     * @param t A teleportkapu
     * @param x Az x koordin�ta
     * @param y Az y koordin�ta
     */
    public void addTeleportView(Teleport t, Color c, int x, int y){
        TeleportView tv = new TeleportView(t,c, x, y);
        teleportViews.put(t, tv);
        teleportcolors.put(t, c);
        if (t.getPair() != null)
            teleportcolors.put(t.getPair(), c);
    }

    /**
     * K�sz�t egy settlerview-t a megadott telepeshez.
     * @param s A telepes, amihez kell a n�zet.
     */
    public void addSettlerView(Settler s){
        SettlerView sv = new SettlerView(s, this);
        settlerViews.add(sv);
        travellerViews.add(sv);
    }

    /**
     * K�sz�t egy ufoview-t a megadott telepeshez.
     * @param ufo Az ufo, amihez kell a n�zet.
     */
    public void addUFOView(UFO ufo){
        travellerViews.add(new UFOView(ufo, this));
    }

    /**
     * V�gigk�rdezi a teleportview-kat �s az asteroidview-kat, hogy
     * melyikre t�rt�nt a kattint�s. Visszat�r azzal a
     * teleportkapuval/aszteroid�val, amelyik n�zet�re kattintottak.
     * Ha nem t�rt�nt egyikre sem kattint�s, akkor null-nal t�r vissza.
     * @param  x A kattint�s x koordin�t�ka.
     * @param  y A kattint�s y koordin�t�ja.
     */
    public INeighbour click(int x, int y) {
        for(TeleportView tv : teleportViews.values())
            if(tv != null && tv.clicked(x, y))

                    return tv.getTeleport();
        for (AsteroidView av : asteroidViews.values())
            if (av != null && av.clicked(x, y))
                return av.getAsteroid();
        return null;
    }

    /**
     * Kirajzolja a n�zeteket.
     * @param g  A Graphics objektum, amire a rajzol�s t�rt�nik.
     */
    public void draw(Graphics g) {
        g.setColor(new Color(27, 20, 100)); //hatterszin beallitasa
    	g.fillRect(0, 0, getWidth(), getHeight()); // hatterszin beallitasa
    	img.paintIcon(this, g, getWidth() - img.getIconWidth(), 0); //nap berajzolasa
        drawNeighbourLines(g);
        for (AsteroidView av : asteroidViews.values())
            av.draw(g);
        for (TeleportView teleportv : teleportViews.values())
            if (teleportv != null)
                teleportv.draw(g);
        for (TravellerView travellerv : travellerViews)
            travellerv.draw(g);
    }

    /**
     * �sb�l sz�rmaz� met�dus, kirajzolja a komponenst
     * a param�terk�nt megadott Graphics objektum.
     * @param g A Graphics objektum, amire a rajzol�s t�rt�nik.
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    /**
     * Getter az aszteroid�k �s n�zeteinek �sszerendel�seihez.
     * @return Az aszteroid�k �s a n�zetek hashmap-je.
     */
    public HashMap<Asteroid, AsteroidView> getAsteroidViews() {
        return asteroidViews;
    }

    /**
     * Getter az teleportkapuk �s n�zeteinek �sszerendel�seihez.
     * @return A teleportkapuk �s a n�zetek hashmap-je.
     */
    public HashMap<Teleport, TeleportView> getTeleportViews() {
        return teleportViews;
    }
}
