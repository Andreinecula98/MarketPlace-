package Electronic;

public class MobilePhoneSpecifications {

    private String model;
    private Double display;
    private Integer memory;

    public MobilePhoneSpecifications(String model, Double display, Integer memory) {
        this.model = model;
        this.display = display;
        this.memory = memory;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getDisplay() {
        return display;
    }

    public void setDisplay(Double display) {
        this.display = display;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    @Override
    public String toString() {
        return "This is a " + getModel() + " with the display: " + getDisplay() + " and memory of: " + getMemory() + " GB";
    }
}
