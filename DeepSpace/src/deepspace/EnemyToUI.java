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
public class EnemyToUI {
    private final String name;
    private final float ammoPower;
    private final float shieldPower;
    
    private LootToUI loot;
    private DamageToUI damage;   
    
    EnemyToUI(EnemyStarShip enemy) {
        name=enemy.getName();
        ammoPower=enemy.getAmmoPower();
        shieldPower=enemy.getShieldPower();
    
        loot=enemy.getLoot().getUIversion();
        damage = enemy.getDamage().getUIversion();
    }

    public String getName() {
        return name;
    }

    public float getAmmoPower() {
        return ammoPower;
    }

    public float getShieldPower() {
        return shieldPower;
    }

    public LootToUI getLoot() {
        return loot;
    }

    public DamageToUI getDamage() {
        return damage;
    }
    
    public static EnemyToUI getPrueba(){
        EnemyToUI e = new EnemyToUI(new EnemyStarShip("Prueba", 3.0f, 4.0f, new Loot(1, 2, 3, 4, 5, false, true), new NumericDamage(5, 3)));
      
        return e;
    }
    
}
