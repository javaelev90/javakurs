package java1.Storages;

import java.util.ArrayList;
import java.util.List;

public class Storage<T> {
	
	protected List<T> storage;
	
	public Storage(){
		storage = new ArrayList<T>();
	}
	
	public T addObject(T object) {
		storage.add(object);
		return object;
	}
	
	public T removeObject(T object) {
		storage.remove(object);
		return object;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(T object : storage) {
			builder.append(object.toString());
		}
		return builder.toString();
	}
}
