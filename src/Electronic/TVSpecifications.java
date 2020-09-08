package Electronic;

public class TVSpecifications {

    private String model;
    private Integer display;
    private String resolution;

    public TVSpecifications(String model, Integer display,String resolution) {

        this.model = model;
        this.display = display;
        this.resolution = resolution;

    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
}
