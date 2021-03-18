package refactoring.longparamerter;

public class GroomRequest {

    private final String serviceRequest;
    private final Double price;
    private final String groomerName;
    private final Boolean callWhenReady;

    public GroomRequest(String serviceRequest, Double price, String groomerName, Boolean callWhenReady) {
        this.serviceRequest = serviceRequest;
        this.price = price;
        this.groomerName = groomerName;
        this.callWhenReady = callWhenReady;
    }

    public String getServiceRequest() {
        return serviceRequest;
    }

    public Double getPrice() {
        return price;
    }

    public String getGroomerName() {
        return groomerName;
    }

    public Boolean getCallWhenReady() {
        return callWhenReady;
    }
}
