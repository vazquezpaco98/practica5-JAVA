
package deepspace;


/**
 *
 * @author paco
 */


public class EnemyStarShip implements Copyable <EnemyStarShip>, SpaceFighter{
    private float ammoPower, shieldPower;
    private String name;
    private Loot loot;                      //preguntar por la visibilidad cuando es la flecha asi.
    private Damage damage;
    
    EnemyStarShip( String n, float a, float s, Loot l, Damage d){
        name = n;
        ammoPower = a;
        shieldPower = s;
        loot = l;
        damage = d;
    }
    
    EnemyStarShip(EnemyStarShip e){
        this.ammoPower = e.ammoPower;
        this.shieldPower = e.shieldPower;
        this.name = e.name;
        this.loot = e.loot;
        this.damage = e.damage;             //preguntar xq victor lo hace con copy
    }
    
    EnemyToUI getUIversion() {
        return new EnemyToUI(this);
    }

    public float getAmmoPower() {
        return ammoPower;
    }

    public float getShieldPower() {
        return shieldPower;
    }

    public String getName() {
        return name;
    }

    public Loot getLoot() {
        return loot;
    }

    public Damage getDamage() {
        return damage;
    }
    
    public float fire(){
        return ammoPower;  
    }
    
    public float protection(){
        return shieldPower;
    }
    
    public ShotResult receiveShot(float s){
        if (s <= shieldPower)
            return ShotResult.RESIST;
        else
            return ShotResult.DONOTRESIST;
    }

    @Override
    public EnemyStarShip copy() {
        return new EnemyStarShip(this);
    }
    
    
    public String toString() {
        return "EnemyStarShip{" + "ammoPower=" + ammoPower + ", shieldPower=" + shieldPower + ", name=" + name + ", loot=" + loot + ", damage=" + damage + '}';
    }
    
    
}
