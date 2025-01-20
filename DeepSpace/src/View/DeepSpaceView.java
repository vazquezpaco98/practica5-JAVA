
package View;

import ControllerText.Controller;
import deepspace.GameUniverse;
import java.util.ArrayList;

/**
 *
 * @author Profe
 */
public interface DeepSpaceView {
  public void updateView();
  public void showView();
  // Inputs
  public ArrayList<String> readNamePlayers();
  // Outputs
  public boolean confirmExitMessage();
  public void nextTurnNotAllowedMessage();
  public void lostCombatMessage();
  public void escapeMessage();
  public void wonCombatMessage();
  public void wonGameMessage();
  public void conversionMessage();
  public void noCombatMessage();
}