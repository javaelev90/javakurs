package java1;

public class WordCounter implements Counter {

	@Override
	public int count(String text) {
		String[] words = text.split("[\\s]");
		return words.length;
	}

}
