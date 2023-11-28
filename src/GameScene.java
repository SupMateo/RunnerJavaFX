import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameScene extends Scene {

    private boolean running = true;

    Camera camera = new Camera(300,0);
    StaticThing bgLeft = new StaticThing("desert.png",0,0);
    StaticThing bgRight = new StaticThing("desert.png",800,0);
    ArrayList<Foe> listOfFoe = new ArrayList<>();
    ArrayList<StaticThing> lifeList = new ArrayList<>();
    ArrayList<Potion> potionOnStage = new ArrayList<>();
    Foe firstFoe = new Foe("goomba.png", 1500,270,75,0,11,80,80);

    Hero hero = new Hero("heros.png", 500,250, 200,0,6,82,100);
    Hero hero_invincible = new Hero("heros_hurt.png", 500,-500, 200,0,6,82,100);

    StaticThing gameOver = new StaticThing("gameover.png",150,100);

    public GameScene(Parent parent, double v, double v1) {
        super(parent, v, v1);
        listOfFoe.add(firstFoe);
        ((Group)parent).getChildren().addAll(bgLeft.getImageView(), bgRight.getImageView(), hero.getImage(), firstFoe.getImage(), hero_invincible.getImage());
        for (int i = 0; i < hero.getHealth(); i++) {
            lifeList.add(new StaticThing("heart.png", i * (-50) + 745, 5));
            ((Group) parent).getChildren().add(lifeList.get(lifeList.size() - 1).getImageView());
        }

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                //check health
                if(hero.getHealth() <= 0){
                    gameOver.getImageView().setX(gameOver.getX());
                    gameOver.getImageView().setY(gameOver.getY());
                    ((Group)parent).getChildren().add(gameOver.getImageView());
                    running = false;
                }

                //health update
                for(StaticThing life:lifeList){
                    ((Group)parent).getChildren().remove(life.getImageView());
                }
                lifeList.clear();
                for (int i = 0; i < hero.getHealth(); i++) {
                    lifeList.add(new StaticThing("heart.png", i * (-50) + 745, 5));
                    ((Group) parent).getChildren().add(lifeList.get(lifeList.size() - 1).getImageView());
                }


                //Foe spawning
                if(Math.random() <0.005 && listOfFoe.get(listOfFoe.size()-1).getX() < hero.getX()+500){
                    listOfFoe.add(new Foe("goomba.png", hero.getX()+700,270,75,0,11,80,80));
                    ((Group)parent).getChildren().add(listOfFoe.get(listOfFoe.size()-1).getImage());
                }

                //Potion spawning
                if(Math.random() <0.0025){
                    potionOnStage.add(new Potion((int)hero.getX()+700,150));
                    System.out.println(" Potion spawned : "+ (potionOnStage.get(potionOnStage.size()-1).getPotionType()));
                    ((Group)parent).getChildren().add(potionOnStage.get(potionOnStage.size()-1).getImageView());
                }

                //Foe despawn
                if (listOfFoe.size()>10){
                    listOfFoe.remove(0);
                }
                //Potion despawn
                if (potionOnStage.size()>10){
                    potionOnStage.remove(0);
                }

                //check foes collision
                for(Foe foe:listOfFoe){
                    if (hero.isCollide(foe.getCollideBox()) && !hero.isInvincible()){
                        System.out.println("COLLIDE");
                        hero.hurt();
                        hero.setInvincibility(125);
                    }
                }
                //check potions collision
                for(Potion potion:potionOnStage) {
                    if (hero.isCollide(potion.getCollideBox()) && !potion.isCollected()) {
                        System.out.println("potion collision");
                        potion.applyEffect(hero);
                        ((Group)parent).getChildren().remove(potion.getImageView());
                    }
                }
                //invincibility
                hero.setInvincibility(hero.getInvicibility()-1);
                if (hero.getInvicibility() < 0){
                    hero.setInvincibility(0);
                }

                if (hero.isInvincible()){
                    hero_invincible.getImage().setVisible(true);
                    hero_invincible.setX(hero.getX());
                    hero_invincible.setY(hero.getY());

                }else{
                    hero_invincible.getImage().setVisible(false);
                }

                //render
                render(l);

                //hero movement
                hero.setX(hero.getX()+hero.getVelocity());

                //dash
                if (hero.getDashTime() > 0 && hero.isDashing()){
                    hero.setDashTime(hero.getDashTime()-1);

                }else if (hero.isDashing() && hero.getDashTime() <= 0) {
                    hero.setVelocity(hero.getVelocity()-10);
                    hero.setIsDashing(false);
                }

                //controls
                setOnKeyPressed((event) -> {
                    if(event.getCode()== KeyCode.D){
                        System.out.println("dash");
                        hero.dash();
                    }
                    if(event.getCode()== KeyCode.Z){
                        if (hero.isOnFloor()) {
                            System.out.println("jump");
                            hero.jump();
                        }
                    }

                });
            }
        };
        timer.start();
    }


    public void render(long time){
        for(StaticThing heart:lifeList){
            heart.getImageView().setX(heart.getX());
            heart.getImageView().setY(heart.getY());
        }

        bgLeft.getImageView().setViewport(new Rectangle2D((camera.getX()%800)-bgLeft.getX(),0,800,400));
        bgLeft.getImageView().setX(0);
        bgLeft.getImageView().setY(0);
        bgRight.getImageView().setViewport(new Rectangle2D((camera.getX()%800)-bgRight.getX(),0,800,400));
        bgRight.getImageView().setX(0);
        bgRight.getImageView().setY(0);

        hero_invincible.setState(hero.getState());
        hero_invincible.setIndex(hero.getIndex());
        hero_invincible.setDashing(hero.isDashing());
        hero_invincible.setJumping(hero.isJumping());
        hero_invincible.setJumpingUp(hero.isJumpingUp());
        hero_invincible.setLastY(hero.getLastY());
        hero_invincible.update(time,camera);

        hero.update(time,camera);
        for(Foe foe : listOfFoe){
            foe.update(time,camera);
        }

        for(Potion potion :potionOnStage){
            potion.update(time,camera);
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
