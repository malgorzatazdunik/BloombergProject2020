package news;

public class Main {
	public static void main(String[] args)
	{
		News news = new News();
		
		news.addStory("Malgorzata", "Coding", "ABC", new String[] {"happy", "sad"});
		news.addStory("Joanna", "Painting", "DEF", new String[] {"art", "work"});
		news.addStory("Kat", "Cooking", "GHI", new String[] {"work", "food", "hobby"});
		news.addStory("Michael", "Hello World", "JKL", new String[] {"code", "hobby", "lala"});
		news.addStory("Stephen King", "Green Mile", "TEXT OF GREEN MILE", new String[] {"art", "fiction", "horror", "books"});
		news.addStory("THEROUX", "WEIRD WEEKENDS", "TV SHOW LOUIS THEORUX BLAH BLAH", new String[] {"theroux", "tv", "BBC"});
		news.addStory("Hacker", "Cracking the coding interview", "Study study study", new String[] {"code", "books", "hobby"});
		news.addStory("Michael", "Hello World 2", "MNO", new String[] {"work", "hobby", "code"});
		news.addStory("Michael", "Hello World 3", "PQR", new String[]{"xyz", "code"});
		news.addStory("Hacker", "OOP", "Something SOmething", new String[] {});
		news.addStory("SSSSSSSSSS", "ZZZZZZZZZZZZZ", "OOOOOOOO", new String[] {});

		
		news.markStoryAsRead(0);
		news.markStoryAsRead(1);
		news.markStoryAsRead(1);
		news.markStoryAsRead(2);
		news.markStoryAsRead(6);
		news.markStoryAsRead(6);
		news.markStoryAsRead(6);
		news.markStoryAsRead(3);
		news.markStoryAsRead(4);
		news.markStoryAsRead(5);
		news.markStoryAsRead(5);
		news.markStoryAsRead(6);
		news.markStoryAsRead(7);
		news.markStoryAsRead(8);
		news.markStoryAsRead(9);
		news.markStoryAsRead(10);
		
		
		news.displayTopTenNews();
		//news.displayStoriesForAuthor("Michael");
		//news.displayStoriesForTags(new String[] {"hobby", "code", "lala"});
		
		
	}

}
