public class Mathagram
{
	public static void main(String[] args)
	{
		//Take input as xxx xxx xxx
		//representing xxx + xxx = xxx
		System.out.println(args[0] + " + " + args[1] + " = " + args[2]);
		String answer = mathagram(args[0],args[1],args[2], getUnused(args[0]+args[1]+args[2]));
		System.out.println("Answer: "+ answer.substring(0,3)+" + "+answer.substring(3,6)+" = "+answer.substring(6,9));
	}

	public static String mathagram(String xyz, String unused)
	{
		return mathagram(xyz.substring(0,3),xyz.substring(3,6),xyz.substring(6,9),unused);
	}
	public static String mathagram(String x, String y, String z, String unused)
	{
		//System.out.println(x+" "+y+" "+z + " // "+unused);
		String used = (x+y+z).replaceAll("x","");
		if(!valid(x,y,z)){return "";}
		if(solved(x,y,z)){return x+y+z;}

		String answer = "";
		for(char c : unused.toCharArray())
		{
			answer = mathagram((x+y+z).replaceFirst("x",""+c), (unused).replace(""+c,""));
			if(answer.length()>0){break;}
		}
		return answer;
	}

	public static boolean valid(String x, String y, String z)
	{
		//check no numbers are used more than once
		//check that known numbers are not PROVEN incorrect i.e. 3xx+4xx=6xx
		String equation = x+y+z;
		for(int i = 1;i<=9;i++)
		{
			if(equation.indexOf(""+i) != equation.lastIndexOf(""+i)){return false;}
		}
		char[] nums = (equation).toCharArray();
		int a = val(nums[0]);
		int b = val(nums[3]);
		int c = val(nums[6]);
		if(a>0 && b>0 && c>0)
		{
			//3xx+4xx=8xx is possible, 352 + 467 = 819
			if((a+b)>(c)){return false;}
			if((a+b)<(c-1)){return false;}
		}
		a = val(nums[1]);
		b = val(nums[4]);
		c = val(nums[7]);
		if(a>0 && b>0 && c>0)
		{
			if((a+b)%10>(c)){return false;}
			if((a+b)%10<(c-1)){return false;}
		}
		a = val(nums[2]);
		b = val(nums[5]);
		c = val(nums[8]);
		if(a>0 && b>0 && c>0)
		{
			if(((a+b)%10)!=c){return false;}
		}
		return true;
	}

	public static String getUnused(String equation)
	{
		String unused = "123456789";
		for(char c : equation.replaceAll("x","").toCharArray())
		{
			unused = unused.replace(""+c,"");
		}
		//System.out.println("Eq: "+equation + " Unused: "+unused);
		return unused;
	}

	public static boolean solved(String x, String y, String z)
	{
		if((x+y+z).contains("x")){return false;}
		if(Integer.parseInt(x)+Integer.parseInt(y)!=Integer.parseInt(z)){return false;}
		return true;
	}

	public static int val(char c)
	{
		switch (c)
		{
			case 'x': return -1;
			case '1': return 1;
			case '2': return 2;
			case '3': return 3;
			case '4': return 4;
			case '5': return 5;
			case '6': return 6;
			case '7': return 7;
			case '8': return 8;
			case '9': return 9;
			default: return -1;
		}
	}
}