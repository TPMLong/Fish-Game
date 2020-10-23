
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ACER
 */
public class gamePanel extends javax.swing.JPanel {

    /**
     * Creates new form gamePanel
     */
    Fish[] list;// danh sach ca ke thu
    Fish nemo;// nhan vat cá của mình
    Thread t;
    boolean cont = true;
    int MAX = 10;
    int mark = 0;
    int smallfish = 0;
    Dimension d;
    BufferedImage img = null;
    BufferedImage nemoimg = null;
    BufferedImage bigimg = null;
    BufferedImage smallimg = null;

    public gamePanel() {

        try {
            img = ImageIO.read(new File("1.jpg"));
            nemoimg = ImageIO.read(new File("6.png"));
            smallimg = ImageIO.read(new File("smalls.png"));
            bigimg = ImageIO.read(new File("bigss.png"));
            //  ImageIcon icon = new ImageIcon("1.jpg");
            //  Image scaleImage = icon.getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            System.out.println(e);
        }

        initComponents();
        InitGame();

        //icon = new ImageIcon("1.jpg");
        t = new Thread(new Runnable() {

            @Override
            public void run() {
                while (cont) {
                    for (Fish fish : list) {
                        Random r = new Random();
                        int huong = (int) Math.round(Math.random() * 100) % 4;
                        int speed = r.nextInt(50) + 10;
                        switch (huong) {
                            case 0: // di len
                                for (int i = 0; i < speed; i++) {
                                    if (fish.y > 10) {
                                        fish.y -= 2;
                                    }
                                    try {
                                        Thread.sleep(2);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(gamePanel.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    isEat();
                                    if (isWin() || gameOver()) {
                                        cont = false;
                                        int choice = JOptionPane.showConfirmDialog(null, "Play again?", "", JOptionPane.YES_NO_OPTION);
                                        if (choice == JOptionPane.YES_OPTION) {
                                            InitGame();
                                        } else {
                                            System.exit(1);
                                        }
                                    }

                                    repaint();
                                }
                                break;
                            case 1:
                                for (int i = 0; i < speed; i++) {
                                    if (fish.x < 2000) {
                                        fish.x += 2;
                                    }

                                    try {
                                        Thread.sleep(2);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(gamePanel.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    isEat();
                                    if (isWin() || gameOver()) {
                                        cont = false;
                                        int choice = JOptionPane.showConfirmDialog(null, "Play again?", "", JOptionPane.YES_NO_OPTION);
                                        if (choice == JOptionPane.YES_OPTION) {
                                            InitGame();
                                        } else {
                                            System.exit(1);
                                        }
                                    }
                                    repaint();
                                }
                                break;
                            case 2:
                                for (int i = 0; i < speed; i++) {
                                    if (fish.y < 1400) {
                                        fish.y += 2;
                                    }
                                    try {
                                        Thread.sleep(2);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(gamePanel.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    isEat();
                                    if (isWin() || gameOver()) {
                                        cont = false;
                                        int choice = JOptionPane.showConfirmDialog(null, "Play again?", "", JOptionPane.YES_NO_OPTION);
                                        if (choice == JOptionPane.YES_OPTION) {
                                            InitGame();
                                        } else {
                                            System.exit(1);
                                        }
                                    }
                                    repaint();
                                }
                                break;
                            case 3:
                                for (int i = 0; i < speed; i++) {
                                    if (fish.x > 10) {
                                        fish.x -= 2;
                                    }

                                    try {
                                        Thread.sleep(2);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(gamePanel.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    isEat();
                                    if (isWin() || gameOver()) {
                                        cont = false;
                                        int choice = JOptionPane.showConfirmDialog(null, "Play again?", "", JOptionPane.YES_NO_OPTION);
                                        if (choice == JOptionPane.YES_OPTION) {
                                            InitGame();
                                        } else {
                                            System.exit(1);
                                        }
                                    }
                                    repaint();
                                }
                                break;

                        } // end swich

                    }

//                    if (isWin()) {
//                        cont = false;
//                        System.out.println("IsWin");
//                    }
//                    if (gameOver()) {
//                        cont = false;
//
//                        //ve chu GameOver len panel
//                    }
//                    if (isWin()) {
//                        cont = false;
//                        //hoi ng dung co muon choi lai hay khong
//                    }
                    repaint();
                    try {
                        Thread.sleep(200);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    private boolean isWin() {
        if (mark == smallfish) {
            return true;
        }
        return false;
    }

    private void isEat() {

        for (Fish fish : list) {
            if (fish.isbigger == 0 && fish.intersects(nemo) && fish.isAlive) {
                //  if (list[i].isbigger == 0 && list[i].intersects(nemo)) {
                mark++;
                fish.isAlive = false;
                //   System.out.println("+" + j);
//                    if(a[j] == i){
//                        for (int k = j; k < a.length - 1; k++) {
//                            a[k] = a[k + 1];
//                        }
//                    }
            }
            //return mark;
        }
        // }
    }

    private boolean gameOver() {
        for (Fish fish : list) {
            if (fish.isbigger == 1 && fish.intersects(nemo)) {
                return true;
            }
        }
        return false;
    }

    private void InitGame() {
        nemo = new Fish(0, 0, 50, 40, -1, nemoimg);
        list = new Fish[MAX];

        int i;
        for (i = 0; i < MAX; i++) {
            Random r = new Random();
            int x = r.nextInt(1000) + 50;
            int y = r.nextInt(800) + 30;
            int w = r.nextInt(70) + 10;
            int h = r.nextInt(50) + 10;
            
            if (w * h > 2000) {
                list[i] = new Fish(x, y, w, h, 1, bigimg);
            } else {
                list[i] = new Fish(x, y, w, h, 0, smallimg);
                smallfish++;
//                a[j] = i;
//
//                System.out.println(j);
//                System.out.println(i);
//                System.out.println(a[j]);
//                j++;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Dimension d = getSize();

        // g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.

        g.drawImage(img, 0, 0, 2000, 1050, this);
        nemo.DrawFish(g);
        for (Fish fish : list) {
            fish.DrawFish(g);
        }
        if (!cont && mark < smallfish) {
            g.setFont(new Font("Arial", Font.BOLD, 100));
            g.setColor(Color.BLACK);
            g.drawString("GAME OVER", 1100, 500);
        }
        if ( !cont && mark == smallfish) {
            g.setFont(new Font("Arial", Font.BOLD, 100));
            g.setColor(Color.GREEN);
            g.drawString("WIN", 1100, 500);
        }
        cont = true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1380, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
