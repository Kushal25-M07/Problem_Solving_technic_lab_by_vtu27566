import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Student implements Comparable<Student> {
    private int id;
    private String name;
    private double cgpa;
    
    public Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }
    
    public int getID() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public double getCGPA() {
        return cgpa;
    }
    
    @Override
    public int compareTo(Student other) {
        // Priority 1: Highest CGPA is served first (Descending order)
        if (Double.compare(other.cgpa, this.cgpa) != 0) {
            return Double.compare(other.cgpa, this.cgpa);
        }
        // Priority 2: Ascending alphabetical order by name
        if (!this.name.equals(other.name)) {
            return this.name.compareTo(other.name);
        }
        // Priority 3: Ascending order of ID
        return Integer.compare(this.id, other.id);
    }
}

class Priorities {
    public List<Student> getStudents(List<String> events) {
        PriorityQueue<Student> pq = new PriorityQueue<>();
        
        for (String event : events) {
            String[] parts = event.split(" ");
            
            if (parts[0].equals("ENTER")) {
                String name = parts[1];
                double cgpa = Double.parseDouble(parts[2]);
                int id = Integer.parseInt(parts[3]);
                
                pq.add(new Student(id, name, cgpa));
            } else if (parts[0].equals("SERVED")) {
                pq.poll();
            }
        }
        
        List<Student> students = new ArrayList<>();
        while (!pq.isEmpty()) {
            students.add(pq.poll());
        }
        
        return students;
    }
}
