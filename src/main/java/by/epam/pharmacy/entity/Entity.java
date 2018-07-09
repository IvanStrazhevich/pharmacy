package by.epam.pharmacy.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable, Cloneable{
    private static final long serialVersionUID = 7552272571756874906L;
    private int id;
    public Entity() {
    }
    public Entity(int id) {
        this.id = id;
    }
    public int getId() {
        return id; }
    public void setId(int id) { this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;

        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }

}
