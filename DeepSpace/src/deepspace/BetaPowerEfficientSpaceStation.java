package deepspace;

public class BetaPowerEfficientSpaceStation extends PowerEfficientSpaceStation{
    
    private Dice dice;
    private final static float EXTRAEFFICIENCY = 1.2f;
    

    BetaPowerEfficientSpaceStation(SpaceStation st){
        super(st);
        dice = new Dice();
    }

    @Override
    public float fire(){
        if (dice.extraEfficiency())
            return super.fire() * EXTRAEFFICIENCY;
        else return super.fire();
    }

    BetaPowerEfficientSpaceStationToUI getUIversion() {
        return new BetaPowerEfficientSpaceStationToUI(this);
    }


}