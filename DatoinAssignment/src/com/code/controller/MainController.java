package com.code.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.code.rainfall.Rainfall;

public class MainController {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Rainfall> rdet=new ArrayList<Rainfall>();
		rdet=rainfallDetailsLoad();
		avgAnnualRainfallCk(rdet);
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("2 . All the subdivisions of KARNATAKA state in descending order of their average annual rainfall.\n");
		descKarnaAvgRain(rdet);
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("3 . TOP 3 months on which KERALA state usually gets higher rainfall than other months.\n");
		keralatopthree(rdet);
		

	}

	

	private static List<Rainfall> rainfallDetailsLoad() {
		String delcsvFile = "C:\\Users\\1304041\\Downloads\\assignment-1 (1)\\assignment-1\\data\\rainfall_india_1901-2015.csv";
        String cvsSplitBy = ",";
        BufferedReader br = null;
        String[] details = null;
        List<Rainfall> rd=new ArrayList<Rainfall>();
        try {
        	br = new BufferedReader(new FileReader(delcsvFile));
        	String line = "";
        	br.readLine();
        	 while ((line = br.readLine()) != null) 
             {
                 details = line.split(cvsSplitBy);
                 rd=createDetailsObject(details,rd);
                 
        }
        	 

}
        catch(Exception e){
        	e.printStackTrace();
        }
		return rd;
	}

	private static List<Rainfall> createDetailsObject(String[] details, List<Rainfall> rd) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<details.length;i++) {
			if(details[i].equalsIgnoreCase("NA")) {
				details[i]=String.valueOf(0);
			}
		}
		rd.add(new Rainfall(details[0], details[1], details[2], details[3], details[4], details[5], details[6], details[7], details[8], details[9], details[10], details[11], details[12], details[13], Double.parseDouble(details[14])));
		
		
		return rd;
		
	}
	
	private static void avgAnnualRainfallCk(List<Rainfall> rdet) {
		// TODO Auto-generated method stub
		double sum=0;
		double avg;
		int i=1;
		for(Rainfall rf:rdet) {
			if(rf.getSubdiv().equalsIgnoreCase("COASTAL KARNATAKA")) {
				sum=sum+rf.getAnnual();
				i++;
			}
		}
		System.out.println("1 . The average annual rainfall of 'COASTAL KARNATAKA' subdivision : "+(sum/i));
	}
	
	
	private static void descKarnaAvgRain(List<Rainfall> rdet) {
		// TODO Auto-generated method stub
		Map<String,Double> mainMap=new TreeMap<String,Double>();
		Map<String,Integer> occMap=new TreeMap<String,Integer>();
		
		Map<String,Double> data=new TreeMap<String,Double>();
		
		
		int i=0;
		for(Rainfall rf:rdet) {
			if(rf.getSubdiv().contains("KARNATAKA")) {
				if(mainMap.containsKey(rf.getSubdiv())) {
					double val=mainMap.get(rf.getSubdiv());
					val=val+rf.getAnnual();
					i=occMap.get(rf.getSubdiv())+1;
					mainMap.replace(rf.getSubdiv(), val);
					occMap.replace(rf.getSubdiv(), i);
					
				}
				else {
					mainMap.put(rf.getSubdiv(), rf.getAnnual());
					occMap.put(rf.getSubdiv(), 1);
				}
			}
		}
		
		for (Map.Entry<String, Double> main : mainMap.entrySet()) {
			for(Map.Entry<String, Integer> occ:occMap.entrySet()) {
				if(main.getKey().equalsIgnoreCase(occ.getKey())) {
					double value=main.getValue()/occ.getValue();
					data.put(occ.getKey(), value);
				}
			}
		    
		}
		
		Set<Entry<String,Double>> dataSet = data.entrySet();
		List<Entry<String,Double>> list = new LinkedList<Entry<String,Double>>(dataSet);
		
		Collections.sort( list, new Comparator<Map.Entry<String, Double>>()
        {
            public int compare( Map.Entry<String, Double> o1, Map.Entry<String, Double> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
		
		for (Map.Entry<String, Double> d : list) {
			System.out.println(d.getKey()+" = "+d.getValue());
		}
	}
	
	private static void keralatopthree(List<Rainfall> rdet) {
		// TODO Auto-generated method stub
		Map<String,Double> map=new TreeMap<String,Double>();
		double sumJan=0;
		double sumFeb=0;
		double sumMarch=0;
		double sumApr=0;
		double sumMay=0;
		double sumJune=0;
		double sumJuly=0;
		double sumAug=0;
		double sumSep=0;
		double sumOct=0;
		double sumNov=0;
		double sumDec=0;
		for(Rainfall rf :rdet) {
			sumJan=sumJan+Double.parseDouble(rf.getJan());
			sumFeb=sumFeb+Double.parseDouble(rf.getFeb());
			sumMarch=sumMarch+Double.parseDouble(rf.getMarch());
			sumApr=sumApr+Double.parseDouble(rf.getApril());
			sumMay=sumMay+Double.parseDouble(rf.getMay());
			sumJune=sumJune+Double.parseDouble(rf.getJune());
			sumJuly=sumJuly+Double.parseDouble(rf.getJul());
			sumAug=sumAug+Double.parseDouble(rf.getAug());
			sumSep=sumSep+Double.parseDouble(rf.getSep());
			sumOct=sumOct+Double.parseDouble(rf.getOct());
			sumNov=sumNov+Double.parseDouble(rf.getNov());
			sumDec=sumDec+Double.parseDouble(rf.getDec());
			}
		
		map.put("jan", sumJan);
		map.put("feb", sumFeb);
		map.put("march", sumMarch);
		map.put("apr", sumApr);
		map.put("may", sumMay);
		map.put("june", sumJune);
		map.put("july", sumJuly);
		map.put("aug", sumAug);
		map.put("sep", sumSep);
		map.put("oct", sumOct);
		map.put("nov", sumNov);
		map.put("dec", sumDec);
		
		List<Entry<String,Double>> list=new ArrayList<Entry<String,Double>>(map.entrySet());
		
		Collections.sort( list, new Comparator<Map.Entry<String, Double>>()
        {
            public int compare( Map.Entry<String, Double> o1, Map.Entry<String, Double> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
		
		int i=1;
		for (Map.Entry<String, Double> d : list) {
			System.out.println(d.getKey()+" = "+d.getValue());
			if(++i>3) {
				break;
			}
		}
		
		
	}
	}
