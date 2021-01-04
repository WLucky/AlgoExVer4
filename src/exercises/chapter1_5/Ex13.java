package exercises.chapter1_5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
//Weighted quick-union with path compression

public class Ex13 {
	private int[] id;
	private int[] sz;
	private int count;
	
	public Ex13(int N) {
		count = N;
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
		for (int i = 0; i < N; i++)
		{
			sz[i] = i;
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
		int smallerRoot = pRoot;
		int largerRoot = qRoot;
		int index = p;
		if(pRoot == qRoot) return;
		
		if(sz[pRoot] > sz[qRoot])
		{
			smallerRoot = qRoot;
			largerRoot = pRoot;
			index = q;
		}
		while(index != smallerRoot)
		{
			int temp = index;
			index = id[index];
			id[temp] = largerRoot;
		}
		id[smallerRoot] = largerRoot;
		
		count--;
	}

	public static void main(String[] args) {
		int N = StdIn.readInt();
		Ex13 uf = new Ex13(N);
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
