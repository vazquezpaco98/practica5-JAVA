package deepspace;

import java.util.ArrayList;

public class SpaceCity extends SpaceStation{
        private SpaceStation base;
        private ArrayList<SpaceStation> collaborators;

        SpaceCity(SpaceStation b, ArrayList<SpaceStation> col){
            super(b);
            base = b;
            collaborators = new ArrayList();
            for(SpaceStation s: col){
                collaborators.add(s);
            }
        }

        public ArrayList<SpaceStation> getCollaborators(){
            ArrayList<SpaceStation> sp = new ArrayList();

            for(SpaceStation s: collaborators){
                sp.add(s);
            }

            return sp;
        }

        public float fire(){
            float f = super.fire();

            for(SpaceStation s: collaborators){
                f += s.fire(); 
            }
            return f;
        }

        public float protection(){
            float f = super.protection();

            for(SpaceStation s: collaborators){
                f += s.protection();
            }
            return f;
        }

        public Transformation setLoot(Loot loot){
            System.out.println("Esto no se hace bien?");
            super.SetLoot(loot);
            System.out.println("Hace bien el return");
            return Transformation.NOTRANSFORM;
        }

}