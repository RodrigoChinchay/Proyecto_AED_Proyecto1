import java.util.ArrayList;
public class Canchas{
    //Atributos privados de canchas
    private String tipoCampo;
    private int minOccAdultos;
    private int minOccMenores;
    private int maxOccAdultos;	
	private int maxOccMenores;
    private int maxNoCanchas;
    //Atributo tarifa de acuerto a la cancha
    private double[] tarifas = new double[7];
    //Atributo objeto reservado que almacenara las diferentes reservas de canchas
    private ArrayList<Reservas> reservado = new ArrayList<Reservas>();
    //Constructor de canchas con atributos de metodo
    public Canchas (String tipoCampo, int minOccAdultos, int minOccMenores, int maxOccAdultos, int maxOccMenores, double[] tarifas, int maxNoCanchas) {
        this.tipoCampo = tipoCampo;
        this.minOccAdultos = minOccAdultos;
        this.minOccMenores = minOccMenores;
        this.maxOccAdultos = maxOccAdultos;
        this.maxOccMenores = maxOccMenores;
        this.tarifas = tarifas;
        this.maxNoCanchas = maxNoCanchas;
    }
    //Getters
    public ArrayList<Reservas> getReservado() {
        return reservado;
    }
    public String getTipoCampo() {
        return tipoCampo;
    }
    public int getMinOccAdultos() {
        return minOccAdultos;
    }
    public int getMinOccMenores() {
        return minOccMenores;
    } 
    public int getMaxOccAdultos() {
  		return maxOccAdultos;
  	}
	public int getMaxOccMenores() {
		return maxOccMenores;
	}
	public double[] getTarifas() {
		return tarifas;
	}
	public int getMaxNoCanchas() {
		return maxNoCanchas;
	}
    
}