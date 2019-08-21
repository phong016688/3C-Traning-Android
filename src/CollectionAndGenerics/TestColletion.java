/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CollectionAndGenerics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author vo phong
 */
public class TestColletion {
    public static void main(String[] args) {
        List<Integer> arrlist = new ArrayList<>();
        arrlist.add(1);
        arrlist.add(2);
        arrlist.add(3);
        arrlist.add(4);
        arrlist.add(5);
        System.out.println(arrlist);
        
        List<Integer> linkedlist = new LinkedList<>();
        linkedlist.add(1);
        linkedlist.add(2);
        linkedlist.add(3);
        linkedlist.add(4);
        linkedlist.add(5);
        System.out.println(linkedlist);
        
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        System.out.println(set);
        
    }
}
