
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ACER
 */
public class Fish extends Rectangle {

    boolean isAlive = true;
    int isbigger;
    Image imgs;
    gamePanel gps;

    //gamePanel gp = new gamePanel();

    public Fish(int x, int y, int width, int height, int isBigger, Image imgs) {
        super(x, y, width, height);
        this.isbigger = isBigger;
        this.imgs = imgs;
    }

    public void DrawFish(Graphics g) {
        if (isAlive) {
            g.drawImage(imgs, x, y, width, height, gps);
        }
    }

}
