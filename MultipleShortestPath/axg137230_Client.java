import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class is the starting point. It takes input from stdin, creates the
 * graph and add edges to it.
 * 
 * @author Abhishek Gupta (axg137230) and Manuj Singh (mxs135630)
 *
 */
public class axg137230_Client {
	public static Scanner scanner;
	private static axg137230_ShortestPath sp;

	public static void main(String[] args) throws Exception {
		try {
			scanner = new Scanner(System.in);
			int V = 0, E = 0, s = 0, t = 0;
			V = Math.min(scanner.nextInt(), 1000000);
			E = Math.min(scanner.nextInt(), 10000000);
			s = scanner.nextInt();
			t = scanner.nextInt();
			sp = new axg137230_ShortestPath(V, s, t);
			for (int i = 0; i < E; i++) {
				// System.out.println(lineNo++);
				int u = scanner.nextInt();
				int v = scanner.nextInt();
				int w = scanner.nextInt();
				sp.addEdge(u, v, w);
			}
			sp.findShortestPaths();
		} catch (NoSuchElementException ne) {
			System.out.println(ne.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
