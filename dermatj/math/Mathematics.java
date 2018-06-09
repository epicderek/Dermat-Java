package dermatj.math;


public abstract class Mathematics 
{

	public static double logBase(double base, double num)
	{
		if(num<=0)
		{
			return num;
		}
		return Math.log(num)/Math.log(base);
		
	}
	
//	public static double geometricSumO(double base, int pow)
//	{
//		double sum = 1;
//		if(pow>0)
//			for(int i=1; i<=pow; i++)
//				sum += Math.pow(base, i);
//		if(pow<0)
//			for(int i=-1; i>=pow; i--)
//				sum += Math.pow(base, i);
//		return sum;
//	}
	
	public abstract static class MathematicalException extends RuntimeException
	{
		/**
		 * Final serial id for this exception. 
		 */
		private static final long serialVersionUID = 1L;
		
		public MathematicalException()
		{
			super(); 
		}
		
		public MathematicalException(String mess)
		{
			super(mess); 
		}
		
		public MathematicalException(Exception cause)
		{
			super(cause); 
		}
		
	}
	
	public static void main(String[] args) 
	{
		
	}

}
