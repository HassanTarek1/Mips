package Integrate;
import Stages.*;

public class Main {
    private Register[] regFile;
    private String[] memory;
    private String[] cache;
    boolean memBus=false;

    public static void main(String[] args) {
        Main n=new Main();
        n.initRegFile();
        n.initMem();

        
    }


    public void initRegFile(){
        this.regFile=new Register[32];
        Register zero=new Register("zero","constant zero",0);
        this.regFile[0]=zero;
        for (int i = 0; i <16 ; i++) {
            Register argument=new Register("a"+Integer.toString(i),"Arguments",0);

            this.regFile[i+1]=argument;
        }
        for (int i = 17; i <28 ; i++) {
            Register temporary=new Register("t"+Integer.toString(i),"Temporaries",0);
            this.regFile[i]=temporary;
        }
        this.regFile[28]=new Register("gp","Global pointer",0);
        this.regFile[29]=new Register("sp","Stack pointer",0);
        this.regFile[30]=new Register("fp","frame pointer",0);
        this.regFile[31]=new Register("ra","return address",0);

    }
    public void initMem(){
        this.memory=new String[2048];
        for (String n:this.memory
             ) {
            n="00000000000000000000000000000000";
        }
    }
}
