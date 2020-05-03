package Stages;

public class WriteBack {

    public static void writeBack(int[] regFile, boolean[] signals, String[] readData){
        if(signals[6]){
            int rt=Integer.parseInt(readData[0],2);
            int dataFromMem=Integer.parseInt(readData[1],2);
            regFile[rt]=dataFromMem;
        }
    }
}
