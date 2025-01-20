/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;

/**
 *
 * @author Profe
 */
public class HangarToUI {
    private int maxElements;
    private ArrayList<WeaponToUI> weapons;
    private ArrayList<ShieldToUI> shieldBoosters;   
    
    
    
    HangarToUI(Hangar h) {
        weapons=new ArrayList<>();
        shieldBoosters=new ArrayList<>(); 
        
        maxElements=h.getMaxElements();
        
        for (Weapon w:h.getWeapons()) {
            weapons.add(w.getUIversion());
        }
        
        for(ShieldBooster s:h.getShieldBoosters()) {
            shieldBoosters.add(s.getUIversion());
        }
    }
    
    public static HangarToUI getPrueba(){
        Weapon w1 = new Weapon("", WeaponType.LASER, 3);
        Weapon w2 = new Weapon("", WeaponType.PLASMA, 5);
        
        ShieldBooster s1 = new ShieldBooster("", 4.0f, 5);
        
        ShieldBooster s2 = new ShieldBooster("", 7.0f, 5);
        
        Hangar h=new Hangar(5);
        
        h.addShieldBooster(s2);
        h.addShieldBooster(s2);
        h.addShieldBooster(s1);
        h.addWeapon(w2);
        h.addWeapon(w2);
        h.addWeapon(w2);
        h.addWeapon(w1);
       
              
       return new HangarToUI(h);
    }

    public int getMaxElements() {
        return maxElements;
    }

    public ArrayList<WeaponToUI> getWeapons() {
        return weapons;
    }

    public ArrayList<ShieldToUI> getShieldBoosters() {
        return shieldBoosters;
    }
    
    
}
