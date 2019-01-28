
package fireworks;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class CanvasPanel extends JPanel {

    private double initVelocity;
    private double angle;
    private double start_x;
    private double start_y;
    private int padding = 0;
    private int labelPadding = 0;
    private double xScale = 0;
    private double yScale = 0;
    private List<InputsFromUser> ipList;
    private List<Axis> graphPoints;
    private List<List<Axis>> graphList;
    private List<Axis> originalPoints;
    private Color lineColor = new Color(44, 102, 230, 180);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private Graphics2D g2;
    private Line2D shape;
    private UserPanel upObject;
    private static int counter = 0;
    InputsFromUser iu;
    private int count;
    private boolean checkbox = false;
    static boolean two_cb;
    private int shapesCond = 0;
    protected JComboBox fireworks;
    protected int fireworkType;
    protected MultiPanel firework = new MultiPanel();
    Random rand = new Random();
    
    

    public CanvasPanel() {

        iu = new InputsFromUser();
        
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D) g;
        setBackground(Color.BLACK);
        
        firework.setFireworkType(fireworkType);
        

        counter++;


        //upObject =  getUPObject();
        //ipList = upObject.ipList;
        ipList = iu.getInputsList();

        for (int i = 0; i < ipList.size(); i++) {


        }



        for (InputsFromUser ipu : ipList) {

            initVelocity = ipu.init_velocity;
            angle = ipu.angle;
            angle = ((Math.PI) / 180) * angle;

            start_x = ipu.start_x;
            start_y = ipu.start_y;


            checkbox = ipu.checkBreak;
            
            fireworkType = ipu.fireworkType;


            graphPoints = new ArrayList<Axis>();
            originalPoints = new ArrayList<Axis>();
            





            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (getMaxHeight() == 0.0) {
                //do nothing
            } else {


                graphPoints = getCoordinates();

                graphList = new ArrayList<List<Axis>>();

                graphList.add(graphPoints);



            }

            for (List<Axis> gl : graphList) {

                drawTrajectory(gl);



                 if (two_cb == true) {

                    if (ipList.size() == 2) {
                        showHit();
                        revalidate();


                    }
                }

                 else if (checkbox == true) {
                	 
                	Color c = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
                    showExplosion(getCentreX(), getCentreY(), 50, c);
                    showMid(getCentreX(), getCentreY(), c, 30);

                }

                 else{
                	Color col = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
                    showExplosion(getCentreX(), getCentreY(), 50, col);

                }





            }



        }

    }
    
   

    public double getMaxHeight() {
        double height = (Math.pow(initVelocity, 2) * Math.pow(Math.sin(angle), 2)) / (2 * 9.8);
        return height;
    }

    public double getInitialVx() {
        double vx = initVelocity * Math.cos(angle);
        return vx;
    }

    public double getInitialVy() {
        double vy = initVelocity * Math.sin(angle);
        return vy;
    }

    public double getTimeOfFlight() {
        double totalTime = (0 - getInitialVy() - getInitialVy()) / (0 - 9.8);
        return totalTime;

    }

    private double getCentreY() {
        double cY = Double.MAX_VALUE;
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            cY = Math.min(cY, graphPoints.get(i).y);
        }
        return cY;
    }

    private double getCentreX() {
        double cX = 0;
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            if (graphPoints.get(i).y == getCentreY()) {
                cX = graphPoints.get(i).x;
            }
        }
        return cX;
    }

    private double getMaxY() {
        double mY = Integer.MIN_VALUE;

        for (Axis p : originalPoints) {
            mY = Math.max(mY, p.y);
        }

        return mY;
    }

    private double getMaxX() {
        double mX = Integer.MIN_VALUE;
        for (Axis p : originalPoints) {
            mX = Math.max(mX, p.x);
        }

        return mX;
    }

    public UserPanel getUPObject() {
        return this.upObject;
    }

    public void setUPObject(UserPanel upObject) {
        this.upObject = upObject;
    }

    private List<Axis> getCoordinates() {
        // we need to find out x and y co ordinate for each time slot
        double timeSlot = getTimeOfFlight() / 200;


        for (double t = 0.0; t <= (getTimeOfFlight() + timeSlot); t += timeSlot) {
            //  int x = v0cos@*t;
            double x = (double) (initVelocity * Math.cos(angle) * t);
            // int y = v0sin@t - 0.5gtt
            double y = (double) ((initVelocity * Math.sin(angle) * t) - (0.5 * 9.8 * t * t));



            if (y >= 0) {
                originalPoints.add(new Axis(x + start_x, y + start_y));
            }
        }


        xScale = 1;
        yScale = 1;

        for (Axis p : originalPoints) {

            double x_axis = (p.x * xScale + padding + labelPadding);
            double y_axis = ((getMaxHeight() - p.y) * yScale + padding);
            y_axis = getHeight() - p.y;

            graphPoints.add(new Axis(x_axis, y_axis));



        }

        return graphPoints;



    }

    private void drawTrajectory(List<Axis> graphPoints) {

        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);

        for (int i = 0; i < graphPoints.size() - 1; i++) {
            double x1 = graphPoints.get(i).x;
            double y1 = graphPoints.get(i).y;
            double x2 = graphPoints.get(i + 1).x;
            double y2 = graphPoints.get(i + 1).y;


            shape = new Line2D.Double();
            shape.setLine(x1, y1, x2, y2);
            g2.draw(shape);


            // g2.drawLine(x1, y1, x2, y2);
        }
    }
    
    public void setFireworkType(int type)
    {
    	fireworkType = type;
    }


    private void showExplosion(double x, double y,int d,Color c) {

        g2.setPaint(c);
        double xc = x;
        double yc = y;

        int x_centre = (int) (xc-(d/2));
        int y_centre = (int) (yc-(d/2)) ;
        
        if(fireworkType == 0)
        {
        	g2.fillOval(x_centre,y_centre,d,d);
        }
        else if(fireworkType == 1)
        {
        	g2.drawRect(x_centre,y_centre,d,d);
        }
        else if(fireworkType == 2)
        {
        	Color uno = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
        	g2.setColor(uno);
        	g2.drawString("BOOM!!!!!", x_centre, y_centre);
        	Color dos = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
        	g2.setColor(dos);
        	g2.drawString("FIREWORKS!!!", x_centre, y_centre + 100);
        	Color tres = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
        	g2.setColor(tres);
        	g2.drawString("LOOK AT THOSE COLORS!!!", x_centre + 100, y_centre);
        	Color quatro = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
        	g2.setColor(quatro);
        	g2.drawString("SUCH AMAZING SHAPES!!!" , x_centre - 200, y_centre);
        	Color cinco = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
        	g2.setColor(cinco);
        	g2.drawString("MUCH WOW!!!", x_centre, y_centre - 100);
        	
        }
        else if(fireworkType == 3)
        {
        	for(int i = 0; i <= 400; i += 5)
        	{
        	Color color = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
			g2.setColor(color);
        	g2.draw3DRect(x_centre, y_centre, i, i, true);
        	}
        }
        else if(fireworkType == 4)
        {
			for(int i = 0; i<=300; i+= 5){
				Color one = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
				g2.setColor(one);
				g2.drawOval(x_centre, y_centre, i-5, i-5);
        }
        }

    }

        	

    
         
        
    
    

     private void showHit() {

        initVelocity = ipList.get(0).init_velocity;
        angle = ipList.get(0).angle;
         angle = ((Math.PI)/180) * angle;
        double x1_a=0;
        double x1_b=0;

        x1_a =  Math.tan(angle);
        x1_b = (9.8/(2*(Math.pow(initVelocity, 2))*Math.pow(Math.cos(angle),2)));

        //for projectile 2
        initVelocity = ipList.get(1).init_velocity;
        angle = ipList.get(1).angle;
        start_x = ipList.get(1).start_x;
        start_y = ipList.get(1).start_x;
      angle = ((Math.PI)/180) * angle;
        double x2_a=0;
        double x2_b=0;
      //  y1 = x1 * Math.tan(angle) - ((9.8 * x1*x1)/2*(Math.pow(initVelocity, 2))*Math.pow(Math.sin(angle),2));
        x2_a =  Math.tan(angle);
        x2_b = (9.8/(2*(Math.pow(initVelocity, 2))*Math.pow(Math.cos(angle),2)));
        System.out.println("x2_a........"+x2_a);
        System.out.println("x2_b........"+x2_b);

        double a;
        double b;
        a= (x1_a) - (x2_a) ;
        b= x1_b - x2_b;


        int x = (int)(a/b);

        angle = ipList.get(1).angle;
        int y = (int)(x*x1_a - (Math.pow(x, 2)*x1_b));



         int x_centre = ((int) (x-(50/2))) ;
         int y_centre = ((int) (y-(50/2))) ;
         
         g2.setColor(Color.green);
         
         //Having problems getting the Y to get to get in the right place.
         g2.fillOval(x_centre,y_centre,50,50);

    }

      private void showMid(double x,double y,Color c,int d) {


        count++;

        double xc;
        double yc;

        for(int i=0;i<5;i++){
            int max = (int)x + 150;
            int min = (int)x - 150;

            Random r = new Random();
            int num_x= r.nextInt((max - min) + 1) + min;

            max = (int)y + 100;
            min = (int)y - 150;

            int num_y = r.nextInt((max - min) + 1) + min;
           xc=(double)num_x;
            yc=(double)num_y;
            showExplosion(xc, yc,d,c);

            for(int j=0;j<5;j++){

                 max = (int)xc + 50;
                 min = (int)xc - 50;


            num_x= r.nextInt((max - min) + 1) + min;

            max = (int)y + 50;
            min = (int)y - 50;

            num_y = r.nextInt((max - min) + 1) + min;
           xc=(double)num_x;
            yc=(double)num_y;
            Color last = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
            showExplosion(xc, yc,10,last);
            }

        }

    }


}
