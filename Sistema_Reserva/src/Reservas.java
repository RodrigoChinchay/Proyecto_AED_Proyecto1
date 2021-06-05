import java.util.GregorianCalendar;
public class Reservas{
    private String tipoCampo;
    private int numero;
    private String nombre;
    private boolean tipoReserva;
    private GregorianCalendar fechaDe;
    private String fechaD;
    private String fechaT;   
    private int numeroHoras;
    private GregorianCalendar fechaHasta;
    private int adultos;
    private int menores;
    private double costoTotal;
    private double deposito;
    /*
    Crear un nuevo objeto de reservas de usuario ingresado.
    */
    public Reservas(String nombre, String fechaD, boolean tipoReserva, int horas, String tipoCampo, int adultos, double deposito, int menores){
        this.numero = (int) (Math.random() * 1000);
        this.nombre = nombre;
        this.fechaD = fechaD;
        String[] f = fechaD.split("/");
        int d = Integer.parseInt(f[0]);
        int m = Integer.parseInt(f[1]) - 1;
        int y = Integer.parseInt(f[2]);
        int length = d + horas;
        this.fechaT = length + "/" + (m+1) + "/" + y;
        this.fechaDe = new GregorianCalendar(y, m, d);
        this.fechaHasta = new GregorianCalendar(y, m, d + horas);
        this.tipoReserva = tipoReserva;
        this.adultos = adultos;
        this.menores = menores;
        this.deposito = deposito;
        this.tipoCampo = tipoCampo;
    }
    /*
    //**Creacion del objeto de reservacion de res El truco aqui es obtener el numero de reservacion oh ticket de el archivo csv entonces ese es el usuario que podra cancelar con el mismo numero su reservacion
    */
    public Reservas(int numero, String nombre, String fechaD, boolean tipoReserva, int horas, String tipoCampo, int adultos, double deposito, int menores ){
        this.numero = numero;
        this.nombre = nombre;
        this.fechaD = fechaD;
        String[] f = fechaD.split("/");
        int d = Integer.parseInt(f[0]);
        int m = Integer.parseInt(f[1]) - 1;
        int y = Integer.parseInt(f[2]);
        int length = d + horas;
        this.fechaT = length + "/" + (m+1) + "/" + y;
        this.fechaDe = new GregorianCalendar(y, m, d);
        this.fechaHasta = new GregorianCalendar(y, m, d + horas);
        this.tipoReserva = tipoReserva;
        this.adultos = adultos;
        this.menores = menores ;
        this.deposito = deposito;
    }
    public String getNombre() {
        return nombre;
    }
    public int getNumero() {
        return numero;
    }
    public boolean isTipoReserva() {                //*Para crear una nueva reservacion
        return tipoReserva;
    }
    public GregorianCalendar getFechaDe() {
        return fechaDe;
    }
    public GregorianCalendar getFechaHasta() {
        return fechaHasta;
    }
    public String getTipoCampo() {
        return tipoCampo;
    }
    public int getAdultos() {
        return adultos;
    }
    public int getMenores(){
        return menores;
    }
    public double getCostoTotal() {
        return costoTotal;
    }
    public double getDeposito() {
        return deposito;
    }
    public String getFechaD() {
        return fechaD;
    }
    public String getFechaT() {
        return fechaT;
    }
    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }
    public void setDeposito(double deposito) {
        this.deposito = deposito;
    }
	public int getNumeroHoras() {
		return numeroHoras;
	}
	
	
}
