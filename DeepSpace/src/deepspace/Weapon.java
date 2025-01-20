/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;


/**
 *
 * @author paco
 */
public class Weapon implements Copyable <Weapon>, CombatElement {
    private String name;
    private WeaponType type;
    private int uses;

    public Weapon(String name, WeaponType type, int uses) {
        this.name = name;
        this.type = type;
        this.uses = uses;
    }
    
    public Weapon(Weapon w){
        this.name = w.name;
        this.type = w.type;
        this.uses = w.uses;
    }

    public WeaponType getType() {
        return type;
    }

    public int getUses() {
        return uses;
    }
    
    public float power(){
        return type.getPower();
    }
    
    public float useIt(){
        if(uses > 0){
            uses -= 1; return power();
        }else{ return 1.0f;}
    }
    
     @Override
    public String toString() {
        return "Weapon{" + "name=" + name + ", type=" + type + ", uses=" + uses + '}';
    }
    
    WeaponToUI getUIversion(){
        return new WeaponToUI(this);
    }

    @Override
    public Weapon copy() {
        return new Weapon(this);
    }
    
    

}
