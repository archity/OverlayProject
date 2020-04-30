import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VirtualNodeInterface extends Remote
{
    void initializeNeighbours(VirtualNodeInterface clockNeighbour, VirtualNodeInterface antiClockNeighbour);
    VirtualNodeInterface getClockNeighbour();
    VirtualNodeInterface getAntiClockNeighbour();
    int getID();
    void sendClockwise(String message);
    void sendAnticlockwise(String message);
    void sendMessageTo(int receiverNodeID, int direction, String message) throws RemoteException;

}
