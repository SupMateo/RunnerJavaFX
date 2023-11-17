import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

import java.util.Random;

public class GameScene extends Scene {

    Camera camera = new Camera(50,0);
    StaticThing bgLeft = new StaticThing("desert.png",0,0);
    StaticThing bgRight = new StaticThing("desert.png",800,0);

    Hero hero = new Hero("heros.png", 300,250, 200,0,6,82,100);
    public GameScene(Parent parent, double v, double v1) {
        super(parent, v, v1);
        ((Group)parent).getChildren().addAll(bgLeft.getImageView(), bgRight.getImageView(), hero.getImage());
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render(l);
                hero.setX(hero.getX()+hero.getVelocity());

                setOnMouseClicked( (event)-> {
                    System.out.println("click");
                    if (hero.isOnFloor()) {
                        System.out.println("jump");
                        hero.jump();
                    }
                });
            }
        };


        timer.start();
    }

    public void render(long time){
        bgLeft.getImageView().setViewport(new Rectangle2D((camera.getX()%800)-bgLeft.getX(),0,800,400));
        bgLeft.getImageView().setX(0);
        bgLeft.getImageView().setY(0);
        bgRight.getImageView().setViewport(new Rectangle2D((camera.getX()%800)-bgRight.getX(),0,800,400));
        bgRight.getImageView().setX(0);
        bgRight.getImageView().setY(0);
        hero.update(time,camera);
        camera.update(hero,time);

    }


    public void testPlus() {
        hero.setVelocity(0);
    }

    public void testMoins() {
        hero.setVelocity(hero.getVelocity()+1);
    }
}
