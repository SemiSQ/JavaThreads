package packageOne;

import java.io.*;

public class SearchThread implements Runnable {
	
	private final int BUFF_LEN = 256;
	
	private long start,end;
	private int sum = 0;
	private RandomAccessFile raf;
	
	public SearchThread(long start, long end, RandomAccessFile raf)
	{
		this.start = start;
		this.end = end;
		this.raf = raf;
	}
	
	public void run()
	{
		long time = System.currentTimeMillis();
		
		long times = (end - start) / BUFF_LEN;
		byte[] buff = new byte[BUFF_LEN];
		int readIn;
		String text = null;
		try {
			raf.seek(start);
			for (int i=0; i!=times; ++i) {
				readIn = raf.read(buff);
				if (readIn < 0) break;
				text = new String(buff);
				int count = text.split("to").length - 1;
				sum += count;
			}		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		time = System.currentTimeMillis() - time;
		System.out.println("Thread complete in:"+time+"\nHaving found uptill now:"+sum);
	}
}
