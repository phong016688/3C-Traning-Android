/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstraction;

/**
 *
 * @author vo phong
 */
public class Abstraction {

    // [pham vi truy cap] [abstract][kieu tra ve] [ten ham] (doi so truyen vao)
    public static void main(String[] args) {
        Animal dog = new Dog();
        dog.printtype();
        dog.run();
        
        Abc abc = new Abc();
        abc.printtype();
        abc.run();
    }
    
}

