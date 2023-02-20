public class Apple {
    public int posX;
    public int posY;

    public Apple (int x, int y) {
        posX = x;
        posY = y;
    }

    public void setRandomPosition() {
        posX = Math.abs((int)(Math.random() * SnakeWindow.WIDTH - 1));
        posY = Math.abs((int)(Math.random() * SnakeWindow.HEIGHT - 1));
    }
}
