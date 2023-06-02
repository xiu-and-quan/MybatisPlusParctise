package com.example.mptest.DesignMode.建造者;

public class 静态内部类建造者 {
}
class Product {
    private String partA;
    private String partB;
    private String partC;

    private Product(Builder builder) {
        this.partA = builder.partA;
        this.partB = builder.partB;
        this.partC = builder.partC;
    }

    /**
     * 静态内部类，调用时才会类加载
     */
    public static class Builder {
        private String partA;
        private String partB;
        private String partC;

        public Builder() {}

        public Builder withPartA(String partA) {
            this.partA = partA;
            return this;
        }

        public Builder withPartB(String partB) {
            this.partB = partB;
            return this;
        }

        public Builder withPartC(String partC) {
            this.partC = partC;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
