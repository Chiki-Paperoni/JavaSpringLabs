package domain;

public class SauceBowl extends Dishes {

    private int volume;

    SauceBowl(String name, Material material, int volume) {
        super(name, material,volume);
        this.volume = volume;
    }

    public int getVolume() {
        return this.volume;
    }
}
