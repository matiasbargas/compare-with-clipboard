package comparewithclipboard.popup.actions;

import org.eclipse.compare.CompareEditorInput;
import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.compare.structuremergeviewer.Differencer;
import org.eclipse.core.runtime.IProgressMonitor;

public class CompareEditor extends CompareEditorInput{

	public CompareEditor(CompareConfiguration cc) {
		super(cc);	
     }
	
     protected Object prepareInput(IProgressMonitor pm) {
    	this.setTitle("Compare With Clipboard");
        CompareItem ancestor = 
           new CompareItem("Common", "contents");
        CompareItem left = 
           new CompareItem("Left", "new contents");
        CompareItem right = 
           new CompareItem("Right", "old contents");
        return new DiffNode(null, Differencer.CONFLICTING, 
           ancestor, left, right);
     }
     
     
}
