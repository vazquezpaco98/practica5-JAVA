package deepspace;

import java.util.ArrayList;

public class NumericDamage extends Damage{
    private int nWeapons;

    NumericDamage(int nw, int ns){
        super(ns);
        nWeapons = nw;
    }

    public int getNWeapons() {
        return nWeapons;
    }

    @Override
    public NumericDamage copy(){
        return new NumericDamage(this.nWeapons, this.getNShields());
    }

    @Override
    public void discardWeapon(Weapon w) {
        if(nWeapons > 0)
            nWeapons--;
    }

    @Override
    public boolean hasNoEffect(){
        return (super.hasNoEffect() && nWeapons == 0);
    }
    
 
    public NumericDamage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s){
        return new NumericDamage(Math.min(w.size(), nWeapons), Math.min(getNShields(), s.size()));
    }

    NumericDamageToUI getUIversion(){
        return new NumericDamageToUI(this);
   }
}