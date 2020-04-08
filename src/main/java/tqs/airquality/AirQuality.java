package tqs.airquality;

public class AirQuality {
    private Fields[] data;
    private long timestamp;

    public Fields[] getData() {
        return data;
    }

    public void setData(Fields[] data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
