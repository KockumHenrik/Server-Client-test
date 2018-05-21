package examples.RMIShape;

import java.util.List;
import java.rmi.*;
import java.util.ArrayList;
//import java.util.Vector;

public class ShapeListServant implements ShapeList{
    private List<GraphicalObject> theList;
    private int version;
    private int callID;
    private List<WhiteboardCallback> client;
    private List<Integer> clientID;
    public int size;
    
    public ShapeListServant()throws RemoteException{
        theList = new ArrayList<GraphicalObject>();
        version = 0;
        callID = 0;
        client = new ArrayList<WhiteboardCallback>();
        size = 0;
        clientID = new ArrayList<Integer>();
    }
    
    public int getID() {
    	callID++;
    	return callID;
    }

    //If new shape has been added to the whiteboard the clients callback will be called and update the clients whiteboard to the latest verision
    //And if one of the registered clients have failed then the error will be caught and the server will be made aware
    public void update() throws RemoteException{
    	for(int i = 0; i < size; i++) {
    		try {
            	client.get(i).callback();
    		}
    		catch(Exception e) {
    			deregister(clientID.get(i));
    			i--;
            }    		
        }
    }
    
    public Shape newShape(GraphicalObject g) throws RemoteException{
    	version++;
       	Shape s = new ShapeServant(g, version);
        theList.add(g); 
        update();
        return s;
        
    }

    public List<GraphicalObject> allShapes()throws RemoteException{
        return theList;
    }

    public int getVersion() throws RemoteException{
        return version;
    }

    //If new client has been added add to the client list and tell server that new client has been added
	public void register(WhiteboardCallback callback) throws RemoteException {
		size++;		
		client.add(callback);
		clientID.add(callID);
		System.out.println("Num of callbackObjs = " + client.size());
	}
	
	//If client leave then their ID will be used to remove them from the list with active clients, then the server is made aware.
	public void deregister(int callbackId) throws RemoteException {		
		for(int i = 0; i < clientID.size(); i++) {
			if(clientID.get(i)==callbackId) {
				client.remove(i);
				clientID.remove(i);
			}
		}
		System.out.println("Num of callbackObjs = " + client.size());
		size--;
	}
}
