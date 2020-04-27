import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PhysicalNodeInterface extends Remote
{
    int getID() throws RemoteException;
    void addNeighbour(PhysicalNodeInterface neighbour, int id) throws RemoteException;
    PhysicalNodeInterface getNeighbour(int id) throws RemoteException;
    void sendMessage(String message, int id) throws RemoteException;
}
