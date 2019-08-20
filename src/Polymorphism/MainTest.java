/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstraction.Polymorphism;

/**
 *
 * @author vo phong
 */
public class MainTest {
    public static void main(String[] args) {
        SinhVien sv = new SinhVien();
        sv.introduce();
        GiangVien gv = new GiangVien();
        gv.introduce();
    }
    
}
class Person {
    public void introduce(){
        System.out.println("I am person");   
    }
}
class SinhVien extends Person{

    @Override
    public void introduce() {
        System.out.println("i am student");
    }
 
}
class GiangVien extends Person{

    @Override
    public void introduce() {
        System.out.println("I am Teacher");
    }
    
}