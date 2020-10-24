import java.lang.*;
import java.util.*;
public class CRC{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
	String s1,s2;
        System.out.println("Enter no. of Data bits: ");
        int ndata = sc.nextInt();
        System.out.println("Enter data bits: ");
        int[] data = new int[ndata];
	s1=sc.next();
        for (int i = 0;i < ndata ;i++ ) {
            data[i] = s1.charAt(i)-'0';
        }


        System.out.println("Enter no. of divisor bits: ");
        int ndivisor = sc.nextInt();
        int[] divisor = new int[ndivisor];
        System.out.println("enter divisor bits: ");
	s2=sc.next();
        for (int i = 0;i < ndivisor ;i++ ) {
            divisor[i] = s2.charAt(i)-'0';
        }

        int crc_length = ndata + ndivisor-1;
        int[] crc = new int[crc_length];
        int[] remainder = new int[crc_length];
        int[] dividend = new int[crc_length];

        //appending 0s
        System.arraycopy(data, 0, dividend, 0, ndata);
        if (dividend.length >= 0) System.arraycopy(dividend, 0, remainder, 0, dividend.length);
        remainder = XOROperation(dividend,divisor,remainder);

        for (int i = 0; i < dividend.length ; i++ ) {
            crc[i] = (dividend[i] ^ remainder[i]);
        }

        System.out.println("message generated is:");
        for (int value : crc) {
            System.out.print(value);
        }
        System.out.println();
        //receiver end
        receiverend(remainder, crc, divisor);
    }

    static int[] XOROperation(int[] dividend, int[] divisor, int[] remainder){
        int temp = 0;
        while(true)
        {
            for(int i=0;i<divisor.length;i++)
                remainder[temp+i]=(remainder[temp+i]^divisor[i]);

            while(remainder[temp]==0 && temp!=remainder.length-1)
                temp++;

            if((remainder.length-temp)<divisor.length)
                break;
        }
        return remainder;
    }
    static void receiverend(int[] remainder, int[] crc, int[] divisor){
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the data received: ");
	String s=sc.next();
        for (int i = 0; i < crc.length ; i++ ) {
            crc[i] = s.charAt(i) -'0';
        }
        System.arraycopy(crc, 0, remainder, 0, crc.length);
        remainder = XOROperation(crc, divisor, remainder );
        for(int i=0; i< remainder.length; i++)
        {
            if(remainder[i]!=0)
            {
                System.out.println("No.");
                break;
            }
            if(i==remainder.length-1)
                System.out.println("Yes.");
        }
    }
}
