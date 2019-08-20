/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstraction.Inheritance;

/**
 *
 * @author vo phong
 */
public class MainTest {
    public static void main(String[] args) {
        Person sv = new SinhVien("10214", 7.4, "nguyen van a");
        SinhVien sv1 = (SinhVien) sv;
        sv1.printhongtin();
        sv1.printdiem();
    }
    
}
