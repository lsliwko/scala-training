package test;

public class MainAppJavaCities {

    public static void main(String[] args) {
        MainAppJavaCities mainApp = new MainAppJavaCities();
        System.out.println(mainApp.findCircleNum(new int[][] {
                {1,2},{2,1}//{1,1,0}, {1,1,0}, {0,0,1}
        }));
    }

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];

        int newCircles = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            newCircles++;
            
            System.out.println("New circle");
            markVisited(i, isConnected, visited);
        }
        
        return newCircles;
    }
    
    private void markVisited(int iCity, int[][] isConnected, boolean[] visited) {
        if (visited[iCity]) return;
        visited[iCity] = true;
        
        System.out.println("Checking: " + iCity);
        
        for (int i = 0; i < isConnected.length; i++) {
            if (i == iCity) continue;
            if (isConnected[iCity][i] == 1) {
                markVisited(i, isConnected, visited);
            }
        }
    }
}
