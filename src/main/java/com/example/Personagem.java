package com.example;

public class Personagem {
    
    private String id;
    private String name;
    private String supertype;
    private int hp;
    private String img;
    private Object types;


    
    public Personagem() {

    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public Object getTypes() {
        return types;
    }
    public void setTypes(Object types) {
        this.types = types;
    }

    public String getSupertype() {
        return supertype;
    }

    public void setSupertype(String supertype) {
        this.supertype = supertype;
    }

}
