import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.HashMap;

public class CourseEquivalency {
	String equivalencyFile; 
	Map<String, ArrayList<String>> courseEquivalencies;
	
	public CourseEquivalency(String fileName) {
		courseEquivalencies = new HashMap<>();
		equivalencyFile = fileName;
	}
	
	public boolean readFile() {
		boolean fileInputStatus = false;
		try {
			File file = new File(equivalencyFile);
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				
				ArrayList<String> equivalents = new ArrayList<String>(Arrays.asList(line.split("\t")));
				
				String key = equivalents.get(0);
				equivalents.remove(0);
				
				courseEquivalencies.put(key, equivalents);
				
				fileInputStatus = true;
			}
		}
		catch(FileNotFoundException e) {
			fileInputStatus = false;
		}
		return fileInputStatus;
	}	
	
	public ArrayList<String> getEquivalents(String keyIn) {
		ArrayList<String> toReturn;
		if(courseEquivalencies.containsKey(keyIn)) {
			toReturn = courseEquivalencies.get(keyIn);
		}
		else {
			toReturn = null;
		}
		return toReturn;
	}
	
	public boolean areEquivalent(String course1, String course2) {
		boolean equivalent = false;
		if(courseEquivalencies.containsKey(course1)) {
			for(int i=0;i<courseEquivalencies.get(course1).size();i++) {
				String j = courseEquivalencies.get(course1).get(i);
				if(j.equals(course2)) {
					equivalent = true;
				}
			}
		}
		else if(courseEquivalencies.containsKey(course2)) {
			for(int i=0;i<courseEquivalencies.get(course2).size();i++) {
				String course = courseEquivalencies.get(course2).get(i);
				if(course.equals(course1)) {
					equivalent = true;
				}
			}
		}
		return equivalent;
	}
	
	public boolean equivalentValues(String course1, String course2) {
		boolean equivalent = false;
		for(Map.Entry<String, ArrayList<String>> entry : courseEquivalencies.entrySet()) {
			boolean match = false;
			ArrayList<String> courses = entry.getValue();
			if(courses.contains(course1) && courses.contains(course2)) {
				equivalent = true;
				break;
			}
			/*
			for(int i=0;i<entry.getValue().size();i++) {
				if(entry.getValue().get(i) == course1 || entry.getValue().get(i) == course2) {
					if(match) {
						equivalent = true;
						break;
					}
					else {
						match = true;
					}
				}
			}
			*/
		}
		return equivalent;
	}
	
	public Map<String, ArrayList<String>> getMap(){
		return courseEquivalencies;
	}
	
	private ArrayList<String> getKeys(){
		ArrayList<String> toReturn = new ArrayList<String>();
		Set<String> keys = courseEquivalencies.keySet();
		toReturn.addAll(keys);
		return toReturn;
	}
	
	//returns the key for an equivalent course
	//if it cannot find the equivalent course in the map, then it returns null
	public String getCourseKey(String courseEquivalent) {
		ArrayList<String> keys = getKeys();
		for(String s : keys) {
			ArrayList<String> currentCourses = courseEquivalencies.get(s);
			if(currentCourses.contains(courseEquivalent)) {
				return s;
			}
		}
		return null;
	}
}
