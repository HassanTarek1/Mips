package Stages;

import Integrate.Register;

public class WriteBack {
    static String rt;
    static String rd;
    static int ALUresult;
    static String readData2;
    static String dataFromMem;
    static boolean[] signals;
    static int zero;
    public static void writeBack(Register[] regFile, Object[] reg){
        rt=(String)reg[1];
        rd=(String)reg[2];
        dataFromMem=(String)reg[0];
        readData2=(String)reg[3];
        ALUresult=(int)reg[4];
        zero=(int)reg[5];
        signals=(boolean[])reg[6];

        if(signals[6]){
            int rtInt=Integer.parseInt(rt,2);
            regFile[rtInt].setValue(Integer.parseInt(dataFromMem,2));
        }
        else{
            int rdInt=Integer.parseInt(rd,2);
            regFile[rdInt].setValue(ALUresult);
        }
    }
}
