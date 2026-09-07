import java.util.HashMap;
import java.util.Map;

class UndergroundSystem {
    
    // Helper class to hold check-in information
    private class CheckInInfo {
        String station;
        int time;
        
        public CheckInInfo(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }
    
    // Helper class to hold route statistics (total time and trip count)
    private class RouteInfo {
        double totalTime;
        int count;
        
        public RouteInfo() {
            this.totalTime = 0.0;
            this.count = 0;
        }
    }

    private Map<Integer, CheckInInfo> checkIns;
    private Map<String, RouteInfo> routeData;

    public UndergroundSystem() {
        checkIns = new HashMap<>();
        routeData = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        // Record the passenger's check-in
        checkIns.put(id, new CheckInInfo(stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t) {
        // Retrieve the check-in data for this passenger
        CheckInInfo startData = checkIns.get(id);
        
        // Calculate travel time and construct the route key
        String routeKey = startData.station + "->" + stationName;
        int tripTime = t - startData.time;
        
        // Initialize the route in the map if it doesn't exist yet
        routeData.putIfAbsent(routeKey, new RouteInfo());
        
        // Update the total time and count for this route
        RouteInfo stats = routeData.get(routeKey);
        stats.totalTime += tripTime;
        stats.count++;
        
        // Remove the passenger from the active check-ins to save memory
        checkIns.remove(id);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        // Construct the route key and return the average
        String routeKey = startStation + "->" + endStation;
        RouteInfo stats = routeData.get(routeKey);
        
        return stats.totalTime / stats.count;
    }
}
