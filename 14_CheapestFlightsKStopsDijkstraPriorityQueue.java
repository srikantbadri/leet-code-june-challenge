//Problem Link - https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/540/week-2-june-8th-june-14th/3360/

class Solution {
	class Pair {
		int city, cost;

		Pair(int city, int cost) {
			this.city = city;
			this.cost = cost;
		}
	}

	class City {
		int city, distFromSrc, costFromSrc;

		City(int city, int distFromSrc, int cost) {
			this.city = city;
			this.distFromSrc = distFromSrc;
			this.costFromSrc = cost;
		}
	}

	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
		if (n <= 0 || flights == null || flights.length == 0 || k < 0)
			return -1;
		// priority queue to keep the elements in the order of increasing costFromSrc
		Queue<City> pQueue = new PriorityQueue<>((City c1, City c2) -> c1.costFromSrc - c2.costFromSrc);
		List<List<Pair>> graph = new ArrayList<>();

		createGraph(n, flights, graph);

		// push the src node in priority queue
		pQueue.offer(new City(src, 0, 0));

		// loop while queue is empty
		while (!pQueue.isEmpty()) {
			City obj = pQueue.poll();

			// reached destination
			if (obj.city == dst) {
				return obj.costFromSrc;
			}

			// more than k stops, then dont add all the neighbors of this node
			if (obj.distFromSrc <= k) {
                List<Pair> list = graph.get(obj.city); 
				for (Pair pair : list) {
					pQueue.offer(new City(pair.city, obj.distFromSrc + 1, obj.costFromSrc + pair.cost));
				}
			}
		}

		return -1;
	}

	// create graph structure using adjacency list
	private void createGraph(int n, int[][] flights, List<List<Pair>> graph) {
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int[] flight : flights) {
			// flight[0] src
			// flight[1] dst
			// flight[2] cost
			graph.get(flight[0]).add(new Pair(flight[1], flight[2]));
		}
	}
}
