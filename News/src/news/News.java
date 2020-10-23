package news;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class News {
	
	// maps storyIDs to Story objects
	private final Map<Integer, Story> storiesMap = new HashMap<>();
	
	// maps authors to lists of storyIDs ( List<storyID> )
	private final Map<String, List<Integer>> authorsMap = new HashMap<>();
	
	// maps tags to lists of storyIDs 
	private final Map<String, List<Integer>> tagsMap = new HashMap<>();
	
	// lists storyIDs in a descending order of number of views
	private final List<Integer> mostRead = new ArrayList<Integer>();
	
	
	public int nextID() 
	{
		return storiesMap.keySet().stream().mapToInt(n -> n).max().orElse(-1) + 1;
	}
	
	public void addStory(String author, String title, String text, String[] tags) {
		
		Story story = new Story(author, title, text, tags);
		
		int nextID = nextID();
		storiesMap.put(nextID, story);
		
		// update authors map
		if (authorsMap.get(author) != null)
		{
			List<Integer> s = authorsMap.get(author);
			s.add(nextID);
			
		}else {
			authorsMap.put(author, new ArrayList<Integer>(Arrays.asList(nextID)));
		}
		
		// update tags map
		for (String tag: tags)
		{
			if (tagsMap.get(tag) != null)
			{
				List<Integer> s = tagsMap.get(tag);
				s.add(nextID);
				
			}else {
				tagsMap.put(tag, new ArrayList<Integer>(Arrays.asList(nextID)));
			}			
		}

		
	}
	
	public void markStoryAsRead(int storyID) 
	{
		if (storiesMap.get(storyID) != null)
		{
			storiesMap.get(storyID).read();
			
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
					if (storiesMap.get(storyID).getTimesRead() > storiesMap.get(mostRead.get(i)).getTimesRead())
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
		System.out.println("TOP 10 NEWS" + "\n");
		int i = 0;
		while (i < mostRead.size())
		{
			int index = mostRead.get(i);
			storiesMap.get(index).printStory();	
			++i;
		}

		
	}
	
	public void displayStoriesForAuthor(String author)
	{
		List<Integer> s = authorsMap.get(author);
		for (int index: s)
		{
			storiesMap.get(index).printStory();
		}
		
	}
	
	public void displayStoriesForTags(String[] tags)
	{
		// key = storyID, value = num of matches
		Map<Integer, Integer> matches = new HashMap<>();
		
		for (String tag: tags)
		{
			for (int i: tagsMap.get(tag))
			{
				if (matches.get(i) == null)
				{
					matches.put(i, 1);
				}
				else
				{
					int f = matches.get(i);
					matches.replace(i, f+1);
				}
			}
		}
		
		// storyIDs
		List<Integer> stories = new ArrayList<>();
		for (Map.Entry<Integer, Integer> entry : matches.entrySet()) {
		    if (!stories.contains(entry.getKey()))
		    {
		    	stories.add(entry.getKey());
		    }
		    else
		    {
		    	stories.remove(stories.indexOf(entry.getKey()));
		    	for (int i = 0; i < stories.size(); i++)
		    	{
		    		if (entry.getValue() > matches.get(stories.get(i)))
		    		{
		    			stories.add(i, entry.getKey());
		    			break;
		    		}
		    	}
		    	if (!stories.contains(entry.getKey()))
		    	{
		    		stories.add(entry.getKey());
		    	}
		    }
		}
		
		// print stories
		
		for (int i: stories)
		{
			storiesMap.get(i).printStory();
		}
		

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
		//news.displayStoriesForAuthor("Joanna");
		news.displayStoriesForTags(new String[] {"sad"});
		
		
	}

}
