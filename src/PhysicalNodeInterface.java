import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface PhysicalNodeInterface extends Remote
{
    int getID() throws RemoteException;
    void addNeighbour(PhysicalNodeInterface neighbour, int id) throws RemoteException;

    // Called by VN to transfer message to corresponding PN
    void sendMessage(String message, int destinationPhyNode) throws RemoteException;

    // Called by sendMessage() to send message within the Physical Network
    void sendMessagesToNode(String message, ArrayList<Integer> path) throws RemoteException;

    // Computer routing within Physical Network
    ArrayList<Integer> computeRouting(int destinationPhyNode, ArrayList<Integer> depthAndPath, int fatherID, ArrayList<Integer> visitedNodes) throws  RemoteException;

}
