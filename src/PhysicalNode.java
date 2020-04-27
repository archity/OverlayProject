import java.rmi.*;
import java.util.ArrayList;

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
    public void sendMessage(String message, int id) throws RemoteException
    {

    }
}
