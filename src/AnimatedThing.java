import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public abstract class AnimatedThing {
    private double x;
    private double y;
    private final ImageView image;
    private int state;
    private int index;
    private final double duration;
    private int maximumIndex;
    private int spriteHeight;
    private int offset;



    public AnimatedThing(String fileName, double initX, double initY, double duration, int state, int maximumIndex, int offset,int spriteHeight){
        this.x = initX;
        this.y = initY;
        this.duration = duration;
        this.state = index;
        this.index = index;
        this.maximumIndex = maximumIndex;
        this.offset = offset;
        this.spriteHeight = spriteHeight;
        this.image = new ImageView("file:src\\img\\"+fileName);
    }

    public void update(double time,Camera camera){
        setIndex((int) ((time/1000000)/getDuration())% getMaximumIndex());
        getImage().setViewport(new Rectangle2D(10+getIndex()* getOffset(),0, getOffset(), getSpriteHeight()));
        getImage().setX(getX()-camera.getX());
        getImage().setY(250);
    }

    public int getSpriteHeight(){
        return spriteHeight;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ImageView getImage(){
        return image;
    }

    public int getState() {
        return state;
    }

    public int getIndex() {
        return index;
    }

    public int getMaximumIndex() {
        return maximumIndex;
    }

    public int getOffset() {
        return offset;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDuration() {
        return duration;
    }
}
