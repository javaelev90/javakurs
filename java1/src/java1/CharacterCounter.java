package java1;

public class CharacterCounter implements Counter {

	@Override
	public int count(String text) {
		String[] chars = text.split("\\S");
		return chars.length;
	}

}
