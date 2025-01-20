/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GUI;

import View.View;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Controller.Controller;
/**
 *
 * @author paco
 */
//pruebas
import deepspace.*;

public class MainWindow extends javax.swing.JFrame implements View {

    private Controller controller;
    private static MainWindow instance = null;
    SpaceStationView estacion;
    EnemyView enemigo;
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        estacion = new SpaceStationView();
        enemigo = new EnemyView();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                Controller.getInstance().finish(0);
            }
        });
    }

    public static MainWindow getInstance() {
        if (instance == null) {
            instance = new MainWindow();
        }
        return instance;
    }

    @Override
    public ArrayList<String> getNames() {
        Bienvenida bien = new Bienvenida(this, true);
        int n = bien.getNum();
        bien.setVisible(false);
        NamesCapture namesCapt = new NamesCapture(this, true, n);
        return namesCapt.getNames();
    }

    

    public void updateView() {

        estacion.setStationView(controller.getCurrentStation());
        panelStation.removeAll();
        panelStation.add(estacion);

        enemigo.setEnemyView(controller.getCurrentEnemy());
        panelEnemy.removeAll();
        panelEnemy.add(enemigo);


        switch (controller.gState()) {
            case INIT:
                bNextTurn.setEnabled(false);
                bCombat.setEnabled(true);
                bEquipar.setEnabled(true);
                bDescartar.setEnabled(true);
                break;
            case BEFORECOMBAT:
                bEquipar.setEnabled(false);
                bDescartar.setEnabled(false);
                bNextTurn.setEnabled(false);
                bCombat.setEnabled(true);
                break;
            case AFTERCOMBAT:
                bEquipar.setEnabled(true);
                bDescartar.setEnabled(true);
                bCombat.setEnabled(false);
                bNextTurn.setEnabled(true);
                break;
        }

        
        panelResumen.removeAll();
        panelResumen.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""),
        "Tablero"));
        int i = 0;
        for (SpaceStationToUI s : controller.getStations()) {
            MiniStationView m = new MiniStationView();
            m.setMiniView(s);
                m.setOpaque(false);
            if (i == controller.getCurrentStationIndex())
                m.setOpaque(true);
            panelResumen.add(m);
            i++;
        }

        repaint();
        revalidate();
    }

    public void showView() {
        setVisible(true);
    }

    public void setController(Controller c) {
        controller = c;
    }

    public boolean confirmExitMessage() {
        return (JOptionPane.showConfirmDialog(this, "¿Estás segur@ que deseas salir?", "Deepspace",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }

    public void Descartar() {
        ArrayList<Integer> armas = estacion.getSelectedWeapons();
        controller.DescartarArmas(armas);

        ArrayList<Integer> escudos = estacion.getSelectedShields();
        controller.DescartarEscudos(escudos);

        DescartarDeHangar();
        updateView();
    }

    public void DescartarDeHangar() {
        ArrayList<Integer> armas = estacion.getHangarView().getSelectedWeapons();
        ArrayList<Integer> escudos = estacion.getHangarView().getSelectedShields();

        controller.DescartarArmasHangar(armas);
        controller.DescartarEscudosHangar(escudos);
    }

    public void Equipar() {
        if (estacion.getHangarView() != null) {
            ArrayList<Integer> armas = estacion.getHangarView().getSelectedWeapons();
            ArrayList<Integer> escudos = estacion.getHangarView().getSelectedShields();

            controller.MontarArmas(armas);
            controller.MontarEscudos(escudos);

            updateView();
        }
    }

    public void Combatir() {
        CombatResult c = controller.combat();
        ShowCombatResult(c);
        updateView();
    }

    public void MostrarCastigoPendiente() {
        JOptionPane.showMessageDialog(this,
                "Tienes un castigo pendiente. Cumplelo descartando elementos y te dejamos seguir",
                "Nuevo Mensaje Espacial", JOptionPane.INFORMATION_MESSAGE);

    }

    @Override
    public void ShowCombatResult(CombatResult c) {
        String out = "";

        switch (c) {
            case ENEMYWINS:
                out += "Lo sentimos. Has perdido el combate. Cumple tu castigo";
                break;
            case STATIONWINS:
                if (controller.HaveAWinner()) {
                    JOptionPane.showMessageDialog(this, "Enhorabuena, eres el campeón de la partida.",
                            "Nuevo Mensaje Espacial", JOptionPane.INFORMATION_MESSAGE);
                    controller.finish(0);
                }
                out += "Enhorabuena, has ganado el combate. Disfruta el Botín";
                break;
            case STATIONWINSANDCONVERTS:
                out += "Enhorabuena, has ganado el combate. Además, te has transformado.";
                break;
            case STATIONESCAPES:
                out += "Eres una gallina espacial muy rápida, has conseguido escapar.";
        }

        JOptionPane.showMessageDialog(this, out, "Nuevo Mensaje Espacial", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelStation = new javax.swing.JPanel();
        bSalir = new javax.swing.JButton();
        panelEnemy = new javax.swing.JPanel();
        bNextTurn = new javax.swing.JButton();
        bCombat = new javax.swing.JButton();
        bDescartar = new javax.swing.JButton();
        bEquipar = new javax.swing.JButton();
        panelResumen = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bSalir.setText("Salir");
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });

        bNextTurn.setText("SiguienteTurno");
        bNextTurn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNextTurnActionPerformed(evt);
            }
        });

        bCombat.setText("Combatir");
        bCombat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCombatActionPerformed(evt);
            }
        });

        bDescartar.setText("Descartar");
        bDescartar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDescartarActionPerformed(evt);
            }
        });

        bEquipar.setText("Equipar");
        bEquipar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEquiparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bDescartar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bEquipar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelStation, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bNextTurn, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                        .addComponent(bSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bCombat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelEnemy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelResumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelEnemy, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bCombat, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bNextTurn)
                    .addComponent(bSalir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelStation, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bDescartar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(577, 577, 577)
                        .addComponent(bEquipar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bEquiparActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_bEquiparActionPerformed
        // TODO add your handling code here:
        Equipar();
    }// GEN-LAST:event_bEquiparActionPerformed

    private void bDescartarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_bDescartarActionPerformed
        // TODO add your handling code here:
        Descartar();
    }// GEN-LAST:event_bDescartarActionPerformed

    private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_bSalirActionPerformed
        // TODO add your handling code here:
        controller.finish(0);
    }// GEN-LAST:event_bSalirActionPerformed

    private void bCombatActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_bCombatActionPerformed
        // TODO add your handling code here:
        Combatir();
    }// GEN-LAST:event_bCombatActionPerformed

    private void bNextTurnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_bNextTurnActionPerformed
        // TODO add your handling code here:
        if (!controller.NextTurn())
            MostrarCastigoPendiente();
        else
            updateView();
    }// GEN-LAST:event_bNextTurnActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCombat;
    private javax.swing.JButton bDescartar;
    private javax.swing.JButton bEquipar;
    private javax.swing.JButton bNextTurn;
    private javax.swing.JButton bSalir;
    private javax.swing.JPanel panelEnemy;
    private javax.swing.JPanel panelResumen;
    private javax.swing.JPanel panelStation;
    // End of variables declaration//GEN-END:variables
}
