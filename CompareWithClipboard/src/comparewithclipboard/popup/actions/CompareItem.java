package comparewithclipboard.popup.actions;

import org.eclipse.compare.ITypedElement;
import org.eclipse.swt.graphics.Image;

public class CompareItem implements ITypedElement {

	private String name;
	private String type;
	
	
	public CompareItem(String type, String name) {
		this.type = type;
		this.name = name;
	}

	@Override
	public Image getImage() {
		return null;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getType() {
		return this.type;
	}

}
