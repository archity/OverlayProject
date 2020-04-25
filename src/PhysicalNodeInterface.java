import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PhysicalNodeInterface extends Remote
{
    public void addNeighbour(PhysicalNodeInterface neighbour, int id) throws RemoteException;
    public PhysicalNodeInterface getNeighbour(int id) throws RemoteException;
    public void sendMessage(String message, int id) throws RemoteException;
}
