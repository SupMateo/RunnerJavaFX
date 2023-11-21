import javafx.geometry.Rectangle2D;

public class Hero extends AnimatedThing{
    private double velocity = 0;
    private double lastY;
    private double dashTime = 0;
    private boolean isDashing = false;
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
        if (isJumping && isJumpingUp) {
            setState(1);
            setIndex(0);
        } else if (isDashing && isJumping){
                isJumpingUp = false;
                setY(lastY);
                setState(0);
                setIndex(1);
        } else if (isJumping) {
            setState(1);
            setIndex(1);
        }

        getImage().setViewport(new Rectangle2D(10+getIndex()* getOffset(),getState()*150, getOffset(), getSpriteHeight()));
        getImage().setX(getX()-camera.getX()-200);
        getImage().setY(getY());
        gravity();
    }


    public void gravity(){
        if (!isOnFloor) {
            getImage().setViewport(new Rectangle2D(10+getIndex()* getOffset(),getState()*150, getOffset(), getSpriteHeight()));
            if (isJumpingUp) {
                getImage().setViewport(new Rectangle2D(10+getIndex()* getOffset(),getState()*150, getOffset(), getSpriteHeight()));
                setY(getY() - 10);
                if (getY()<100){
                    getImage().setViewport(new Rectangle2D(10+getIndex()* getOffset(),getState()*150, getOffset(), getSpriteHeight()));
                    isJumpingUp=false;
                }
            }
            setY(getY()+5);
            if (getY()>250){
                setState(0);
                System.out.println("atterie");
                setY(250);
                isOnFloor=true;
                isJumping = false;
            }
        }
    }

    public void dash(){
        if (!isDashing()) {
            setVelocity(getVelocity() + 10);
            lastY = getY();
            isDashing = true;
            if (isJumping){
                isJumping=true;
                isJumpingUp=false;
            }
            dashTime = 25;
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

    public double getDashTime() {
        return dashTime;
    }

    public void setDashTime(double dashTime) {
        this.dashTime = dashTime;
        isDashing = true;
    }

    public boolean isDashing() {
        return isDashing;
    }

    public void setIsDashing(boolean b) {
        isDashing = b;
    }
}
