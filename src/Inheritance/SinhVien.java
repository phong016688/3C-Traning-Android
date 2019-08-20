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
public class SinhVien extends Person implements 
        XuLiDiem,Thongtinsinhvien{
    private String mssv;
    private double diem;

    public SinhVien(String mssv, double diem, String ten) {
        super(ten);
        this.mssv = mssv;
        this.diem = diem;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    @Override
    public void printdiem() {
        System.out.println("diem: " + this.diem);
    }

    @Override
    public void printhongtin() {
        System.out.println("name: " + this.getTen()+"  "  + "mssv: " + this.mssv);
    }
    
}
