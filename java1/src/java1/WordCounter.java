package java1;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCounter implements Counter {

	@Override
	public int count(String text) {
		Pattern pattern = Pattern.compile("\\w");
		List<String> words = Stream.of(text.split("\\b")).filter(pattern.asPredicate()).collect(Collectors.toList());
		return words.size();
	}

}
