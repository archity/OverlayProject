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
            VirtualNode virtualNode = new VirtualNode(listOfPhysicalNodes.get(i));

            // Add the VN to an arraylist of Virtual Nodes.
            listOfVirtualNodes.add(virtualNode);

            System.out.println("Virtual Node " + nodeIDList.get(i) + " connected with physical node " + listOfPhysicalNodes.get(i).getID());

        }


    }
}
