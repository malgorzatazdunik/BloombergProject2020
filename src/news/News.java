package news;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class News {
	
	private final StoryCollection stories = new StoryCollection();
	
	private final Map<String, List<Integer>> authorsMap = new HashMap<>();
	
	private final Map<String, List<Integer>> tagsMap = new HashMap<>();
	
	private final List<Integer> mostRead = new ArrayList<Integer>();
	
	
	public void addStory(String author, String title, String text, String[] tags) {
		
		Story story = new Story(author, title, text, tags);
		int storyID = stories.addStory(story);
		
		
		/*
		 * Add to or update authors Map
		 */
		if (authorsMap.get(author) == null)
		{
			authorsMap.put(author, new ArrayList<Integer>(Arrays.asList(storyID)));
		}else 
		{
			List<Integer> s = authorsMap.get(author);
			s.add(storyID);
		}
		
		/*
		 * Add to or update tags Map
		 */
		for (String tag: tags)
		{
			if (tagsMap.get(tag) == null)
			{
				tagsMap.put(tag, new ArrayList<Integer>(Arrays.asList(storyID)));
			}else 
			{
				List<Integer> s = tagsMap.get(tag);
				s.add(storyID);
			}			
		}

		
	}
	
	public void markStoryAsRead(int storyID) 
	{
		if (stories.getStory(storyID) != null)
		{
			stories.getStory(storyID).read();
			
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
					if (stories.getStory(storyID).getTimesRead() > stories.getStory(mostRead.get(i)).getTimesRead())
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
		System.out.println("****************************** TOP 10 NEWS ******************************" + "\n");
		int i = 0;
		while (i < mostRead.size())
		{
			int index = mostRead.get(i);
			stories.getStory(index).printStory();	
			++i;
			if (i == 10) break;
		}
	}
	
	public void displayStoriesForAuthor(String author)
	{
		System.out.println("************************** STORIES BY AUTHOR: " + author + " **************************\n");
		List<Integer> s = authorsMap.get(author);
		for (int index: s)
		{
			stories.getStory(index).printStory();
		}
		
	}
	
	public void displayStoriesForTags(String[] tags)
	{
		System.out.println("****************** STORIES WITH TAGS: " + Arrays.toString(tags)+" ******************\n");
		// key = storyID, value = num of matches
		Map<Integer, Integer> matches = new HashMap<>();
		List<Integer> matchedStoriesList = new ArrayList<>();
		
		/*
		 * for each tag, check the map of tags for ids of stories; Every time the story is already in the map of matches, 
		 * add to the number of matches
		 */
		
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
		
		/*
		 * Create a new list of stories and iterate the entry set to add stories to the list in descending order of matches 
		 */
		
		for (Map.Entry<Integer, Integer> entry : matches.entrySet())
		{
			if (matchedStoriesList.size() == 0) matchedStoriesList.add(entry.getKey());
			else {
				for (int i = 0; i < matchedStoriesList.size(); i++)
				{
					if (entry.getValue() > matches.get(matchedStoriesList.get(i)))
					{
						matchedStoriesList.add(i, entry.getKey());
						break;
					}
					if(!matchedStoriesList.contains(entry.getKey())) matchedStoriesList.add(entry.getKey()); // add to the end;
				}
			}
		}
		
		// print stories
		
		for (int i: matchedStoriesList)
		{
			stories.getStory(i).printStory();
		}
		

	}
	

}

