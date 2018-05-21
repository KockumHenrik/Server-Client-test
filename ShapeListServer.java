/*
 * (1,2) Used eclipses IDE for compilation and execution, no particular settings
 * (4) No external libraries outside of java standards
 * (5) No policy file
 * Instruction:
 * Run ShapeListServer.java to start server
 * Run ShapeListClient.java to add a client to the server
 */

package examples.RMIShape;
import java.io.IOException;
import java.net.Socket;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class ShapeListServer {
	
	//Start server
    public static void main(String args[]) throws RemoteException{
    LocateRegistry.createRegistry(1099);    	 
        try{
            ShapeList aShapeList = new ShapeListServant();

            ShapeList stub = (ShapeList) UnicastRemoteObject.exportObject(aShapeList,0);
            Naming.rebind("ShapeList", aShapeList);
			
            System.out.println("ShapeList server ready");                      
        }
        catch(Exception e) {
            System.out.println("ShapeList server main " + e.getMessage());
        }
    }
}
