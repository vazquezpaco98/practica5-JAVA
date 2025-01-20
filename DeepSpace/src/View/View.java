/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import java.util.ArrayList;
import Controller.Controller;
import deepspace.CombatResult;
/**
 *
 * @author paco
 */
public interface View {
                public boolean confirmExitMessage();
                public void updateView();
                public void showView();
                public ArrayList<String> getNames();
                public void setController(Controller c);
                public void ShowCombatResult(CombatResult c);
                public void Descartar();
                public void Equipar();
                public void Combatir();
                public void MostrarCastigoPendiente();
}
