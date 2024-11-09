/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.delimare.pmb.function;

import javax.swing.JOptionPane;

/**
 *
 * @author MyBook Z Series
 */
public class Alert {
    public static void info(String message) {
        JOptionPane.showMessageDialog(null, message, "Informasi", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void warning(String message) {
        JOptionPane.showMessageDialog(null, message, "Peringatan", JOptionPane.WARNING_MESSAGE);
    }
}
