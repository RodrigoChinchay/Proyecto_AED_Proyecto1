import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;



public class Mainsys{
	private LeerData read = new LeerData();
	private DataReserva data;
	private Registro checkData;

	public static void main(String[] args) throws Exception {
		Mainsys mainsys = new Mainsys();
		Local can = new Local(mainsys.getRead().readLocalFile());
		
		DataReserva data = new DataReserva(mainsys.getRead().readResFile(), can);
		mainsys.setData(data);
		
		Registro verifData = new Registro(mainsys.getRead().registroFile(), mainsys.getRead().comprobadoFile(), mainsys.getRead().cancelacionesFile(), can);
		mainsys.setVerifData(verifData);
		Menu mainMenu = new Menu();
		mainMenu.menu(can, mainsys);
	}

	
	/*
	Crear una reservacion
	*/
	public Reservas createRes(Local can, String nombre, String fechaDe, boolean tipoReserva, int horas, String tipoCampo, int adultos, int menores, double deposito){
		Reservas newRes = new Reservas(nombre, fechaDe, tipoReserva, horas,  tipoCampo, adultos, deposito, menores );
		return newRes;
	}
		


	public boolean addToList(Reservas newRes, Local can){
		if(data.verificarDisponibilidad(newRes, can, newRes.getFechaDe(), newRes.getFechaHasta(), newRes.getTipoCampo())){
			data.getList().add(newRes);
			if(newRes.getNumeroHoras() > 1){
				for(int i = 1; i < newRes.getNumeroHoras(); i++){
					if(data.verificarDisponibilidad(newRes, can, newRes.getFechaDe(), newRes.getFechaHasta(), newRes.getTipoCampo())){

					}else{
						data.encontrarCancha(newRes.getNumero(), can);
						return false;
					}	
				}
				read.writeReservas(can, data);
				return true;
			}else if(newRes.getNumeroHoras() == 1){
				read.writeReservas(can, data);
				return true;
			}
		}else{
			return false;
		}
		return false;
	}
	
	/*
	Si la cancha esta disponible devuelve un true.
	*/
	public boolean checkCanchaNoWrite(Reservas newRes, Local can){
		if(data.verificarDisponibilidad(newRes, can, newRes.getFechaDe(), newRes.getFechaHasta(), newRes.getTipoCampo())){
			data.getList().add(newRes);
			if(newRes.getNumeroHoras() > 1){
				for(int i = 1; i < newRes.getNumeroHoras(); i++){
					if(data.verificarDisponibilidad(newRes, can, newRes.getFechaDe(), newRes.getFechaHasta(), newRes.getTipoCampo())){
					}else{
						data.encontrarCancha(newRes.getNumero(), can);
						return false;
					}
				}
				return true;
			}else if(newRes.getNumeroHoras() == 1){
				return true;
			}
		}else{
			return false;
		}
		return false;
	}
	
	
	/*
	
	Devuelve el costo total del objeto de reserva pasado	
	*/
	public double getTotalCost(Reservas newRes, Local can){
		int start = newRes.getFechaDe().get(Calendar.DAY_OF_WEEK);		
		if(start == 1){
			start = 6;
		}else{
			start = start - 2;
		}
		int numDays = newRes.getNumeroHoras();
		double[] rates = data.getTarifas(newRes, can);
		double totalCost = 0;
		if(numDays == 1){
			totalCost = rates[start];
		}else{	
			while(numDays > 0){
				while(start < rates.length){
					totalCost = totalCost + rates[start];
					start++;
					numDays--;
					if(numDays == 0){
						break;
					}
				}
				start = 0;
			}
		}
		int numCanchas = newRes.getNumeroHoras();
		totalCost = totalCost * numCanchas;
		boolean ap =newRes.isTipoReserva();
		if(ap == false){
			totalCost = ((totalCost / 100) * 5) + totalCost;
		}
		return totalCost;
	}
	
	public boolean isValidDate(String inDate) {// metodo importado de  http://www.javadb.com/check-if-a-string-is-a-valid-date

		if (inDate == null){
			System.out.println("Fecha esta vacia");
			return false;
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		if (inDate.trim().length() != dateFormat.toPattern().length()){
			System.out.println("Formato incorrecto");
			return false;
		}

		dateFormat.setLenient(false);
		
		try{
			
			dateFormat.parse(inDate.trim());
		}
		catch (ParseException pe) {
			System.out.println("Fecha invalida");
			return false;

		}
		return true;
	}
	
	public boolean FechaFutura(String date){
		if(isValidDate(date)){
			GregorianCalendar today = new GregorianCalendar();
			String[] f = date.split("/");
			int d = Integer.parseInt(f[0]);
			int m = Integer.parseInt(f[1]) - 1;
			int y = Integer.parseInt(f[2]);
			GregorianCalendar future =  new GregorianCalendar(y, m, d);
			if(future.getTimeInMillis() > today.getTimeInMillis()){
				return true;
			}
			System.out.println("Fecha no esta programada");
		}
		return false;
	}

	
	public LocalData getRead() {
		return read;
	}

	public DataReserva getData() {
		return data;
	}

	public Registro getCheckData() {
		return checkData;
	}

	public void setData(DataReserva data) {
		this.data = data;
	}

	public void setVerifData(Registro checkData) {
		this.checkData = checkData;
	}
}