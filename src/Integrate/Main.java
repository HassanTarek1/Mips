package Integrate;
import Stages.*;

public class Main {
    static Register[] regFile;
    static int[] memory;
    static String[] cache;
    static boolean memBus=false;
    static Object[][] pipRegs=new Object[4][];
    static int cycle=0;

    public static void main(String[] args) {
        Main n=new Main();
        n.initRegFile();
        for (int i = 0; i <4 ; i++) {
            System.out.println("\nCycle: "+cycle);
            Object[] ifReg=Fetch.ProgCount();
            if(pipRegs[0]!=null){
                Object[] decodeReg=InstructionDecode.InstDecode(pipRegs[0],regFile);
                pipRegs[0]=ifReg;
                if(pipRegs[1]!=null){
                    Object[] ALUreg=ALU.Execute(pipRegs[1]);
                    pipRegs[1]=decodeReg;
                    if (pipRegs[2]!=null){
                        Object[] memReg=MemoryAccess.memAccess(memory,pipRegs[2]);
                        pipRegs[2]=ALUreg;
                        if (pipRegs[3]!=null){
                            WriteBack.writeBack(regFile,pipRegs[3]);
                            pipRegs[3]=memReg;
                        }else{
                            pipRegs[3]=memReg;
                        }
                    }else{
                        pipRegs[2]=ALUreg;
                    }
                }else{
                    pipRegs[1]=decodeReg;
                }

            }else{
                pipRegs[0]=ifReg;
            }
            cycle++;
        }


        
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

}
