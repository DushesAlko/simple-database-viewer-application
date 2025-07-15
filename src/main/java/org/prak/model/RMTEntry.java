package org.prak.model;

public class RMTEntry {
    private int temperatureTime;
    private int t10;
    private int t30;
    private int t100;
    private int t300;
    private int t1000;
    private int t3000;
    private Integer t10000;
    private Integer t30000;
    private Integer t100000;
    private Integer t200000;
    private Integer t300000;

    public RMTEntry(int temperatureTime, int t10, int t30, int t100, int t300,
                    int t1000, int t3000, Integer t10000, Integer t30000,
                    Integer t100000, Integer t200000, Integer t300000) {
        this.temperatureTime = temperatureTime;
        this.t10 = t10;
        this.t30 = t30;
        this.t100 = t100;
        this.t300 = t300;
        this.t1000 = t1000;
        this.t3000 = t3000;
        this.t10000 = t10000;
        this.t30000 = t30000;
        this.t100000 = t100000;
        this.t200000 = t200000;
        this.t300000 = t300000;
    }

    public int getTemperatureTime() { return temperatureTime; }
    public int getT10() { return t10; }
    public int getT30() { return t30; }
    public int getT100() { return t100; }
    public int getT300() { return t300; }
    public int getT1000() { return t1000; }
    public int getT3000() { return t3000; }
    public Integer getT10000() { return t10000; }
    public Integer getT30000() { return t30000; }
    public Integer getT100000() { return t100000; }
    public Integer getT200000() { return t200000; }
    public Integer getT300000() { return t300000; }
}
