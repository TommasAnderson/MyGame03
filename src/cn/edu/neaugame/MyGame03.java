package cn.edu.neaugame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class MyGame03 extends JFrame {

    Image plane = GameUtil.getImage("images/plane.png");
    Image bg = GameUtil.getImage("images/bg.jpg");

    int planeX = 250, planeY = 250;

    public void paint(Graphics g) {

        g.drawImage(bg, 0, 0, null);
        g.drawImage(plane, planeX, planeY, null);
        planeX++;

    }

    class PaintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("窗口被重画了一次");
                repaint();

                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public void launchFrame() {
        this.setTitle("Derek的第一个游戏");
        this.setVisible(true);
        this.setSize(500, 500);
        this.setLocation(300, 300);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        new PaintThread().start(); // 启动重画窗口的线程
    }

    public static void main(String[] args) {
        MyGame03 f = new MyGame03();
        f.launchFrame();
    }

}
