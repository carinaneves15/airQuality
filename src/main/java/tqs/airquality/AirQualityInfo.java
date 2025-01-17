package tqs.airquality;

/**
 * AirQualityInfo.java
 * Purpose: Selection of parameters to be obtained from the external API
 *
 * @author Carina Neves
 */
public class AirQualityInfo {
    private String aqi;
    private String o3;
    private String co;
    private String so2;
    private String no2;

    public AirQualityInfo() {
    }

    public AirQualityInfo(String aqi, String o3, String co, String so2, String no2) {
        this.aqi = aqi;
        this.o3 = o3;
        this.co = co;
        this.so2 = so2;
        this.no2 = no2;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getO3() {
        return o3;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

}
