
package deepspace;

import java.util.ArrayList;
import java.util.Collections;

public class SpecificDamage extends Damage{
    private ArrayList<WeaponType> weapons;

    SpecificDamage(ArrayList<WeaponType> w, int ns){
        super(ns);
        this.weapons = w;
   }

   public SpecificDamage copy(){
       return new SpecificDamage(getWeapons(), getNShields());
   }

   public ArrayList<WeaponType> getWeapons() {
    ArrayList<WeaponType> wea = new ArrayList();

    for (WeaponType w : weapons){
        wea.add(w);
    }

    return wea;
    }

    public SpecificDamage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s){
        ArrayList<WeaponType> aux = new ArrayList();
        ArrayList<WeaponType> res = new ArrayList();
        for(Weapon elem: w){
             aux.add(elem.getType());
        }
        
        WeaponType wt [] = WeaponType.values();
        
        for(int i = 0; i < wt.length; i++){
             int min = Math.min(Collections.frequency(aux, wt[i]), Collections.frequency(weapons, wt[i]));
             for(int j = 0; j < min; j++){
                  res.add(wt[i]);
             }
        }
        
        return new SpecificDamage(res, Math.min(getNShields(), s.size()));
    }

    @Override
    public void discardWeapon(Weapon w){
             weapons.remove(w.getType());
        }

 
    
    public boolean hasNoEffect(){
        return (super.hasNoEffect() && weapons.size() == 0);
    }

    SpecificDamageToUI getUIversion(){
        return new SpecificDamageToUI(this);
   }
}

