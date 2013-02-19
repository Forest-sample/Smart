package fr.umlv.lastproject.smart;

/**
 * This class represent the Item in the Home ListView.
 * 
 * This Item contains a title and an icon.
 * 
 * @author Fad's
 * 
 */
public class ListViewItem {

	private int imageId;
	private String title;
	private boolean isEnabled;

	/**
	 * ListViewItem constructor
	 * 
	 * @param imageId
	 *            : icon for the item
	 * @param title
	 *            : title for the item
	 * @param enabled
	 *            : the item status
	 */
	public ListViewItem(int imageId, String title, boolean enabled) {
		this.imageId = imageId;
		this.title = title;
		this.isEnabled = enabled;
	}

	/**
	 * 
	 * @return the icon Id
	 */
	public int getImageId() {
		return imageId;
	}

	/**
	 * 
	 * @param imageId
	 *            : the icon Id to set
	 */
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	/**
	 * 
	 * @return the item title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @param title
	 *            : the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 * @return true if the item is enable, else false
	 */
	public boolean isEnabled() {
		return this.isEnabled;
	}

	/**
	 * 
	 * @param enabled
	 *            : status to set
	 */
	public void setEnabled(boolean enabled) {
		this.isEnabled = enabled;
	}

}