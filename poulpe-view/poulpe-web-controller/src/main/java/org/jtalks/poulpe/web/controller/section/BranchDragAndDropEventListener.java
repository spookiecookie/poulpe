/**
 * Copyright (C) 2011  JTalks.org Team
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.jtalks.poulpe.web.controller.section;

import org.jtalks.poulpe.model.entity.PoulpeBranch;
import org.jtalks.poulpe.model.entity.PoulpeSection;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.GenericEventListener;
import org.zkoss.zul.Treeitem;

import java.util.List;

/**
 * Listener for Drag'n'Drop event.
 * 
 * @author Guram Savinov
 */
@SuppressWarnings("serial")
class BranchDragAndDropEventListener extends GenericEventListener<Event> {

    private final SectionPresenter presenter;

    /**
     * @param presenter section presenter
     */
    public BranchDragAndDropEventListener(SectionPresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Invoked when drop event occurred. Once section has changed its status, it
     * is saved.
     */
    @Override
    public void onEvent(Event evt) throws Exception {
        if (!(evt instanceof DropEvent)) {
            return;
        }
        DropEvent dropevent = (DropEvent) evt;
        Treeitem dragged = (Treeitem) dropevent.getDragged().getParent();
        Treeitem target = (Treeitem) dropevent.getTarget().getParent();

        int draggedIndex = dragged.getIndex();
        int targetIndex = target.getIndex();
        if ((targetIndex - 1) == draggedIndex) {
            return;
        } else if (targetIndex > draggedIndex) {
            targetIndex--;
        }

        PoulpeSection section = dragged.getParentItem().getValue();
        List<PoulpeBranch> branches = section.getPoulpeBranches();

        branches.remove(draggedIndex);
        PoulpeBranch value = dragged.getValue();
        branches.add(targetIndex, value);

        presenter.saveSection(section);

        target.getParent().insertBefore(dragged, target);
    }
}