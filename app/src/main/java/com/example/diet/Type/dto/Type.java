package com.example.diet.Type.dto;

public class Type {
    String _id;

    String typeName;

    boolean isActive;

    public Type() {
    }

    public Type(String _id, String typeName, boolean isActive) {
        this._id = _id;
        this.typeName = typeName;
        this.isActive = isActive;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
