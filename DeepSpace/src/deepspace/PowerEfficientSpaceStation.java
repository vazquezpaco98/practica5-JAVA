package deepspace;

public class PowerEfficientSpaceStation extends SpaceStation{
    
    private final static float EFFICIENCYFACTOR = 1.1f;

    PowerEfficientSpaceStation(SpaceStation st){
        super(st);
    }

    public float fire(){
        return super.fire() * EFFICIENCYFACTOR;
    }

    public float protection(){
        return super.protection() * EFFICIENCYFACTOR;
    }
    
    PowerEfficientSpaceStationToUI getUIversion() {
        return new PowerEfficientSpaceStationToUI(this);
    }

    public Transformation setLoot(Loot loot){
        System.out.println("Esto no se hace bien?");
        super.SetLoot(loot);
        System.out.println("Hace bien el return en power");
        return Transformation.NOTRANSFORM;
    }
}