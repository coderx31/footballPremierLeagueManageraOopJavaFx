import java.io.Serializable;

public class Address implements Serializable {
    private String number;
    private String road;
    private String town;
    private int postalCode;
    private String country;

    /*default constructor*/
    public Address(){
        this.number = null;
        this.road = null;
        this.town = null;
        this.postalCode = 0;
        this.country = null;
    }

    public Address(String number, String road, String town,
                   int postalCode, String country){
        this.number = number;
        this.road = road;
        this.town = town;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getNumber(){
        return this.number;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public String getRoad(){
        return this.road;
    }

    public void setRoad(String road){
        this.road = road;
    }

    public String getTown(){
        return this.town;

    }

    public void setTown(String town){
        this.town = town;
    }

    public int getPostalCode(){
        return this.postalCode;
    }

    public void setPostalCode(int postalCode){
        this.postalCode = postalCode;
    }

    public String getCountry(){
        return this.country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    @Override
    public String toString(){
        return "No: "+this.getNumber()+"\n"
                +this.getRoad()+"\n"
                +this.getTown()+"\n"
                +this.getCountry()+"\n"
                +this.getPostalCode();
    }
}
