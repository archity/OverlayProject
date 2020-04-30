import java.lang.reflect.Array;
import java.rmi.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PhysicalNode implements PhysicalNodeInterface
{
    private ArrayList<PhysicalNodeInterface> listOfNeighbours;
    private int phyNodeID;

    public PhysicalNode(int ID)
    {
        listOfNeighbours = new ArrayList<>();
        phyNodeID = ID;
    }


    @Override
    public int getID() throws RemoteException
    {
        return phyNodeID;
    }

    @Override
    public void addNeighbour(PhysicalNodeInterface neighbour, int id) throws RemoteException
    {
        listOfNeighbours.add(neighbour);
    }

    @Override
    public PhysicalNodeInterface getNeighbour(int id) throws RemoteException
    {
        return null;
    }

    @Override
    public void sendMessage(String message, int destinationPhyNode) throws RemoteException
    {
        System.out.println("Message transferred to Physical Node " + this.phyNodeID);
        ArrayList<Integer> tracePath = new ArrayList<>();
        tracePath = computeRouting(destinationPhyNode, tracePath);
        System.out.println("Size of path: " + tracePath.size());
    }

    @Override
    public ArrayList<Integer> computeRouting(int destinationPhyNode, ArrayList<Integer> path) throws RemoteException
    {

        path.add(this.phyNodeID);
        if(this.phyNodeID == destinationPhyNode)
        {
            return path;
        }

        for(int i = 0; i < listOfNeighbours.size(); i++)
        {
            return listOfNeighbours.get(i).computeRouting(destinationPhyNode, path);
        }

        return path;

    }
}
