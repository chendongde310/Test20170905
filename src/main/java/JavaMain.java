import java.util.Scanner;

/**
 * 作者：陈东  —  www.renwey.com
 * 日期：2017/9/5 - 上午10:50
 * 注释：
 */
public class JavaMain {


    public static void main(String args[]){

//        findCombination(33, 50, new int[33]);

        int n,m;
        Scanner s=new Scanner(System.in);
        n=s.nextInt();
        m=s.nextInt();
        length=n;
        int[] flag=new int[n];
        findCombination(n, m, flag);
    }

  int[] num = new int[6];
    public static  void  go(int r1,int r2,int r3,int r4,int r5,int r6){

    }





    static int length;
    static void findCombination(int n,int m,int flagI[]){
        if (n<1||m<1) {
            return;
        }
        if (n>m) {
            n=m;
        }
        if (n==m) {
            flagI[n-1]=1;
            for (int i = 0; i < length; i++) {
                if (flagI[i]==1) {
                    System.out.print(i+1+" ");
                }
            }
            System.out.println();
            flagI[n-1]=0;
        }

        flagI[n-1]=1;
        findCombination(n-1, m-n, flagI);

        flagI[n-1]=0;
        findCombination(n-1, m, flagI);
    }

}
