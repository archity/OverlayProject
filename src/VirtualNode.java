import java.rmi.RemoteException;

public class VirtualNode implements VirtualNodeInterface
{
    private PhysicalNode physicalNode;
    private VirtualNodeInterface clockNeighbour;
    private VirtualNodeInterface antiClockNeighbour;
    private int nodeID;

    public VirtualNode(PhysicalNode physicalNode, int nodeID)
    {
        this.physicalNode = physicalNode;
        this.clockNeighbour = null;
        this.antiClockNeighbour = null;
        this.nodeID = nodeID;
    }


    @Override
    public void initializeNeighbours(VirtualNodeInterface clockNeighbour, VirtualNodeInterface antiClockNeighbour)
    {
        this.clockNeighbour = clockNeighbour;
        this.antiClockNeighbour = antiClockNeighbour;
    }

    @Override
    public VirtualNodeInterface getClockNeighbour()
    {
        return this.clockNeighbour;
    }

    @Override
    public VirtualNodeInterface getAntiClockNeighbour()
    {
        return this.antiClockNeighbour;
    }

    @Override
    public int getID()
    {
        return this.nodeID;
    }

    @Override
    public void sendMessageTo(int receiverNodeID, int direction, String message) throws RemoteException
    {
        if(receiverNodeID == nodeID)    // Reached the intended VN
        {
            System.out.println("Message successfully reached VN " + this.nodeID + " !");
        }
        else
        {
            System.out.println("Next Destination: Node " + receiverNodeID);
            System.out.println("Message currently in Virtual Node " + nodeID);

            // Pass on the message to current VN's corresponding PN
            physicalNode.sendMessage(message, receiverNodeID);

        }

    }

}
