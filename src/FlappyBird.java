
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    private static final long FLAP_INTERVAL_NANOS = 200_000_000L;

    int boardWidth = 360;
    int boardHeight = 640;

    //Image variables
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    //Bird
    int birdX = boardWidth / 8;
    int birdY = boardHeight / 2;
    int birdWidth = 34;
    int birdHeight = 24;

    class Bird {

        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird(Image img) {
            this.img = img;
        }
    }

    //pipes
    int pipeX = boardWidth;
    int pipeY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    class pipe {

        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img;
        boolean passed = false;

        pipe(Image img) {
            this.img = img;
        }
    }

    //Game logic
    Bird bird;
    int velocityX = -4; //move pipes to the left speed (simulates bird moving right)
    int velocityY = 0; //move bird up/down speed
    int gravity = 1;

    ArrayList<pipe> pipes;
    Random random = new Random();

    Timer gameLoop;
    Timer PlacePipesTimer;
    boolean gameOver = false;
    double score = 0;
    private boolean spaceHeld = false;
    private long lastFlapNanos = 0;

    public FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        // setBackground(Color.blue);
        setFocusable(true);
        addKeyListener(this);
        //Load images
        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();

        //bird
        bird = new Bird(birdImg);
        pipes = new ArrayList<pipe>();

        //place pipes timer
        PlacePipesTimer = new Timer(1500, new ActionListener() {
            @Override //proti 1.5s e notun pipe
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        PlacePipesTimer.start();

        //game timer
        gameLoop = new Timer(1000 / 60, this); // 1000ms/60frame = 16.6(fpms) (60fps)
        gameLoop.start();
    }

    public void placePipes() {
        //(0-1) * pipeHeight/2 -> (0-256)
        //128
        //0-128 (0-256) --> pipeHeight/4 -> 3/4 pipeHeight

        int randomPipeY = (int) (pipeY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = boardHeight / 4;

        pipe topPipe = new pipe(topPipeImg);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        pipe bottompipe = new pipe(bottomPipeImg);
        bottompipe.y = topPipe.y + pipeHeight + openingSpace;
        pipes.add(bottompipe);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // System.out.println("Draw");
        //background
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);

        //bird
        g.drawImage(birdImg, bird.x, bird.y, bird.width, bird.height, null);

        //pipes
        for (int i = 0; i < pipes.size(); i++) {
            pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }

        //score
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (gameOver) {
            g.drawString("Game Over: " + String.valueOf((int) score), 10, 35);
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            String retryMessage = "Press SPACE to try again";
            FontMetrics metrics = g.getFontMetrics();
            g.drawString(retryMessage, (boardWidth - metrics.stringWidth(retryMessage)) / 2, boardHeight / 2);
        } else {
            g.drawString(String.valueOf((int) score), 10, 35);
        }
    }

    public void move() {
        if (spaceHeld && System.nanoTime() - lastFlapNanos >= FLAP_INTERVAL_NANOS) {
            flap();
        }

        //bird
        velocityY += gravity;
        bird.y += velocityY; //go -0,0, so in next line:
        bird.y = Math.max(bird.y, 0);//Stops at (0,0)

        //pipes
        for (int i = 0; i < pipes.size(); i++) {
            pipe pipe = pipes.get(i);
            pipe.x += velocityX;

            if (!pipe.passed && bird.x > pipe.x + pipe.width) {
                pipe.passed = true;
                score += 0.5; //0.5 bacause there are 2 pipes! so 0.5 * 2 = 1, 1 for each set of pipes
            }

            if (collision(bird, pipe)) {
                gameOver = true;
            }
        }

        if (bird.y > boardHeight) {
            gameOver = true;
        }
    }

    public boolean collision(Bird a, pipe b) {
        return a.x < b.x + b.width &&   //a'stop left corner doesn't reach b's top corner
                a.x + a.width > b.x &&  //a's top right corner passed b's top left corner
                a.y < b.y + b.height && //a's top left corner doesn't reach b's bottom left corner
                a.y + a.height > b.y;   //a's bottom left corner passed b's top left corner
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move(); //runs at -6 pixels per second
        repaint();

        if (gameOver) {
            PlacePipesTimer.stop();
            gameLoop.stop();
        }
    }

    private void flap() {
        velocityY = -9;
        lastFlapNanos = System.nanoTime();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !spaceHeld) {
            spaceHeld = true;
            if (gameOver) {
                //restart the game by resetting the conditions
                bird.y = birdY;
                velocityY = 0;
                pipes.clear();
                score = 0;
                gameOver = false;
                gameLoop.start();
                PlacePipesTimer.start();
            }
            flap();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            spaceHeld = false;
        }
    }

}
