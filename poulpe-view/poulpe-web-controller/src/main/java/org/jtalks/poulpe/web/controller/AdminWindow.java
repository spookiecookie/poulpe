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
package org.jtalks.poulpe.web.controller;

import org.jtalks.poulpe.model.entity.Component;
import org.jtalks.poulpe.model.entity.ComponentType;
import org.jtalks.poulpe.service.ComponentService;
import org.jtalks.poulpe.web.controller.component.ComponentsVm;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Center;
import org.zkoss.zul.Window;

import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static org.jtalks.poulpe.web.controller.LocaleProvidingFilter.USER_LOCALE;

/**
 * Server-side representation of view for main 'Admin Window'.
 *
 * @author Vladimir Bukhoyarov
 * @author Vyacheslav Zhivaev
 * @author Alexandr Afanasev
 */

public class AdminWindow {
    public static final String RU_LOCALE_LANG = "ru";
    public static final String EN_LOCALE_LANG = "en";
    private ComponentType adminPanelType = ComponentType.ADMIN_PANEL; 

    @Wire
    private Center workArea;
    @Wire
    private Window adminWindow;
    private WindowManager windowManager;
    private ZkHelper zkHelper = new ZkHelper(adminWindow);
    private ComponentService componentService;
    private List<Component> components;                  

    @Init
    public void init(@ContextParam(ContextType.VIEW) org.zkoss.zk.ui.Component view) {
        zkHelper.wireComponents(view, this);
        windowManager.setWorkArea(workArea);
        components = componentService.getAll();
    }

    /**
     * Sets the service for working with components
     * @param componentService componentService to set
     */
    public void setComponentService(ComponentService componentService) {
        this.componentService = componentService;
    }

    /**
     * Returns the list of components, that were created
     * @return the list of components, that were created
     */
    public List<Component> getComponents() {
        return components;
    }

    /**
     * Returns the type of admin panel
     * @return the type of admin panel
     */
    public ComponentType getAdminPanelType() {
        return adminPanelType;
    }

    /**
     * Handles the event, when one of the components was clicked
     * @param component component, that was clicked
     */
    @Command
    public void onComponentClick(@BindingParam("component") Component component) {
        if (component.getComponentType().equals(ComponentType.FORUM)) {
            onShowForumStructure();
        } else {
            onBlankPage();
        }
    }
    
    /**
     * Sets Russian language for the admin panel.
     */
    @Command
    public void onChangeLocaleToRu() {
        changeLocaleAndReload(RU_LOCALE_LANG);
    }

    /**
     * Sets English language for the admin panel.
     */
    @Command
    public void onChangeLocaleToEn() {
        changeLocaleAndReload(EN_LOCALE_LANG);
    }

    private void changeLocaleAndReload(String localeLanguage) {
        saveLocaleInCookie(localeLanguage);
        zkHelper.reloadPage();
    }

    private void saveLocaleInCookie(String localeLanguage) {
        HttpServletResponse response = zkHelper.getResponse();
        response.addCookie(zkHelper.createCookie(USER_LOCALE, localeLanguage));
    }

    /**
     * Show the component list view
     */
    @Command
    public void onShowComponents() {
        ComponentsVm.show(windowManager);
    }

    /**
     * Show the branches list view
     */
    @Command
    public void onShowBranches() {
        windowManager.open("brancheditor.zul");
    }

    /**
     * Show the topic type list view
     */
    @Command
    public void onShowTopicTypes() {
        windowManager.open("topictype.zul");
    }

    /**
     * Points to the new implementation of Sections & Branches. Now it's called Forum Structure.
     */
    @Command
    public void onShowForumStructure() {
        windowManager.open("WEB-INF/pages/forum/structure/ForumStructure.zul");
    }

    /**
     * Shows user banning window
     */
    @Command
    public void onShowUserBanning() {
        windowManager.open("WEB-INF/pages/users/UserBanning.zul");
    }

    /**
     * Shows User Groups window that allows admins to CRUD groups.
     */
    @Command
    public void onShowUserGroups() {
        windowManager.open("usergroup.zul");
    }

    /**
     * Show the users list view
     */
    @Command
    public void onShowUsers() {
        windowManager.open("users.zul");
    }

    /**
     * Show the ranks page.
     */
    @Command
    public void onShowRanks() {
        windowManager.open("ranks.zul");
    }

    /**
     * Show Group Permissions page.
     */
    @Command
    public void onShowGroupsPermissions() {
        windowManager.open("groups/GroupsPermissions.zul");
    }

    @Command
    public void onShowPersonalPermissions() {
        windowManager.open("WEB-INF/pages/users/PersonalPermissions.zul");
    }

    /**
     * Show blank page.
     */
    @Command
    public void onBlankPage() {
        if (workArea.getLastChild() != null) {
            workArea.getLastChild().detach();
        }
    }

    /**
     * @param windowManager the new window manager
     */
    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    /**
     * @param zkHelper the zkHelper to set
     */
    public void setZkHelper(ZkHelper zkHelper) {
        this.zkHelper = zkHelper;
    }

}