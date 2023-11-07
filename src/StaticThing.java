import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StaticThing {
    private int x;
    private int y;

    ImageView image;


    public StaticThing(String fileName, int x, int y){
        this.x = x;
        this.y = y;
        this.image = new ImageView("file:src\\img\\"+fileName);
    }

    public ImageView getImageView(){
        return image;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }



}
