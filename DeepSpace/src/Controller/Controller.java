/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import deepspace.*;
import View.*;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author paco
 */
public class Controller {
        private GameUniverse model;
        private View view;
        
        private static Controller instance = null;
        
        public static Controller getInstance(){
            if (instance == null){
                instance = new Controller();
            }
            return instance;
        }
        
        private Controller(){
            
        }
        
        public void setModelView(GameUniverse mod, View vie){
            model = mod;
            view = vie;
        }
        
        public void start(){
            model.init(view.getNames());
            //model.init(new ArrayList<>(Arrays.asList("Clo", "Marta", "Paco", "Markel", "Irene")));
            view.updateView();
            view.showView();
            // AQUI HAY TRABAJITO
        }
        
        public void finish(int i){
            if(view.confirmExitMessage() == true){
            System.exit(i);
            }
        }

        public GameState gState(){
            return model.getState();
        }
        
        public SpaceStationToUI getCurrentStation(){
            return model.getUIversion().getCurrentStation();
        }
        
        public EnemyToUI getCurrentEnemy(){
            return model.getUIversion().getCurrentEnemy();
        }

        public ArrayList<SpaceStationToUI> getStations(){
            return model.getStations();
        }

        public int getCurrentStationIndex(){
            return model.getCurrentStationIndex();
        }

        public CombatResult combat(){
            return model.combat();
        }

        public boolean NextTurn(){
           return model.nextTurn();
        }

        public void DescartarArmas(ArrayList<Integer> a){
           for(int i = a.size()-1; i >=0; i--){
               model.discardWeapon(i);
           }
        }

        public void DescartarEscudos(ArrayList<Integer> a){
            for(int i = a.size()-1; i >=0; i--){
                model.discardShieldBooster(a.get(i));
            }
         }

		public void MontarArmas(ArrayList<Integer> armas) {
            for(int i = armas.size()-1; i >=0; i--){
                model.mountWeapon(armas.get(i));
            }
		}

		public void MontarEscudos(ArrayList<Integer> escudos) {
            for(int i = escudos.size()-1; i >= 0; i--){
                model.mountShieldBooster(escudos.get(i));
            }
        }
        
        public boolean HaveAWinner(){
            return model.haveAWinner();
        }

		public void DescartarEscudosHangar(ArrayList<Integer> escudos) {
            for(int i = escudos.size()-1; i >= 0; i--){
                model.discardShieldBoosterInHangar(escudos.get(i));;
            }
		}

		public void DescartarArmasHangar(ArrayList<Integer> armas) {
            for(int i = armas.size()-1; i >=0; i--){
                model.discardWeaponInHangar(armas.get(i));
            }
        }
        
}
