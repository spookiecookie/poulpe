/**
 * Copyright (C) 2011  jtalks.org Team
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
 * Also add information on how to contact you by electronic and paper mail.
 * Creation date: Apr 12, 2011 / 8:05:19 PM
 * The jtalks.org Project
 */
package org.jtalks.poulpe.web.controller.component;

/**
 * Interface which represents information about component displayed in admin panel.
 * 
 * @author Dmitriy Sukharev
 * 
 */
public interface ItemView extends PlainComponent {

    /**
     * Returns the component's id.
     * @return the component's id
     */
    long getCid();

    /**
     * Sets the component's id.
     * @param cid the new value of the component's id
     */
    void setCid(long cid);

    /**
     * Returns the component's name.
     * @return the component's name
     */
    String getName();

    /**
     * Sets the component's name.
     * @param name the new value of the component's name
     */
    void setName(String name);

    /**
     * Returns the component's description.
     * @return the component's description
     */
    String getDescription();

    /**
     * Sets the component's description.
     * @param description the new value of the component's description.
     */
    void setDescription(String description);

    /**
     * Returns the component's type.
     * @return the component's type
     */
    String getComponentType();

    /**
     * Sets the component's type.
     * @param type the new value of the component's type
     */
    void setComponentType(String type);

//    /**
//     * Adds new or updates existing item in the list of visible items.
//     * @param component the component to be update in callback window
//     * @param isNew the flag to show if {@code component} must be saved, not update
//     */
//    void updateCallbackWindow(PlainComponentItem component, boolean isNew);

}
