package substring;

import java.util.ArrayList;
import java.util.Scanner;

public class Substring {

    public static ArrayList<Integer> rabinKarp (String T, String P){

        ArrayList<Integer> result = new ArrayList<>();
        if(P.length() == 0)
            return result;

        int n = T.length();
        int m = P.length();
        int q = Integer.MAX_VALUE;
        Scanner in = new Scanner(System.in);
        System.out.print("Input d for RabinKarp: ");
        int d = in.nextInt();
        int dm = (int)Math.pow(d,m-1) % q;
        int h = 0;
        int h1 = 0;
        for(int i = 0; i <= m-1; i++){
            h = d * h + P.charAt(i) % q;
            h1 = d * h1 + T.charAt(i) % q;
        }
        for(int s = 0; s <= n - m; s++){
            if(h == h1)
                if(P.compareTo(T.substring(s,s+m)) == 0)
                    result.add(s);
            if(s < n - m)
                h1 = d * (h1 - dm * T.charAt(s)) + T.charAt(s + m) % q;
        }
        return result;
    }

    private static int[] suffixTable(String P) {

        int m = P.length();
        int[] U = prefix(P);
        String reversedP = new StringBuilder(P).reverse().toString();
        int[] U1 = prefix(reversedP);
        int[] table = new int[m+1];
        int index, shift;

        for(int i = 0; i < m + 1; i++)
            table[i] = m - U[m-1];

        for(int i = 0; i < m; i++) {
            index = m - U1[i];
            shift = i = U1[i] + 1;
            if(table[index] > shift)
                table[index] = shift;
        }
        return table;
    }

    private static int[] prefix (String S) {

        int[] pi = new int[S.length()];
        int k = 0;

        for(int i = 1; i < S.length(); i++) {
            while(k > 0 && (S.charAt(k) != S.charAt(i)))
                k = pi[k - 1];
            if (S.charAt(k) == S.charAt(i))
                k = k + 1;
            pi[i] = k;
        }
        return pi;
    }

    private static int[] stopTable(String P){
        int[] result = new int[65536];
        for (int i = 0; i < 65536; i++)
            result[i] = -1;
        for (int i = 0; i < P.length(); i++)
            result[P.charAt(i)] = i;
        return result;
    }

    public static ArrayList<Integer> boyerMoore (String T, String P) {

        ArrayList<Integer> result = new ArrayList<>();
        if(P.length() == 0)
            return result;

        int[] suffix = suffixTable(P);
        int[] stop = stopTable(P);
        int j, deltaStop, deltaSuffix;

        for(int i = 0; i < T.length() - P.length() + 1; i += 0) {
            j = P.length() - 1;
            while(j >= 0 && (P.charAt(j) == T.charAt(i + j)))
                j = j - 1;
            if(j == -1){
                result.add(i);
                deltaStop = 1;
            } else
                deltaStop = j - stop[T.charAt(i+j)];
            deltaSuffix = suffix[j+1];
            i += Math.max(deltaStop, deltaSuffix);
        }
        return result;
    }

    public static ArrayList<Integer> knuthMorrisPratt (String T, String P) {

        ArrayList<Integer> result = new ArrayList<>();
        if(P.length() == 0)
            return result;

        int[] pi = prefix(P);
        int k = 0;

        for(int i = 0; i < T.length(); i++){
            while(k > 0 && (P.charAt(k) != T.charAt(i)))
                k = pi[k-1];
            if(P.charAt(k) == T.charAt(i))
                k = k + 1;
            if(k == P.length()){
                result.add(i - P.length() + 1);
                k = pi[k-1];
            }
        }
        return result;
    }
}
