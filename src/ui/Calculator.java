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
					result = factorial(inputInt, inputString, memory, memoryNames, a, mode, result);
					System.out.println(a + "! = \n" + result);
				break;
				case "sqrt":
					result = squareRoot(a);
					System.out.printf("sqrt(" + a + ") = " + "%.4f%n",result);
				break;
				case "^":
					b = operationValue(inputString, inputInt, memory, memoryNames, mode);
					result = intPower(inputInt, a, b);
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
			else //If it gets here it knows is a string, in order to go to memoryUse method.
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
	public static double factorial(Scanner inputString, Scanner inputInt, double[] memory, String[] memoryNames, double a, int mode, double result)
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
	public static double intPower(Scanner inputInt, double a, double b)
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