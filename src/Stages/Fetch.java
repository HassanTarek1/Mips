package Stages;

public class Fetch {

	static String PCincrementedByOne="00000000000000000000000000000000";
	static String Theinstruction;
	static String[] instructions = {"00010000000001000100010000000000","0010000000001000100010000000000","0011000000001000100010000000000","010000000000001000100010000000000","0101000000001000100010000000000","0110000000001000100010000000000","0111000000001000100010000000000","1000000000001000100010000000000","1001000000001000100010000000000","1010000000001000100010000000000","10110000001001000010110000000000","11000000001001000010110000000000","11010001100001000001100000000000","11100011100000000000000000000000"};
	static String[] IF_ID=new String[2];
	static Boolean PCSrc=false;
	static String SignExResult;
	
//If U need anythings from this class call ProgCount() and it will do the job ~ By the order of Peaky Blinders
		public static void ProgCount() 
		{

			   //Fetching the current Instruction
				 InstFetch(PCincrementedByOne);
               //incrementing the PC
				 if(PCSrc==false) {
			           PCincrementedByOne=String.format("%" + 32 + "s", Integer.toBinaryString(Integer.parseInt(PCincrementedByOne,2)+1)).replaceAll(" ", "0");}
				 else {
				       PCincrementedByOne=String.format("%" + 32 + "s", Integer.toBinaryString(Integer.parseInt(PCincrementedByOne,2)+Integer.parseInt(SignExResult,2))).replaceAll(" ", "0");}
				//Adding the outputs to Pipelined Register
				  IF_ID[0]=PCincrementedByOne;
				  IF_ID[1]=Theinstruction;
				//Printing the result
				  System.out.println("\n"+"Next PC:"+"\n"+PCincrementedByOne+"\n"+"Instruction:"+"\n"+Theinstruction);

       }
            

		public static void InstFetch(String pc)
		{
			//get the intended instruction 
			int c=Integer.parseInt(PCincrementedByOne,2);
			Theinstruction=instructions[c];

        }
}