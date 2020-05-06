package Stages;

import Integrate.Main;

public class Fetch {

	public static String PCincrementedByOne="00000000000000000000000000000000";
	public static String Theinstruction;
	public static String[] instructions= Main.cache;
	public static Object[] IF_ID=new Object[2];
	public static Boolean PCSrc=InstructionDecode.PCsrc;
	public static String SignExResult;
	public static int flag=1;
	
//If U need anythings from this class call ProgCount() and it will do the job ~ By the order of Peaky Blinders
		public static Object[] ProgCount()
		{
			System.out.println("\nFetch......."+"\n");
			   //Fetching the current Instruction
				 InstFetch(PCincrementedByOne);
               //incrementing the PC
				 if(InstructionDecode.Branch) {
				 	PCincrementedByOne=ALU.BranchAddressResult;
				 }
				 if (PCSrc){
					 PCincrementedByOne=ALU.jumpAddress;
				 }
				 else {
				       PCincrementedByOne=String.format("%" + 32 + "s", Integer.toBinaryString(Integer.parseInt(PCincrementedByOne,2)+1)).replaceAll(" ", "0");
				 }
				//Adding the outputs to Pipelined Register
				  IF_ID[0]=PCincrementedByOne;
				  IF_ID[1]=Theinstruction;
				//Printing the result
				  System.out.println("Next PC:"+"\n"+PCincrementedByOne+"\n"+"Instruction:"+"\n"+Theinstruction);
				return IF_ID;
       }
            

		public static void InstFetch(String pc)
		{
			//get the intended instruction

			int c=Integer.parseInt(PCincrementedByOne,2);
			if (c<instructions.length && instructions[c]!=null)
				Theinstruction=instructions[c];
			else
				flag=0;
        }
}