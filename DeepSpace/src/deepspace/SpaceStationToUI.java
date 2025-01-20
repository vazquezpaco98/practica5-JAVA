/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;
//pruebas
import java.util.Arrays;
/**
 *
 * @author Profe
 */
public class SpaceStationToUI {
    private String name;
    private int nMedals;
    
    private float ammoPower=1.0f;
    private float fuelUnits=1.0f;
    private float shieldPower=1.0f;
    
    private ArrayList<WeaponToUI> weapons;
    private ArrayList<ShieldToUI> shieldBoosters;
    private HangarToUI hangar;   
    private DamageToUI pendingDamage;
    
    SpaceStationToUI(SpaceStation station) {
        weapons=new ArrayList<>();
        shieldBoosters=new ArrayList<>();         
        
        name=station.getName();
        nMedals=station.getNMedals();
        
        ammoPower=station.getAmmoPower();
        fuelUnits=station.getFuelUnits();
        shieldPower=station.getShieldPower();
        
        for (Weapon w:station.getWeapons()) {
            weapons.add(w.getUIversion());
        }
        
        for(ShieldBooster s:station.getShieldBoosters()) {
            shieldBoosters.add(s.getUIversion());
        }
        
        Hangar h=station.getHangar();
        if (h!=null) {
            hangar=h.getUIversion();
        }
        else {
            hangar=null;
        }
        
        // MIGUEL: Añadido de  pendingDamage  y su consultor
        
        Damage d = station.getPendingDamage();
        if (d != null) {
          pendingDamage = d.getUIversion();
        } else {
          pendingDamage = null;
        }
            
    }

    public String getName() {
        return name;
    }

    public int getnMedals() {
        return nMedals;
    }

    public float getAmmoPower() {
        return ammoPower;
    }

    public float getFuelUnits() {
        return fuelUnits;
    }

    public float getShieldPower() {
        return shieldPower;
    }

    public ArrayList<WeaponToUI> getWeapons() {
        return weapons;
    }

    public ArrayList<ShieldToUI> getShieldBoosters() {
        return shieldBoosters;
    }

    public HangarToUI getHangar() {
        return hangar;
    }
    
    public DamageToUI getPendingDamage() {
        return pendingDamage;
    }
    
    public static SpaceStationToUI getPrueba(){
        Hangar h = new Hangar(15);
        Weapon w = new Weapon("", WeaponType.LASER, 6);
        ShieldBooster sh = new ShieldBooster("", 4.0f, 4);
         SuppliesPackage s = new SuppliesPackage(3.0f, 3.0f, 5.0f);
        SpaceStation sp = new SpaceStation("Prueba", s);
        for(int i = 0; i < 7; i++){
            h.addWeapon(w);
            h.addShieldBooster(sh);  
        }
        
        
        
        sp.receiveHangar(h);
        
        
        
        for(int i = 0; i < 3; i++){
            sp.mountShieldBooster(0);
            sp.mountWeapon(0);
        }
        SpecificDamage d = new SpecificDamage(new ArrayList<>(Arrays.asList(WeaponType.LASER,  WeaponType.LASER, WeaponType.LASER)), 2);
       
       
        sp.setPendingDamage(d);
        
        return new SpaceStationToUI(sp);
    }

    public String Tipo(){
        return "Estacion";
    }

    public String getOriginal(){
        return getName();
      }
}
