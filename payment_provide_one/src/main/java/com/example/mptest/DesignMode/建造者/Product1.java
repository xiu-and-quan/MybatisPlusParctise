package com.example.mptest.DesignMode.建造者;

public class Product1 {
    private String partA;
    private String partB;
    private String partC;

    public void setPartA(String partA) {
        this.partA = partA;
    }

    public void setPartB(String partB) {
        this.partB = partB;
    }

    public void setPartC(String partC) {
        this.partC = partC;
    }

    public String getPartA() {
        return partA;
    }

    public String getPartB() {
        return partB;
    }

    public String getPartC() {
        return partC;
    }
}

interface Builder {
    void buildPartA(String partA);
    void buildPartB(String partB);
    void buildPartC(String partC);
    Product1 getResult();
}

class ConcreteBuilder implements Builder {
    private Product1 product = new Product1();

    @Override
    public void buildPartA(String partA) {
        product.setPartA(partA);
    }

    @Override
    public void buildPartB(String partB) {
        product.setPartB(partB);
    }

    @Override
    public void buildPartC(String partC) {
        product.setPartC(partC);
    }

    @Override
    public Product1 getResult() {
        return product;
    }
}

class Director {
    public void construct(Builder builder) {
        builder.buildPartA("PartA");
        builder.buildPartB("PartB");
        builder.buildPartC("PartC");
    }
}

class Main {
    public static void main(String[] args) {
        Director director = new Director();
        Builder builder = new ConcreteBuilder();
        director.construct(builder);
        Product1 product = builder.getResult();
        System.out.println(product.getPartA());
        System.out.println(product.getPartB());
        System.out.println(product.getPartC());
    }
}