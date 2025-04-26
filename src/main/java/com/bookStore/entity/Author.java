package com.bookStore.entity;

public class Author {
    private String id;
    private String name;
    private String biography;

    public Author() {
    }

    public Author(String id, String name, String biography) {
        this.id = id;
        this.name = name;
        this.biography = biography;
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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    // Getters and Setters

}
