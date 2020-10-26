package news;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StoryCollection {
	/*
	 * This is the class that stores all of the news stories
	 */
	
	private final Map<Integer, Story> storiesMap;
	
	StoryCollection(){
		this.storiesMap = new HashMap<>();
	}
	
	private int nextID() 
	{
		return storiesMap.keySet().stream().mapToInt(n -> n).max().orElse(-1) + 1;
	}
	
	int addStory(Story story) {
		
		int nextID = nextID();
		storiesMap.put(nextID, story);
		return nextID;
		
	}
	
	Story getStory(int storyID)
	{
		return storiesMap.get(storyID);
	}
	
	Set<Integer> getKeySet()
	{
		return storiesMap.keySet();
	}
	
	
	
	

}
