import javafx.geometry.Rectangle2D;

import java.util.concurrent.ThreadLocalRandom;

public class Potion extends StaticThing{
    private final int potionType;
    private boolean isCollected = false;
    public Potion(int x, int y){
        super("default",x,y);
        this.potionType = ThreadLocalRandom.current().nextInt(0,3);
        if (this.potionType == 0){
            this.setImage("heart_up.png");
        }
        else if (this.potionType == 1){
            this.setImage("speed_up.png");
        }
        else if (this.potionType == 2){
            this.setImage("speed_down.png");
        }
    }
    public int getPotionType() {
        return potionType;
    }

    public void applyEffect(Hero hero){
        if (potionType == 0){
            hero.healthUp();
            System.out.println("HEALTH : "+ hero.getHealth());
        }
        if (potionType == 1){
            hero.setVelocity(hero.getVelocity()+2);
            System.out.println("VELOCITY : "+hero.getVelocity());
        }
        if (potionType == 2){
            hero.setVelocity(hero.getVelocity()-2);
            System.out.println("VELOCITY : "+hero.getVelocity());


        }
        this.isCollected = true;
    }

    public void update(double time,Camera camera){
        getImageView().setViewport(new Rectangle2D(0,0, 64, 64));
        getImageView().setX(getX()-camera.getX());
        getImageView().setY(getY());

    }

    public Rectangle2D getCollideBox(){
        return new Rectangle2D(getX()+5,getY()+5,54,54);
    }

    public boolean isCollide(Rectangle2D collider){
        return getCollideBox().intersects(collider);
    }

    public boolean isCollected() {
        return isCollected;
    }
}
