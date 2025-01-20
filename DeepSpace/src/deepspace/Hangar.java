
package deepspace;
import java.util.ArrayList;

//TERMINADAAAA
/**
 *
 * @author paco
 */
public class Hangar implements Copyable <Hangar>{
    private int maxElements;
    private ArrayList<Weapon> weapons = new ArrayList();
    private ArrayList<ShieldBooster> shieldBoosters = new ArrayList();
    
    Hangar(int cap){
        maxElements = cap;
    }
    
    
    Hangar(Hangar h){
        maxElements = h.maxElements;
        weapons = h.getWeapons();
        shieldBoosters = h.getShieldBoosters();
    }
    
    HangarToUI getUIversion(){
        return new HangarToUI(this);
    }
    
    public boolean spaceAvailable(){
        return ((weapons.size() + shieldBoosters.size()) < maxElements);
    }
    
    public boolean addWeapon(Weapon w){
        if(spaceAvailable()){
            return weapons.add(w);
        }else
            return false;
    }
    
    public boolean addShieldBooster(ShieldBooster s){
        if(spaceAvailable()){
            return shieldBoosters.add(s);
        }else
            return false;
    }

    public int getMaxElements() {
        return maxElements;
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
    
      public Weapon removeWeapon(int w){
       if(w >= 0 && w < weapons.size())
         return weapons.remove(w);  
       
       return null;
    }
   
    public ShieldBooster removeShieldBooster(int s){
        if( s >= 0 && s < shieldBoosters.size())
            return shieldBoosters.remove(s);        
       return null;
    }

    @Override
    public Hangar copy() {
        return new Hangar(this);
    }
   
    
    public String toString(){
        return "maxElements: "+maxElements+"\nWeapons: "+ weapons + "\nShieldBoosters:"+ shieldBoosters;
    }
    
}
