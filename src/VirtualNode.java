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


}
