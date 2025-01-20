
package deepspace;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Damage {
     private int nShields;
     
     
      Damage(int ns){
          this.nShields = ns;
     }


    
     public String toString() {
        return "nShields: " + nShields;
    }
     
     
     
     public void discardShieldBooster(){
          if(nShields > 0)
               nShields--;
     }
     
     public boolean hasNoEffect(){
          return nShields == 0;
     }
     
     private int arrayContainsType(ArrayList<Weapon> wea, WeaponType t){
          for(int i = 0; i < wea.size(); i++){
               if (wea.get(i).getType() == t)
                    return i;
          }
          return -1;
     }

     public int getNShields() {
          return nShields;
     }

     abstract public Damage copy(); 
     
     abstract DamageToUI getUIversion();

     abstract void discardWeapon(Weapon w);

     abstract Damage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s);
}
