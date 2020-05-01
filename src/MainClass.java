import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Scanner;

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

        System.out.println("------------------------------------------------");
        // Initialize the physical network
        PhysicalNetwork physicalNetwork = new PhysicalNetwork(inputFile);

        // Initialize the virtual network
        VirtualNetwork virtualNetwork = new VirtualNetwork(physicalNetwork.getPhysicalNodes());


        while(true)
        {
            System.out.println("Send a message from one node to another");
            System.out.println("SYNTAX: send <VNx> <VNy> <CW / ACW> <message string>");

            Scanner messageScanner = new Scanner(System.in);

            String inputString = messageScanner.nextLine();
            Scanner scanner = new Scanner(inputString);
            String cmd = scanner.next();
            int senderNode = scanner.nextInt();
            int receiverNode = scanner.nextInt();
            if (senderNode == receiverNode)
            {
                System.out.println("Please send to a different node only");
                continue;
            }
            String dirStr = scanner.next();
            int direction = 0;
            if(dirStr.equals("CW"))
            {
                direction = 1;
            }
            else if(dirStr.equals("ACW"))
            {
                direction = -1;
            }

            else
            {
                System.out.println("Please specify the direction as CW or ACW.");
                continue;
            }

            String message = scanner.next();
            if(inputString.equals("exit"))
            {
                break;
            }
            else
            {
                System.out.println("------------------------------------------------");
                virtualNetwork.messageDesk(senderNode, receiverNode, direction, message);
                System.out.println("------------------------------------------------");
                System.out.println("\n\n\n\n");
            }

        }

    }

}
