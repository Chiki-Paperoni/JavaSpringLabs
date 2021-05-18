package domain;

public enum Material {
    PORCELAIN("farfor"),
    FAIENCE("fayans"),
    CERAMICS("keramika"),
    SILVER("serebro"),
    GOLD("zoloto"),
    GLASS("steklo");

    private final String material;

    Material(String m) {
        this.material = m;
    }

    @Override
    public String toString() {
        return this.material;
    }
}
