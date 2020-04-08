package tqs.airquality;

public class City {
    private String name;
    private String coutry = "PT";

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoutry() {
        return coutry;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", coutry='" + coutry + '\'' +
                '}';
    }
}
