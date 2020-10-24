package news;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class News {
	
	private final StoryCollection stories = new StoryCollection();
	
	private final Map<String, List<Integer>> authorsMap = new HashMap<>();
	
	private final Map<String, List<Integer>> tagsMap = new HashMap<>();
	
	
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
		}

	}
	
	public void displayTopTenNews()
	{
		System.out.println("****************************** TOP 10 NEWS ******************************" + "\n");
		
		/*
		 * Create a list from keySet of stories, and sort it by corresponding numbers of times read from story object
		 */
		List<Integer> mostRead = new ArrayList<Integer>(stories.getKeySet());
		
		Collections.sort(mostRead, new Comparator<Integer>() {
			public int compare(Integer left, Integer right)
			{
				int compare = stories.getStory(left).getTimesRead() - stories.getStory(right).getTimesRead();
				if (compare == 0) return left.compareTo(right);
				return compare;
			}
		});
		
		/*
		 * print the last ten stories (that were read the most often)
		 */
		int i = mostRead.size()-1;
		while (i >= 0)
		{
			int index = mostRead.get(i);
			stories.getStory(index).printStory();	
			--i;
			if (i == mostRead.size()-11) break;
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
		 * Add all story Ids to the list, and sort it by corresponding values from the matches map (number of tags matched)
		 */
		
		List<Integer> matchedStoriesList = new ArrayList<Integer>(matches.keySet());
		
		Collections.sort(matchedStoriesList, new Comparator<Integer>() {
			public int compare(Integer left, Integer right)
			{
				int compare = matches.get(left) - matches.get(right);
				if (compare == 0) return left.compareTo(right);
				return compare;
			}
		});
			
		
		/*
		 * Print the list in reverse (the most matched stories in the beginning
		 */
		
		for (int i = matchedStoriesList.size()-1; i >= 0; i--)
		{
			stories.getStory(matchedStoriesList.get(i)).printStory();
		}
		

	}
	

}

