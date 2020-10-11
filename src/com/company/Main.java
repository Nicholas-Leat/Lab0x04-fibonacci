package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int x = 32;
        int t = fibLoop(32);fibMatrix(x);
        System.out.println(t);
    }
    public static int fibRecur(int x){
        if(x<=1){
            return x;
        }
        return fibRecur(x-1) + fibRecur(x-2);
    }
    public static int fibCache(int x){
        int f[] = new int[x+2];
        if(x == 0){
            return 0;
        }
        f[0] = 0;
        f[1] = 1;
        for(int i = 2; i <= x; i++){
            f[i] = f[i-1]+f[i-2];
        }

        return f[x];
    }
    public static int fibLoop(int x){
        int sum = 0,t1 =0,t2 =1;
        if(x == 0){
            return sum;
        }
        for(int i = 1; i <x; i++){
            sum = t1+t2;
            t1 = t2;
            t2 = sum;
        }
        return t2;
    }
    public static int fibMatrix(int x){
        int F[][] = new int[][]{{1,1},{1,0}};
        if(x == 0){
            return 0;
        }
        power(F,x-1);
        return F[0][0];
    }
    public static void power(int F[][], int x){
        int i;
        int M[][] = new int[][]{{1,1},{1,0}};
        for(i = 2; i <= x;i++){
            multiply(F,M);
        }
    }
    public static void multiply(int F[][], int M[][]){
        int x = F[0][0]*M[0][0] + F[0][1]*M[1][0];
        int y = F[0][0]*M[0][1] + F[0][1]*M[1][1];
        int z = F[1][0]*M[0][0] + F[1][1]*M[1][0];
        int w = F[1][0]*M[0][1] + F[1][1]*M[1][1];

        F[0][0] = x;
        F[0][1] = y;
        F[1][0] = z;
        F[1][1] = w;
    }
    public static void results(){

    }
}
