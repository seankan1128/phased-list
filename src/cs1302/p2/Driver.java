package cs1302.p2;

import java.util.Arrays;

import cs1302.adt.StringList;
import cs1302.adt.FancyStringList;


/**
* This is a Test driver for the list.
*/
public class Driver {

    public static void main(String[] args) {
        StringList sl = new LinkedStringList();
          
        sl.add(0,"Three");
        sl.add(0,"Two");
        sl.add(0,"One");

        System.out.println(sl);
        
        FancyStringList fsl = new LinkedStringList();

        fsl.add(0,"a");
        fsl.add(1,"b");
        fsl.add(2,"c");

        fsl.add(2, sl);

        System.out.println(fsl.toString());
        System.out.println(sl);
        
        fsl.append(sl);

        System.out.println(fsl.toString());

        fsl.prepend(sl);

        System.out.println(fsl.toString());

        System.out.println(fsl.contains(1,"One"));
        System.out.println(fsl.contains(0,"B"));

        System.out.println(fsl.indexOf(1,"One"));

        FancyStringList fsl2 = new ArrayStringList(fsl);
        System.out.println(fsl2);
        System.out.println(fsl2.size());

        System.out.println(fsl2.slice(0,12,4));
        System.out.println(fsl2.reverse());

        FancyStringList fsl2 = new ArrayStringList(fsl2);
        System.out.println(fsl2);

    } // main

}