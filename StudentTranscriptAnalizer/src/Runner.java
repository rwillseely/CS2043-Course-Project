import java.util.ArrayList;

public class Runner {

	public static void main(String[] args) {
		
		University uni = new University();
		
		uni.readInputFile("./files/SWECourseData.txt");
		
		DistributionSchema schema = new DistributionSchema();
		
		Distribution dis = new RawDistribution(schema,null,uni);
		
		ArrayList<String> raw = dis.getGrades();
		
		dis.binGrade(raw);
		
		System.out.println("Exceeds\tMeets\tMarginal\tFails\tOthers");
		System.out.print(dis.getBinCount("Exceeds") + "\t");
		System.out.print(dis.getBinCount("Meets")+ "\t");
		System.out.print(dis.getBinCount("Marginal")+ "\t");
		System.out.print(dis.getBinCount("Fails")+ "\t");
		System.out.print(dis.getBinCount("Others")+ "\t");
		System.out.print("Repeats: \t");
		System.out.print(dis.getBinCount("RExceeds") + "\t");
		System.out.print(dis.getBinCount("RMeets")+ "\t");
		System.out.print(dis.getBinCount("RMarginal")+ "\t");
		System.out.print(dis.getBinCount("RFails")+ "\t");
		System.out.print(dis.getBinCount("ROthers")+ "\t");
		
		System.out.println("\n");
		
		ArrayList<String> courseList = new ArrayList<String>();
		
		courseList.add("CS*1003");
		courseList.add("ENGG*1001");
		courseList.add("ENGG*1003");
		courseList.add("CCOM*7037");
		courseList.add("CMUS*4020");
		courseList.add("MATH*1503");
		courseList.add("CHEM*2401");
		courseList.add("CHEM*2416");	
		courseList.add("CS*1083");
		courseList.add("APSC*2023");
		courseList.add("APSC*2028");
		
		dis = new AreaDistribution(schema,courseList,uni);
		
		ArrayList<String> area = dis.getGrades();
		
		dis.binGrade(area);
		
		System.out.println("Exceeds\tMeets\tMarginal\tFails\tOthers");
		System.out.print(dis.getBinCount("Exceeds") + "\t");
		System.out.print(dis.getBinCount("Meets")+ "\t");
		System.out.print(dis.getBinCount("Marginal")+ "\t");
		System.out.print(dis.getBinCount("Fails")+ "\t");
		System.out.print(dis.getBinCount("Others")+ "\t");
		
		System.out.println("\n");
		
		System.out.print(uni.getTranscript(5510254));

	}

}
