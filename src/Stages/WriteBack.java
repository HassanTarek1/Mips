package Stages;

public class WriteBack {

    public static void writeBack(int[] regFile, boolean[] signals, String[] readData){
        int rt=Integer.parseInt(readData[0],2);
        if(signals[6]){
            int dataFromMem=Integer.parseInt(readData[2],2);
            regFile[rt]=dataFromMem;
        }
        else{
            int rd=Integer.parseInt(InstructionDecode.rd,2);
            int aluResult=Integer.parseInt(readData[1],2);
            regFile[rd]=aluResult;
        }
    }
}
