package news;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class News {
	
	private final Map<Integer, Story> stories = new HashMap<>();
	private final Map<String, List<Integer>> authors = new HashMap<>();
	private final List<Integer> mostRead = new ArrayList<Integer>();
	
	
	public int nextID() 
	{
		return stories.keySet().stream().mapToInt(n -> n).max().orElse(-1) + 1;
	}
	
	public void addStory(String author, String title, String text, String[] tags) {
		
		Story story = new Story(author, title, text, tags);
		
		int nextID = nextID();
		stories.put(nextID, story);
		
		//update authors map
		if (authors.get(author) != null)
		{
			List<Integer> s = authors.get(author);
			s.add(nextID);
			authors.replace(author, s);
		}else {
			authors.put(author, new ArrayList<Integer>(Arrays.asList(nextID)));
		}
		
	}
	
	public void markStoryAsRead(int storyID) 
	{
		if (stories.get(storyID) != null)
		{
			stories.get(storyID).read();
			
			if (mostRead.size() == 0)
			{
				mostRead.add(storyID);
				return;
			}
				
			// if it's already in the list
			if (mostRead.contains(storyID))
			{
				//remove it from the list
				mostRead.remove(mostRead.indexOf(storyID));
				
				// insert it maintaining the descending order of the number of reads
				for (int i = 0; i < mostRead.size(); i++)
				{
					if (stories.get(storyID).getTimesRead() > stories.get(mostRead.get(i)).getTimesRead())
					{
						mostRead.add(i, storyID); //the rest of the indices will be moved to the left
						break;
					}				
				}
				// if it wasn't inserted
				if (!mostRead.contains(storyID)) mostRead.add(storyID); // add to the end
			}
			else { // add it to the end
				mostRead.add(storyID);
			}			
				
		}
		else System.out.println("Story doesn't exist");

	}
	
	public void displayTopTenNews()
	{
		int i = 0;
		while (i < mostRead.size())
		{
			int index = mostRead.get(i);
			stories.get(index).printStory();	
			++i;
		}

		
	}
	
	public void displayStoriesForAuthor(String author)
	{
		List<Integer> s = authors.get(author);
		for (int index: s)
		{
			stories.get(index).printStory();
		}
		
	}
	
	public void displayStoriesForTags(String[] tags)
	{
		
	}
	
	public static void main(String[] args)
	{
		News news = new News();
		String author = "Malgorzata";
		String title = "Coding";
		String text = "ABC";
		String[] tags = {"happy", "sad"};
		
		news.addStory(author, title, text, tags);
		news.addStory("Joanna", "Painting", "DEF", new String[] {"art", "work"});

//		news.stories.get(0).printStory();
//		news.stories.get(1).printStory();
		
		news.markStoryAsRead(0);
		news.markStoryAsRead(1);
		news.markStoryAsRead(1);
		
		//news.displayTopTenNews();
		news.displayStoriesForAuthor("Joanna");
		
		
	}

}
