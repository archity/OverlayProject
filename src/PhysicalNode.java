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
    public void sendMessage(String message, int destinationPhyNode) throws RemoteException
    {
        System.out.println("Message transferred to Physical Node " + this.phyNodeID);
        ArrayList<Integer> tracePath = new ArrayList<>();
        ArrayList<Integer> visitedNodes = new ArrayList<>();
        tracePath = computeRouting(destinationPhyNode, tracePath, -1, visitedNodes);


        //System.out.println("Size of path: " + tracePath.size());
        System.out.print("PATH: ");
        for (int i = 0; i < tracePath.size(); i++)
        {
            System.out.print(tracePath.get(i) + ", ");
        }
        System.out.print("\n");

        //------------------------------------------------------

        // Now that the path has been computed, send the message along this path.
        sendMessagesToNode(message, tracePath);

    }

    @Override
    public void sendMessagesToNode(String message, ArrayList<Integer> path) throws RemoteException
    {
        //System.out.println("Message currently in PN " + this.phyNodeID);
        if(path.get(path.size() - 1) == this.phyNodeID)
        {
            System.out.println("-----------------------");
            // The message has reached the Physical node
            return;
        }
        path.remove((Integer) this.phyNodeID);
        int neighbourToSendTo = path.get(0);

        for (int i = 0; i < listOfNeighbours.size(); i++)
        {
            if(listOfNeighbours.get(i).getID() == neighbourToSendTo)
            {
                this.listOfNeighbours.get(i).sendMessagesToNode(message, path);
            }
        }

    }

    @Override
    public ArrayList<Integer> computeRouting(int destinationPhyNode, ArrayList<Integer> path, int fatherID, ArrayList<Integer> visitedNodes) throws RemoteException
    {

        // Add the current node to the path list.
        path.add(this.phyNodeID);

        if(this.phyNodeID == destinationPhyNode)
        {
            return path;
        }


        visitedNodes.add(this.phyNodeID);


        // If still hasn't reached the destination AND the node doesn't have any neighbour
        // except the one which called it
        if(this.phyNodeID != destinationPhyNode && this.listOfNeighbours.size() == 1 && fatherID != -1)
        {
            path.remove((Integer) this.phyNodeID);
            return path;
        }

        // The ID of current Physical Node  is stored so as to not check it with the
        // next recursive call (else it leads to infinite loop)
        int fatherNode = this.phyNodeID;


        for(int i = 0; i < this.listOfNeighbours.size(); i++)
        {

            if(fatherID == this.listOfNeighbours.get(i).getID())
            {
                // you have reached the parent node which had itself called the current
                // node, so skip this parent (father) node, else there would be
                // infinite loop :)
                continue;
            }
            if(visitedNodes.contains(this.listOfNeighbours.get(i).getID()))
            {
                continue;
            }
            path = this.listOfNeighbours.get(i).computeRouting(destinationPhyNode, path, fatherNode, visitedNodes);
            if(path.contains(destinationPhyNode))
            {
                break;
            }
        }

        return path;

    }

}
