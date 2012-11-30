package comparewithclipboard.popup.actions;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.compare.CompareEditorInput;
import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.IStreamContentAccessor;
import org.eclipse.compare.ITypedElement;
import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.compare.structuremergeviewer.Differencer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.graphics.Image;

public class CompareEditor extends CompareEditorInput {
	
	private String selectedText;

	public CompareEditor(CompareConfiguration configuration, String selectedText) {
		super(configuration);
		this.selectedText = selectedText;
	}

	protected Object prepareInput(IProgressMonitor monitor)
			throws InvocationTargetException, InterruptedException {
		Differencer d = new Differencer();
		Object diff = d.findDifferences(false, new NullProgressMonitor(), null,
				null, new Input(selectedText), new Input(getClipboardContents()));
		return diff;
	}
	
	public String getClipboardContents() {
		String result = "";
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		//odd: the Object param of getContents is not currently used
		Transferable contents = clipboard.getContents(null);
		boolean hasTransferableText =
			(contents != null) &&
			contents.isDataFlavorSupported(DataFlavor.stringFlavor)
			;
		if ( hasTransferableText ) {
			try {
				result = (String)contents.getTransferData(DataFlavor.stringFlavor);
			}
			catch (UnsupportedFlavorException ex){
				//highly unlikely since we are using a standard DataFlavor
				System.out.println(ex);
				ex.printStackTrace();
			}
			catch (IOException ex) {
				System.out.println(ex);
				ex.printStackTrace();
			}
		}
		return result;
	}

}

class Input implements ITypedElement, IStreamContentAccessor {

	String fContent;

	public Input(String s) {
		fContent = s;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "name";
	}

	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getType() {
		// TODO Auto-generated method stub
		return "txt";
	}

	public InputStream getContents() throws CoreException {
		// TODO Auto-generated method stub
		return new ByteArrayInputStream(fContent.getBytes());
	}

}
