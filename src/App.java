import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;



public class App extends JPanel {

    int gravity;
    public int x=300;
    public int y=300;
    public int angleX ;
    public int angleY;
    public int velocity;
    float now;
    float lastTime;
    

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.CYAN);
        g.fillOval(x, y, 20, 20);
    }


    public int velocity()
    {
        now = System.currentTimeMillis();
        velocity = (int)(((x - angleX) + (y - angleY))/ ((now - lastTime)/1000)) ;
        return velocity;
    }
    
    public void gravity()
    {
        gravity = 2;
        
        
         //angleY = ((y - getHeight())*-1) / gravity;
         angleY = gravity*(velocity())/(y - getHeight())*-1;



    }

    private void wallCollision() {
        lastTime = System.currentTimeMillis();
        gravity();
        if(x + angleX < 5) 
        {
            angleX += velocity();
            angleX *= -1;

        } else if(x + angleX > getWidth() -15)
        {
            angleX += velocity();
            angleX *= -1; 

        }else if(y + angleY < 5)
        {
            angleY += velocity();
            angleY  *= -1;

        } else if(y + angleY >  getHeight() -15)
        {
            angleY += velocity();
            angleY*= -1;
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
            Thread.sleep(1000 / 30); // Limit to 60 FPS

        
        
        
        
        }
    }
}

