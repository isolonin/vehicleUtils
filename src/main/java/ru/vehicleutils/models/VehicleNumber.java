package ru.vehicleutils.models;

/**
 *
 * @author ivan
 */
public class VehicleNumber implements Comparable<VehicleNumber>{
    private Integer transportId;
    private String transportChars;
    private Integer transportReg;
    private String textTail;
    
    public VehicleNumber(String transportChars, String transportId, String transportReg) {
        this.transportChars = transportChars;
        if(transportId != null){
            this.transportId = new Integer(transportId);
        }        
        if(transportReg != null){
            this.transportReg = new Integer(transportReg);
        }
    }

    public VehicleNumber() {
    }
    
    public Integer getCost(){
        Integer cost = 0;
        if(transportId != null) cost++;
        if(transportChars != null) cost++;
        if(transportReg != null) cost++;
        return cost;
    }

    public Integer getTransportId() {
        return transportId;
    }

    public void setTransportId(Integer transportId) {
        this.transportId = transportId;
    }

    public String getTransportChars() {
        return transportChars;
    }

    public void setTransportChars(String transportChars) {
        this.transportChars = transportChars;
    }

    public Integer getTransportReg() {
        return transportReg;
    }

    public void setTransportReg(Integer transportReg) {
        this.transportReg = transportReg;
    }

    public String getTextTail() {
        return textTail;
    }

    public void setTextTail(String textTail) {
        this.textTail = textTail;
    }

    @Override
    public String toString() {
        return "transportId: "+transportId+", transportChars: "+transportChars+", transportReg: "+transportReg+", text: "+textTail;
    }

    @Override
    public int compareTo(VehicleNumber t) {
        return this.getCost().compareTo(t.getCost());
    }
}
