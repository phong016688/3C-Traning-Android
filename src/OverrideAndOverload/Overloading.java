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
class C {

    public int add(int a, int b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public String add(String s, int i) {
        return "chuoi: " + s + "  so: " + i;
    }

    public String add(int i, String s) {
        return "chuoi: " + s + "  so: " + i;
    }
}

public class Overloading {
    public static int fun(int ...x){
        int s=0;
        for(int i : x){
            s += i;
        }
        return s;
    }
    public static void main(String[] args) {
        C c = new C();
        System.out.println(c.add(1, 2) + "\t" +c.add(1,2,3) + "\t" 
        + c.add(1.5,2.4) + "\t" + c.add("abc", 5) + "\t" + 
                c.add(0, "123") + c.add('a','n','w') + "\t" + fun(1,4,5,7,6,8));
    }

}
