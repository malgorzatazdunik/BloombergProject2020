package news;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class News {
	
	private final StoryCollection stories = new StoryCollection();
	
	private final Map<String, List<Integer>> authorsMap = new HashMap<>();
	
	private final Map<String, List<Integer>> tagsMap = new HashMap<>();
	
	private final PriorityQueue<Integer> mostRead = new PriorityQueue<>(new Comparator<Integer>() {

		@Override
		public int compare(Integer i1, Integer i2) {
			// TODO Auto-generated method stub
			if (stories.getStory(i1).getTimesRead() > stories.getStory(i2).getTimesRead())
			{
				return -1;
			}
			else 
			{
				return 1;
			}
		}
		
	});
	
	
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
			if (!mostRead.contains(storyID)) mostRead.add(storyID);
			else {
				mostRead.remove(storyID);
				mostRead.add(storyID);
			}
		}

	}
	
	public void displayTopTenNews()
	{
		System.out.println("****************************** TOP 10 NEWS ******************************" + "\n");
		
		List<Integer> helperList = new ArrayList<>();
		
		int i = 0;
		while (i < 10 && !mostRead.isEmpty())
		{
			int head = mostRead.poll();
			stories.getStory(head).printStory();
			helperList.add(head);
			++i;
		}
		
		// add elements back to the priority queue
		for (int j: helperList) mostRead.add(j);
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
		
		PriorityQueue<Integer> matchedStories = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer i1, Integer i2) {
				// TODO Auto-generated method stub
				if (matches.get(i1) > matches.get(i2)) return -1;
				else return 1;
			}
			
		});
		
		matchedStories.addAll(matches.keySet());
			
		
		/*
		 * Print the list (the most matched stories in the beginning)
		 */
		while (!matchedStories.isEmpty())
		{
			int head = matchedStories.poll();
			stories.getStory(head).printStory();
		}
		
		

	}
	

}

