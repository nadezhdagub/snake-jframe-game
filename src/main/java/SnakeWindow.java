import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeWindow extends JPanel implements ActionListener {

    public static JFrame jFrame;
    public static int speed = 10;
    public static final int SCALE = 32;
    public static final int WIDTH = 16;
    public static final int HEIGHT = 16;
    Timer timer = new Timer(1000/speed, this);

    Apple apple = new Apple(Math.abs((int)(Math.random() * SnakeWindow.WIDTH - 1)), Math.abs((int)(Math.random() * SnakeWindow.HEIGHT - 1)));
    Snake s = new Snake(5, 6, 5, 5);

    public SnakeWindow() {
        timer.start();
        addKeyListener(new KeyBoard());
        setFocusable(true);
    }

    public void paint (Graphics graphics) {
        graphics.setColor(Color.pink);
        graphics.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);

        for (int x = 0; x < WIDTH*SCALE; x+=SCALE) {
            graphics.setColor(Color.white);
            graphics.drawLine(x, 0, x,HEIGHT * SCALE);
        }

        for (int y = 0; y < HEIGHT*SCALE; y+=SCALE) {
            graphics.setColor(Color.white);
            graphics.drawLine(0,y, WIDTH * SCALE, y);
        }

        graphics.setColor(Color.red);
        graphics.fillOval(apple.posX * SCALE + 1, apple.posY * SCALE + 1, SCALE - 1, SCALE - 1);


        for (int l = 0; l < s.length; l++) {
            graphics.setColor(Color.darkGray);
            graphics.fillRect(s.sX[l] * SCALE + 1, s.sY[l] * SCALE + 1, SCALE - 1, SCALE - 1);
        }
    }

    public static void main(String[] args) {
        jFrame = new JFrame("Ssssnake");
        jFrame.add(new SnakeWindow());
        jFrame.setSize(WIDTH * SCALE + 16, HEIGHT * SCALE + 6);          // установка размеров
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);     // закрытие объекта с помощью крестика
        jFrame.setVisible(true);       // видимость объекта
        jFrame.setResizable(false);    // запрет растяжимости
        jFrame.setLocationRelativeTo(null);     // расположение по центру, потому что null
        jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\gitlab\\snake\\src\\main\\resources\\snake-cartoon-drawing.jpg"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        s.move();
        if ((s.sX[0] == apple.posX) && (s.sY[0] == apple.posY)) {
            apple.setRandomPosition();
            s.length++;
        }
        repaint();
    }

    public class KeyBoard extends KeyAdapter {
        public void keyPressed (KeyEvent event) {
            int key = event.getKeyCode();
            if (key == KeyEvent.VK_UP) s.direction = 0;
            if (key == KeyEvent.VK_DOWN) s.direction = 2;
            if (key == KeyEvent.VK_LEFT) s.direction = 3;
            if (key == KeyEvent.VK_RIGHT) s.direction = 1;
        }
    }
}
