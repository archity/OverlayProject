import java.rmi.RemoteException;
import java.util.ArrayList;

public class VirtualNetwork
{
    private ArrayList<VirtualNodeInterface> listOfVirtualNodeInterface;
    private ArrayList<VirtualNode> listOfVirtualNodes;
    private ArrayList<PhysicalNode> listOfPhysicalNodes;
    private ArrayList<Integer> nodeIDList;

    public VirtualNetwork(ArrayList<PhysicalNode> listOfPhysicalNodes) throws RemoteException
    {
        listOfVirtualNodeInterface = new ArrayList<>();
        listOfVirtualNodes = new ArrayList<>();
        nodeIDList = new ArrayList<>();
        this.listOfPhysicalNodes = listOfPhysicalNodes;
        //System.out.println("VN's constructor");

        initializeNetwork();
        connectNeighbours();
    }

    public void initializeNetwork() throws RemoteException
    {
        int noOfPhyNodes = listOfPhysicalNodes.size();
        System.out.println("Nodes:" + noOfPhyNodes + "\n");
        for (int i = 0; i < noOfPhyNodes; i++)
        {
            // Keeping the assignment of Virtual Node ID simple
            nodeIDList.add(i);

            // Add (pass) the physical node to virtual node's constructor.
            VirtualNode virtualNode = new VirtualNode(listOfPhysicalNodes.get(i), i);

            // Add the VN to an arraylist of Virtual Nodes.
            listOfVirtualNodes.add(virtualNode);

            listOfVirtualNodeInterface.add(virtualNode);

            System.out.println("Virtual Node " + nodeIDList.get(i) + " connected with physical node " + listOfPhysicalNodes.get(i).getID());

        }
        System.out.println("\n");

    }

    public void connectNeighbours()
    {
        int size = listOfVirtualNodes.size();
        //System.out.println("SIZE: " + size);

        for(int i = 0; i < size; i++)
        {
            if(i > 0 & i < size - 1)
            {
                listOfVirtualNodeInterface.get(i).initializeNeighbours(listOfVirtualNodeInterface.get(i + 1), listOfVirtualNodeInterface.get(i - 1));
            }
            else if(i == 0)
            {
                listOfVirtualNodeInterface.get(i).initializeNeighbours(listOfVirtualNodeInterface.get(i + 1), listOfVirtualNodeInterface.get(size - 1));
            }
            else if(i == size - 1)
            {
                listOfVirtualNodeInterface.get(i).initializeNeighbours(listOfVirtualNodeInterface.get(0), listOfVirtualNodeInterface.get(i - 1));
            }

            // Printout current VN's neighbours
            System.out.println("Virtual Node " + nodeIDList.get(i) + " has neighbours " + listOfVirtualNodeInterface.get(i).getClockNeighbour().getID() + " and " + listOfVirtualNodeInterface.get(i).getAntiClockNeighbour().getID());
        }
        System.out.println("\n");
    }

    public void messageDesk(int senderNode, int receiverNode, int direction, String message) throws RemoteException
    {
        int i = senderNode;
        while(true)
        {
            int currentNode = i;

            if(i == receiverNode)
            {
                listOfVirtualNodeInterface.get(currentNode).sendMessageTo(i, -1, "Message reached successfully VN ");
                break;
            }


            if(direction == 1)
            {
                i = (i + 1) % listOfVirtualNodes.size();
            }
            else if (direction == -1)
            {
                if (i == 0)
                {
                    i = listOfVirtualNodes.size() - 1;
                }
                else
                {
                    i = (i - 1) % listOfVirtualNodes.size();
                }
            }

            // Pass on the message to the specific VN, telling the message and which VN neighbour
            // (i) to pass the message to.
            listOfVirtualNodeInterface.get(currentNode).sendMessageTo(i, direction, message);
        }



    }


}
