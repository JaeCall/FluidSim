import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;



public class App extends JPanel {

    float gravity;
    public int x=300;
    public int y=300;
    public int angleX ;
    public int angleY;
    public int velocity;
    float now;
    float lastTime;
    float deltaTime;
    float vx = 0;
    float vy = 0;
    

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.CYAN);
        g.fillOval(x, y, 20, 20);
    }


    public int velocity()
    {
        
        now = System.currentTimeMillis();
        deltaTime = (now - lastTime) / 1000;
        vx = (x - angleX)/deltaTime;
        vy = (y - angleY)/deltaTime;
        velocity = (int) Math.sqrt(vx*vx + vy*vy);
        
        return velocity;
    }
    
    public int gravity()
    {

        gravity = Math.round(1.0f/(getHeight()-y));
        
        
         return (int)gravity;


    }

    private void wallCollision() {
        lastTime = System.currentTimeMillis();

        gravity();
        if(x + angleX < 5 ||x + angleX > getWidth() -15 ) 
        {
            //angleX = velocity();
            angleX *= -1;
        }
        else if(y + angleY < 5|| y + angleY >  getHeight() -15)
        {
            //angleY += velocity();
            angleY  *= -1;
        } 
       
        angleX += velocity();
        angleY += gravity();
        x += angleX;
        
        y += angleY;
        
        

    }
        
    

    public static void main(String[] args) throws Exception {
        
        JFrame frame = new JFrame("Wata");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setSize(1000, 600);
        frame.setResizable(true); // Disable resizing
        frame.setBackground(Color.BLACK); // Set background color to black
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setVisible(true);
        

        App app = new App();
        frame.add(app);

        while (true) {
            app.wallCollision(); // Move the app to the top-left corner of the window
            app.repaint(); // Repaint the app to update the graphics
            Thread.sleep(1000 / 60); // Limit to 60 FPS

        
        
        
        
        }
    }
}

