package comparewithclipboard.popup.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.compare.IStreamContentAccessor;
import org.eclipse.compare.ITypedElement;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;

public class CompareItem implements ITypedElement, IStreamContentAccessor  {

	private String name;
	private String fContent;
	
	public CompareItem(String name, String content) {
		this.fContent = content;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Image getImage() {
		return null;
	}

	public String getType() {
		return "txt";
	}

	public InputStream getContents() throws CoreException {
		return new ByteArrayInputStream(fContent.getBytes());
	}


}
