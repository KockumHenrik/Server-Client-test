package examples.RMIShape;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class CallbackImpl implements WhiteboardCallback {

	public void callback() throws RemoteException {
		try {
			ShapeList aShapeList  = (ShapeList) Naming.lookup("//localhost/ShapeList");			
			System.out.println();
			System.out.println("The list has been modified:");
			System.out.println("--------------------------");
			for(int i=0; i<aShapeList.allShapes().size(); i++){
			    GraphicalObject g = (GraphicalObject) aShapeList.allShapes().get(i);
			    g.print();
			}
			System.out.println("--------------------------");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}

}
