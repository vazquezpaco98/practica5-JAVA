package deepspace;
import java.util.ArrayList;

/**
 *
 * @author paco
 */
public class SpaceStation implements SpaceFighter{
    
    private final static int MAXFUEL= 100;
    private final static float SHIELDLOSSPERUNITSHOT = 0.1f;
    private float ammoPower, shieldPower, fuelUnits;
    private int nMedals;
    private final String name;
    private Damage pendingDamage;
    private final ArrayList<Weapon> weapons;
    private final ArrayList<ShieldBooster> shieldBoosters;
    private Hangar hangar;

    SpaceStation(final String n, final SuppliesPackage sup) {
        name = n;
        ammoPower = sup.getAmmoPower();
        shieldPower = sup.getShieldPower();
        assignFuelValue(sup.getFuelUnits());
        nMedals = 0;
        pendingDamage = null;
        weapons = new ArrayList();
        shieldBoosters = new ArrayList();
        hangar = null;
    }

    SpaceStation(final SpaceStation st){
        name = st.name;
        ammoPower = st.getAmmoPower();
        shieldPower = st.getShieldPower();
        assignFuelValue(st.fuelUnits);
        nMedals = st.nMedals;
        pendingDamage = st.getPendingDamage();
        hangar = st.getHangar();
        weapons = st.getWeapons();
        shieldBoosters =st.getShieldBoosters();
    }

    private void assignFuelValue(final float f) {
        float aux = Math.min(f, MAXFUEL);
        fuelUnits = Math.max(aux, 0.0f);
    }

    public void cleanPendingDamage() {
        if (pendingDamage.hasNoEffect())
            pendingDamage = null;
    }

    public void cleanUpMountedItems() {
        for (int i = weapons.size()-1; i >= 0; i--) {
            if (weapons.get(i).getUses() == 0)
                weapons.remove(i);
        }

        for (int i = shieldBoosters.size()-1; i >= 0; i--) {
            if (shieldBoosters.get(i).getUses() == 0)
                shieldBoosters.remove(i);
        }
    }

    public void discardWeapon(int i){
        if ( i < weapons.size() && i>=0){
            Weapon w = weapons.remove(i);
            //System.out.println("Llega a station de vdd");
            if (pendingDamage != null){
                pendingDamage.discardWeapon(w);
                cleanPendingDamage();
            }
        }
    }

    public void discardShieldBooster(int i){
        if (i < shieldBoosters.size() && i >= 0){
            ShieldBooster s = shieldBoosters.remove(i);
            if (pendingDamage != null){
                pendingDamage.discardShieldBooster();
                cleanPendingDamage();
            }
        }
    }

    public void discardHangar() {
        hangar = null;
    }

    public void discardShieldBoosterInHangar(final int i) {
        if (hangar != null) {
            hangar.removeShieldBooster(i);
        }
    }

    public void discardWeaponInHangar(final int i) {
        if (hangar != null)
            hangar.removeWeapon(i);
    }

    public float fire() {
        float factor = 1.0f;
        for (int i = 0; i < weapons.size(); i++) {
            factor *= weapons.get(i).useIt();
        }
        return ammoPower * factor;
    }

    public float getAmmoPower() {
        return ammoPower;
    }

    public float getShieldPower() {
        return shieldPower;
    }

    public float getFuelUnits() {
        return fuelUnits;
    }

    public int getNMedals() {
        return nMedals;
    }

    public String getName() {
        return name;
    }

    public Damage getPendingDamage() {
        if(pendingDamage == null){
            return null;
        }else{
            return pendingDamage.copy();
        }
    }

    public ArrayList<Weapon> getWeapons() {
        ArrayList<Weapon> wea = new ArrayList();
        for(Weapon w: weapons){
            wea.add(w);
        }
        return wea;
    }

    public ArrayList<ShieldBooster> getShieldBoosters() {
        ArrayList<ShieldBooster> sh = new ArrayList();

        for(ShieldBooster s : shieldBoosters){
            sh.add(s);
        }

        return sh;
    }

    public Hangar getHangar() {
       if(hangar == null){
           return null;
       }else
            return new Hangar(hangar);
    }

    SpaceStationToUI getUIversion() {
        return new SpaceStationToUI(this);
    }

    public float getSpeed() {
        return fuelUnits / MAXFUEL;
    }

    public void move() {
        fuelUnits -= getSpeed() * fuelUnits;
    }

    public void mountWeapon(final int i) {
        if (hangar != null) {
            final Weapon w = hangar.removeWeapon(i);
            if (w != null) {
                weapons.add(w);
            }
        }
    }

    public void mountShieldBooster(final int i) {
        if (hangar != null) {
            final ShieldBooster s = hangar.removeShieldBooster(i);
            if (s != null)
                shieldBoosters.add(s);
        }
    }

    public float protection(){
        float factor = 1.0f;

        for(ShieldBooster s:shieldBoosters){
            factor*=s.useIt();
        }

        return shieldPower*factor;
    }

    public boolean receiveWeapon(final Weapon w) {
        if (hangar != null) {
            return hangar.addWeapon(w);
        } else
            return false;
    }

    public boolean receiveShieldBooster(final ShieldBooster s) {
        if (hangar != null) {
            return hangar.addShieldBooster(s);
        } else
            return false;
    }

    public void receiveHangar(final Hangar h) {
        if (hangar == null) {
            hangar = h;
        }
    }

    public void receiveSupplies(final SuppliesPackage s) {
        this.ammoPower += s.getAmmoPower();
        this.fuelUnits += s.getFuelUnits();
        this.shieldPower += s.getShieldPower();
    }

    public ShotResult receiveShot(float shot){
        if(this.protection() >= shot){
            shieldPower-=SHIELDLOSSPERUNITSHOT*shot;
            shieldPower=Math.max(0.0f, shieldPower);
            return ShotResult.RESIST;
        }
        else{
            shieldPower=0.0f;
            return ShotResult.DONOTRESIST;
        }
    }

    public Transformation SetLoot(Loot loot){
        CardDealer dealer = CardDealer.getInstance();

        if(loot.getNHangars() > 0){
            Hangar h = dealer.nextHangar();
            receiveHangar(h);
        }

        for (int i = 0; i < loot.getNSupplies(); i++){
            SuppliesPackage sup = dealer.nextSuppliesPackage();
            receiveSupplies(sup);
        }

        for (int i = 0; i < loot.getNWeapons(); i++){
            Weapon w = dealer.nextWeapon();
            boolean receiveWeapon = receiveWeapon(w);
        }

        for (int i = 0; i < loot.getNShields(); i++){
            ShieldBooster s = dealer.nextShieldBooster();
            boolean receiveShieldBooster = receiveShieldBooster(s);
        }

        nMedals += loot.getNMedals();
        if(loot.getEfficient()){
            return Transformation.GETEFFICIENT;
        }else{
            if (loot.spaceCity()){
                return Transformation.SPACECITY;
            }else return Transformation.NOTRANSFORM;
        }
    }

    public void setPendingDamage(final Damage d) {
         pendingDamage = d.adjust(weapons, shieldBoosters);
    }
   
         
    public boolean validState(){
        //System.out.println(pendingDamage);
        return (pendingDamage == null || pendingDamage.hasNoEffect());
    }
    
   public String toString(){
       return "name: "+name + " AmmoPower: "+ammoPower + " shieldPower: "+ shieldPower + " fuelUnits " + fuelUnits + " nMedals: " + nMedals +"\nHangar:"+hangar+ " \npendingDamage:" + pendingDamage + " \nShieldBoosters: " + shieldBoosters + "\nWeapons:" + weapons + "\n";
   }
    
    
}
