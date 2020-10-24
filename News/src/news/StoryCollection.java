package news;

import java.util.HashMap;
import java.util.Map;

public class StoryCollection {
	/*
	 * This is the class that stores all of the news stories
	 */
	
	private final Map<Integer, Story> storiesMap;
	
	public StoryCollection(){
		this.storiesMap = new HashMap<>();
	}
	
	int nextID() 
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
	
	

}
