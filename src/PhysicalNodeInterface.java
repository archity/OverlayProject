import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface PhysicalNodeInterface extends Remote
{
    int getID() throws RemoteException;
    void addNeighbour(PhysicalNodeInterface neighbour, int id) throws RemoteException;
    ArrayList<PhysicalNodeInterface> getNeighbour() throws RemoteException;
    void sendMessage(String message, int destinationPhyNode) throws RemoteException;
    void sendMessagesToNode(String message, ArrayList<Integer> path) throws RemoteException;

    ArrayList<Integer> computeRouting(int destinationPhyNode, ArrayList<Integer> depthAndPath, int fatherID, ArrayList<Integer> visitedNodes) throws  RemoteException;

}
