
package deepspace;
import java.util.ArrayList;
/**
 *
 * @author paco
 */
//TERMINADA EXCEPTO DUDAS.
public class GameUniverse {
    
     private static int WIN = 5;
     private int currentStationIndex;
     private int turns;
     private final GameStateController gameState;
     private final Dice dice;
     private EnemyStarShip currentEnemy; 
     private SpaceStation currentStation;
     private ArrayList<SpaceStation> spaceStations;
     private boolean haveSpaceCity;
     
     public GameUniverse(){
               gameState = new GameStateController();
               turns = 0;
               dice = new Dice();
               haveSpaceCity = false;
     }
     
    public boolean haveAWinner(){
          return WIN <= currentStation.getNMedals();

     }
     
      public void discardHangar() {
       if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
        currentStation.discardHangar();
    }
    
     /*
    public void discardWeapon(int i) {
       if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
        currentStation.discardWeapon(i);
    }
    
    public void discardShieldBooster(int i) {
       if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
        currentStation.discardShieldBooster(i);
    }
    */

    public void init(final ArrayList<String> names){
        if (gameState.getState() == GameState.CANNOTPLAY){
            spaceStations = new ArrayList();
            final CardDealer dealer = CardDealer.getInstance();
            for (final String n: names){
                final SuppliesPackage supplies = dealer.nextSuppliesPackage();

                final SpaceStation station = new SpaceStation(n, supplies);
                spaceStations.add(station);

                final int nh = dice.initWithNHangars(), nw = dice.initWithNWeapons(),  ns = dice.initWithNShields();
                final Loot lo = new Loot(0, nw, ns, nh, 0, false, false);

                station.SetLoot(lo);
            }

            currentStationIndex = dice.whoStarts(names.size());

            currentStation = spaceStations.get(currentStationIndex);

            currentEnemy = dealer.nextEnemy();

            gameState.next(turns, spaceStations.size());
        }
    }

    public boolean nextTurn(){
        if (gameState.getState() == GameState.AFTERCOMBAT){
            if(currentStation.validState()){
                currentStationIndex = (currentStationIndex+1)%spaceStations.size();
                turns++;

                currentStation = spaceStations.get(currentStationIndex);

                currentStation.cleanUpMountedItems();

                final CardDealer dealer = CardDealer.getInstance();

                currentEnemy = dealer.nextEnemy();

                gameState.next(turns, spaceStations.size());

                return true;
            }
            return false;
        }
        return false;
    }

    public CombatResult combat(){
        CombatResult res;
        GameState state = gameState.getState();
        if (state == GameState.BEFORECOMBAT || state == GameState.INIT){
            res = combat(currentStation, currentEnemy);
            
            return res;
        }else
            return CombatResult.NOCOMBAT;
    }

    public CombatResult combat(final SpaceStation station, final EnemyStarShip enemy){
        CombatResult res;

        GameCharacter ch = dice.firstShot();
        boolean enemyWins;
        if (ch == GameCharacter.ENEMYSTARSHIP){
            float fire = enemy.fire();
            ShotResult result = station.receiveShot(fire);
            
            if (result == ShotResult.RESIST){
                fire = station.fire();
                result = enemy.receiveShot(fire);
                enemyWins = (result == ShotResult.RESIST);
            }else{
                enemyWins = true;
            }
        }else{
            float fire = station.fire();
            ShotResult result = enemy.receiveShot(fire);
            enemyWins = (result == ShotResult.RESIST);
        }

        if (enemyWins){
            float s = station.getSpeed();
            
            boolean moves = dice.spaceStationMoves(s);

            if(!moves){
                Damage dam = enemy.getDamage();
                station.setPendingDamage(dam);
                res = CombatResult.ENEMYWINS;
            }else{
                station.move();
                res = CombatResult.STATIONESCAPES;
            }
        }else{
            Loot aLoot = enemy.getLoot();
            Transformation r = station.SetLoot(aLoot);
            if (r == Transformation.GETEFFICIENT){
                makeStationEfficient();
                res = CombatResult.STATIONWINSANDCONVERTS;
            }else if (r == Transformation.SPACECITY){
                createSpaceCity();
                res = CombatResult.STATIONWINSANDCONVERTS;
            }else
                res = CombatResult.STATIONWINS;
        }
        gameState.next(turns, spaceStations.size());
        return res;
    }

    private void makeStationEfficient(){
        if (dice.extraEfficiency())
            currentStation = new BetaPowerEfficientSpaceStation(currentStation);
        else 
            currentStation = new PowerEfficientSpaceStation(currentStation);

        spaceStations.set(currentStationIndex, currentStation);
    }

    private void createSpaceCity(){
            if (!haveSpaceCity){
                ArrayList<SpaceStation> aux = new ArrayList(spaceStations);

                aux.remove(currentStation);

                currentStation = new SpaceCity(currentStation, aux);

                spaceStations.set(currentStationIndex, currentStation);

                haveSpaceCity = true;
            }
    }
    
    
    public void discardShieldBoosterInHangar(final int i) {
       if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
        currentStation.discardShieldBoosterInHangar(i);
    }
    
    public void discardWeaponInHangar(final int i) {
       if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
        currentStation.discardWeaponInHangar(i);
    }

    public void discardWeapon(final int i){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
        currentStation.discardWeapon(i);
    }

    public void discardShieldBooster(final int i) {
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
         currentStation.discardShieldBooster(i);
    }
    
    public void mountWeapon(final int i) {
       if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
        currentStation.mountWeapon(i);
    }
    
    public void mountShieldBooster(final int i) {
       if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
        currentStation.mountShieldBooster(i);
    }
    
     public GameUniverseToUI getUIversion() {
        return new GameUniverseToUI(currentStation, currentEnemy);
    }

    public ArrayList<SpaceStationToUI> getStations(){
        ArrayList<SpaceStationToUI> sp = new ArrayList();
        for(SpaceStation s: spaceStations){
            sp.add(s.getUIversion());
        }
        return sp;
    }
     
    public int getCurrentStationIndex(){
        return currentStationIndex;
    }

     public GameState getState() {
          return gameState.getState();
     }


}
