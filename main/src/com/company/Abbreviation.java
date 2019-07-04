package com.company;
/*
You can perform the following operations on the string, :

Capitalize zero or more of 's lowercase letters.
Delete all of the remaining lowercase letters in .
Given two strings,  and , determine if it's possible to make  equal to  as described.

For example, given a="AbcDE" and b="ABDE", in _a_ we can convert 'b' and delete 'c' to match.
If a="AbcDE" and b="AFDE", matching is not possible because letters may only be capitalized or discarded, not changed.

http://hr.gs/cefccd
*/

public class Abbreviation {

    static String a = "aaa";
    static String b= "aBA";

    public static void main(String[] args) {
        System.out.println(abbreviate(a.length()-1, b.length()-1));
        System.out.println(abbreviateDynamic());
    }

    static boolean abbreviateDynamic(){
        boolean[][] K = new boolean[a.length()+1][b.length()+1];

        for (int i = 0; i <= a.length() ; i++) {
            K[i][0] = true;
        }

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {

                K[i][j] =  Character.toUpperCase(a.charAt(i-1)) == Character.toUpperCase(b.charAt(j-1)) && K[i-1][j-1]
                        || Character.isLowerCase(a.charAt(i-1)) && K[i-1][j];
            }
        }
        return K[a.length()][b.length()];
    }

    static boolean abbreviate(int an, int bn){
        if(an < 0 && bn < 0) return true;
        if(an < 0 || bn < 0) return false;

        return Character.toUpperCase(a.charAt(an)) == Character.toUpperCase(b.charAt(bn)) && abbreviate(an-1,bn-1)
                || Character.isLowerCase(a.charAt(an)) && abbreviate(an-1,bn);
    }
}
