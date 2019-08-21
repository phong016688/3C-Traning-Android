/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CollectionAndGenerics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author vo phong
 */
class MyGenerics<T> {

    private T x;

    public MyGenerics() {
    }

    public MyGenerics(T x) {
        this.x = x;
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }
}
abstract class Person{
    abstract void show();
}
class SinhVien extends Person{

    @Override
    void show() {
        System.out.println("class Sinh Vien");
    }
}
class GiangVien extends Person{

    @Override
    void show() {
        System.out.println("class Giang Vien");
    }
    
}
public class Generics {
    
    public static <T> void show(T[] t){
        for(T i : t){
            System.out.print(i + " ");
        }
        System.out.println("");
    } 
    public static void showClass(List<? extends Person> list){
        for(Person p : list){
            p.show();
        }
    }

    public static void main(String[] args) {
        //generics class
        MyGenerics<Integer> x1 = new MyGenerics<>(5);
        System.out.println(x1.getX());
        MyGenerics<String> x2 = new MyGenerics<>();
        x2.setX("abc");
        System.out.println(x2.getX());
        
        //generics method
        String s1[] = {"a", "b", "c"};
        show(s1);
        Integer arr[] = {1,2,3};
        show(arr);
        
        //? ký tự đại diện
        List<Person> list = new ArrayList<>();
        list.add(new SinhVien());
        list.add(new GiangVien());
        showClass(list);
        
        List<SinhVien> list2 = new ArrayList<>();
        list2.add(new SinhVien());
        showClass(list2);
        
        
    }
}
