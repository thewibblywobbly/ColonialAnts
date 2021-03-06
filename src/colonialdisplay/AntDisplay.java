///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package colonialdisplay;
//
//import colonialants.Ant;
//import colonialants.CommonScents;
//import colonialants.Environment;
//import colonialants.Leaf;
//import colonialants.Sand;
//import colonialants.Stream;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.graphics.DeviceData;
//import org.eclipse.swt.graphics.Image;
//import org.eclipse.swt.layout.FillLayout;
//import org.eclipse.swt.widgets.Canvas;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Event;
//import org.eclipse.swt.widgets.Group;
//import org.eclipse.swt.widgets.Listener;
//import org.eclipse.swt.widgets.Shell;
//
///**
// *
// * @author George McDaid
// */
//public class AntDisplay {
//
//    Display display;
//    Shell shell;
//    Composite composite;
//    Group group;
//    Canvas canvas;    
//    
//    Environment e;
//    
//    Image sand,
//          dirt,
//          stream,
//          leaf,
//          ant,
//          scent;
//    
//    private int          x               = 0;
//    private int          y               = 0;
//    private int          r               = 15;    
//    
//    // The timer interval in milliseconds
//    private static final int    TIMER_INTERVAL  = 10;
//    
//    Runnable drawThread;
//
//    boolean forward = true;
//    
//    protected void initView(){
//        DeviceData data = new DeviceData();
//        data.tracking = true;
//        display = new Display(data);
//        //Sleak sleak = new Sleak();
//        //sleak.open();
//        shell = new Shell(display);
//        
//        shell.setLayout(new FillLayout(SWT.HORIZONTAL));
//        shell.setSize(770, 770);
//        
//        composite = new Composite(shell, SWT.NONE);
//        composite.setLayout(new FillLayout(SWT.HORIZONTAL));
//        
//        group = new Group(composite, SWT.SHADOW_ETCHED_IN);
//        group.setText("The Colonial Ants");
//        group.setLayout(new FillLayout(SWT.HORIZONTAL));
//        
//        canvas = new Canvas(group, SWT.DOUBLE_BUFFERED);
//        
//        //final Image image = new Image(display, "src/swtDemo/angryBird.jpg");
//        
//        canvas.addListener(SWT.Paint, new Listener() {
//
//            @Override
//
//            public void handleEvent(Event event) {
//
//                renderModel(event);
//                //renderCircle(event);
//                renderAnt(event);
//                renderPheromone(event);
//                //event.gc.drawImage(image, 200, 200);
//
//            }
//            
//        });
//        
//        drawThread = new Runnable(){
//            @Override
//            public void run()
//            {
//                canvas.redraw();
//
//                display.timerExec(TIMER_INTERVAL, this);
//            }
//        };
//        
//        display.timerExec(TIMER_INTERVAL, drawThread);
//        
//    }
//        
//    protected void showView(){
//        shell.open();
//
//        while (!shell.isDisposed()) {
//            if (!display.readAndDispatch()) {
//                display.sleep();
//            }
//        }
//
//        // Kill the timer
//        display.timerExec(-1, drawThread);
//        
//        display.dispose();
//        
//        
//    }
//    
//    protected void initModel(){
//        e = new Environment();
//        e.initEmptyField();
//        e.addTestAnts();
//        e.addTestScent();
//    }
//    
//    protected void renderModel(Event event){
//        sand = new Image(display, "src/colonialimages/Sand.png");
//        leaf = new Image(display, "src/colonialimages/Leaf.png");
//        stream = new Image(display, "src/colonialimages/Water.png");
//        dirt = new Image(display, "src/colonialimages/CrackedEarth.png");
//        
//        for (GridLocation[] location : e.getLocations()) {
//            for (GridLocation space : location) {
//                int s = (int)(e.getSpaceSize()/2);
//                double cx = space.getX();
//                double cy = space.getY();
//                
//                //Color c3;
//                
//                if(space.getTerrain() instanceof Sand){
//                    //c3 = new Color(event.display, 217, 209, 128);
//                    event.gc.drawImage(sand, (int)(cx-s), (int)(cy-s));
//                }else if(space.getTerrain() instanceof Leaf){
//                    //c3 = new Color(event.display, 15, 186, 9);
//                    event.gc.drawImage(leaf, (int)(cx-s), (int)(cy-s));
//                }else if(space.getTerrain() instanceof Stream){
//                    //c3 = new Color(event.display, 85, 60, 245);
//                    event.gc.drawImage(stream, (int)(cx-s), (int)(cy-s));
//                }else{
//                    //c3 = new Color(event.display, 33, 200, 100);
//                    event.gc.drawImage(dirt, (int)(cx-s), (int)(cy-s));
//                }
//                
//                //event.gc.setBackground(c3);
//                //event.gc.fillRectangle((int)(cx-s/2), (int)(cy-s/2), s, s);
//                
//            }
//        }
//        
//        sand.dispose();
//        leaf.dispose();
//        stream.dispose();
//        dirt.dispose();
//    }
//    
//    private void renderAnt(Event event) {
//        Ant[] ants = e.getTestAnts();
//        Image antImg = new Image(event.display, "src/colonialimages/AntBitNoMandibles.png");
//        for (Ant a : ants) {
//            event.gc.drawImage(antImg, a.getLocation().getX(), a.getLocation().getY());
//            
//            if(!a.isMoving()){
//                //a.changeDest(e.getSquare(a.getLocation().getPoint()));
//            }
//            
//            a.update();
//        }
//        antImg.dispose();
//        //Color c3 = new Color(event.display, 0, 0, 0);
//        //event.gc.setBackground(c3);
//        //event.gc.fillOval(a.getLocation().getX(), a.getLocation().getY(), r*2, r*2);
//        
//        
//        
//    }
//    
//    private void renderPheromone(Event event) {
//        CommonScents s = e.getTestScent();
//        Image scentImg = new Image(event.display, "src/colonialimages/PheromoneNone.png");
//        //Color c3 = new Color(event.display, 0, 0, 0);
//        //event.gc.setBackground(c3);
//        //event.gc.fillOval(a.getLocation().getX(), a.getLocation().getY(), r*2, r*2);
//        event.gc.drawImage(scentImg, s.getLocation().getX(), s.getLocation().getY());
//        scentImg.dispose();
//        //e.getSquare(s.getLocation());                
//      }
//    
//    protected void start(){
//        initModel();
//        initView();
//        showView();
//    }
//    
//    public static void main(String[] args) {
//        AntDisplay ad = new AntDisplay();
//        ad.start();
//    }
//}
