package tqs.airquality;

public class AirQuality {
    private AirQualityInfo[] data;
    private long timestamp;

    public AirQuality() {

    }

    public AirQuality(AirQualityInfo[] data, long timestamp) {
        this.data = data;
        this.timestamp = timestamp;
    }

    public AirQualityInfo[] getData() {
        return data;
    }

    public void setData(AirQualityInfo[] data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
