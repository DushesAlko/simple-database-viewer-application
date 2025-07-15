package org.prak.model;

public class PhisMechProperty {
    private String temperature;
    private String c; // единицы измерения
    private Float t20;
    private Float t50;
    private Float t100;
    private Float t150;
    private Float t200;
    private Float t250;
    private Float t300;
    private Float t350;
    private Float t400;
    private Float t450;
    private Float t500;

    public PhisMechProperty(String temperature, String c,
                            Float t20, Float t50, Float t100, Float t150,
                            Float t200, Float t250, Float t300, Float t350,
                            Float t400, Float t450, Float t500) {
        this.temperature = temperature;
        this.c = c;
        this.t20 = t20;
        this.t50 = t50;
        this.t100 = t100;
        this.t150 = t150;
        this.t200 = t200;
        this.t250 = t250;
        this.t300 = t300;
        this.t350 = t350;
        this.t400 = t400;
        this.t450 = t450;
        this.t500 = t500;
    }

    public String getTemperature() { return temperature; }
    public String getC() { return c; }
    public Float getT20() { return t20; }
    public Float getT50() { return t50; }
    public Float getT100() { return t100; }
    public Float getT150() { return t150; }
    public Float getT200() { return t200; }
    public Float getT250() { return t250; }
    public Float getT300() { return t300; }
    public Float getT350() { return t350; }
    public Float getT400() { return t400; }
    public Float getT450() { return t450; }
    public Float getT500() { return t500; }
}
