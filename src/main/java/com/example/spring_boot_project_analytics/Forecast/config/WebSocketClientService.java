//import io.socket.client.IO;
//import io.socket.client.Socket;
//import io.socket.emitter.Emitter;
//import org.springframework.stereotype.Service;
//
//import java.net.URISyntaxException;
//
//@Service
//public class WebSocketClientService {
//
//    public void sendDataAndGetResponse(String url, String data) {
//        try {
//            // Connect to the Socket.IO server
//            Socket socket = IO.socket(url);
//
//            // Define event handler for receiving response
//            socket.on("responseEventName", new Emitter.Listener() {
//                @Override
//                public void call(Object... args) {
//                    String response = (String) args[0];
//                    System.out.println("Received response from server: " + response);
//                    // Process the response and add it to the database
//                    saveResponseToDatabase(response);
//                    // Close the socket after receiving the response
//                    socket.close();
//                }
//            });
//
//            // Connect to the server
//            socket.connect();
//
//            // Send data to the server
//            socket.emit("requestDataEventName", data);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void saveResponseToDatabase(String response) {
//        // Implement logic to save the response to the database
//        // For example:
//        // forecastResultRepository.save(new ForecastResult(response));
//    }
//}
