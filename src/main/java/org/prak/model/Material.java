package org.prak.model;

public class Material {
    private int id;
    private String name;
    private String description;
    private int groupId;

    public Material(int id, String name, String description, int groupId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.groupId = groupId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getGroupId() {
        return groupId;
    }

    @Override
    public String toString() {
        return name;
    }
}
