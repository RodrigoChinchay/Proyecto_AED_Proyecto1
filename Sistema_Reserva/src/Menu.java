import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	/*
	Ejecute el menú. Si el menú termina, también lo hace el programa.	
	*/
	public void menu(Local can, Mainsys mainsys) throws IOException{
		boolean run = true;
		Scanner input = new Scanner(System.in);	
		while(run){
			boolean continueInput = true;
			
			System.out.println("Bienvenido al Sistema de Reservas de Canchas. Por favor ingresa su tipo de cancha:\n" + "Escoja la Calidad de Cancha a reservar:" +
					"1) Basic, 2) Normal, 3) Premium or Q)Salir");
			String choice = input.next().toUpperCase();
			do{
				try{
					if(choice.equals("3")|| choice.equals("4")|| choice.equals("5") || choice.equals("Q") ){
						continueInput  = false;
					}else{
						throw new InputMismatchException("Eleccion no valida");
					}
				}catch(InputMismatchException ex){
					System.out.println("Intenta de nuevo (entrada incorrecta)");
					System.out.println("Bienvenido al Sistema de Reservas de Canchas. Por favor ingresa su tipo de cancha:\n" + "Escoja la Calidad de Cancha a reservar:" +
							"1) Basic, 2) Normal, 3) Premium");
					input.nextLine();
					choice = input.next().toUpperCase();
				}
			}while(continueInput);
			if(choice.equals("Q")){
				System.out.println("Gracias por utilizar nuestro Sistema de Reservas.");
				break;
			}
			can.setChoice(choice);
			

			
			System.out.println("Logearse como : S)upervisor, D)admin, C)Cliente, Q)Salir");
			choice = input.next().toUpperCase();
			continueInput = true;
			do{
				try{
					if(choice.equals("S")|| choice.equals("D")|| choice.equals("C") || choice.equals("Q")){
						continueInput  = false;
					}else{
						throw new InputMismatchException("Eleccion no valida");
					}
				}catch(InputMismatchException ex){
					System.out.println("Intentar de nuevo (Ingreso incorrecto)");
					System.out.println("Logearse como: S)upervisor, D)Admin, C)Cliente, Q)Salir");;
					input.nextLine();
					choice = input.next().toUpperCase();
				}
			}while(continueInput);
			
			
			if(choice.equals("S")){
				System.out.println("Bienvenido porfavor ingrese la contraseña:");
				if(input.next().equals("super")){
					System.out.println("Bienvenido porfavor elija una opcion: N)ueva Reserva, C)ancelacion, Check I)n");
					choice = input.next().toUpperCase();
					continueInput = true;
					do{
						try{
							if(choice.equals("N")|| choice.equals("C") || choice.equals("I")  ){
								continueInput  = false;
							}else{
								throw new InputMismatchException("Eleccion no valida");
							}
						}catch(InputMismatchException ex){
							System.out.println("Intente de nuevo (Ingreso incorrecto)");
							System.out.println("Bienvenido ingrese un opcionn: N)ueva Reservacion, C)ancelar, Check I)n");
							input.nextLine();
							choice = input.next().toUpperCase();
						}
					}while(continueInput);
					
					
					
					if(choice.equals("N")){
						resMenu(can, mainsys);
					}else if(choice.equals("C")){
						
						System.out.println("Porfavor ingrese el numero de su reserva:");
						int resNum = 1;
						continueInput = true;
						do{
							try{
								resNum = input.nextInt();
								if(mainsys.getData().cancelaciones(resNum, can, mainsys)){
									System.out.println("Su reservacion fue removida de la base de datos");
									mainsys.getRead().writeReservas(can, mainsys.getData());
								}else{
									System.out.println("NUmero de reserva no encontrado!");
								}
								continueInput  = false;
				
							}catch(InputMismatchException ex){
								System.out.println("Intente de nuevo (Ingreso incorrecto: Ingresar un entero)");
								System.out.println("Porfavor ingresar su numero de reserva:");
								input.nextLine();
							}
						}while(continueInput);
						
					}else if(choice.equals("I")){						
						System.out.println("Porfavor ingresar su numero de reserva:");
						int resNum = 1;
						continueInput = true;
						do{
							try{
								resNum = input.nextInt();
								if(mainsys.getCheckData().crearRegistro(resNum, mainsys.getData())){
									System.out.println("Checked In");
									mainsys.getRead().writeReservas(can, mainsys.getData());
									mainsys.getRead().writeRegistro(can, mainsys.getCheckData());
								}else{
									System.out.println("No se puede registrar, o llegaste demasiado pronto o el número de reserva es incorrecto.");
								}
								continueInput  = false;
				
							}catch(InputMismatchException ex){
								System.out.println("Intente de nuevo (Ingreso incorrecto: Ingresar un entero)");
								System.out.println("Porfavor ingresar su numero de reserva:");
								input.nextLine();
							}
						}while(continueInput);
						
					}else if(choice.equals("D")){
				System.out.println("Bienvenido por favor ingrese su contraseña:");	
				if(input.next().equals("escritorio")){
					System.out.println("Bienvenido, elija una opción: Nueva reserva, C)ancelacion, Check I) n");
					choice = input.next().toUpperCase();
					continueInput = true;
					do{
						try{
							if(choice.equals("N")|| choice.equals("C") || choice.equals("I") ){
								continueInput  = false;
							}else{
								throw new InputMismatchException("opcion no valida");
							}
						}catch(InputMismatchException ex){
							System.out.println("intente de nuevo (Ingreso incorrecto)");
							System.out.println("Bienvenido, elija una opción: N) ew Reserva, C) ancelacion, Check I) n");
							input.nextLine();
							choice = input.next().toUpperCase();
						}
					}while(continueInput);
					
					
					
					
		
	/*
	Menu para la creacion de usuarios
	*/
	public void resMenu(Local can, Mainsys mainsys){
		boolean run = true;
		Scanner input = new Scanner(System.in);
		mainsys.getRead().printInfo();
		System.out.println("Arriba podemos observar nuestras tarifas y tipo de canchas para reservar");
		while(run){
			boolean available = true;
			while(available){
				
				System.out.println("Que tipo de cancha te gustaria?:");
				ArrayList<String> cTypes = new ArrayList<String>();
				cTypes = can.getTipoDeCanchaUnica(can.getArray(can.getChoice()));
				for(int i = 0; i < cTypes.size(); i++){
					System.out.println(i + ")" + cTypes.get(i));
				}
				String selection = input.next().toUpperCase();
				if(cTypes.size() == 4){
					boolean continueInput = true;
					do{
						try{
							if(selection.equals("0") || selection.equals("1") || selection.equals("2") || selection.equals("3")){
								continueInput  = false;
							}else{
								throw new InputMismatchException("opciones no validas");
							}
						}catch(InputMismatchException ex){
							System.out.println("Intentar de nuevo (ingreso incorrecto)");
							for(int i = 0; i < cTypes.size(); i++){
								System.out.println(i + ")" + cTypes.get(i));
							}
							input.nextLine();
							selection = input.next().toUpperCase();
						}
					}while(continueInput);
					
				}else{
					
					boolean continueInput = true;
					do{
						try{
							if(selection.equals("0") || selection.equals("1") || selection.equals("2")){
								continueInput  = false;
							}else{
								throw new InputMismatchException("Eleccion no valida");
							}
						}catch(InputMismatchException ex){
							System.out.println("Intentar de nuevo (Ingreso incorrecto)");
							for(int i = 0; i < cTypes.size(); i++){
								System.out.println(i + ")" + cTypes.get(i));
							}
							input.nextLine();
							selection = input.next().toUpperCase();
						}
					}while(continueInput);
				}
				int select = Integer.parseInt(selection);
				String tipoCampo = cTypes.get(select);
				
				boolean continueInput = true;
				
								
				System.out.println("Fecha de reserva(DD/MM/YYYY):");
				String from = input.next();
				while(mainsys.isDateInFuture(from) == false){
					System.out.println("Fecha de reserva(DD/MM/YYYY):");
					input.nextLine();
					from = input.next();
				}
				
				System.out.println("Que numero de horas estara usted?:");
				int numHoras = 1;
				continueInput = true;
		
				
				Reservas res = new Reservas("meh", from, true, numHoras, tipoCampo, 1,  10.0, 1); 
				
				if(mainsys.checkCanchaNoWrite(res, can)){
					System.out.println("Canchas disponibles.");
					mainsys.getData().encontrarCancha(res.getNumero(), can);
				}else{
					System.out.println("Canchas no disponibles.");
					break;
				}
			
				System.out.println("Porfavor ingresar su nombre:");
				input.nextLine();
				String name = input.nextLine();
				
							
								
				System.out.println("Porfavor ingresar el numero de adultos:");
				int adults = 1;
				continueInput = true;
				do{
					try{
						adults = input.nextInt();
						if(adults > can.getMaxAdultos(tipoCampo)){
							throw new InputMismatchException("Muchas personas.");
						}else if(adults < 1){
							throw new InputMismatchException("Debe ser menos gente.");
						}
						continueInput  = false;
		
					}catch(InputMismatchException ex){
						System.out.println("Intentar de nuevo (Ingreso incorrecto): " + ex +")");
						System.out.println("Porfavor ingresar el numero de personas:");
						input.nextLine();
					}
				}while(continueInput);
		
				
				System.out.println("Ingresar el numero de niños:");
				int children = 1;
				
				continueInput = true;
				do{
					try{
						children = input.nextInt();
						if(children > can.getMaxMenores(tipoCampo)){
							throw new InputMismatchException("Muchas personas");
						}else if(children < 0){
							throw new InputMismatchException("No puedo tener un número negativo.");
						}
						continueInput  = false;
		
					}catch(InputMismatchException ex){
						System.out.println("Intentar de nuevo (Ingreso no adecuado): " + ex +")");
						System.out.println("Ingresar el numero de niños:");
						input.nextLine();
					}
				}while(continueInput);
									
				Reservas newRes = mainsys.createRes(can, name, from,  numHoras, tipoCampo, adults, children, 0.0);
				double totalCost = mainsys.getTotalCost(newRes, can);
				System.out.println("Costo total: " + totalCost + "\n¿Le gustaría reservar esta cancha? S)Si N)No");
				Object choice = input.next().toUpperCase();
				continueInput = true;
				do{
					try{
						if(choice.equals("S") || choice.equals("N")){
							continueInput  = false;
						}else{
							throw new InputMismatchException("Opcion no valida");
						}
					}catch(InputMismatchException ex){
						System.out.println("Intente de nuevo (Ingreso incorrecto)");
						System.out.println("¿Le gustaría reservar esta cancha? S)si N)o");
						input.nextLine();
						choice = input.next().toUpperCase();
					}
				}while(continueInput);
				
				if(choice.equals("S")){
					
					System.out.println("¿Le gustaría reservar esta cancha?:"); 
					double deposit = 0.00;
					
					continueInput = true;
					do{
						try{
							deposit = input.nextDouble();
							if(deposit > totalCost){
								throw new InputMismatchException("Depósito demasiado grande");
							}else if(deposit < 0){
								throw new InputMismatchException("No puedo tener un número negativo.");
							}
							newRes.setDeposito(deposit);
							continueInput  = false;
			
						}catch(InputMismatchException ex){
							System.out.println("Intente de nuevo (ingreso incorrecto): " + ex + ")");
							System.out.println("¿Cuánto del depósito le gustaría pagar?:");
							input.nextLine();
						}
					}while(continueInput);
					
					if(mainsys.addToList(newRes, can)){
						System.out.println("Gracias por tu reserva. Su número de reserva es " + newRes.getNumero() + " Porfavor de recordar el numero para cancelaciones. DIVIERTASE!");
						System.out.println();
						run = false;
					}else{
						System.out.println("Nosotros no tenemos disponibles canchas en estos momentos");
						System.out.println();
					}
				}else if(choice.equals("N")){
					run = false;
				}
				break;
			}
		}
	}	
}

