package news;

import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Story {
	
	private String author, title, content, date;
	private String[] tags;
	private int timesRead;
	
	Story(String _author, String _title, String _content, String[] _tags)
	{
		this.author = _author;
		this.title = _title;
		this.content = _content;
		this.tags = _tags;
		this.timesRead = 0;
		
		Date date = new Date();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		this.date = formatter.format(date);
	}

	String getAuthor() {
		return author;
	}

	String getTitle() {
		return title;
	}

	String getContent() {
		return content;
	}

	String[] getTags() {
		return tags;
	}
	
	void read() {
		this.timesRead++;
	}
	
	int getTimesRead()
	{
		return timesRead;
	}
	
	String getDate() {
		
		return date;
	}
	
	public void printStory() {
		System.out.println("Author: " + author + ", Title: " + title + ", Views: " + timesRead);
		System.out.println("\n" + content + "\n");
		System.out.println(date + " " + Arrays.toString(tags));
		System.out.println("-----------------------------------------------");
	}

	
	

}
