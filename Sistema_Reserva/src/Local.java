import java.util.ArrayList;
import java.io.FileNotFoundException;
public class Local {
    private ArrayList<Canchas> threeStar = new ArrayList<Canchas>();
    private ArrayList<Canchas> fourStar =  new ArrayList<Canchas>();
    private ArrayList<Canchas> fiveStar =  new ArrayList<Canchas>();
    private String choice;
    /*
    Local crear los objetos seguido de los arreglos anteriores
    */
    public Local(String info[][]) throws FileNotFoundException{ 
        Cancha1(info);
        Cancha2(info);
        Cancha3(info);
    }
    /*Creamos segun el tipo de cancha que querramos elejir
     * 
     */
    public void Cancha1(String info[][]){
        for(int i = 0; i < 3; i++){
                String tipoCampo = info[i+9][1];
                int minOccAdultos = Character.getNumericValue(info[i+9][3].charAt(0));
                int minOccMenores = Character.getNumericValue(info[i+9][3].charAt(2));
                int maxOccAdultos = Character.getNumericValue(info[i+9][4].charAt(0));
                int maxOccMenores = Character.getNumericValue(info[i+9][4].charAt(2));
                int noDeCanchas = Integer.parseInt(info[i+9][2]);
                double[] tarifas = new double[7];
                for(int r = 0; r < 7; r++){
                    tarifas[r] = Double.parseDouble(info[i+9][r+5]);
                }
                for(int z = 0; z < noDeCanchas; z++){
                    Canchas rum = new Canchas(tipoCampo, minOccAdultos, minOccMenores, maxOccAdultos, maxOccMenores, tarifas, noDeCanchas);
                    threeStar.add(rum);
                }
        }
    }
    public void Cancha2(String info[][]){
        for(int i = 0; i < 3; i++){
                String tipoCampo = info[i+6][1];
                int minOccAdultos = Character.getNumericValue(info[i+6][3].charAt(0));
                int minOccMenores = Character.getNumericValue(info[i+6][3].charAt(2));
                int maxOccAdultos = Character.getNumericValue(info[i+6][4].charAt(0));
                int maxOccMenores = Character.getNumericValue(info[i+6][4].charAt(2));
                int noDeCanchas = Integer.parseInt(info[i+6][2]);
                double[] tarifas = new double[7];
                for(int r = 0; r < 7; r++){
                    tarifas[r] = Double.parseDouble(info[i+6][r+5]);
                }
                for(int z = 0; z < noDeCanchas; z++){
                    Canchas rum = new Canchas(tipoCampo, minOccAdultos, minOccMenores, maxOccAdultos, maxOccMenores, tarifas, noDeCanchas);
                    fourStar.add(rum);
                }
        }
    }
    public void Cancha3(String info[][]){
        for(int i = 0; i < 4; i++){
                String tipoCampo = info[i+2][1];
                int minOccAdultos = Character.getNumericValue(info[i+2][3].charAt(0));
                int minOccMenores = Character.getNumericValue(info[i+2][3].charAt(2));
                int maxOccAdultos = Character.getNumericValue(info[i+2][4].charAt(0));
                int maxOccMenores = Character.getNumericValue(info[i+2][4].charAt(2));
                int noDeCanchas = Integer.parseInt(info[i+2][2]);
                double[] tarifas = new double[7];
                for(int r = 0; r < 7; r++){
                    tarifas[r] = Double.parseDouble(info[i+2][r+5]);
                }
                for(int z = 0; z < noDeCanchas; z++){
                    Canchas rum = new Canchas(tipoCampo, minOccAdultos, minOccMenores, maxOccAdultos, maxOccMenores, tarifas, noDeCanchas);
                    fiveStar.add(rum);
                }
        }
    }
    public String getArrayString(String choice){
        String local = "3Star";
        if(choice.equals("3")){
            local = "3Star";
        }else if(choice.equals("4")){
            local = "4Star";
        }else if(choice.equals("5")){
            local = "4Star";
        }
        return local;
    }
    /*
    Conseguir una lista de los tipos de campos de juego asociados con el centro deportivo seleccionado, de tal forma que el usuario puede selccionar una de estas.
    */
    public ArrayList<String> getTipoDeCanchaUnica(ArrayList<Canchas> list){
        ArrayList<String> unica = new ArrayList<String>();
        unica.add(list.get(0).getTipoCampo());
        for(int i = 1; i < list.size(); i++){
            if(list.get(i).getTipoCampo() != list.get(i - 1).getTipoCampo()){
                unica.add(list.get(i).getTipoCampo());
            }
        }
        return unica;
    }
    public ArrayList<Canchas> getThreeStar() {
        return threeStar;
    }
    public ArrayList<Canchas> getFourStar() {
        return fourStar;
    }
    public ArrayList<Canchas> getFiveStar() {
        return fiveStar;
    }
    public ArrayList<Canchas> getArray(String choice){
        ArrayList<Canchas> cancha;
        cancha = getThreeStar();
        if(choice.equals("3")){
            cancha = getThreeStar();
        }else if(choice.equals("4")){
            cancha = getFourStar();
        }else if(choice.equals("5")){
            cancha = getFiveStar();
        }
        return cancha;
    }
    public String getChoice() {
        return choice;
    }
    public void setChoice(String choice) {
        this.choice = choice;
    }
    public int getMaxAdultos(String tipoCampo){
        int max = 0; 
            for(int i = 0; i < getArray(this.choice).size(); i++){
                if((tipoCampo.equals(getArray(this.choice).get(i).getTipoCampo())) == true){
                        max = getArray(this.choice).get(i).getMaxOccAdultos();
                        return max;
                }
            }
        return max;
    }
    public int getMaxMenores(String tipoCampo){
        int max = 0; 
        for(int i = 0; i < getArray(this.choice).size(); i++){
            if((tipoCampo.equals(getArray(this.choice).get(i).getTipoCampo())) == true){
                    max = getArray(this.choice).get(i).getMaxOccMenores();
                    return max;
            }
        }
        return max;
    }
    public int getMaxCanchas(String tipoCampo){
        int max = 0; 
        for(int i = 0; i < getArray(this.choice).size(); i++){
            if((tipoCampo.equals(getArray(this.choice).get(i).getTipoCampo())) == true){
                    max = getArray(this.choice).get(i).getMaxOccMenores();
                    return max;
            }
        }
        return max;
    }
}