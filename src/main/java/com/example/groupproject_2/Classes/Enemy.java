package com.example.groupproject_2.Classes;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Enemy {
    private String name;
    private double health;
    private ImageView imageView;

    public Enemy() {
    }

    public Enemy(String name, double health, Image image) {
        this.name = name;
        this.health = health;
        this.imageView = new ImageView(image);
    }

    public Image getImage() {
        return imageView.getImage();
    }
    public void setImage(Image image) {
        this.imageView.setImage(image);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", imageView=" + imageView +
                '}';
    }
    public String healthToString() {
        return "Health: " + health;
    }
}
