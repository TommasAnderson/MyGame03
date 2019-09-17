package cn.edu.neaugame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class MyGame03 extends JFrame {

    Image planeImg = GameUtil.getImage("images/plane.png");
    Image bg = GameUtil.getImage("images/bg.jpg");

    Plane plane = new Plane(planeImg, 250, 250);
    Plane plane2 = new Plane(planeImg, 350, 250);
    Plane plane3 = new Plane(planeImg, 450, 250);

    public void paint(Graphics g) { //自动被调用，相当于一个画笔

        g.drawImage(bg, 0, 0, null);
        plane.drawSelf(g);//画飞机
        plane2.drawSelf(g);
        plane3.drawSelf(g);

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
