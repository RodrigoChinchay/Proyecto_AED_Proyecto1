import java.util.ArrayList;
import java.util.GregorianCalendar;
public class Registro {
    private ArrayList<Reservas> registrado = new ArrayList<Reservas>();
    private ArrayList<Reservas> comprobado = new ArrayList<Reservas>();
    private ArrayList<Reservas> cancelaciones = new ArrayList<Reservas>();
    
    public Registro(String[][] registrarData, String[][] comprobarData, String[][] cancelaciones, Local can){
        crearRegistro(registrarData, can);
        crearComprobado(comprobarData, can);
        crearCancelaciones(cancelaciones, can);
    }
    public void crearRegistro(String[][] checkInData, Local can){
        ArrayList<Canchas> rum;
        for(int i = 1; i< checkInData.length; i++){
            int number = Integer.parseInt(checkInData[i][0]);
            int count = 0;
            for(int z = 0; z< registrado.size(); z++){
                if(number == registrado.get(z).getNumero()){
                    count++;
                }
            }
            if(count == 0){
                if(checkInData[i][7].equals("Cancha Voley") || checkInData[i][7].equals("Cancha Futbol") || checkInData[i][7].equals("Cancha Basquet")){
                    rum = can.getArray("3");
                }else if(checkInData[i][7].equals("Cancha Full (Iluminada)") || checkInData[i][7].equals("Cancha Normal (Techada)") || checkInData[i][7].equals("Cancha Simple")){
                    rum = can.getArray("4");
                }else{
                    rum = can.getArray("5");
                }
                String nombre = checkInData[i][1];
                boolean tipoReserva = Boolean.parseBoolean(checkInData[i][2]);
                String fechaDe = checkInData[i][3];
                int horas = Integer.parseInt(checkInData[i][5]);
                int Canchas = Integer.parseInt(checkInData[i][6]);
                String tipoCampo = checkInData[i][7];
                int adultos = Integer.parseInt(checkInData[i][8]);
                int menores = Integer.parseInt(checkInData[i][9]);
                double deposito = Double.parseDouble(checkInData[i][10]);
                Reservas res = new Reservas(nombre, fechaDe, tipoReserva, horas, tipoCampo, adultos, deposito, menores);
                int loop = 1;                
            }
        }
    }
    public void crearComprobado(String[][] checkOutData, Local can){
        ArrayList<Canchas> rum;
        for(int i = 1; i< checkOutData.length; i++){
            int number = Integer.parseInt(checkOutData[i][0]);
            int count = 0;
            for(int z = 0; z< comprobado.size(); z++){
                if(number == comprobado.get(z).getNumero()){
                    count++;
                }
            }
            if(count == 0){
                if(checkOutData[i][7].equals("Cancha Voley") || checkOutData[i][7].equals("Cancha Futbol") || checkOutData[i][7].equals("Cancha Basquet")){
                    rum = can.getArray("3");
                }else if(checkOutData[i][7].equals("Cancha Full (Iluminada)") || checkOutData[i][7].equals("Cancha Normal (Techada)") || checkOutData[i][7].equals("Cancha Simple")){
                    rum = can.getArray("4");
                }else{
                    rum = can.getArray("5");
                }
                String nombre = checkOutData[i][1];
                boolean tipoReserva = Boolean.parseBoolean(checkOutData[i][2]);
                String fechaDe = checkOutData[i][3];
                int horas = Integer.parseInt(checkOutData[i][5]);
                int Cancha = Integer.parseInt(checkOutData[i][6]);
                String tipoCampo = checkOutData[i][7];
                int adultos = Integer.parseInt(checkOutData[i][8]);
                int menores = Integer.parseInt(checkOutData[i][9]);                
                double deposito = Double.parseDouble(checkOutData[i][10]);
                Reservas res = new Reservas(nombre, fechaDe, tipoReserva, horas, tipoCampo, adultos, deposito, menores);
                int loop = 1;
                double sevenYears = 220898664000.0;
                GregorianCalendar today = new GregorianCalendar();
                
            }
        }
    }
    public void crearCancelaciones(String[][] cancelacionData, Local can){
        ArrayList<Canchas> rum;
        for(int i = 1; i< cancelacionData.length; i++){
            int number = Integer.parseInt(cancelacionData[i][0]);
            int count = 0;
            for(int z = 0; z< cancelaciones.size(); z++){
                if(number == cancelaciones.get(z).getNumero()){
                    count++;
                }
            }
            if(count == 0){
                if(cancelacionData[i][7].equals("Cancha Voley") || cancelacionData[i][7].equals("Cancha Futbol") || cancelacionData[i][7].equals("Cancha Basquet")){
                    rum = can.getArray("3");
                }else if(cancelacionData[i][7].equals("Cancha Full (Iluminada)") || cancelacionData[i][7].equals("Cancha Normal (Techada)") || cancelacionData[i][7].equals("Cancha Simple\"")){
                    rum = can.getArray("4");
                }else{
                    rum = can.getArray("5");
                }
                String nombre = cancelacionData[i][1];
                boolean tipoReserva = Boolean.parseBoolean(cancelacionData[i][2]);
                String fechaDe = cancelacionData[i][3];
                int horas = Integer.parseInt(cancelacionData[i][5]);               
                String tipoCampo = cancelacionData[i][6];
                int adultos = Integer.parseInt(cancelacionData[i][7]);
                int menores = Integer.parseInt(cancelacionData[i][8]);
                double deposito = Double.parseDouble(cancelacionData[i][9]);
                Reservas res = new Reservas(nombre, fechaDe, tipoReserva, horas, tipoCampo, adultos, deposito, menores);
                int loop = 1;
                double sevenYears = 220898664000.0;
                GregorianCalendar today = new GregorianCalendar();
                
                }
            }
        }
    public boolean crearRegistro(int num, DataReserva data){
        GregorianCalendar today = new GregorianCalendar();
        for(int i = 0; i < data.getList().size(); i++){
            if(data.getList().get(i).getNumero() == num){
                if(data.getList().get(i).getFechaDe().after(today)){
                }else{
                    registrado.add(data.getList().get(i));
                    data.getList().remove(i);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean crearComprobado(int num){
        for(int i = 0; i < this.registrado.size(); i++){
            if(this.registrado.get(i).getNumero() == num){
                    comprobado.add(this.registrado.get(i));
                    registrado.remove(i);
                    return true;
            }
        }
        return false;
    }
    public ArrayList<Reservas> getRegistrado(){
        return registrado;
    }
    public ArrayList<Reservas> getComprobado() {
        return comprobado;
    }
    public ArrayList<Reservas> getCancelaciones() {
        return cancelaciones;
    }
}
