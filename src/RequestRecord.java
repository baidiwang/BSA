import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RequestRecord {

    public static String FILE_OF_REQUEST_OBJECTS = "requests.ser";

    public static RequestRecord requestRecord = new RequestRecord();

    public <T extends Action> Object makeRequest(Request<T> aRequest) {
        /*
         *
         * POSTCONDITION 1: aRequest is last object on FILE_OF_REQUEST_OBJECTS locally
         * AND a "written" message is on the console, including aRequest.toString()
         *
         * POSTCONDITION 2: As per aRequest.getAction().performAction()
         *
         */
        // POSTCONDITION 1
        try {
            if (Files.exists(Paths.get(FILE_OF_REQUEST_OBJECTS))) {
                // Append the request object to the existing file
                FileOutputStream fileOut = new FileOutputStream
                        (FILE_OF_REQUEST_OBJECTS, true);
                ObjectOutputStream out = new ObjectOutputStream(fileOut) {
                    @Override
                    protected void writeStreamHeader() throws IOException {
                        reset(); // Reset the stream header to avoid conflicts
                    }
                };
                out.writeObject(aRequest);
                System.out.println("Request object " + aRequest.toString() + " stored");
                out.close();
                fileOut.close();
            } else {
                // Create a new file and write the request object
                FileOutputStream fileOut = new
                        FileOutputStream(FILE_OF_REQUEST_OBJECTS);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(aRequest);
                System.out.println("Request object " + aRequest.toString() + " stored");
                        out.close();
                fileOut.close();
            }
        } catch (IOException i) {
            i.printStackTrace();
        }
        // POSTCONDITION 2
        try {
            Action action = aRequest.getAction();
            action.run();
            action.join();
            return action.getQuery();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void readRequests() {
        try {
            // Read the request objects from the file
            FileInputStream fileIn = new FileInputStream(FILE_OF_REQUEST_OBJECTS);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            while (true) {
                try {
                    Request<Action> request = (Request<Action>) in.readObject();
                    System.out.println("Read request: " +
                            request.getAction().toString());
                } catch (EOFException e) { // end of file
                    break;
                }
            }
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Request class not found");
            c.printStackTrace();
        }
    }
}
