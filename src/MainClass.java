import java.io.IOException;
import java.rmi.RemoteException;

import static java.lang.System.exit;

public class MainClass
{
    public static void main(String [] args) throws IOException
    {
        String inputFile;
        if (args.length < 1)
        {
            System.out.println("Usage: java MainClass <inputMatrix.txt>");
            exit(0);
        }
        inputFile = args[0];

        // Initialize the physical network
        PhysicalNetwork physicalNetwork = new PhysicalNetwork(inputFile);

        // Initialize the virtual network
        VirtualNetwork virtualNetwork = new VirtualNetwork(physicalNetwork.getPhysicalNodes());



    }

}
