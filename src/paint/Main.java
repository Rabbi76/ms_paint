/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package paint;




import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.lang.Math.*;

import java.awt.Scrollbar.*;

public class Main extends Applet implements ActionListener, AdjustmentListener, MouseListener, MouseMotionListener
{

 private final int MAX_X           = 800;
 private final int MAX_Y           = 600;


 private final int  NO_OP          = 0;
 private final int  PEN_OP         = 1;
 private final int  LINE_OP        = 2;
 private final int  ERASER_OP      = 3;
 private final int  CLEAR_OP       = 4;
 private final int  RECT_OP        = 5;
 private final int  OVAL_OP        = 6;
 private final int  FRECT_OP       = 7;
 private final int  FOVAL_OP       = 8;




 private int mousex                = 0;
 private int mousey                = 0;



 private int prevx                 = 0;
 private int prevy                 = 0;



 private boolean initialPen        = true;
 private boolean initialLine       = true;
 private boolean initialEraser     = true;
 private boolean initialRect       = true;
 private boolean initialOval       = true;
 private boolean initialFRect      = true;
 private boolean initialFOval      = true;



 private int  Orx                  = 0;
 private int  Ory                  = 0;
 private int  OrWidth              = 0;
 private int  OrHeight             = 0;
 private int  drawX                = 0;
 private int  drawY                = 0;
 private int  eraserLength         = 5;



 private int    opStatus           = PEN_OP;
 private int    colorStatus        = 1;
 private Color  mainColor          = new Color(0,0,0);
 private Color  xorColor           = new Color(255,255,255);
 private Color  statusBarColor     = new Color(166,166,255);


 private Button penButton          = new Button("Pen");
 private Button lineButton         = new Button("Line");
 private Button eraserButton       = new Button("Eraser");
 private Button clearButton        = new Button("Clear");
 private Button rectButton         = new Button("Rectangle");
 private Button ovalButton         = new Button("Oval");
 private Button fillRectButton     = new Button("Filled Rectangle");
 private Button fillOvalButton     = new Button("Filled Oval");



 private Button blackButton        = new Button("Black");
 private Button blueButton         = new Button("Blue");
 private Button redButton          = new Button("Red");
 private Button greenButton        = new Button("Green");
 private Button purpleButton       = new Button("Purple");
 private Button orangeButton       = new Button("Orange");
 private Button pinkButton         = new Button("Pink");
 private Button grayButton         = new Button("Gray");
 private Button yellowButton       = new Button("Yellow");





 private TextField colorStatusBar  = new TextField(20);
 private TextField opStatusBar     = new TextField(20);
 private TextField mouseStatusBar  = new TextField(10);



 private Label operationLabel      = new Label("   Tool mode:");
 private Label colorLabel          = new Label("   Color mode:");
 private Label cursorLabel         = new Label("   Cursor:");


 private Panel controlPanel        = new Panel(new GridLayout(11,2,0,0));
 private Panel drawPanel           = new Panel();
 private Panel statusPanel         = new Panel();
 private Panel udefcolPanel        = new Panel(new GridLayout(3,2,0,0));
 private Panel udefdemcolPanel     = new Panel();


 public void init()
 {
    setLayout(new BorderLayout());


    controlPanel.add(blackButton);
    controlPanel.add(blueButton);
    controlPanel.add(redButton);
    controlPanel.add(greenButton);
    controlPanel.add(purpleButton);
    controlPanel.add(orangeButton);
    controlPanel.add(pinkButton);
    controlPanel.add(grayButton);
    controlPanel.add(yellowButton);



    blueButton.setBackground(Color.blue);
    redButton.setBackground(Color.red);
    greenButton.setBackground(Color.green);
    purpleButton.setBackground(Color.magenta);
    orangeButton.setBackground(Color.orange);
    pinkButton.setBackground(Color.pink);
    grayButton.setBackground(Color.gray);
    yellowButton.setBackground(Color.yellow);




    controlPanel.add(penButton);
    controlPanel.add(lineButton);
    controlPanel.add(eraserButton);
    controlPanel.add(clearButton);
    controlPanel.add(rectButton);
    controlPanel.add(ovalButton);
    controlPanel.add(fillRectButton);
    controlPanel.add(fillOvalButton);


    controlPanel.setBounds(0,0,100,300);
    controlPanel.add(udefcolPanel);
    controlPanel.add(udefdemcolPanel);


    statusPanel.add(colorLabel);
    statusPanel.add(colorStatusBar);


    statusPanel.add(operationLabel);
    statusPanel.add(opStatusBar);


    statusPanel.add(cursorLabel);
    statusPanel.add(mouseStatusBar);


    colorStatusBar.setEditable(false);
    opStatusBar.setEditable(false);
    mouseStatusBar.setEditable(false);

    statusPanel.setBackground(statusBarColor);
    controlPanel.setBackground(Color.white);
    drawPanel.setBackground(Color.white);
    add(statusPanel, "North");
    add(controlPanel, "West");
    add(drawPanel, "Center");


    penButton.addActionListener(this);
    lineButton.addActionListener(this);
    eraserButton.addActionListener(this);
    clearButton.addActionListener(this);
    rectButton.addActionListener(this);
    ovalButton.addActionListener(this);
    fillRectButton.addActionListener(this);
    fillOvalButton.addActionListener(this);



    blackButton.addActionListener(this);
    blueButton.addActionListener(this);
    redButton.addActionListener(this);
    greenButton.addActionListener(this);
    purpleButton.addActionListener(this);
    orangeButton.addActionListener(this);
    pinkButton.addActionListener(this);
    grayButton.addActionListener(this);
    yellowButton.addActionListener(this);




    drawPanel.addMouseMotionListener(this);
    drawPanel.addMouseListener(this);
    this.addMouseListener(this);
    this.addMouseMotionListener(this);



    opStatusBar.setText("Pen");
    colorStatusBar.setText("Black");
 }



 public void actionPerformed(ActionEvent e)
 {


    if (e.getActionCommand() == "Pen")
       opStatus = PEN_OP;

    if (e.getActionCommand() == "Line")
       opStatus = LINE_OP;

    if (e.getActionCommand() == "Eraser")
       opStatus = ERASER_OP;

    if (e.getActionCommand() == "Clear")
       opStatus = CLEAR_OP;

    if (e.getActionCommand() == "Rectangle")
       opStatus = RECT_OP;

    if (e.getActionCommand() == "Oval")
       opStatus = OVAL_OP;

    if (e.getActionCommand() == "Filled Rectangle")
       opStatus = FRECT_OP;

    if (e.getActionCommand() == "Filled Oval")
       opStatus = FOVAL_OP;


    if (e.getActionCommand() == "Black")
       colorStatus = 1;

    if (e.getActionCommand() == "Blue")
       colorStatus = 2;

    if (e.getActionCommand() == "Green")
       colorStatus = 3;

    if (e.getActionCommand() == "Red")
       colorStatus = 4;

    if (e.getActionCommand() == "Purple")
       colorStatus = 5;

    if (e.getActionCommand() == "Orange")
       colorStatus = 6;

    if (e.getActionCommand() == "Pink")
       colorStatus = 7;

    if (e.getActionCommand() == "Gray")
       colorStatus = 8;

    if (e.getActionCommand() == "Yellow")
       colorStatus = 9;

    if (e.getActionCommand() == "User-Def")
       colorStatus = 10;



    switch (opStatus)
    {
       case PEN_OP   : opStatusBar.setText("Pen");
                       break;

       case LINE_OP  : opStatusBar.setText("Line");
                       break;

       case ERASER_OP: opStatusBar.setText("Eraser");
                       break;

       case CLEAR_OP : clearPanel(drawPanel);
                       break;

       case RECT_OP  : opStatusBar.setText("Rectangle");
                       break;

       case OVAL_OP  : opStatusBar.setText("Oval");
                       break;

       case FRECT_OP : opStatusBar.setText("Fill-Rectangle");
                       break;

       case FOVAL_OP : opStatusBar.setText("Fill-Oval");
                       break;

    }


    switch (colorStatus)
    {
       case  1: colorStatusBar.setText("Black");
                break;

       case  2:  colorStatusBar.setText("Blue");
                 break;

       case  3:  colorStatusBar.setText("Green");
                 break;

       case  4:  colorStatusBar.setText("Red");
                 break;

       case  5:  colorStatusBar.setText("Purple");
                 break;

       case  6:  colorStatusBar.setText("Orange");
                 break;

       case  7:  colorStatusBar.setText("Pink");
                 break;

       case  8:  colorStatusBar.setText("Gray");
                 break;

       case  9: colorStatusBar.setText("Yellow");
                break;


    }


    setMainColor();


 }






 public void clearPanel(Panel p)
 {
    opStatusBar.setText("Clear");
    Graphics g = p.getGraphics();
    g.setColor(p.getBackground());
    g.fillRect(0,0,p.getBounds().width,p.getBounds().height);
  }



 public void penOperation(MouseEvent e)
 {
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);


    if (initialPen)
    {
       setGraphicalDefaults(e);
       initialPen = false;
       g.drawLine(prevx,prevy,mousex,mousey);
    }


    if (mouseHasMoved(e))
    {

       mousex = e.getX();
       mousey = e.getY();


       g.drawLine(prevx,prevy,mousex,mousey);


       prevx = mousex;
       prevy = mousey;
    }
 }



 public void lineOperation(MouseEvent e)
 {
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);

    
    if (initialLine)
    {
       setGraphicalDefaults(e);
       g.setXORMode(xorColor);
       g.drawLine(Orx,Ory,mousex,mousey);
       initialLine=false;
    }

   
    if (mouseHasMoved(e))
    {
      
       g.setXORMode(xorColor);
       g.drawLine(Orx,Ory,mousex,mousey);

       
       mousex = e.getX();
       mousey = e.getY();

     
       g.drawLine(Orx,Ory,mousex,mousey);
    }
 }


 
 public void rectOperation(MouseEvent e)
 {
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);

   
    if (initialRect)
    {
       setGraphicalDefaults(e);
       initialRect = false;
    }

   
    if (mouseHasMoved(e))
    {
      
       g.setXORMode(drawPanel.getBackground());
       g.drawRect(drawX,drawY,OrWidth,OrHeight);

      
       mousex = e.getX();
       mousey = e.getY();

      
       setActualBoundry();

       
       g.drawRect(drawX,drawY,OrWidth,OrHeight);

    }

 }


 
 public void ovalOperation(MouseEvent e)
 {
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);

    
    if (initialOval)
    {
       setGraphicalDefaults(e);
       initialOval=false;
    }

    
    if (mouseHasMoved(e))
    {
      
       g.setXORMode(xorColor);
       g.drawOval(drawX,drawY,OrWidth,OrHeight);

     
       mousex = e.getX();
       mousey = e.getY();

     
       setActualBoundry();

       
       g.drawOval(drawX,drawY,OrWidth,OrHeight);
    }
 }


 
 public void frectOperation(MouseEvent e)
 {

    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);

   
    if (initialFRect)
    {
       setGraphicalDefaults(e);
       initialFRect=false;
    }

   
    if (mouseHasMoved(e))
    {
     
       g.setXORMode(xorColor);
       g.drawRect(drawX,drawY,OrWidth-1,OrHeight-1);

      
       mousex = e.getX();
       mousey = e.getY();

       
       setActualBoundry();

       
       g.drawRect(drawX,drawY,OrWidth-1,OrHeight-1);

    }

 }


 
 public void fovalOperation(MouseEvent e)
 {
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);

   
    if (initialFOval)
    {
       setGraphicalDefaults(e);
       initialFOval = false;
    }

   
    if (mouseHasMoved(e))
    {
       
       g.setXORMode(xorColor);
       g.drawOval(drawX,drawY,OrWidth,OrHeight);

      
       mousex = e.getX();
       mousey = e.getY();

       
       setActualBoundry();

      
       g.drawOval(drawX,drawY,OrWidth,OrHeight);
    }

 }


 
 public void eraserOperation(MouseEvent e)
 {
    Graphics g  = drawPanel.getGraphics();

    
    if (initialEraser)
    {
       setGraphicalDefaults(e);
       initialEraser = false;
       g.setColor(mainColor.white);
       g.fillRect(mousex-eraserLength, mousey-eraserLength,eraserLength*2,eraserLength*2);
       g.setColor(Color.black);
       g.drawRect(mousex-eraserLength,mousey-eraserLength,eraserLength*2,eraserLength*2);
       prevx = mousex;
       prevy = mousey;
    }

   
    if (mouseHasMoved(e))
    {
       g.setColor(mainColor.white);
       g.drawRect(prevx-eraserLength, prevy-eraserLength,eraserLength*2,eraserLength*2);

      
       mousex  = e.getX();
       mousey  = e.getY();

      
       g.setColor(mainColor.white);
       g.fillRect(mousex-eraserLength, mousey-eraserLength,eraserLength*2,eraserLength*2);
       g.setColor(Color.black);
       g.drawRect(mousex-eraserLength,mousey-eraserLength,eraserLength*2,eraserLength*2);
       prevx = mousex;
       prevy = mousey;
    }
 }


 public boolean mouseHasMoved(MouseEvent e)
 {
    return (mousex != e.getX() || mousey != e.getY());
 }


 
 public void setActualBoundry()
 {
      
       if (mousex < Orx || mousey < Ory)
       {
          
          if (mousex < Orx)
          {
             OrWidth = Orx - mousex;
             drawX   = Orx - OrWidth;
          }
          else
          {
             drawX    = Orx;
             OrWidth  = mousex - Orx;

          }
          
          if (mousey < Ory)
          {
             OrHeight = Ory - mousey;
             drawY    = Ory - OrHeight;
          }
          else
          {
             drawY    = Ory;
             OrHeight = mousey - Ory;
          }
       }
      
       else
       {
          drawX    = Orx;
          drawY    = Ory;
          OrWidth  = mousex - Orx;
          OrHeight = mousey - Ory;
       }
 }


 
 public void setGraphicalDefaults(MouseEvent e)
 {
    mousex   = e.getX();
    mousey   = e.getY();
    prevx    = e.getX();
    prevy    = e.getY();
    Orx      = e.getX();
    Ory      = e.getY();
    drawX    = e.getX();
    drawY    = e.getY();
    OrWidth  = 0;
    OrHeight = 0;
 }



 public void mouseDragged(MouseEvent e)
 {
    updateMouseCoordinates(e);

    switch (opStatus)
    {
      
       case PEN_OP   : penOperation(e);
                       break;

      
       case LINE_OP  : lineOperation(e);
                       break;

       
       case RECT_OP  : rectOperation(e);
                       break;

       
       case OVAL_OP  : ovalOperation(e);
                       break;

      
       case FRECT_OP : frectOperation(e);
                       break;

      
       case FOVAL_OP : fovalOperation(e);
                       break;

       
       case ERASER_OP: eraserOperation(e);
                       break;
    }
 }


 
 public void mouseReleased(MouseEvent e)
 {
   
    updateMouseCoordinates(e);

    switch (opStatus)
    {
       
       case PEN_OP    : releasedPen();
                        break;

      
       case LINE_OP   : releasedLine();
                        break;

     
       case RECT_OP   : releasedRect();
                        break;

     
       case OVAL_OP   : releasedOval();
                        break;

      
       case FRECT_OP  : releasedFRect();
                        break;

       
       case FOVAL_OP  : releasedFOval();
                        break;

      
       case ERASER_OP : releasedEraser();
                        break;
    }
 }



 public void mouseEntered(MouseEvent e)
 {
    updateMouseCoordinates(e);
 }



 public void setMainColor()
 {
    switch (colorStatus)
    {
       case 1 : mainColor = Color.black;
                break;

       case 2:  mainColor = Color.blue;
                break;

       case 3:  mainColor = Color.green;
                break;

       case 4:  mainColor = Color.red;
                break;

       case 5:  mainColor = Color.magenta;
                break;

       case 6:  mainColor = Color.orange;
                break;

       case 7:  mainColor = Color.pink;
                break;

       case 8:  mainColor = Color.gray;
                break;

       case 9:  mainColor = Color.yellow;
                break;


    }
 }


 
 public void releasedPen()
 {
    initialPen = true;
 }


 
 public void releasedLine()
 {
    if ((Math.abs(Orx - mousex) + Math.abs(Ory - mousey)) != 0)
    {
       System.out.println("Line has been released....");
       initialLine = true;
       Graphics g  = drawPanel.getGraphics();
       g.setColor(mainColor);
       g.drawLine(Orx,Ory,mousex,mousey);
    }
 }


 
 public void releasedEraser()
 {
    initialEraser = true;
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor.white);
    g.drawRect(mousex-eraserLength,mousey-eraserLength,eraserLength*2,eraserLength*2);
 }



 public void releasedRect()
 {
    initialRect = true;
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);
    g.drawRect(drawX,drawY,OrWidth,OrHeight);
 }



 public void releasedOval()
 {
    initialOval = true;
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);
    g.drawOval(drawX,drawY,OrWidth,OrHeight);
 }


 
 public void releasedFRect()
 {
    initialFRect = true;
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);
    g.fillRect(drawX,drawY,OrWidth,OrHeight);
 }


 
 public void releasedFOval()
 {
    initialFOval = true;
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);
    g.fillOval(drawX - 1,drawY - 1,OrWidth + 2,OrHeight + 2);
 }


 
 public void updateMouseCoordinates(MouseEvent e)
 {
    String xCoor ="";
    String yCoor ="";

    if (e.getX() < 0) xCoor = "0";
    else
    {
       xCoor = String.valueOf(e.getX());
    }

    if (e.getY() < 0) xCoor = "0";
    else
    {
       yCoor = String.valueOf(e.getY());
    }
    mouseStatusBar.setText("x:" + xCoor + "   y:" + yCoor);
 }












 public void mouseExited(MouseEvent e)
 {
    updateMouseCoordinates(e);
 }



 public void mouseMoved(MouseEvent e)
 {
    updateMouseCoordinates(e);
 }


 
 public void mousePressed(MouseEvent e)
 {
    updateMouseCoordinates(e);
 }

    public void adjustmentValueChanged(AdjustmentEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

} 


