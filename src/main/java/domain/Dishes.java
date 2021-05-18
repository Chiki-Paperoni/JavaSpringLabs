package domain;

public abstract class Dishes  {
    private String name;
    private Material material;
    protected int measure;
    public boolean isBroken;
    public boolean isEmpty;

    public String getName() {
        return this.name;
    }
    public Material getMaterial(){
        return this.material;
    }
    public int getVolume() {
        return this.measure;
    }

    Dishes(String name,Material m,int measure) {
        this.name = name;
        this.material = m;
        this.isBroken = false;
        this.isEmpty = true;
        this.measure = measure;
    }
    Dishes(){}

}