import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PhysicalNetwork
{
    private String inputFile;
    private int numberOfNodes;
    private ArrayList<Integer> NodeIDList;

    private HashMap<Integer, ArrayList<Integer>> nodeNeighbourMap;

    public PhysicalNetwork(String inputFile)
    {
        this.inputFile = inputFile;
        numberOfNodes = 0;
        nodeNeighbourMap = new HashMap<>();
    }

    /* This method will use the Java RMI to create a physical network of nodes, the information
    about which is extracted from the inputFile by the function readFile().
    */
    void CreateRemoteNetwork()
    {
        

    }

    /*  This function is used for extracting all information from the inputMatrix.txt file.
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
            NodeIDList.add(Integer.parseInt(String.valueOf(getLine.charAt(i))));
            i++;
        }
        getLine = bufferedReader.readLine();

        ArrayList<Integer> neighbourList = new ArrayList<>();
        for (int j = 0; j < numberOfNodes; j++)
        {
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
        }

        return nodeNeighbourMap;
    }
}
