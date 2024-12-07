/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.delimare.pmb.function;

/**
 *
 * @author MyBook Z Series
 */
public class Logger {
    public static void error(Object o, String msg) {
        System.err.println("[" + o.getClass().getPackage()+ "." + o.getClass().getSimpleName() + "] ERROR: " + msg);
    }
    public static void error(Class c, String msg) {
        System.err.println("[" + c.getPackage().getName()+ "." + c.getSimpleName() + "] ERROR: " + msg);
    }
}
