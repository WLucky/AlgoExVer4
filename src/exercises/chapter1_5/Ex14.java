package exercises.chapter1_5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

//Weighted quick-union by height
public class Ex14 {
	private int[] id;
	private int[] h;
	private int count;
	
	public Ex14(int N) {
		count = N;
		id = new int[N];
		h = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
		for (int i = 0; i < N; i++)
		{
			h[i] = i;
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
		
		if(h[pRoot] < h[qRoot])
		{
			id[pRoot] = qRoot;
		}
		else if(h[pRoot] > h[qRoot])
		{
			id[qRoot] = pRoot;
		}
		else //高度相等的情况
		{
			id[pRoot] = qRoot;
			h[qRoot] += 1;
		}
		count--;
	}

	public static void main(String[] args) {
		int N = StdIn.readInt();
		Ex14 uf = new Ex14(N);
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
