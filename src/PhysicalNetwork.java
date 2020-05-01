import org.w3c.dom.Node;

import java.io.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class PhysicalNetwork
{
    private String inputFile;
    private int numberOfNodes;
    private ArrayList<Integer> nodeIDList;

    private ArrayList<PhysicalNode> listOfNodes;
    private ArrayList<PhysicalNodeInterface> listOfNodesInterfaces;

    private HashMap<Integer, ArrayList<Integer>> nodeNeighbourMap;

    public PhysicalNetwork(String inputFile) throws IOException
    {
        this.inputFile = inputFile;
        numberOfNodes = 0;
        nodeNeighbourMap = new HashMap<>();
        listOfNodes = new ArrayList<>();
        listOfNodesInterfaces = new ArrayList<>();
        nodeIDList = new ArrayList<>();

        readFile(this.inputFile);
        createRemoteNetwork();
        connectNeighbours();

    }

    /*  FUNC: createRemoteNetwork()

        This method will use the Java RMI to create a physical network of nodes, the information
        about which is extracted from the inputFile by the function readFile().
    */
    void createRemoteNetwork() throws RemoteException
    {
        //PhysicalNetwork physicalNetwork = new PhysicalNetwork("fd");

        for (int i= 0; i < numberOfNodes; i++)
        {
            PhysicalNode physicalNode = new PhysicalNode(nodeIDList.get(i));
            PhysicalNodeInterface physicalNodeInterface = (PhysicalNodeInterface) UnicastRemoteObject.exportObject(physicalNode, 0);
            listOfNodes.add(physicalNode);
            listOfNodesInterfaces.add(physicalNodeInterface);
        }

        System.out.println("Physical Network created");
    }


    /*  FUNC: connectNeighbours()

        The nodes that are supposed to be physically connected are supposed to have an instance of
        stub of each other. Usually, rmiregistry takes care of that. But in our case, we will pass
        an instance of the stub of the node to the other node that is supposed to be connected to.
        The function connectNeighbours() takes care of this.
     */
    void connectNeighbours() throws RemoteException
    {
        for(int i = 0; i < numberOfNodes; i++)
        {
            // First get the number of neighbours of ith node
            int noOfNeighbours = nodeNeighbourMap.get(nodeIDList.get(i)).size();

            for(int j = 0; j < noOfNeighbours; j++)
            {
                // Get the ID of jth neighbour of ith node (and put in x)
                int x = nodeNeighbourMap.get(nodeIDList.get(i)).get(j);

                // Add the current node (i) as a neighbour of node j (whose ID is x) by passing i's stub and ID
                // as function parameters.
                listOfNodesInterfaces.get( x ).addNeighbour(listOfNodesInterfaces.get(i), nodeIDList.get(i));
            }
        }

        System.out.println("Neighbours connected");
        System.out.println("LIST OF NEIGHBOURS:");

        /*
        for(int i = 0; i < listOfNodesInterfaces.size(); i++)
        {
            for(int j = 0; j < listOfNodesInterfaces.get(i).getNeighbour().size(); j++)
            {
                System.out.print(listOfNodesInterfaces.get(i).getNeighbour().get(j).getID());
            }

            System.out.println("\n");
        }
        */


    }

    /*  FUNC: readFile()

        This function is used for extracting all information from the inputMatrix.txt file.
        Information includes:

        - No. of nodes (1st line)
        - Node ID (2nd line)
        - Neighbourhood connection (rest of the lines)
     */
    public HashMap<Integer, ArrayList<Integer>> readFile(String fileName) throws IOException
    {
        ArrayList<String> graphNetworkInfo = new ArrayList<>();
        FileReader fileReader = null;
        File f = new File(fileName);

        try
        {
            fileReader = new FileReader(fileName);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int ch;
        String getLine = "";

        getLine = bufferedReader.readLine();
        numberOfNodes = Integer.parseInt(getLine);

        getLine = bufferedReader.readLine();
        getLine = bufferedReader.readLine();
        int i = 0;
        while (i < numberOfNodes)
        {
            nodeIDList.add(Integer.parseInt(String.valueOf(getLine.charAt(i))));
            i++;
        }
        getLine = bufferedReader.readLine();

        //ArrayList<Integer> neighbourList = new ArrayList<>();
        for (int j = 0; j < numberOfNodes; j++)
        {
            ArrayList<Integer> neighbourList = new ArrayList<>();
            getLine = bufferedReader.readLine();
            i = 0;

            while (i < numberOfNodes)
            {
                if(Integer.parseInt(String.valueOf(getLine.charAt(i))) == 1)
                {
                    //It means ith element is the neighbour of jth node.
                    neighbourList.add(i);
                }
                i++;
            }
            nodeNeighbourMap.put(j, neighbourList);
            //neighbourList.clear();
        }
        //System.out.println("inputFile read successfully !");

        /*
        for(int k = 0; k< nodeNeighbourMap.size(); k++)
        {
            for(int l = 0; l < nodeNeighbourMap.get(k).size(); l++)
            {
                System.out.print(nodeNeighbourMap.get(k).get(l));
            }
            System.out.println("\n");
        }
         */
        return nodeNeighbourMap;
    }

    public ArrayList<PhysicalNode> getPhysicalNodes()
    {
        return listOfNodes;
    }

}
