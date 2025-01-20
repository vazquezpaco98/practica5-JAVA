/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;
import java.util.Random;
/**
 *
 * @author paco
 */
public class Dice {
    private final float NHANGARSPROB,
NSHIELDSPROB,
NWEAPONSPROB,
FIRSTSHOTPROB,
EXTRAEFFICIENCY;
    private Random generator;

    public Dice() {
        this.NHANGARSPROB = 0.25f;
        this.NSHIELDSPROB = 0.25f;
        this.NWEAPONSPROB = 0.3f;
        this.FIRSTSHOTPROB = 0.5f;
        this.EXTRAEFFICIENCY = 0.8f;
        generator = new Random();
    }

    boolean extraEfficiency(){
        return generator.nextFloat() < EXTRAEFFICIENCY;
    }

    int initWithNHangars(){
        //float aux = generator.nextFloat();
        if(generator.nextFloat() < NHANGARSPROB){
            return 0;
        }else{return 1;}
    }

    int initWithNWeapons(){
        float aux = generator.nextFloat();
        if(aux < NWEAPONSPROB){
            return 1;
        }else if (aux < 2*NWEAPONSPROB )return 2;
        else return 3;
    }
    
    int initWithNShields(){
        if(generator.nextFloat() < NSHIELDSPROB){
            return 0;
        }else{return 1;}
    }
    
    int whoStarts(int nPlayers){
        return generator.nextInt(nPlayers);
    }
    
    GameCharacter firstShot(){
        if(generator.nextFloat() < FIRSTSHOTPROB){
            return GameCharacter.SPACESTATION;
        }else return GameCharacter.ENEMYSTARSHIP;
    }
    
    boolean spaceStationMoves(float speed){
            return (generator.nextFloat() < speed);
    }
    
    
}
