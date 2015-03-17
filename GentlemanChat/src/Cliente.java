/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Esteban Gerpe
 */
public class Cliente {
    
    public static void main(String[] args) {
        
        JFrameApp frame = new JFrameApp();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        JPanel_app panel = new JPanel_app();
        frame.setContentPane(panel);
        frame.setVisible(true);
        
    }
    
}
