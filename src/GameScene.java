import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

public class GameScene extends Scene {

    Camera camera = new Camera(1000,10);
    StaticThing bgLeft = new StaticThing("desert.png",0,0);
    StaticThing bgRight = new StaticThing("desert.png",1920,0);
    public GameScene(Parent parent, double v, double v1) {
        super(parent, v, v1);
//        ((Group)parent).getChildren().addAll(bgRight.getImageView(),bgLeft.getImageView());
          ((Group)parent).getChildren().addAll(bgLeft.getImageView());
          render();
    };

    public void render(){
        //bgLeft.setX(bgLeft.getX() - camera.getX());
        //bgLeft.setY(bgLeft.getY() - camera.getY());
        //bgRight.setX(bgRight.getX() - camera.getX());
        //bgRight.setY(bgRight.getY() - camera.getY());
        bgLeft.getImageView().setViewport(new Rectangle2D((camera.getX()%600)-bgLeft.getX(),0,800,400));
        bgLeft.getImageView().setX(0);
        bgLeft.getImageView().setY(0);
/*        bgRight.getImageView().setViewport(new Rectangle2D(bgRight.getX(),bgRight.getY(),800,400));
        bgRight.getImageView().setX(bgRight.getX());
        bgRight.getImageView().setY(bgRight.getY());
*/

    }

    public void test() {
        camera.setX(camera.getX()+50);
        render();
    }
}
