package com.company;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.lang.Math;
public class Main {

    public static void main(String[] args) {
	// write your code here
        results();
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
        //amount of trials to get average time
        int trialnum = 5;
        //the values for x
        int len = 100;
        //max time allowed
        int mxTime = 2000000;

        boolean continuefibMatrix = true;
        boolean continuefibCache = true;
        boolean continuefibLoop = true;
        boolean continuefibRecur = true;
        boolean con = true;
        double[] FibRecurTime = new double[len];
        double[] FibLoopTime = new double[len];
        double[] FibCacheTime = new double[len];
        double[] FibMatrixTime = new double[len];
        double[] FibRecurDR = new double[len];
        double[] FibLoopDR = new double[len];
        double[] FibCacheDR = new double[len];
        double[] FibMatrixDR = new double[len];
        double[] FibRecurEDR = new double[len];
        double[] FibLoopEDR = new double[len];
        double[] FibCacheEDR = new double[len];
        double[] FibMatrixEDR = new double[len];
        int[] xVal = new int[len];
        int[] NVal = new int[len];
        double timeBefore = getCpuTime();
        double timeAfter = getCpuTime();
        double timeNot = timeAfter-timeBefore;
        double avg = 0;
        int x = 1;
        int count = 0;
        for(int i = 0; i < 400; i++){
            System.out.printf("-");
        }
        System.out.println(" ");
        System.out.format("%-20s %-90s %-90s %-90s %-90s\n"," ", "FibRecur", "FibCache", "FibLoop", "FibMatrix");
        System.out.format("%-10s %-9s %-30s %-30s %-28s %-30s %-30s %-28s %-30s %-30s %-28s %-30s %-30s %-30s\n", "X","N","Time","Doubling Ratio","Expected Doubling Ratio","Time","Doubling Ratio","Expected Doubling Ratio","Time","Doubling Ratio","Expected Doubling Ratio","Time","Doubling Ratio","Expected Doubling Ratio");
        while(con){
            if(count >= xVal.length){
                break;
            }else{
                NVal[count] = (int) Math.ceil(Math.log(2*(x+1)));
                xVal[count] = x;
            }
            if(continuefibRecur){
                for(int i = 0; i < trialnum; i++){
                    timeBefore = getCpuTime();
                    fibRecur(x);
                    timeAfter = getCpuTime();
                    avg += timeAfter - timeBefore ;
                }
                avg = avg/trialnum;
                FibRecurTime[count] = avg;
                if(FibRecurTime[count] > mxTime){
                    continuefibRecur = false;
                }
                avg = 0;
                if(count != 0) {
                    FibRecurDR[count] = FibRecurTime[count] / FibRecurTime[x / 2];
                    FibRecurEDR[count] = 0;
                }
            }
            if(continuefibLoop){
                for(int i = 0; i < trialnum; i++){
                    timeBefore = getCpuTime();
                    fibLoop(x);
                    timeAfter = getCpuTime();
                    avg += timeAfter - timeBefore ;
                }
                avg = avg/trialnum;
                FibLoopTime[count] = avg;
                if(FibLoopTime[count] > mxTime){
                    continuefibLoop = false;
                }
                avg = 0;
                if(count != 0) {
                    FibLoopDR[count] = FibLoopTime[count] / FibLoopTime[x / 2];
                    FibLoopEDR[count] = 0;
                }
            }
            if(continuefibCache){
                for(int i = 0; i < trialnum; i++){
                    timeBefore = getCpuTime();
                    fibCache(x);
                    timeAfter = getCpuTime();
                    avg += timeAfter - timeBefore ;
                }
                avg = avg/trialnum;
                FibCacheTime[count] = avg;
                if(FibCacheTime[count] > mxTime){
                    continuefibCache = false;
                }
                avg = 0;
                if(count != 0) {
                    FibCacheDR[count] = FibCacheTime[count] / FibCacheTime[x / 2];
                    FibCacheEDR[count] = 0;
                }
            }
            if(continuefibMatrix){
                for(int i = 0; i < trialnum; i++){
                    timeBefore = getCpuTime();
                    fibMatrix(x);
                    timeAfter = getCpuTime();
                    avg += timeAfter - timeBefore ;
                }
                avg = avg/trialnum;
                FibMatrixTime[count] = avg;
                if(FibMatrixTime[count] > mxTime){
                    continuefibMatrix = false;
                }
                avg = 0;
                if(count != 0) {
                    FibMatrixDR[count] = FibMatrixTime[count] / FibMatrixTime[x / 2];
                    FibMatrixEDR[count] = 0;
                }
            }
            if(!continuefibCache && !continuefibLoop && !continuefibMatrix && !continuefibRecur){
                con = false;
            }
            if(count == 0){
                FibCacheDR[0] = 0;
                FibCacheEDR[0]=0;
                FibLoopDR[0] = 0;
                FibLoopEDR[0] = 0;
                FibRecurDR[0] = 0;
                FibRecurEDR[0] = 0;
                FibMatrixDR[0] = 0;
                FibMatrixEDR[0] = 0;
            }

            if(con) {
                System.out.format("%-10s %-9s %-30s %-30s %-28s %-30s %-30s %-28s %-30s %-30s %-28s %-30s %-30s %-30s\n", xVal[count], NVal[count], FibRecurTime[count], FibRecurDR[count], FibRecurEDR[count], FibCacheTime[count], FibCacheDR[count], FibCacheEDR[count], FibLoopTime[count], FibLoopDR[count], FibLoopEDR[count], FibMatrixTime[count], FibMatrixDR[count], FibMatrixEDR[count]);
            }
            count++;
            x++;
        }
        count--;

    }
    public static long getCpuTime( ) {

        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );

        return ((ThreadMXBean) bean).isCurrentThreadCpuTimeSupported( ) ?

                bean.getCurrentThreadCpuTime( ) : 0L;

    }
}
