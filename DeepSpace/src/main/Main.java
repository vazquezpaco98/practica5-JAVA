/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import deepspace.GameUniverse;
import View.GUI.MainWindow;
import View.View;
import Controller.Controller;
/**
 *
 * @author paco
 */
public class Main {
    public static void main(String[] args) {
        GameUniverse model = new GameUniverse();
        View view = MainWindow.getInstance();
        Controller controller = Controller.getInstance();
        controller.setModelView(model,view);
        view.setController(controller);
        controller.start();
    }
}
