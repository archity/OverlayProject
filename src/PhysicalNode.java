import java.rmi.*;

public class PhysicalNode implements PhysicalNodeInterface {

    @Override
    public void addNeighbour(PhysicalNodeInterface neighbour, int id) throws RemoteException {

    }

    @Override
    public PhysicalNodeInterface getNeighbour(int id) throws RemoteException {
        return null;
    }

    @Override
    public void sendMessage(String message, int id) throws RemoteException {

    }
}
