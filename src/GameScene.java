import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class GameScene extends Scene {

    Camera camera = new Camera(10,0);
    StaticThing bgLeft = new StaticThing("desert.png",0,0);
    StaticThing bgRight = new StaticThing("desert.png",800,0);
    ArrayList<Foe> listOfFoe = new ArrayList<>();
    Foe firstFoe = new Foe("goomba.png", 1000,270,75,0,11,80,80);

    Hero hero = new Hero("heros.png", 500,250, 200,0,6,82,100);
    public GameScene(Parent parent, double v, double v1) {
        super(parent, v, v1);
        listOfFoe.add(firstFoe);
        ((Group)parent).getChildren().addAll(bgLeft.getImageView(), bgRight.getImageView(), hero.getImage(), firstFoe.getImage());
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                if(Math.random() <0.005 && listOfFoe.get(listOfFoe.size()-1).getX() < hero.getX()+500){
                    listOfFoe.add(new Foe("goomba.png", hero.getX()+700,270,75,0,11,80,80));
                    ((Group)parent).getChildren().add(listOfFoe.get(listOfFoe.size()-1).getImage());
                }
                if (listOfFoe.size()>10){
                    listOfFoe.remove(0);
                }
                render(l);
                hero.setX(hero.getX()+hero.getVelocity());
                if (hero.getDashTime() > 0 && hero.isDashing()){
                    hero.setDashTime(hero.getDashTime()-1);
                    System.out.println(hero.getDashTime());

                }else if (hero.isDashing() && hero.getDashTime() <= 0) {
                    hero.setVelocity(hero.getVelocity()-10);
                    hero.setIsDashing(false);
                }


                setOnMouseClicked( (event)-> {
                    System.out.println("click");
                    if (hero.isOnFloor()) {
                        System.out.println("jump");
                        hero.jump();
                    }
                });

                setOnKeyPressed((event) -> {
                    System.out.println("key pressed");
                        hero.dash();
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
        for(Foe foe : listOfFoe){
            foe.update(time,camera);
        }
        camera.update(hero,time);

    }


    public void testPlus() {
        hero.setVelocity(0);
    }

    public void testMoins() {
        hero.setVelocity(hero.getVelocity()+4);
    }
}
