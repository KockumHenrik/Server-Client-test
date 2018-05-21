/*
 * (1,2) Used eclipses IDE for compilation and execution, no particular settings
 * (4) No external libraries outside of java standards
 * (5) No policy file
 * Instruction:
 * Run ShapeListServer.java to start server
 * Run ShapeListClient.java to add a client to the server
 */

package examples.RMIShape;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.Rectangle;
import java.net.Socket;
import java.awt.Color;

public class ShapeListClient{
	
	private int Id;
	private ShapeList aShapeList;
	private List<GraphicalObject> listOfGObjs;

	public void run() {
		try {
			aShapeList  = (ShapeList) Naming.lookup("//localhost/ShapeList");			
			
			//Get id to use as port
			Id = aShapeList.getID();
			
			WhiteboardCallback callback = new CallbackImpl();			
			WhiteboardCallback stub = (WhiteboardCallback) UnicastRemoteObject.exportObject(callback,Id);
            Naming.rebind("client", callback);
            
            aShapeList.register(callback);
		    	    		    
		    Scanner sc = new Scanner(System.in);
		    String option = "";
		    
		    //If entry is E then program will quit
		    while(!option.equals("E")) {		    		
		    	System.out.print("[R]ead, [W]rite, or [E]xit: ");	   
		    	option = sc.nextLine();
		    	listOfGObjs = aShapeList.allShapes();
		    	
		    	//If entry is W then program will ask to submit a shape, if shape allready exist on whiteboard then nothing happens
		    	if(option.equals("W")) {
		    		System.out.print("Enter the type of geometric object to write: ");
		    		GraphicalObject g = new GraphicalObject(sc.nextLine());
		    		boolean check = false;
		    		for(int j = 0; j < listOfGObjs.size();j++) {
		    			if(g.getType().equals(listOfGObjs.get(j).getType())) {
		    				check = true;
		    			}		    			
		    		}
		    		if(check == false) {
		    			aShapeList.newShape(g);
		    		}		    		
		    	}
		    	
		    	//If entry is R then the current whiteboard will be shown
		    	else if (option.equals("R")) {
		    		for(int i=0; i<listOfGObjs.size(); i++){
					    GraphicalObject g = listOfGObjs.get(i);
					    g.print();
					}
		    	}
		    }
		    aShapeList.deregister(Id);
		    System.exit(0);
		}
		catch(RemoteException e){
	    	System.out.println("allShapes: " + e.getMessage());	    	
		}		
	    catch(Exception e){
	        System.out.println("Lookup: " + e.getMessage());
        }
	}
	
    public static void main(String args[]){
    	ShapeListClient c = new ShapeListClient();
		c.run();	
    }
}


	             
