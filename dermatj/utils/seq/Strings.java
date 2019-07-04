package dermatj.utils.seq;

import static dermatj.utils.seq.Strings.Format.*; 
import dermatj.utils.seq.Array; 

import java.util.Random; 

public abstract class Strings 
{	
	
	public static final char[] LOWERCASE = {'q','w','e','r','t','y','u','i','o','p','a','s','d','f',
			'g','h','j','k','l','z','x','c','v','b','n','m'};
	
	public static final char[] UPPERCASE = {'Q','W','E','R','T','Y','U','I','O','P','A','S','D','F',
			'G','H','J','K','L','Z','X','C','V','B','N','M'}; 
	
	public static final char[] NUMS = {0,1,2,3,4,5,6,7,8,9}; 
	
	public static final char[] PUNCS = {' ',',','.','?','/',':',';','<','>','"','[',']','{','}','~',
			'`','!','@','#','$','%','^','&','*','(',')','_','-','+','=','\'','\\'}; 
	
	public static final char[] CHARS = new char[52]; 
	
	public static final char[] CHARNUMS = new char[62]; 
	
	public static final char[] ALL = new char[94]; 
	
	public static Format format = MIXED; 
	
	private static final StringBuilder BUILDER = new StringBuilder(); 
	
	private static final Random RAND = new Random(0);  
	
	static
	{
		//Incrementally construct character arrays of combinations of the fundamental characters given. 
		Array.addAll(CHARS,0,Array.mergeArray(UPPERCASE,LOWERCASE));
		Array.addAll(CHARNUMS,0,Array.mergeArray(NUMS,CHARS));
		Array.addAll(ALL,0,Array.mergeArray(CHARNUMS,PUNCS));
	}
	
	public static enum Format
	{
		UPPER,LOWER,NUM,CHAR,CHARNUM,MIXED
	}
	
	/**
	 * Generate a random 
	 * @param length
	 * @param form
	 * @return
	 */
	public static String random(int length, Format... form)
	{
		//The format this String is to be generated. 
		Format fo; 
		//Clear the builder for generation of new String. 
		BUILDER.setLength(0); 
		//If no format is specified, use default format for the class. 
		if(form.length==0) 
			fo = format;
		else
			fo = form[0]; 
		//Extract random characters of the group specified. 
		for(int i=0; i<length; i++)
			switch(fo)
			{
			case UPPER: BUILDER.append(UPPERCASE[RAND.nextInt(UPPERCASE.length)]); break; 
			case LOWER: BUILDER.append(LOWERCASE[RAND.nextInt(LOWERCASE.length)]); break;
			case NUM: BUILDER.append(NUMS[RAND.nextInt(NUMS.length)]); break;
			case CHAR: BUILDER.append(CHARS[RAND.nextInt(CHARS.length)]); break;
			case CHARNUM: BUILDER.append(CHARNUMS[RAND.nextInt(CHARNUMS.length)]); break;
			case MIXED: BUILDER.append(ALL[RAND.nextInt(ALL.length)]); break; 
			}
		return BUILDER.toString(); 
	}
	
	public static void main(String[] args) 
	{
		Array.print(random(10,Format.MIXED));
	}

}
