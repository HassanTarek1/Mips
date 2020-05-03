package Stages;

public class MemoryAccess {

    public static String[] memAccess(int[] memory, boolean[] signals,
                                     String address, String writedata,String rt){
        String[] result=new String[]{rt,""};
        System.out.println("ALU result: "+writedata);
        if (!signals[3] && !signals[4]) {
            System.out.println("memory word read: xxxxxxx"
            +"\nrt/rd field: "+rt);
            return null;
        }
        else{
            int add=Integer.parseInt(address,2);
            if(signals[3]){
                String data=intToBin(memory[add]);
                result[1]=data;
                System.out.println("memory word read: "+data
                        +"\nrt/rd field: "+rt);
            }
            if (signals[4]){
                int wrtData=Integer.parseInt(writedata,2);
                memory[add]=wrtData;
                System.out.println("memory word read: "+writedata
                        +"\nrt/rd field: "+rt);
            }
        }
        System.out.println("WB controls: MemToReg: "+signals[6]+", RegWrite: "+signals[2]);
        return result;
    }

    private static String intToBin(int n){
        String result=Integer.toBinaryString(n);
        while (result.length()<32){
            result="0"+result;
        }
        return result;
    }
}
