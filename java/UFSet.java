package peter.util;

import java.util.*;

public class UFSet {
	int[] parent;
	int[] rank;
	
	public UFSet(int size) {
		parent = new int[size];
		rank = new int[size];
		for (int i = 0; i < size; i++) {
			parent[i] = i;
			rank[i] = 1;
		}
	}
		
	public int find(int u) {
		if (u == parent[u])
			return u;
		List<Integer> path = new ArrayList<Integer>();
		while (u != parent[u]) {
			path.add(u);
			u = parent[u];
		}
		
		//	Path compression
		for (int v : path)
			parent[v] = u;
		return u;
	}
	
	public void union(int u, int v) {
		u = find(u); v = find(v);
		if (u == v)
			return;
		if (rank[u] < rank[v]) {
			parent[u] = v;
			rank[v] += rank[u];
		}
		else {
			parent[v] = u;
			rank[u] += rank[v];
		}
	}
	
	public int getRank(int u) {
		return rank[find(u)];
	}
	
	public int size() {
		return parent.length;
	}
}
