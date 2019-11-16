package models;

/**
 * @Created by Terrax on 28-Feb-2016.
 */
public abstract class Entity {
    protected int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}