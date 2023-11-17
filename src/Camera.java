public class Camera {

    private int a = 0;
    private int v = 0;
    private int x;
    private int y;

    public Camera(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void update(Hero hero, long time){
        // Constants
        double const1 = 1;
        double const2 = 1.4;

        a = (int)(0.07*(const1*(hero.getX() - getX() - 300) + const2*v));

        v = (int)(v -  a);

        x = (int) ( getX() - v);
        setX(x);
        //System.out.println("a : "+ Double.toString(a) + " / v : "+ Double.toString(v) +" / x hero : "+ Double.toString(hero.getX()) + " / x camera : " +Double.toString(getX()));



    }

    @Override
    public String toString() {
        return  x + " ; " + y;
    }
}
