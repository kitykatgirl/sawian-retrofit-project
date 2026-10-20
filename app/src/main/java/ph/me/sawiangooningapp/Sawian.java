package ph.me.sawiangooningapp;

import com.google.gson.annotations.SerializedName;
public class Sawian {
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("aim")
    private int aim;
    @SerializedName("move")
    private int move;
    @SerializedName("reload")
    private int reload;
    @SerializedName("health")
    private int health;
    @SerializedName("gameplay")
    private String gameplay;
    @SerializedName("weapon")
    private String weapon;

    public Sawian(String name, String description, int aim, int move, int reload, int health, String gameplay, String weapon) {
        this.name = name;
        this.description = description;
        this.aim = aim;
        this.move = move;
        this.reload = reload;
        this.health = health;
        this.gameplay = gameplay;
        this.weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAim() {
        return "Aim speed: " + aim + "%";
    }

    public String getMove() {
        return "Move speed: " + move + "%";
    }

    public String getReload() {
        return "Reload speed: " + reload + "%";
    }

    public String getHealth() {
        return "Health: " + health;
    }

    public String getGameplay() {
        return gameplay;
    }

    public String getWeapon() {
        return weapon;
    }
}
