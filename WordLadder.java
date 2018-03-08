import java.util.*;
import java.io.InputStreamReader;  
import java.io.BufferedReader;    
import java.io.FileInputStream;
import java.io.FileNotFoundException;

  
public class WordLadder {  
 
    public static void main(String[] args) throws FileNotFoundException {  
    	Scanner sc = new Scanner(System.in);
    	System.out.print("Input the path of the dictionary: ");
    	String fileName = sc.nextLine();
    	System.out.print("Input the first word: ");
    	String start = sc.nextLine();
    	System.out.print("Input the last word: ");
    	String end = sc.nextLine();
    	try 
    	{
    		HashSet<String> dict = build(fileName);
    		if (dict.contains(start) && dict.contains(end))
            {
            	ladder(start,end,dict);
            }
            else
            {
            	System.out.print("These word doesn't exists in the dictionary. \n");
            }
            sc.close();
    	}
    	catch (Exception e)
    	{
    		System.out.print("No such dictionary. \n");
    	}
        
    }  
  
    public static HashSet<String> build(String fileName) throws FileNotFoundException{
    	HashSet<String> dict = new HashSet<String>();
    	InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName));
        try {   
            BufferedReader br = new BufferedReader(reader); 
            String line = null;   
            
            while ((line = br.readLine()) != null) {  
                dict.add(line);
            }  
            reader.close();  
        } 
        catch (Exception e) {  
        	System.out.print("No such dictionary. \n");
        } 
    	return dict;
    }
    
    public static void view (String start, String end,Stack<String> stk)
    {
    	Iterator<String> it = stk.iterator();  
    	if( stk.size() <= 1)
    	{
    		System.out.print("There is not such a word ladder. \n");
    	}
    	System.out.print("A word ladder from " + start + " to " + end + " is : " );
        while(it.hasNext())  
        {  
            Object obj = it.next();  
            System.out.print(obj);
            System.out.print(" ");
        }
        System.out.print("\n");
    }
    public static void ladder(String start, String end, HashSet<String> dict) {
    	Stack<String> answer = new Stack<String>();
    	Queue<Stack<String>> Q = new LinkedList<Stack<String>>();
    	HashSet<String> map = dict;
    	map.remove(start);
    	Stack<String> head = new Stack<String>();
    	head.push(start);  	
    	Q.add(head);
    	boolean done = false;
    	while ((Q.size() != 0) && !done)
    	{
    		Stack<String> temp = Q.poll();
    		HashSet<String> neighbour = new HashSet<String>();
    		String temp2 = temp.peek();
    		int len = temp2.length();
    		for (int i = 0; i < len; i++)
    		{
    			for (char c ='a';c<= 'z';c++)
    			{
    				char[] temp3 = temp2.toCharArray(); 
    				temp3[i] = c;
    				
    	    		String newWord = new String(temp3);
    				if (dict.contains(newWord) && (!newWord.equals(temp2)))
    				{
    					neighbour.add(newWord);
    				}
    			}
    		}
    		for (String tempword : neighbour)
    		{
    			if (map.contains(tempword))
    			{
    				if (tempword.equals(end))
    				{
    					temp.push(end);
    					answer = temp;
    					done = true;
    					
    				}
    				else
    				{
    					Stack<String> copy = new Stack<String>();
    					for (String s : temp)
    					{
    						copy.push(s);
    					}
    					copy.push(tempword);
    					Q.add(copy);
    				}
    				map.remove(tempword);
    			}
    		}	
    	}
    	view(start,end,answer);
    }
    
}  
