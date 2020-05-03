package Stages;

public class ALU {

	static int zero;
	static String Opcode;
	static String BranchAddressResult; 
	static int ALUresult;
	static boolean RegDst=InstructionDecode.signals[0],RegWrite=InstructionDecode.signals[2]
			,ALUSrc=InstructionDecode.signals[1],Branch=InstructionDecode.signals[5]
			,MemRead=InstructionDecode.signals[3],MemWrite=InstructionDecode.signals[4],MemToReg=InstructionDecode.signals[6];
    static Object [] PipReg = new Object[]{zero,BranchAddressResult,ALUresult,InstructionDecode.ReadData1,InstructionDecode.rs,Branch,MemRead,MemWrite,MemToReg,RegWrite};
    //PipReg to be edited
	//all control values are taken from Hoba and also their print 
	
	public static void Execute(String ReadData1 , String ReadData2 , String SignExtend ,String nxtpc) {
		String Operand22 = "" ;
		
		if (ALUSrc = true) {
			Operand22 = SignExtend ;
		}
		else {
			Operand22 = ReadData2 ;
		}
		
		int Operand2 = Integer.parseInt( Operand22 , 2);
		int Operand1 = Integer.parseInt( ReadData1 , 2);
		
		Evaluate( Opcode , Operand1 , Operand2 );
		
		int SignExtendInteger = Integer.parseInt( SignExtend , 2);
		
		int pc = Integer.parseInt( nxtpc , 2);
		
		BranchAddressResult = Integer.toBinaryString((pc + SignExtendInteger));
		
		System.out.println("zero flag : " + zero);
		System.out.println("branch address :" + BranchAddressResult );
		System.out.println("ALU result/address: " + ALUresult);
		System.out.println("register value to write to memory: "+ ReadData1);
		System.out.println("rt/rd register: "+ InstructionDecode.rs);//need to check
		System.out.println("WB controls: MemToReg: "+((MemToReg) ? 1 : 0)+", RegWrite: "+((RegWrite) ? 1 : 0));
		System.out.println("MEM controls: MemRead: "+((MemRead) ? 1 : 0)+", MemWrite: "+((MemWrite) ? 1 : 0)+", Branch: "+((Branch) ? 1 : 0));
		
		
	}
	public static void Evaluate(String Opcode, int Operand1, int Operand2) {

		if (Opcode.equals("000000")) {
			subOp(Operand1, Operand2);
			RegDst=true;
			ALUSrc=false;
			RegWrite=true;
			MemRead=false;
			MemWrite=false;
			Branch=false;
			MemToReg=false;
		}

		else if (Opcode.equals("000001")) {
			addOp(Operand1, Operand2);
			RegDst=true;
			ALUSrc=false;
			RegWrite=true;
			MemRead=false;
			MemWrite=false;
			Branch=false;
			MemToReg=false;
		}

		else if (Opcode.equals("000010")) {
			multOp(Operand1, Operand2);
			RegDst=true;
			ALUSrc=false;
			RegWrite=true;
			MemRead=false;
			MemWrite=false;
			Branch=false;
			MemToReg=false;
 
		}

		else if (Opcode.equals("000011")) {
			OROp(Operand1, Operand2);
			RegDst=true;
			ALUSrc=false;
			RegWrite=true;
			MemRead=false;
			MemWrite=false;
			Branch=false;
			MemToReg=false;
 
		}

		else if (Opcode.equals("000100")) {
			srlOp(Operand1, Operand2);
			RegDst=true;
			ALUSrc=false;
			RegWrite=true;
			MemRead=false;
			MemWrite=false;
			Branch=false;
			MemToReg=false;
    
		}

		else if (Opcode.equals("000101")) {
			sllOp(Operand1, Operand2);
			RegDst=true;
			ALUSrc=false;
			RegWrite=true;
			MemRead=false;
			MemWrite=false;
			Branch=false;
			MemToReg=false;

		}
		else if (Opcode.equals("010110")) {
			addimOp(Operand1, Operand2);
			RegDst=false;
			ALUSrc=true;
			RegWrite=true;
			MemRead=false;
			MemWrite=false;
			Branch=false;
			MemToReg=false;

		}
		else if (Opcode.equals("010111")) {
			ANDimOp(Operand1, Operand2);
			RegDst=false;
			ALUSrc=true;
			RegWrite=true;
			MemRead=false;
			MemWrite=false;
			Branch=false;
			MemToReg=false;    
		}
		else if (Opcode.equals("011000")) {
			lwOp(Operand1, Operand2);
			RegDst=false;
			ALUSrc=true;
			RegWrite=true;
			MemRead=true;
			MemWrite=false;
			Branch=false;
			MemToReg=true;
		}
		else if (Opcode.equals("011001")) {
			swOp(Operand1, Operand2);
			ALUSrc=true;
			RegWrite=false;
			MemRead=false;
			MemWrite=true;
			Branch=false;
		}
		else if (Opcode.equals("011010")) {
			beqOp(Operand1, Operand2);
			ALUSrc=false;
			RegWrite=false;
			MemRead=false;
			MemWrite=false;
			Branch=true;

		}
		else if (Opcode.equals("011011")) {
			bltOp(Operand1, Operand2);
			RegDst=false;
			ALUSrc=true;
			RegWrite=true;
			MemRead=false;
			MemWrite=false;
			MemToReg=false;

		}
		else if (Opcode.equals("011100")) {
			sltimOp(Operand1, Operand2);
			RegDst=false;
			ALUSrc=true;
			RegWrite=true;
			MemRead=false;
			MemWrite=false;
			Branch=false;
			MemToReg=false;
			 
			
		}
		else if (Opcode.equals("101101")) {
			jrOp(Operand1);
			RegWrite=false;
			MemRead=false;
			MemWrite=false;
			
		}

	}

	public static void subOp(int Operand1, int Operand2) {
		ALUresult = Operand1 - Operand2;
		if (ALUresult == 0) {
			zero = 1;
		} else {
			zero = 0;
		}
	}
	
	public static void addOp(int Operand1, int Operand2) {
		ALUresult = Operand1 + Operand2;
		if (ALUresult == 0) {
			zero = 1;
		} else {
			zero = 0;
		}
	}
	
   public static void addimOp(int Operand1, int Operand2) {
	  ALUresult = Operand1 + Operand2;
	  if (ALUresult == 0) {
		zero = 1;
	   } else {
		zero = 0;
	      }
    }

	public static void multOp(int Operand1, int Operand2) {
		ALUresult = Operand1 * Operand2;
		if (ALUresult == 0) {
			zero = 1;
		} else {
			zero = 0;
		}
	}
	
	public static void OROp(int Operand1, int Operand2) {
		ALUresult = Operand1 | Operand2;
		if (ALUresult == 0) {
			zero = 1;
		} else {
			zero = 0;
		}
	}
	
	public static void ANDimOp(int Operand1, int Operand2) {
		ALUresult = Operand1 & Operand2;
		if (ALUresult == 0) {
			zero = 1;
		} else {
			zero = 0;
		}

	}
	
	public static void srlOp(int Operand1, int Operand2) {
		ALUresult = Operand1 >> Operand2;
		if (ALUresult == 0) {
			zero = 1;
		} else {
			zero = 0;
		}
	}

	public static void sllOp(int Operand1, int Operand2) {
		ALUresult = Operand1 << Operand2;
		if (ALUresult == 0) {
			zero = 1;
		} else {
			zero = 0;
		}
	}
	public static void lwOp(int Operand1, int Operand2) {
		 ALUresult = Operand1 + Operand2;
		//int ALUresult1 = Operand1 + Operand2;
		//String ALUresult = DataMemory.dataMemory[ALUresult1];
		if (ALUresult == 0) {
			zero = 1;
		} else {
			zero = 0;
		}
	}
	
	public static void swOp(int Operand1, int Operand2) {
		 ALUresult = Operand1 + Operand2;
		//int ALUresult1 = Operand1 + Operand2;
		//String ALUresult = DataMemory.dataMemory[ALUresult1];
		if (ALUresult == 0) {
			zero = 1;
		} else {
			zero = 0;
		}
	}
	public static void beqOp(int Operand1, int Operand2) {
		 ALUresult = Operand1 - Operand2;
		if (ALUresult == 0) {
			zero = 1;
			Branch=true;
		} else {
			zero = 0;
			Branch=false;
		}

	}
	
	public static void bltOp(int Operand1, int Operand2) {
		  ALUresult = Operand1 - Operand2;
		 if (ALUresult == 0) {
			 zero = 1;
			 Branch = false;
		 }
		 else if (ALUresult < 0) {
			 zero = 0;
			 Branch = true;
		 }
		 else {
			 zero = 0;
			 Branch = false;
		 }
	}
	
	public static void sltimOp(int Operand1, int Operand2) {
		int Output1 = Operand1 - Operand2;
		if (Output1 < 0) {
			ALUresult = 1;
			zero = 0;
		} else if (ALUresult == 0 ){
			ALUresult = 0;
			zero = 1;
		}
		else if (ALUresult > 0 ){
			ALUresult = 0;
			zero = 1;
			//need to handle 
		}

	}

	
	public static void jrOp(int Operand1) {
		
		ALUresult = Operand1;
		}	

}

