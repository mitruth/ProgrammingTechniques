package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.MonitoredData;

public class DataManipulation {

	public List<MonitoredData> readInput() throws IOException{
		List<MonitoredData> monitoredData = new ArrayList<MonitoredData>();
		
		String fName = new File("DailyActivities.txt").getAbsolutePath();
		String line = null;
		
		FileReader fileReader = new FileReader(fName);
		
		BufferedReader buffReader = new BufferedReader(fileReader);
		
		line = buffReader.readLine();
		while(line != null) {
			String[] s;
			s = line.split("\\s+");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss");
			
			String activityLabel = s[4];
			
			String[] startTime1 = { s[0], s[1] };
			String startTime2 = String.join(" ", startTime1);
			LocalDateTime startTime = LocalDateTime.parse(startTime2, formatter);
			
			String[] endTime1 = { s[2], s[3] };
			String endTime2 = String.join(" ", endTime1);
			LocalDateTime endTime = LocalDateTime.parse(endTime2, formatter);
			
			MonitoredData mD = new MonitoredData(activityLabel, startTime, endTime);
			monitoredData.add(mD);
		}
		
		buffReader.close();
		return monitoredData;
	}
	
	public int countDistinctDays(List<MonitoredData> monitoredData) {
		
		Set<Integer> days = new TreeSet<Integer>();
		
		days = monitoredData.stream().map(temp -> temp.getStartTime().getDayOfMonth()).collect(Collectors.toSet());
		
		return days.size();
	}
	
	public int count(String activity, List<MonitoredData> monitoredData) {
		int nr = 0;
		for(int i = 0; i < monitoredData.size(); i++) {
			if(monitoredData.get(i).getActivityLabel() == activity) {
				nr++;
			}
		}
		return nr;
	}
	
	public void ActivityOccurances(List<MonitoredData> monitoredData) throws IOException {
		List<String> activities = new ArrayList<String>();
		
		String file = new File("DailyActivities.txt").getAbsolutePath();
		
		try (Stream <String> stream = Files.lines(Paths.get(file))){
			activities = monitoredData.stream().map(temp -> temp.getActivityLabel()).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Map<String, Long> activityOccurance = activities.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		try {
			FileOutputStream fName = new FileOutputStream("ActivityOccurances.txt");
			
			OutputStreamWriter output = new OutputStreamWriter(fName);
			
			Writer writer = new BufferedWriter(output);
			
			for(String a : activityOccurance.keySet()) {
				Long nr = activityOccurance.get(a);
				writer.write(a + " :" + nr);
				((BufferedWriter) writer).newLine();
			}
			
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private long getTime(MonitoredData mD) {
		return mD.getStartTime().until(mD.getEndTime(), ChronoUnit.HOURS);
	}

	public void activityTotalDuration(List<MonitoredData> monitoredData) throws IOException {
		Map <String, Long> activityMap = monitoredData.stream().collect(Collectors.groupingBy(MonitoredData::getActivityLabel, Collectors.summingLong(temp -> getTime(temp))));
		
		activityMap = activityMap.entrySet().stream().filter(temp -> temp.getValue() > 10).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
		
		try {
			FileOutputStream fName = new FileOutputStream("totalDuration.txt");
			
			OutputStreamWriter output = new OutputStreamWriter(fName);
			
			Writer writer = new BufferedWriter(output);
			
			for(String s : activityMap.keySet()) {
				Long nr = activityMap.get(s);
				
				writer.write(s + " :" + nr + " h");
				((BufferedWriter) writer).newLine();
			}
			
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void activityFilter(List<MonitoredData> monitoredData) throws IOException {
		
		Map <String, Long> activity = monitoredData.stream().filter(temp -> getTime(temp) < 1 / 12).collect(Collectors.groupingBy(MonitoredData::getActivityLabel, Collectors.counting()));
		
		activity = activity.entrySet().stream().filter(temp -> (double)(temp.getValue() / (count(temp.getKey(), monitoredData))) > 0.9).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
		
		try {
			FileOutputStream fName = new FileOutputStream("filter.txt");
			
			OutputStreamWriter output = new OutputStreamWriter(fName);
			
			Writer writer = new BufferedWriter(output);
			
			List<String> filteredList = activity.entrySet().stream().map(temp -> temp.getKey()).collect(Collectors.toList());
			
			int i = 0;
			while(i < filteredList.size()) {
				writer.write(filteredList.get(i));
				((BufferedWriter) writer).newLine();
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
