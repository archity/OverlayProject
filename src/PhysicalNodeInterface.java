import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface PhysicalNodeInterface extends Remote
{
    int getID() throws RemoteException;
    void addNeighbour(PhysicalNodeInterface neighbour, int id) throws RemoteException;
    PhysicalNodeInterface getNeighbour(int id) throws RemoteException;
    void sendMessage(String message, int destinationPhyNode) throws RemoteException;

    ArrayList<Integer> computeRouting(int destinationPhyNode, ArrayList<Integer> depthAndPath) throws  RemoteException;

}
