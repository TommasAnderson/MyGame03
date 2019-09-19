package cn.edu.neaugame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.Frame;

public class MyGame03 extends Frame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Image planeImg = GameUtil.getImage("images/plane.png");
    Image bg = GameUtil.getImage("images/bg.jpg");

    Plane plane = new Plane(planeImg, 250, 250);
    // Shell shell = new Shell();
    Shell[] shells = new Shell[50];

    public void paint(Graphics g) { // 自动被调用，相当于一个画笔

        g.drawImage(bg, 0, 0, null);
        plane.drawSelf(g);// 画飞机

        for(int i=0;i<shells.length;i++){
            shells[i].draw(g);

            //飞机和炮弹的碰撞检测
            boolean peng = shells[i].getRect().intersects(plane.getRect());

            if (peng) {
                System.out.println("相撞了！！！");
                plane.live = false;
            }

        }

         // shell.draw(g);//画炮弹
            
        

    }

    class PaintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                // System.out.println("窗口被重画了一次");
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

    class KeyMonitor extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            plane.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            plane.minusDirection(e);
        }

        

    }

    public void launchFrame() {
        this.setTitle("Derek的第一个游戏");
        this.setVisible(true);
        this.setSize(Constant.GameFrame_WIDTH, Constant.GameFrame_HEIGHT);
        this.setLocation(300, 300);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        new PaintThread().start(); // 启动重画窗口的线程
        addKeyListener(new KeyMonitor()); //增加键盘的监听

        //初始化50个炮弹
        for (int i = 0; i < shells.length; i++) {
            shells[i]=new Shell();
        }

    }

    public static void main(String[] args) {
        MyGame03 f = new MyGame03();
        f.launchFrame();
    }

    private Image offScreenImage = null;

    public void update(Graphics g){
        if (offScreenImage == null) 
            offScreenImage = this.createImage(Constant.GameFrame_WIDTH,Constant.GameFrame_HEIGHT);
        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage,0,0,null);
        
    }





}
