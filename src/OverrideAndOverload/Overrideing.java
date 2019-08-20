/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstraction.OverrideAndOverload;

/**
 *
 * @author vo phong
 */
class A{
private int a;
    public A() {
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
    public void getnameclass(){
        System.out.println("this is class A");
    }
    public void print(){
        
    }
}
class B extends A{

    public B() {
        super();
    }

    @Override
    public void getnameclass() {
        System.out.println("this is class B");
    }
    
}
public class Overrideing{    
    public static void main(String[] args) {
        B b = new B();
        b.getnameclass();
    }
}
