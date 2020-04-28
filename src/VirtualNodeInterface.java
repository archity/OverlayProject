import java.rmi.Remote;

public interface VirtualNodeInterface extends Remote
{
    void initializeNeighbours(VirtualNodeInterface clockNeighbour, VirtualNodeInterface antiClockNeighbour);
    VirtualNodeInterface getClockNeighbour();
    VirtualNodeInterface getAntiClockNeighbour();
    int getID();

}
