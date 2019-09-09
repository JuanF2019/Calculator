import java.util.Scanner;
import java.lang.Math;

public class Calculator	
{
	public static double pi = 3.14159265358979323846;
	public static void main(String args[])
	{
		Scanner inputInt = new Scanner(System.in);
		Scanner inputString = new Scanner(System.in);
		System.out.println("Welcome to calculator v1.0");
		boolean menu = true;
		int mode = 0;
		double a = 0;
		double b = 0;
		double result = 0;
		String operator;
		double[] memory = new double[11];
		memory[(memory.length-1)] = pi;
		String[] memoryNames = {"A","B","C","D","E","F","G","H","I","J","PI"};
		String conversionArray[] = new String[2];
		String number = "";
		String conversionType = "";
		String numberType = "";
		
		int memoryPosition = 0;
		boolean continue1 = true;
		boolean check = false;
	
		while (menu)
		{
			System.out.println("Main menu.");
			System.out.println("1. Simple mode");
			System.out.println("2. Flow mode");
			System.out.println("3. Conversions");
			System.out.println("4. Exit");
			mode = inputInt.nextInt();				
			switch (mode)
			{
				case 1:
					System.out.println("When adding a number, if you want to see and use the memory type any letter.\nWhen typing the operator, type # to exit.\n------------------");		
					continue1 = true;
					do
					{
						a = operationValue(inputString, inputInt, memory, memoryNames,mode);					
						operator = inputString.nextLine();						
						if (!operator.equals("#"))		
						{	
							result = operationSelector(inputString,inputInt,operator, memory, memoryNames, a, mode);
							memoryPosition = memorySave(memoryPosition, result, memory);
						}
						if (operator.equals("#"))
						{
							continue1 = false;
						}						
					} while (continue1 == true);
					System.out.println("------------------");
				break;
				case 2:
					System.out.println("When adding a number, if you want to see and use the memory type any letter.\nWhen typing the operator, type # to exit.\n------------------");
					a = operationValue(inputString, inputInt, memory, memoryNames, mode);
					continue1 = true;
					do
					{											
						if (check)
						{
							a = result;
						}
						check = true;
						operator = inputString.nextLine();
						if (!operator.equals("#"))		
						{	
							result = operationSelector(inputString,inputInt,operator, memory, memoryNames, a, mode);
							memoryPosition = memorySave(memoryPosition, result, memory);
						}
						if (operator.equals("#"))
						{
							continue1 = false;
						}
					}while (continue1 == true);
					System.out.println("------------------");
				break;
				case 3:
					System.out.println("------------------");
					continue1 = true;
					conversionType = "";
					do
					{											
						if (!conversionType.equals("#") )		
						{	
							System.out.println("Select the conversion you want to make:");
							System.out.println("1. Binary to decimal.");
							System.out.println("2. Decimal to binary.");
							System.out.println("3. Hexadecimal to decimal.");
							System.out.println("4. Decimal to hexadecimal.");
							System.out.println("5. Binary to hexadecimal.");
							System.out.println("6. Hexadecimal to binary.");
							System.out.println("7. Degress to radians.");
							System.out.println("8. Radians to degress.");
							conversionType = inputString.nextLine();
							conversionArray = conversion(inputString,inputInt,conversionType,numberType);
							number = conversionArray[0];							
							numberType = conversionArray[1];
							System.out.println("=\n" + number + " " + numberType);							
						}
						if (conversionType.equals("#"))
						{
							continue1 = false;
						}
					}while (continue1 == true);
				break;
				case 4:
					System.out.println("Closing calculator...");
					menu = false;
				break;
				default:
					System.out.println("Invalid mode, write a valid one.");
					
			}
		}
	}
	/** Selects and makes an operation given a initial value "a" and an operator that is expressed in the cases of the switch, it also partially formats the result for the given mode.<br>
	*<b>pre:</b> The method for each operation must be written, or given in the corresponding case of the switch.
	*@param inputString Created scanner for reading Strings. inputString!=null
	*@param inputInt Created scanner for reading numbers. inputInt!=null
	*@param operator Operator for selecting the operation to make. operator != null 
	*@param memory[] The memory array must be initialized. memory != null
	*@param memoryNames[] The names of the values stored in memory[]. Must be initialized and fill with letters. memoryNames != null
	*@param a Initial value to participate in the operation. Must be initialized
	*@param mode Calculator mode to partially format the result. mode != 0
	*@return result Result of the operation as a double.
	*/
	public static double operationSelector(Scanner inputString, Scanner inputInt, String operator, double[] memory, String[] memoryNames, double a, int mode) 
	{
		boolean check = false;
		double b = 0;
		double result = 0;
		while (!check)
		{
			check = true;
			switch(operator)
			{
				case "+":
				    b = operationValue(inputString, inputInt, memory, memoryNames, mode);
					result = a+b;
					System.out.println("=\n" + result);
				break;
				case "-":
					b = operationValue(inputString, inputInt, memory, memoryNames,mode);
					result = a-b;
					System.out.println("=\n" + result);
				break;
				case "*":
					b = operationValue(inputString, inputInt, memory, memoryNames,mode);
					result = a*b;
					System.out.println("=\n" + result);
				break;
				case "/":
					b = operationValue(inputString, inputInt, memory, memoryNames, mode);
					while(b==0)
					{
						System.out.println("Can't divide by zero. Type another number or select a different value: ");
						b = operationValue(inputString, inputInt, memory, memoryNames, mode);
					}
					result = a/b;
					System.out.println("=\n" + result);
				break;
				case "%":
					b = operationValue(inputString, inputInt, memory, memoryNames, mode);
					while(b==0)
					{
						System.out.println("Can't divide by zero. Type another number or select a different value: ");
						b = operationValue(inputString, inputInt, memory, memoryNames, mode);
					}
					result = a%b;
					System.out.println("=\n" + result);
				break;
				case "!":
					result = factorial(inputInt, inputString, memory, memoryNames, a, mode);
					System.out.println(a + "! = \n" + result);
				break;
				case "sqrt":
					result = squareRoot(a);
					System.out.printf("sqrt(" + a + ") = " + "%.4f%n",result);
				break;
				case "^":
					b = operationValue(inputString, inputInt, memory, memoryNames, mode);
					result = intPower(a, b);
					System.out.println("=\n" + result);
				break;
				case "e":
					result = a;
					System.out.printf("=%n" + "%.4e%n",a);
				break;
				case "sen":				 
					result = Math.sin(a*(pi/180));
					System.out.printf("sen(" + a + ") = " + "%.4f%n",result);
				break;
				case "cos":
					result = Math.cos(a*(pi/180));
					System.out.printf("cos(" + a + ") = " + "%.4f%n",result);
				break;
				case "tan":
					result = Math.tan(a*(pi/180));
					System.out.printf("tan(" + a + ") = " + "%.4f%n",result);
				break;
				case "nrt":
					b = operationValue(inputString, inputInt, memory, memoryNames, mode);
					result = Math.pow(a,1/b);
					System.out.printf(b + " root of " + a + " = " + "%.4f%n",result);
				break;
				case"log":
					result = Math.log10(a);
					System.out.printf("log ( " + a + " ) = " + "%.4f%n",result);
				break;
				case"logn":
					b = operationValue(inputString, inputInt, memory, memoryNames, mode);
					result = (Math.log(a)/Math.log(b));
					System.out.printf("log of " + a + " in base " + b + " = " + "%.4f%n",result);
				case "#":
					check = true;
				break;
				default:
					check = false;
					System.out.println("Invalid operator, write a valid one: ");
					operator = inputString.nextLine();									
			}
		}
		if(mode == 1)
		{
			System.out.println("");
		}			
		return result;		
	}
	/** The method asks for a value, if it corresponds to an operation it invokes the method operationSelector, if it corresponds to letter<br>
	*it invokes the memoryUse method, and if it corresponds to a number it stored as a double. At the end is returned the final stored value<br>
	*as a double<br>
	*<b>pre:</b> The methods operationSelector and memoryUse must be created.
	*@param inputString Created scanner for reading Strings. inputString!=null
	*@param inputInt Created scanner for reading numbers. inputInt!=null
	*@param memory[] The memory array must be initialized. memory != null
	*@param memoryNames[] The names of the values stored in memory[]. Must be initialized and fill with letters. memoryNames != null
	*@param mode Calculator mode for method operationSelector. mode != 0
	*@return value Final stored value as a double.
	*/
	public static double operationValue(Scanner inputString, Scanner inputInt, double[] memory, String[] memoryNames, int mode)
	{
		boolean check = true;
		boolean check2 = false;
		double value = 0;	
		int a = 0;
		String operator = "";
		String[] singleValueOperations = {"sen","cos","tan","log","logn","sqrt","nrt","!"};
		
		String c = inputString.nextLine();
		c = c.toLowerCase();
		
		
		
		Scanner inputSave = new Scanner(c);			
		while (check && inputSave.hasNext())
		{
			if (inputSave.hasNextDouble()) //The program checks if the input is double.
			{
				value = inputSave.nextDouble();
				check2 = true;				
			}
			else //If it gets here it knows is a string. 
			{
				check2 = false;
				for (int cont = 0;cont<singleValueOperations.length;cont++)
				{
					if (c.indexOf(singleValueOperations[cont])>=0)
					operator = singleValueOperations[cont];				
				}
				if (operator!="")
				{
					a = inputInt.nextInt();
					value = operationSelector(inputString, inputInt, operator,memory, memoryNames, a, mode);
					check2 = true;
				}
				check = false;
			}
		}
			
		inputSave.close();
		
		if (!check2)
		{
			value = memoryUse(inputString, inputInt, memory, memoryNames);
		}
		return value;
	}
	/** The method allows the use of the memory[], given the corresponding letter of the memory value it returns it as a double to use in.<br>
	*further operations.<br>
	*@param inputString Created scanner for reading Strings. inputString!=null
	*@param inputInt Created scanner for reading numbers. inputInt!=null
	*@param memory[] The memory array must be initialized and fill with zeroes. memory != null
	*@param memoryNames[] The names of the values stored in memory[]. Must be initialized and fill with letters. memoryNames != null
	*@return value Value to use in further operations.
	*/
	public static double memoryUse(Scanner inputString, Scanner inputInt, double[] memory, String[] memoryNames) //Is only used when using memory.
	{
		double value = 0;
		System.out.println("Saved values: ");
		for (int cont=0;cont<memory.length;cont++)
		{	
			System.out.printf(memoryNames[cont] + ":" + memory[cont] + "  ");
		}		
		System.out.println("");
		String letter;
		boolean check = false;
		do
		{
			System.out.println("Type the letter corresponding to the value you want: ");
			letter = inputString.nextLine();
			letter = letter.toUpperCase();
				
			for (int cont = 0 ; cont < memory.length; cont++) //Compares the letter input with the value corresponding to the letter position.
			{
				if (letter.equals(memoryNames[cont]))
				{
					value = memory[cont];
					System.out.println(value);
					cont = memory.length-1;	
					check = true;
				}
				else if (cont == memory.length-1 && !check) {}				
			}			
			if (!check) //Notifies the user of the invalid input.
			{
				System.out.println("Invalid letter choose one from the list.");
			}
			
		} while(!check);
		return value;
	}
	/** Saves a given value to memory and stores the memoryPosition for the next time the method is invoked.<br>
	**<b>pos:</b> The memory[] is modified adding the new value
	*@param memoryPosition Must be initialized. memoryPosition<11
	*@param result The result to store in the memory[].
	*@param memory[] The memory array must be initialized and fill with zeroes. memory != null	
	*@return memoryPosition The position to use the next time this method is invoked.
	*/
	public static int memorySave(int memoryPosition, double result, double[] memory)//Requiere arreglo de memoria ya creado y lleno de 0. Requiere variable memoryPosition declarada e inicializada(param). Requiere valor de resultado a guardar en memoria.(param)
	{
		memory[memoryPosition] = result;
		if (memoryPosition >= memory.length-2) 
		{	
			memoryPosition = 0;
		}
		else 
		{
			memoryPosition++;
		}
		return memoryPosition;
	}
	/** Calculates the factorial of a given number. The other parameters are requiered for re asking the user for another value, it could also be from the memory.<br>
	*@param inputString Created scanner for reading Strings. inputString!=null
	*@param inputInt Created scanner for reading numbers. inputInt!=null
	*@param memory[] The memory array must be initialized and fill with zeroes. memory != null
	*@param memoryNames[] The names of the values stored in memory[]. Must be initialized and fill with letters. memoryNames != null
	*@param a Value to take the factorial of.
	*@param mode Calculator mode required in method operationValue. mode != 0
	*@return factorialResult Result of the factorial as a double.
	*/
	public static double factorial(Scanner inputString, Scanner inputInt, double[] memory, String[] memoryNames, double a, int mode)
	{
		double factorialResult = a;
		boolean check = false;
		do
		{
			if (a%1 == 0 && a>=0)
			{	
				check = true;
				if (a == 0)
				{	
					factorialResult = 1;
				}
				else 
				{
					for (double cont = a-1; cont>0 ; cont--)
					{
						factorialResult = factorialResult*cont;
					}
				}				
			}	
			else
			{
				System.out.println("Invalid value, factorial function only accepts positive integers and zero. Type another value:");
				a = operationValue(inputString,inputInt,memory,memoryNames,mode);
				factorialResult = a;
				
			}
		} while (!check);
		return factorialResult;
	}
	/** Calculates the square root of a given value, does not check if the value is negative. <br>
	*@param a The value for calculating the root. a>0
	*@return squareRootResult Result of the square root as a double.
	*/
	public static double squareRoot(double a)
	{
		double squareRootResult=0;
		double impairSum = 0;
		double cont = 1;
		double root = 0;
		boolean check = true;
		while (impairSum<=a)
		{	
			impairSum = impairSum + cont;
			cont = cont + 2;
			root = root + 1;
			if (a==impairSum)
			{
				squareRootResult = root;
				check = false;
			}
		}
			
		
		double temp = a;
		double temp2 = a/2;
		if (check)
		{
			for (cont = 1; cont<20; cont++)
			{
				if ((temp*temp)<a)
				{
					 
					temp = temp + temp2;				
				}
				else
				{
					temp = temp - temp2;
				}
				temp2 = temp2/2;
			}
			squareRootResult = temp;
		}
		return squareRootResult;	
	}
	/**Calculates the power of a given value with another value as an exponent.<br>
	*@param a Value of the base.
	*@param b Value of the exponent, must be an integer but as a double. b%1=0
	*@return powerResult Result of the power as a double.
	*/
	public static double intPower(double a, double b)
	{
		double powerResult = a;
		double bSave = b;
		if (b<0) {b=b*-1;}
		for (int cont = 1 ; cont<b ; cont++)
		{
			powerResult = powerResult*a;
		}
		if (bSave>=0)
		{
			powerResult = powerResult;
		}
		else if (bSave < 0)
		{
			powerResult = 1/powerResult;
		}		
		return powerResult;		
	}
	/**This method serves as the menu for the conversion mode in the calculator. <br>
	*<b>pos:</b> The array nnT is created.
	*@param inputString Created scanner for reading Strings. inputString!=null
	*@param inputInt Created scanner for reading numbers. inputInt!=null
	*@param conversionType Must be initialized, must be an integer.
	*@param numberType Must be Initialized.
	*@return nnT array composed of the converted value after conversion and the numerical system in which is expressed.
	*/
	public static String[] conversion(Scanner inputString, Scanner inputInt, String conversionType, String numberType)
	{
		boolean check = false;		
		String number = "";
		double numberN = 0;
		do
		{
			check = true;
			switch (conversionType)
			{
				case "1"://binario a decimal
					number = inputString.nextLine();
					System.out.println("bin");
					number = binToDec(number);
					numberType = "dec";
				break;
				case "2"://decimal a binario
					number = inputString.nextLine();
					System.out.println("dec");
					number = decToBin(number);
					numberType = "bin";
				break;
				case "3"://hexadecimal a decimal
					number = inputString.nextLine();
					System.out.println("hex");
					number = hexToDec(number);
					numberType = "dec";
				break;
				case "4"://decimal a hexadecimal
					number = inputString.nextLine();
					System.out.println("dec");
					number = decToHex(number);
					numberType = "hex";
				break;
				case "5"://binario a hexadecimal
					number = inputString.nextLine();
					System.out.println("bin");
					number = binToDec(number);
					number = decToHex(number);
					numberType = "hex";
				break;
				case "6"://hexadecimal a binario
					number = inputString.nextLine();
					System.out.println("hex");
					number = decToHex(number);
					number = binToDec(number);
					numberType = "bin";
				break;
				case "7"://grados a radianes
					number = inputString.nextLine();
					System.out.println("°");
					numberN = Double.parseDouble(number);
					number = Double.toString(numberN*(pi/180));
					numberType = "rad";
				break;
				case "8"://radianes a grados
					number = inputString.nextLine();
					System.out.println("rad");
					numberN = Double.parseDouble(number);
					number = Double.toString(numberN*(180/pi));
					numberType = "°";
				break;
				case "#":
				break;
				default:
					check = false;
					System.out.println("Invalid option, type a valid one:");
					conversionType = inputString.nextLine();
			}
		}while(!check);
		String[] nnTArray = {number,numberType};
		return nnTArray;
	}
	/** This method converts a binary number into a decimal. <br>
	*@param number Number in binary to convert to decimal, is assumed that is a valid binary. Must be a string
	*@return decimalS Decimal value of the number as a string.
	*/
	public static String binToDec(String number)
	{
		int decimal = 0;
		String binaryNumber = number;
		
		String sString;
		int length = binaryNumber.length();
		int[] binaryNumberArray = new int[length];
		for (int cont = 0; cont<length; cont++)
		{
			sString = binaryNumber.substring(cont,cont+1);
			binaryNumberArray[cont] = Integer.parseInt(sString);
		}	
		
		int[] binaryArray = new int[length];  
		
		int lastPosValue = 1;
		binaryArray[binaryArray.length-1] = lastPosValue;
		for (int cont = binaryArray.length-1; cont > 0; cont-- )
		{
			binaryArray[cont-1] = 2*lastPosValue;
			lastPosValue = 2*lastPosValue;
		}
			
		for (int digitPosition = 0 ; digitPosition<binaryArray.length; digitPosition++)
		{
			if (binaryNumberArray[digitPosition] == 1)
			{
				decimal = decimal + binaryArray[digitPosition];
			}			 
		}
		String decimalS = Integer.toString(decimal);
		return decimalS;
	}
	/** This method converts a decimal number into a binary number. <br>
	*@param number Number in decimal to convert to binary, is assumed that is a valid decimal, must be a String.
	*@return binaryNumber Binary value of the number as a string.
	*/
	public static String decToBin(String number)
	{
		int module = 0;
		int divResult = Integer.parseInt(number);
		String binaryNumber = "";
		do
		{
			module = divResult%2;
			divResult = (int)(divResult/2);
			binaryNumber = (Integer.toString(module)) + binaryNumber;
		}while(divResult>0);
		
		return binaryNumber;
	}
	/** This method converts a hexadecimal number into a decimal. <br>
	*@param number Number in hexadecimal to convert to decimal, is assumed that is a valid hexadecimal. Must be a String.
	*@return decimalS Decimal value of the number as a string.
	*/
	public static String hexToDec(String number)
	{
		int decimal = 0;
		String hexNumber = number;
		
		String sString;
		int length = hexNumber.length();
		String[] hexNumberArray = new String[length];
		for (int cont = 0; cont < length; cont++)
		{
			sString = hexNumber.substring(cont,cont+1);
			hexNumberArray[cont] = sString;
		}//Se convierte la cadena con el numero hexadecimal en un arreglo de string, donde cada posicion es un digito _ _ = [_,_]
		
		String[] hexDigits = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
		int[] hexArray = new int[length];
		
		int lastPosValue = 1;
		hexArray[hexNumberArray.length-1] = lastPosValue; //Asigna a la ultima posicion del arreglo hexarray como la de valor 16^0
		
		for (int cont = hexNumberArray.length-1; cont > 0; cont-- ) //Asigna el valor de la posicion del sistema hex a cada posion del arreglo hex array.
		{
			hexArray[cont-1] = 16*lastPosValue;
			lastPosValue = 16*lastPosValue;
		}
		
		for (int digitPosition = 0 ; digitPosition < length; digitPosition++)
		{
			//Comparar valor del digito con el valor del arreglo hexDigits.
			for (int digitValue = 0; digitValue < hexDigits.length; digitValue++)
			{
				if (hexNumberArray[digitPosition].equals(hexDigits[digitValue]))
				{
					decimal = digitValue*hexArray[digitPosition] + decimal;
				}
			}			 
		}
		String decimalS = Integer.toString(decimal);
		return decimalS;		
	}
	/** This method converts a decimal number into a hexadecimal number. <br>
	*@param number Number in decimal to convert to hexadecimal, is assumed that is a valid decimal, must be a String.
	*@return hexNumber Hexadecimal value of the number as a string.
	*/
	public static String decToHex(String number)
	{
		String[] hexDigits = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
		
		int module = 0;
		int divResult = Integer.parseInt(number);
		String hexNumber = "";
		do
		{
			module = divResult%16;
			divResult = (int)(divResult/16);
			hexNumber = hexDigits[module] + hexNumber;
		}while(divResult>0);
		return hexNumber;
	}	
	
}