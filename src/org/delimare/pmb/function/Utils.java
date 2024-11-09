/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.delimare.pmb.function;

import javax.swing.JFrame;

/**
 *
 * @author MyBook Z Series
 */
public class Utils {
    public static void openFrame(JFrame source, JFrame target, boolean closeParent) {
        target.setVisible(true);
        if (closeParent) source.dispose();
    }
}
