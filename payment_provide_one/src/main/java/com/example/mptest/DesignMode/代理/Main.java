package com.example.mptest.DesignMode.代理;

// 定义一个接口
interface Image {
    void display();
}

// 定义一个实现接口的类
class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }

    private void loadFromDisk(String fileName) {
        System.out.println("Loading " + fileName);
    }
}

// 定义一个代理类，实现接口
class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}

// 使用代理类来获取 RealImage 对象
public class Main {
    public static void main(String[] args) {
        Image image = new ProxyImage("test.jpg");

        // 图像将从磁盘加载
        image.display();
        System.out.println("");

        // 图像不需要从磁盘加载
        image.display();
    }
}
