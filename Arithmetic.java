import java.util.Scanner;

public class Arithmetic
{
	public static long gcd(long x, long y)
	{
		if(y == 0)	
			return x;
		else
			return gcd(y, x % y);
	}
	
	public static void main (String []args)
	{
		Scanner scn = new Scanner(System.in);
		while(scn.hasNext())
		{
			String str = scn.next();
			boolean flag = true;
			char ope[] = new char[str.length()];
			long num[][] = new long[str.length()][2];
			int j = 0;
			for(int i = 0 ; i < str.length() ; i ++)
			{
				if(str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*' || str.charAt(i) == '/')
				{
					ope[j] = str.charAt(i);
					j++;
				}
			}
			
			if(j % 2 == 0)
				flag = false;
			
			for(int i = 0 ; i < j ; i ++)
			{
				if(i % 2 == 0)
					if(ope[i] != '/')
					{
						flag = false;
						break;
					}
			}
			
	
			
			if(flag == true)
			{
				//int result[][] = new int[1][2];
				boolean flagg = false;
				//str.split("\\+|-|\\*|/");
				str = str.replace("+", " ");
				str = str.replace("-", " ");
				str = str.replace("*", " ");
				str = str.replace("/", " ");
				//System.out.println(str);
				//int index = 0;
				int k = 0;
				long numb = 0;
				for(int i = 0 ; i < str.length() ; i ++)
				{
					//index = 0;
	
					if(str.charAt(i) == ' ')
					{
						if(flagg == false) //kid
						{
							num[k][0] = numb;
							flagg = true;
							//System.out.println(numb);
							numb = 0;
						}
						else//mother
						{
							num[k][1] = numb;
							flagg = false;
							//System.out.println(numb);
							numb = 0;
							k++;
						}
					}
					else
						numb = (str.charAt(i) - '0') + numb * 10;
					if(i == str.length() - 1)
					{
						num[k][1] = numb;
						//System.out.println(numb);
						k++;
					}
				}
				for(int i = 0 ; i < k ; i ++)
				{
					if(num[i][1] == 0)	//check mother
					{	
						flag = false;
						break;
					}
				}
				k = 0;
				char oper1[] = new char [j/2];
				for(int i = 0 ; i < j ; i ++)
				{
					if(i % 2 == 0)
						continue;
					else
					{
						oper1[k++] = ope[i];
					}
				}
				
				//System.out.println("k=" + k);//HOW MANY OPERATORS
				//int compute[][] = new int [2][2];
				long top = 0, bottom = 0;
					int i = 0;
					for(i = 0 ; i < k ; i ++)
					{
						if(oper1[i] == '+' || oper1[i] == '-')
							continue;
						else //*/
						{
							if(oper1[i] == '*')
							{
								top = num[i][0] * num[i+1][0];
								bottom = num[i][1] * num[i+1][1];
						//	System.out.println( num[i][0] + "/"+ num[i][1] + "* " + num[i+1][0] +"/" + num[i+1][1]);
							}
							else if(oper1[i] == '/')
							{
								top = num[i][0] * num[i+1][1];
								bottom = num[i][1] * num[i+1][0];
							}
							long temp = gcd(top, bottom);
							top /= temp;
							bottom /= temp;
							num[i][0] = top;
							num[i][1] = bottom;
							//System.out.println(top + " " + bottom);
							///////
							//delete i+1
							//boolean arrive = false;
							for(int l = 0 ; l < k ; l ++)
							{
								if(l > i)
								{
									num[l][0] = num[l+1][0];
									num[l][1] = num[l+1][1];
								}
							
							}
		
							for(int l = 0 ; l < k ; l ++)
							{
								if(l > i)
									oper1[l-1] = oper1[l];
							}
						/*	for(int l = 0 ; l < k ; l ++)
							{
								System.out.println(num[l][0] +"/" + num[l][1]);
							}
							System.out.println(oper1);*/
							k--;
							i = -1;
						}
				}
				/////
				for(i = 0 ; i < k ; i ++)///////+-
				{
					if(oper1[i] == '+')
					{
						bottom = num[i][1] * num[i+1][1];
						//System.out.println("bottom=" + bottom);
						top = num[i][0] * num[i+1][1] + num[i][1] * num[i+1][0];
						//System.out.println("top=" + top);
						long temp = gcd(top, bottom);
						top /= temp;
						bottom /= temp;
						//System.out.println(top + "/" + bottom);
					}
						
					else if(oper1[i] == '-')
					{
						bottom = num[i][1]* num[i+1][1];
						top = num[i][0] * num[i+1][1] - num[i][1] * num[i+1][0];
						long temp = gcd(top, bottom);
						top /= temp;
						bottom /= temp;
					}
					num[i][0] = top;
					num[i][1] = bottom;
					for(int l = 0 ; l < k ; l ++)
					{
						if(l > i)
						{
							num[l][0] = num[l+1][0];
							num[l][1] = num[l+1][1];
						}
					}
	
					for(int l = 0 ; l < k ; l ++)
					{
						if(l > i)
							oper1[l-1] = oper1[l];
					}
				/*	for(int l = 0 ; l < k ; l ++)
					{
						System.out.println(num[l][0] +"/" + num[l][1]);
					}*/
					//System.out.println(oper1);
					k--;
					i = -1;
					
				}
				
				long temp = gcd(num[0][0], num[0][1]);
				num[0][0] /= temp;
				num[0][1] /= temp;
				System.out.println(num[0][0] + "/" + num[0][1]);
			}
	
			if(flag == false)
			{
				System.out.println("-1");
			}
		}
	}
}