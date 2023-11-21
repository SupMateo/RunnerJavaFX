import javafx.geometry.Rectangle2D;

public class Foe extends AnimatedThing{
    public Foe(String fileName, double initX, double initY, double duration, int state, int maximumIndex, int offset, int spriteHeight) {
        super(fileName, initX, initY, duration, state, maximumIndex, offset, spriteHeight);
    }

    public void update(double time,Camera camera){
        setIndex((int)( getMaximumIndex()- (((time/1000000)/getDuration())% getMaximumIndex())));
        setX(getX()-1);
        getImage().setViewport(new Rectangle2D(getIndex()* getOffset(),0, getOffset(), getSpriteHeight()));
        getImage().setX(getX()-camera.getX());
        getImage().setY(getY());

    }
}
