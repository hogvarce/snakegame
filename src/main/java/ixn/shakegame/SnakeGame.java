package ixn.shakegame;

import ixn.shakegame.objects.Rabbit;
import ixn.shakegame.objects.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Антон on 20.08.2016.
 */
public class SnakeGame extends JPanel implements ActionListener{

    public static final int SCALE = 32;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static final int SPEED = 5;

    Snake s = new Snake(10, 10, 9, 10);
    Rabbit r = new Rabbit((int)(Math.random()*WIDTH), (int)(Math.random()*HEIGHT));
    Timer t = new Timer(1000/SPEED, this);

    public SnakeGame() {
        t.start();
        addKeyListener(new Keyboard());
        setFocusable(true);

    }

    public void paint(Graphics g) {
        g.setColor(color(5, 50, 10));
        g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
//        g.setColor(color(255,216,0));

//        for (int x = 0; x <= WIDTH*SCALE; x += SCALE) {
//            g.drawLine(x, 0, x, HEIGHT*SCALE);
//        }
//
//        for (int y = 0; y <= HEIGHT*SCALE; y += SCALE) {
//            g.drawLine(0, y, WIDTH*SCALE, y);
//        }

        for (int d = 0; d < s.length; d++) {
            ImageIcon imageIcon;
            Image image;
            int max = s.length - 1;
            if (d == 0) {
                imageIcon = new ImageIcon("src/main/resource/images/snakehead.png"); // load the image to a imageIcon
                image = imageIcon.getImage(); // transform it
                g.drawImage(ScaledImage(image, SCALE, SCALE, (s.direction * 90)), s.snakeX[d] * SCALE, s.snakeY[d] * SCALE, this);
            } else if (d == max) {
                imageIcon = new ImageIcon("src/main/resource/images/snakeend.png"); // load the image to a imageIcon
                image = imageIcon.getImage(); // transform it
                g.drawImage(ScaledImage(image, SCALE, SCALE, (s.direction * 90)*s.directions[d]), s.snakeX[d] * SCALE, s.snakeY[d] * SCALE, this);
            } else {
                g.setColor(color(200,150,0));
                g.fillRect(s.snakeX[d]*SCALE, s.snakeY[d]*SCALE, SCALE, SCALE);
            }
        }

        ImageIcon imageIcon = new ImageIcon("src/main/resource/images/rabbit2.gif"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        g.drawImage(ScaledImage(image, SCALE, SCALE, 0), r.posX*SCALE+1, r.posY*SCALE+1, this);
    }

    public Color color(int red, int green, int blue) {
        return new Color(red, green, blue);
    }

    private static Image ScaledImage(Image img, int w, int h, double r) {
        final BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.rotate(Math.toRadians(r), w/2, h/2);
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.drawImage(img, 0, 0, w, h, null);
        graphics2D.dispose();
        return bufferedImage;
    }

    public static void main(String[] args) throws MalformedURLException {
        JFrame f = new JFrame("Snake");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setSize(WIDTH*SCALE+SCALE/4, HEIGHT*SCALE+SCALE);
        f.setLocationRelativeTo(null);
        f.add(new SnakeGame());
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        s.move();
        for(int i = 1; i < s.length; i++) {
            if( s.snakeX[i] == s.snakeX[0] && s.snakeY[i] == s.snakeY[0] )
                s.length = 2;
        }
        if ( (s.snakeX[0] == r.posX) && (s.snakeY[0] == r.posY) ) {
            s.length++;
            r.setRandomPosition();
        }
        for(int i = 1; i < s.length; i++) {
            if( s.snakeX[i] == r.posX && s.snakeY[i] == r.posY )
                r.setRandomPosition();
        }
        repaint();
    }

    private class Keyboard extends KeyAdapter {
        public void keyPressed(KeyEvent kEvt) {

            int key = kEvt.getKeyCode();
            if((key == KeyEvent.VK_RIGHT) && s.direction != 2) s.direction = 0;
            if((key == KeyEvent.VK_DOWN) && s.direction != 3) s.direction = 1;
            if((key == KeyEvent.VK_LEFT) && s.direction !=0) s.direction = 2;
            if((key == KeyEvent.VK_UP) && s.direction != 1) s.direction = 3;
        }
    }
}
