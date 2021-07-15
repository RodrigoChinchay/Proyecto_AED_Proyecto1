import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DataReserva{
	private ArrayList<Reservas> list = new ArrayList<Reservas>();
	
	public DataReserva(){
	}
	
	/*
	Anadimos la Lista de reservas desde el archivo res.csv
	*/
	public DataReserva(String[][] resData, Local can){
		ArrayList<Canchas> rum;
		for(int i = 1; i< resData.length; i++){
			int number = Integer.parseInt(resData[i][0]);
			int count = 0;
			for(int z = 0; z< list.size(); z++){
				if(number == list.get(z).getNumero()){
					count++;
				}
			}
			if(count == 0){
				if(resData[i][7].equals("Classic Single") || resData[i][7].equals("Classic Twin") || resData[i][7].equals("Classic Double")){
					rum = can.getArray("3");
				}else if(resData[i][7].equals("Executie Double") || resData[i][7].equals("Executive Twin") || resData[i][7].equals("Executive Single")){
					rum = can.getArray("4");
				}else{
					rum = can.getArray("5");
				}
				
				String nombre = resData[i][1];
				boolean tipoReserva = Boolean.parseBoolean(resData[i][2]);
				String fechaDe = resData[i][3];
				int horas = Integer.parseInt(resData[i][5]);
				String tipoCampo = resData[i][7];
				int adultos = Integer.parseInt(resData[i][8]);
				int menores = Integer.parseInt(resData[i][9]);
				double deposito = Double.parseDouble(resData[i][11]);
				
				Reservas res = new Reservas(nombre, fechaDe, tipoReserva, horas, tipoCampo, adultos, deposito, menores);
				
			}
		}
	}
	
	/*
	Verificar que las canchas esten disponibles y agregamos una reservacion como objeto para ambos la lista de reservaciones y de las canchas estan asociadas entre si.
	*/
	public boolean verificarDisponibilidad(Reservas res, Local can, GregorianCalendar dateFrom,  GregorianCalendar dateTo, String tipoCampo){
		GregorianCalendar from = null;
		GregorianCalendar to = null;
		int pos = 0;
		for(int i = 0; i < can.getArray(can.getChoice()).size(); i++){
			if((tipoCampo.equals(can.getArray(can.getChoice()).get(i).getTipoCampo())) == true){
				if(can.getArray(can.getChoice()).get(i).getReservado().size() > 0){
					for(int j = 0; j < can.getArray(can.getChoice()).get(i).getReservado().size(); j++){
						from = can.getArray(can.getChoice()).get(i).getReservado().get(j).getFechaDe();
						to = can.getArray(can.getChoice()).get(i).getReservado().get(j).getFechaHasta();
						if((dateFrom.before(from) && dateTo.before(from)) || (dateFrom.after(to) && dateTo.after(to))){
							if(can.getArray(can.getChoice()).get(i).getReservado().size() == j+1){
									pos = i;
									can.getArray(can.getChoice()).get(pos).getReservado().add(res);
									return true;
							}
						}else{
							break;
						}
					}
				}else{
					can.getArray(can.getChoice()).get(i).getReservado().add(res);
					return true;
				}
			}
		}
		return false;
	}
	
	/*
	Obtiene los tipos de rangos de campo que estan asociado al objeto de reservacion.
	*/
	public double[] getTarifas(Reservas res, Local can){
		for(int i = 0; i < can.getArray(can.getChoice()).size(); i++){
			if((res.getTipoCampo().equals(can.getArray(can.getChoice()).get(i).getTipoCampo())) == true){
				return can.getArray(can.getChoice()).get(i).getTarifas();
			}
		}
		return null;
	}
	
	/*
	Busca una Reserva y la cancela, la elimina de la Lista de Reservas y la elimina de las salas asociadas. No escribe en archivos.	*/
	public boolean encontrarCancha(int num, Local can){
		GregorianCalendar greg = new GregorianCalendar();
		for(int i = 0; i < can.getArray(can.getChoice()).size(); i++){
			for(int j = 0; j < can.getArray(can.getChoice()).get(i).getReservado().size(); j++){
				if(num == can.getArray(can.getChoice()).get(i).getReservado().get(j).getNumero()){
					if(can.getArray(can.getChoice()).get(i).getReservado().get(j).isTipoReserva()){
						if((can.getArray(can.getChoice()).get(i).getReservado().get(j).getFechaHasta().getTimeInMillis() - greg.getTimeInMillis()) > 172800000 ){
							can.getArray(can.getChoice()).get(i).getReservado().remove(j);
						}else{
							System.out.println("Too close to checkin date to cancel!");
							return false;
						}
					}else{
						System.out.println("Not allowed cancel AP reservations!");
						return false;
					}
				}
			}
		}
		
		
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getNumero() == num){
				if((list.get(i).getFechaDe().getTimeInMillis() - greg.getTimeInMillis()) > 172800000 ){
					list.remove(i);
				}
			}
		}
		return true;
	}
	
	public boolean cancelaciones(int num, Local can, Mainsys mainsys){
		GregorianCalendar greg = new GregorianCalendar();
		boolean flip = false;
		Reservas cancelada = null;
		for(int i = 0; i < can.getArray(can.getChoice()).size(); i++){
			for(int j = 0; j < can.getArray(can.getChoice()).get(i).getReservado().size(); j++){
				if(num == can.getArray(can.getChoice()).get(i).getReservado().get(j).getNumero()){
					if(can.getArray(can.getChoice()).get(i).getReservado().get(j).isTipoReserva()){
						if((can.getArray(can.getChoice()).get(i).getReservado().get(j).getFechaDe().getTimeInMillis() - greg.getTimeInMillis()) > 172800000 ){
							cancelada = can.getArray(can.getChoice()).get(i).getReservado().get(j);
							can.getArray(can.getChoice()).get(i).getReservado().remove(j);
							flip = true;

						}else{
							System.out.println("Muy cerca de la fecha de control!");
							return false;
						}
					}else{
						System.out.println("No se permite cancelar las resevas!");
						return false;
					}
				}
			}
		}
		
		if(flip == false){
			return false;
		}
		mainsys.getCheckData().getCancelaciones().add(cancelada);
		mainsys.getRead().writeCancelaciones(can, mainsys.getCheckData());
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getNumero() == num){
				if((list.get(i).getFechaDe().getTimeInMillis() - greg.getTimeInMillis()) > 172800000 ){
					list.remove(i);
				}
			}
		}
		return true;
	}
	
	public ArrayList<Reservas> getList() {
		return list;
	}
}