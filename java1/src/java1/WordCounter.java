package java1;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCounter implements Counter {

	@Override
	public int count(String text) {
		Pattern pattern = Pattern.compile("\\w");
		// Splits on word boundaries, then filter out non-words and collects them into a list
		List<String> words = Stream.of(text.split("\\b")).filter(pattern.asPredicate()).collect(Collectors.toList());
		return words.size();
	}

}
