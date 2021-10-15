package com.example.zadatak1.BoxHandling;

public class Box {
    private String name;
    private int width;
    private int height;
    private int count;

    public Box(){}

    public Box(String name, int width, int height, int count) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
