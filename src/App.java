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
        g.setColor(Color.BLACK);
        g.fillRect(25, 25, 10, 100);
    
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

        gravity = Math.round(1.0f/(getHeight()-y)*(getHeight()-y));
        
        
         return (int)gravity;


    }

    private void wallCollision() {
        lastTime = System.currentTimeMillis();

        
        
       
        // for some reason the ball is not bouncing off the walls and velocity on the x axis is overpowering the gravity on the y axis
        angleX += velocity();
        angleY -= gravity()*velocity();


        //angleY += gravity();
        
        // this seems to work closer to what I want but the ball is not bouncing off the floor and the x axis is still attracting to one side
        //x += angleX + velocity();
        //y += angleY + gravity();
        
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
            Thread.sleep(1000 / 20); // Limit to 60 FPS

        
        
        
        
        }
    }
}

