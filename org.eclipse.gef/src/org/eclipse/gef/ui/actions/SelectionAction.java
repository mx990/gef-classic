package org.eclipse.gef.ui.actions;
/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001, 2002 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;

/**
 * Superclass for an action needing the current selection.
 */
public abstract class SelectionAction
	extends EditorPartAction
{

/*
 * The current selection.
 */
private ISelection selection;

/**
 * Creates a <code>SelectionAction</code> and associates it with the 
 * given editor.
 *
 * @param editor The editor that this action is associated with.
 */
public SelectionAction(IEditorPart editor) {
	super(editor);
}

public void dispose() {
	this.selection = StructuredSelection.EMPTY;
	super.dispose();
}

/**
 * Gets the current selection.
 *
 * @return The current selection.
 */
protected ISelection getSelection() {
	return selection;
}

/**
 * Returns a <code>List</code> containing the currently
 * selected objects.
 *
 * @return A List containing the currently selected objects.
 */
protected List getSelectedObjects() {
	if (!(getSelection() instanceof IStructuredSelection))
		return Collections.EMPTY_LIST;
	return ((IStructuredSelection)getSelection()).toList();
}

protected void handleSelectionChanged() {
	refresh();
}

/**
 * Sets the current selection and calls on subclasses 
 * to handle the selectionChanged event.
 *
 * @param selection The new selection.
 */
protected void setSelection(ISelection selection) {
	this.selection = selection;
	handleSelectionChanged();
}

/**
 * @see org.eclipse.gef.ui.actions.EditorPartAction#update()
 */
public void update() {
	setSelection(getEditorPart().getSite().getSelectionProvider().getSelection());
}

}
