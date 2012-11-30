package comparewithclipboard.popup.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareUI;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class CompareWithClipBoardAction implements IEditorActionDelegate {

	private Shell shell;
	
	private final List<String> selectedText= new ArrayList<String>();
	
	
	/**
	 * Constructor for Action1.
	 */
	public CompareWithClipBoardAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		setShell(targetPart.getSite().getShell());
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		CompareConfiguration cc = new CompareConfiguration();
		cc.setLeftEditable(true);
		//cc.setProperty(NewAction.SELECTED_TEXT, selectedText.get(0));
		CompareUI.openCompareEditor(new CompareEditor(cc, selectedText.get(0)));
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		TextSelection tSelection = (TextSelection) selection;
		String text = tSelection.getText();
		selectedText.add(0,text);
		System.out.println(text);
	}

	@Override
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		// TODO Auto-generated method stub
	}

	public void setShell(Shell shell) {
		this.shell = shell;
	}

	public Shell getShell() {
		return shell;
	}

}
