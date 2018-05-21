package examples.RMIShape;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface WhiteboardCallback extends Remote{
	void callback() throws RemoteException;
}
