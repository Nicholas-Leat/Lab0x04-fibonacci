package com.company;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
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
        int trialnum = 5;
        boolean continuefibMatrix = true;
        boolean continuefibCache = true;
        boolean continuefibLoop = true;
        boolean continuefibRecur = true;
        boolean con = true;
        double[] FibRecurTime = new double[10000];
        double[] FibLoopTime = new double[10000];
        double[] FibCacheTime = new double[10000];
        double[] FibMatrixTime = new double[10000];
        double[] FibRecurDR = new double[10000];
        double[] FibLoopDR = new double[10000];
        double[] FibCacheDR = new double[10000];
        double[] FibMatrixDR = new double[10000];
        double[] FibRecurEDR = new double[10000];
        double[] FibLoopEDR = new double[10000];
        double[] FibCacheEDR = new double[10000];
        double[] FibMatrixEDR = new double[10000];
        int[] xVal = new int[10000];
        int[] NVal = new int[10000];
        double timeBefore = getCpuTime();
        double timeAfter = getCpuTime();
        double timeNot = timeAfter-timeBefore;
        double avg = 0;
        int x = 1;
        int count = 0;
        while(con){
            if(count >= xVal.length){
                continuefibMatrix = false;
                continuefibCache = false;
                continuefibLoop = false;
                continuefibRecur = false;
                con = false;
            }else{
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
                if(FibRecurTime[count] > 900000){
                    continuefibRecur = false;
                }
                avg = 0;
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
                if(FibLoopTime[count] > 900000){
                    continuefibLoop = false;
                }
                avg = 0;
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
                if(FibCacheTime[count] > 900000){
                    continuefibCache = false;
                }
                avg = 0;
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
                if(FibMatrixTime[count] > 900000){
                    continuefibMatrix = false;
                }
                avg = 0;
            }
            if(!continuefibCache && !continuefibLoop && !continuefibMatrix && !continuefibRecur){
                con = false;
            }
            //N val calculation here
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
