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
class ShieldBooster implements Copyable <ShieldBooster> , CombatElement{
    private String name;
    private float boost;
    private int uses;

    ShieldBooster(String name, float boost, int uses) {
        this.name = name;
        this.boost = boost;
        this.uses = uses;
    }
    
    ShieldBooster(ShieldBooster s){
        this.name = s.name;
        this.boost = s.boost;
        this.uses = s.uses;
    }



    public float getBoost() {
        return boost;
    }

    public int getUses() {
        return uses;
    }
    
    public float useIt(){
        if(uses > 0){
            uses -= 1; return boost;
        }else
            return 1.0f;
    }
    
      public String toString() {
        return "ShieldBooster{" + "name=" + name + ", boost=" + boost + ", uses=" + uses + '}';
    }
      
      ShieldToUI getUIversion(){
        return new ShieldToUI(this);
    }

    @Override
    public ShieldBooster copy() {
        return new ShieldBooster(this);
    }
    
}
