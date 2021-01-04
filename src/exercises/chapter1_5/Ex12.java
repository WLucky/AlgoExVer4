package exercises.chapter1_5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex12 {
	private int[] id;
	private int count;
	
	public Ex12(int N) {
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}

	public int count() {
		return count;
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	// quick-found
	public int find(int p) {
		while(p != id[p])
			p = id[p];
		
		return id[p];
	}

	public void union(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		if(pRoot == qRoot) return;
		
		while(p != pRoot)
		{
			int temp = p;
			p = id[p];
			id[temp] = qRoot;
		}
		id[pRoot] = qRoot;
		count--;
	}

	public static void main(String[] args) {
		int N = StdIn.readInt();
		Ex12 uf = new Ex12(N);
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (uf.connected(p, q)) {
				continue;
			}
			uf.union(p, q);
			StdOut.println(p + " " + q);
		}
		StdOut.println(uf.count() + " components");
	}
}
