package java1;

public class CharacterCounter implements Counter {

	@Override
	public int count(String text) {
		String textMod = text.replaceAll("[^a-zA-Z������]", "");
		return textMod.length();
	}

}
