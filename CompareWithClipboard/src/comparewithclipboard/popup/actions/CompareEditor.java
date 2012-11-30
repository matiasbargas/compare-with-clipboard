package comparewithclipboard.popup.actions;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareEditorInput;
import org.eclipse.compare.structuremergeviewer.Differencer;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

public class CompareEditor extends CompareEditorInput {

	private String selectedText;

	public CompareEditor(CompareConfiguration configuration, String selectedText) {
		super(configuration);
		this.selectedText = selectedText;
	}

	protected Object prepareInput(IProgressMonitor monitor)
			throws InvocationTargetException, InterruptedException {
		Differencer d = new Differencer();
		Object diff = d.findDifferences(
				false,
				new NullProgressMonitor(),
				null,
				null,
				new CompareItem("Selected Text", selectedText),
				new CompareItem("Clipboard", ClipboardUtils
						.getClipboardContents()));
		return diff;
	}

}