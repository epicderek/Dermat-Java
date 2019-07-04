package dermatj.eval.time;

public abstract class Timer 
{
	private static long record1;
	private static long record2;
	
	public static void timerStart()
	{
		record1 = 0;
		record2 = 0;
		record1 = System.nanoTime();
	}
	
	public static long timerStop()
	{
		record2 = System.nanoTime();
		System.out.println("Record is "+(record2-record1));
		return record2-record1;
	}
	
}
