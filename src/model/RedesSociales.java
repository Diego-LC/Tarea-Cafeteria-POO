package model;

public enum RedesSociales {
    INSTAGRAM("Instagram"),
    FACEBOOK("Facebook"),
    TWITTER("Twitter");
    private String red;

    private RedesSociales(String red) {
        this.red = red;
    }

    public String getCategoria() {
        return this.red;
    }
}
