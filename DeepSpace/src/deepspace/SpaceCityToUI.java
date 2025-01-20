/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;

/**
 *
 * @author Profe
 */
public class SpaceCityToUI extends SpaceStationToUI {

  // MIGUEL: He añadido este atributo para que el ToUI sea completo
  // Modificado el constructor y añadido un consultor
    
  ArrayList<SpaceStationToUI> collaborators = new ArrayList<>();
  
  SpaceCityToUI (SpaceCity city) {
    super (city);
    for (SpaceStation s : city.getCollaborators()) {
        collaborators.add (new SpaceStationToUI(s));
    }
  }
  
  @Override
  public String getName () {
    return super.getName()+" (CIUDAD ESPACIAL)";
  }

  public String getOriginal(){
    return super.getName();
  }
  
  public ArrayList<SpaceStationToUI> getCollaborators() {
      return collaborators;
  }

  public String Tipo(){
    return "Eficiente";
  }
}
