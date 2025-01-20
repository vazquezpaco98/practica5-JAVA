/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author Profe
 */
public abstract class DamageToUI {
    private int nShields;

    DamageToUI(Damage d) {
        nShields=d.getNShields();
    }

    public int getNShields() {
        return nShields;
    }
    
    public abstract String getWeaponInfo();
   
    public static DamageToUI getPruebaNum(){
        return new NumericDamageToUI(new NumericDamage(2, 3));
    }
    public static DamageToUI getPrueba(){
        return new NumericDamageToUI(new NumericDamage(7, 2));
    }
}
