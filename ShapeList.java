package examples.RMIShape;
import java.rmi.*;
import java.util.ArrayList;
//import java.util.Vector;
import java.util.List;

public interface ShapeList extends Remote {
    Shape newShape(GraphicalObject g) throws RemoteException;  	    
    List allShapes()throws RemoteException;
    int getVersion() throws RemoteException;
    void register(WhiteboardCallback callback) throws RemoteException;
    void deregister(int callbackId) throws RemoteException;
    int getID() throws RemoteException;
}
