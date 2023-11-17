import javafx.geometry.Rectangle2D;

public class Hero extends AnimatedThing{
    private double velocity = 0;
    private boolean isOnFloor = true;
    private boolean isJumpingUp = false;
    private boolean isJumping = false;

    public Hero(String fileName, int initX, int initY, int duration, int state, int maximumIndex, int offset, int spriteHeight){
        super(fileName, initX, initY, duration,state,maximumIndex,offset,spriteHeight);
    }

    public void setVelocity(double v){
        velocity = v;
    }

    public double getVelocity() {
        return velocity;
    }

    public void update(double time,Camera camera){
        setIndex((int) ((time/1000000)/getDuration())% getMaximumIndex());
        getImage().setViewport(new Rectangle2D(10+getIndex()* getOffset(),getState()*150, getOffset(), getSpriteHeight()));
        getImage().setX(getX()-camera.getX());
        getImage().setY(getY());
        gravity();
    }


    public void gravity(){
        if (!isOnFloor) {
            if (isJumpingUp) {
                setY(getY() - 10);
                if (getY()<100){
                    isJumpingUp=false;
                }
            }
            setY(getY()+5);
            if (getY()>250){
                System.out.println("atterie");
                setY(250);
                isOnFloor=true;
                isJumping = false;
            }

        }


    }

    public void jump() {
        isOnFloor = false;
        setY(getY()-5);
        isJumpingUp = true;
        isJumping = true;
    }

    public boolean isOnFloor(){
        return isOnFloor;
    }
    public boolean isJumping(){
        return isJumping;
    }
}
