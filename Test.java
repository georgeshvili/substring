package substring;

import java.util.ArrayList;

public class Test extends Substring {

    public static void main(String[] args) {

        ArrayList<Integer> test;
        test = rabinKarp("ababaababaaba", "aaba");
        System.out.println("RabinKarp: "+test);
        test = boyerMoore("ababaababaaba", "aba");
        System.out.println("BoyerMoore: "+test);
        test = knuthMorrisPratt("ababaababaaba", "bab");
        System.out.println("KnuthMorrisPratt: "+test);
    }

}
