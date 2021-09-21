package com.company;


public class Ship {

    enum Size {
        SIZE_10(10),
        SIZE_50(50),
        SIZE_100(100);

        private int value;

        private Size(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    enum Type {
        BREAD,
        BANANAS,
        CLOTHES
    }

    private int count;
    private Size size;
    private Type type;

    public Ship(Size size, Type type) {
        this.size = size;
        this.type = type;
    }

    public void add(int count) {
        this.count += count;
    }

    public boolean countCheck() {
        return count < size.getValue();
    }

    public int getCount() {
        return count;
    }

    public Type getType() {
        return type;
    }

    public Size getSize() {
        return size;
    }
}