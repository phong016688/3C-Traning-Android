/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encapsulation;

/**
 *
 * @author vo phong
 */
public class MainClass {
    public static void main(String[] args) {
        A a = new A();
        
        //a.value = 5 err
        //a.name = "phong"
        a.setName("phong");
        a.setValue(5);
        
        System.out.println("name: " + a.getName() + " " + "value: " + a.getValue());
    }
}
