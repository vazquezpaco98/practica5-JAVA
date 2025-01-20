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
class Loot {
    private int nSupplies, nWeapons, nShields, nHangars, nMedals;
    private boolean spaceCity, getEfficient;
    Loot(int nSupplies, int nWeapons, int nShields, int nHangars, int nMedals, boolean s, boolean get) {
        this.nSupplies = nSupplies;
        this.nWeapons = nWeapons;
        this.nShields = nShields;
        this.nHangars = nHangars;
        this.nMedals = nMedals;
        this.getEfficient = get;
        this.spaceCity = s;
    }

    public boolean getEfficient(){
        return getEfficient;
    }

    public boolean spaceCity(){
        return spaceCity;
    }
    
    public int getNSupplies() {
        return nSupplies;
    }

    public int getNWeapons() {
        return nWeapons;
    }

    public int getNShields() {
        return nShields;
    }

    public int getNHangars() {
        return nHangars;
    }

    public int getNMedals() {
        return nMedals;
    }
    
     LootToUI getUIversion() {
        return new LootToUI(this);
    }
     
      public String toString() {
        return "Loot{" + "nSupplies=" + nSupplies + ", nWeapons=" + nWeapons + ", nShields=" + nShields + ", nHangars=" + nHangars + ", nMedals=" + nMedals + '}';
    }
    
    
}
