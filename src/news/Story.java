package news;

import java.util.Arrays;
//import java.util.Date;

public class Story {
	
	private String author;
	private String title;
	private String content;
	private String[] tags;
	//private Date date;
	private int timesRead;
	
	Story(String _author, String _title, String _content, String[] _tags)
	{
		this.author = _author;
		this.title = _title;
		this.content = _content;
		this.tags = _tags;
		this.timesRead = 0;
		// do: set date to current time, and getter for date
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String[] getTags() {
		return tags;
	}
	
	public void read() {
		this.timesRead++;
	}
	
	public int getTimesRead()
	{
		return timesRead;
	}
	
	public void printStory() {
		System.out.println("Author: " + author + ", Title: " + title + ", Views: " + timesRead);
		System.out.println("\n" + content + "\n");
		System.out.println(Arrays.toString(tags));
		System.out.println("-----------------------------------------------");
	}

	
	

}