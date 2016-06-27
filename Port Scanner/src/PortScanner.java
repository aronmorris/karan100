import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class PortScanner {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		final ExecutorService es = Executors.newFixedThreadPool(20);
		final String ip = "127.0.0.1"; //point home
		final int timeout = 200; //millis to decide port is closed
		final List<Future<Boolean>> futures = new ArrayList<>();
		
		int portNo = 0;
		
		for (int port = 1; port < 65535; port++) {
			futures.add(portIsOpen(es, ip, port, timeout));
		}
	
		es.shutdown();
	
		for (final Future<Boolean> f : futures) {
			
			portNo++;
			
			if (f.get()) {
				System.out.println("Port " + portNo + " is open.");
			}
		}
		
	}
	
	public static Future<Boolean> portIsOpen(final ExecutorService es, final String ip, final int port, final int timeout) {
		return es.submit(new Callable<Boolean>() {
			@Override public Boolean call() {
				try {
					Socket socket = new Socket();
					socket.connect(new InetSocketAddress(ip, port), timeout);
					socket.close();
					return true;
				} catch (Exception e) {
					return false;
				}
			}
		});
	}
	
}
