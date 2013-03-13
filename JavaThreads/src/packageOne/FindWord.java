package packageOne;

import java.util.*;
import java.io.*;

public class FindWord {
	public static void main(String[] args)
	{
		int threadCount=0;
		try {
			System.out.println("Please enter how many threads you want \n" +
					"to work on this problem.");
			Scanner inputNum = new Scanner(System.in);
			threadCount = inputNum.nextInt();
			long length = new File("ThreadWiki.txt").length();
			
			List<RandomAccessFile> filePool = new LinkedList<RandomAccessFile>();
			
			List<Thread> threadPool = new LinkedList<Thread>();
			
			for (int i=0; i!=threadCount; ++i) {
				long start,end;
				RandomAccessFile tempFile = new RandomAccessFile("ThreadWiki.txt","rw");
				filePool.add(tempFile);
				if (i==threadCount-1) {
					end = length;
				} else end = (i+1) * (length/threadCount) + 1; // in this case "to"
				start = i * (length/threadCount);
				Thread temp = new Thread(new SearchThread(start, end, filePool.get(i)));
				temp.start();
				threadPool.add(temp);				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
