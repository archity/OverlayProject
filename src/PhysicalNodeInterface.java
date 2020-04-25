import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PhysicalNodeInterface extends Remote
{
    public void addNeighbours(PhysicalNodeInterface neighbour, int id) throws RemoteException;
    public PhysicalNodeInterface getNeighbours(int id) throws RemoteException;
    public void sendMessage(String message, int id) throws RemoteException;
}
