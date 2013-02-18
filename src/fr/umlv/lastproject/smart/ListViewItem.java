package fr.umlv.lastproject.smart;

import java.util.HashMap;

public class ListViewItem {

	private final HashMap<String, String> item;

	public ListViewItem(String name, String img) {
		this.item = new HashMap<String, String>();
		this.item.put("name", name);
		this.item.put("img", img);
	}

	public HashMap<String, String> getItem() {
		return this.item;
	}
}
